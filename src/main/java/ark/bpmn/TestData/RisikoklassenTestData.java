package ark.bpmn.TestData;

import java.util.Map;

import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;

public class RisikoklassenTestData {

	public static final String ModelKey = "RisikoklasseModel";
	public static final String UserTaskId = "UserRisikoklasseModelOutput";
	public static final String EndTaskId = "EndRisikoklasseModel";
	public static final String IntegrationModelKey = "RisikoklasseIntModel";
	
	public class models {
		public static final String BpmnInt_Risikoklasse = "RisikoklasseModelIntModel.bpmn";
		public static final String Bpmn_RisikoklasseModel = "./Bpmn/Risikoklasse Model.bpmn";
		public static final String Dmn_01_Risikoklassifisering ="./Dmn/01_Risikoklassifisering.dmn";
		public static final String Dmn_01a_RisikoklasseFraTypeVirksomhet = "./Dmn/01a_RisikoklasseFraTypeVirksomhet.dmn";
		public static final String Dmn_01b_VedleggTilRisikoklasse = "./Dmn/01b_VedleggTilRisikoklasse.dmn";
	}
	

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
