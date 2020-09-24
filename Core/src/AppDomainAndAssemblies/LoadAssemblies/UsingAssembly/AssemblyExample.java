package AppDomainAndAssemblies.LoadAssemblies.UsingAssembly;

import com.dotNet4Java.IClrAssembly;
import com.dotNet4Java.TClrAssembly;

public class AssemblyExample {

    static void DisplayAssemblyInfo(IClrAssembly xAssembly) throws Exception {
        if (xAssembly == null)
            System.out.println("Assembly cannot be loaded");
        else {
            System.out.println("Assembly has been loaded");
            System.out.println("Global Assembly Cache: " + xAssembly.getGlobalAssemblyCache());
            System.out.println("Location:              " + xAssembly.getLocation());
            System.out.println("Image Runtime Version: " + xAssembly.getImageRuntimeVersion());
            System.out.println("Number of Types:       " + xAssembly.getTypes().length);
            System.out.println();
            System.out.println();
        }
    }

    static void LoadAssemblyFromFile() throws Exception {
        System.out.println("Loading System.Data.dll from file");
        IClrAssembly oAssembly = TClrAssembly.loadFrom("C:\\Windows\\Microsoft.NET\\Framework\\v2.0.50727\\System.Data.dll");
        DisplayAssemblyInfo(oAssembly);
    }

    static void LoadAssemblyByAssemblyNameString() throws Exception {
        System.out.println("Loading System.Data.dll using full Assembly Name");
        IClrAssembly oAssembly = TClrAssembly.load("System.Data, Version=2.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089");
        DisplayAssemblyInfo(oAssembly);
    }

    static void LoadAssemblyWithPartialName() throws Exception {
        System.out.println("Loading System.Data.dll using Partial Assembly Name");
        IClrAssembly oAssembly = TClrAssembly.loadWithPartialName("System.Data");
        DisplayAssemblyInfo(oAssembly);
    }

    static void LoadAssemblyByFilePath() throws Exception {
        System.out.println("Loading System.Data.dll using File Path");
        IClrAssembly oAssembly = TClrAssembly.loadFile("C:\\Windows\\Microsoft.NET\\Framework\\v2.0.50727\\System.Data.dll");
        DisplayAssemblyInfo(oAssembly);
    }

    public static void main(String[] arg) {
        System.out.println("                    Hello! Welcome to dotNet4Java                         ");
        System.out.println("==========================================================================");
        System.out.println("The program demonstrate how to use Static Assembly to Load .Net Assemblies");
        System.out.println();
        try {
            LoadAssemblyWithPartialName();
            LoadAssemblyByAssemblyNameString();
            LoadAssemblyByFilePath();
            LoadAssemblyFromFile();
        } catch (Exception eClrError) {
            eClrError.printStackTrace();
        }
    }
}