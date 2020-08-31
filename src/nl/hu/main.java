package nl.hu;

import java.sql.*;

public class main {


    public static void main(String[] args) {
        try{
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ovchip", "postgres", "karatekid2001");
            Statement myStatement = con.createStatement();
            ResultSet set = myStatement.executeQuery("select * from reiziger");

            while(set.next()){
                System.out.println(set.getString("voorletters") + ". " + set.getString("achternaam") + " (" + set.getString("geboortedatum") + ") ");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}