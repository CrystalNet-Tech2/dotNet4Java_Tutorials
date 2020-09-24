package Configuration;

import com.dotNet4Java.IClrObject;
import com.dotNet4Java.TClrActivator;
import com.dotNet4Java.TClrArray;
import com.dotNet4Java.TClrObject;
import com.dotNet4Java.api.EClrError;
import com.dotNet4Java.api.Enums.FlagEnum;
import com.dotNet4Java.api.core.DotNetNativeTypes;

import java.util.HashMap;
import java.util.Map;

//The example below assumes your project has an ConfigurationExample.exe.config file as shown below.

//<?xml version="1.0" encoding="utf-8" ?>
//<configuration>
//    <startup>
//        <supportedRuntime version="v4.0" sku=".NETFramework,Version=v4.5" />
//    </startup>
//  <appSettings>
//    <add key="Setting1" value="May 5, 2014"/>
//    <add key="Setting2" value="May 6, 2014"/>
//  </appSettings>
//</configuration>


enum ConfigurationUserLevel implements FlagEnum<ConfigurationUserLevel> {
    None(0x0),
    PerUserRoaming(0xA),
    PerUserRoamingAndLocal(0x14);

    private static final Map<Long, ConfigurationUserLevel> mappedEnums = new HashMap<>();

    static {
        for (ConfigurationUserLevel value : ConfigurationUserLevel.values()) {
            mappedEnums.put(value._flag, value);
        }
    }

    private final long _flag;

    ConfigurationUserLevel(int value) {
        _flag = value;
    }

    @Override
    public long getFlag() {
        return _flag;
    }

    @Override
    public Map<Long, ConfigurationUserLevel> getMappedEnums() {
        return mappedEnums;
    }
}

enum ConfigurationSaveMode implements FlagEnum<ConfigurationSaveMode> {
    Modified(0x0),
    Minimal(0x1),
    Full(0x2);

    private static final Map<Long, ConfigurationSaveMode> mappedEnums = new HashMap<>();

    static {
        for (ConfigurationSaveMode value : ConfigurationSaveMode.values()) {
            mappedEnums.put(value._flag, value);
        }
    }

    private final long _flag;

    ConfigurationSaveMode(int value) {
        _flag = value;
    }

    @Override
    public long getFlag() {
        return _flag;
    }

    @Override
    public Map<Long, ConfigurationSaveMode> getMappedEnums() {
        return mappedEnums;
    }
}

class ConfigurationManager {
    private static IClrObject staticType;
    private static NameValueCollection appSettings;
    private static Configuration configuration;

    private static IClrObject getStaticConfigurationManager() {
        if (staticType == null) {
            staticType = TClrActivator.createStaticInstance("System.Configuration.ConfigurationManager");
        }
        return staticType;
    }

    public static NameValueCollection AppSettings() throws Exception {
        if (appSettings == null) {
            DotNetNativeTypes.ClrObject clrObject = getStaticConfigurationManager().getPropertyValue("AppSettings", DotNetNativeTypes.ClrObject.class);
            appSettings = clrObject == null ? null : new NameValueCollection(clrObject);
        }
        return appSettings;
    }

    public static Configuration OpenExeConfiguration(ConfigurationUserLevel userLevel) throws Exception {
        if (configuration == null) {
            DotNetNativeTypes.ClrObject clrObject = getStaticConfigurationManager().invokeMethod("OpenExeConfiguration",
                    new String[]{"System.Configuration.ConfigurationUserLevel"}, new Object[]{userLevel}, DotNetNativeTypes.ClrObject.class);
            configuration = clrObject == null ? null : new Configuration(clrObject);
        }
        return configuration;
    }

    public static void RefreshSection(String sectionName) throws EClrError {
        getStaticConfigurationManager().invokeVoidMethod("RefreshSection", new String[]{"System.String"}, new Object[]{sectionName});
    }
}

class NameValueCollection extends TClrObject {

    public NameValueCollection(DotNetNativeTypes.ClrObject clrObject) {
        super(clrObject);
    }

    public NameValueCollection() throws EClrError {
        super("System.Collections.Specialized.NameValueCollection",
                new Object[]{});
    }

    public int getCount() throws EClrError {
        return getPropertyValueAsInt("Count");
    }

    public String[] AllKeys() throws EClrError {
        return (String[]) getPropertyValue("AllKeys");
    }

    public String get(String key) throws Exception {
        return (String) getPropertyIndexValue("Item", new String[]{"System.String"}, new Object[]{key});
    }

    public void set(String key, String value) throws Exception {
        setPropertyIndexValue("Item", new String[]{"System.String"}, new Object[]{key}, value);
    }

    public int Add(String key, String value) throws Exception {
        return invokeIntMethod("Add", TClrArray.of("System.String", "System.String"), TClrArray.of(key, value));
    }
}

class Configuration extends TClrObject {

    private AppSettingsSection appSettings;

    public Configuration(DotNetNativeTypes.ClrObject clrObject) {
        super(clrObject);
    }

    public AppSettingsSection AppSettings() throws EClrError {
        if (appSettings == null) {
            DotNetNativeTypes.ClrObject clrObject = getPropertyValueAsClrObject("AppSettings");
            appSettings = clrObject == null ? null : new AppSettingsSection(clrObject);
        }
        return appSettings;
    }

    public void Save(ConfigurationSaveMode saveMode) throws EClrError {
        invokeVoidMethod("Save", new String[]{"System.Configuration.ConfigurationSaveMode"}, new Object[]{saveMode});
    }
}

class AppSettingsSection extends TClrObject {

    private SectionInformation sectionInformation;
    private KeyValueConfigurationCollection keyValueConfigurationCollection;

    public AppSettingsSection(DotNetNativeTypes.ClrObject clrObject) {
        super(clrObject);
    }

    public SectionInformation SectionInformation() throws EClrError {
        if (sectionInformation == null) {
            DotNetNativeTypes.ClrObject clrObject = getPropertyValueAsClrObject("SectionInformation");
            sectionInformation = clrObject == null ? null : new SectionInformation(clrObject);
        }
        return sectionInformation;
    }

    public KeyValueConfigurationCollection Settings() throws EClrError {
        if (keyValueConfigurationCollection == null) {
            DotNetNativeTypes.ClrObject clrObject = getPropertyValueAsClrObject("Settings");
            keyValueConfigurationCollection = clrObject == null ? null : new KeyValueConfigurationCollection(clrObject);
        }
        return keyValueConfigurationCollection;
    }
}

class KeyValueConfigurationCollection extends TClrObject {

    public KeyValueConfigurationCollection(DotNetNativeTypes.ClrObject clrObject) {
        super(clrObject);
    }

    public String get(String key) throws Exception {
        return (String) getPropertyIndexValue("Item", new String[]{"System.String"}, new Object[]{key});
    }

    public void set(String key, String value) throws Exception {
        setPropertyIndexValue("Item", new String[]{"System.String"}, new Object[]{key}, value);
    }

    public int Add(String key, String value) throws Exception {
        return invokeIntMethod("Add", TClrArray.of("System.String", "System.String"), TClrArray.of(key, value));
    }
}

class SectionInformation extends TClrObject {

    public SectionInformation(DotNetNativeTypes.ClrObject clrObject) {
        super(clrObject);
    }

    public String Name() throws EClrError {
        return getPropertyValueAsString("Name");
    }
}

public class ConfigurationExample {

    static void ReadAllSettings() {
        try {
            NameValueCollection appSettings = ConfigurationManager.AppSettings();
            if (appSettings.getCount() == 0)
                System.out.println("AppSettings is empty.");
            else {
                for (int i = 0; i < appSettings.AllKeys().length; i++) {
                    String AKey = appSettings.AllKeys()[i];
                    System.out.println(String.format("Key: %s Value: {1}", AKey, appSettings.get(AKey)));
                }
            }
        } catch (Exception ex) {
            System.out.println("Error reading app settings");
        }
    }

    static void ReadSetting(String Key) {
        try {
            NameValueCollection appSettings = ConfigurationManager.AppSettings();
            String result = appSettings.get(Key);
            if (result == null)
                result = "Not Found";

            System.out.println(result);
        } catch (Exception ex) {
            System.out.println("Error reading app settings");
        }
    }

    static void AddUpdateAppSettings(String Key, String value) {
        try {
            Configuration configFile = ConfigurationManager.OpenExeConfiguration(ConfigurationUserLevel.PerUserRoaming);
            KeyValueConfigurationCollection settings = configFile.AppSettings().Settings();
            if (settings.get(Key) == null)
                settings.Add(Key, value);
            else
                settings.set(Key, value);

            configFile.Save(ConfigurationSaveMode.Modified);
            ConfigurationManager.RefreshSection(configFile.AppSettings().SectionInformation().Name());
        } catch (Exception ex) {
            System.out.println("Error writing app settings");
        }
    }

    public static void main(String[] arg) {

        System.out.println("          Hello! Welcome to dotNet4Java.          ");
        System.out.println("==================================================");
        System.out.println("The program demonstrates how to use .Net Configuration to load and save appConfig.");
        System.out.println();

        ReadAllSettings();
        ReadSetting("Setting1");
        ReadSetting("NotValid");
        AddUpdateAppSettings("NewSetting", "May 7, 2014");
        AddUpdateAppSettings("Setting1", "May 8, 2014");
        ReadAllSettings();
    }
}