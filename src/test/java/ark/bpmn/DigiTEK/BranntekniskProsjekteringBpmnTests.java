package ark.bpmn.DigiTEK;

import static ark.bpmn.TestData.BranntekniskProsjekteringTestData.*;
import ark.bpmn.TestData.BranntekniskProsjekteringTestData.models;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.init;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.processEngine;
import static org.junit.Assert.assertEquals;

import java.util.Map;

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

@Deployment(resources = { models.BpmnInt_BranntekniskProsjektering, models.Bpmn_BrannseksjonOgBrannmotstand,
		models.Bpmn_KravTilBranntiltaktModel, models.Bpmn_LedesystemModel, models.Bpmn_RisikoklasseModel,
		models.Dmn_05_TiltakBrannveggHoyeBygg
		// bkl
		, models.Bpmn_BrannklasseModel, models.Dmn_02_KonsekvensBrannklassifisering, models.Dmn_02a_Brannklasse,
		models.Dmn_02b_BrannklasseKonsekvensBeskrivelse
		// brannMostand
		, models.Bpmn_BrannmotstandModel, models.Dmn_06_BrannmotstandBaerendeBygningsdeler,
		models.Dmn_08a_KlassifiseringTrapperom, models.Dmn_08b_BrannmotstandTrapperom, models.Dmn_13_OverflateKledning,
		models.Dmn_15_BrannmotstandIsolasjon
		// BrannsekjonOgBrannmostand
		, models.Bpmn_BrannseksjonOgBrannmotstand, models.Dmn_03_TiltakStorrelseBrannseksjonBelastning,
		models.Dmn_04_BrannmotstandSeksjoneringsvegg, models.Dmn_07_BrannmotstandSkillendeKonstruksjon,
		models.Dmn_16_BrannmotstandDorRomningsvei, models.Dmn_17_BrannmotstandDorISeksjvegg,
		models.Dmn_18_BrannmotstandVinduMotstParallellYttervegg, models.Dmn_20_BranncelleRomningUtgang
		// krav brantiltak
		, models.Bpmn_KravTilBranntiltaktModel, models.Dmn_10a_BrannalarmKategori,
		models.Dmn_10b_DetektorBrannalarmKategori, models.Dmn_10b_KravBranndetektorRom,
		models.Dmn_11_TiltakManuellBrannslokking, models.Dmn_19_BrannmotstandVinduInnvHjørne,
		models.Dmn_21_TiltakPavirkeRomningstidSlokkeanlegg, models.Dmn_22_TiltakPavirkeRomningstidAlarmanlegg,
		models.Dmn_23_TiltakPavirkeRomningstidLedesystem, models.Dmn_24_TiltakPavirkeRomningstidEvakueringsplan
		// Ledesystem
		, models.Bpmn_LedesystemModel, models.Dmn_12a_LedesystemEvakuering, models.Dmn_12b_LedesystemEvakueringVarighet
		// Risikoklasse
		, models.Bpmn_RisikoklasseModel, models.Dmn_01_Risikoklassifisering,
		models.Dmn_01a_RisikoklasseFraTypeVirksomhet, models.Dmn_01b_VedleggTilRisikoklasse

})
public class BranntekniskProsjekteringBpmnTests {
	@ClassRule
	@Rule
	public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

	@Before
	public void setup() {
		init(rule.getProcessEngine());
	}


	// @Test
	// public void LedesystemModel_bpmnOpt02() {
	//
	// ProcessInstance processInstance =
	// processEngine().getRuntimeService().startProcessInstanceByKey(ModelKey,
	// BranntekniskProsjekteringModelOpt02());
	//
	// assertThat(processInstance).isStarted().isEnded();
	// }

	@Test
	public void LedesystemModel_IntegrationTest() {
		ProcessInstance processInstance = processEngine().getRuntimeService()
				.startProcessInstanceByKey(IntegrationModelKey, BranntekniskProsjekteringModelOpt01());

		assertThat(processInstance).task(UserTaskId);
		Task task = rule.getTaskService().createTaskQuery().singleResult();
		System.out.println(rule.getRuntimeService().getActivityInstance(processInstance.getId()));
		rule.getTaskService().complete(task.getId());

		// assertThat(processInstance).isStarted().isEnded().hasPassed(IntegrationModelEndTaskId);
		assertThat(processInstance).isStarted().isEnded().hasPassed(EndTaskId);
	}
	
	@Test
	public void LedesystemModel_IntegrationTestOp1() {
		ProcessInstance processInstance = processEngine().getRuntimeService()
				.startProcessInstanceByKey(IntegrationModelKey, BranntekniskProsjekteringModelOpt01());

		assertThat(processInstance).task(UserTaskId);
		Task task = rule.getTaskService().createTaskQuery().singleResult();
		System.out.println(rule.getRuntimeService().getActivityInstance(processInstance.getId()));
		Map<String,Object> modelOutputs =(Map<String, Object>) rule.getRuntimeService().getVariable(processInstance.getId(), "modelOutputs");
		System.out.println(modelOutputs);
		Integer number = modelOutputs.size();
		System.out.println(number);
		assertEquals(number.toString(),"27");
		rule.getTaskService().complete(task.getId());

		// assertThat(processInstance).isStarted().isEnded().hasPassed(IntegrationModelEndTaskId);
		assertThat(processInstance).isStarted().isEnded().hasPassed(EndTaskId);
	}
}
