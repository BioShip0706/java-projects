package interfacce;
import java.util.*;

public interface IDAO
{
	boolean create(Map<String,String> mappa);
	List<Map<String,String>> read(String query);
	List<Map<String,String>> leggiTutti();
	boolean update(Map<String,String> mappa);
	boolean delete(int id);
	
	default String stampaArray(List<Map<String,String>> elenco)
	{
		String ris = "";
		for(Map<String,String> m : elenco)
			ris += m + "\n";
		return ris;
	}
	
	default double mediaPrezzi()
	{
		double ris = 0;
		for(Map<String,String> m : leggiTutti())
			ris += Double.parseDouble(m.get("prezzo"));
		ris /= leggiTutti().size();
		return ris;
	}
}