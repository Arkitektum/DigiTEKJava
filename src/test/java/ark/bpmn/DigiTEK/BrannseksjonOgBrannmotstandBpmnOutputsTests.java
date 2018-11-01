package ark.bpmn.DigiTEK;

import static ark.bpmn.TestData.BrannseksjonOgBrannmotstandTestData.*;
import ark.bpmn.TestData.BrannseksjonOgBrannmotstandTestData.models;

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

@Deployment(resources = { models.BpmnSub_BrannseksjonOgBrannmotstand, models.Bpmn_BrannseksjonOgBrannmotstand,
		models.Dmn_03_TiltakStorrelseBrannseksjonBelastning, models.Dmn_04_BrannmotstandSeksjoneringsvegg,
		models.Dmn_07_BrannmotstandSkillendeKonstruksjon, models.Dmn_16_BrannmotstandDorRomningsvei,
		models.Dmn_17_BrannmotstandDorISeksjvegg, models.Dmn_18_BrannmotstandVinduMotstParallellYttervegg,
		models.Dmn_19_BrannmotstandVinduInnvHjørne })
public class BrannseksjonOgBrannmotstandBpmnOutputsTests {
	@ClassRule
	@Rule
	public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

	@Before
	public void setup() {
		init(rule.getProcessEngine());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void BrannseksjonOgBrannmotstandModel_Seksjonering_Test() {
		Map<String, Object> inputsVariables = BrannseksjonOgBrannmotstand_Seksjonering_Test();
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
		assertEquals(number.toString(), "7");
		// get one specific table result
		Map<String, Object> brannmotstandDorISeksjoneringsvegg = (Map<String, Object>) modelOutputsvariables
				.get("brannmotstandDorISeksjoneringsvegg");
		// print result from table in console
		System.out.println("brannmotstandTrapperom result :" + brannmotstandDorISeksjoneringsvegg);
		// assert result from table
		assertThat(brannmotstandDorISeksjoneringsvegg).containsOnly(entry("kravBrannmotstandDorISeksjvegg", "Analyse"));
		// print in console the tree process of the model
		System.out.println(rule.getRuntimeService().getActivityInstance(processInstance.getId()));
		rule.getTaskService().complete(task.getId());
		// assert that the model has end in the right endTask
		assertThat(processInstance).isStarted().isEnded().hasPassed(EndTaskId);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void BrannseksjonOgBrannmotstandModel_Test() {
		Map<String, Object> inputsVariables = BrannseksjonOgBrannmotstand_test();
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
		assertEquals(number.toString(), "7");
		// get one specific table result
		Map<String, Object> tiltakStorrelseBrannseksjonBelastning = (Map<String, Object>) modelOutputsvariables
				.get("tiltakStorrelseBrannseksjonBelastning");
		// print result from table in console
		System.out.println("tiltakStorrelseBrannseksjonBelastning result :" + tiltakStorrelseBrannseksjonBelastning);
		// assert result from table
		assertThat(tiltakStorrelseBrannseksjonBelastning)
				.containsOnly(entry("brannTiltakStrSeksjonBelastning", "Røykventilasjon"));
		// print in console the tree process of the model
		System.out.println("Tree view: "+rule.getRuntimeService().getActivityInstance(processInstance.getId()));
		rule.getTaskService().complete(task.getId());

		assertThat(processInstance).isStarted().isEnded().hasPassed(EndTaskId);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void BrannseksjonOgBrannmotstandModel_Sykehjem_Test() {
		
		Map<String, Object> inputsVariables = BrannseksjonOgBrannmotstand_Sykehjem_test();
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
		assertEquals(number.toString(), "7");
		// get one specific table result
		Map<String, Object> tiltakStorrelseBrannseksjonBelastning = (Map<String, Object>) modelOutputsvariables
				.get("tiltakStorrelseBrannseksjonBelastning");
		// print result from table in console
		System.out.println("tiltakStorrelseBrannseksjonBelastning result :" + tiltakStorrelseBrannseksjonBelastning);
		// assert result from table
		assertThat(tiltakStorrelseBrannseksjonBelastning)
		.containsOnly(entry("brannTiltakStrSeksjonBelastning", "Minst to brannseksjoner"));
		// print in console the tree process of the model
		System.out.println(rule.getRuntimeService().getActivityInstance(processInstance.getId()));
		rule.getTaskService().complete(task.getId());

		assertThat(processInstance).isStarted().isEnded().hasPassed(EndTaskId);
	}
}
