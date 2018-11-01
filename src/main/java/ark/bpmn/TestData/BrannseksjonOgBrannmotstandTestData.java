package ark.bpmn.TestData;

import java.util.Map;

import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;

public class BrannseksjonOgBrannmotstandTestData {
	public static final String IntegrationModelKey = "BrannseksjonOgBrannmotstandSubModel";
	public static final String UserTaskId = "UserBrannseksjonOgBrannmotstandSubModel";
	public static final String EndTaskId = "EndBrannseksjonOgBrannmotstandSubModel";
	public static final String ModelKey = "BrannseksjonOgBrannmotstand";

	public class models {
		public static final String BpmnSub_BrannseksjonOgBrannmotstand = "BrannseksjonOgBrannmotstandSubModel.bpmn";
		public static final String Bpmn_BrannseksjonOgBrannmotstand = "./Bpmn/Brannseksjon og Brannmotstand Model.bpmn";
	    public static final String Dmn_03_TiltakStorrelseBrannseksjonBelastning = "./Dmn/03_TiltakStorrelseBrannseksjonBelastning.dmn";
	    public static final String Dmn_04_BrannmotstandSeksjoneringsvegg = "./Dmn/04_ BrannmotstandSeksjoneringsvegg.dmn";
	    public static final String Dmn_17_BrannmotstandDorISeksjvegg = "./Dmn/17_BrannmotstandDorISeksjvegg.dmn";
	    public static final String Dmn_07_BrannmotstandSkillendeKonstruksjon = "./Dmn/07_BrannmotstandSkillendeKonstruksjon.dmn";
	    public static final String Dmn_16_BrannmotstandDorRomningsvei = "./Dmn/16_BrannmotstandDorRomningsvei.dmn";
	    public static final String Dmn_18_BrannmotstandVinduMotstParallellYttervegg = "./Dmn/18_BrannmotstandVinduMotstParallellYttervegg.dmn";
	    public static final String Dmn_19_BrannmotstandVinduInnvHjørne = "./Dmn/19_BrannmotstandVinduInnvHjørne.dmn";

	}

	public static Map<String, Object> BrannseksjonOgBrannmotstand_Seksjonering_Test() {
		VariableMap variableMap = Variables.createVariables()
				.putValue("typeVirksomhet", "Bolig")
				.putValue("brannenergi", 801)
				.putValue("arealBrannseksjonPrEtasje", 5001)
				.putValue("avstandMellomMotstVinduerIMeter",3)
				.putValue("rkl", "RKL1")
				.putValue("bkl", "BKL1");
		return variableMap;
	}

	public static Map<String, Object> BrannseksjonOgBrannmotstand_test() {
		VariableMap variableMap = Variables.createVariables()
				.putValue("typeVirksomhet", "Bolig")
				.putValue("brannenergi", 399)
				.putValue("arealBrannseksjonPrEtasje", 1900)
				.putValue("avstandMellomMotstVinduerIMeter",3)
				.putValue("rkl", "RKL1")
				.putValue("bkl", "BKL1");
		
		return variableMap;
	}
	public static Map<String, Object> BrannseksjonOgBrannmotstand_Sykehjem_test() {
		VariableMap variableMap = Variables.createVariables()
				.putValue("typeVirksomhet", "Sykehjem")
				.putValue("brannenergi", 399)
				.putValue("arealBrannseksjonPrEtasje", 1900)
				.putValue("avstandMellomMotstVinduerIMeter",3)
				.putValue("rkl", "RKL6")
				.putValue("bkl", "BKL3");
		
		return variableMap;
	}
	public static Map<String, Object> Dmn_TiltakStorrelseBrannseksjonBelastning() {
		VariableMap variableMap = Variables.createVariables().putValue("typeVirksomhet", "Pleieinstitusjoner")
				.putValue("brannenergi", 49).putValue("arealBrannseksjonPrEtasje", 1801);
		return variableMap;
	}

	public static Map<String, Object> Dmn_BrannmotstandSeksjoneringsvegg() {
		VariableMap variableMap = Variables.createVariables().putValue("bkl", "BKL1").putValue("brannenergi", 801);
		return variableMap;
	}

	public static Map<String, Object> Dmn_BrannmotstandDorISeksjoneringsvegg() {
		VariableMap variableMap = Variables.createVariables().putValue("kravBrannmotstSeksjVegg",
				"REI 180-M A2-s1,d0 [A 180]");
		return variableMap;
	}

	public static Map<String, Object> Dmn_BrannmotstandSkillendeKonstruksjon() {
		VariableMap variableMap = Variables.createVariables().putValue("bkl", "BKL1");
		return variableMap;
	}

	public static Map<String, Object> Dmn_BrannmotstandDorRomningsvei() {
		VariableMap variableMap = Variables.createVariables().putValue("bkl", "BKL3");
		return variableMap;
	}

	public static Map<String, Object> Dmn_BrannmotstandVinduMotstParallellYttervegg() {
		VariableMap variableMap = Variables.createVariables().putValue("bkl", "BKL2")
				.putValue("avstandMellomMotstVinduerIMeter", 2);
		return variableMap;
	}

	public static Map<String, Object> Dmn_BrannmotstandVinduInnvendigHjorne() {
		VariableMap variableMap = Variables.createVariables()
				.putValue("bkl", "BKL1")
				.putValue("avstandMellomMotstVinduerIMeter", 3);
		return variableMap;
	}

}
