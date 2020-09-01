package nl.hu;

import nl.hu.model.Reiziger;
import nl.hu.model.ReizigerDAO;
import nl.hu.model.ReizigerDAOPsql;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class Main {

    private static final String CONNECTION_URL = "jdbc:postgresql://localhost/ovchip";
    private static final String USERNAME_STD = "postgres";
    private static final String PASSWORD_DB = "karatekid2001";

    private ReizigerDAOPsql sql;
    private Connection connection;

    public Main() throws SQLException {
        connection = DriverManager.getConnection(CONNECTION_URL, USERNAME_STD, PASSWORD_DB);
        sql = new ReizigerDAOPsql(connection);
        testReizigerDAO(sql);
    }

    public static void main(String[] args) throws SQLException {
        //Connect to database
        new Main();
    }

    private void tempTestFunc_001() throws SQLException {
        Statement tempStatement = connection.createStatement();

        ResultSet rs = tempStatement.executeQuery("SELECT reiziger_id FROM reiziger");

        while(rs.next()){
            System.out.println(rs.getString("reiziger_id"));
        }
    }

    private static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test ReizigerDAO -------------");

        // Haal alle reizigers op uit de database
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
        System.out.println();

        // Maak een nieuwe reiziger aan en persisteer deze in de database
        String gbdatum = "1981-03-14";
        Reiziger sietske = new Reiziger(77, "S", "", "Boers", LocalDate.parse(gbdatum));

        //VOEG EEN SPECEFIEKE REIZIGER TOE
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        rdao.save(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

        //UPDATE REIZIGER
        rdao.update(new Reiziger(5, "B", "van", "xd", LocalDate.parse("2001-09-20")));
        reizigers = rdao.findAll();
        System.out.println("[TEST] Geupdate lijst!: " + "\n");
        for (Reiziger r : reizigers){
            System.out.println(r);
        }
        System.out.println("\n");

        //VERWIJDER EEN SPECEFIEKE REIZIGER
        System.out.println("[TEST] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.delete() ");
        rdao.delete(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");


        //VIND REIZIGER VIA ID
        System.out.println("Gevonden met ID: " + rdao.findById(1) + "\n");

        //VIND REIZIGER(S) VIA GEBOORTEDATUM
        for(Reiziger r : rdao.findByGbDatum("2001-09-20")){
            System.out.println("Gevonden met geboortedatum: " + r);
        }

    }
}