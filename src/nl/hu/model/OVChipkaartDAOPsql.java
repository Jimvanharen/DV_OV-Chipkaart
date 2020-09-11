package nl.hu.model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OVChipkaartDAOPsql implements OVChipkaartDAO{

    private Connection connection;

    public OVChipkaartDAOPsql(Connection connection){
        this.connection = connection;
    }

    @Override
    public List<OVChipkaart> findByReiziger(Reiziger reiziger) throws SQLException {
        String query = "SELECT * FROM ov_chipkaart WHERE reiziger_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, reiziger.getID());
        ResultSet rs = preparedStatement.executeQuery();
        List<OVChipkaart> ovChipkaarts = new ArrayList<OVChipkaart>();

        while (rs.next()){
            ovChipkaarts.add(new OVChipkaart(rs.getInt("kaart_nummer"), rs.getDate("geldig_tot"), rs.getInt("klasse"),
                    rs.getDouble("saldo"), rs.getInt("reiziger_id"), reiziger));
        }
        return ovChipkaarts;
    }

    @Override
    public List<OVChipkaart> findAll() throws SQLException {
        List<OVChipkaart> ovChipkaarts = new ArrayList<OVChipkaart>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM ov_chipkaart INNER JOIN reiziger ON ov_chipkaart.reiziger_id = reiziger.reiziger_id");

        while (resultSet.next()){
            ovChipkaarts.add(new OVChipkaart(resultSet.getInt("kaart_nummer"), resultSet.getDate("geldig_tot"), resultSet.getInt("klasse"),
                    resultSet.getDouble("saldo"), resultSet.getInt("reiziger_id"), new Reiziger(resultSet.getInt("reiziger_id"),
                    resultSet.getString("voorletters"), resultSet.getString("tussenvoegsel"), resultSet.getString("achternaam"),
                    LocalDate.parse(resultSet.getString("geboortedatum")), null)));
        }

        return ovChipkaarts;
    }

    @Override
    public boolean save(OVChipkaart ovChipkaart) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ov_chipkaart VALUES (?, ?, ?, ?, ?)");
        preparedStatement.setInt(1, ovChipkaart.getKaart_nummer());
        preparedStatement.setDate(2, ovChipkaart.getGeldig_tot());
        preparedStatement.setInt(3, ovChipkaart.getKlasse());
        preparedStatement.setDouble(4, ovChipkaart.getSaldo());
        preparedStatement.setInt(5, ovChipkaart.getReiziger_id());
        return preparedStatement.execute();
    }

    @Override
    public boolean update(OVChipkaart ovChipkaart) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE ov_chipkaart SET kaart_nummer=?, geldig_tot=?, klasse=?, saldo=? WHERE reiziger_id=? AND kaart_nummer=?");
        preparedStatement.setInt(1, ovChipkaart.getKaart_nummer());
        preparedStatement.setDate(2, ovChipkaart.getGeldig_tot());
        preparedStatement.setInt(3, ovChipkaart.getKlasse());
        preparedStatement.setDouble(4, ovChipkaart.getSaldo());
        preparedStatement.setInt(5, ovChipkaart.getReiziger_id());
        preparedStatement.setInt(6, ovChipkaart.getKaart_nummer());
        return preparedStatement.execute();
    }

    @Override
    public boolean delete(OVChipkaart ovChipkaart) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM ov_chipkaart WHERE reiziger_id=? AND kaart_nummer=?");
        preparedStatement.setInt(1, ovChipkaart.getReiziger_id());
        preparedStatement.setInt(2, ovChipkaart.getKaart_nummer());
        return preparedStatement.execute();
    }
}
