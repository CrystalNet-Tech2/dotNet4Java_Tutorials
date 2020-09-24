package Data.SystemData;

import com.dotNet4Java.IClrObject;
import com.dotNet4Java.TClrActivator;
import com.dotNet4Java.api.EClrError;

public class Console {
    private static IClrObject staticType;

    private static IClrObject getConsole() throws EClrError {
        if (staticType == null)
            staticType = TClrActivator.createStaticInstance(dotNetAssembly.INSTANCE.sc_sys_Console);
        return staticType;
    }

    public static void WriteLine() throws EClrError {
        getConsole().invokeVoidMethod("WriteLine");
    }
}