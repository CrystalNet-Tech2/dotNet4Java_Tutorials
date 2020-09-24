package Data.SystemData.Events;

public interface DataRowChangeEventHandler {
    void invoke(Object sender, DataRowChangeEventArgs e) throws Exception;
}
