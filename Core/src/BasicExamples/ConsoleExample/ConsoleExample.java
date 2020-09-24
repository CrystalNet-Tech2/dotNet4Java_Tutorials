package BasicExamples.ConsoleExample;

import com.dotNet4Java.IClrObject;
import com.dotNet4Java.TClrActivator;
import com.dotNet4Java.api.EClrError;

/**
 * Java Class equivalent of .Net Console Static Class 'System.Console'
 */
class Console {
    private static IClrObject staticType;

    private static IClrObject getStaticConsole() {
        if (staticType == null) {
            staticType = TClrActivator.createStaticInstance("System.Console");
        }
        return staticType;
    }

    public static void WriteLine(String value) throws EClrError {
        getStaticConsole().invokeVoidMethod("WriteLine", new String[]{"System.String"}, new Object[]{value});
    }

    public static void WriteLine() throws EClrError {
        getStaticConsole().invokeVoidMethod("WriteLine");
    }

    public static void ReadKey() throws EClrError {
        getStaticConsole().invokeVoidMethod("ReadKey");
    }
}

public class ConsoleExample {
    public static void main(String[] arg) {
        try {
            Console.WriteLine("          Hello! Welcome to dotNet4Java.          ");
            Console.WriteLine("==================================================");
            Console.WriteLine("The program displays the string Hello World!");
            Console.WriteLine();
            Console.WriteLine("Hello World!");
            Console.WriteLine("Press any key to exit.");
        } catch (EClrError eClrError) {
            eClrError.printStackTrace();
        }
    }
}