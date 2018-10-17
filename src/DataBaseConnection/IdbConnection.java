package DataBaseConnection;

import java.util.List;

public interface IdbConnection {

    void connectToDb();

    void createNewTable(String tableName);

    String[] getEntryById(String tableName, String entryId);

    List getAllFromTable(String tableName);

    void insert(String tableName,int id, String name, double capacity);

    void updateEntry(int id, String first_name, double score);

    void deleteAllFromTable(String tableName);

    void deleteDb(String dbName);

    void deleteById(int id, String tableName);

    void closeConnection();




}