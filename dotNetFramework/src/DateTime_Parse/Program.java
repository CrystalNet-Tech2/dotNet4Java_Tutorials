package DateTime_Parse;

import system.Console;
import system.DateTime;
import system.FormatException;
import system.IFormatProvider;
import system.globalization.CultureInfo;

import java.util.Calendar;

public class Program {

    public static void main(String[] args) {
        try {
            // Assume the current culture is en-US.
            // The date is February 16, 2008, 12 hours, 15 minutes and 12 seconds.

            // Use standard en-US date and time value.
            DateTime dateValue;
            String dateString = "2/16/2008 12:15:12 PM";
            try {
                dateValue = DateTime.Parse(dateString);
                Console.WriteLine("'{0}' converted to {1}.", dateString, dateValue);
            } catch (FormatException fex) {
                Console.WriteLine("Unable to convert '{0}'.", dateString);
            }

            // Reverse month and day to conform to the fr-FR culture.
            // The date is February 16, 2008, 12 hours, 15 minutes and 12 seconds.
            dateString = "16/02/2008 12:15:12";
            try {
                dateValue = DateTime.Parse(dateString);
                Console.WriteLine("'{0}' converted to {1}.", dateString, dateValue);
            } catch (FormatException fex) {
                Console.WriteLine("Unable to convert '{0}'.", dateString);
            }

            // Call another overload of Parse to successfully convert String
            // formatted according to conventions of fr-FR culture.
            try {
                dateValue = DateTime.Parse(dateString, new CultureInfo("fr-FR", false).AsType(IFormatProvider.class));
                Console.WriteLine("'{0}' converted to {1}.", dateString, dateValue);
            } catch (FormatException fex) {
                Console.WriteLine("Unable to convert '{0}'.", dateString);
            }

            // Parse String with date but no time component.
            dateString = "2/16/2008";
            try {
                dateValue = DateTime.Parse(dateString);
                Console.WriteLine("'{0}' converted to {1}.", dateString, dateValue);
            } catch (FormatException fex) {
                Console.WriteLine("Unable to convert '{0}'.", dateString);
            }

        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
        }
    }
}