package client.messages;

import tools.StringUtil;

public class CommandProcessorUtil
{
    public static String joinAfterString(final String[] splitted, final String str) {
        for (int i = 1; i < splitted.length; ++i) {
            if (splitted[i].equalsIgnoreCase(str) && i + 1 < splitted.length) {
                return StringUtil.joinStringFrom(splitted, i + 1);
            }
        }
        return null;
    }
    
    public static int getOptionalIntArg(final String[] splitted, final int position, final int def) {
        if (splitted.length > position) {
            try {
                return Integer.parseInt(splitted[position]);
            }
            catch (NumberFormatException nfe) {
                return def;
            }
        }
        return def;
    }
    
    public static String getNamedArg(final String[] splitted, final int startpos, final String name) {
        for (int i = startpos; i < splitted.length; ++i) {
            if (splitted[i].equalsIgnoreCase(name) && i + 1 < splitted.length) {
                return splitted[i + 1];
            }
        }
        return null;
    }
    
    public static Long getNamedLongArg(final String[] splitted, final int startpos, final String name) {
        final String arg = getNamedArg(splitted, startpos, name);
        if (arg != null) {
            try {
                return Long.valueOf(Long.parseLong(arg));
            }
            catch (NumberFormatException ex) {}
        }
        return null;
    }
    
    public static Integer getNamedIntArg(final String[] splitted, final int startpos, final String name) {
        final String arg = getNamedArg(splitted, startpos, name);
        if (arg != null) {
            try {
                return Integer.valueOf(Integer.parseInt(arg));
            }
            catch (NumberFormatException ex) {}
        }
        return null;
    }
    
    public static int getNamedIntArg(final String[] splitted, final int startpos, final String name, final int def) {
        final Integer ret = getNamedIntArg(splitted, startpos, name);
        if (ret == null) {
            return def;
        }
        return (int)ret;
    }
    
    public static Double getNamedDoubleArg(final String[] splitted, final int startpos, final String name) {
        final String arg = getNamedArg(splitted, startpos, name);
        if (arg != null) {
            try {
                return Double.valueOf(Double.parseDouble(arg));
            }
            catch (NumberFormatException ex) {}
        }
        return null;
    }
}
