package ark.bpmn.DigiTEK;

import static ark.bpmn.TestData.BrannmotstandTestData.*;
import static ark.bpmn.TestData.KravTilBranntiltaktModelTestData.Dmn_TiltakRomningstidEvakueringsplan;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.init;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.camunda.bpm.dmn.engine.DmnDecisionRuleResult;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.engine.DecisionService;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;


public class BrannmotstandDmnTests {
	@ClassRule
	@Rule
	public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

	@Before
	public void setup() {
		init(rule.getProcessEngine());
	}

	@Test
	@Deployment(resources = "./Dmn/06_BrannmotstandBaerendeBygningsdeler.dmn")
 
	public void BrannmotstandBaerendeBygningsdeler_DmnTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey(
				"BrannmotstandBaerendeBygningsdeler", Dmn_BrannmotstandBaerendeBygningsdeler());
		assertEquals(1, decisionResult.getResultList().size());
	}

	@Test
	@Deployment(resources = "./Dmn/06_BrannmotstandBaerendeBygningsdeler.dmn")
	public void BrannmotstandBaerendeBygningsdeler_DmnOutputTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey(
				"BrannmotstandBaerendeBygningsdeler", Dmn_BrannmotstandBaerendeBygningsdeler());
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		System.out.println(result);
		assertThat(result).containsOnly(
				entry("baerendeHovedSystem", "R 15 [B 15]"),
				entry("sekundareBaerendeDeler", "R 15 [B 15]"), 
				entry("trappeLop", "Ikke angitt"),
				entry("baerendeDelerUnderOversteKjeller", "R 60 A2-s1,d0 [A 60]"),
				entry("beskytttetUtvTrappeLop", "Ikke angitt"));
	}

	@Test
	@Deployment(resources = "./Dmn/08a_KlassifiseringTrapperom.dmn")
	public void KlassifiseringTrapperom_DmnTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("KlassifiseringTrapperom",
				Dmn_KlassifiseringTrapperom());
		assertEquals(1, decisionResult.getResultList().size());
	}

	@Test
	@Deployment(resources = "./Dmn/08a_KlassifiseringTrapperom.dmn")
	public void KlassifiseringTrapperom_DmnOutputTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		Map<String, Object> variables = Dmn_KlassifiseringTrapperom();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("KlassifiseringTrapperom",
				variables);
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		System.out.println("DmnInput: " + variables);
		System.out.println("DmnOutput: " + result);
		assertThat(result).containsOnly(entry("trappeRomKlasse", "TR2"));
	}

	@Test
	@Deployment(resources = "./Dmn/08b_BrannmotstandTrapperom.dmn")
	public void VedleggTilRisikoklasse_DmnTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("BrannmotstandTrapperom",
				Dmn_BrannmotstandTrapperom());
		assertEquals(1, decisionResult.getResultList().size());
	}

	@Test
	@Deployment(resources = "./Dmn/08b_BrannmotstandTrapperom.dmn")
	public void BrannmotstandTrapperom_DmnOutputTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("BrannmotstandTrapperom",
				Dmn_BrannmotstandTrapperom());

		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		assertThat(result).containsOnly(entry("kravBrannmotstandRundtTrapperom", "EI 60 [B 60]"));
	}

	@Test
	@Deployment(resources = "./Dmn/13_OverflateKledning.dmn")
	public void OverflateKledning_DmnTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("OverflateKledning",
				Dmn_OverflateKledning());
		assertEquals(1, decisionResult.getResultList().size());
	}

	@Test
	@Deployment(resources = "./Dmn/13_OverflateKledning.dmn")
	public void OverflateKledning_DmnOutputTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("OverflateKledning",
				Dmn_OverflateKledning());

		DmnDecisionRuleResult result = decisionResult.getSingleResult();
		assertThat(result).containsOnly(entry("kravOverflVegHimlingTakIkkeRomningsvei", "B-s1,d0 [In 1]"),
				entry("kravOverflSjakHulIkkeRomningsvei", "B-s1,d0 [In 1]"),
				entry("kravKledningerIkkeRomningsvei", "K210 B-s1,d0 [K1]"),
				entry("kravGolvIkkeRomningsvei", "Ikke krav"),
				entry("kravVegHimlingTakErRomningsvei", "B-s1,d0 [In 1]"), entry("kravGolvErRomningsvei", "Dfl-s1 [G]"),
				entry("kravKledningerErRomningsvei", "K210 A2-s1,d0 [K1-A]"),
				entry("kravYtterkledning", "B-s3,d0 [Ut 1]"), entry("kravKledningSjaktHulrom", "K210 A2-s1,d0 [K1-A]"));
	}

	@Test
	@Deployment(resources = "./Dmn/15_BrannmotstandIsolasjon.dmn")
	public void BrannmotstandIsolasjon_DmnTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("BrannmotstandIsolasjon",
				Dmn_BrannmotstandIsolasjon());
		assertEquals(1, decisionResult.getResultList().size());
	}

	@Test
	@Deployment(resources ="./Dmn/15_BrannmotstandIsolasjon.dmn")
	public void BrannmotstandIsolasjon_DmnOutputTest() {

		DecisionService decisionService = rule.getProcessEngine().getDecisionService();
		// Evaluate DMN
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("BrannmotstandIsolasjon",
				Dmn_BrannmotstandIsolasjon());
		 
		DmnDecisionRuleResult result = decisionResult.getSingleResult();
	    assertThat(result)
	      .containsOnly(
	        entry("kravUtvendigOverflate", "Ut1"),
	        entry("kravIsolasjonEureficKlasse", "plast ikke tillatt")
	    		  );
	}
}
