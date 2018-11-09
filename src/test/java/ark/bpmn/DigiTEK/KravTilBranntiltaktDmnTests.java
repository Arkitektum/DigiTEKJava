package ark.bpmn.DigiTEK;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.init;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.junit.Assert.assertEquals;

import java.util.Map;

import static ark.bpmn.TestData.KravTilBranntiltaktModelTestData.*;
import ark.bpmn.TestData.KravTilBranntiltaktModelTestData.models;

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



public class KravTilBranntiltaktDmnTests {
	@ClassRule
	@Rule
	public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

	@Before
	public void setup() {
		init(rule.getProcessEngine());
	}

	@Test
	@Deployment(resources = models.Dmn_10a_KravBrannalarmKategori)
	public void BrannalarmKategori_DmnTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("KravBrannalarmKategori",
				Dmn_BrannalarmKategori());
		assertEquals(1, decisionResult.getResultList().size());
	}

	@Test
	@Deployment(resources = models.Dmn_10a_KravBrannalarmKategori)
	public void BrannalarmKategori_DmnOutputTest() {
		Map<String, Object> variables = Dmn_BrannalarmKategori();
		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("KravBrannalarmKategori",
				variables);
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		// Print results
		System.out.println("DmnInput: " + variables);
		System.out.println("DmnOutput: " + result);
		// test
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
		Map<String, Object> variables = Dmn_DetektorBrannalarmKategori();
		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("DetektorBrannalarmKategori",
				variables);
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		// Print results
		System.out.println("DmnInput: " + variables);
		System.out.println("DmnOutput: " + result);
		// test
		assertThat(result)
				.containsOnly(entry("beskrBrannDetektor", "Optiske røykdetektorer i rømningsveier og fellesarealer"));
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
		Map<String, Object> variables = Dmn_TiltakManuellBrannslokking();
		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("TiltakManuellBrannslokking",
				variables);
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		// Print results
		System.out.println("DmnInput: " + variables);
		System.out.println("DmnOutput: " + result);
		// test
		assertThat(result).containsOnly(
				entry("kravManuellSlokking", "Håndslokkeapparat, evt. brannslange"),
				entry("maxBrannslangeLengde", "maks 30m lengde"));
	}

	@Test
	@Deployment(resources = models.Dmn_20_BranncelleRomningUtgang)
	public void BranncelleRomningUtgang_DmnTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("BranncelleRomningUtgang",
				Dmn_BranncelleRomningUtgang());
		assertEquals(1, decisionResult.getResultList().size());
	}

	@Test
	@Deployment(resources = models.Dmn_20_BranncelleRomningUtgang)
	public void BranncelleRomningUtgang_DmnOutputTest() {
		Map<String, Object> variables = Dmn_BranncelleRomningUtgang();
		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("BranncelleRomningUtgang",
				variables);
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		// Print results
		System.out.println("DmnInput: " + variables);
		System.out.println("DmnOutput: " + result);
		// test
		assertThat(result).containsOnly(
				entry("kravFriBreddeRomnVei", "1cm pr.pers / min. 0,86m"),
				entry("kravMinFriDorBredde", 0.86),
				entry("kravMinFriHoyde", 2.0),
				entry("kravMaxLengdeFluktvei", "50"),
				entry("avstandDorIBranncelle1Utgang", 15),
				entry("kravAvstandDorIBranncelleflereUtganger", 30));
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
		Map<String, Object> variables = Dmn_TiltakRomningstidSlokkeanlegg();
		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService
				.evaluateDecisionTableByKey("TiltakRomningstidSlokkeanlegg", variables);
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		// Print results
		System.out.println("DmnInput: " + variables);
		System.out.println("DmnOutput: " + result);
		// test
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
		Map<String, Object> variables = Dmn_TiltakRomningstidAlarmanlegg();
		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService
				.evaluateDecisionTableByKey("TiltakRomningstidAlarmanlegg", variables);
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		// Print results
		System.out.println("DmnInput: " + variables);
		System.out.println("DmnOutput: " + result);
		// test
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
		Map<String, Object> variables = Dmn_TiltakRomningstidLedesystem();
		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService
				.evaluateDecisionTableByKey("TiltakRomningstidLedesystem", variables);
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		// Print results
		System.out.println("DmnInput: " + variables);
		System.out.println("DmnOutput: " + result);
		// test
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
		Map<String, Object> variables = Dmn_TiltakRomningstidEvakueringsplan();
		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService
				.evaluateDecisionTableByKey("TiltakRomningstidEvakueringsplan", variables);
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		// Print results
		System.out.println("DmnInput: " + variables);
		System.out.println("DmnOutput: " + result);
		// test
		assertThat(result).containsOnly(entry("tiltakPavirkeRomningstid", "Evakueringsplan"),
				entry("kommentar", "Evakueringsplan hvis faste arbeidsplasser"));
	}
}
