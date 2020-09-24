package Net.UdpClient;

import com.dotNet4Java.TRefObject;
import com.dotNet4Java.types.BigByte;
import system.Console;
import system.net.IPAddress;
import system.net.IPEndPoint;
import system.net.sockets.UdpClient;
import system.text.Encoding;

public class Program {
    public static void main(String[] args) {
        try {
            // This constructor arbitrarily assigns the local port number.
            UdpClient udpClient = new UdpClient(11000);
            udpClient.Connect("www.crystalnet-tech.com", 11000);

            // Sends a message to the host to which you have connected.
            BigByte[] sendBytes = Encoding.get_ASCII().GetBytes("Is anybody there?");

            udpClient.Send(sendBytes, sendBytes.length);

            // Sends a message to a different host using optional hostname and port parameters.
            UdpClient udpClientB = new UdpClient();
            udpClientB.Send(sendBytes, sendBytes.length, "AlternateHostMachineName", 11000);

            //IPEndPoint object will allow us to read datagrams sent from any source.
            IPEndPoint RemoteIpEndPoint = new IPEndPoint(IPAddress.getAny(), 0);
            TRefObject<IPEndPoint> Ref_RemoteIpEndPoint = new TRefObject<>(RemoteIpEndPoint);

            // Blocks until a message returns on this socket from a remote host.
            BigByte[] receiveBytes = udpClient.Receive(Ref_RemoteIpEndPoint);
            String returnData = Encoding.get_ASCII().GetString(receiveBytes);

            // Uses the IPEndPoint object to determine which of these two hosts responded.
            Console.WriteLine("This is the message you received " + returnData);
            Console.WriteLine("This message was sent from " +
                    RemoteIpEndPoint.get_Address().ToString() +
                    " on their port number " + RemoteIpEndPoint.get_Port());

            udpClient.Close();
            udpClientB.Close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
