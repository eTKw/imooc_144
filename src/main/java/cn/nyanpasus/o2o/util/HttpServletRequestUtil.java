package cn.nyanpasus.o2o.util;

import javax.servlet.http.HttpServletRequest;

public class HttpServletRequestUtil {
    public static int getInt(HttpServletRequest request, String key) {
        try {
            return Integer.decode(request.getParameter(key));
        } catch (Exception e) {
            return -1;
        }
    }

    public static long getLong(HttpServletRequest request, String key) {
        try {
            return Long.parseLong(request.getParameter(key));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static Double getDouble(HttpServletRequest request, String key) {
        try {
            return Double.parseDouble(key);
        } catch (NumberFormatException e) {
            return -1d;
        }
    }

    public static boolean getBoolean(HttpServletRequest request, String key) {
        try {
            return Boolean.parseBoolean(key);
        } catch (Exception e) {
            return false;
        }
    }

    public static String getString(HttpServletRequest request, String key) {
        try {
            String r = request.getParameter(key);
            if (r != null) {
                r = r.trim();
            }
            if ("".equals(r)) {
                r = null;
            }
            return r;
        } catch (Exception e) {
            return null;
        }
    }

}
