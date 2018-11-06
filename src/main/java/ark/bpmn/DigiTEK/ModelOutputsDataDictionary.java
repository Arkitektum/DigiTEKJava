package ark.bpmn.DigiTEK;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import ark.bpmn.BrannModels.BrannDictionaryModel;
import ark.bpmn.BrannModels.BrannDictionaryModel.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ModelOutputsDataDictionary implements JavaDelegate {

	@SuppressWarnings("unchecked")
	@Override
	public void execute(DelegateExecution execution) throws Exception {

		// get outputModel variables from execution
		Map<String, Object> modelOutputsMap = (Map<String, Object>) execution.getVariable("modelOutputs");
		Map<String, Object> modelInputsMap = (Map<String, Object>) execution.getVariable("modelInputs");

		// Get Json from file
		JSONArray dmnInfoJsonArray = new ModelOutputsDataDictionary().GetJsonFromFile("JsonDmn2TEK.json");
		JSONArray jsonDmnVariablesNamesArray = new ModelOutputsDataDictionary()
				.GetJsonFromFile("JsonDmnVariablesNames.json");
		JSONArray jsonTable2VariablesArray = new ModelOutputsDataDictionary()
				.GetJsonFromFile("JsonTable2Variables.json");

		// Start BrannDictionaryModel
		BrannDictionaryModel dictionaryModel = new BrannDictionaryModel();
		dictionaryModel.BranntekniskProsjekteringDictionary = new HashMap<String, TableInfo>();

		// Loop from all inputs in model output map
		try {
			for (Map.Entry<String, Object> entry : modelOutputsMap.entrySet()) {
				String entryKey = entry.getKey();

				// Get Dmn info from Json by DmnId
				JSONObject dmnInfo = new ModelOutputsDataDictionary().GetJsonObject(dmnInfoJsonArray, "DmnId",
						entryKey);

				// Add dmn info to Model
				TableInfo tableInfo = new ModelOutputsDataDictionary().SetTableInfo(dmnInfo);

				// Get dmn outputs variables
				Map<String, Object> entryValues = (Map<String, Object>) entry.getValue();
				tableInfo.OutputVariablesInfo = new ModelOutputsDataDictionary().GetOutputsVariablesInfo(entryValues,
						jsonDmnVariablesNamesArray);

				// get all inputs to Dmn from json
				JSONArray jsonDmnInputs1 = new ModelOutputsDataDictionary().GetJsonArrayObject(jsonTable2VariablesArray,
						"DmnId", entryKey, "VariabelType", "input");
				tableInfo.InputVariablesInfo = new ModelOutputsDataDictionary().GetinputsVariablesInfo(jsonDmnInputs1,
						jsonDmnVariablesNamesArray);

				// add table info to map and BranntekniskProsjekteringDictionary model
				dictionaryModel.BranntekniskProsjekteringDictionary.put(entryKey, tableInfo);
			}

		} catch (Exception e) {

		}

		ObjectValue modelDataDictionary = Variables.objectValue(dictionaryModel.BranntekniskProsjekteringDictionary)
				.serializationDataFormat("application/json").create();

		execution.removeVariables();
		execution.setVariable("modelDataDictionary", modelDataDictionary);
		execution.setVariable("modelOutputs", modelOutputsMap);
		execution.setVariable("modelInputs", modelInputsMap);

	}

	public JSONArray GetJsonFromFile(String fileName) throws ParseException {
		JSONArray jsonArray = null;
		try {
			Path currentRelativePath = Paths
					.get("C:\\Code\\Arkitektum\\DigiTEK\\src\\main\\resources\\Data\\" + fileName);
			byte[] encoded = Files.readAllBytes(currentRelativePath);
			String jsonString = new String(encoded);

			// Read JSON file
			JSONParser parser = new JSONParser();
			Object jsonObject = parser.parse(jsonString);
			jsonArray = (JSONArray) jsonObject;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	public JSONObject GetJsonObject(JSONArray jsonArray, String key, String value) {

		JSONObject SelectjsonObj = null;
		try {
			for (int index = 0; index < jsonArray.size(); index++) {
				JSONObject jsonObject = (JSONObject) jsonArray.get(index);
				if (jsonObject.get(key).equals(value)) {
					SelectjsonObj = jsonObject;
					break;
					// ind= index; //Index of the JSONObject
				}
			}
		} catch (Exception e) {
			return null;
		}
		if (SelectjsonObj == null) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("DmnId", 0);
		}

		return SelectjsonObj;
	}

	public JSONArray GetJsonArrayObject(JSONArray jsonInputArray, String key, String value, String key2,
			String value2) {

		try {
			JSONArray jsonOutputArray = new JSONArray();
			for (int index = 0; index < jsonInputArray.size(); index++) {
				JSONObject jsonObject = (JSONObject) jsonInputArray.get(index);
				if (jsonObject.get(key).equals(value) && jsonObject.get(key2).equals(value2)) {
					jsonOutputArray.add(jsonObject);
					// ind= index; //Index of the JSONObject
				}
			}
			return jsonOutputArray;
		} catch (Exception e) {
			return null;
		}
	}

	public TableInfo SetTableInfo(JSONObject dmnInfo) {

		TableInfo tableInfo = new BrannDictionaryModel().new TableInfo();
		try {
			tableInfo.DmnId = dmnInfo.get("DmnId").toString();
			tableInfo.DmnNavn = dmnInfo.get("DmnNavn").toString();
			tableInfo.TekKapitel = dmnInfo.get("TekKapitel").toString();
			tableInfo.TekLedd = dmnInfo.get("TekLedd").toString();
			tableInfo.TekTabell = dmnInfo.get("TekTabell").toString();
			tableInfo.TekForskriften = dmnInfo.get("TekForskriften").toString();
			tableInfo.TekWebLink = dmnInfo.get("TekWebLink").toString();
		} catch (Exception e) {

		}

		return tableInfo;
	}

	public VariablesInfo SetVariableInfo(JSONObject json) {
		VariablesInfo variableInfo = new BrannDictionaryModel().new VariablesInfo();

		try {
			variableInfo.VariabelId = json.get("VariabelId").toString();
			variableInfo.VariabelNavn = json.get("VariabelNavn").toString();
			variableInfo.VariabelBeskrivelse = json.get("VariabelBeskrivelse").toString();
		} catch (Exception e) {
			return null;
		}
		return variableInfo;
	}

	public VariablesInfo[] GetOutputsVariablesInfo(Map<String, Object> outputs, JSONArray jsonDmnVariablesNamesArray) {
		List<VariablesInfo> outputVariablesInfo = new ArrayList<>();
		// loop throw all outputs from dmn
		for (Map.Entry<String, Object> entryValue : outputs.entrySet()) {
			String variabelId = entryValue.getKey();
			// Get variable info from Json
			JSONObject jsonVariableInfo = GetJsonObject(jsonDmnVariablesNamesArray, "VariabelId", variabelId);
			// Add variables info to list
			if (jsonVariableInfo != null) {
				outputVariablesInfo.add(SetVariableInfo(jsonVariableInfo));
			}
		}
		// add outputs variables information to table info
		VariablesInfo[] outputVariablesArray = outputVariablesInfo
				.toArray(new VariablesInfo[outputVariablesInfo.size()]);
		return outputVariablesArray;
	}

	public VariablesInfo[] GetinputsVariablesInfo(JSONArray jsonDmnInputs, JSONArray jsonDmnVariablesNamesArray) {
		List<VariablesInfo> inputVariablesInfo = new ArrayList<>();
		// loop throw Dmn inputs
		for (Object o : jsonDmnInputs) {
			if (o instanceof JSONObject) {
				// get variableId from the Json object
				String variabelId = ((JSONObject) o).get("VariabelId").toString();
				// Get variable info from Json
				JSONObject jsonVariableInfo = GetJsonObject(jsonDmnVariablesNamesArray, "VariabelId", variabelId);
				inputVariablesInfo.add(SetVariableInfo(jsonVariableInfo));
			}
		}
		// Add dmn inputs to table info
		VariablesInfo[] inputVariablesArray = inputVariablesInfo.toArray(new VariablesInfo[inputVariablesInfo.size()]);
		return inputVariablesArray;
	}

}
