package Globalization.CultureInfo;

import system.Console;
import system.globalization.CultureInfo;

public class Program {

    public static void main(String[] arg) {

        try {
            // Creates and initializes the CultureInfo which uses the international sort.
            CultureInfo myCIintl = new CultureInfo("es-ES", false);

            // Creates and initializes the CultureInfo which uses the traditional sort.
            CultureInfo myCItrad = new CultureInfo(0x040A, false);

            // Displays the properties of each culture.
            Console.WriteLine("{0,-31}{1,-47}{2,-25}", "PROPERTY", "INTERNATIONAL", "TRADITIONAL");
            Console.WriteLine("{0,-31}{1,-47}{2,-25}", "CompareInfo", myCIintl.get_CompareInfo(), myCItrad.get_CompareInfo());
            Console.WriteLine("{0,-31}{1,-47}{2,-25}", "DisplayName", myCIintl.get_DisplayName(), myCItrad.get_DisplayName());
            Console.WriteLine("{0,-31}{1,-47}{2,-25}", "EnglishName", myCIintl.get_EnglishName(), myCItrad.get_EnglishName());
            Console.WriteLine("{0,-31}{1,-47}{2,-25}", "IsNeutralCulture", myCIintl.get_IsNeutralCulture(), myCItrad.get_IsNeutralCulture());
            Console.WriteLine("{0,-31}{1,-47}{2,-25}", "IsReadOnly", myCIintl.get_IsReadOnly(), myCItrad.get_IsReadOnly());
            Console.WriteLine("{0,-31}{1,-47}{2,-25}", "LCID", myCIintl.get_LCID(), myCItrad.get_LCID());
            Console.WriteLine("{0,-31}{1,-47}{2,-25}", "Name", myCIintl.get_Name(), myCItrad.get_Name());
            Console.WriteLine("{0,-31}{1,-47}{2,-25}", "NativeName", myCIintl.get_NativeName(), myCItrad.get_NativeName());
            Console.WriteLine("{0,-31}{1,-47}{2,-25}", "Parent", myCIintl.get_Parent(), myCItrad.get_Parent());
            Console.WriteLine("{0,-31}{1,-47}{2,-25}", "TextInfo", myCIintl.get_TextInfo(), myCItrad.get_TextInfo());
            Console.WriteLine("{0,-31}{1,-47}{2,-25}", "ThreeLetterISOLanguageName", myCIintl.get_ThreeLetterISOLanguageName(), myCItrad.get_ThreeLetterISOLanguageName());
            Console.WriteLine("{0,-31}{1,-47}{2,-25}", "ThreeLetterWindowsLanguageName", myCIintl.get_ThreeLetterWindowsLanguageName(), myCItrad.get_ThreeLetterWindowsLanguageName());
            Console.WriteLine("{0,-31}{1,-47}{2,-25}", "TwoLetterISOLanguageName", myCIintl.get_TwoLetterISOLanguageName(), myCItrad.get_TwoLetterISOLanguageName());
            Console.WriteLine();

            // Compare two strings using myCIintl.get_
            Console.WriteLine("Comparing \"llegar\" and \"lugar\"");
            Console.WriteLine("   With myCIintl.get_CompareInfo.Compare: {0}", myCIintl.get_CompareInfo().Compare("llegar", "lugar"));
            Console.WriteLine("   With myCItrad.get_CompareInfo.Compare: {0}", myCItrad.get_CompareInfo().Compare("llegar", "lugar"));
        } catch (Exception error) {
            error.printStackTrace();
        }
    }
}

/*
This code produces the following output.

PROPERTY                       INTERNATIONAL                                  TRADITIONAL
CompareInfo                    CompareInfo - es-ES                            CompareInfo - es-ES_tradnl
DisplayName                    Spanish (Spain)                                Spanish (Spain)
EnglishName                    Spanish (Spain, International Sort)            Spanish (Spain, Traditional Sort)
IsNeutralCulture               False                                          False
IsReadOnly                     False                                          False
LCID                           3082                                           1034
Name                           es-ES                                          es-ES
NativeName                     Español (España, alfabetización internacional) Español (España, alfabetización tradicional)
Parent                         es                                             es
TextInfo                       TextInfo - es-ES                               TextInfo - es-ES_tradnl
ThreeLetterISOLanguageName     spa                                            spa
ThreeLetterWindowsLanguageName ESN                                            ESP
TwoLetterISOLanguageName       es                                             es

Comparing "llegar" and "lugar"
   With myCIintl.get_CompareInfo.Compare: -1
   With myCItrad.get_CompareInfo.Compare: 1

*/
