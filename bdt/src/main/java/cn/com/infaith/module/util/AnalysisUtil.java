package cn.com.infaith.module.util;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class AnalysisUtil {

    public static final String fileDirectory = "/Users/tangqi/IdeaProjects/xiaoan/xiaoan-api/src/main/resources/template/";


    // 是否是2003的excel，返回true是2003
    public static boolean isExcel2003(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    //是否是2007的excel，返回true是2007
    public static boolean isExcel2007(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }



    public static String getFileNameWithOutSuffix(String originFileName){
        return originFileName.substring(0,originFileName.lastIndexOf("."));
    }

    //判断是否是合并单元格
    public static boolean isMergedCell(Sheet sheet,int row ,int column){
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if(row >= firstRow && row <= lastRow){
                if(column >= firstColumn && column <= lastColumn){
                    return true;
                }
            }
        }
        return false;
    }

    //获取合并单元格的值
    public static String getMergedRegionValue(Sheet sheet ,int row , int column){
        int sheetMergeCount = sheet.getNumMergedRegions();

        for(int i = 0 ; i < sheetMergeCount ; i++){
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();

            if(row >= firstRow && row <= lastRow){

                if(column >= firstColumn && column <= lastColumn){
                    Row fRow = sheet.getRow(firstRow);
                    Cell fCell = fRow.getCell(firstColumn);
                    return fCell.getStringCellValue();
                }
            }
        }

        return null ;
    }

    public static Date accessAbnormalDate(double data) throws ParseException {
        if(Double.valueOf(data)!=null){
            DecimalFormat df = new DecimalFormat("0");
            String stringData = df.format(data);
            if(!stringData.equals("0")){
                stringData = stringData.substring(0,4)+"-"+stringData.substring(4,6)+"-"+stringData.substring(6);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date destDate = sdf.parse(stringData);
                return destDate;
            }
        }
           return null;
    }

    public static List<String> getprovinceList(){
        List<String> provinceList = new ArrayList<>();
        provinceList.addAll( Arrays.asList(new String[]{"山东","湖南","湖北","河北","四川","重庆","广东"}));
        return provinceList;
    }

    public static String sortMap(HashMap<String, Integer> amountMap,String provinceName) {
        TreeSet<Integer> ts = new TreeSet<>();
        for (String province:amountMap.keySet()
             ) {
            ts.add(amountMap.get(province));
        }
        Integer[] integers = ts.toArray(new Integer[ts.size()]);
        for (int i = 0; i <integers.length ; i++) {
            if(integers[i] == amountMap.get(provinceName)){
                return integers.length-(i+1)+1+"";
            }
        }
        return null;
    }

    //根据value排序
    public static Map<String,List> sortMarketPriceMap(HashMap<String, BigDecimal> marketPriceMap) {
        List<Map.Entry<String, BigDecimal>> list = new ArrayList<>(marketPriceMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, BigDecimal>>() {
            @Override
            public int compare(Map.Entry<String, BigDecimal> o1, Map.Entry<String, BigDecimal> o2) {
                return o2.getValue().compareTo(o1.getValue());//降序
            }
        });
        List<String> industryList = new ArrayList<>();
        List<BigDecimal> martetPriceList = new ArrayList<>();
        for (Map.Entry<String,BigDecimal> entry:list) {
            industryList.add(entry.getKey());
            martetPriceList.add(entry.getValue());
        }
        if(industryList.size() > 10){
            for (int i = 9; i <industryList.size() ; i++) {
                industryList.remove(i);
            }
            industryList.add("其他");
            BigDecimal price = new BigDecimal("0.0000000000000000000000000000000");
            for (int i = 9; i < martetPriceList.size(); i++) {
                price = price.add(martetPriceList.get(i));
                martetPriceList.remove(i);
            }
            martetPriceList.add(price);
        }
        ArrayList<String> stringMarketPriceList = new ArrayList<>();
        for (BigDecimal b:martetPriceList) {
            stringMarketPriceList.add(bigDecimalConventWithRound(b.stripTrailingZeros()).replaceAll(",",""));
        }
        HashMap<String, List> resultMap = new HashMap<>();
        resultMap.put("x",industryList);
        resultMap.put("y",stringMarketPriceList);
        return resultMap;
    }

    //元与亿元转化(无四舍五入)
    public static String bigDecimalConventWithoutRound(BigDecimal result){
        result = result.multiply(new BigDecimal("0.00000001"));
        DecimalFormat df = new DecimalFormat("#,###.00");
        df.setRoundingMode(RoundingMode.FLOOR);
        String format = df.format(result.doubleValue());
        return format;
    }

    //百分比转换
    public static String percentageConvent(BigDecimal result){
        result = result.multiply(new BigDecimal("100"));
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.HALF_UP);
        String format = df.format(result.doubleValue());
        return format+"%";
    }
    //元与亿元转化(四舍五入)
    public static String bigDecimalConventWithRound(BigDecimal result) {
        result = result.multiply(new BigDecimal("0.00000001"));
        DecimalFormat df = new DecimalFormat("#,###.00");
        df.setRoundingMode(RoundingMode.HALF_UP);
        String format = df.format(result.doubleValue());
        return format;
    }

    public static void exportToPath(Template documentTemplate,Map<String, Object> dataMap, Template chart1Template,Map<String, Object> chart1DataMap,String documentOutFilePath,String chart1OutFilePath,String documentName,String chart1Name) {
        File documentFile = new File(documentOutFilePath);
        File chart1File = new File(chart1OutFilePath);
        FileOutputStream documentfos = null;
        FileOutputStream chart1fos = null;
        try {
            documentfos = new FileOutputStream(documentFile);
            chart1fos = new FileOutputStream(chart1File);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Writer documentout = new BufferedWriter(new OutputStreamWriter(documentfos),10240);
        Writer chart1out = new BufferedWriter(new OutputStreamWriter(chart1fos),10240);
        try {
            documentTemplate.process(dataMap,documentout);
            chart1Template.process(chart1DataMap,chart1out);
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(documentout != null){
            try {
                documentout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(chart1out != null){
            try {
                chart1out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ZipInputStream zipInputStream = null;
        try {
            zipInputStream = ZipUtils.wrapZipInputStream(new FileInputStream(new File(fileDirectory+"一月山东辖区上市公司动态快报.zip")));
            ZipOutputStream zipOutputStream = ZipUtils.wrapZipOutputStream(new FileOutputStream(new File(fileDirectory+"一月山东辖区上市公司动态快报.docx")));
            ZipUtils.replaceItem(zipInputStream, zipOutputStream, documentName, new FileInputStream(new File(documentOutFilePath)));
            ZipUtils.replaceItem(zipInputStream, zipOutputStream, chart1Name, new FileInputStream(new File(chart1OutFilePath)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static BigDecimal retainTwo(BigDecimal changeRatio) {
        DecimalFormat df = new DecimalFormat("0.00");
        //df.setRoundingMode(RoundingMode.HALF_UP);
        String format = df.format(changeRatio);
        new BigDecimal(format);
        return new BigDecimal(format);
    }

    public static String getRatio(Integer statisticCompanyCount, Integer companyCount) {
        BigDecimal number1 = new BigDecimal(statisticCompanyCount);
        BigDecimal number2 = new BigDecimal(companyCount);
        BigDecimal divide = number1.divide(number2, 2, RoundingMode.HALF_UP);
        BigDecimal multiply = divide.multiply(new BigDecimal(100));
        return multiply + "%";
    }
}
