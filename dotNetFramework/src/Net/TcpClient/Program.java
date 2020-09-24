package Net.TcpClient;

import com.dotNet4Java.types.BigByte;
import system.ArgumentNullException;
import system.Console;
import system.net.sockets.NetworkStream;
import system.net.sockets.SocketException;
import system.net.sockets.TcpClient;

public class Program {
    public static void main(String[] args) {
        try {
            String ServerName = "localhost:";
            String Message = "Hello World!";

            Connect(ServerName, Message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void Connect(String server, String message) throws Exception {
        try {
            // Create a TcpClient.
            // Note, for this client to work you need to have a TcpServer
            // connected to the same address as specified by the server, port
            // combination.
            int port = 13000;
            TcpClient client = new TcpClient(server, port);

            // Translate the passed message into ASCII and store it as a Byte array.
            BigByte[] data = system.text.Encoding.get_ASCII().GetBytes(message);

            // Get a client stream for reading and writing.
            //  Stream stream = client.GetStream();

            NetworkStream stream = client.GetStream();

            // Send the message to the connected TcpServer.
            stream.Write(data, 0, data.length);

            Console.WriteLine("Sent: {0}", message);

            // Receive the TcpServer.response.

            // Buffer to store the response bytes.
            data = new BigByte[256];

            // String to store the response ASCII representation.
            String responseData = system.String.getEmpty();

            // Read the first batch of the TcpServer response bytes.
            int bytes = stream.Read(data, 0, data.length);
            responseData = system.text.Encoding.get_ASCII().GetString(data, 0, bytes);
            Console.WriteLine("Received: {0}", responseData);

            // Close everything.
            stream.Close();
            client.Close();
        } catch (ArgumentNullException e) {
            Console.WriteLine("ArgumentNullException: {0}", e);
        } catch (SocketException e) {
            Console.WriteLine("SocketException: {0}", e);
        }

        Console.WriteLine("\n Press Enter to continue...");
        Console.Read();
    }
}
