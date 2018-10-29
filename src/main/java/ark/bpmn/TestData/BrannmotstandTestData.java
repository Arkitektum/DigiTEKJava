package ark.bpmn.TestData;

import java.util.Map;

import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;

public class BrannmotstandTestData {

	public static final String ModelKey = "BrannmotstandModel";
	public static final String UserTaskId = "UserBrannmotstandModelOutput";
	public static final String EndTaskId = "EndBrannmotstandModel";
	public static final String IntegrationModelKey = "BrannmotstandIntModel";

	public class models {
		public static final String Bpmn_BrannmotstandModel = "./Bpmn/Brannmotstand Model.bpmn";
		public static final String BpmnInt_BrannmotstandModelInt = "BrannmotstandModelIntModel.bpmn";
		public static final String Dmn_06_BrannmotstandBaerendeBygningsdeler = "./Dmn/06_BrannmotstandBaerendeBygningsdeler.dmn";
		public static final String Dmn_08a_KlassifiseringTrapperom = "./Dmn/08a_KlassifiseringTrapperom.dmn";
		public static final String Dmn_08b_BrannmotstandTrapperom = "./Dmn/08b_BrannmotstandTrapperom.dmn";
		public static final String Dmn_13_OverflateKledning = "./Dmn/13_OverflateKledning.dmn";
		public static final String Dmn_15_BrannmotstandIsolasjon = "./Dmn/15_BrannmotstandIsolasjon.dmn";
	}

	public static Map<String, Object> BrannmotstandOpt01() {
		VariableMap variableMap = Variables.createVariables().putValue("bkl", "BKL1").putValue("rkl", "RKL2")
				.putValue("antallEtasjer", 1).putValue("arealBrannseksjonPrEtasje", 201);
		return variableMap;
	}

	public static Map<String, Object> BrannmotstandOpt02() {
		VariableMap variableMap = Variables.createVariables().putValue("bkl", "BKL1").putValue("rkl", "RKL2")
				.putValue("antallEtasjer", null).putValue("arealBrannseksjonPrEtasje", 201);
		return variableMap;
	}

	public static Map<String, Object> Dmn_BrannmotstandBaerendeBygningsdeler() {
		VariableMap variableMap = Variables.createVariables().putValue("bkl", "BKL1").putValue("rkl", "RKL2")
				.putValue("antallEtasjer", 1);
		return variableMap;
	}

	public static Map<String, Object> Dmn_KlassifiseringTrapperom() {
		VariableMap variableMap = Variables.createVariables().putValue("rkl", "RKL3").putValue("antallEtasjer", 3);
		return variableMap;
	}

	public static Map<String, Object> Dmn_BrannmotstandTrapperom() {
		VariableMap variableMap = Variables.createVariables().putValue("bkl", "BKL2").putValue("trappeRomKlasse",
				"TR3");
		return variableMap;
	}

	public static Map<String, Object> Dmn_OverflateKledning() {
		VariableMap variableMap = Variables.createVariables().putValue("arealBrannseksjonPrEtasje", 201)
				.putValue("bkl", "BKL2").putValue("rkl", null);
		return variableMap;
	}

	public static Map<String, Object> Dmn_BrannmotstandIsolasjon() {
		VariableMap variableMap = Variables.createVariables().putValue("rkl", "RKL1").putValue("bkl", "BKL2");
		return variableMap;
	}
}
