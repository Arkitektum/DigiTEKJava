package ark.bpmn.DigiTEK;

import static ark.bpmn.TestData.BrannmotstandTestData.*;

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

import ark.bpmn.TestData.BrannmotstandTestData.models;

//@Deployment(resources = { "BrannmotstandModelIntModel.bpmn", "./Bpmn/Brannmotstand Model.bpmn",
//		"./Dmn/06_BrannmotstandBaerendeBygningsdeler.dmn", "./Dmn/08a_KlassifiseringTrapperom.dmn",
//		"./Dmn/08b_BrannmotstandTrapperom.dmn",
//		"./Dmn/13_OverflateKledning.dmn",
//		"./Dmn/15_BrannmotstandIsolasjon.dmn" })
@Deployment(resources = {models.Bpmn_BrannmotstandModel,models.BpmnInt_BrannmotstandModelInt,models.Dmn_06_BrannmotstandBaerendeBygningsdeler,models.Dmn_08a_KlassifiseringTrapperom,models.Dmn_08b_BrannmotstandTrapperom
		,models.Dmn_13_OverflateKledning,models.Dmn_15_BrannmotstandIsolasjon})
public class BrannmotstandBpmnTests {
	@ClassRule
	@Rule
	public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

	@Before
	public void setup() {
		init(rule.getProcessEngine());
	}

	@Test
	public void BrannmotstandModel_bpmnOpt01() {

		ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey(ModelKey,
				BrannmotstandOpt01());
		System.out.println(rule.getRuntimeService().getActivityInstance(processInstance.getId()));
		assertThat(processInstance).isStarted();
	}

	@Test
	public void BrannmotstandMode_bpmnOpt02() {

		ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey(ModelKey,
				BrannmotstandOpt02());

		assertThat(processInstance).isStarted().isEnded();
	}

	@Test
	public void BrannmotstandModel_IntegrationTest() {
		ProcessInstance processInstance = processEngine().getRuntimeService()
				.startProcessInstanceByKey(IntegrationModelKey, BrannmotstandOpt01());

		assertThat(processInstance).task(UserTaskId);
		Task task = rule.getTaskService().createTaskQuery().singleResult();
		System.out.println(rule.getRuntimeService().getActivityInstance(processInstance.getId()));
		rule.getTaskService().complete(task.getId());

		// assertThat(processInstance).isStarted().isEnded().hasPassed(IntegrationModelEndTaskId);
		assertThat(processInstance).isStarted().isEnded().hasPassed(EndTaskId);
	}
}
