package Net.SmtpClient;

import system.Console;
import system.Environment;
import system.componentmodel.AsyncCompletedEventArgs;
import system.net.mail.MailAddress;
import system.net.mail.MailMessage;
import system.net.mail.SendCompletedEventHandler;
import system.net.mail.SmtpClient;
import system.text.Encoding;

public class Program {
    static boolean mailSent = false;

    public static void Main(String[] args) {
        try {
            // Command-line argument must be the SMTP host.
            SmtpClient client = new SmtpClient(args[0]);
            // Specify the email sender.
            // Create a mailing address that includes a UTF8 character
            // in the display name.
            MailAddress from = new MailAddress("jane@contoso.com",
                    "Jane " + (char) 0xD8 + " Clayton",
                    Encoding.get_UTF8());
            // Set destinations for the email message.
            MailAddress to = new MailAddress("ben@contoso.com");
            // Specify the message content.
            MailMessage message = new MailMessage(from, to);
            message.set_Body("This is a test email message sent by an application. ");
            // Include some non-ASCII characters in body and subject.
            String someArrows = new String(new char[]{'\u2190', '\u2191', '\u2192', '\u2193'});
            message.set_Body(message.get_Body() + Environment.get_NewLine() + someArrows);
            message.set_BodyEncoding(Encoding.get_UTF8());
            message.set_Subject("test message 1" + someArrows);
            message.set_SubjectEncoding(Encoding.get_UTF8());
            // Set the method that is called back when the send operation ends.

            SendCompletedEventHandler SendCompletedCallback = new SendCompletedEventHandler() {
                @Override
                public void invoke(Object o, AsyncCompletedEventArgs e) throws Exception {
                    // Get the unique identifier for this asynchronous operation.
                    String token = (String) e.get_UserState();

                    if (e.get_Cancelled()) {
                        Console.WriteLine("[{0}] Send canceled.", token);
                    }
                    if (e.get_Error() != null) {
                        Console.WriteLine("[{0}] {1}", token, e.get_Error().ToString());
                    } else {
                        Console.WriteLine("Message sent.");
                    }
                    mailSent = true;
                }
            };

            client.add_SendCompleted(SendCompletedCallback);
            // The userState can be any object that allows your callback
            // method to identify this send operation.
            // For this example, the userToken is a String constant.
            String userState = "test message1";
            client.SendAsync(message, userState);
            Console.WriteLine("Sending message... press c to cancel mail. Press any other key to exit.");
            String answer = Console.ReadLine();
            // If the user canceled the send, and mail hasn't been sent yet,
            // then cancel the pending operation.
            if (answer.startsWith("c") && mailSent == false) {
                client.SendAsyncCancel();
            }
            // Clean up.
            message.Dispose();
            Console.WriteLine("Goodbye.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
