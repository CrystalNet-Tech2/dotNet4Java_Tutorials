package BasicExamples.StringBuilder;

import com.dotNet4Java.IClrObject;
import com.dotNet4Java.TClrActivator;

public class StringBuilderExample {

    public static void main(String[] arg) {
        System.out.println("Hello! Welcome to dotNet4Java");
        System.out.println("==================================================");
        System.out.println("This program prints out StringBuilder values.");
        System.out.println();
        try {
            IClrObject AStringBuilder = TClrActivator.createInstance("System.Text.StringBuilder");
            AStringBuilder.invokeVoidMethod("Append", new String[]{"System.String"}, new Object[]{"Hello "});
            AStringBuilder.invokeVoidMethod("Append", new String[]{"System.String"}, new Object[]{"World"});
            AStringBuilder.invokeVoidMethod("Append", new String[]{"System.String"}, new Object[]{"!"});

            System.out.println("AStringBuilder");
            System.out.println(String.format("    Count:    %s", AStringBuilder.getPropertyValue("Length", Integer.class)));
            System.out.println(String.format("    Capacity: %s", AStringBuilder.getPropertyValue("Capacity", Integer.class)));
            System.out.println(String.format("    Values:   %s", AStringBuilder.invokeMethod("ToString", String.class)));

        } catch (Exception eClrError) {
            eClrError.printStackTrace();
        }
    }
}