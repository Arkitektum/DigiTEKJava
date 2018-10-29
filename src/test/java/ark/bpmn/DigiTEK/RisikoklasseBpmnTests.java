package ark.bpmn.DigiTEK;

import static ark.bpmn.TestData.RisikoklassenTestData.*;
import ark.bpmn.TestData.RisikoklassenTestData.models;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.init;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.processEngine;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.assertThat;

import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

@Deployment(resources = { models.BpmnInt_Risikoklasse,models.Bpmn_RisikoklasseModel,models.Dmn_01_Risikoklassifisering,
		models.Dmn_01a_RisikoklasseFraTypeVirksomhet, models.Dmn_01b_VedleggTilRisikoklasse })
public class RisikoklasseBpmnTests {
	@ClassRule
	@Rule
	public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

	@Before
	public void setup() {
		init(rule.getProcessEngine());
	}

	@Test
	public void RisikoklassenMode_bpmnOpt01() {

		ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey(ModelKey,
				RisikoklasseOpt01());

		assertThat(processInstance).isStarted().isEnded();
	}

	@Test
	public void RisikoklassenMode_bpmnOpt02() {

		ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey(ModelKey,
				RisikoklasseOpt02());

		assertThat(processInstance).isStarted().isEnded();
	}

	@Test
	public void RisikoklasseModel_IntegrationTest() {
		ProcessInstance processInstance = processEngine().getRuntimeService()
				.startProcessInstanceByKey(IntegrationModelKey, RisikoklasseOpt02());

		assertThat(processInstance).task(UserTaskId);
		Task task = rule.getTaskService().createTaskQuery().singleResult();
		System.out.println(rule.getRuntimeService().getActivityInstance(processInstance.getId()));
		rule.getTaskService().complete(task.getId());

		// assertThat(processInstance).isStarted().isEnded().hasPassed(IntegrationModelEndTaskId);
		assertThat(processInstance).isStarted().isEnded().hasPassed(EndTaskId);
	}
}
