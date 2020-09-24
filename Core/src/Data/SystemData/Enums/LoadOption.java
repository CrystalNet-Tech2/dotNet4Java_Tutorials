package Data.SystemData.Enums;

import com.dotNet4Java.api.Enums.FlagEnum;

import java.util.HashMap;
import java.util.Map;

public enum LoadOption implements FlagEnum<LoadOption> {
    OverwriteChanges(0x1),
    PreserveChanges(0x2),
    Upsert(0x3);

    private static final Map<Long, LoadOption> mappedEnums = new HashMap<Long, LoadOption>();

    static {
        for (LoadOption value : LoadOption.values()) {
            mappedEnums.put(value._flag, value);
        }
    }

    private final long _flag;

    LoadOption(int value) {
        _flag = value;
    }

    @Override
    public long getFlag() {
        return _flag;
    }

    @Override
    public Map<Long, LoadOption> getMappedEnums() {
        return mappedEnums;
    }
}
