package ark.bpmn.DigiTEK;

import static ark.bpmn.TestData.RisikoklassenTestData.*;
import ark.bpmn.TestData.RisikoklassenTestData.models;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.init;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.processEngine;
import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.assertThat;

import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

@Deployment(resources = { models.BpmnSub_Risikoklasse, models.Bpmn_RisikoklasseModel,
		models.Dmn_01_Risikoklassifisering, models.Dmn_01a_RisikoklasseFraTypeVirksomhet,
		models.Dmn_01b_VedleggTilRisikoklasse,models.Dmn_01c__RKLForklaring, models.Dmn_01c_RKL2Forklaring })
public class RisikoklasseBpmnOutputTests {
	@ClassRule
	@Rule
	public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

	@Before
	public void setup() {
		init(rule.getProcessEngine());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void RisikoklasseModel_typeVirksomhet_Test() {
		Map<String, Object> inputsVariables = Risikoklasse_typeVirksomhet_Test();
		ProcessInstance processInstance = processEngine().getRuntimeService()
				.startProcessInstanceByKey(IntegrationModelKey, inputsVariables);
		// assert that model is in waiting for the user to get the result
		assertThat(processInstance).task(UserTaskId);
		Task task = rule.getTaskService().createTaskQuery().singleResult();
		// Get the result of the model
		Map<String, Object> modelOutputsvariables = (Map<String, Object>) rule.getRuntimeService()
				.getVariable(processInstance.getId(), "modelOutputs");
		Integer number = modelOutputsvariables.size();
		// print result in console
		System.out.println("Model inputs :" + inputsVariables);
		System.out.println("number of tables: " + number);
		// Assert number of run tables
		assertEquals(number.toString(), "2");
		// get one specific table result
		Map<String, Object> vedleggTilRisikoklasse = (Map<String, Object>) modelOutputsvariables
				.get("vedleggTilRisikoklasse");
		// print result from table in console
		System.out.println("vedleggTilRisikoklasse result :" + vedleggTilRisikoklasse);
		// assert result from table
		assertThat(vedleggTilRisikoklasse).containsOnly(
				entry("bareSporadiskPersonopphold", "Nei"),
				entry("alleKjennerRomningsVeiene", "Nei"),
				entry("beregnetForOvernatting", "Ja"),
				entry("liteBrannfarligAktivitet", "Ja"));
		// print in console the tree process of the model
		System.out.println("Tree view: " + rule.getRuntimeService().getActivityInstance(processInstance.getId()));
		rule.getTaskService().complete(task.getId());
		// assert that the model has end in the right endTask
		assertThat(processInstance).isStarted().isEnded().hasPassed(EndTaskId);
	}
	@SuppressWarnings("unchecked")
	@Test
	public void RisikoklasseModel_typeVirksomhetRKL2_Test() {
		Map<String, Object> inputsVariables = Risikoklasse_typeVirksomhetRKL2_BpmnTest();
		ProcessInstance processInstance = processEngine().getRuntimeService()
				.startProcessInstanceByKey(IntegrationModelKey, inputsVariables);
		// assert that model is in waiting for the user to get the result
		assertThat(processInstance).task(UserTaskId);
		Task task = rule.getTaskService().createTaskQuery().singleResult();
		// Get the result of the model
		Map<String, Object> modelOutputsvariables = (Map<String, Object>) rule.getRuntimeService()
				.getVariable(processInstance.getId(), "modelOutputs");
		Integer number = modelOutputsvariables.size();
		// print result in console
		System.out.println("Model inputs :" + inputsVariables);
		System.out.println("number of tables: " + number);
		System.out.println("Model Outputs :" + modelOutputsvariables);
		// Assert number of run tables
		assertEquals(number.toString(), "3");
		// get one specific table result
		Map<String, Object> vedleggTilRisikoklasse = (Map<String, Object>) modelOutputsvariables
				.get("vedleggTilRisikoklasse");
		// print result from table in console
		System.out.println("vedleggTilRisikoklasse result :" + vedleggTilRisikoklasse);
		// assert result from table
		assertThat(vedleggTilRisikoklasse).containsOnly(
				entry("bareSporadiskPersonopphold", "Nei"),
				entry("alleKjennerRomningsVeiene", "Nei"),
				entry("beregnetForOvernatting", "Ja"),
				entry("liteBrannfarligAktivitet", "Ja"));
		// print in console the tree process of the model
		System.out.println("Tree view: " + rule.getRuntimeService().getActivityInstance(processInstance.getId()));
		rule.getTaskService().complete(task.getId());
		// assert that the model has end in the right endTask
		assertThat(processInstance).isStarted().isEnded().hasPassed(EndTaskId);
	}


	@SuppressWarnings("unchecked")
	@Test
	public void RisikoklasseModel_IkketypeVirksomhet_Test() {
		Map<String, Object> inputsVariables = Risikoklasse_IkketypeVirksomhet_Test();
		ProcessInstance processInstance = processEngine().getRuntimeService()
				.startProcessInstanceByKey(IntegrationModelKey, inputsVariables);
		// assert that model is in waiting for the user to get the result
		assertThat(processInstance).task(UserTaskId);
		Task task = rule.getTaskService().createTaskQuery().singleResult();
		// Get the result of the model
		Map<String, Object> modelOutputsvariables = (Map<String, Object>) rule.getRuntimeService()
				.getVariable(processInstance.getId(), "modelOutputs");
		Integer number = modelOutputsvariables.size();
		// print result in console
		System.out.println("Model inputs :" + inputsVariables);
		System.out.println("number of tables: " + number);
		// Assert number of run tables
		assertEquals(number.toString(), "1");
		// get one specific table result
		Map<String, Object> risikoklassifisering = (Map<String, Object>) modelOutputsvariables
				.get("risikoklassifisering");
		// print result from table in console
		System.out.println("risikoklassifisering result :" + risikoklassifisering);
		// assert result from table
		assertThat(risikoklassifisering).containsOnly(entry("rkl", "RKL4"));
		// print in console the tree process of the model
		System.out.println("Tree view: " + rule.getRuntimeService().getActivityInstance(processInstance.getId()));
		rule.getTaskService().complete(task.getId());
		// assert that the model has end in the right endTask
		assertThat(processInstance).isStarted().isEnded().hasPassed(EndTaskId);
	}

}
