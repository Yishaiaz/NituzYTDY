package DataBaseConnection;

import EntriesObject.IEntry;

import java.util.LinkedList;

public interface IdbConnection {

    void connectToDb();

    void createNewTable(String tableName,String[] tableColumns);

    String[] getEntryById(String tableName,String entryId,IEntry entry);

    public LinkedList<String[]> getAllFromTable(IEntry entry, String tableName);

    String insert(String tableName, IEntry entry);//should db connection use be implemented inside an entry object?

    void updateEntry(IEntry entry,String tableName,String entryId, String[] newValues);

    void deleteAllFromTable(String tableName);

    void deleteDb(String dbName);

    void deleteById(IEntry entry,String tableName,String entryId);

    void closeConnection();




}