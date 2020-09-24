package Globalization.Calendar;

import com.dotNet4Java.api.EClrError;
import system.Console;
import system.DateTime;
import system.globalization.Calendar;
import system.globalization.CultureInfo;
import system.globalization.GregorianCalendar;

public class Program {

    public static void main(String[] arg) {

        // Sets a DateTime to April 3, 2002 of the Gregorian calendar.
        DateTime myDT = null;
        try {
            myDT = new DateTime(2002, 4, 3, new GregorianCalendar());

            // Uses the default calendar of the InvariantCulture.
            Calendar myCal = CultureInfo.get_InvariantCulture().get_Calendar();

            java.util.Calendar javaCalendar = myDT.AsType(java.util.Calendar.class);

            // Displays the values of the DateTime.
            Console.WriteLine("April 3, 2002 of the Gregorian calendar:");
            DisplayValues(myCal, javaCalendar);

            // Adds 5 to every component of the DateTime.
            javaCalendar = myCal.AddYears(javaCalendar, 5);
            javaCalendar = myCal.AddMonths(javaCalendar, 5);
            javaCalendar = myCal.AddWeeks(javaCalendar, 5);
            javaCalendar = myCal.AddDays(javaCalendar, 5);
            javaCalendar = myCal.AddHours(javaCalendar, 5);
            javaCalendar = myCal.AddMinutes(javaCalendar, 5);
            javaCalendar = myCal.AddSeconds(javaCalendar, 5);
            javaCalendar = myCal.AddMilliseconds(javaCalendar, 5);

            // Displays the values of the DateTime.
            Console.WriteLine("After adding 5 to each component of the DateTime:");
            DisplayValues(myCal, javaCalendar);

        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public static void DisplayValues(Calendar myCal, java.util.Calendar myDT) throws EClrError {
        Console.WriteLine("   Era:          {0}", myCal.GetEra(myDT));
        Console.WriteLine("   Year:         {0}", myCal.GetYear(myDT));
        Console.WriteLine("   Month:        {0}", myCal.GetMonth(myDT));
        Console.WriteLine("   DayOfYear:    {0}", myCal.GetDayOfYear(myDT));
        Console.WriteLine("   DayOfMonth:   {0}", myCal.GetDayOfMonth(myDT));
        Console.WriteLine("   DayOfWeek:    {0}", myCal.GetDayOfWeek(myDT));
        Console.WriteLine("   Hour:         {0}", myCal.GetHour(myDT));
        Console.WriteLine("   Minute:       {0}", myCal.GetMinute(myDT));
        Console.WriteLine("   Second:       {0}", myCal.GetSecond(myDT));
        Console.WriteLine("   Milliseconds: {0}", myCal.GetMilliseconds(myDT));
        Console.WriteLine();
    }
}


/*
This code produces the following output.

April 3, 2002 of the Gregorian calendar:
   Era:          1
   Year:         2002
   Month:        4
   DayOfYear:    93
   DayOfMonth:   3
   DayOfWeek:    Wednesday
   Hour:         0
   Minute:       0
   Second:       0
   Milliseconds: 0

After adding 5 to each component of the DateTime:
   Era:          1
   Year:         2007
   Month:        10
   DayOfYear:    286
   DayOfMonth:   13
   DayOfWeek:    Saturday
   Hour:         5
   Minute:       5
   Second:       5
   Milliseconds: 5

*/
