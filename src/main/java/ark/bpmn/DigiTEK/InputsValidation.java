package ark.bpmn.DigiTEK;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import ark.bpmn.BrannModels.BranntekniskProsjekteringModel;

public class InputsValidation implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		BranntekniskProsjekteringModel model = new BranntekniskProsjekteringModel();
		
		model.typeVirksomhet = CheckString(execution.getVariable("typeVirksomhet"));
		model.antallEtasjer = CheckInteger(execution.getVariable("antallEtasjer"));
		model.brtArealPrEtasje = CheckInteger(execution.getVariable("brtArealPrEtasje"));
		model.utgangTerrengAlleBoenheter = CheckBoolean(execution.getVariable("utgangTerrengAlleBoenheter"));

		model.bareSporadiskPersonopphold = CheckBoolean(execution.getVariable("bareSporadiskPersonopphold"));
		model.alleKjennerRomningsVeiene = CheckBoolean(execution.getVariable("alleKjennerRomningsVeiene"));
		model.beregnetForOvernatting = CheckBoolean(execution.getVariable("beregnetForOvernatting"));
		model.liteBrannfarligAktivitet = CheckBoolean(execution.getVariable("liteBrannfarligAktivitet"));

		model.konsekvensAvBrann = CheckString(execution.getVariable("konsekvensAvBrann"));
		model.arealBrannseksjonPrEtasje = CheckInteger(execution.getVariable("arealBrannseksjonPrEtasje"));
		model.brannenergi = CheckInteger(execution.getVariable("brannenergi"));
		model.bygningOffentligUnderTerreng = CheckBoolean(execution.getVariable("bygningOffentligUnderTerreng"));

		model.avstandMellomMotstVinduerIMeter = CheckInteger(execution.getVariable("avstandMellomMotstVinduerIMeter"));
		model.brtArealBygg = CheckInteger(execution.getVariable("brtArealBygg"));
		model.kravOmHeis = CheckBoolean(execution.getVariable("kravOmHeis"));

		// sub models inputs
		model.rkl = CheckString(execution.getVariable("rkl"));
		model.bkl = CheckString(execution.getVariable("bkl"));
		model.brannalarmKategori = CheckInteger(execution.getVariable("brannalarmKategori"));
		model.brannTiltakStrSeksjonBelastning = CheckString(execution.getVariable("brannTiltakStrSeksjonBelastning"));
		model.kravBrannmotstSeksjVegg = CheckString(execution.getVariable("kravBrannmotstSeksjVegg"));
		model.kravLedesystemEvakuering = CheckBoolean(execution.getVariable("kravLedesystemEvakuering"));
		model.trappeRomKlasse = CheckString(execution.getVariable("trappeRomKlasse"));

		Map<String, Object> modelInputs = new HashMap<String, Object>();
		Map<String, Object> map = model.getObjectMap();

		// ObjectValue modelDataDictionary = Variables.objectValue(model)
		// .serializationDataFormat("application/json").create();
		execution.setVariables(map);

		for (Map.Entry<String, Object> entry : map.entrySet()) {
			Object value = entry.getValue();
			execution.setVariable(entry.getKey(), entry.getValue());
			if (value != null) {
				modelInputs.put(entry.getKey(), entry.getValue());
			}
		}

		execution.setVariable("modelInputs", modelInputs);
	}

	private Boolean CheckBoolean(Object value) {
		try {

			return (boolean) value;

		} catch (Exception e) {
			return null;
		}
	}

	private Integer CheckInteger(Object value) {
		try {
			return (Integer) value;
		} catch (Exception e) {
			return null;
		}
	}

	private String CheckString(Object value) {
		try {
			return (String) value;
		} catch (Exception e) {
			return null;
		}
	}
}
