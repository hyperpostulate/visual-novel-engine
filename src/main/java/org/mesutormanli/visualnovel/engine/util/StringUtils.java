package org.mesutormanli.visualnovel.engine.util;

public class StringUtils {
    public static String wrapLabelString(String unWrapped) {
        return "<html><body style='100%'> " + unWrapped.replace("\n", "<br/>") + "</body></html>";
    }

    public static boolean isEmpty(String str) {
        return ("".equals(str)) || (null == str);
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

}
