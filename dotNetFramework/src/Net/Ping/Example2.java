package Net.Ping;

import com.dotNet4Java.types.BigByte;
import system.Console;
import system.net.networkinformation.IPStatus;
import system.net.networkinformation.Ping;
import system.net.networkinformation.PingOptions;
import system.net.networkinformation.PingReply;
import system.reflection.PropertyInfo;
import system.text.Encoding;

public class Example2 {
    // args[0] can be an IPaddress or host name.
    public static void main(String[] args) {
        try {
            args = new String[1];
            args[0] = "192.168.56.1";
            Ping pingSender = new Ping();

            PingOptions options = new PingOptions();

            // Use the default Ttl value which is 128,
            // but change the fragmentation behavior.
            options.set_DontFragment(true);


            // Create a buffer of 32 bytes of data to be transmitted.
            String data = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
            BigByte[] buffer = Encoding.get_ASCII().GetBytes(data);
            int timeout = 120;
            PingReply reply = pingSender.Send(args[0], timeout, buffer, options);
            if (reply.get_Status() == IPStatus.Success) {
                Console.WriteLine("Address: {0}", reply.get_Address().ToString());
                Console.WriteLine("RoundTrip time: {0}", reply.get_RoundtripTime());
                Console.WriteLine("Time to live: {0}", reply.get_Options().get_Ttl());
                Console.WriteLine("Don't fragment: {0}", reply.get_Options().get_DontFragment());
                Console.WriteLine("Buffer size: {0}", reply.get_Buffer().length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
