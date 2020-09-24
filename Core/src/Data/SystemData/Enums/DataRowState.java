package Data.SystemData.Enums;

import com.dotNet4Java.api.Enums.BitwiseEnum;

public enum DataRowState implements BitwiseEnum<DataRowState> {
    Detached(0x1),
    Unchanged(0x2),
    Added(0x4),
    Deleted(0x8),
    Modified(0x10);

    private final int _flags;

    DataRowState(int flags) {
        _flags = flags;
    }

    @Override
    public long getFlags() {
        return _flags;
    }
}