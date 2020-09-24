package Data.SystemData.Enums;

import com.dotNet4Java.api.Enums.FlagEnum;

import java.util.HashMap;
import java.util.Map;

public enum XmlReadMode implements FlagEnum<XmlReadMode> {
    Auto(0x0),
    ReadSchema(0x1),
    IgnoreSchema(0x2),
    InferSchema(0x3),
    DiffGram(0x4),
    Fragment(0x5),
    InferTypedSchema(0x6);

    private static final Map<Long, XmlReadMode> mappedEnums = new HashMap<>();

    static {
        for (XmlReadMode value : XmlReadMode.values()) {
            mappedEnums.put(value._flag, value);
        }
    }

    private final long _flag;

    XmlReadMode(int value) {
        _flag = value;
    }

    @Override
    public long getFlag() {
        return _flag;
    }

    @Override
    public Map<Long, XmlReadMode> getMappedEnums() {
        return mappedEnums;
    }
}
