package nl.hu;

import nl.hu.model.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class Main {

    private static final String CONNECTION_URL = "jdbc:postgresql://localhost/ovchip";
    private static final String USERNAME_STD = "postgres";
    private static final String PASSWORD_DB = "karatekid2001";

    private ReizigerDAOPsql sql;
    private AdresDAOPsql sql2;
    private OVChipkaartDAOPsql sql3;
    private Connection connection;

    public Main() throws SQLException {
        connection = DriverManager.getConnection(CONNECTION_URL, USERNAME_STD, PASSWORD_DB);
        sql = new ReizigerDAOPsql(connection);
        sql2 = new AdresDAOPsql(connection, sql);
        sql3 = new OVChipkaartDAOPsql(connection);
        //testReizigerDAO(sql);
        //testAdresDAO(sql2);
        testOVChipkaartDAO(sql3);
        closeConnection();
    }

    public static void main(String[] args) throws SQLException {
        //Connect to database
        new Main();
    }

    private void closeConnection() throws SQLException {
        connection.close();
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
        Reiziger sietske = new Reiziger(77, "S", "", "Boers", LocalDate.parse(gbdatum), null);

        //VOEG EEN SPECEFIEKE REIZIGER TOE
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        rdao.save(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

        //UPDATE REIZIGER
        rdao.update(new Reiziger(5, "B", "van", "xd", LocalDate.parse("2001-09-20"), null));
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
        System.out.println("Gevonden met ID: " + rdao.findById(2) + "\n");

        //VIND REIZIGER(S) VIA GEBOORTEDATUM
       /* for(Reiziger r : rdao.findByGbDatum("2001-09-20")){
            System.out.println("Gevonden met geboortedatum: " + r);
        }

        */
    }

    private void testAdresDAO(AdresDAO adao) throws SQLException {
        System.out.println("\n---------- Test ReizigerDAO -------------");

        // Haal alle reizigers op uit de database
        List<Adres> adresList = adao.findAll();
        System.out.println("[Test] AdresDAO.findAll() geeft de volgende adressen:");
        for (Adres a : adresList) {
            System.out.println(a);
        }
        System.out.println();

        adao.findByReiziger(new Reiziger(1, "B", "van", "xd", LocalDate.parse("2001-09-20"), null));
        System.out.print("[Test] Eerst " + adresList.size() + " reizigers, na ReizigerDAO.save()");
        Reiziger reiziger = new Reiziger(16, "L", "van", "Haren", LocalDate.parse("2005-05-02"), null);
        sql.save(reiziger);
        Adres adres = new Adres(16, "4125RD", "3", "Notaris appelpad", "Vianen", 16, null);
        adao.save(adres);
        adresList = adao.findAll();
        System.out.println(adresList.size() + " adreslist grootte met lijst geupdate lijst: \n\n");
        adresList = adao.findAll();
        for(Adres a : adresList){
            System.out.println(a);
        }

        adao.delete(adres);
        sql.delete(reiziger);
        adresList = adao.findAll();
        System.out.println("\n Na delete is de grootte van de adresList " + adresList.size() + "\n");
        for (Adres a : adresList){
            System.out.println(a);
        }

        adao.update(new Adres(1, "3535JK", "4", "Coole straat", "Vianen", 1, null));
        System.out.println("Na update is de lijst: \n");
        adresList = adao.findAll();
        for (Adres a : adresList){
            System.out.println(a);
        }
    }

    private void testOVChipkaartDAO(OVChipkaartDAO chipDAO) throws SQLException{
        List<OVChipkaart> ovChipkaartList = chipDAO.findAll();
        System.out.println("[TEST] GROOTTE VAN LIST VOOR SAVE: " + ovChipkaartList.size() + "\n\n");
        chipDAO.save(new OVChipkaart(23890, Date.valueOf("2020-09-20"), 2, 66.66, 2, new Reiziger(2, null, null, null, null, null)));
        ovChipkaartList = chipDAO.findAll();
        System.out.println("[TEST] GROOTTE VAN LIST NA SAVE: " + ovChipkaartList.size() + "\n\n");
        chipDAO.update(new OVChipkaart(35283, Date.valueOf("2020-09-20"), 1, 66.66, 2, new Reiziger( 2, null, null, null, null, null)));
        System.out.println("GEUPDATE LIST: \n (reiziger met id 2 en kaartnummer 35283  gaat naar klasse 1)");
        ovChipkaartList = chipDAO.findAll();
        for (OVChipkaart ovChipkaart : ovChipkaartList){
            System.out.println(ovChipkaart);
        }
        System.out.println("\n");
        chipDAO.delete(new OVChipkaart(23890, Date.valueOf("2020-09-20"), 2, 66.66, 2, new Reiziger(2, null, null, null, null, null)));
        ovChipkaartList = chipDAO.findAll();
        System.out.println("GROOTTE VAN DE LIST NA DE DELETE " + ovChipkaartList.size());
        List<OVChipkaart> ovChipkaarts = chipDAO.findByReiziger(new Reiziger(2, null, null, null, null, null));

        System.out.println("\n");

        System.out.println("[TEST] ALLE REIZIGERS GEVONDEN MET ID 2 \n");
        for (OVChipkaart os : ovChipkaarts){
            System.out.println(os);
        }
        System.out.println("\n");
        ovChipkaarts.clear();

        System.out.println("[TEST] ALLLE OV CHIPKAARTEN GEVONDEN DIE AANWEZIG ZIJN");
        ovChipkaarts = chipDAO.findAll();
        for (OVChipkaart os : ovChipkaarts){
            System.out.println(os);
        }

    }
}