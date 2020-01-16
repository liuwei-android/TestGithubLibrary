package com.liuw.baselibrary.util;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liuw on 2019/8/8.
 */
public class StringUtils {
    private static int count = 0;


    public static boolean isNullOrWhiteSpace(String value) {
        return value == null || value.trim().length() == 0 || isEqual(value, "null");
    }

    public static boolean isEqual(String value, String value2) {
        return (value != null && value.equals(value2)) || (value == null && value2 == null);
    }

    public static String output(String value) {
        if (value == null) {
            return "";
        }
        return value;
    }

    public static boolean isPassword(String password) {
        Pattern p = Pattern.compile("^[a-z0-9A-Z]{6,}$");
        Matcher m = p.matcher(password);
        return m.matches();
    }

    public static boolean isMobile(String mobile) {
        Pattern p = Pattern.compile("^1[0-9]{10}$");
        Matcher m = p.matcher(mobile);
        return m.matches();
    }

    public static boolean isNumber(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static int count(String content, String child) {
        int count = 0;
        if (!isNullOrWhiteSpace(content)) {
            Pattern p = Pattern.compile(child, Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(content);
            while (m.find()) {
                count++;
            }
        }
        return count;
    }

    /**
     * 将“0.00”形式的价格解析成double类型的价格。
     *
     * @param price
     * @return
     */
    public static double parsePrice(String price) {
        DecimalFormat dfspeed = new DecimalFormat("0.00");
        try {
            return dfspeed.parse(price).doubleValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 将double类型的价格转换成"0.00"形式的字符串。
     *
     * @param price
     * @return
     */
    public static String formatPrice(double price) {
        DecimalFormat dfspeed = new DecimalFormat("0.00");
        return dfspeed.format(price);
    }

    public static String formatCount(double price) {
        DecimalFormat dfspeed = new DecimalFormat("0.0");
        return dfspeed.format(price);
    }

    /**
     * 将double类型的价格转换成"0.##"形式的字符串。
     *
     * @param price
     * @return
     */
    public static String formatPrice2(double price) {
        DecimalFormat dfspeed = new DecimalFormat("0.##");
        return dfspeed.format(price);
    }

    public static String format3(double value) {
        DecimalFormat dfspeed = new DecimalFormat("0.###");
        return dfspeed.format(value);
    }


    /**
     * 拼接字符串代替“+”拼接。
     *
     * @param items
     * @return
     */
    public static String append(String... items) {
        StringBuffer buffer = new StringBuffer();
        for (String item : items) {
            if (!TextUtils.isEmpty(item)) {
                buffer.append(item);
            }
        }
        return buffer.toString();
    }


    /**
     * 提取出城市名称,如果结果小于等于一个字符，则不做提取操作。
     *
     * @param city
     * @return
     */
    public static String extractLocation(String city) {
        String result = new String(city);
        if (result != null) {
            if (result.endsWith("维吾尔自治区")) {
                result = result.substring(0, result.length() - 6);
            } else if (result.endsWith("特别行政区")) {
                result = result.substring(0, result.length() - 5);
            } else if (result.endsWith("自治州") || result.endsWith("自治县") || result.endsWith("自治区")) {
                result = result.substring(0, result.length() - 3);
            } else if (result.endsWith("地区")) {
                result = result.substring(0, result.length() - 2);
            } else if (result.endsWith("市") || result.endsWith("盟") || result.endsWith("省")
                    || result.endsWith("县") || result.endsWith("区") || result.endsWith("旗")) {
                result = result.substring(0, result.length() - 1);
            }
            if (result.length() <= 1) {
                return city;
            }
        }
        return result;
    }

    public static void setHtml(final Context context, final TextView tvText, final String html) {
        if (!StringUtils.isNullOrWhiteSpace(html)) {
            Spanned span = Html.fromHtml(html);
            tvText.setText(span);
        } else {
            tvText.setText("");
        }
    }

    public static String replace(String text, String v1, String v2) {
        if (!StringUtils.isNullOrWhiteSpace(text)) {
            return text.replace(v1, v2);
        }
        return "";
    }

    public static String replaceRN(String text) {
        if (!StringUtils.isNullOrWhiteSpace(text)) {
            text = text.replace("\\u000d", "\r").replace("\\u000a", "\n");
        }
        return text;
    }

    public static String nullToEmpty(String value) {
        if (value == null) {
            return "";
        }
        return value;

    }

    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static String isEmptyValue(String value) {
        return isEmpty(value) ? "" : value;
    }

    /**
     * 判断字符串是否为"空格",""或null
     * StringUtils.isBlank(null)      = state_true
     * StringUtils.isBlank("")        = state_true
     * StringUtils.isBlank(" ")       = state_true
     * StringUtils.isBlank("bob")     = state_false
     * StringUtils.isBlank("  bob  ") = state_false
     *
     * @param cs
     * @return
     */
    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(cs.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    public static String join(String s, List<String> list) {
        String result = "";
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                result += list.get(i) + s;
            }
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }


    public static String listToString(List<String> list) {
        if (list == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean first = true;
        //第一个前面不拼接";"
        for (String string : list) {
            if (first) {
                first = false;
            } else {
                result.append(";");
            }
            result.append(string);
        }
        return result.toString();
    }


    public static String listToString2(List<String> list) {
        if (list == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean first = true;
        //第一个前面不拼接","
        for (String string : list) {
            if (first) {
                first = false;
            } else {
                result.append(",");
            }
            result.append(string);
        }
        return result.toString();
    }

    //第一个参数s1 中是否包含s2
    public static boolean isContain(String s1, String s2) {
        return !StringUtils.isNullOrWhiteSpace(s1) && s1.contains(s2);
    }

    public static String emptyOrDefault(String value, String defaultValue) {
        if (!isNullOrWhiteSpace(value)) {
            return value;
        }
        return defaultValue;
    }

    public static String getIndexText(int index) {
        String value = "";
        if (index >= 10) {
            value += getIndexText(index / 10);
        }
        int mo = index % 10;
        switch (mo) {
            case 1:
                value += "一";
                break;
            case 2:
                value += "二";
                break;
            case 3:
                value += "三";
                break;
            case 4:
                value += "四";
                break;

            case 5:
                value += "五";
                break;

            case 6:
                value += "六";
                break;

            case 7:
                value += "七";
                break;

            case 8:
                value += "八";
                break;

            case 9:
                value += "九";
                break;

            case 0:
                value += "零";
                break;
        }
        return value;
    }

    public static String showSpeed(double speed) {
        if (speed > 1000) {
            return String.format("%.1f公里/秒", speed / 1000);
        }
        return String.format("%.1f米/秒", speed);
    }

    public static String joinHyphen(String a, String b) {
        if (!StringUtils.isNullOrWhiteSpace(a) && !StringUtils.isNullOrWhiteSpace(b)) {
            return a + "-" + b;
        } else if (!StringUtils.isNullOrWhiteSpace(a)) {
            return a;
        }
        return b;
    }

    public static String getExipreDaysString(int days) {
        if (days < 0) {
            return String.format("剩%d天 ", Math.abs(days));
        } else {
            return days == 0 ? "即将逾期" : String.format("逾期%d天 ", days);
        }
    }

    public static String getCountTransform(String counts) {
        try{
            if (!StringUtils.isNullOrWhiteSpace(counts)) {
                double count = Double.parseDouble(counts);
                if (count >= 1000) {
                    return String.format("%sk", formatCount(count/1000));
                }
            }
            return counts;
        } catch (Exception e) {
            return "0";
        }
    }

    public static SpannableString getTitleCore(String title) {
        SpannableString str = new SpannableString(title + "*");
        str.setSpan(new ForegroundColorSpan(Color.RED), title.length(), title.length() + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return str;
    }

    public static boolean isSplitContainer(String s1, String s2) {
        if (!StringUtils.isNullOrWhiteSpace(s1)) {
            String[] results = s1.split(",");
            for (String s : results) {
                if (StringUtils.isEqual(s, s2)) {
                    return true;
                }
            }
        }
        return false;
    }
}
