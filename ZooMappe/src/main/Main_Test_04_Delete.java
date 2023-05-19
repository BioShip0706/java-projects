package main;
import dao.DAO;
import interfacce.IDAO;

public class Main_Test_04_Delete
{
	public static void main(String[] args)
	{
		IDAO i = new DAO("zoojavasql");
		int idKill = 8;
		System.out.println(	"Esito della cancellazione dell'id "	+	idKill		+ ": "	+
							(i.delete(idKill) ? "Successo"	:	"Non puoi uccidere Bill"))	;
	}
}