package Xml.XmlDocument;

import system.Console;
import system.xml.XmlDocument;

//This example gets the root node and then uses that node to output the contents of the document to the console.
public class Example1 {
    public static void main(String[] arg) {
        try {
            //Create the XmlDocument.
            XmlDocument doc = new XmlDocument();
            doc.LoadXml("<?xml version='1.0' ?>" +
                    "<book genre='novel' ISBN='1-861001-57-5'>" +
                    "<title>Pride And Prejudice</title>" +
                    "</book>");

            //Display the document element.
            Console.WriteLine(doc.get_DocumentElement().get_OuterXml());
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }
}
