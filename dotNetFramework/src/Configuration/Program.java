package Configuration;

import system.collections.specialized.NameValueCollection;
import system.configuration.*;

//The example below assumes your project has an List.exe.config file as shown below.

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

public class Program {

    static void ReadAllSettings() {
        try {
            NameValueCollection appSettings = ConfigurationManager.get_AppSettings();
            if (appSettings.get_Count() == 0)
                System.out.println("AppSettings is empty.");
            else {
                for (int i = 0; i < appSettings.get_AllKeys().length; i++) {
                    String AKey = appSettings.get_AllKeys()[i];
                    System.out.println(String.format("Key: %s Value: {1}", AKey, appSettings.get_Item(AKey)));
                }
            }
        } catch (Exception ex) {
            System.out.println("Error reading app settings");
        }
    }

    static void ReadSetting(String Key) {
        try {
            NameValueCollection appSettings = ConfigurationManager.get_AppSettings();
            String result = appSettings.get_Item(Key);
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
            KeyValueConfigurationCollection settings = configFile.get_AppSettings().get_Settings();
            if (settings.get_Item(Key) == null)
                settings.Add(Key, value);
            else
                settings.get_Item(Key).set_Value(value);

            configFile.Save(ConfigurationSaveMode.Modified);
            ConfigurationManager.RefreshSection(configFile.get_AppSettings().get_SectionInformation().get_Name());
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