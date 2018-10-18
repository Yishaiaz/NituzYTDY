package EntriesObject;

public interface IEntry {
    String[] getColumnsTitles();
    String[] getAllData();
    String getIdentifiers();
    String toString();
    void insertToDb();
    void deleteFromDb();


}
