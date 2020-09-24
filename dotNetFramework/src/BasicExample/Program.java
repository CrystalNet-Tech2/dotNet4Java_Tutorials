package BasicExample;

import com.dotNet4Java.api.EClrError;
import system.Math;
import system.*;

import java.lang.Exception;
import java.lang.String;

public class Program {

    static void MathClass() throws EClrError {

        System.out.println("Using Math Class Methods/Properties..");

        System.out.println("Exp(50):              " + Math.Exp(50));
        System.out.println("PI:                   " + Math.getPI());
        System.out.println("Cos(50):              " + Math.Cos(50));
        System.out.println("Log(50):              " + Math.Log(50));
        System.out.println("Pow(50, 3):           " + Math.Pow(50, 3));
        System.out.println("Round(234.094833, 2): " + Math.Round(234.094833, 2));
        System.out.println("Truncate(234.094833): " + Math.Truncate(234.094833));
        System.out.println("Sqrt(16):             " + Math.Sqrt(16));
        System.out.println();
        System.out.println();
    }

    static void RandomClass() throws Exception {

        System.out.println("Using Random Class Methods/Properties..");

        Random m_random = new Random();

        System.out.println("Next Value: " + m_random.Next());
        System.out.println("NextDouble: " + m_random.NextDouble());
        System.out.println("Next(2000): " + m_random.Next(2000));
        System.out.println();
        System.out.println();
    }

    static void GuidClass() throws Exception {

        System.out.println("Using Guid Class Methods/Properties..");

        System.out.println("Empty Guid: " + Guid.getEmpty().ToString());
        System.out.println("NewGuid:    " + Guid.NewGuid().ToString());
        System.out.println();
        System.out.println();
    }

    static void EnvironmentClass() throws Exception {

        System.out.println("Using Environment Class Methods/Properties..");

        System.out.println("CommandLine:            " + Environment.get_CommandLine());
        System.out.println("CurrentDirectory:       " + Environment.get_CurrentDirectory());
        System.out.println("ExitCode:               " + Environment.get_ExitCode());
        System.out.println("Is64BitOperatingSystem: " + Environment.get_Is64BitOperatingSystem());
        System.out.println("Is64BitProcess:         " + Environment.get_Is64BitProcess());
        System.out.println("MachineName:            " + Environment.get_MachineName());
        System.out.println("ProcessorCount:         " + Environment.get_ProcessorCount());
        System.out.println("StackTrace:             " + Environment.get_StackTrace());
        System.out.println("SystemDirectory:        " + Environment.get_SystemDirectory());
        System.out.println("SystemPageSize:         " + Environment.get_SystemPageSize());
        System.out.println("UserName:               " + Environment.get_UserName());
        System.out.println("WorkingSet:             " + Environment.get_WorkingSet());
        System.out.println();
        System.out.println();

        OSVersion(Environment.get_OSVersion());
    }

    public static void OSVersion(OperatingSystem os) throws Exception {
        System.out.println("Current OS Information:\n");

        System.out.println("Platform: " + os.get_Platform());
        System.out.println("Version String: " + os.get_VersionString());
        System.out.println("Version Information:");

        System.out.println("   Major: " + os.get_Version().get_Major());
        System.out.println("   Minor: " + os.get_Version().get_Minor());
        System.out.printf("Service Pack: '%s'\n", os.get_ServicePack());
    }

    public static void main(String[] arg) {
        System.out.println("Hello! Welcome to dotNet4Java");
        System.out.println("==================================================");
        System.out.println("This program demonstrate how to use basic .Net framework classes in Java");
        System.out.println();
        try {

            MathClass();
            RandomClass();
            GuidClass();
            EnvironmentClass();
        } catch (Exception eClrError) {
            eClrError.printStackTrace();
        }
    }
}