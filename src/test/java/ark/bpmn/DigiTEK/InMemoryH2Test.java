package ark.bpmn.DigiTEK;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test {

  @ClassRule
  @Rule
  public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

  private static final String PROCESS_DEFINITION_KEY = "DigiTEKNew";

  static {
    LogFactory.useSlf4jLogging(); // MyBatis
  }

  @Before
  public void setup() {
    init(rule.getProcessEngine());
  }

  /**
   * Just tests if the process definition is deployable.
   */
  @Test
  @Deployment(resources = {"process.bpmn","./Bpmn/Brannklasse Model.bpmn", "./Dmn/02_KonsekvensBrannklassifisering.dmn", "./Dmn/02a_Brannklasse.dmn", "./Dmn/02b_BrannklasseKonsekvensBeskrivelse.dmn"})
  public void testParsingAndDeployment() {
    // nothing is done here, as we just want to check for exceptions during deployment
  }

  @Test
  @Deployment(resources = {"process.bpmn","./Bpmn/Brannklasse Model.bpmn", "./Dmn/02_KonsekvensBrannklassifisering.dmn", "./Dmn/02a_Brannklasse.dmn", "./Dmn/02b_BrannklasseKonsekvensBeskrivelse.dmn"})
  public void testHappyPath() {
	  Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("konsekvensAvBrann", null);
		variables.put("typeVirksomhet", "Bolig");
		variables.put("antallEtasjer", 3);
		variables.put("rkl", "RKL4");
		variables.put("brtArealPrEtasje", 400);
		variables.put("utgangTerrengAlleBoenheter", true);
	  ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY, variables);
	  
	  // Now: Drive the process by API and assert correct behavior by camunda-bpm-assert
  }

}
