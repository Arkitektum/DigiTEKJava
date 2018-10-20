package ark.bpmn.DigiTEK;

import java.util.Map;

import org.apache.velocity.runtime.directive.Foreach;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;


import ark.bpmn.BrannModels.BranntekniskProsjekteringModel;

public class InputsValidation implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		BranntekniskProsjekteringModel model = new BranntekniskProsjekteringModel();
		
		model.typeVirksomhet= CheckString(execution.getVariable("typeVirksomhet"));
		model.antallEtasjer= CheckLong(execution.getVariable("antallEtasjer"));
		model.brtArealPrEtasje= CheckLong(execution.getVariable("brtArealPrEtasje"));
		model.utgangTerrengAlleBoenheter= CheckBoolean(execution.getVariable("utgangTerrengAlleBoenheter"));
		
		model.bareSporadiskPersonopphold= CheckString(execution.getVariable("bareSporadiskPersonopphold"));
		model.alleKjennerRomningsVeiene= CheckBoolean(execution.getVariable("alleKjennerRomningsVeiene"));
		model.beregnetForOvernatting= CheckBoolean(execution.getVariable("beregnetForOvernatting"));
		model.liteBrannfarligAktivitet= CheckBoolean(execution.getVariable("liteBrannfarligAktivitet"));
		
		model.konsekvensAvBrann= CheckString(execution.getVariable("konsekvensAvBrann"));
		model.arealBrannseksjonPrEtasje= CheckLong(execution.getVariable("arealBrannseksjonPrEtasje"));
		model.brannenergi= CheckLong(execution.getVariable("brannenergi"));
		model.bygningOffentligUnderTerreng= CheckBoolean(execution.getVariable("bygningOffentligUnderTerreng"));

		model.avstandMellomMotstVinduerIMeter=CheckLong(execution.getVariable("antallEtasjer"));
		model.rkl= CheckString(execution.getVariable("rkl"));
		model.bkl= CheckString(execution.getVariable("bkl"));
		model.brannalarmKategori=CheckLong(execution.getVariable("brannalarmKategori"));
		model.brannTiltakStrSeksjonBelastning= CheckString(execution.getVariable("brannTiltakStrSeksjonBelastning"));
		model.kravBrannmotstSeksjVegg= CheckString(execution.getVariable("kravBrannmotstSeksjVegg"));
		model.kravLedesystemEvakuering= CheckBoolean(execution.getVariable("kravLedesystemEvakuering"));
		model.trappeRomKlasse= CheckString(execution.getVariable("trappeRomKlasse"));
		
		Map<String, Object> modelInputsVariables = model.getObjectMap();
		Map<String, Object> map = model.getObjectMap();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			Object value= entry.getValue();
			execution.setVariable(entry.getKey(), entry.getValue());
			if(value!= null) {
				modelInputsVariables.put(entry.getKey(), entry.getValue());
			}
		}

		execution.setVariable("modelInputsVariables",modelInputsVariables);
	}

	private Object CheckBoolean(Object value) {
		try {
			return (Boolean) value;
		}
		catch (Exception e)
		{
			return null;
		}
	}
	private Long CheckLong(Object value) {
		try {
			return (Long) value;
		}
		catch (Exception e)
		{
			return null;
		}
	}
	private String CheckString(Object value) {
		try {
			return (String) value;
		}
		catch (Exception e)
		{
			return null;
		}
	}
}
