package cn.com.infaith.module.util;

import cn.com.infaith.module.model.ExcelData;
import cn.com.infaith.module.model.ExcelSheetData;
import com.aliyun.oss.model.ObjectMetadata;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ExcelUtil {

    /**
     * 单个，多个sheet通用
     *
     * @param excelData
     * @return
     */
    public static File toExcel(ExcelData excelData, String filePath) {
        File file = null;
        if (CollectionUtils.isEmpty(excelData.getSheetDataList())) {
            return file;
        }
        XSSFWorkbook wb = new XSSFWorkbook();
        List<ExcelSheetData> sheetDataList = excelData.getSheetDataList();
        for (int i = 0; i < sheetDataList.size(); i++) {
            ExcelSheetData sheetData = sheetDataList.get(i);
            XSSFSheet sheet = wb.createSheet(sheetData.getSheetName());
            //设置表头
            XSSFRow row = sheet.createRow(0);
            XSSFCellStyle style = wb.createCellStyle();
            //设置表头高度
            row.setHeightInPoints(40);
            //设置表头样式
            Font font = wb.createFont();
            font.setFontName("黑体");
            font.setBold(true);
            style.setFont(font);
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setVerticalAlignment(VerticalAlignment.CENTER);
            style.setFillForegroundColor(new XSSFColor(new java.awt.Color(215, 220, 228)));
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            style.setBorderBottom(BorderStyle.THIN);
            style.setBottomBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
            style.setBorderLeft(BorderStyle.THIN);
            style.setLeftBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
            style.setBorderRight(BorderStyle.THIN);
            style.setRightBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
            style.setBorderTop(BorderStyle.THIN);
            style.setRightBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
            style.setWrapText(true);
            Map<String, String> map = sheetData.getMapList().get(0);
            int titleIndex = 0;
            //设置表头
            for (String key : map.keySet()) {
                XSSFCell cell = row.createCell((short) titleIndex);
                cell.setCellValue(key);
                cell.setCellStyle(style);
//                if (!CollectionUtils.isEmpty(sheetData.getWidthMap())) {
//                    sheet.setColumnWidth(titleIndex, sheetData.getWidthMap().get(key) * 256);
//                }
                titleIndex++;
            }
            int rowIndex = 1;
            for (Map<String, String> maps : sheetData.getMapList()) {
                int valueIndex = 0;
                row = sheet.createRow(rowIndex);
                //设置高度
                row.setHeightInPoints(17);
                //创建单元格并设置值
                for (String key : maps.keySet()) {
                    Cell cellValue = row.createCell((short) valueIndex);
                    //设置值
                    cellValue.setCellValue(maps.get(key));
                    //设置样式
                    CellStyle cellStyle = wb.createCellStyle();
                    cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
//                    if (!CollectionUtils.isEmpty(sheetData.getAlignmentMap())) {
//                        cellStyle.setAlignment(sheetData.getAlignmentMap().get(key));
//                    }
                    cellValue.setCellStyle(cellStyle);
                    valueIndex++;
                }
                rowIndex++;
            }
        }
        // 第六步，将文件存到指定位置
        try {
            file = new File(filePath);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            wb.write(fileOutputStream);
            fileOutputStream.close();
            return file;
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 创建临时的excel，调用完成后建议删除
     *
     * @param mapList
     * @param sheetName
     * @return
     * @throws Exception
     */
    public static File toExcel(List<Map<String, String>> mapList, String sheetName, String filePath) throws Exception {
        if (CollectionUtils.isEmpty(mapList)) {
            return null;
        }
        // 第一步，创建一个webbook，对应一个Excel文件
        Workbook wb = new XSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        Sheet sheet = wb.createSheet(sheetName);
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        Row row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头
        CellStyle style = wb.createCellStyle();
//        style.setAlignment(HorizontalAlignment.CENTER);// 创建一个居中格式
        Font font = wb.createFont();
        font.setFontName("等线 Regular (正文)");
        font.setBold(true);
        style.setFont(font);

        Map<String, String> map = mapList.get(0);
        int titleIndex = 0;
        for (String key : map.keySet()) {
            Cell cell = row.createCell((short) titleIndex);
            cell.setCellValue(key);
            cell.setCellStyle(style);
            titleIndex++;
        }

        int rowIndex = 1;
        for (Map<String, String> maps : mapList) {
            int valueIndex = 0;
            row = sheet.createRow(rowIndex);
            //创建单元格并设置值
            for (String key : maps.keySet()) {
                Cell cellValue = row.createCell((short) valueIndex);
                cellValue.setCellValue(maps.get(key));
//                cellValue.setCellStyle(style);
                valueIndex++;
            }
            rowIndex++;
        }
        // 第六步，将文件存到指定位置
        try {
//            File file = File.createTempFile("temp", ".xlsx");
            File file = new File(filePath);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            wb.write(fileOutputStream);
            fileOutputStream.close();
            return file;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 上传excel至oss
     *
     * @param list      excel内容
     * @param excelName excel名称
     * @param sheetName sheet名称
     * @param ossKey    ossKey
     * @return
     */
    public static String uploadExcelToOSS(List<Map<String, String>> list, String excelName, String sheetName, String ossKey) {

        File file = null;
        try {
            file = ExcelUtil.toExcel(list, sheetName,"");
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentDisposition("filename=" + excelName);
            PublicUtil.uploadSingleFile(ossKey, new FileInputStream(file), metadata);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            if (file != null) {
                file.deleteOnExit();
            }
        }
        return "http://oss.in-hope.cn/" + ossKey;
    }

    public static void main(String[] args) {

        String ossKey = "gxf/shuoming/daorushuoming_shenshi.xlsx";
        File file = new File("/Users/lbj/Desktop/导入数据说明-深市 .xlsx");
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentDisposition("filename=" + "导入数据说明-深市.xlsx");
            PublicUtil.uploadSingleFile(ossKey, new FileInputStream(file), metadata);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("http://oss.in-hope.cn/" + ossKey);
    }

    /**
     * 初始化excel
     * @param filePath
     * @return
     */
    public static Workbook initExcel(String filePath) throws Exception {

        Workbook wb = null;
        if (StringUtils.isBlank(filePath)) {
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        is = new FileInputStream(filePath);
        if (".xls".equals(extString)) {
            wb = new HSSFWorkbook(is);
        } else if (".xlsx".equals(extString)) {
            wb = new XSSFWorkbook(is);
        } else {
            return null;
        }
        return wb;
    }

    /**
     * 获取excel的值
     * @param cell
     * @return
     */
    public static Object getCellFormatValue(Cell cell) {

        Object cellValue = "";
        if (cell != null) {
            try {
                switch (cell.getCellTypeEnum()) {
                    case NUMERIC:
                        BigDecimal value = new BigDecimal(cell.getNumericCellValue());
                        //默认有小数点的保存两个小数
                        if (value != null && StringUtils.isNotBlank(value.toString()) && value.toString().indexOf(".") != -1) {
                            cellValue = value.setScale(2, BigDecimal.ROUND_DOWN);
                        } else {
                            cellValue = value;
                        }
                        break;
                    case STRING:
                        cellValue = cell.getStringCellValue().toString();
                        break;
                    case FORMULA:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            cellValue = cell.getDateCellValue();
                        } else {
                            cellValue = String.valueOf(cell.getNumericCellValue());
                        }
                        break;
                    default:
                        cellValue = "";
                }
            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.error(ExcelUtil.class, "excel获取列值失败！！" + e.getMessage());
            }
        }
        return cellValue;
    }

}
