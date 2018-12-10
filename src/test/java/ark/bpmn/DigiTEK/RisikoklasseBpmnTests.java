package ark.bpmn.DigiTEK;

import static ark.bpmn.TestData.RisikoklassenTestData.*;
import ark.bpmn.TestData.RisikoklassenTestData.models;

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

@Deployment(resources = { models.Bpmn_RisikoklasseModel,models.Dmn_01_Risikoklassifisering,
		models.Dmn_01a_RisikoklasseFraTypeVirksomhet, models.Dmn_01b_VedleggTilRisikoklasse,models.Dmn_01c_RisikoklasseForklaring,models.Dmn_01c_Risikoklasse2Forklaring })
public class RisikoklasseBpmnTests {
	@ClassRule
	@Rule
	public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

	@Before
	public void setup() {
		init(rule.getProcessEngine());
	}

	@Test
	public void RisikoklasseModel_TypeVirksomhet_BpmnTest() {

		ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey(ModelKey,
				Risikoklasse_typeVirksomhet_Test());

		assertThat(processInstance).isStarted().isEnded();
	}

	@Test
	public void RisikoklasseModel_IkkeTypeVirksomhet_BpmnTest() {

		ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey(ModelKey,
				Risikoklasse_IkketypeVirksomhet_BpmnTest());

		assertThat(processInstance).isStarted().isEnded();
	}
}
