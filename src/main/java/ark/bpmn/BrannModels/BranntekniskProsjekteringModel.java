package ark.bpmn.BrannModels;

import java.util.HashMap;
import java.util.Map;

public class BranntekniskProsjekteringModel {
	public String typeVirksomhet;
	public Long antallEtasjer;
	public Long brtArealPrEtasje;
	public Object utgangTerrengAlleBoenheter;
	public String bareSporadiskPersonopphold;
	public Object alleKjennerRomningsVeiene;
	public Object beregnetForOvernatting;
	public Object liteBrannfarligAktivitet;
	public String konsekvensAvBrann;
	public Long arealBrannseksjonPrEtasje;
	public Long brannenergi;
	public Object bygningOffentligUnderTerreng;

	public Long avstandMellomMotstVinduerIMeter;
	public String rkl;
	public String bkl;
	public Long brannalarmKategori;
	public String brannTiltakStrSeksjonBelastning;
	public String kravBrannmotstSeksjVegg;
	public Object kravLedesystemEvakuering;
	public String trappeRomKlasse;

	public Map<String, Object> getObjectMap() {
		 Map<String, Object> map = new HashMap<String, Object>();
		 map.put("typeVirksomhet",typeVirksomhet);
		 map.put("antallEtasjer",antallEtasjer);
		 map.put("brtArealPrEtasje",brtArealPrEtasje);
		 map.put("utgangTerrengAlleBoenheter",utgangTerrengAlleBoenheter);
		 map.put("bareSporadiskPersonopphold",bareSporadiskPersonopphold);
		 map.put("alleKjennerRomningsVeiene",alleKjennerRomningsVeiene);
		 map.put("beregnetForOvernatting",beregnetForOvernatting);
		 map.put("liteBrannfarligAktivitet",liteBrannfarligAktivitet);
		 map.put("konsekvensAvBrann",konsekvensAvBrann);
		 map.put("arealBrannseksjonPrEtasje",arealBrannseksjonPrEtasje);
		 map.put("brannenergi",brannenergi);
		 map.put("bygningOffentligUnderTerreng",bygningOffentligUnderTerreng);
		 map.put("avstandMellomMotstVinduerIMeter",avstandMellomMotstVinduerIMeter);
		 map.put("rkl",rkl);
		 map.put("bkl",bkl);
		 map.put("brannalarmKategori",brannalarmKategori);
		 map.put("brannTiltakStrSeksjonBelastning",brannTiltakStrSeksjonBelastning);
		 map.put("kravBrannmotstSeksjVegg",kravBrannmotstSeksjVegg);
		 map.put("kravLedesystemEvakuering",kravLedesystemEvakuering);
		 map.put("trappeRomKlasse",trappeRomKlasse);
		 return map;
		 }

}
