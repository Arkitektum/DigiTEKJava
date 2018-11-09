package ark.bpmn.BrannModels;

import java.util.HashMap;
import java.util.Map;

public class BranntekniskProsjekteringModel {
	public String typeVirksomhet;
	public Integer antallEtasjer;
	public Integer brtArealPrEtasje;
	public Object utgangTerrengAlleBoenheter;
	
	public Object bareSporadiskPersonopphold;
	public Object alleKjennerRomningsVeiene;
	public Object beregnetForOvernatting;
	public Object liteBrannfarligAktivitet;
	public String konsekvensAvBrann;
	public Integer arealBrannseksjonPrEtasje;
	public Integer brannenergi;
	public Object bygningOffentligUnderTerreng;

	public Integer avstandMellomMotstVinduerIMeter;
	public String rkl;
	public String bkl;
	public Integer brannalarmKategori;
	public String brannTiltakStrSeksjonBelastning;
	public String kravBrannmotstSeksjVegg;
	public Boolean kravLedesystemEvakuering;
	public String trappeRomKlasse;
	
	public Integer brtArealBygg;
	public Object kravOmHeis;
	
	
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
		 map.put("brtArealBygg",brtArealBygg);
		 map.put("kravOmHeis",kravOmHeis);
		 return map;
		 }

}
