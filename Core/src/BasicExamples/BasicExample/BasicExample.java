package BasicExamples.BasicExample;

import com.dotNet4Java.IClrObject;
import com.dotNet4Java.TClrActivator;
import com.dotNet4Java.TClrObject;
import com.dotNet4Java.TClrStaticObject;
import com.dotNet4Java.api.EClrError;

public class BasicExample {

    static void MathClass() throws EClrError {

        System.out.println("Using Math Class Methods/Properties..");

        TClrStaticObject m_math = TClrActivator.createStaticInstance("System.Math");

        System.out.println("Exp(50):              " + m_math.invokeDoubleMethod("Exp", new String[]{"System.Double"}, new Object[]{50}));
        System.out.println("PI:                   " + m_math.getFieldValueAsDouble("PI"));
        System.out.println("Cos(50):              " + m_math.invokeDoubleMethod("Cos", new String[]{"System.Double"}, new Object[]{50}));
        System.out.println("Log(50):              " + m_math.invokeDoubleMethod("Log", new String[]{"System.Double"}, new Object[]{50}));
        System.out.println("Pow(50, 3):           " + m_math.invokeDoubleMethod("Pow", new String[]{"System.Double", "System.Double"}, new Object[]{50, 3}));
        System.out.println("Round(234.094833, 2): " + m_math.invokeDoubleMethod("Round", new String[]{"System.Double", "System.Int32"}, new Object[]{234.094833, 2}));
        System.out.println("Truncate(234.094833): " + m_math.invokeDoubleMethod("Truncate", new String[]{"System.Double"}, new Object[]{234.094833}));
        System.out.println("Sqrt(16):             " + m_math.invokeDoubleMethod("Sqrt", new String[]{"System.Double"}, new Object[]{16}));
        System.out.println();
        System.out.println();
    }

    static void RandomClass() throws Exception {

        System.out.println("Using Random Class Methods/Properties..");

        IClrObject m_random = TClrActivator.createInstance("System.Random");

        System.out.println("Next Value: " + m_random.invokeMethod("Next", Integer.class));
        System.out.println("NextDouble: " + m_random.invokeMethod("NextDouble", Double.class));
        System.out.println("Next(2000): " + m_random.invokeMethod("Next", new String[]{"System.Int32"}, new Object[]{2000}, Double.class));
        System.out.println();
        System.out.println();
    }

    static void GuidClass() throws Exception {

        System.out.println("Using Guid Class Methods/Properties..");

        TClrStaticObject m_guid = TClrActivator.createStaticInstance("System.Guid");

        IClrObject emptyGuid = new TClrObject(m_guid.getFieldValueAsClrObject("Empty"));
        System.out.println("Empty Guid: " + emptyGuid.invokeMethod("ToString", String.class));

        IClrObject staticGuid = TClrActivator.createStaticInstance("System.Guid");
        IClrObject newGuid = new TClrObject(staticGuid.invokeMethod("NewGuid"));

        System.out.println("NewGuid:    " + newGuid.invokeMethod("ToString", String.class));
        System.out.println();
        System.out.println();
    }

    static void EnvironmentClass() throws Exception {

        System.out.println("Using Environment Class Methods/Properties..");

        IClrObject m_environment = TClrActivator.createStaticInstance("System.Environment");

        System.out.println("CommandLine:            " + m_environment.getPropertyValue("CommandLine", String.class));
        System.out.println("CurrentDirectory:       " + m_environment.getPropertyValue("CurrentDirectory", String.class));
        System.out.println("ExitCode:               " + m_environment.getPropertyValue("ExitCode", Integer.class));
        System.out.println("Is64BitOperatingSystem: " + m_environment.getPropertyValue("Is64BitOperatingSystem", Boolean.class));
        System.out.println("Is64BitProcess:         " + m_environment.getPropertyValue("Is64BitProcess", Boolean.class));
        System.out.println("MachineName:            " + m_environment.getPropertyValue("MachineName"));
        System.out.println("ProcessorCount:         " + m_environment.getPropertyValue("ProcessorCount", Integer.class));
        System.out.println("StackTrace:             " + m_environment.getPropertyValue("StackTrace", String.class));
        System.out.println("SystemDirectory:        " + m_environment.getPropertyValue("SystemDirectory", String.class));
        System.out.println("SystemPageSize:         " + m_environment.getPropertyValue("SystemPageSize", Integer.class));
        System.out.println("UserName:               " + m_environment.getPropertyValue("UserName", String.class));
        System.out.println("WorkingSet:             " + m_environment.getPropertyValue("WorkingSet", Long.class));
        System.out.println();
        System.out.println();

        OSVersion(m_environment.getPropertyValue("OSVersion"));
    }

    public static void OSVersion(Object osVersion) throws Exception {
        System.out.println("Current OS Information:\n");

        IClrObject os = new TClrObject(osVersion);

        System.out.println("Platform: " + os.getPropertyValue("Platform", Integer.class));
        System.out.println("Version String: " + os.getPropertyValue("VersionString", String.class));
        System.out.println("Version Information:");

        IClrObject version = new TClrObject(os.getPropertyValue("Version"));

        System.out.println("   Major: " + version.getPropertyValue("Major", Integer.class));
        System.out.println("   Minor: " + version.getPropertyValue("Minor", Integer.class));
        System.out.printf("Service Pack: '%s'\n", os.getPropertyValue("ServicePack", String.class));
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