package ark.bpmn.DigiTEK;

import static ark.bpmn.TestData.KravTilBranntiltaktModelTestData.*;
import ark.bpmn.TestData.KravTilBranntiltaktModelTestData.models;

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




@Deployment(resources = {models.BpmnSub_KravTilBranntiltakt, models.Bpmn_KravTilBranntiltaktModel,models.Dmn_10a_BrannalarmKategori,models.Dmn_10b_DetektorBrannalarmKategori,
		models.Dmn_11_TiltakManuellBrannslokking,models.Dmn_20_BranncelleRomningUtgang,models.Dmn_21_TiltakPavirkeRomningstidSlokkeanlegg,models.Dmn_22_TiltakPavirkeRomningstidAlarmanlegg,models.Dmn_23_TiltakPavirkeRomningstidLedesystem
		,models.Dmn_24_TiltakPavirkeRomningstidEvakueringsplan})
public class KravTilBranntiltakBpmnOutputsTests {
	@ClassRule
	@Rule
	public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

	@Before
	public void setup() {
		init(rule.getProcessEngine());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void KravTilBranntiltaktModel_Ukjent_Test() {
		Map<String, Object> inputsVariables = KravTilBranntiltakt_Ukjent_Test();
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
		// print result from table in console
		System.out.println("modelOutputsvariables result :" + modelOutputsvariables);
		// assert result from table
		assertThat(modelOutputsvariables).containsOnly(entry("Advarsel", "Inputs gir ikke noe svar"));
		// print in console the tree process of the model
		System.out.println(rule.getRuntimeService().getActivityInstance(processInstance.getId()));
		rule.getTaskService().complete(task.getId());
		// assert that the model has end in the right endTask
		assertThat(processInstance).isStarted().isEnded().hasPassed(EndTaskId);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void KravTilBranntiltaktModel_AntallEtasjer_Test() {
		Map<String, Object> inputsVariables = KravTilBranntiltakt_AntallEtasjer_Test();
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
		assertEquals(number.toString(), "8");
		// get one specific table result
		Map<String, Object> detektorBrannalarmKategori = (Map<String, Object>) modelOutputsvariables
				.get("detektorBrannalarmKategori");
		// print result from table in console
		System.out.println("detektorBrannalarmKategori result :" + detektorBrannalarmKategori);
		// assert result from table
		assertThat(detektorBrannalarmKategori).containsOnly(entry("beskrBrannDetektor", "Heldekkende brannalarmanlegg med optiske røykdetektorer i alle områder"));
		// print in console the tree process of the model
		System.out.println("Tree view: "+rule.getRuntimeService().getActivityInstance(processInstance.getId()));
		rule.getTaskService().complete(task.getId());
		// assert that the model has end in the right endTask
		assertThat(processInstance).isStarted().isEnded().hasPassed(EndTaskId);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void KravTilBranntiltaktModel_IkkeAntallEtasjer_Test() {
		Map<String, Object> inputsVariables = KravTilBranntiltakt_IkkeAntallEtasjer_Test();
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
		assertEquals(number.toString(), "6");
		// get one specific table result
		Map<String, Object> tiltakRomningstidLedesystem = (Map<String, Object>) modelOutputsvariables
				.get("tiltakRomningstidLedesystem");
		// print result from table in console
		System.out.println("tiltakRomningstidLedesystem result :" + tiltakRomningstidLedesystem);
		// assert result from table
		assertThat(tiltakRomningstidLedesystem).containsOnly(entry("tiltakPavirkeRomningstid", "Ledesystem"));
		// print in console the tree process of the model
		System.out.println("Tree view: "+rule.getRuntimeService().getActivityInstance(processInstance.getId()));
		rule.getTaskService().complete(task.getId());
		// assert that the model has end in the right endTask
		assertThat(processInstance).isStarted().isEnded().hasPassed(EndTaskId);
	}
}
