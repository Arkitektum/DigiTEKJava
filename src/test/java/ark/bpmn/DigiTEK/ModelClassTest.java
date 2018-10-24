package ark.bpmn.DigiTEK;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.spin.plugin.variable.SpinValues;
import org.camunda.spin.plugin.variable.value.JsonValue;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ark.bpmn.BrannModels.BrannDictionaryModel;
import ark.bpmn.BrannModels.BrannDictionaryModel.TableInfo;
import spinjar.com.fasterxml.jackson.databind.ObjectMapper;
import spinjar.com.fasterxml.jackson.databind.ObjectWriter;

import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

public class ModelClassTest {

	DelegateExecution execution = Mockito.mock(DelegateExecution.class);
	InputsValidation inputsValidation = new InputsValidation();
	ModelOutputsDataDictionary modelOutputsDataDictionary = new ModelOutputsDataDictionary();

	@Before
	public void setup() {
		// execution.setVariable("typeVirksomhet", "Bolig");
	}

	@Test
	public void InputsValidation() throws Exception {
		execution.setVariable("typeVirksomhet", "Bolig");
		inputsValidation.execute(execution);
		System.out.println(execution.getVariables());

		verify(execution, times(1)).setVariable("typeVirksomhet", "Bolig");
	}

	@Test
	public void OutputsValidation() throws Exception {
		Map<String, Object> variabels = new HashMap<String, Object>();
		variabels.put("beregnetForOvernatting", true);
		variabels.put("liteBrannfarligAktivitet", true);
		variabels.put("bareSporadiskPersonopphold", false);
		variabels.put("alleKjennerRomningsVeiene", true);

		Map<String, Object> modelOutputsMap = new HashMap<String, Object>();
		modelOutputsMap.put("risikoklasseFraTypeVirksomhet", variabels);

		Map<String, Object> modelDataDictionary = new HashMap<>();

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
		for (Map.Entry<String, Object> entry : modelOutputsMap.entrySet()) {
			String entryKey = entry.getKey();

			// Get Dmn info from Json by DmnId
			JSONObject dmnInfo = new ModelOutputsDataDictionary().GetJsonObject(dmnInfoJsonArray, "DmnId", entryKey);

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
			dictionaryModel.BranntekniskProsjekteringDictionary.put("entryKey", tableInfo);
			modelDataDictionary.put(entryKey, tableInfo);
		}
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(modelDataDictionary);
		JsonValue jsonValue = SpinValues.jsonValue(json).create();
		
		
	}

}
