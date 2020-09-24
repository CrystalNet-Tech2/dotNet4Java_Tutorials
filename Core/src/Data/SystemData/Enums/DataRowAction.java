package Data.SystemData.Enums;

import com.dotNet4Java.api.Enums.BitwiseEnum;

public enum DataRowAction implements BitwiseEnum<DataRowAction> {
    Nothing(0x0),
    Delete(0x1),
    Change(0x2),
    Rollback(0x4),
    Commit(0x8),
    Add(0x10),
    ChangeOriginal(0x20),
    ChangeCurrentAndOriginal(0x40);

    private final int _flags;

    DataRowAction(int flags) {
        _flags = flags;
    }

    @Override
    public long getFlags() {
        return _flags;
    }
}
