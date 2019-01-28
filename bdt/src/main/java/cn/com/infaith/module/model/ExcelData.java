package cn.com.infaith.module.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author lbj
 *
 */
public class ExcelData implements Serializable{

    private String excelName;

    private List<ExcelSheetData> sheetDataList;

    public String getExcelName() {
        return excelName;
    }

    public void setExcelName(String excelName) {
        this.excelName = excelName;
    }

    public List<ExcelSheetData> getSheetDataList() {
        return sheetDataList;
    }

    public void setSheetDataList(List<ExcelSheetData> sheetDataList) {
        this.sheetDataList = sheetDataList;
    }
}
