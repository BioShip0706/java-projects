package main;
import java.util.*;
import dao.DAO;
import interfacce.IDAO;

public class Main_Test_03_Update
{
	public static void main(String[] args)
	{
		IDAO i = new DAO("zoojavasql");
		Map<String,String> m = new HashMap<String,String>();
		m.put("nome","Piccione");
		m.put("tipo","Pillone");
		m.put("eta","20");
		m.put("peso","30");
		m.put("id","3");
		System.out.println(	"Esito dell'aggiornamento: "			+
							(i.update(m) ? "Successo" : "Errore")	);
	}
}