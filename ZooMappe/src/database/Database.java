package database;
import java.sql.*;
//La classe Database � un esempio di PATTERN
//Un PATTERN � una soluzione generica a un problema ricorrente.
//Database � un esempio di pattern FACADE
//Una FACADE nasconde tutto ci� che non serve al programmatore per dargli la possibilit� di
//richiamare solo ci� su cui poi potr� lavorare.
//La classe Database al suo interno ha:
//- getC() che restituisce la connessione
//- apriConnessione() che stabilisce una connessione al DB
//- chiudiConnessione() che chiude la connessione al DB
//- Il costruttore del DB.
//Grazie al pattern Facade il programmatore pu� richiamare ognuno di questi metodi senza
//dover riscrivere o aver conoscenza del codice al loro interno.
//Facade non nasconde con la logica di incapsulare ma per migliorare il lavoro del programmatore
//e liberare le classi da codice extra.
//Facade � un pattern di tipo ARCHITETTURALE.
public class Database
{
	private String percorso;
	private String username;
	private String password;
	private Connection c;
	private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
	
	public Database(String nomeDB)
	{
		setPercorso(nomeDB);
		username = "root";
		password = "root";
	}

	public Connection getC()
	{
		return c;
	}

	public void setPercorso(String nomeDB)
	{
		this.percorso = "jdbc:mysql://localhost:3306/" + nomeDB + "?";
	}
	
	public void apriConnessione()
	{
		try
		{
			Class.forName(DRIVER);
			c = DriverManager.getConnection(percorso,username,password);
		}
		catch(Exception e)
		{
			System.out.println("Controlla build path, username e password!");
			e.printStackTrace();
		}
	}
	
	public void chiudiConnessione()
	{
		try
		{
			c.close();
		}
		catch(Exception e)
		{
			System.out.println("Non � stato possibile chiudere la connessione.");
		}
	}
}