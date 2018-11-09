package ark.bpmn.TestData;

import java.util.Map;

import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;

public class BranntekniskProsjekteringTestData {
	public static final String IntegrationModelKey = "BranntekniskProsjekteringModel";
	public static final String UserTaskId = "UserBranntekniskProsjekteringModelOutput";
	public static final String EndTaskId = "EndBranntekniskProsjekteringModel";

	public class models {
		public static final String BpmnInt_BranntekniskProsjektering = "BranntekniskProsjekteringModel.bpmn";
		public static final String Dmn_05_TiltakBrannveggHoyeBygg ="./Dmn/05_TiltakBrannveggHoyeBygg.dmn";
		
		//BrannKlasse
		public static final String Bpmn_BrannklasseModel = "./Bpmn/Brannklasse Model.bpmn";
		public static final String Dmn_02_KonsekvensBrannklassifisering ="./Dmn/02_KonsekvensBrannklassifisering.dmn";
		public static final String Dmn_02a_Brannklasse = "./Dmn/02a_Brannklasse.dmn";
		public static final String Dmn_02b_BrannklasseKonsekvensBeskrivelse = "./Dmn/02b_BrannklasseKonsekvensBeskrivelse.dmn";
		//Brannmostand
		public static final String Bpmn_BrannmotstandModel = "./Bpmn/Brannmotstand Model.bpmn";
		public static final String Dmn_06_BrannmotstandBaerendeBygningsdeler = "./Dmn/06_BrannmotstandBaerendeBygningsdeler.dmn";
		public static final String Dmn_08a_KlassifiseringTrapperom = "./Dmn/08a_KlassifiseringTrapperom.dmn";
		public static final String Dmn_08b_BrannmotstandTrapperom = "./Dmn/08b_BrannmotstandTrapperom.dmn";
		public static final String Dmn_13_OverflateKledning = "./Dmn/13_OverflateKledning.dmn";
//		public static final String Dmn_15_BrannmotstandIsolasjon = "./Dmn/15_BrannmotstandIsolasjon.dmn";
		//BrannsekjonOgBrannmostand
		public static final String Bpmn_BrannseksjonOgBrannmotstand = "./Bpmn/Brannseksjon og Brannmotstand Model.bpmn";
	    public static final String Dmn_03_TiltakStorrelseBrannseksjonBelastning = "./Dmn/03_TiltakStorrelseBrannseksjonBelastning.dmn";
	    public static final String Dmn_04_BrannmotstandSeksjoneringsvegg = "./Dmn/04_ BrannmotstandSeksjoneringsvegg.dmn";
	    public static final String Dmn_17_BrannmotstandDorISeksjvegg = "./Dmn/17_BrannmotstandDorISeksjvegg.dmn";
	    public static final String Dmn_07_BrannmotstandSkillendeKonstruksjon = "./Dmn/07_BrannmotstandSkillendeKonstruksjon.dmn";
	    public static final String Dmn_16_BrannmotstandDorRomningsvei = "./Dmn/16_BrannmotstandDorRomningsvei.dmn";
	    public static final String Dmn_18_BrannmotstandVinduMotstParallellYttervegg = "./Dmn/18_BrannmotstandVinduMotstParallellYttervegg.dmn";
	    public static final String Dmn_20_BranncelleRomningUtgang = "./Dmn/20_BranncelleRomningUtgang.dmn";
		//Krav Branntiltak
	    public static final String Bpmn_KravTilBranntiltaktModel = "./Bpmn/Krav til branntiltak Model.bpmn";
	    public static final String Dmn_10a_KravBrannalarmKategori = "./Dmn/10a_KravBrannalarmKategori.dmn";
	    public static final String Dmn_10b_DetektorBrannalarmKategori = "./Dmn/10b_DetektorBrannalarmKategori.dmn";
//	    public static final String Dmn_10b_KravBranndetektorRom = "./Dmn/10b_KravBranndetektorRom.dmn";
	    public static final String Dmn_11_TiltakManuellBrannslokking = "./Dmn/11_TiltakManuellBrannslokking.dmn";
	    public static final String Dmn_19_BrannmotstandVinduInnvHjørne = "./Dmn/19_BrannmotstandVinduInnvHjørne.dmn";
	    public static final String Dmn_21_TiltakPavirkeRomningstidSlokkeanlegg = "./Dmn/21_TiltakPavirkeRomningstidSlokkeanlegg.dmn";
	    public static final String Dmn_22_TiltakPavirkeRomningstidAlarmanlegg = "./Dmn/22_TiltakPavirkeRomningstidAlarmanlegg.dmn";
	    public static final String Dmn_23_TiltakPavirkeRomningstidLedesystem = "./Dmn/23_TiltakPavirkeRomningstidLedesystem.dmn";
	    public static final String Dmn_24_TiltakPavirkeRomningstidEvakueringsplan = "./Dmn/24_TiltakPavirkeRomningstidEvakueringsplan.dmn";
	    //Ledesystem
		public static final String BpmnInt_LedesystemModel = "LedesystemModelIntModel.bpmn";
		public static final String Bpmn_LedesystemModel = "./Bpmn/Ledesystem Model.bpmn";
		public static final String Dmn_12a_LedesystemEvakuering = "./Dmn/12a_LedesystemEvakuering.dmn";
		public static final String Dmn_12b_LedesystemEvakueringVarighet = "./Dmn/12b_LedesystemEvakueringVarighet.dmn";
		//Risikoklasse
		public static final String Bpmn_RisikoklasseModel = "./Bpmn/Risikoklasse Model.bpmn";
		public static final String Dmn_01_Risikoklassifisering ="./Dmn/01_Risikoklassifisering.dmn";
		public static final String Dmn_01a_RisikoklasseFraTypeVirksomhet = "./Dmn/01a_RisikoklasseFraTypeVirksomhet.dmn";
		public static final String Dmn_01b_VedleggTilRisikoklasse = "./Dmn/01b_VedleggTilRisikoklasse.dmn";

	}

	public static Map<String, Object> BranntekniskProsjekteringModelOpt01() {
		VariableMap variableMap = Variables.createVariables().putValue("typeVirksomhet", "Bolig")
				.putValue("antallEtasjer", 3).putValue("brtArealPrEtasje", 400)
				.putValue("utgangTerrengAlleBoenheter", true).putValue("brannenergi", 100)
				.putValue("bygningOffentligUnderTerreng", true).putValue("arealBrannseksjonPrEtasje", 2000);
		return variableMap;
	}

	public static Map<String, Object> BranntekniskProsjekteringModelOpt02() {
		VariableMap variableMap = Variables.createVariables().putValue("typeVirksomhet", "Bolig")
				.putValue("antallEtasjer", 3).putValue("brtArealPrEtasje", 400)
				.putValue("utgangTerrengAlleBoenheter", true).putValue("brannenergi", 100)
				.putValue("bygningOffentligUnderTerreng", true).putValue("arealBrannseksjonPrEtasje", 2000);
		return variableMap;
	}

	public static Map<String, Object> Dmn_TiltakBrannveggHoyeBygg() {
		VariableMap variableMap = Variables.createVariables().putValue("brannenergi", 401);
		return variableMap;
	}

}
