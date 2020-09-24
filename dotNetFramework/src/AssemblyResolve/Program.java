package AssemblyResolve;


import system.AppDomain;
import system.Console;
import system.ResolveEventArgs;
import system.ResolveEventHandler;
import system.reflection.Assembly;

public class Program {

    public static void main(String[] args) {
        try {

            AppDomain.get_CurrentDomain().add_AssemblyResolve(new ResolveEventHandler() {
                @Override
                public Assembly invoke(Object o, ResolveEventArgs e) throws Exception {
                    Console.WriteLine("Resolving {0}", e.get_Name());
                    return Assembly.Load(e.get_Name());
                    //Alternatively, you can use
                    // return TClrAssembly.load(e.get_Name());
                }
            });

            AppDomain.get_CurrentDomain().CreateInstanceAndUnwrap("MyAssembly, version=1.2.3.4, culture=neutral, publicKeyToken=null", "MyType");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
/*
    This example produces output similar to the following:

    Resolving MyAssembly, Version=1.2.3.4, Culture=neutral, PublicKeyToken=null
    Resolving MyAssembly, Version=1.2.3.4, Culture=neutral, PublicKeyToken=null
    ...
    Resolving MyAssembly, Version=1.2.3.4, Culture=neutral, PublicKeyToken=null
    Resolving MyAssembly, Version=1.2.3.4, Culture=neutral, PublicKeyToken=null

    Process is terminated due to StackOverflowException.
*/
