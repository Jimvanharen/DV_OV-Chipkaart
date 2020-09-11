package nl.hu.model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO{

    private Connection connection;

    public ReizigerDAOPsql(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean save(Reiziger reiziger) throws SQLException {
        PreparedStatement myStatement = connection.prepareStatement("INSERT INTO reiziger VALUES (?, ?, ?, ?, ?)");

        myStatement.setInt(1, reiziger.getID());
        myStatement.setString(2, reiziger.getVoorletters());
        myStatement.setString(3, reiziger.getTussenvoegsel());
        myStatement.setString(4, reiziger.getAchternaam());
        myStatement.setDate(5, Date.valueOf(reiziger.getGeboorteDatum()));

        return myStatement.execute();
    }

    @Override
    public boolean update(Reiziger reiziger) throws SQLException {
        List<Reiziger> reizigerList = findAll();
        PreparedStatement myStatement = connection.prepareStatement("UPDATE reiziger SET voorletters=?, tussenvoegsel=?, achternaam=?, geboortedatum=? WHERE reiziger_id=?");
        for (Reiziger r : reizigerList){
            if(r.getID() == reiziger.getID()){
                myStatement.setString(1, reiziger.getVoorletters());
                myStatement.setString(2, reiziger.getTussenvoegsel());
                myStatement.setString(3, reiziger.getAchternaam());
                myStatement.setDate(4, Date.valueOf(reiziger.getGeboorteDatum()));
                myStatement.setInt(5, reiziger.getID());

                return myStatement.execute();
            }
        }

        return false;
    }

    @Override
    public boolean delete(Reiziger reiziger) throws SQLException {
        PreparedStatement myStatement = connection.prepareStatement("DELETE FROM reiziger WHERE reiziger_id=?");
        myStatement.setInt(1, reiziger.getID());

        return myStatement.execute();
    }

    @Override
    public Reiziger findById(int id) throws SQLException {
        List<Reiziger> reizigerList = findAll();

        for (Reiziger r : reizigerList){
                if(r.getID() == id){
                    return r;
                }
        }
        return null;
    }

    @Override
    public List<Reiziger> findByGbDatum(String datum) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM reiziger WHERE geboortedatum=?");
        //Statement statement = connection.createStatement();
        //statement.executeQuery("SELECT * FROM reiziger AS r INNER JOIN ov_chipkaart AS o ON r.reiziger_id=o.reiziger_id");
        List<Reiziger> reizigerList = findAll();
        List<Reiziger> reizigerList1 = new ArrayList<>();
        preparedStatement.setDate(1, Date.valueOf(LocalDate.parse(datum)));

        ResultSet rs = preparedStatement.executeQuery();

        while(rs.next()){
            reizigerList1.add(new Reiziger(rs.getInt("reiziger_id"), rs.getString("voorletters"), rs.getString("tussenvoegsel"),
                    rs.getString("achternaam"), LocalDate.parse(rs.getString("geboortedatum")), new OVChipkaart(rs.getInt("kaart_nummer"),
                    rs.getDate("geldig_tot"), rs.getInt("klasse"), rs.getDouble("saldo"), rs.getInt("reiziger_id"), null)));
        }

        return reizigerList1;
    }

    @Override
    public List<Reiziger> findAll() throws SQLException{
        List<Reiziger> reizigerList = new ArrayList<Reiziger>();
        Statement myStatement = connection.createStatement();
        String query = "SELECT * FROM reiziger AS r INNER JOIN ov_chipkaart AS o ON r.reiziger_id=o.reiziger_id";

        ResultSet rs = myStatement.executeQuery(query);

        while(rs.next()){
            Reiziger reiziger = new Reiziger(rs.getInt("reiziger_id"), rs.getString("voorletters"), rs.getString("tussenvoegsel"),
                    rs.getString("achternaam"), LocalDate.parse(rs.getString("geboortedatum")), new OVChipkaart(rs.getInt("kaart_nummer"),
                    rs.getDate("geldig_tot"), rs.getInt("klasse"), rs.getDouble("saldo"), rs.getInt("reiziger_id"), null));
            reizigerList.add(reiziger);
        }

        return reizigerList;
    }
}