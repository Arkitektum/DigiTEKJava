package ark.bpmn.DigiTEK;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.init;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.processEngine;
import static org.junit.Assert.assertEquals;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.assertThat;
import static ark.bpmn.TestData.BrannKlasseTestData.*;
import ark.bpmn.TestData.BrannKlasseTestData.models;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

@Deployment(resources = { models.BpmnSub_BrannKlasse, models.Bpmn_BrannKlasseModel,
		models.Dmn_02_KonsekvensBrannklassifisering, models.Dmn_02a_Brannklasse,
		models.Dmn_02b_BrannklasseKonsekvensBeskrivelse })
public class BrannKlasseBpmnOutputsTests {
	@ClassRule
	@Rule
	public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

	@Before
	public void setup() {
		init(rule.getProcessEngine());
	}

	@Test
	@SuppressWarnings("unchecked")
	public void BrannklasseModel_BKL4_Test() {

		Map<String, Object> variables = Brannklasse_BKL4_Test();
		ProcessInstance processInstance = processEngine().getRuntimeService()
				.startProcessInstanceByKey(IntegrationModelKey, variables);

		assertThat(processInstance).task(UserTaskId);
		System.out.println(rule.getRuntimeService().getVariables(processInstance.getId()));
		Task task = rule.getTaskService().createTaskQuery().singleResult();
		System.out.println(rule.getRuntimeService().getActivityInstance(processInstance.getId()));

		Map<String, Object> variable = (Map<String, Object>) rule.getRuntimeService()
				.getVariable(processInstance.getId(), "modelOutputs");
		Map<String, Object> modelOutputs = variable;
		Integer number = modelOutputs.size();

		System.out.println("Model inputs :" + variables);
		System.out.println("number of tables: " + number);
		assertEquals(number.toString(), "1");

		System.out.println("modelOutputs result :" + modelOutputs);
		assertThat(modelOutputs).containsOnly(entry("Advarsel", "BKL4 Analyse"));

		rule.getTaskService().complete(task.getId());

		assertThat(processInstance).isStarted().isEnded().hasPassed(EndTaskId);
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void BrannklasseModel_BKL2_Test() {

		Map<String, Object> variables = Brannklasse_BKL2_Test();
		ProcessInstance processInstance = processEngine().getRuntimeService()
				.startProcessInstanceByKey(IntegrationModelKey, variables);

		assertThat(processInstance).task(UserTaskId);
		System.out.println(rule.getRuntimeService().getVariables(processInstance.getId()));
		Task task = rule.getTaskService().createTaskQuery().singleResult();
		System.out.println(rule.getRuntimeService().getActivityInstance(processInstance.getId()));

		Map<String, Object> variable = (Map<String, Object>) rule.getRuntimeService()
				.getVariable(processInstance.getId(), "modelOutputs");
		Map<String, Object> modelOutputs = variable;
		Integer number = modelOutputs.size();

		System.out.println("Model inputs :" + variables);
		System.out.println("number of tables: " + number);
		assertEquals(number.toString(), "2");
		Map<String, Object> brannklasse = (Map<String, Object>) modelOutputs.get("brannklasse");

		System.out.println("brannklasse result :" + brannklasse);
		assertThat(brannklasse).containsOnly(entry("bkl", "BKL2"));

		rule.getTaskService().complete(task.getId());

		assertThat(processInstance).isStarted().isEnded().hasPassed(EndTaskId);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void BrannklasseModel_Bolig_Test() {

		Map<String, Object> variables = Brannklasse_Bolig_Test();
		ProcessInstance processInstance = processEngine().getRuntimeService()
				.startProcessInstanceByKey(IntegrationModelKey, variables);

		assertThat(processInstance).task(UserTaskId);
		System.out.println(rule.getRuntimeService().getVariables(processInstance.getId()));
		Task task = rule.getTaskService().createTaskQuery().singleResult();
		System.out.println(rule.getRuntimeService().getActivityInstance(processInstance.getId()));

		Map<String, Object> variable = (Map<String, Object>) rule.getRuntimeService()
				.getVariable(processInstance.getId(), "modelOutputs");
		Map<String, Object> modelOutputs = variable;
		Integer number = modelOutputs.size();

		System.out.println("Model inputs :" + variables);
		System.out.println("number of tables: " + number);
		assertEquals(number.toString(), "2");
		Map<String, Object> brannklasse = (Map<String, Object>) modelOutputs.get("brannklasse");

		System.out.println("brannklasse result :" + brannklasse);
		assertThat(brannklasse).containsOnly(entry("bkl", "BKL1"));

		rule.getTaskService().complete(task.getId());

		assertThat(processInstance).isStarted().isEnded().hasPassed(EndTaskId);
	}

}
