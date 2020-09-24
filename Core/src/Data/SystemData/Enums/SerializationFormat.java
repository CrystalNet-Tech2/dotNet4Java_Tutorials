package Data.SystemData.Enums;

import com.dotNet4Java.api.Enums.FlagEnum;

import java.util.HashMap;
import java.util.Map;

public enum SerializationFormat implements FlagEnum<SerializationFormat> {
    Xml(0x0),
    Binary(0x1);

    private static final Map<Long, SerializationFormat> mappedEnums = new HashMap<Long, SerializationFormat>();

    static {
        for (SerializationFormat value : SerializationFormat.values()) {
            mappedEnums.put(value._flag, value);
        }
    }

    private final long _flag;

    SerializationFormat(int value) {
        _flag = value;
    }

    @Override
    public long getFlag() {
        return _flag;
    }

    @Override
    public Map<Long, SerializationFormat> getMappedEnums() {
        return mappedEnums;
    }
}
