package DataBaseConnection;

import EntriesObject.IEntry;

import java.util.LinkedList;

public interface IdbConnection {

    void connectToDb();

    void createNewTable(IEntry entry);

    String[] getEntryById(String entryId,IEntry entry);

    LinkedList<String[]> getAllFromTable(IEntry entry);

    void insert(IEntry entry);//should db connection use be implemented inside an entry object?

    void updateEntry(IEntry entry, String[] newValues);

    void deleteAllFromTable(String tableName);

    void deleteDb(String dbName);

    void deleteById(IEntry entry);

//    String[] getSpecificData();

    void closeConnection();
}