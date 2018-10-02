package cn.com.infaith.module.util;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContentUtil {

    private final static String[] marketList = {"沪市主板", "沪主板", "深市主板", "深主板", "深市中小板", "中小板", "深市创业板", "创业板", "新三板"};

    public static String getContentByKey(String content, String contentKey, int length) {

        if (StringUtils.isNotBlank(content)) {
            int index = content.indexOf(contentKey);
            if (index == -1) {
                return content.length() > length ? content.substring(0, length) : content;
            }
            String c = content.substring(0, index);
            int startIndex = c.lastIndexOf("。");
            if (startIndex == -1) {
                if (content.length() > length) {
                    content = content.substring(0, length);
                }
            } else {
                if (content.substring(startIndex).length() > length) {
                    content = content.substring(startIndex + 1, startIndex + length);
                } else {
                    content = content.substring(startIndex + 1);
                }
            }
            content = content.replaceAll("\n|\t", "");
        }
        return content;
    }

    /**
     * 适用板块排序
     *
     * @param markets
     * @return
     */
    public static List<String> marketSort(String markets) {

        List<String> list = new ArrayList<>();
        String[] market = markets.split("、|,|，");
        if (market.length == 0) {
            return list;
        }
        for (int i = 0; i < marketList.length; i++) {
            if (!list.contains(marketList[i]) && Arrays.asList(market).contains(marketList[i])) {
                list.add(marketList[i]);
            }
        }
        return list;
    }

    public static String getContentByPunctuation(String content, String contentKey, int length) {
        if (StringUtils.isNotBlank(content)) {
            if (StringUtils.isBlank(contentKey)) {
                return content.length() > length ? content.substring(0, length) : content;
            }
            if (contentKey.contains(",")) {
                contentKey = contentKey.substring(0, contentKey.indexOf(","));
            }
            int index = content.indexOf(contentKey);
            if (index == -1) {
                return content.length() > length ? content.substring(0, length) : content;
            }
            String c = content.substring(0, index);
            //逗号位置
            int commaIndex = c.lastIndexOf("，");
            //句号位置
            int periodIndex = c.lastIndexOf("。");
            //分号位置
            int semicolonIndex = c.lastIndexOf("；");
            //顿号位置
            int pauseIndex = c.lastIndexOf("、");
            int startIndex = 0;
            if (commaIndex > periodIndex && commaIndex > semicolonIndex && commaIndex > pauseIndex) {
                startIndex = commaIndex;
            } else if (periodIndex > commaIndex && periodIndex > semicolonIndex && periodIndex > pauseIndex) {
                startIndex = periodIndex;
            } else if (semicolonIndex > commaIndex && semicolonIndex > periodIndex && semicolonIndex > pauseIndex) {
                startIndex = semicolonIndex;
            } else if (pauseIndex > commaIndex && pauseIndex > periodIndex && pauseIndex > semicolonIndex) {
                startIndex = pauseIndex;
            }
            int distance = index - startIndex;
            if (distance > 60) {
                startIndex = startIndex + distance-20;
            }
            content = subStrByIndex(content, startIndex, length);
        }
        return content;
    }

    private static String subStrByIndex(String content, int startIndex, int length) {
        if (startIndex == -1) {
            if (content.length() > length) {
                content = content.substring(0, length);
            }
        } else {
            if (content.substring(startIndex).length() > length) {
                content = content.substring(startIndex + 1, startIndex + length);
            } else {
                content = content.substring(startIndex + 1);
            }
        }
        content = content.replaceAll("\n|\t|\r", "");
        return content;
    }

    public static void main(String[] args) {
        String s = "无锡上机数控股份有限公司 Wuxi Shangji Automation Co., Ltd. （住址：无锡市滨湖区雪浪街道南湖中路 158 号）     首次";
        System.out.println(s.length());
    }
}
