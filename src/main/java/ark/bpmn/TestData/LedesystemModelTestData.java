package ark.bpmn.TestData;

import java.util.Map;

import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;

public class LedesystemModelTestData {
	public static final String IntegrationModelKey = "LedesystemIntModel";
	public static final String UserTaskId = "UserLedesystemModelOutput";
	public static final String EndTaskId = "EndLedesystemModel";
	public static final String ModelKey = "LedesystemModel";

	public class models {
		public static final String BpmnInt_LedesystemModel = "LedesystemModelIntModel.bpmn";
		public static final String Bpmn_LedesystemModel = "./Bpmn/Ledesystem Model.bpmn";
		public static final String Dmn_12a_LedesystemEvakuering = "./Dmn/12a_LedesystemEvakuering.dmn";
		public static final String Dmn_12b_LedesystemEvakueringVarighet = "./Dmn/12b_LedesystemEvakueringVarighet.dmn";
	}

	public static Map<String, Object> LedesystemModelOpt01() {
		VariableMap variableMap = Variables.createVariables().putValue("rkl", "RKL5")
				.putValue("bygningOffentligUnderTerreng", true);
		return variableMap;
	}

	public static Map<String, Object> LedesystemModelOpt02() {
		VariableMap variableMap = Variables.createVariables().putValue("rkl", "RKL5")
				.putValue("bygningOffentligUnderTerreng", null);
		return variableMap;
	}

	public static Map<String, Object> Dmn_LedesystemEvakuering() {
		VariableMap variableMap = Variables.createVariables().putValue("rkl", "RKL5")
				.putValue("bygningOffentligUnderTerreng", true);
		return variableMap;
	}

	public static Map<String, Object> Dmn_LedesystemEvakueringVarighet() {
		VariableMap variableMap = Variables.createVariables().putValue("bkl", "BKL2")
				.putValue("kravLedesystemEvakuering", true);
		return variableMap;
	}
}
