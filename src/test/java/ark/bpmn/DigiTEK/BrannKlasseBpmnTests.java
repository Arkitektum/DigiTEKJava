package ark.bpmn.DigiTEK;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.init;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.processEngine;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.assertThat;
import static ark.bpmn.TestData.BrannKlasseTestData.*;
import ark.bpmn.TestData.BrannKlasseTestData.models;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

@Deployment(resources = {models.Bpmn_BrannKlasseModel, models.Dmn_02_KonsekvensBrannklassifisering
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
	public void testParsingAndDeployment() {
	    // nothing is done here, as we just want to check for exceptions during deployment
	  }
	
	@Test
	public void BrannklasseModel_IkkeKonsekvensAvBrann_Test() {
		ProcessInstance processInstance = processEngine().getRuntimeService()
				.startProcessInstanceByKey(ModelKey, Brannklasse_IkkeKonsekvensAvBrann_BpmnTest());
		assertThat(processInstance).isStarted().isEnded();
	}
	@Test
	public void BrannklasseModel_KonsekvensAvBrann_Test() {
		ProcessInstance processInstance = processEngine().getRuntimeService()
				.startProcessInstanceByKey(ModelKey, Brannklasse_KonsekvensAvBrann_BpmnTest());
		assertThat(processInstance).isStarted().isEnded();
	}


}
