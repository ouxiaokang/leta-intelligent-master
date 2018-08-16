package cn.leta.common.utils;

/**
 * 经纬度帮助类
 * Created by <a href="mailto:xiegengcai@foxmail.com">xiegengcai</a> on 2017-12-18.
 * @author Xie Gengcai
 */
public final class LongLatUtils {
    public static final double RATIO = 30000;

    private LongLatUtils() {
    }

    public static double checkLongitude(double longitude) {
        return (longitude + 180) % 360.0 - 180;
    }

    public static double checkLatitude(double latitude) {
        return Math.min(90, Math.max(-90, latitude));
    }

    public static double convertDFToD(int dsrc, double f) {
       return dsrc + f / 60.0;
    }

    public static double convertDToDF(double dsrc) {
        int dintsrc = Double.valueOf(dsrc).intValue();
        double fsrc = dsrc - dintsrc;
        fsrc = fsrc * 60;
        return dintsrc * 100 + fsrc;
    }

    public static double convertDToPositiveDF(double dsrc) {
        double dfdest = convertDToDF(dsrc);
        if (dfdest < 0) {
            dfdest = -dfdest;
        }
        return dfdest;
    }

    public static String getLatPosLetter(double lat) {
        if (lat > 0) {
            return  "N";
        }
        return "S";
    }

    public static String getLonPosLetter(double lon) {
        if (lon > 0) {
            return  "E";
        }
        return "W";
    }

    public static double lonlatStrToDouble(String l) {
        String indicator = l.substring(0, 1);
        String valuestr = l.substring(1);
        double result = Double.valueOf(valuestr);
        if (indicator.compareToIgnoreCase("E") == 0 || indicator.compareToIgnoreCase("N") == 0) {
            return result;
        } else {
            return  -result;
        }
    }

    public static double getRotateAngle(double ax, double ay, double mx, double my, double bx, double by) {
        double ma_x = ax - mx;
        double ma_y = ay - my;
        double mb_x = bx - mx;
        double mb_y = by - my;
        double v1 = (ma_x * mb_x) + (ma_y * mb_y);
        double ma_val = Math.sqrt(ma_x * ma_x + ma_y * ma_y);
        double mb_val = Math.sqrt(mb_x * mb_x + mb_y * mb_y);
        double cosM = v1 / (ma_val * mb_val);
        return Math.acos(cosM) * 180 / Math.PI;
    }

    public static double convertFToD(double fen) {
        int d = (int) fen / 60;
        double f  = fen - d * 60;
        return convertDFToD(d, f);
    }


    public static double convertLatDAndStrToD(double latd, boolean latisnors) {
        StringBuilder result  = new StringBuilder();
        if (latisnors) {
            result.append("N");
        } else {
            result.append("S");
        }
        return lonlatStrToDouble(result.append(latd).toString());
    }

    public static double convertLonDAndStrToD(double lond, boolean iseorw) {
        StringBuilder result  = new StringBuilder();
        if (iseorw) {
            result.append("W");
        } else {
            result.append("E");
        }
        return lonlatStrToDouble(result.append(lond).toString());
    }
}
