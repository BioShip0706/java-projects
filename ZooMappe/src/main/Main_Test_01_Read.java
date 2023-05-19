package main;
import dao.DAO;
import interfacce.IDAO;

public class Main_Test_01_Read
{
	public static void main(String[] args)
	{
		String nomeDB = "zoojavasql";
		IDAO i = new DAO(nomeDB);
		String query = 	"select * from animali";
		//query = "select count(*) as 'nLibri' from libri";
		System.out.println(i.stampaArray(i.read(query)));
	}
}