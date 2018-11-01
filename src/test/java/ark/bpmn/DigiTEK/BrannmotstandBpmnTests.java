package ark.bpmn.DigiTEK;

import static ark.bpmn.TestData.BrannmotstandTestData.*;
import ark.bpmn.TestData.BrannmotstandTestData.models;

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




@Deployment(resources = {models.Bpmn_BrannmotstandModel,models.Dmn_06_BrannmotstandBaerendeBygningsdeler,models.Dmn_08a_KlassifiseringTrapperom,models.Dmn_08b_BrannmotstandTrapperom
		,models.Dmn_13_OverflateKledning})
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
				Brannmotstand_AntallEtasjer_Test());
		System.out.println(rule.getRuntimeService().getActivityInstance(processInstance.getId()));
		assertThat(processInstance).isStarted();
	}

	@Test
	public void BrannmotstandMode_bpmnOpt02() {

		ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey(ModelKey,
				Brannmotstand_IkkeAntallEtasjer_Test());

		assertThat(processInstance).isStarted().isEnded();
	}
	
}
