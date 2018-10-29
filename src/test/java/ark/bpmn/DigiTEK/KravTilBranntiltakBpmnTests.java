package ark.bpmn.DigiTEK;

import static ark.bpmn.TestData.KravTilBranntiltaktModelTestData.*;

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

import ark.bpmn.TestData.KravTilBranntiltaktModelTestData.models;


@Deployment(resources = {models.BpmnInt_KravTilBranntiltakt, models.Bpmn_KravTilBranntiltaktModel,models.Dmn_10a_BrannalarmKategori,models.Dmn_10b_DetektorBrannalarmKategori,models.Dmn_10b_KravBranndetektorRom
		,models.Dmn_11_TiltakManuellBrannslokking,models.Dmn_19_BrannmotstandVinduInnvHjørne,models.Dmn_21_TiltakPavirkeRomningstidSlokkeanlegg,models.Dmn_22_TiltakPavirkeRomningstidAlarmanlegg,models.Dmn_23_TiltakPavirkeRomningstidLedesystem
		,models.Dmn_24_TiltakPavirkeRomningstidEvakueringsplan})
public class KravTilBranntiltakBpmnTests {
	@ClassRule
	@Rule
	public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

	@Before
	public void setup() {
		init(rule.getProcessEngine());
	}

	@Test
	public void KravTilBranntiltakt_bpmnOpt01() {

		ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey(ModelKey,
				KravTilBranntiltaktOpt01());

		assertThat(processInstance).isStarted().isEnded();
	}

	@Test
	public void KravTilBranntiltakt_bpmnOpt02() {

		ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey(ModelKey,
				KravTilBranntiltaktOpt02());

		assertThat(processInstance).isStarted().isEnded();
	}

	@Test
	public void KravTilBranntiltakt_IntegrationTest() {
		ProcessInstance processInstance = processEngine().getRuntimeService()
				.startProcessInstanceByKey(IntegrationModelKey, KravTilBranntiltaktOpt01());

		assertThat(processInstance).task(UserTaskId);
		Task task = rule.getTaskService().createTaskQuery().singleResult();
		System.out.println(rule.getRuntimeService().getActivityInstance(processInstance.getId()));
		rule.getTaskService().complete(task.getId());

		// assertThat(processInstance).isStarted().isEnded().hasPassed(IntegrationModelEndTaskId);
		assertThat(processInstance).isStarted().isEnded().hasPassed(EndTaskId);
	}
}
