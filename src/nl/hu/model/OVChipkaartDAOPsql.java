package nl.hu.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                    rs.getDouble("saldo"), rs.getInt("reiziger_id")));
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
