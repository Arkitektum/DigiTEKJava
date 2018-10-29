package ark.bpmn.TestData;

import java.util.Map;

import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;

public class BrannKlasseTestData {
	public static final String IntegrationModelKey = "BrannklasseIntModel";
	public static final String UserTaskId = "UserBrannklasseModelOutput";
	public static final String EndTaskId = "EndBrannklasseIntModel";
	public static final String ModelKey = "BrannklasseModel";
	
	public class models {
		public static final String BpmnInt_BrannKlasse = "BrannKlasseIntModel.bpmn";
		public static final String Bpmn_BrannKlasseModel = "./Bpmn/Brannklasse Model.bpmn";
		public static final String Dmn_02_KonsekvensBrannklassifisering ="./Dmn/02_KonsekvensBrannklassifisering.dmn";
		public static final String Dmn_02a_Brannklasse = "./Dmn/02a_Brannklasse.dmn";
		public static final String Dmn_02b_BrannklasseKonsekvensBeskrivelse = "./Dmn/02b_BrannklasseKonsekvensBeskrivelse.dmn";
	}
	
	public static Map<String, Object> BrannklasseOpt01() {
		VariableMap variableMap = Variables.createVariables().putValue("konsekvensAvBrann", "Middels konsekvens");
		return variableMap;
	}

	public static Map<String, Object> BrannklasseOpt02() {
		VariableMap variableMap = Variables.createVariables().putValue("konsekvensAvBrann", null)
				.putValue("typeVirksomhet", "Bolig").putValue("antallEtasjer", 3).putValue("rkl", "RKL4")
				.putValue("brtArealPrEtasje", 400).putValue("utgangTerrengAlleBoenheter", true);
		return variableMap;
	}
	
	public static Map<String, Object> Dmn_KonsekvensBrannklassifisering() {
		VariableMap variableMap = Variables.createVariables().putValue("konsekvensAvBrann", "Middels konsekvens");
		return variableMap;
	}
	public static Map<String, Object> Dmn_Brannklasse() {
		VariableMap variableMap = Variables.createVariables()
				.putValue("konsekvensAvBrann", null)
				.putValue("typeVirksomhet", "Hotell")
				.putValue("antallEtasjer", 2)
				.putValue("rkl", "RKL6")
				.putValue("brtArealPrEtasje", 299)
				.putValue("utgangTerrengAlleBoenheter", true);
		return variableMap;
	}
	public static Map<String, Object> Dmn_BrannklasseKonsekvensBeskrivelse() {
		VariableMap variableMap = Variables.createVariables().putValue("bkl", "BKL2");
		return variableMap;
	}

}
