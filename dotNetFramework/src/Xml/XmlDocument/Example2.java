package Xml.XmlDocument;

import system.Console;
import system.xml.XmlDocument;
import system.xml.XmlNode;

//This example writes the price of a book to the console. The price node is the last child of a book node.
public class Example2 {
    public static void main(String[] arg) {
        try {

            XmlDocument doc = new XmlDocument();
            doc.LoadXml("<book ISBN='1-861001-57-5'>" +
                    "<title>Pride And Prejudice</title>" +
                    "<price>19.95</price>" +
                    "</book>");

            XmlNode root = doc.get_FirstChild();

            Console.WriteLine("Display the price element...");
            Console.WriteLine(root.get_LastChild().get_OuterXml());

        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }
}