package Text.StringBuilder;

import system.text.StringBuilder;

public class Program {

    public static void main(String[] arg) {
        System.out.println("Hello! Welcome to dotNet4Java");
        System.out.println("==================================================");
        System.out.println("This program prints out Text.StringBuilder values.");
        System.out.println();
        try {
            StringBuilder AStringBuilder = new StringBuilder();
            AStringBuilder.Append("Hello");
            AStringBuilder.Append("World");
            AStringBuilder.Append("!");

            System.out.println("AStringBuilder");
            System.out.println(String.format("    Count:    %s", AStringBuilder.get_Length()));
            System.out.println(String.format("    Capacity: %s", AStringBuilder.get_Capacity()));
            System.out.println(String.format("    Values:   %s", AStringBuilder.ToString()));

        } catch (Exception eClrError) {
            eClrError.printStackTrace();
        }
    }
}