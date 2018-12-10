package ark.bpmn.DigiTEK;

import static ark.bpmn.TestData.BranntekniskProsjekteringTestData.*;
import ark.bpmn.TestData.BranntekniskProsjekteringTestData.models;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.init;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.processEngine;
import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;

import static org.assertj.core.api.Assertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.assertThat;

import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

@Deployment(resources = { models.BpmnInt_BranntekniskProsjektering, models.Bpmn_BrannseksjonOgBrannmotstand,
		models.Bpmn_KravTilBranntiltaktModel, models.Bpmn_LedesystemModel, models.Bpmn_RisikoklasseModel,
		models.Dmn_05_TiltakBrannveggHoyeBygg
		// bkl
		, models.Bpmn_BrannklasseModel, models.Dmn_02_KonsekvensBrannklassifisering, models.Dmn_02a_Brannklasse,
		models.Dmn_02b_BrannklasseKonsekvensBeskrivelse
		// brannMostand
		, models.Bpmn_BrannmotstandModel, models.Dmn_06_BrannmotstandBaerendeBygningsdeler,
		models.Dmn_08a_KlassifiseringTrapperom, models.Dmn_08b_BrannmotstandTrapperom, models.Dmn_13_OverflateKledning
		// BrannsekjonOgBrannmostand
		, models.Bpmn_BrannseksjonOgBrannmotstand, models.Dmn_03_TiltakStorrelseBrannseksjonBelastning,
		models.Dmn_04_BrannmotstandSeksjoneringsvegg, models.Dmn_07_BrannmotstandSkillendeKonstruksjon,
		models.Dmn_16_BrannmotstandDorRomningsvei, models.Dmn_17_BrannmotstandDorISeksjvegg,
		models.Dmn_18_BrannmotstandVinduMotstParallellYttervegg, models.Dmn_20_BranncelleRomningUtgang
		// krav brantiltak
		, models.Bpmn_KravTilBranntiltaktModel, models.Dmn_10a_KravBrannalarmKategori,models.Dmn_10b_DetektorBrannalarmKategori,
		models.Dmn_11_TiltakManuellBrannslokking, models.Dmn_19_BrannmotstandVinduInnvHjørne,
		models.Dmn_21_TiltakPavirkeRomningstidSlokkeanlegg, models.Dmn_22_TiltakPavirkeRomningstidAlarmanlegg,
		models.Dmn_23_TiltakPavirkeRomningstidLedesystem, models.Dmn_24_TiltakPavirkeRomningstidEvakueringsplan
		// Ledesystem
		, models.Bpmn_LedesystemModel, models.Dmn_12a_LedesystemEvakuering, models.Dmn_12b_LedesystemEvakueringVarighet
		// Risikoklasse
		, models.Bpmn_RisikoklasseModel, models.Dmn_01_Risikoklassifisering,
		models.Dmn_01a_RisikoklasseFraTypeVirksomhet, models.Dmn_01b_VedleggTilRisikoklasse, models.Dmn_01c_RisikoklasseForklaring, models.Dmn_01c_Risikoklasse2Forklaring

})
public class BranntekniskProsjekteringBpmnOutputsTests {
	@ClassRule
	@Rule
	public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

	@Before
	public void setup() {
		init(rule.getProcessEngine());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void BranntekniskProsjektering_IntegrationTestOp1() {
		Map<String, Object> inputsVariables = BranntekniskProsjekteringModelOpt01();
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
		assertEquals(number.toString(), "25");
//		Map<String, Object> tiltakStorrelseBrannseksjonBelastning = (Map<String, Object>) modelOutputsvariables
//				.get("tiltakStorrelseBrannseksjonBelastning");
//
//		System.out.println("tiltakStorrelseBrannseksjonBelastning result :" + tiltakStorrelseBrannseksjonBelastning);
//		assertThat(tiltakStorrelseBrannseksjonBelastning).containsOnly(entry("brannTiltakStrSeksjonBelastning", "Minst to brannseksjoner"));

		rule.getTaskService().complete(task.getId());

		assertThat(processInstance).isStarted().isEnded().hasPassed(EndTaskId);
	}
}
