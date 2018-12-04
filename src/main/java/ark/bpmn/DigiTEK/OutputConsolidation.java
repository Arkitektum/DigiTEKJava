package ark.bpmn.DigiTEK;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
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
				if(entryValue instanceof ArrayList<?>)
				{
				
					Map<String, Object> arrayMap = GetString(entryValue);
					Entry<String, Object> entry1 = arrayMap.entrySet().iterator().next();
					 String key= entry1.getKey();
					 String value=(String) entry1.getValue();
					 tableOutputsMap.put(key, value);
					
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
		Map<String, Object> tableOutputsMap = new HashMap<String, Object>();
		
		ArrayList<Map<String, Object>> array = (ArrayList<Map<String, Object>>)noko;
		String listSting = null;
		String key = String.join(",", array.toString());
		for (Map<String, Object> entry : array) {
			for (Map.Entry<String, Object> mapentry : entry.entrySet()) {
				if(listSting==null) {
					listSting=mapentry.getValue().toString();
					key=mapentry.getKey();
				}
				else
					listSting=listSting +","+ mapentry.getValue().toString();
			}
			
		}
		tableOutputsMap.put(key, listSting);
		
		return tableOutputsMap;
	}
}
