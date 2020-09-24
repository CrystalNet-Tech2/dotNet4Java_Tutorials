package Event_Handler;

import com.dotNet4Java.api.EClrError;
import com.dotNet4Java.api.Enums.EnumUtils;
import system.data.StateChangeEventArgs;
import system.data.StateChangeEventHandler;
import system.data.sqlclient.SqlConnection;

class EventHandler implements AutoCloseable {
    SqlConnection sqlConnection;
    String connectionString;
    // Event Handler
    StateChangeEventHandler stateChangeEventHandler = new StateChangeEventHandler() {
        @Override
        public void invoke(Object o, StateChangeEventArgs e) throws Exception {
            long m_currentState = EnumUtils.setToInteger(e.get_CurrentState());
            long m_originalState = EnumUtils.setToInteger(e.get_OriginalState());

            System.out.print("Current State : ");
            writeStateChange(m_currentState);
            System.out.print("Original State : ");
            writeStateChange(m_originalState);
        }
    };

    EventHandler() throws Exception {
    }

    void createSQLConnectionTypeInstance() throws EClrError {
        sqlConnection = new SqlConnection();
        sqlConnection.add_StateChange(stateChangeEventHandler);
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
    void writeStateChange(long State) {
        switch ((int) State) {
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
            sqlConnection.remove_StateChange(stateChangeEventHandler);
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
