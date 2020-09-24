package AppDomainAndAssemblies.CreateInstance.LoadAndInvokeDotNetDLL;

import com.dotNet4Java.api.EClrError;

public class Main {
    static Mathematics mathematics;

    static void createMathematicsObjectInstance() throws EClrError {
        mathematics = new Mathematics();
    }

    static void accessMathematicsObjectMethods() throws EClrError {
        System.out.printf("Add(30, 50):      %d%n", mathematics.Add(30, 50));
        System.out.println("Subtract(30, 50): " + mathematics.Subtract(30, 50));
        System.out.println("Equal(30, 50):    " + mathematics.Equal(30, 50));
        System.out.println("Equal(50, 50):    " + mathematics.Equal(50, 50));
    }

    public static void main(String[] arg) {
        System.out.println("            Hello! Welcome to dotNet4Java.        ");
        System.out.println("==================================================");
        System.out.println("This program demonstrates how to use dotNet4Java to");
        System.out.println("load and invoke .Net Library and Types            ");
        System.out.println();

        try {
            createMathematicsObjectInstance();
            accessMathematicsObjectMethods();
        } catch (EClrError eClrError) {
            eClrError.printStackTrace();
        }
    }
}
