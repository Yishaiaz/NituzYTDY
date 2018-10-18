package EntriesObject;

import DataBaseConnection.IdbConnection;

public interface IEntry {
    String[] getColumnsTitles();
    String[] getAllData();
    String getIdentifiers();
    String toString();
    void insertToDb(IdbConnection idbConnection);
    void deleteFromDb(IdbConnection idbConnection);


}
