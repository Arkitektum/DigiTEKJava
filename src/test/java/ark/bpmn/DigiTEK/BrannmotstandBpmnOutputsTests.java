package ark.bpmn.DigiTEK;

import static ark.bpmn.TestData.BrannmotstandTestData.*;
import ark.bpmn.TestData.BrannmotstandTestData.models;

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

@Deployment(resources = { models.BpmnSub_Brannmotstand, models.Bpmn_BrannmotstandModel,
		models.Dmn_06_BrannmotstandBaerendeBygningsdeler, models.Dmn_08a_KlassifiseringTrapperom,
		models.Dmn_08b_BrannmotstandTrapperom, models.Dmn_13_OverflateKledning })
public class BrannmotstandBpmnOutputsTests {
	@ClassRule
	@Rule
	public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

	@Before
	public void setup() {
		init(rule.getProcessEngine());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void BrannmotstandModel_BKL4_Test() {
		Map<String, Object> inputsVariables = Brannmotstand_BKL4_Test();
		ProcessInstance processInstance = processEngine().getRuntimeService()
				.startProcessInstanceByKey(IntegrationModelKey, inputsVariables);

		assertThat(processInstance).task(UserTaskId);
		System.out.println(rule.getRuntimeService().getVariables(processInstance.getId()));
		Task task = rule.getTaskService().createTaskQuery().singleResult();
		System.out.println(rule.getRuntimeService().getActivityInstance(processInstance.getId()));

		Map<String, Object> modelOutputsvariables = (Map<String, Object>) rule.getRuntimeService()
				.getVariable(processInstance.getId(), "modelOutputs");

		Integer number = modelOutputsvariables.size();

		System.out.println("Model inputs :" + inputsVariables);
		System.out.println("number of tables: " + number);
		assertEquals(number.toString(), "1");
		System.out.println("brannklasse result :" + modelOutputsvariables);
		assertThat(modelOutputsvariables).containsOnly(entry("Advarsel", "BKL4 Analyse"));

		rule.getTaskService().complete(task.getId());

		assertThat(processInstance).isStarted().isEnded().hasPassed(EndTaskId);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void BrannmotstandModel_AntallEtasjer_Test() {
		Map<String, Object> inputsVariables = Brannmotstand_AntallEtasjer_Test();
		ProcessInstance processInstance = processEngine().getRuntimeService()
				.startProcessInstanceByKey(IntegrationModelKey, inputsVariables);

		assertThat(processInstance).task(UserTaskId);
		System.out.println(rule.getRuntimeService().getVariables(processInstance.getId()));
		Task task = rule.getTaskService().createTaskQuery().singleResult();
		System.out.println(rule.getRuntimeService().getActivityInstance(processInstance.getId()));

		Map<String, Object> modelOutputsvariables = (Map<String, Object>) rule.getRuntimeService()
				.getVariable(processInstance.getId(), "modelOutputs");

		Integer number = modelOutputsvariables.size();

		System.out.println("Model inputs :" + inputsVariables);
		System.out.println("number of tables: " + number);
		assertEquals(number.toString(), "4");
		Map<String, Object> brannmotstandTrapperom = (Map<String, Object>) modelOutputsvariables
				.get("brannmotstandTrapperom");

		System.out.println("brannmotstandTrapperom result :" + brannmotstandTrapperom);
		assertThat(brannmotstandTrapperom).containsOnly(entry("kravBrannmotstandRundtTrapperom", "EI 30 [B 30]"));

		rule.getTaskService().complete(task.getId());

		assertThat(processInstance).isStarted().isEnded().hasPassed(EndTaskId);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void BrannmotstandModel_IkkeAntallEtasjer_Test() {
		Map<String, Object> inputsVariables = Brannmotstand_IkkeAntallEtasjer_Test();
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
		Map<String, Object> overflateKledning = (Map<String, Object>) modelOutputsvariables
				.get("overflateKledning");
		// print result from table in console
		System.out.println("OverflateKledning result :" + overflateKledning);
		// assert result from table
		assertThat(overflateKledning).containsOnly(
				entry("kravOverflVegHimlingTakIkkeRomningsvei", "D-s2,d0 [In 2]"),
				entry("kravOverflSjakHulIkkeRomningsvei", "B-s1,d0 [In 1]"),
				entry("kravKledningerIkkeRomningsvei", "K210 D-s2,d0 [K2]"),
				entry("kravGolvIkkeRomningsvei", "Ikke krav"),
				entry("kravVegHimlingTakErRomningsvei","B-s1,d0 [In 1]"), 
				entry("kravGolvErRomningsvei", "Dfl-s1 [G]"),
				entry("kravKledningerErRomningsvei", "K210 B-s1,d0 [K1]"),
				entry("kravYtterkledning", "B-s3,d0 [Ut 1]"),
				entry("kravKledningSjaktHulrom","K210 B-s1,d0 [K1]"));
		// print in console the tree process of the model
		System.out.println("Model tree");
		System.out.println(rule.getRuntimeService().getActivityInstance(processInstance.getId()));
		rule.getTaskService().complete(task.getId());
		// assert that the model has end in the right endTask
		assertThat(processInstance).isStarted().isEnded().hasPassed(EndTaskId);
	}
}
