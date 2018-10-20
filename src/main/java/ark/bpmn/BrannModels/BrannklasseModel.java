package ark.bpmn.BrannModels;
import java.util.HashMap;
import java.util.Map;

public class BrannklasseModel {
	public String typeVirksomhet;
	public Long antallEtasjer;
	public Long brtArealPrEtasje;
	public Object utgangTerrengAlleBoenheter;
	public String rkl;

	public String getTypeVirksomhet() {
		return typeVirksomhet;
	}

	public void setTypeVirksomhet(String typeVirksomhet) {
		this.typeVirksomhet = typeVirksomhet;
	}

	public Long getAntallEtasjer() {
		return antallEtasjer;
	}

	public void setAntallEtasjer(Long antallEtasjer) {
		this.antallEtasjer = antallEtasjer;
	}

	public Object getUtgangTerrengAlleBoenheter() {
		return utgangTerrengAlleBoenheter;
	}

	public void setUtgangTerrengAlleBoenheter(boolean utgangTerrengAlleBoenheter) {
		this.utgangTerrengAlleBoenheter = utgangTerrengAlleBoenheter;
	}

	public Map<String, Object> getObjectMap() {
		 Map<String, Object> map = new HashMap<String, Object>();
		 map.put("typeVirksomhet",typeVirksomhet);
		 map.put("antallEtasjer",antallEtasjer);
		 map.put("brtArealPrEtasje",brtArealPrEtasje);
		 map.put("utgangTerrengAlleBoenheter",utgangTerrengAlleBoenheter);
		 map.put("rkl",rkl);
		 return map;
		 }
}
