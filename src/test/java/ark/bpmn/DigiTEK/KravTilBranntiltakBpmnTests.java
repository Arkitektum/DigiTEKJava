package ark.bpmn.DigiTEK;

import static ark.bpmn.TestData.KravTilBranntiltaktModelTestData.*;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.init;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.processEngine;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.assertThat;

import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import ark.bpmn.TestData.KravTilBranntiltaktModelTestData.models;


@Deployment(resources = {models.Bpmn_KravTilBranntiltaktModel,models.Dmn_10a_KravBrannalarmKategori,models.Dmn_10b_DetektorBrannalarmKategori
		,models.Dmn_11_TiltakManuellBrannslokking,models.Dmn_20_BranncelleRomningUtgang,models.Dmn_21_TiltakPavirkeRomningstidSlokkeanlegg,models.Dmn_22_TiltakPavirkeRomningstidAlarmanlegg,
		models.Dmn_23_TiltakPavirkeRomningstidLedesystem
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
	public void KravTilBranntiltaktModel_AntallEtasjer_Test() {
		ProcessInstance processInstance = processEngine().getRuntimeService()
				.startProcessInstanceByKey(ModelKey, KravTilBranntiltakt_AntallEtasjer_Test());
		assertThat(processInstance).isStarted().isEnded();
	}
	@Test
	public void KravTilBranntiltaktModel_IkkeAntallEtasjer_Test() {
		ProcessInstance processInstance = processEngine().getRuntimeService()
				.startProcessInstanceByKey(ModelKey, KravTilBranntiltakt_IkkeAntallEtasjer_Test());
		assertThat(processInstance).isStarted().isEnded();
	}

}
