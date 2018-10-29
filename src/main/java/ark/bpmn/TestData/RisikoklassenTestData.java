package ark.bpmn.TestData;

import java.util.Map;

import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;

public class RisikoklassenTestData {

	public static final String ModelKey = "RisikoklasseModel";
	public static final String UserTaskId = "UserRisikoklasseModelOutput";
	public static final String EndTaskId = "EndRisikoklasseModel";
	public static final String IntegrationModelKey = "RisikoklasseIntModel";

	public static Map<String, Object> RisikoklasseOpt01() {
		VariableMap variableMap = Variables.createVariables().putValue("typeVirksomhet", "Sykehus");
		return variableMap;
	}

	public static Map<String, Object> RisikoklasseOpt02() {
		VariableMap variableMap = Variables.createVariables()
				.putValue("typeVirksomhet", null)
				.putValue("bareSporadiskPersonopphold", false)
				.putValue("alleKjennerRomningsVeiene", false)
				.putValue("beregnetForOvernatting", true)
				.putValue("liteBrannfarligAktivitet", true);
		return variableMap;
	}
	
	public static Map<String, Object> Dmn_Risikoklassifisering() {
		VariableMap variableMap = Variables.createVariables()
				.putValue("bareSporadiskPersonopphold", false)
				.putValue("alleKjennerRomningsVeiene", false)
				.putValue("beregnetForOvernatting", false)
				.putValue("liteBrannfarligAktivitet", true);
		return variableMap;
	}
	public static Map<String, Object> Dmn_RisikoklasseFraTypeVirksomhet() {
		VariableMap variableMap = Variables.createVariables().putValue("typeVirksomhet", "Sykehus");
		return variableMap;
	}
	public static Map<String, Object> Dmn_VedleggTilRisikoklasse() {
		VariableMap variableMap = Variables.createVariables().putValue("rkl", "RKL3");
		return variableMap;
	}
}
