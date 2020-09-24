package Globalization.NumberFormatInfo;

import system.Console;
import system.globalization.CultureInfo;
import system.globalization.CultureTypes;
import system.globalization.NumberFormatInfo;
import system.text.StringBuilder;

import java.util.EnumSet;

public class Program {
   public static void main(String[] arg) {
        try {
            StringBuilder sb = new StringBuilder();

            // Loop through all the specific cultures known to the CLR.
            for (CultureInfo ci : CultureInfo.GetCultures(EnumSet.of(CultureTypes.SpecificCultures)).toArray()) {
                // Only show the currency symbols for cultures that speak English.
                if (ci.get_TwoLetterISOLanguageName() != "en") continue;

                // Display the culture name and currency symbol.
                NumberFormatInfo nfi = ci.get_NumberFormat();
                sb.AppendFormat("The currency symbol for '{0}' is '{1}'", ci.get_DisplayName(), nfi.get_CurrencySymbol());
                sb.AppendLine();
            }
            Console.WriteLine(sb.ToString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// This code produces the following output.
//
// The currency symbol for 'English (United States)' is '$'
// The currency symbol for 'English (United Kingdom)' is '£'
// The currency symbol for 'English (Australia)' is '$'
// The currency symbol for 'English (Canada)' is '$'
// The currency symbol for 'English (New Zealand)' is '$'
// The currency symbol for 'English (Ireland)' is '?'
// The currency symbol for 'English (South Africa)' is 'R'
// The currency symbol for 'English (Jamaica)' is 'J$'
// The currency symbol for 'English (Caribbean)' is '$'
// The currency symbol for 'English (Belize)' is 'BZ$'
// The currency symbol for 'English (Trinidad and Tobago)' is 'TT$'
// The currency symbol for 'English (Zimbabwe)' is 'Z$'
// The currency symbol for 'English (Republic of the Philippines)' is 'Php'