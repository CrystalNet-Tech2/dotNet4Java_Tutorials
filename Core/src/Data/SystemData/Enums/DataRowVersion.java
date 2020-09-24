package Data.SystemData.Enums;

import com.dotNet4Java.api.Enums.FlagEnum;

import java.util.HashMap;
import java.util.Map;

public enum DataRowVersion implements FlagEnum<DataRowVersion> {
    Original(0x100),
    Current(0x200),
    Proposed(0x400),
    Default(0x600);

    private static final Map<Long, DataRowVersion> mappedEnums = new HashMap<>();

    static {
        for (DataRowVersion value : DataRowVersion.values()) {
            mappedEnums.put(value._flag, value);
        }
    }

    private final long _flag;

    DataRowVersion(int value) {
        _flag = value;
    }

    @Override
    public long getFlag() {
        return _flag;
    }

    @Override
    public Map<Long, DataRowVersion> getMappedEnums() {
        return mappedEnums;
    }
}
