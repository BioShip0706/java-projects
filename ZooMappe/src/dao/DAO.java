package dao;
import java.sql.*;
import java.util.*;
import database.Database;
import interfacce.IDAO;

public class DAO implements IDAO
{
	private Database db;
	
	public DAO(String nomeDB)
	{
		db = new Database(nomeDB);
	}

	@Override
	public List<Map<String, String>> read(String query)
	{
		List<Map<String,String>> ris = new ArrayList<Map<String,String>>();
		Map<String,String> mappa;
		try
		{
			db.apriConnessione();
			PreparedStatement ps = db.getC().prepareStatement(query);
			ResultSet tabella = ps.executeQuery(query);
			
			int nColonne = tabella.getMetaData().getColumnCount();
			
			while(tabella.next())
			{
				mappa = new LinkedHashMap<String,String>();
				for(int i = 1; i <= nColonne; i++)
				{
					String chiave = tabella.getMetaData().getColumnLabel(i);
					String valore = tabella.getString(i);
					mappa.put(chiave,valore);
				}
				ris.add(mappa);	
			}
		}
		catch(SQLException e)
		{
			System.out.println("Errore nella query: " + query);
			e.printStackTrace();
		}
		catch(Exception e)
		{
			System.out.println("Errore generico");
			e.printStackTrace();
		}
		finally
		{
			db.chiudiConnessione();
		}
		return ris;
	}
	
	@Override
	public List<Map<String, String>> leggiTutti()
	{
		String query = "select * from animali";
		return read(query);
	}

	@Override
	public boolean create(Map<String, String> mappa)
	{
		boolean ris;
		String queryInsert =	"insert into animali "						+
								"(nome,tipo,eta,peso) "		+
								"values "									+
								"(?,?,?,?)"								;
		try
		{
			db.apriConnessione();
			PreparedStatement ps = db.getC().prepareStatement(queryInsert);
			ps.setString(1, mappa.get("nome"));
			ps.setString(2, mappa.get("tipo"));
			ps.setString(3, mappa.get("eta"));
			ps.setString(4, mappa.get("peso"));
			
			ps.executeUpdate();
			ris = true;
		}
		catch(SQLSyntaxErrorException e)
		{
			System.out.println("Errore nella query: " + queryInsert);
			e.printStackTrace();
			ris = false;
		}
		catch(Exception e)
		{
			ris = false;
		}
		finally
		{
			db.chiudiConnessione();
		}
		return ris;
	}

	@Override
	public boolean update(Map<String, String> mappa)
	{
		boolean ris;
		String queryUpdate =	"update animali "						+
								"set nome = ?, tipo = ?, "		+
								"eta = ?, peso = ?"	+
								"where id = ?"						;
		try
		{
			db.apriConnessione();
			PreparedStatement ps = db.getC().prepareStatement(queryUpdate);
			ps.setString(1, mappa.get("nome"));
			ps.setString(2, mappa.get("tipo"));
			ps.setString(3, mappa.get("eta"));
			ps.setString(4, mappa.get("peso"));
			ps.setString(5, mappa.get("id"));
			ps.executeUpdate();
			ris = true;
		}
		catch(SQLSyntaxErrorException e)
		{
			System.out.println("Errore nella query: " + queryUpdate);
			e.printStackTrace();
			ris = false;
		}
		catch(Exception e)
		{
			ris = false;
		}
		finally
		{
			db.chiudiConnessione();
		}
		return ris;
	}
	
	public boolean cercaPerId(int id)
	{
		boolean ris = false;
		for(Map<String,String> m : leggiTutti())
			if(Integer.parseInt(m.get("id")) == id)
				ris = true;
		return ris;
	}

	@Override
	public boolean delete(int id)
	{
		boolean ris;
		String queryKill = "delete from animali where id = ?";
		if(cercaPerId(id))
		{
			try
			{
				db.apriConnessione();
				PreparedStatement ps = db.getC().prepareStatement(queryKill);
				ps.setString(1,id + "");
				ps.executeUpdate();
				ris = true;
			}
			catch(Exception e)
			{
				ris = false;
			}
			finally
			{
				db.chiudiConnessione();
			}
		}
		else
		{
			System.out.println("Id " + id + " non trovato.");
			ris = false;
		}
		return ris;
	}
}