package ark.bpmn.DigiTEK;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.init;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.junit.Assert.assertEquals;

import static ark.bpmn.TestData.BrannKlasseTestData.*;
import ark.bpmn.TestData.BrannKlasseTestData.models;

import org.camunda.bpm.dmn.engine.DmnDecisionRuleResult;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.engine.DecisionService;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

public class BrannKlasseDmnTests {
	@ClassRule
	@Rule
	public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

	@Before
	public void setup() {
		init(rule.getProcessEngine());
	}

	@Test
	@Deployment(resources = models.Dmn_02_KonsekvensBrannklassifisering)
	public void KonsekvensBrannklassifisering_DmnTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Set input variables
		VariableMap variablesDmn = Variables.createVariables().putValue("konsekvensAvBrann", "Middels konsekvens");

		DmnDecisionTableResult decisionResult = decisionService
				.evaluateDecisionTableByKey("KonsekvensBrannklassifisering", BrannklasseOpt01());

		assertEquals(1, decisionResult.getResultList().size());
	}

	@Test
	@Deployment(resources =  models.Dmn_02_KonsekvensBrannklassifisering)
	public void KonsekvensBrannklassifisering_DmnOutputTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService
				.evaluateDecisionTableByKey("KonsekvensBrannklassifisering", Dmn_KonsekvensBrannklassifisering());
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		System.out.println(result);
		assertThat(result).containsOnly(entry("bkl", "BKL2"));
	}

	@Test
	@Deployment(resources = models.Dmn_02a_Brannklasse)
	public void Brannklasse_DmnTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Set input variables
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("Brannklasse",
				BrannklasseOpt02());
		System.out.println(decisionResult.getResultList());
		assertEquals(1, decisionResult.getResultList().size());
	}

	@Test
	@Deployment(resources = models.Dmn_02a_Brannklasse)
	public void Brannklasse_DmnOutputTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("Brannklasse",
				Dmn_Brannklasse());
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		System.out.println(result);
		assertThat(result).containsOnly(
				entry("bkl", "BKL1"),
				entry("merknadBkl", "Bruttoareal per etasje kan ikke økes ved seksjonering."));
	}

	@Test
	@Deployment(resources = models.Dmn_02b_BrannklasseKonsekvensBeskrivelse)
	public void BrannklasseKonsekvensBeskrivelse_DmnTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Set input variables
		VariableMap variablesDmn = Variables.createVariables().putValue("bkl", "BKL4");

		DmnDecisionTableResult decisionResult = decisionService
				.evaluateDecisionTableByKey("BrannklasseKonsekvensBeskrivelse", variablesDmn);
		System.out.println(decisionResult.getResultList());
		assertEquals(1, decisionResult.getResultList().size());
	}

	@Test
	@Deployment(resources = models.Dmn_02b_BrannklasseKonsekvensBeskrivelse)
	public void BrannklasseKonsekvensBeskrivelse_DmnOutputTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService
				.evaluateDecisionTableByKey("BrannklasseKonsekvensBeskrivelse", Dmn_BrannklasseKonsekvensBeskrivelse());
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		System.out.println(result);
		assertThat(result).containsOnly(
				entry("konsekvensAvBrann", "Middels konsekvens"));
	}

}
