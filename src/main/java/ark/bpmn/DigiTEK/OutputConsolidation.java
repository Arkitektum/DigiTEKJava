package ark.bpmn.DigiTEK;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class OutputConsolidation implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Map<String, Object> variables = execution.getVariables();
		Map<String, Object> tableOutputsMap = new HashMap<String, Object>();
		String bkl = (String) variables.get("bkl");

		if (bkl != null && bkl.equalsIgnoreCase("BKL4")) {
			tableOutputsMap.put("Advarsel", "BKL4 Analyse");
		} else {

			for (Map.Entry<String, Object> entry : variables.entrySet()) {
				String entryKey = entry.getKey();
				Object entryValue = entry.getValue();
				if (entryKey.contains("model"))
					continue;
				if (entryValue instanceof ArrayList<?>) {

					Map<String, Object> arrayMap = GetString(entryValue);
					if (!arrayMap.isEmpty()) {
						if (entryKey.contains("Forklaring"))
							entryKey = "risikoklasseForklaring";
						tableOutputsMap.put(entryKey, arrayMap);

					}

				}

				if (entryValue instanceof HashMap) {
					tableOutputsMap.put(entryKey, entryValue);
				}
			}

			if (tableOutputsMap.isEmpty()) {
				tableOutputsMap.put("Advarsel", "Inputs gir ikke noe svar");
			}
		}

		execution.setVariable("modelOutputs", tableOutputsMap);
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> GetString(Object noko) {
		Map<String, Object> dmnCollectionMap = new HashMap<String, Object>();

		ArrayList<Map<String, Object>> arrayMap = (ArrayList<Map<String, Object>>) noko;
		String mergeString = null;
		String key = null;
		for (Map<String, Object> entry : arrayMap) {

			Entry<String, Object> mapTemp = entry.entrySet().iterator().next();
			String value = (String) mapTemp.getValue();
			if (mergeString == null) {
				mergeString = value;
				key = mapTemp.getKey();
			} else
				mergeString = mergeString + "," + value;

		}
		dmnCollectionMap.put(key, mergeString);

		return dmnCollectionMap;
	}
}
