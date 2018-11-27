package ark.bpmn.DigiTEK;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
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

import com.google.common.base.Charsets;

public class ModelOutputsDataDictionary implements JavaDelegate {

	@SuppressWarnings("unchecked")
	@Override
	public void execute(DelegateExecution execution) throws Exception {

		// get outputModel variables from execution
		Map<String, Object> modelOutputsMap = (Map<String, Object>) execution.getVariable("modelOutputs");
		Map<String, Object> modelInputsMap = (Map<String, Object>) execution.getVariable("modelInputs");

		// Get Json from file
		JSONArray dmnToTEKInfoJsonArray = new ModelOutputsDataDictionary().GetJsonFromFile("JsonDmn2TEK.json");
		JSONArray dmnVariablesInfoJsonArray = new ModelOutputsDataDictionary()
				.GetJsonFromFile("JsonDmnVariablesNames.json");
		JSONArray dmnTableAndVariablesJsonArray = new ModelOutputsDataDictionary()
				.GetJsonFromFile("JsonTable2Variables.json");

		// Start BrannDictionaryModel
		BrannDictionaryModel dictionaryModel = new BrannDictionaryModel();
		dictionaryModel.BranntekniskProsjekteringDictionary = new HashMap<String, TableInfo>();

		// Loop from all inputs in model output map
		try {
			for (Map.Entry<String, Object> entry : modelOutputsMap.entrySet()) {
				String entryKey = entry.getKey();

				// Get Dmn info from Json by DmnId
				char ch[] = entryKey.toCharArray();
				ch[0] = Character.toUpperCase(ch[0]);
				String dmnKeyTemp = new String(ch);

				JSONObject dmnInfo = new ModelOutputsDataDictionary().GetJsonObject(dmnToTEKInfoJsonArray, "DmnId",
						dmnKeyTemp);

				// Add dmn info to Model
				TableInfo tableInfo = new ModelOutputsDataDictionary().SetTableInfo(dmnInfo);

				// Get dmn outputs variables
				Map<String, Object> entryValues = (Map<String, Object>) entry.getValue();
				tableInfo.OutputVariablesInfo = new ModelOutputsDataDictionary().GetOutputsVariablesInfo(entryValues,
						dmnVariablesInfoJsonArray);

				// get all inputs to Dmn from json
				char c[] = entryKey.toCharArray();
				c[0] = Character.toUpperCase(c[0]);
				String newkey = new String(c);

				JSONArray jsonDmnInputs1 = new ModelOutputsDataDictionary()
						.GetJsonArrayObject(dmnTableAndVariablesJsonArray, "DmnId", newkey, "Type", "input");
				tableInfo.InputVariablesInfo = new ModelOutputsDataDictionary().GetinputsVariablesInfo(jsonDmnInputs1,
						dmnVariablesInfoJsonArray);

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

			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream inputStream = classLoader.getResourceAsStream("./Data/" + fileName);

			StringWriter writer = new StringWriter();
			IOUtils.copy(inputStream, writer, Charsets.UTF_8);
			String theString = writer.toString();

			// Read JSON file
			JSONParser parser = new JSONParser();
			Object jsonObject = parser.parse(theString);
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

	// @SuppressWarnings("unchecked")
	public TableInfo SetTableInfo(JSONObject dmnInfo) {

		TableInfo tableInfo = new BrannDictionaryModel().new TableInfo();
		
			tableInfo.DmnId = GetValue(dmnInfo.get("DmnId"));
			tableInfo.DmnNavn = GetValue(dmnInfo.get("DmnNavn"));
			tableInfo.TekKapitel = GetValue(dmnInfo.get("TekKapitel"));
			tableInfo.TekLedd = GetValue(dmnInfo.get("TekLedd"));
			tableInfo.TekTabell = GetValue(dmnInfo.get("TekTabell"));
			tableInfo.TekBokstav = GetValue(dmnInfo.get("TekBokstav"));
			tableInfo.TekWebLink = GetValue(dmnInfo.get("TekWebLink"));
			return tableInfo;
	}

	private String GetValue(Object value) {
		try {
			return (String) value;
		} catch (Exception e) {
			return null;
		}

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
