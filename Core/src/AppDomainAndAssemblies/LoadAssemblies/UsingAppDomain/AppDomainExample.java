package AppDomainAndAssemblies.LoadAssemblies.UsingAppDomain;

import com.dotNet4Java.IClrAssembly;
import com.dotNet4Java.TClrAppDomain;

public class AppDomainExample {

    static void DisplayAssemblyInfo(IClrAssembly AAssembly) throws Exception {
        if (AAssembly == null) {
            System.out.println("Assembly cannot be loaded");
        } else {
            System.out.println("Assembly has been loaded");
            System.out.println("Assembly FullName:     " + AAssembly.getFullName());
            System.out.println("Global Assembly Cache: " + AAssembly.getGlobalAssemblyCache());
            System.out.println("Location:              " + AAssembly.getLocation());
            System.out.println("Image Runtime Version: " + AAssembly.getImageRuntimeVersion());
            System.out.println("Number of Types:       " + AAssembly.getTypes().length);
            System.out.println();
            System.out.println();
        }
    }

    static void loadAssemblyByAssemblyString() throws Exception {
        System.out.println("Loading System.Data.dll using full Assembly String");
        IClrAssembly m_assembly = TClrAppDomain.getCurrentDomain().load("System.Data, Version=2.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089");
        DisplayAssemblyInfo(m_assembly);
    }

    public static void main(String[] arg) {
        System.out.println("                     Hello! Welcome to dotNet4Java                  ");
        System.out.println("====================================================================");
        System.out.println("The program demonstrate how to use AppDomain to load .Net Assemblies");
        System.out.println();
        try {
            loadAssemblyByAssemblyString();
        } catch (Exception eClrError) {
            eClrError.printStackTrace();
        }
    }
}
