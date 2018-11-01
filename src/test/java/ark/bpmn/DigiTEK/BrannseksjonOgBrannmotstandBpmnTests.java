package ark.bpmn.DigiTEK;

import static ark.bpmn.TestData.BrannseksjonOgBrannmotstandTestData.*;

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

import ark.bpmn.TestData.BrannseksjonOgBrannmotstandTestData.models;


@Deployment(resources = {models.Bpmn_BrannseksjonOgBrannmotstand,models.Dmn_03_TiltakStorrelseBrannseksjonBelastning,models.Dmn_04_BrannmotstandSeksjoneringsvegg,
		models.Dmn_07_BrannmotstandSkillendeKonstruksjon,models.Dmn_16_BrannmotstandDorRomningsvei,models.Dmn_17_BrannmotstandDorISeksjvegg,models.Dmn_18_BrannmotstandVinduMotstParallellYttervegg,models.Dmn_19_BrannmotstandVinduInnvHjørne})
public class BrannseksjonOgBrannmotstandBpmnTests {
	@ClassRule
	@Rule
	public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

	@Before
	public void setup() {
		init(rule.getProcessEngine());
	}

	@Test
	public void BrannseksjonOgBrannmotstand_bpmnOpt01() {

		ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey(ModelKey,
				BrannseksjonOgBrannmotstand_test());

		assertThat(processInstance).isStarted().isEnded();
	}
}
