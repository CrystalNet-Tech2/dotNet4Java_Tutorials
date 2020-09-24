package Data.SystemData.Events;

public interface DataColumnChangeEventHandler<T extends DataColumnChangeEventArgs> {
    void invoke(Object sender, T e) throws Exception;
}
