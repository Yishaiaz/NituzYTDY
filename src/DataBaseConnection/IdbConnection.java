package DataBaseConnection;

import EntriesObject.IEntry;

import java.util.LinkedList;

public interface IdbConnection {

    void connectToDb();

    void createNewTable(String tableName,String[] tableColumns);

    String[] getEntryById(String tableName,String entryId,IEntry entry);

    public LinkedList<String[]> getAllFromTable(IEntry entry, String tableName);

    void insert(String tableName, IEntry entry,int id);

    void updateEntry(IEntry entry,String tableName,String entryId, String[] newValues);

    void deleteAllFromTable(String tableName);

    void deleteDb(String dbName);

    void deleteById(IEntry entry,String tableName,String entryId);

    void closeConnection();




}