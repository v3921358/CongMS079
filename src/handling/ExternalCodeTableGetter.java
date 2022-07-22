package handling;

import java.util.Iterator;
import tools.HexTool;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Properties;

public class ExternalCodeTableGetter
{
    final Properties props;
    
    public ExternalCodeTableGetter(final Properties properties) {
        this.props = properties;
    }
    
    private static <T extends Enum> T valueOf(final String name, final T[] values) {
        for (final T val : values) {
            if (((java.lang.Enum)val).name().equals((Object)name)) {
                return val;
            }
        }
        return null;
    }
    
    private <T extends java.lang.Enum> short getValue(final String name, final T[] values, final short def) {
        final String prop = this.props.getProperty(name);
        if (prop == null || prop.length() <= 0) {
            return def;
        }
        final String trimmed = prop.trim();
        final String[] args = trimmed.split(" ");
        int base = 0;
        String offset;
        if (args.length == 2) {
            base = ((WritableIntValueHolder)valueOf(args[0], values)).getValue();
            if (base == def) {
                base = this.getValue(args[0], (Enum[])values, def);
            }
            offset = args[1];
        }
        else {
            offset = args[0];
        }
        if (offset.length() > 2 && offset.substring(0, 2).equals((Object)"0x")) {
            return (short)(Short.parseShort(offset.substring(2), 16) + base);
        }
        return (short)(Short.parseShort(offset) + base);
    }
    
    public static final <T extends java.lang.Enum> String getOpcodeTable(final T[] enumeration) {
        final StringBuilder enumVals = new StringBuilder();
        final List<T> all = new ArrayList<T>();
        all.addAll((Collection<? extends T>)Arrays.asList(enumeration));
        Collections.sort((List)all, new Comparator<WritableIntValueHolder>() {
            @Override
            public int compare(final WritableIntValueHolder o1, final WritableIntValueHolder o2) {
                return Short.valueOf(o1.getValue()).compareTo(Short.valueOf(o2.getValue()));
            }
        });
        for (final T code : all) {
            enumVals.append(((java.lang.Enum)code).name());
            enumVals.append(" = ");
            enumVals.append("0x");
            enumVals.append(HexTool.toString((int)((WritableIntValueHolder)code).getValue()));
            enumVals.append(" (");
            enumVals.append((int)((WritableIntValueHolder)code).getValue());
            enumVals.append(")\n");
        }
        return enumVals.toString();
    }
    
    public static final <T extends java.lang.Enum> void populateValues(final Properties properties, final T[] values) {
        final ExternalCodeTableGetter exc = new ExternalCodeTableGetter(properties);
        for (final T code : values) {
            ((WritableIntValueHolder)code).setValue(exc.getValue(((java.lang.Enum)code).name(), values, (short)(-2)));
        }
    }
}
