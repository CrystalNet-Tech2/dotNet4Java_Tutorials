package Net.WebClient;

import system.ApplicationException;
import system.Console;
import system.io.Stream;
import system.io.StreamReader;
import system.net.WebClient;

public class Program {
    public static void main(String[] args) {
        try {

            if (args == null || args.length == 0) {
                throw new ApplicationException("Specify the URI of the resource to retrieve.");
            }

            WebClient client = new WebClient();

            // Add a user agent header in case the
            // requested URI contains a query.

            client.get_Headers().Add("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; .NET CLR 1.0.3705;)");

            Stream data = client.OpenRead(args[0]);
            StreamReader reader = new StreamReader(data);
            String s = reader.ReadToEnd();
            Console.WriteLine(s);
            data.Close();
            reader.Close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}