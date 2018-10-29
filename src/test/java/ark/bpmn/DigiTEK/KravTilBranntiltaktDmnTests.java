package ark.bpmn.DigiTEK;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.init;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.junit.Assert.assertEquals;

import java.util.Map;

import static ark.bpmn.TestData.KravTilBranntiltaktModelTestData.*;

import org.camunda.bpm.dmn.engine.DmnDecisionRuleResult;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.engine.DecisionService;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import ark.bpmn.TestData.KravTilBranntiltaktModelTestData.models;

public class KravTilBranntiltaktDmnTests {
	@ClassRule
	@Rule
	public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

	@Before
	public void setup() {
		init(rule.getProcessEngine());
	}

	@Test
	@Deployment(resources = models.Dmn_10a_BrannalarmKategori)
	public void BrannalarmKategori_DmnTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("BrannalarmKategori",
				Dmn_BrannalarmKategori());
		assertEquals(1, decisionResult.getResultList().size());
	}

	@Test
	@Deployment(resources = models.Dmn_10a_BrannalarmKategori)
	public void BrannalarmKategori_DmnOutputTest() {

		Map<String, Object> variables = Dmn_BrannalarmKategori();
		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("BrannalarmKategori",
				variables);
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		System.out.println("DmnInput: " + variables);
		System.out.println("DmnOutput: " + result);
		assertThat(result).containsOnly(entry("brannalarmKategori", 1));
	}

	@Test
	@Deployment(resources = models.Dmn_10b_DetektorBrannalarmKategori)
	public void DetektorBrannalarmKategori_DmnTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("DetektorBrannalarmKategori",
				Dmn_DetektorBrannalarmKategori());
		assertEquals(1, decisionResult.getResultList().size());
	}

	@Test
	@Deployment(resources = models.Dmn_10b_DetektorBrannalarmKategori)
	public void DetektorBrannalarmKategori_DmnOutputTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		Map<String, Object> variables = Dmn_DetektorBrannalarmKategori();

		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("DetektorBrannalarmKategori",
				variables);
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		System.out.println("DmnInput: " + variables);
		System.out.println("DmnOutput: " + result);
		assertThat(result)
				.containsOnly(entry("beskrBrannDetektor", "Optiske røykdetektorer i rømningsveier og fellesarealer"));
	}

	@Test
	@Deployment(resources = models.Dmn_10b_KravBranndetektorRom)
	public void KravBranndetektorRom_DmnTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("KravBranndetektorRom",
				Dmn_KravBranndetektorRom());
		assertEquals(1, decisionResult.getResultList().size());
	}

	@Test
	@Deployment(resources = models.Dmn_10b_KravBranndetektorRom)
	public void KravBranndetektorRom_DmnOutputTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		Map<String, Object> variables = Dmn_KravBranndetektorRom();

		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("KravBranndetektorRom",
				variables);
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		System.out.println("DmnInput: " + variables);
		System.out.println("DmnOutput: " + result);
		assertThat(result).containsOnly(entry("kravBranndetektorRomningsvei", "Røykdetektor"),
				entry("kravBranndetektorFellesrom", "Røykdetektor"), entry("kravBrannsetektorSengerom", "Røykdetektor"),
				entry("kravBranndetektorTekniskRom", "Røykdetektor"),
				entry("kravBranndetektorLoft", "Varme- eller Røykdetektor"),
				entry("kravBranndetektorKjeller", "Varme- eller Røykdetektor"),
				entry("kravBranndetektorAndre", "Varme- eller Røykdetektor"));
	}

	@Test
	@Deployment(resources = models.Dmn_11_TiltakManuellBrannslokking)
	public void TiltakManuellBrannslokking_DmnTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("TiltakManuellBrannslokking",
				Dmn_TiltakManuellBrannslokking());
		assertEquals(1, decisionResult.getResultList().size());
	}

	@Test
	@Deployment(resources = models.Dmn_11_TiltakManuellBrannslokking)
	public void TiltakManuellBrannslokking_DmnOutputTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		Map<String, Object> variables = Dmn_TiltakManuellBrannslokking();

		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("TiltakManuellBrannslokking",
				variables);
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		System.out.println("DmnInput: " + variables);
		System.out.println("DmnOutput: " + result);
		assertThat(result).containsOnly(entry("kravManuellSlokking", "Håndslokkeapparat, evt. brannslange"),
				entry("maxBrannslangeLengde", "maks 30m lengde"));
	}

	@Test
	@Deployment(resources = models.Dmn_19_BrannmotstandVinduInnvHjørne)
	public void BrannmotstandVinduInnvendigHjorne_DmnTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey(
				"BrannmotstandVinduInnvendigHjorne", Dmn_BrannmotstandVinduInnvendigHjorne());
		assertEquals(1, decisionResult.getResultList().size());
	}

	@Test
	@Deployment(resources = models.Dmn_19_BrannmotstandVinduInnvHjørne)
	public void BrannmotstandVinduInnvendigHjorne_DmnOutputTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		Map<String, Object> variables = Dmn_BrannmotstandVinduInnvendigHjorne();

		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService
				.evaluateDecisionTableByKey("BrannmotstandVinduInnvendigHjorne", variables);
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		System.out.println("DmnInput: " + variables);
		System.out.println("DmnOutput: " + result);
		assertThat(result).containsOnly(entry("kravAvstandInnvHjorne1Vindu", "E 60 [F 60]"),
				entry("kravAvstandInnvHjorneBegge", "E 30 [F 30]"));
	}

	@Test
	@Deployment(resources = models.Dmn_21_TiltakPavirkeRomningstidSlokkeanlegg)
	public void TiltakRomningstidSlokkeanlegg_DmnTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService
				.evaluateDecisionTableByKey("TiltakRomningstidSlokkeanlegg", Dmn_TiltakRomningstidSlokkeanlegg());
		assertEquals(1, decisionResult.getResultList().size());
	}

	@Test
	@Deployment(resources = models.Dmn_21_TiltakPavirkeRomningstidSlokkeanlegg)
	public void TiltakRomningstidSlokkeanlegg_DmnOutputTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		Map<String, Object> variables = Dmn_TiltakRomningstidSlokkeanlegg();

		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService
				.evaluateDecisionTableByKey("TiltakRomningstidSlokkeanlegg", variables);
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		System.out.println("DmnInput: " + variables);
		System.out.println("DmnOutput: " + result);
		assertThat(result).containsOnly(entry("tiltakPavirkeRomningstid", "Brannslokkeanlegg"));
	}

	@Test
	@Deployment(resources = models.Dmn_22_TiltakPavirkeRomningstidAlarmanlegg)
	public void TiltakRomningstidAlarmanlegg_DmnTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService
				.evaluateDecisionTableByKey("TiltakRomningstidAlarmanlegg", Dmn_TiltakRomningstidAlarmanlegg());
		assertEquals(1, decisionResult.getResultList().size());
	}

	@Test
	@Deployment(resources = models.Dmn_22_TiltakPavirkeRomningstidAlarmanlegg)
	public void TiltakRomningstidAlarmanlegg_DmnOutputTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		Map<String, Object> variables = Dmn_TiltakRomningstidAlarmanlegg();

		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService
				.evaluateDecisionTableByKey("TiltakRomningstidAlarmanlegg", variables);
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		System.out.println("DmnInput: " + variables);
		System.out.println("DmnOutput: " + result);
		assertThat(result).containsOnly(entry("tiltakPavirkeRomningstid", "Brannalarmanlegg"),
				entry("kommentar", "Forutsatt enkle og oversiktelige rømningsforhold"));
	}

	@Test
	@Deployment(resources = models.Dmn_23_TiltakPavirkeRomningstidLedesystem)
	public void TiltakRomningstidLedesystem_DmnTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService
				.evaluateDecisionTableByKey("TiltakRomningstidLedesystem", Dmn_TiltakRomningstidLedesystem());
		assertEquals(1, decisionResult.getResultList().size());
	}

	@Test
	@Deployment(resources = models.Dmn_23_TiltakPavirkeRomningstidLedesystem)
	public void TiltakRomningstidLedesystem_DmnOutputTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		Map<String, Object> variables = Dmn_TiltakRomningstidLedesystem();

		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService
				.evaluateDecisionTableByKey("TiltakRomningstidLedesystem", variables);
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		System.out.println("DmnInput: " + variables);
		System.out.println("DmnOutput: " + result);
		assertThat(result).containsOnly(entry("tiltakPavirkeRomningstid", "Ledesystem"));
	}

	@Test
	@Deployment(resources = models.Dmn_24_TiltakPavirkeRomningstidEvakueringsplan)
	public void TiltakRomningstidEvakueringsplan_DmnTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService
				.evaluateDecisionTableByKey("TiltakRomningstidEvakueringsplan", Dmn_TiltakRomningstidEvakueringsplan());
		assertEquals(1, decisionResult.getResultList().size());
	}

	@Test
	@Deployment(resources = models.Dmn_24_TiltakPavirkeRomningstidEvakueringsplan)
	public void TiltakRomningstidEvakueringsplan_DmnOutputTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		Map<String, Object> variables = Dmn_TiltakRomningstidEvakueringsplan();

		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService
				.evaluateDecisionTableByKey("TiltakRomningstidEvakueringsplan", variables);
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		System.out.println("DmnInput: " + variables);
		System.out.println("DmnOutput: " + result);
		assertThat(result).containsOnly(entry("tiltakPavirkeRomningstid", "Evakueringsplan"),
				entry("kommentar", "Evakueringsplan hvis faste arbeidsplasser"));
	}
}
