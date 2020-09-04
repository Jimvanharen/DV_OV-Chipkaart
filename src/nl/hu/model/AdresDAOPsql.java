package nl.hu.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdresDAOPsql implements AdresDAO{

    private Connection connection;
    private ReizigerDAO rdao;


    public AdresDAOPsql(Connection conn, ReizigerDAO rdao){
        this.connection = conn;
        this.rdao = rdao;
    }

    @Override
    public boolean save(Adres adres) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO adres VALUES (?, ?, ?, ?, ?, ?)");
        preparedStatement.setInt(1, adres.getId());
        preparedStatement.setString(2, adres.getPostcode());
        preparedStatement.setString(3, adres.getHuisnummer());
        preparedStatement.setString(4, adres.getStraat());
        preparedStatement.setString(5, adres.getWoonplaats());
        preparedStatement.setInt(6, adres.getReiziger_id());

        return preparedStatement.execute();
    }

    @Override
    public boolean update(Adres adres) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE adres SET postcode=?, huisnummer=?, straat=?, woonplaats=? WHERE adres_id=?");
        preparedStatement.setString(1, adres.getPostcode());
        preparedStatement.setString(2, adres.getHuisnummer());
        preparedStatement.setString(3, adres.getStraat());
        preparedStatement.setString(4, adres.getWoonplaats());
        preparedStatement.setInt(5, adres.getId());

        return preparedStatement.execute();
    }

    @Override
    public boolean delete(Adres adres) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM adres WHERE adres_id=? AND reiziger_id=?");
        preparedStatement.setInt(1, adres.getId());
        preparedStatement.setInt(2, adres.getReiziger_id());

        return preparedStatement.execute();
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) throws SQLException {
       PreparedStatement preparedStatement = connection.prepareStatement("SELECT adres_id, reiziger_id FROM adres");
       List<Adres> adresList = findAll();

       for(Adres adres : adresList){
           if(reiziger.getID() == adres.getId()){
               return adres;
           }
       }

        return null;
    }

    @Override
    public List<Adres> findAll() throws SQLException {
        List<Adres> adresList = new ArrayList<>();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM adres");

        int counter = 0;
        while(resultSet.next()){
            Adres adres = new Adres(resultSet.getInt("adres_id"), resultSet.getString("postcode"), resultSet.getString("huisnummer"),
                    resultSet.getString("straat"), resultSet.getString("woonplaats"), resultSet.getInt("reiziger_id"), rdao.findById(resultSet.getInt("reiziger_id")));
            adresList.add(adres);
            counter++;
        }
        return adresList;
    }
}