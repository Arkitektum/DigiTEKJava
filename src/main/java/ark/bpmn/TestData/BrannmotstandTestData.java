package ark.bpmn.TestData;

import java.util.Map;

import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;

public class BrannmotstandTestData {

	public static final String ModelKey = "BrannmotstandModel";
	public static final String UserTaskId = "UserBrannmotstandModelOutput";
	public static final String EndTaskId = "EndBrannmotstandModel";
	public static final String IntegrationModelKey = "BrannmotstandModelIntModel";

	public static Map<String, Object> BrannmotstandOpt01() {
		VariableMap variableMap = Variables.createVariables()
				.putValue("bkl", "BKL1")
				.putValue("rkl", "RKL2")
				.putValue("antallEtasjer", 1)
				.putValue("arealBrannseksjonPrEtasje", 201);
		return variableMap;
	}

	public static Map<String, Object> BrannmotstandOpt02() {
		VariableMap variableMap = Variables.createVariables()
				.putValue("bkl", "BKL1")
				.putValue("rkl", "RKL2")
				.putValue("antallEtasjer", null)
				.putValue("arealBrannseksjonPrEtasje", 201);
		return variableMap;
	}
	
	public static Map<String, Object> Dmn_BrannmotstandBaerendeBygningsdeler() {
		VariableMap variableMap = Variables.createVariables()
				.putValue("bkl", "BKL1")
				.putValue("rkl", "RKL2")
				.putValue("antallEtasjer", 1);
		return variableMap;
	}
	public static Map<String, Object> Dmn_KlassifiseringTrapperom() {
		VariableMap variableMap = Variables.createVariables()
				.putValue("rkl", "RKL3")
				.putValue("antallEtasjer", 3);
		return variableMap;
	}
	public static Map<String, Object> Dmn_BrannmotstandTrapperom() {
		VariableMap variableMap = Variables.createVariables()
				.putValue("bkl", "BKL2")
				.putValue("trappeRomKlasse", "TR3");
		return variableMap;
	}
	public static Map<String, Object> Dmn_OverflateKledning() {
		VariableMap variableMap = Variables.createVariables()
				.putValue("arealBrannseksjonPrEtasje", 201)
				.putValue("bkl", "BKL2")
				.putValue("rkl", null);
		return variableMap;
	}
	public static Map<String, Object> Dmn_BrannmotstandIsolasjon() {
		VariableMap variableMap = Variables.createVariables()
				.putValue("rkl", "RKL1")
				.putValue("bkl", "BKL2");
		return variableMap;
	}
}
