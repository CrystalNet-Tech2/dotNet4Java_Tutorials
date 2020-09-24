package Directory_Services;

import com.dotNet4Java.TClrAssembly;
import com.dotNet4Java.TClrObject;
import com.dotNet4Java.api.EClrError;
import com.dotNet4Java.api.core.DotNetNativeTypes;

class DirectoryEntry extends TClrObject {
    PropertyCollection properties;

    public DirectoryEntry(String path) throws EClrError {
        super("System.DirectoryServices.DirectoryEntry", path);
    }

    public DirectoryEntry(DotNetNativeTypes.ClrObject clrObject) {
        super(clrObject);
    }

    public String getName() throws EClrError {
        return getPropertyValueAsString("Name");
    }

    public String getPath() throws EClrError {
        return getPropertyValueAsString("Path");
    }

    public String getSchemaClassName() throws EClrError {
        return getPropertyValueAsString("SchemaClassName");
    }

    public PropertyCollection getProperties() throws EClrError {
        if (properties == null) {
            DotNetNativeTypes.ClrObject clrObject = getPropertyValueAsClrObject("Properties");
            properties = clrObject == null ? null : new PropertyCollection(clrObject);
        }
        return properties;
    }
}

class DirectorySearcher extends TClrObject {

    public DirectorySearcher(DirectoryEntry entry) throws EClrError {
        super("System.DirectoryServices.DirectorySearcher", entry);
    }

    public String getFilter() throws EClrError {
        return getPropertyValueAsString("Filter");
    }

    public void setFilter(String value) throws EClrError {
        setPropertyValue("Filter", value);
    }

    public SearchResultCollection FindAll() throws EClrError {
        DotNetNativeTypes.ClrObject clrObject = getPropertyValueAsClrObject("FindAll");
        return clrObject == null ? null : new SearchResultCollection(clrObject);
    }

    public SearchResult FindOne() throws EClrError {
        DotNetNativeTypes.ClrObject clrObject = getPropertyValueAsClrObject("FindOne");
        return clrObject == null ? null : new SearchResult(clrObject);
    }
}

class SearchResultCollection extends TClrObject {

    public SearchResultCollection(DotNetNativeTypes.ClrObject clrObject) {
        super(clrObject);
    }

    public int getCount() throws EClrError {
        return getPropertyValueAsInt("Count");
    }

    public SearchResult get(int index) throws EClrError {
        DotNetNativeTypes.ClrObject clrObject = getPropertyIndexValueAsClrObject("Item", index);
        return clrObject == null ? null : new SearchResult(clrObject);
    }
}

class SearchResult extends TClrObject {
    DirectoryEntry directoryEntry;

    public SearchResult(DotNetNativeTypes.ClrObject clrObject) {
        super(clrObject);
    }

    public String getPath() throws EClrError {
        return getPropertyValueAsString("Path");
    }

    public DirectoryEntry GetDirectoryEntry() throws EClrError {
        if (directoryEntry == null) {
            DotNetNativeTypes.ClrObject clrObject = invokeClrObjectMethod("GetDirectoryEntry");
            directoryEntry = clrObject == null ? null : new DirectoryEntry(clrObject);
        }
        return directoryEntry;
    }
}

class PropertyCollection extends TClrObject {

    public PropertyCollection(DotNetNativeTypes.ClrObject clrObject) {
        super(clrObject);
    }
}

public class ActiveDirectory {

    static void loadDirectoryServicesAssemblyFromGAC() throws EClrError {
        TClrAssembly.loadWithPartialName("System.DirectoryServices");
    }


    public static void main(String[] arg) {
        try {
            loadDirectoryServicesAssemblyFromGAC();

            DirectoryEntry Entry = new DirectoryEntry("LDAP://MCBcorp, DC=com");
            System.out.println("Name = " + Entry.getName());
            System.out.println("Path = " + Entry.getPath());
            System.out.println("SchemaClassName = " + Entry.getSchemaClassName());

            DirectorySearcher DirSearcher = new DirectorySearcher(Entry);
            DirSearcher.setFilter("(objectClass=*)");
            System.out.println("Active Directory Information");
            System.out.println("=====================================");

            SearchResultCollection searchResultCollection = DirSearcher.FindAll();
            for (int i = 0; i < searchResultCollection.getCount(); i++) {
                SearchResult searchResult = searchResultCollection.get(i);
                System.out.println(searchResult.GetDirectoryEntry().getName());
                System.out.println(searchResult.GetDirectoryEntry().getName());
                System.out.println("===================================");
            }
        } catch (Exception E) {
            System.out.println(E.getMessage());
        }
    }
}

