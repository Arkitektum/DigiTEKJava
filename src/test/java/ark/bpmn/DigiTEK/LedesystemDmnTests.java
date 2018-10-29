package ark.bpmn.DigiTEK;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.init;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.junit.Assert.assertEquals;

import java.util.Map;

import static ark.bpmn.TestData.LedesystemModelTestData.*;

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

import ark.bpmn.TestData.LedesystemModelTestData.models;

public class LedesystemDmnTests {
	@ClassRule
	@Rule
	public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

	@Before
	public void setup() {
		init(rule.getProcessEngine());
	}

	@Test
	@Deployment(resources = models.Dmn_12a_LedesystemEvakuering)
	public void LedesystemEvakuering_DmnTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("LedesystemEvakuering",
				Dmn_LedesystemEvakuering());
		assertEquals(1, decisionResult.getResultList().size());
	}

	@Test
	@Deployment(resources = models.Dmn_12a_LedesystemEvakuering)
	public void LedesystemEvakuering_DmnOutputTest() {

		Map<String, Object> variables = Dmn_LedesystemEvakuering();
		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("LedesystemEvakuering",
				variables);
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		System.out.println("DmnInput: " + variables);
		System.out.println("DmnOutput: " + result);
		assertThat(result).containsOnly(entry("kravLedesystemEvakuering", true));
	}

	@Test
	@Deployment(resources = models.Dmn_12b_LedesystemEvakueringVarighet)
	public void LedesystemEvakueringVarighet_DmnTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("LedesystemEvakueringVarighet",
				Dmn_LedesystemEvakueringVarighet());
		assertEquals(1, decisionResult.getResultList().size());
	}

	@Test
	@Deployment(resources = models.Dmn_12b_LedesystemEvakueringVarighet)
	public void DetektorBrannalarmKategori_DmnOutputTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		Map<String, Object> variables = Dmn_LedesystemEvakueringVarighet();

		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("LedesystemEvakueringVarighet",
				variables);
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		System.out.println("DmnInput: " + variables);
		System.out.println("DmnOutput: " + result);
		assertThat(result)
				.containsOnly(
						entry("kravTrappTekniskRomVarighet", "minst 60 minutter"),
						entry("kravLedesystemVarighet", "minst 60 minutter"));
	}
}
