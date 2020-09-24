package ActiveDirectory;

import system.directoryservices.DirectoryEntry;
import system.directoryservices.DirectorySearcher;
import system.directoryservices.SearchResult;
import system.directoryservices.SearchResultCollection;

public class Program {

    public static void main(String[] arg) {
        try {
            DirectoryEntry Entry = new DirectoryEntry("LDAP://MCBcorp, DC=com");
            System.out.println("Name = " + Entry.get_Name());
            System.out.println("Path = " + Entry.get_Path());
            System.out.println("SchemaClassName = " + Entry.get_SchemaClassName());

            DirectorySearcher DirSearcher = new DirectorySearcher(Entry);
            DirSearcher.set_Filter("(objectClass=*)");
            System.out.println("Active Directory Information");
            System.out.println("=====================================");

            SearchResultCollection searchResultCollection = DirSearcher.FindAll();
            for (int i = 0; i < searchResultCollection.get_Count(); i++) {
                SearchResult searchResult = searchResultCollection.get_Item(i);
                System.out.println(searchResult.GetDirectoryEntry().get_Name());
                System.out.println(searchResult.GetDirectoryEntry().get_Name());
                System.out.println("===================================");
            }
        } catch (Exception E) {
            System.out.println(E.getMessage());
        }
    }
}

