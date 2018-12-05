package ark.bpmn.DigiTEK;

import static ark.bpmn.TestData.RisikoklassenTestData.*;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.init;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.junit.Assert.assertEquals;

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

//@Deployment(resources = {"./Dmn/01_Risikoklassifisering.dmn",
//		"./Dmn/01a_RisikoklasseFraTypeVirksomhet.dmn", "./Dmn/01b_VedleggTilRisikoklasse.dmn" })
public class RisikoklasseDmnTests {
	@ClassRule
	@Rule
	public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

	@Before
	public void setup() {
		init(rule.getProcessEngine());
	}

	@Test
	@Deployment(resources ="./Dmn/01_Risikoklassifisering.dmn")
	// 01_Risikoklassifisering.dmn
	public void Risikoklassifisering_DmnTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("Risikoklassifisering",
				Dmn_Risikoklassifisering());
		assertEquals(1, decisionResult.getResultList().size());
	}
	@Test
	@Deployment(resources ="./Dmn/01_Risikoklassifisering.dmn")
	// 01_Risikoklassifisering.dmn
	public void Risikoklassifisering_DmnOutputTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("Risikoklassifisering",
				Dmn_Risikoklassifisering());
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
	    assertThat(result)
	      .containsOnly(
	        entry("rkl", "RKL5")
	      );
	}

	@Test
	@Deployment(resources ="./Dmn/01a_RisikoklasseFraTypeVirksomhet.dmn")
	// 01a_RisikoklasseFraTypeVirksomhet.dmn
	public void RisikoklasseFraTypeVirksomhet_DmnTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService
				.evaluateDecisionTableByKey("RisikoklasseFraTypeVirksomhet", Dmn_RisikoklasseFraTypeVirksomhet());
		assertEquals(1, decisionResult.getResultList().size());
	}

	@Test
	@Deployment(resources ="./Dmn/01a_RisikoklasseFraTypeVirksomhet.dmn")
	// 01a_RisikoklasseFraTypeVirksomhet.dmn
	public void RisikoklasseFraTypeVirksomhet_DmnOutputTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService
				.evaluateDecisionTableByKey("RisikoklasseFraTypeVirksomhet", Dmn_RisikoklasseFraTypeVirksomhet());
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
	    assertThat(result)
	      .containsOnly(
	        entry("rkl", "RKL6")
	      );
	}

	@Test
	@Deployment(resources ="./Dmn/01b_VedleggTilRisikoklasse.dmn")
	// 01b_VedleggTilRisikoklasse.dmn
	public void VedleggTilRisikoklasse_DmnTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("VedleggTilRisikoklasse",
				Dmn_VedleggTilRisikoklasse());
		assertEquals(1, decisionResult.getResultList().size());
	}
	@Test
	@Deployment(resources ="./Dmn/01b_VedleggTilRisikoklasse.dmn")
	// 01b_VedleggTilRisikoklasse.dmn
	public void VedleggTilRisikoklasse_DmnOutputTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("VedleggTilRisikoklasse",
				Dmn_VedleggTilRisikoklasse());
		 
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
	    assertThat(result)
	      .containsOnly(
	        entry("bareSporadiskPersonopphold", false),
	        entry("alleKjennerRomningsVeiene", true),
	        entry("beregnetForOvernatting", false),
	        entry("liteBrannfarligAktivitet", true)
	      );
	}
}

