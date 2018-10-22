package ark.bpmn.DigiTEK;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.init;
import static org.junit.Assert.assertEquals;

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
	@Deployment(resources = "./Dmn/02_KonsekvensBrannklassifisering.dmn")
	public void KonsekvensBrannklassifisering() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Set input variables
		VariableMap variablesDmn = Variables.createVariables().putValue("konsekvensAvBrann", "Middels konsekvens");

		DmnDecisionTableResult decisionResult = decisionService
				.evaluateDecisionTableByKey("KonsekvensBrannklassifisering", variablesDmn);

		assertEquals(1, decisionResult.getResultList().size());
	}

	@Test
	@Deployment(resources = "./Dmn/02a_Brannklasse.dmn")
	public void Brannklasse() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Set input variables
		VariableMap variablesDmn = Variables.createVariables()
				.putValue("konsekvensAvBrann", null)
				.putValue("typeVirksomhet", "Bolig")
				.putValue("antallEtasjer", 3)
				.putValue("rkl", "RKL4")
				.putValue("brtArealPrEtasje", 400)
				.putValue("utgangTerrengAlleBoenheter", true);

		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("Brannklasse", variablesDmn);
		System.out.println(decisionResult.getResultList());
//		System.out.println(processEngine.getRuntimeService().getActivityInstance(processInstance.getId()));
		assertEquals(1, decisionResult.getResultList().size());
	}
	@Test
	@Deployment(resources = "./Dmn/02b_BrannklasseKonsekvensBeskrivelse.dmn")
	public void BrannklasseKonsekvensBeskrivelse() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Set input variables
		VariableMap variablesDmn = Variables.createVariables()
				.putValue("bkl", "BKL4");
		
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("BrannklasseKonsekvensBeskrivelse", variablesDmn);
		System.out.println(decisionResult.getResultList());
//		System.out.println(processEngine.getRuntimeService().getActivityInstance(processInstance.getId()));
		assertEquals(1, decisionResult.getResultList().size());
	}

}
