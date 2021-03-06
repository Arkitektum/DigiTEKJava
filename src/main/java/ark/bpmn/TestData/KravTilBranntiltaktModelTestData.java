package ark.bpmn.TestData;

import java.util.Map;

import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;

public class KravTilBranntiltaktModelTestData {
	public static final String IntegrationModelKey = "KravTilBranntiltakSubModel";
	public static final String UserTaskId = "UserKravTilBranntiltakSubModel";
	public static final String EndTaskId = "EndKravTilBranntiltakSubModel";
	public static final String ModelKey = "KravTilBranntiltaktModel";

	public class models {
		public static final String BpmnSub_KravTilBranntiltakt = "KravTilBranntiltaktSubModel.bpmn";
		public static final String Bpmn_KravTilBranntiltaktModel = "./Bpmn/Krav til branntiltak Model.bpmn";
	    public static final String Dmn_10a_KravBrannalarmKategori = "./Dmn/10a_KravBrannalarmKategori.dmn";
	    public static final String Dmn_10b_DetektorBrannalarmKategori = "./Dmn/10b_DetektorBrannalarmKategori.dmn";
	    public static final String Dmn_11_TiltakManuellBrannslokking = "./Dmn/11_TiltakManuellBrannslokking.dmn";
	    public static final String Dmn_20_BranncelleRomningUtgang = "./Dmn/20_BranncelleRomningUtgang.dmn";
	    public static final String Dmn_21_TiltakPavirkeRomningstidSlokkeanlegg = "./Dmn/21_TiltakPavirkeRomningstidSlokkeanlegg.dmn";
	    public static final String Dmn_22_TiltakPavirkeRomningstidAlarmanlegg = "./Dmn/22_TiltakPavirkeRomningstidAlarmanlegg.dmn";
	    public static final String Dmn_23_TiltakPavirkeRomningstidLedesystem = "./Dmn/23_TiltakPavirkeRomningstidLedesystem.dmn";
	    public static final String Dmn_24_TiltakPavirkeRomningstidEvakueringsplan = "./Dmn/24_TiltakPavirkeRomningstidEvakueringsplan.dmn";
	}

	public static Map<String, Object> KravTilBranntiltakt_Ukjent_Test() {
		VariableMap variableMap = Variables.createVariables()
				.putValue("rkl", "Ukjent");
		return variableMap;
	}

	public static Map<String, Object> KravTilBranntiltakt_AntallEtasjer_Test() {
		VariableMap variableMap = Variables.createVariables()
				.putValue("antallEtasjer", 2)
				.putValue("avstandMellomMotstVinduerIMeter", 3)
				.putValue("bkl", "BKL3")
				.putValue("brtArealBygg", 1201)
				.putValue("brtArealPrEtasje", 1201)
				.putValue("kravOmHeis", true)
				.putValue("rkl", "RKL4")
				.putValue("typeVirksomhet", "Kontor");
		return variableMap;
	}

	public static Map<String, Object> KravTilBranntiltakt_IkkeAntallEtasjer_Test() {
		VariableMap variableMap = Variables.createVariables()
				.putValue("antallEtasjer", null)
				.putValue("avstandMellomMotstVinduerIMeter", 3)
				.putValue("bkl", "BKL3")
				.putValue("brtArealBygg", 1201)
				.putValue("brtArealPrEtasje", 1201)
				.putValue("kravOmHeis", true)
				.putValue("rkl", "RKL4")
				.putValue("typeVirksomhet", "Kontor");
		return variableMap;
	}

	public static Map<String, Object> Dmn_BrannalarmKategori() {
		VariableMap variableMap = Variables.createVariables()
				.putValue("rkl", "RKL2")
				.putValue("antallEtasjer", 1);
		return variableMap;
	}
	public static Map<String, Object> Dmn_DetektorBrannalarmKategori() {
		VariableMap variableMap = Variables.createVariables()
				.putValue("brannalarmKategori", 1);
		return variableMap;
	}
	public static Map<String, Object> Dmn_KravBranndetektorRom() {
		VariableMap variableMap = Variables.createVariables()
				.putValue("brannalarmKategori", 2);
		return variableMap;
	}
	public static Map<String, Object> Dmn_TiltakManuellBrannslokking() {
		VariableMap variableMap = Variables.createVariables()
				.putValue("rkl", "RKL2");
		return variableMap;
	}
	public static Map<String, Object> Dmn_BranncelleRomningUtgang() {
		VariableMap variableMap = Variables.createVariables()
				.putValue("rkl", "RKL1");
		return variableMap;
	}
	public static Map<String, Object> Dmn_TiltakRomningstidSlokkeanlegg() {
		VariableMap variableMap = Variables.createVariables()
				.putValue("rkl", "RKL4")
				.putValue("kravOmHeis", true);
		return variableMap;
	}
	public static Map<String, Object> Dmn_TiltakRomningstidAlarmanlegg() {
		VariableMap variableMap = Variables.createVariables()
				.putValue("rkl", "RKL2")
				.putValue("typeVirksomhet", "Industri")
				.putValue("kravOmHeis", true)
				.putValue("brtArealBygg", 1201);
		return variableMap;
	}
	public static Map<String, Object> Dmn_TiltakRomningstidLedesystem() {
		VariableMap variableMap = Variables.createVariables()
				.putValue("rkl", "RKL2")
				.putValue("typeVirksomhet", "Kontor")
				.putValue("kravOmHeis", true)
				.putValue("brtArealBygg", 1201);
		return variableMap;
	}
	public static Map<String, Object> Dmn_TiltakRomningstidEvakueringsplan() {
		VariableMap variableMap = Variables.createVariables()
				.putValue("rkl", "RKL2")
				.putValue("typeVirksomhet", "Kontor")
				.putValue("brtArealBygg", 1201);
		return variableMap;
	}
}
