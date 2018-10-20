package ark.bpmn.DigiTEK;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.init;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.processEngine;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

public class BrannklasseModelTest {
	@ClassRule
	@Rule
	public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

	@Before
	public void setup() {
		init(rule.getProcessEngine());
	}

	@Test
	@Deployment(resources = { "./Bpmn/Brannklasse Model.bpmn", "./Dmn/02_KonsekvensBrannklassifisering.dmn", "./Dmn/02a_Brannklasse.dmn", "./Dmn/02b_BrannklasseKonsekvensBeskrivelse.dmn" })
	public void BrannklasseModel01() {

		ProcessEngine processEngine = rule.getProcessEngine();
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("konsekvensAvBrann", "Middels konsekvens");

		ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey("BrannklasseModel",
				variables);

//		assertThat(processInstance).task("userTask");
//		System.out.println(processEngine.getRuntimeService().getVariables(processInstance.getId()));
//		List<Task> task = processEngine.getTaskService().createTaskQuery().taskDefinitionKey("userTask").list();
//		Task task1 = processEngine.getTaskService().createTaskQuery().singleResult();
//		System.out.println(processEngine.getRuntimeService().getActivityInstance(processInstance.getId()));
//		processEngine.getTaskService().complete(task1.getId());
		
		
		assertThat(processInstance).isStarted().isEnded();

	}
	@Test
	@Deployment(resources = { "./Bpmn/Brannklasse Model.bpmn", "./Dmn/02_KonsekvensBrannklassifisering.dmn", "./Dmn/02a_Brannklasse.dmn", "./Dmn/02b_BrannklasseKonsekvensBeskrivelse.dmn" })
	public void BrannklasseModel02() {

		ProcessEngine processEngine = rule.getProcessEngine();
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("konsekvensAvBrann", null);
		variables.put("typeVirksomhet", "Bolig");
		variables.put("antallEtasjer", 3);
		variables.put("rkl", "RKL4");
		variables.put("brtArealPrEtasje", 400);
		variables.put("utgangTerrengAlleBoenheter", true);

		ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey("BrannklasseModel",
				variables);

		assertThat(processInstance).isStarted().isEnded();

	}
}
