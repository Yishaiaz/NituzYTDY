import DataBaseConnection.SqliteDbConnection;
import EntriesObject.AEntry;
import EntriesObject.User;

import java.sql.Date;


public class main {
    public static void main (String[] args){
        SqliteDbConnection test=new SqliteDbConnection(true);
        test.connectToDb();

        AEntry testEntry= new User("userName","testPass", Date.valueOf("2010-01-01"),"testFN","testLN","testCity");

        test.createNewTable("Users", testEntry.getColumnsTitles());
        test.connectToDb();
        test.insert("Users",testEntry,1);
        test.connectToDb();
        for (String s :
                test.getEntryById("Users","1",testEntry)) {
            System.out.println(s+",");
        }
        String[] testValues = {"yishaiaZ","password123","2010-01-02","yishaia","zabary","haifa"};
        test.updateEntry(testEntry,"Users","1",testValues);
        for (String s :
                test.getEntryById("Users","1",testEntry)) {
            System.out.println(s+",");
        }
        for (String[] s:test.getAllFromTable(testEntry,"Users")
             ) {
            System.out.println("line:\n");
            for (String sInner :
                   s ) {
                System.out.println(sInner+",");

            }
            System.out.println("\n");

        }

    }

}
