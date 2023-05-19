package main;
import java.util.*;
import dao.DAO;
import interfacce.IDAO;

public class Main_Test_02_Create
{
	public static void main(String[] args)
	{
		IDAO i = new DAO("zoojavasql");
		Map<String,String> m = new HashMap<String,String>();
		m.put("nome","Aquila");
		m.put("tipo","Pillone");
		m.put("eta","20");
		m.put("peso","35");
		System.out.println("Esito dell'inserimento: " + i.create(m));
	}
}