package Collections.Generics;

import com.dotNet4Java.TRefObject;
import system.collections.generic.GenericDictionary;
import system.collections.generic.GenericDictionary_Enumerator;

public class Dictionary {

    public static void main(String[] arg) {
        System.out.println("            Hello! Welcome to dotNet4Java         ");
        System.out.println("==================================================");
        System.out.println("This following example demonstrates how to use .Net Dictionary.");
        System.out.println();

        try {
            // Create a new dictionary of strings, with String keys.
            GenericDictionary<String, String> OpenWith = new GenericDictionary<>(new String[]{"System.String", "System.String"});

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
            } catch (Exception error) {
                //ArgumentException
                System.out.println("An element with TKey = \"txt\" already exists.");
            }

            // The Item property is another name for the indexer, so you
            // can omit its name when accessing elements.
            System.out.println(String.format("For key = \"rtf\", value = %s.", OpenWith.get_Item("rtf")));

            // The indexer can be used to change the value associated
            // with a key.
            OpenWith.set_Item("rtf", "winword.exe");
            System.out.println(String.format("For key = \"rtf\", value = %s.", OpenWith.get_Item("rtf")));

            // If a key does not exist, setting the indexer for that key
            // adds a new key/value pair.
            OpenWith.set_Item("doc", "winword.exe");

            // The indexer throws an exception if the requested key is
            // not in the dictionary.
            try {
                System.out.println(String.format("For key = \"tif\", value =%s.", OpenWith.get_Item("tif")));
            } catch (Exception error) {
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
                System.out.println(String.format("TValue added for key = \"ht\": %s", OpenWith.get_Item("ht")));
            }

            // When you use when loop to enumerate dictionary elements from GetEnumerator,
            // the elements are retrieved as KeyValuePair objects.
            System.out.println();

            GenericDictionary_Enumerator<String, String> OpenWithEnumerator = OpenWith.GetEnumerator();
            while (OpenWithEnumerator.MoveNext()) {
                System.out.println(String.format("TKey = %s, TValue = %s", OpenWithEnumerator.get_Current().get_Key(), OpenWithEnumerator.get_Current().get_Value()));
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