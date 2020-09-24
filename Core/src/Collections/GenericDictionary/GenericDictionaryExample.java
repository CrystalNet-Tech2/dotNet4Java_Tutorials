package Collections.GenericDictionary;

import com.dotNet4Java.TClrArray;
import com.dotNet4Java.TClrGenericObject;
import com.dotNet4Java.TClrObject;
import com.dotNet4Java.TRefObject;
import com.dotNet4Java.api.EClrError;


/**
 * Java equivalent of the .Net generic Dictionary class 'Dictionary<,>'
 */
class Dictionary<TKey, TValue> extends TClrGenericObject {
    public Dictionary(String[] genericTypeNames) throws EClrError {
        super("System.Collections.Generic.Dictionary`2", genericTypeNames, TClrArray.emptyObjectArray());
    }

    public int getCount() throws EClrError {
        return getPropertyValueAsInt("Count");
    }

    public TValue get(TKey key) throws Exception {
        return (TValue) getPropertyIndexValue("Item", new String[]{getTypeParameters()[0].getFullName()}, new Object[]{key});
    }

    public void set(TKey key, TValue value) throws Exception {
        setPropertyIndexValue("Item", TClrArray.of(getTypeParameters()[0].getFullName()), TClrArray.of(key), value);
    }

    public boolean ContainsKey(TKey key) throws Exception {
        return invokeBooleanMethod("ContainsKey", TClrArray.of(getTypeParameters()[0].getFullName()), TClrArray.of(key));
    }

    public boolean TryGetValue(TKey key, TRefObject<TValue> value) throws Exception {
        return invokeBooleanMethod("TryGetValue", TClrArray.of(getTypeParameters()[0].getFullName(),
                getTypeParameters()[1].makeByRefType().getFullName()), TClrArray.of(key, value));
    }

    public TKey GetKey(int index) throws EClrError {
        return (TKey) invokeMethod("GetKey", TClrArray.of("System.Int32"), TClrArray.of(index));
    }

    public int Add(TKey key, TValue value) throws Exception {
        return invokeIntMethod("Add", TClrArray.of(getTypeParameters()[0].getFullName(), getTypeParameters()[1].getFullName()), TClrArray.of(key, value));
    }

    public void Remove(TKey key) throws Exception {
        invokeVoidMethod("Remove", TClrArray.of(getTypeParameters()[0].getFullName()), TClrArray.of(key));
    }

    public DictionaryEnumerator<TKey, TValue> GetEnumerator() throws EClrError {
        Object enumerator = invokeMethod("GetEnumerator");
        return enumerator == null ? null : new DictionaryEnumerator<>(enumerator);
    }
}

/**
 * Java equivalent of the .Net Dictionary<TKey,TValue>.Enumerator class
 */
class DictionaryEnumerator<TKey, TValue> extends TClrObject {

    public DictionaryEnumerator(Object enumerator) throws EClrError {
        super(enumerator);
    }

    public DictionaryKeyValuePair<TKey, TValue> getCurrent() throws EClrError {
        return new DictionaryKeyValuePair<TKey, TValue>(getPropertyValue("Current"));
    }

    public boolean MoveNext() throws EClrError {
        return invokeBooleanMethod("MoveNext");
    }
}

/**
 * Java equivalent of the .Net KeyValuePair<TKey,TValue> class
 */
class DictionaryKeyValuePair<TKey, TValue> extends TClrObject {
    public DictionaryKeyValuePair(Object enumerator) throws EClrError {
        super(enumerator);
    }

    public TKey getKey() throws EClrError {
        return (TKey) getPropertyValue("Value");
    }

    public TValue getValue() throws EClrError {
        return (TValue) getPropertyValue("Value");
    }
}

public class GenericDictionaryExample {

    public static void main(String[] arg) {
        System.out.println("            Hello! Welcome to dotNet4Java         ");
        System.out.println("==================================================");
        System.out.println("This following example demonstrates how to use .Net Dictionary.");
        System.out.println();

        try {
            // Create a new dictionary of strings, with string keys.
            Dictionary<String, String> OpenWith = new Dictionary<>(new String[]{"System.String", "System.String"});

            // Add some elements to the dictionary. There are no
            // duplicate keys, but some of the values are duplicates.
            OpenWith.Add("txt", "notepad.exe");
            OpenWith.Add("bmp", "paint.exe");
            OpenWith.Add("dib", "paint.exe");
            OpenWith.Add("rtf", "wordpad.exe");

            // The Add method throws an exception if the new key is
            // already in the dictionary.
            try {
                OpenWith.Add("txt", "winword.exe");
            } catch (EClrError error) {
                //ArgumentException
                System.out.println("An element with TKey = \"txt\" already exists.");
            }

            // The Item property is another name for the indexer, so you
            // can omit its name when accessing elements.
            System.out.println(String.format("For key = \"rtf\", value = %s.", OpenWith.get("rtf")));

            // The indexer can be used to change the value associated
            // with a key.
            OpenWith.set("rtf", "winword.exe");
            System.out.println(String.format("For key = \"rtf\", value = %s.", OpenWith.get("rtf")));

            // If a key does not exist, setting the indexer for that key
            // adds a new key/value pair.
            OpenWith.set("doc", "winword.exe");

            // The indexer throws an exception if the requested key is
            // not in the dictionary.
            try {
                System.out.println(String.format("For key = \"tif\", value =%s.", OpenWith.get("tif")));
            } catch (EClrError error) {
                //(KeyNotFoundException)
                System.out.println("TKey = \"tif\" is not found.");
            }

            // When a program often has to try keys that turn out not to
            // be in the dictionary, TryGetValue can be a more efficient
            // way to retrieve values.
            TRefObject<String> TValue = new TRefObject<>(null);
            //if (OpenWith.TryGetValue("tif", TValue))
            if (OpenWith.TryGetValue("rtf", TValue))
                System.out.println(String.format("For key = \"tif\", value = %s.", TValue.argValue));
            else
                System.out.println("TKey = \"tif\" is not found.");

            // ContainsKey can be used to test keys before inserting them.
            if (!OpenWith.ContainsKey("ht")) {
                OpenWith.Add("ht", "hypertrm.exe");
                System.out.println(String.format("TValue added for key = \"ht\": %s", OpenWith.get("ht")));
            }

            // When you use when loop to enumerate dictionary elements from GetEnumerator,
            // the elements are retrieved as KeyValuePair objects.
            System.out.println();

            DictionaryEnumerator<String, String> OpenWithEnumerator = OpenWith.GetEnumerator();
            while (OpenWithEnumerator.MoveNext()) {
                System.out.println(String.format("TKey = %s, TValue = %s", OpenWithEnumerator.getCurrent().getKey(), OpenWithEnumerator.getCurrent().getValue()));
            }

            // Use the Remove method to remove a key/value pair.
            System.out.println("Remove(\"doc\")");
            OpenWith.Remove("doc");

            if (!OpenWith.ContainsKey("doc"))
                System.out.println("TKey \"doc\" is not found.");

        } catch (Exception eClrError) {
            eClrError.printStackTrace();
        }
    }
}