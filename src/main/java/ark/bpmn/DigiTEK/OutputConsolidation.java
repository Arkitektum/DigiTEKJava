package ark.bpmn.DigiTEK;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class OutputConsolidation implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Map<String, Object> variables = execution.getVariables();
		Map<String, Object> tableOutputsMap = new HashMap<String, Object>();
		String bkl = (String) variables.get("bkl");
		if (bkl == "BKL4") {
			tableOutputsMap.put("Advarsel", "BKL4 Analyse");
		} else {

			for (Map.Entry<String, Object> entry : variables.entrySet()) {
				String entryKey = entry.getKey();
				Object entryValue = entry.getValue();
				if (entryKey.contains("model"))
					continue;
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

}
