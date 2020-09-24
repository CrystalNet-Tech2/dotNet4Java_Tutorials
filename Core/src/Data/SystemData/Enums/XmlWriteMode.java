package Data.SystemData.Enums;

import com.dotNet4Java.api.Enums.FlagEnum;

import java.util.HashMap;
import java.util.Map;

public enum XmlWriteMode implements FlagEnum<XmlWriteMode> {
    WriteSchema(0x0),
    IgnoreSchema(0x1),
    DiffGram(0x2);

    private static final Map<Long, XmlWriteMode> mappedEnums = new HashMap<>();

    static {
        for (XmlWriteMode value : XmlWriteMode.values()) {
            mappedEnums.put(value._flag, value);
        }
    }

    private final long _flag;

    XmlWriteMode(int value) {
        _flag = value;
    }

    @Override
    public long getFlag() {
        return _flag;
    }

    @Override
    public Map<Long, XmlWriteMode> getMappedEnums() {
        return mappedEnums;
    }
}
