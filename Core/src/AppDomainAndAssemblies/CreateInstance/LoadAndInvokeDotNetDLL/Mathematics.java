package AppDomainAndAssemblies.CreateInstance.LoadAndInvokeDotNetDLL;

/*  Assuming you have .Net library project below built as Mathematics.Dll
    namespace Mathematics
    {
        public class Mathematics
        {
            public int Add(int a, int b)
            {
                return a + b;
            }

            public int Subtract(int a, int b)
            {
                return a - b;
            }

            public bool Equal(int a, int b)
            {
                return a == b;
            }
        }
    }
*/

//Corresponding Java class type of Mathematics type in the Mathematics.dll

import com.dotNet4Java.IClrAssembly;
import com.dotNet4Java.TClrAssembly;
import com.dotNet4Java.TClrObject;
import com.dotNet4Java.api.EClrError;
import com.dotNet4Java.enums.AssemblyLoadType;

/**
 * Class to list the type names in the Mathematics.dll and load the .net dll
 */
class MathematicsAssembly {
    public static final MathematicsAssembly INSTANCE = new MathematicsAssembly();
    private final static String assemblyId = "1";
    private final static String assemblyPath = "Mathematics.dll";

    static {
        TClrAssembly.register(assemblyId, assemblyPath, AssemblyLoadType.fileLocation);
    }

    public final String MathematicsTypeName = "Mathematics.Mathematics";

    public final String IntegerTypeName = "System.Int32";
    private IClrAssembly MathematicsAssembly;

    public IClrAssembly getAssembly() throws EClrError {
        if (MathematicsAssembly == null) {
            MathematicsAssembly = TClrAssembly.getRegisteredAssembly(assemblyId);
        }
        return MathematicsAssembly;
    }
}

/**
 * Java Class equivalent of the .Net type 'Mathematics' in Mathematics.dll
 */
class Mathematics extends TClrObject {

    public Mathematics() throws EClrError {
        super(MathematicsAssembly.INSTANCE.MathematicsTypeName, (Object[]) null);
    }

    public int Add(int a, int b) throws EClrError {
        return invokeIntMethod("Add",
                new String[]{MathematicsAssembly.INSTANCE.IntegerTypeName, MathematicsAssembly.INSTANCE.IntegerTypeName},
                new Object[]{a, b});
    }

    public int Subtract(int a, int b) throws EClrError {
        return invokeIntMethod("Subtract",
                new String[]{MathematicsAssembly.INSTANCE.IntegerTypeName, MathematicsAssembly.INSTANCE.IntegerTypeName},
                new Object[]{a, b});
    }

    public boolean Equal(int a, int b) throws EClrError {
        return invokeBooleanMethod("Equal",
                new String[]{MathematicsAssembly.INSTANCE.IntegerTypeName, MathematicsAssembly.INSTANCE.IntegerTypeName},
                new Object[]{a, b});
    }
}
