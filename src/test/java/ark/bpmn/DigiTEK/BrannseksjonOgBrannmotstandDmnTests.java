package ark.bpmn.DigiTEK;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.init;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.junit.Assert.assertEquals;

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
	@Deployment(resources =models.Dmn_03_TiltakStorrelseBrannseksjonBelastning)
	public void TiltakStorrelseBrannseksjonBelastning_DmnTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("TiltakStorrelseBrannseksjonBelastning",
				Dmn_TiltakStorrelseBrannseksjonBelastning());
		assertEquals(1, decisionResult.getResultList().size());
	}
	@Test
	@Deployment(resources =models.Dmn_03_TiltakStorrelseBrannseksjonBelastning)
	// 01_Risikoklassifisering.dmn
	public void TiltakStorrelseBrannseksjonBelastning_DmnOutputTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("TiltakStorrelseBrannseksjonBelastning",
				Dmn_TiltakStorrelseBrannseksjonBelastning());
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		System.out.println("DmnInput: "+Dmn_TiltakStorrelseBrannseksjonBelastning());
		System.out.println("DmnOutput: "+result);
	    assertThat(result)
	      .containsOnly(
	        entry("brannTiltakStrSeksjonBelastning", "Minst to brannseksjoner")
	      );
	}
	@Test
	@Deployment(resources ="./Dmn/04_ BrannmotstandSeksjoneringsvegg.dmn")
	public void BrannmotstandSeksjoneringsvegg_DmnTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("BrannmotstandSeksjoneringsvegg",
				Dmn_BrannmotstandSeksjoneringsvegg());
		assertEquals(1, decisionResult.getResultList().size());
	}
	@Test
	@Deployment(resources ="./Dmn/04_ BrannmotstandSeksjoneringsvegg.dmn")
	public void BrannmotstandSeksjoneringsvegg_DmnOutputTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("BrannmotstandSeksjoneringsvegg",
				Dmn_BrannmotstandSeksjoneringsvegg());
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		System.out.println(result);
	    assertThat(result)
	      .containsOnly(
	        entry("kravBrannmotstSeksjVegg", "Analyse")
	      );
	}
	@Test
	@Deployment(resources ="./Dmn/17_BrannmotstandDorISeksjvegg.dmn")
	public void BrannmotstandDorISeksjoneringsvegg_DmnTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("BrannmotstandDorISeksjoneringsvegg",
				Dmn_BrannmotstandDorISeksjoneringsvegg());
		assertEquals(1, decisionResult.getResultList().size());
	}
	@Test
	@Deployment(resources ="./Dmn/17_BrannmotstandDorISeksjvegg.dmn")
	public void BrannmotstandDorISeksjoneringsvegg_DmnOutputTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("BrannmotstandDorISeksjoneringsvegg",
				Dmn_BrannmotstandDorISeksjoneringsvegg());
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		System.out.println(result);
	    assertThat(result)
	      .containsOnly(
	        entry("kravBrannmotstandDorISeksjvegg", "EI-180C")
	      );
	}
	@Test
	@Deployment(resources ="./Dmn/07_BrannmotstandSkillendeKonstruksjon.dmn")
	public void BrannmotstandSkillendeKonstruksjon_DmnTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("BrannmotstandSkillendeKonstruksjon",
				Dmn_BrannmotstandSkillendeKonstruksjon());
		assertEquals(1, decisionResult.getResultList().size());
	}
	@Test
	@Deployment(resources ="./Dmn/07_BrannmotstandSkillendeKonstruksjon.dmn")
	public void BrannmotstandSkillendeKonstruksjon_DmnOutputTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("BrannmotstandSkillendeKonstruksjon",
				Dmn_BrannmotstandSkillendeKonstruksjon());
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		System.out.println(result);
	    assertThat(result)
	      .containsOnly(
	        entry("kravBranncelleBegrensKonstruksjon","EI-30 [B 30]" ),
	        entry("kravBygningRundtTrapperomHeisEtc","EI-30 [B 30]" ),
	        entry("kravHeisMaskinRom", "EI-60 [B 60]"),
	        entry("kravFyrromSVAFastBrensel","EI-60 [B 60]" ),
	        entry("kravFyrromSVAInnfyrtEffektmax50","K 2 10 A2-s1,d0 [K1-A]" ),
	        entry("kravFyrromSVAInnfyrtEffekt50p100", "EI 30 [B 30]"),
	        entry("kravFyrromSVAInnfyrtEffektmin100", "EI 60 A2-s1,d0 [A 60]"));
	}
	@Test
	@Deployment(resources ="./Dmn/16_BrannmotstandDorRomningsvei.dmn")
	public void BrannmotstandDorRomningsvei_DmnTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("BrannmotstandDorRomningsvei",
				Dmn_BrannmotstandDorRomningsvei());
		assertEquals(1, decisionResult.getResultList().size());
	}
	@Test
	@Deployment(resources ="./Dmn/16_BrannmotstandDorRomningsvei.dmn")
	public void BrannmotstandDorRomningsvei_DmnOutputTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("BrannmotstandDorRomningsvei",
				Dmn_BrannmotstandDorRomningsvei());
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		System.out.println(result);
	    assertThat(result)
	      .containsOnly(
	        entry("kravDorBranncelleTrapperomTr1","EI230-CSa[B30S]"),
	        entry("kravDorBoenhetTrapperom","EI230-Sa"),
	        entry("kravDorKorridorTrapperomTr2","EI230-CSa[F30S]"),
	        entry("kravDorRomTrapperomTr3","EI260-CSa[B60S]"),
	        entry("kravDorGarasjeBrannsluse","EI260-CSa[B60S]"),
	        entry("kravDorBranncelleKorridor","EI230-CSa[B30]"),
	        entry("kravDorKorridorFriLuft","EI230-CSa[B30]"));
	}
	@Test
	@Deployment(resources ="./Dmn/18_BrannmotstandVinduMotstParallellYttervegg.dmn")
	public void BrannmotstandVinduMotstParallellYttervegg_DmnTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("BrannmotstandVinduMotstParallellYttervegg",
				Dmn_BrannmotstandVinduMotstParallellYttervegg());
		assertEquals(1, decisionResult.getResultList().size());
	}
	@Test
	@Deployment(resources ="./Dmn/18_BrannmotstandVinduMotstParallellYttervegg.dmn")
	public void BrannmotstandVinduMotstParallellYttervegg_DmnOutputTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("BrannmotstandVinduMotstParallellYttervegg",
				Dmn_BrannmotstandVinduMotstParallellYttervegg());
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		System.out.println(result);
	    assertThat(result)
	      .containsOnly(
	        entry("kravMotstParallellYttervegg1Vindu", "EI 60"),
	        entry("kravMotstParallellYtterveggBegge", "EI 30")
	      );
	}	
	@Test
	@Deployment(resources =models.Dmn_19_BrannmotstandVinduInnvHjørne)
	public void BrannmotstandVinduInnvendigHjorne_DmnTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("BrannmotstandVinduInnvendigHjorne",
				Dmn_BrannmotstandVinduInnvendigHjorne());
		assertEquals(1, decisionResult.getResultList().size());
	}
	@Test
	@Deployment(resources =models.Dmn_19_BrannmotstandVinduInnvHjørne)
	public void BrannmotstandVinduInnvendigHjorne_DmnOutputTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("BrannmotstandVinduInnvendigHjorne",
				Dmn_BrannmotstandVinduInnvendigHjorne());
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		System.out.println(result);
	    assertThat(result)
	      .containsOnly(
	        entry("kravAvstandInnvHjorne1Vindu","E 30 [F 30]" ),
	        entry("kravAvstandInnvHjorneBegge","EI 15"));
	}	
}
