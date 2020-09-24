package Event_Handler;

import com.dotNet4Java.IClrObject;
import com.dotNet4Java.TClrActivator;
import com.dotNet4Java.TClrAssembly;
import com.dotNet4Java.TClrObject;
import com.dotNet4Java.api.EClrError;
import com.dotNet4Java.api.core.DotNetNativeTypes;

class EventHandler implements AutoCloseable {
    IClrObject sqlConnection;
    String connectionString;
    // Event Handler
    DotNetNativeTypes.IClrEventHandler stateChangeEventHandler = (sender, e) -> {

        IClrObject m_eventArgs = new TClrObject(e);
        int m_currentState = m_eventArgs.getPropertyValue("CurrentState", Integer.class);
        int m_originalState = m_eventArgs.getPropertyValue("OriginalState", Integer.class);

        System.out.print("Current State : ");
        writeStateChange(m_currentState);
        System.out.print("Original State : ");
        writeStateChange(m_originalState);
    };

    void loadAssembly() throws EClrError {
        //Load .Net Assembly from GAC with just partial name
        TClrAssembly.loadWithPartialName("System.Data");
    }

    void createSQLConnectionTypeInstance() throws EClrError {
        //Create Instance of System.Data.SqlClient.SqlConnection object
        sqlConnection = new TClrObject(TClrActivator.createInstance("System.Data.SqlClient.SqlConnection"));
        sqlConnection.registerEventCallBack("StateChange", stateChangeEventHandler);
    }

        /*    ====== C# EVENT HANDLERS ====
              //C# Delegate of SqlConnection.StateChange event
              public delegate void StateChangeEventHandler(object sender, StateChangeEventArgs e);

              public enum ConnectionState
              {
                  Closed = 0,
                  Open = 1,
                  Connecting = 2,
                  Executing = 4,
                  Fetching = 8,
                  Broken = 16,
              }
              public sealed class StateChangeEventArgs
              {
                  public ConnectionState CurrentState { get; }
                  public ConnectionState OriginalState { get; }
              }
        */

    void openAndCloseSQLConnection() throws EClrError {

        connectionString = "Data Source=MyDataSourceName;Initial Catalog=MyDBName;User ID=MyUserName;Password=MyPasswd"; //<Change Me>
        sqlConnection.setPropertyValue("ConnectionString", connectionString);

        sqlConnection.invokeVoidMethod("Open");
        System.out.println("Connection Opened");
        System.out.println();

        sqlConnection.invokeVoidMethod("Close");
        System.out.println("Connection Closed");
    }

    // ====== JAVA EVENT HANDLERS EQUIVALENT ====
    void writeStateChange(int State) {
        switch (State) {
            case 0:
                System.out.print("Closed");
                break;
            case 1:
                System.out.print("Open");
                break;
            case 2:
                System.out.print("Connecting");
                break;
            case 4:
                System.out.print("Executing");
                break;
            case 8:
                System.out.print("Fetching");
                break;
            case 16:
                System.out.print("Broken");
                break;
            default:
                System.out.println();
        }
    }

    @Override
    public void close() throws Exception {
        if (sqlConnection != null) {
            sqlConnection.unRegisterEventCallBack("StateChange", stateChangeEventHandler);
            sqlConnection.close();
        }
    }
}

public class AdvancedEventHandler {

    public static void main(String[] arg) {

        System.out.println("            Hello! Welcome to dotNet4Java.          ");
        System.out.println("====================================================");
        System.out.println("This program demonstrates how to handle .Net events from Java.");
        System.out.println();

        try {
            EventHandler eventHandler = new EventHandler();
            try {
                eventHandler.loadAssembly();
                eventHandler.createSQLConnectionTypeInstance();
                eventHandler.openAndCloseSQLConnection();
            } finally {
                eventHandler.close();
            }
        } catch (EClrError eClrError) {
            eClrError.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
