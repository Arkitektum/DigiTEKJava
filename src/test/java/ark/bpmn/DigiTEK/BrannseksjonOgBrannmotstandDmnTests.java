package ark.bpmn.DigiTEK;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.init;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.junit.Assert.assertEquals;

import java.util.Map;

import static ark.bpmn.TestData.BrannKlasseTestData.Dmn_Brannklasse;
import static ark.bpmn.TestData.BrannseksjonOgBrannmotstandTestData.*;

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

import ark.bpmn.TestData.BrannseksjonOgBrannmotstandTestData.models;

public class BrannseksjonOgBrannmotstandDmnTests {
	@ClassRule
	@Rule
	public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

	@Before
	public void setup() {
		init(rule.getProcessEngine());
	}

	@Test
	@Deployment(resources = models.Dmn_03_TiltakStorrelseBrannseksjonBelastning)
	public void TiltakStorrelseBrannseksjonBelastning_DmnTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey(
				"TiltakStorrelseBrannseksjonBelastning", Dmn_TiltakStorrelseBrannseksjonBelastning());
		assertEquals(1, decisionResult.getResultList().size());
	}

	@Test
	@Deployment(resources = models.Dmn_03_TiltakStorrelseBrannseksjonBelastning)
	// 01_Risikoklassifisering.dmn
	public void TiltakStorrelseBrannseksjonBelastning_DmnOutputTest() {
		Map<String, Object> variables = Dmn_Brannklasse();
		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey(
				"TiltakStorrelseBrannseksjonBelastning", Dmn_TiltakStorrelseBrannseksjonBelastning());
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		// Print results
		System.out.println("DmnInput: " + variables);
		System.out.println("DmnOutput: " + result);
		// test
		assertThat(result).containsOnly(entry("brannTiltakStrSeksjonBelastning", "Minst to brannseksjoner"));
	}

	@Test
	@Deployment(resources = "./Dmn/04_ BrannmotstandSeksjoneringsvegg.dmn")
	public void BrannmotstandSeksjoneringsvegg_DmnTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService
				.evaluateDecisionTableByKey("BrannmotstandSeksjoneringsvegg", Dmn_BrannmotstandSeksjoneringsvegg());
		assertEquals(1, decisionResult.getResultList().size());
	}

	@Test
	@Deployment(resources = "./Dmn/04_ BrannmotstandSeksjoneringsvegg.dmn")
	public void BrannmotstandSeksjoneringsvegg_DmnOutputTest() {
		Map<String, Object> variables = Dmn_Brannklasse();
		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService
				.evaluateDecisionTableByKey("BrannmotstandSeksjoneringsvegg", Dmn_BrannmotstandSeksjoneringsvegg());
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		// Print results
		System.out.println("DmnInput: " + variables);
		System.out.println("DmnOutput: " + result);
		// test
		assertThat(result).containsOnly(entry("kravBrannmotstSeksjVegg", "Analyse"));
	}

	@Test
	@Deployment(resources = "./Dmn/17_BrannmotstandDorISeksjvegg.dmn")
	public void BrannmotstandDorISeksjoneringsvegg_DmnTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey(
				"BrannmotstandDorISeksjoneringsvegg", Dmn_BrannmotstandDorISeksjoneringsvegg());
		assertEquals(1, decisionResult.getResultList().size());
	}

	@Test
	@Deployment(resources = "./Dmn/17_BrannmotstandDorISeksjvegg.dmn")
	public void BrannmotstandDorISeksjoneringsvegg_DmnOutputTest() {
		Map<String, Object> variables = Dmn_Brannklasse();
		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey(
				"BrannmotstandDorISeksjoneringsvegg", Dmn_BrannmotstandDorISeksjoneringsvegg());
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		// Print results
		System.out.println("DmnInput: " + variables);
		System.out.println("DmnOutput: " + result);
		// test
		assertThat(result).containsOnly(entry("kravBrannmotstandDorISeksjvegg", "EI-180C"));
	}

	@Test
	@Deployment(resources = "./Dmn/07_BrannmotstandSkillendeKonstruksjon.dmn")
	public void BrannmotstandSkillendeKonstruksjon_DmnTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey(
				"BrannmotstandSkillendeKonstruksjon", Dmn_BrannmotstandSkillendeKonstruksjon());
		assertEquals(1, decisionResult.getResultList().size());
	}

	@Test
	@Deployment(resources = "./Dmn/07_BrannmotstandSkillendeKonstruksjon.dmn")
	public void BrannmotstandSkillendeKonstruksjon_DmnOutputTest() {
		Map<String, Object> variables = Dmn_Brannklasse();
		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey(
				"BrannmotstandSkillendeKonstruksjon", Dmn_BrannmotstandSkillendeKonstruksjon());
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		// Print results
		System.out.println("DmnInput: " + variables);
		System.out.println("DmnOutput: " + result);
		// test
		assertThat(result).containsOnly(entry("kravBranncelleBegrensKonstruksjon", "EI-30 [B 30]"),
				entry("kravBygningRundtTrapperomHeisEtc", "EI-30 [B 30]"), entry("kravHeisMaskinRom", "EI-60 [B 60]"),
				entry("kravFyrromSVAFastBrensel", "EI-60 [B 60]"),
				entry("kravFyrromSVAInnfyrtEffektmax50", "K 2 10 A2-s1,d0 [K1-A]"),
				entry("kravFyrromSVAInnfyrtEffekt50p100", "EI 30 [B 30]"),
				entry("kravFyrromSVAInnfyrtEffektmin100", "EI 60 A2-s1,d0 [A 60]"));
	}

	@Test
	@Deployment(resources = "./Dmn/16_BrannmotstandDorRomningsvei.dmn")
	public void BrannmotstandDorRomningsvei_DmnTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService
				.evaluateDecisionTableByKey("BrannmotstandDorRomningsvei", Dmn_BrannmotstandDorRomningsvei());
		assertEquals(1, decisionResult.getResultList().size());
	}

	@Test
	@Deployment(resources = "./Dmn/16_BrannmotstandDorRomningsvei.dmn")
	public void BrannmotstandDorRomningsvei_DmnOutputTest() {
		Map<String, Object> variables = Dmn_Brannklasse();
		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService
				.evaluateDecisionTableByKey("BrannmotstandDorRomningsvei", Dmn_BrannmotstandDorRomningsvei());
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		// Print results
		System.out.println("DmnInput: " + variables);
		System.out.println("DmnOutput: " + result);
		// test
		assertThat(result).containsOnly(
				entry("kravDorBranncelleTrapperomTr1", "EI2 30-Sa[B30S]"),
				entry("kravDorBoenhetTrapperom", "EI2 30-Sa"), 
				entry("kravDorKorridorTrapperomTr2", "EI2 30-Sa[F30S]"),
				entry("kravDorRomTrapperomTr3", "EI2 60-Sa[B60S]"),
				entry("kravDorGarasjeBrannsluse", "EI2 60-Sa[B60S]"),
				entry("kravDorBranncelleKorridor", "EI2 30-Sa[B30]"),
				entry("kravDorKorridorFriLuft", "EI2 30-Sa[B30]"));
	}

	@Test
	@Deployment(resources = "./Dmn/18_BrannmotstandVinduMotstParallellYttervegg.dmn")
	public void BrannmotstandVinduMotstParallellYttervegg_DmnTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey(
				"BrannmotstandVinduMotstParallellYttervegg", Dmn_BrannmotstandVinduMotstParallellYttervegg());
		assertEquals(1, decisionResult.getResultList().size());
	}

	@Test
	@Deployment(resources = "./Dmn/18_BrannmotstandVinduMotstParallellYttervegg.dmn")
	public void BrannmotstandVinduMotstParallellYttervegg_DmnOutputTest() {
		Map<String, Object> variables = Dmn_Brannklasse();
		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey(
				"BrannmotstandVinduMotstParallellYttervegg", Dmn_BrannmotstandVinduMotstParallellYttervegg());
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		// Print results
		System.out.println("DmnInput: " + variables);
		System.out.println("DmnOutput: " + result);
		// test
		assertThat(result).containsOnly(entry("kravMotstParallellYttervegg1Vindu", "EI 60"),
				entry("kravMotstParallellYtterveggBegge", "EI 30"));
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
		Map<String, Object> variables = Dmn_Brannklasse();
		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey(
				"BrannmotstandVinduInnvendigHjorne", Dmn_BrannmotstandVinduInnvendigHjorne());
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		// Print results
		System.out.println("DmnInput: " + variables);
		System.out.println("DmnOutput: " + result);
		// test
		assertThat(result).containsOnly(entry("kravAvstandInnvHjorne1Vindu", "E 30 [F 30]"),
				entry("kravAvstandInnvHjorneBegge", "EI 15"));
	}
}
