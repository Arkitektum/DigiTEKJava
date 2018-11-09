package ark.bpmn.DigiTEK;

import static ark.bpmn.TestData.LedesystemModelTestData.*;
import ark.bpmn.TestData.LedesystemModelTestData.models;

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




@Deployment(resources = {models.BpmnSub_LedesystemModel,models.Bpmn_LedesystemModel,models.Dmn_12a_LedesystemEvakuering,models.Dmn_12b_LedesystemEvakueringVarighet})
public class LedesystemBpmnTests {
	@ClassRule
	@Rule
	public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

	@Before
	public void setup() {
		init(rule.getProcessEngine());
	}

	@Test
	public void LedesystemModel_bpmnOpt01() {

		ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey(ModelKey,
				LedesystemModelOpt01());

		assertThat(processInstance).isStarted().isEnded();
	}

	@Test
	public void LedesystemModel_bpmnOpt02() {

		ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey(ModelKey,
				LedesystemModelOpt02());

		assertThat(processInstance).isStarted().isEnded();
	}

	@Test
	public void LedesystemModel_IntegrationTest() {
		ProcessInstance processInstance = processEngine().getRuntimeService()
				.startProcessInstanceByKey(IntegrationModelKey, LedesystemModelOpt02());

		assertThat(processInstance).task(UserTaskId);
		Task task = rule.getTaskService().createTaskQuery().singleResult();
		System.out.println(rule.getRuntimeService().getActivityInstance(processInstance.getId()));
		rule.getTaskService().complete(task.getId());

		// assertThat(processInstance).isStarted().isEnded().hasPassed(IntegrationModelEndTaskId);
		assertThat(processInstance).isStarted().isEnded().hasPassed(EndTaskId);
	}
}
