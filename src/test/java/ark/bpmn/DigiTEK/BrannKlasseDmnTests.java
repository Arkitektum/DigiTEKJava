package ark.bpmn.DigiTEK;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.init;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.junit.Assert.assertEquals;

import java.util.Map;

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
		DmnDecisionTableResult decisionResult = decisionService
				.evaluateDecisionTableByKey("KonsekvensBrannklassifisering", Dmn_KonsekvensBrannklassifisering());

		assertEquals(1, decisionResult.getResultList().size());
	}

	@Test
	@Deployment(resources = models.Dmn_02_KonsekvensBrannklassifisering)
	public void KonsekvensBrannklassifisering_DmnOutputTest() {

		Map<String, Object> variables = Dmn_KonsekvensBrannklassifisering();
		DecisionService decisionService = rule.getProcessEngine().getDecisionService();

		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService
				.evaluateDecisionTableByKey("KonsekvensBrannklassifisering", variables);
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		System.out.println("DmnInput: " + variables);
		System.out.println("DmnOutput: " + result);
		assertThat(result).containsOnly(entry("bkl", "BKL2"));
	}

	@Test
	@Deployment(resources = models.Dmn_02a_Brannklasse)
	public void Brannklasse_DmnTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Set input variables
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("Brannklasse",
				Dmn_Brannklasse());
		System.out.println(decisionResult.getResultList());
		assertEquals(1, decisionResult.getResultList().size());
	}

	@Test
	@Deployment(resources = models.Dmn_02a_Brannklasse)
	public void Brannklasse_DmnOutputTest() {

		Map<String, Object> variables = Dmn_Brannklasse();
		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("Brannklasse", variables);
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		// Print results
		System.out.println("DmnInput: " + variables);
		System.out.println("DmnOutput: " + result);
		// test
		assertThat(result).containsOnly(entry("bkl", "BKL1"),
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

		Map<String, Object> variables = Dmn_BrannklasseKonsekvensBeskrivelse();
		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("BrannklasseKonsekvensBeskrivelse", variables);
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		// Print results
		System.out.println("DmnInput: " + variables);
		System.out.println("DmnOutput: " + result);
		// test
		assertThat(result).containsOnly(entry("konsekvensAvBrann", "Middels konsekvens"));
	}

}
