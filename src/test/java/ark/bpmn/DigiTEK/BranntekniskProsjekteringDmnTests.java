package ark.bpmn.DigiTEK;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.init;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.junit.Assert.assertEquals;

import java.util.Map;

import static ark.bpmn.TestData.BranntekniskProsjekteringTestData.*;

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

import ark.bpmn.TestData.BranntekniskProsjekteringTestData.models;

public class BranntekniskProsjekteringDmnTests {
	@ClassRule
	@Rule
	public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

	@Before
	public void setup() {
		init(rule.getProcessEngine());
	}

	@Test
	@Deployment(resources = models.Dmn_05_TiltakBrannveggHoyeBygg)
	public void TiltakBrannveggHoyeBygg_DmnTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("TiltakBrannveggHoyeBygg",
				Dmn_TiltakBrannveggHoyeBygg());
		assertEquals(1, decisionResult.getResultList().size());
	}

	@Test
	@Deployment(resources = models.Dmn_05_TiltakBrannveggHoyeBygg)
	public void LedesystemEvakuering_DmnOutputTest() {

		Map<String, Object> variables = Dmn_TiltakBrannveggHoyeBygg();
		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("TiltakBrannveggHoyeBygg",
				variables);
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		System.out.println("DmnInput: " + variables);
		System.out.println("DmnOutput: " + result);
		assertThat(result).containsOnly(entry("kravBrannveggKvalitet", "REI 180-M A2-s1, d0 [A 180]"));
	}
}
