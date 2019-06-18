package ark.bpmn.BrannModels;

import java.util.Map;

public class BrannDictionaryModel {
	
	public Map<String, TableInfo> BranntekniskProsjekteringDictionary;
	public class TableInfo {
		public String DmnId;
		public String DmnNavn;
		public String TekKapitel;
		public String TekLedd;
		public String TekTabell;
		public String TekBokstav;
		public String TekWebLink;
		public VariablesInfo[] InputVariablesInfo;
		public VariablesInfo[] OutputVariablesInfo;
	}
	public class  VariablesInfo {
		public String VariabelId;
		public String VariabelNavn;
		public String VariabelBeskrivelse;
		public String Ifc;
		public String IfcUrl;
	}
}







