package ark.bpmn.DigiTEK;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.init;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.processEngine;
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

@Deployment(resources = { models.BpmnInt_BrannKlasse,models.Bpmn_BrannKlasseModel, models.Dmn_02_KonsekvensBrannklassifisering
		,models.Dmn_02a_Brannklasse,models.Dmn_02b_BrannklasseKonsekvensBeskrivelse })
public class BrannKlasseBpmnTests {
	@ClassRule
	@Rule
	public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

	@Before
	public void setup() {
		init(rule.getProcessEngine());
	}
	@Test
	public void BrannklasseMode_bpmnOpt01() {

		ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey(ModelKey,
				BrannklasseOpt01());

		assertThat(processInstance).isStarted().isEnded();
	}

	@Test
	public void BrannklasseModel_bpmnOpt02() {

		ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey(ModelKey,
				BrannklasseOpt02());

		assertThat(processInstance).isStarted().isEnded();
	}

	@Test
	public void BrannklasseModel_IntegrationTest() {
		ProcessInstance processInstance = processEngine().getRuntimeService()
				.startProcessInstanceByKey(IntegrationModelKey, BrannklasseOpt02());

		assertThat(processInstance).task(UserTaskId);
		System.out.println(rule.getRuntimeService().getVariables(processInstance.getId()));
		Task task = rule.getTaskService().createTaskQuery().singleResult();
		System.out.println(rule.getRuntimeService().getActivityInstance(processInstance.getId()));
		rule.getTaskService().complete(task.getId());

		assertThat(processInstance).isStarted().isEnded().hasPassed(EndTaskId);
	}


}
