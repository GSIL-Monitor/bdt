package cn.com.infaith.module.util;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;

/**
 * Created by Administrator on 2017/4/18.
 */
public class TimeUtil {
    public static SimpleDateFormat timeSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat dateSDF = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat yearSDF = new SimpleDateFormat("yyyy");
    public static SimpleDateFormat cnSDF = new SimpleDateFormat("MM月dd日");

    /**
     * 获取当天的零点
     *
     * @return
     */
    public static Date getTodayZero() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date zero = calendar.getTime();
        return zero;
    }

    public static Long getYesterdayTimestamp() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime().getTime();
    }

    public static long getTodayZeroTimestamp() {
        Date zero = getTodayZero();
        return zero.getTime();
    }

    public static String getTimeString() {
        Calendar calendar = new GregorianCalendar();
        return getTimeString(calendar);
    }

    private static String getTimeString(Calendar calendar) {
        StringBuffer sb = new StringBuffer();
        sb.append(String.valueOf(calendar.get(Calendar.YEAR)))
                .append(valueOfString(String.valueOf(calendar.get(Calendar.MONTH) + 1), 2))
                .append(valueOfString(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)), 2))
                .append(valueOfString(String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)), 2))
                .append(valueOfString(String.valueOf(calendar.get(Calendar.MINUTE)), 2))
                .append(valueOfString(String.valueOf(calendar.get(Calendar.SECOND)), 2))
                .append(valueOfString(String.valueOf(calendar.get(Calendar.MILLISECOND)), 3));
        return sb.toString();
    }

    private static String valueOfString(String str, int len) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len - str.length(); i++) {
            sb.append("0");
        }
        return (sb.length() == 0) ? (str) : (sb.toString() + str);
    }

    public static Date strToDate(String dateStr) throws ParseException {
        return dateSDF.parse(dateStr);
    }

    /**
     * 获取当前时间 格式为yyyy-MM-dd hh:mm:ss
     */
    public static String getNowTime() {
        return timeSDF.format(System.currentTimeMillis());
    }

    /**
     * 获取当前日期 格式为yyyy-MM-dd
     */
    public static String getNowDate() {
        return dateSDF.format(System.currentTimeMillis());
    }


    /**
     * //Convert Unix timestamp to normal date style
     *
     * @param timestampString
     * @return
     */
    public static String timeStamp2Date(String timestampString) {
        if (StringUtils.isBlank(timestampString)) {
            return "";
        }
        Long timestamp = Long.parseLong(timestampString) * 1000;
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date(timestamp));
        return date;
    }

    /**
     * //Convert Unix timestamp to normal date style
     *
     * @param timestampString
     * @return
     */
    public static String timeStamp2TimeStr(String timestampString) {
        if (StringUtils.isBlank(timestampString)) {
            return "";
        }
        Long timestamp = Long.parseLong(timestampString);
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timestamp));
        return date;
    }

    /**
     * //Convert Unix timestamp to normal date style
     *
     * @param timestampString
     * @return
     */
    public static String timeStampToSecondFomartDate(String timestampString) {
        Long timestamp = Long.parseLong(timestampString);
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date(timestamp));
        return date;
    }

    public static String getBeforeOrAfterDate(int amount) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, amount);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String mDateTime = formatter.format(c.getTime());
        return mDateTime;
    }

    /**
     * 根据时间类型比较时间大小
     *
     * @param source
     * @param traget
     * @param type   "YYYY-MM-DD" "yyyyMMdd HH:mm:ss"  类型可自定义
     *               传递时间的对比格式
     * @return 0 ：source和traget时间相同
     * 1 ：source比traget时间大
     * -1：source比traget时间小
     * @throws Exception
     */
    public static int DateCompare(String source, String traget, String type) throws Exception {
        int ret = 2;
        SimpleDateFormat format = new SimpleDateFormat(type);
        Date sourcedate = format.parse(source);
        Date tragetdate = format.parse(traget);
        ret = sourcedate.compareTo(tragetdate);
        return ret;
    }

    /**
     * //Convert Unix timestamp to normal date style
     *
     * @param timestampString
     * @return
     */
    public static Date timeStampToDate(String timestampString) {
        Long timestamp = Long.parseLong(timestampString) * 1000;
        Date date = new Date(timestamp);
        return date;
    }


    public static Date timeStampToSecondDate(String timestampString) {
        Long timestamp = Long.parseLong(timestampString);
        Date date = new Date(timestamp);
        return date;
    }

    public static Date timeStampToDateByOptional(String timestampString) {

        Date date = Optional.ofNullable(timestampString)
                .map(l -> Long.parseLong(l) * 1000)
                .map(Date::new)
                .orElse(null);
        return date;
    }


    /**
     * 将短时间格式时间转换为字符串 yyyy-MM-dd
     *
     * @param dateDate
     * @return
     */
    public static String dateToStr(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(dateDate);
        return dateString;
    }


    /**
     * 将短时间格式时间转换为字符串 yyyy-MM-dd HH:mm:SS
     *
     * @param dateDate
     * @return
     */
    public static String dateToStrTime(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }


    /**
     * 判断时间是否在时间段内
     *
     * @param date         当前时间 yyyy-MM-dd HH:mm:ss
     * @param strDateBegin 开始时间 00:00:00
     * @param strDateEnd   结束时间 00:05:00
     * @return
     */
    public static boolean isInDate(Date date, String strDateBegin, String strDateEnd) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(date);
        // 截取当前时间时分秒
        int strDateH = Integer.parseInt(strDate.substring(11, 13));
        int strDateM = Integer.parseInt(strDate.substring(14, 16));
        int strDateS = Integer.parseInt(strDate.substring(17, 19));
        // 截取开始时间时分秒
        int strDateBeginH = Integer.parseInt(strDateBegin.substring(0, 2));
        int strDateBeginM = Integer.parseInt(strDateBegin.substring(3, 5));
        int strDateBeginS = Integer.parseInt(strDateBegin.substring(6, 8));
        // 截取结束时间时分秒
        int strDateEndH = Integer.parseInt(strDateEnd.substring(0, 2));
        int strDateEndM = Integer.parseInt(strDateEnd.substring(3, 5));
        int strDateEndS = Integer.parseInt(strDateEnd.substring(6, 8));
        if ((strDateH >= strDateBeginH && strDateH <= strDateEndH)) {
            // 当前时间小时数在开始时间和结束时间小时数之间
            if (strDateH > strDateBeginH && strDateH < strDateEndH) {
                return true;
                // 当前时间小时数等于开始时间小时数，分钟数在开始和结束之间
            } else if (strDateH == strDateBeginH && strDateM >= strDateBeginM
                    && strDateM <= strDateEndM) {
                return true;
                // 当前时间小时数等于开始时间小时数，分钟数等于开始时间分钟数，秒数在开始和结束之间
            } else if (strDateH == strDateBeginH && strDateM == strDateBeginM
                    && strDateS >= strDateBeginS && strDateS <= strDateEndS) {
                return true;
            }
            // 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数小等于结束时间分钟数
            else if (strDateH >= strDateBeginH && strDateH == strDateEndH
                    && strDateM <= strDateEndM) {
                return true;
                // 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数等于结束时间分钟数，秒数小等于结束时间秒数
            } else if (strDateH >= strDateBeginH && strDateH == strDateEndH
                    && strDateM == strDateEndM && strDateS <= strDateEndS) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static Boolean isOpeningTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        if (calendar.get(Calendar.HOUR_OF_DAY) >= 17 || calendar.get(Calendar.HOUR_OF_DAY) < 9 || (calendar.get(Calendar.HOUR_OF_DAY) <= 9 && calendar.get(Calendar.MINUTE) < 30)) {
            return false;
        }
        return true;
    }


    public static Date getFrontMonth() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar c = Calendar.getInstance();
        String format = sdf.format(new Date());
        c.setTime(sdf.parse(format));
        c.add(Calendar.MONTH, -1);
        return c.getTime();
    }

    public static String getFrontMonthLastDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("M月d日");
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 0);
        String thr_one_lastDay = sdf.format(c.getTime());
        return thr_one_lastDay;
    }

    public static String getFrontMonthLastDayWithYear() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 0);
        String thr_one_lastDay = sdf.format(c.getTime());
        return thr_one_lastDay;
    }

    public static String getCurrentYear() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        String year = sdf.format(new Date());
        return year;
    }

    public static String getFrontMonthWithOutYear() {
        SimpleDateFormat sdf = new SimpleDateFormat("M月");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        String format = sdf.format(c.getTime());
        return format;
    }

    //前一个月第一天
    public static String getFrontMonthFirstDay() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return sdf.format(c.getTime());
    }

    //前一个月最后一天
    public static String getFrontMonthLastDayWithDate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 0);
        return sdf.format(c.getTime());
    }

    public static Long getLastWeekTimeStamp() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, -7);
        return calendar.getTimeInMillis();
    }

    public static Long getLastMonthTimeStamp() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, -30);
        return calendar.getTimeInMillis();
    }

    public static String getYearWithoutMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, 0);
        Date date = c.getTime();
        String frontYearWithoutMonth = sdf.format(date);
        return frontYearWithoutMonth;
    }

    public static String getFrontYearWithoutMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, -1);
        Date date = c.getTime();
        String frontYearWithoutMonth = sdf.format(date);
        return frontYearWithoutMonth;
    }

    public static String getYearLastDay(String year) {
        int thisYear = Integer.parseInt(year);
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, thisYear);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(currYearLast);
        return format;
    }

    public static String getFrontYearLastDay(String year) {
        int frontYear = Integer.parseInt(year) - 1;
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, frontYear);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(currYearLast);
        return format;
    }

    public static String getFrontTwoYearLastDayWithoutYear(String year) {
        int frontYear = Integer.parseInt(year) - 2;
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, frontYear);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        String format = sdf.format(currYearLast);
        return format;
    }

    public static String getYearLastDayWithoutYear(String year, int number) {
        int frontYear = Integer.parseInt(year) - number;
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, frontYear);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        String format = sdf.format(currYearLast);
        return format;
    }

    public static String getTenDayStr() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, -10);
        return dateSDF.format(calendar.getTime());
    }

    public static String getYearLastDayWithoutYear(String year) {
        int frontYear = Integer.parseInt(year) - 1;
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, frontYear);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        String format = sdf.format(currYearLast);
        return format;
    }

    public static String getFrontYearLastDayWithoutYear(String year) {
        int frontYear = Integer.parseInt(year) - 1;
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, frontYear);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        String format = sdf.format(currYearLast);
        return format;
    }

    public static String getFrontTwoYearLastDay(String time) {
        int frontYear = Integer.parseInt(time) - 2;
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, frontYear);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(currYearLast);
        return format;
    }

    public static String getFrontYearByNumberWithoutYear(String time, int number) {
        int frontYear = Integer.parseInt(time) - number;
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, frontYear);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        String format = sdf.format(currYearLast);
        return format;
    }

    public static String getFrontYearWithNumber(String time, int number) {
        int frontYear = Integer.parseInt(time) - number;
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, frontYear);
        Date currYearLast = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String format = sdf.format(currYearLast);
        return format;
    }

    public static String getDateYearStr(Date date) {
        return yearSDF.format(date);
    }

    public static int getCurrentYearInteger() {
        LocalDate localDate = LocalDate.now();
        return localDate.getYear();
    }

    public static int getCurrentMonth() {
        LocalDate localDate = LocalDate.now();
        return localDate.getMonthValue();
    }

    public static int getCurrentQuarter() {
        int month = getCurrentMonth();
        switch (month) {
            case 1:
            case 2:
            case 3:
                return 1;
            case 4:
            case 5:
            case 6:
                return 2;
            case 7:
            case 8:
            case 9:
                return 3;
            case 10:
            case 11:
            case 12:
                return 4;
        }
        return 0;
    }

    public static String getYesterDayStr() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return dateSDF.format(calendar.getTime());
    }

    public static String formatCNDateStr(String dateStr) {
        try {
            return cnSDF.format(dateSDF.parse(dateStr));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(getYesterDayStr());
    }


    public static LocalDateTime getLocalDateTimeOfTimestamp(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    public static Date dateAddDays(Date date, int days) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, days);
        return c.getTime();
    }

    public static Date dateAddYear(Date date, int years) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, years);
        return c.getTime();
    }

    public static Date dateAddMonth(Date date, int months) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, months);
        return c.getTime();
    }

    public static Date getTodayZeroDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date zero = calendar.getTime();
        return zero;
    }

    public static long getTodayZeroTime() {
        return getTodayZeroDate().getTime();
    }

    public static Date getDateZeroDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Long getDateZeroTime(Date date) {
        return getDateZeroDate(date).getTime();
    }
}
