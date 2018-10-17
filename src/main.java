import DataBaseConnection.SqliteDbConnection;

import java.util.Random;


public class main {
    public static void main (String[] args){
        SqliteDbConnection test=new SqliteDbConnection(true);
        test.connectToDb();
        test.createNewTable("students");
        int randNum =new Random().nextInt();
        test.insert("students",randNum,
        "yishaia"+Integer.toString(randNum),
                97.0);
        test.getAllFromTable("students");
        test.closeConnection();


    }

}
