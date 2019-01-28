package cn.com.infaith.module.model;

import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author lbj
 *
 */
public class ExcelSheetData implements Serializable {

    private String sheetName;

    private List<Map<String, String>> mapList;

    private Map<String, Integer> widthMap;

    private Map<String, HorizontalAlignment> alignmentMap;

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public List<Map<String, String>> getMapList() {
        return mapList;
    }

    public void setMapList(List<Map<String, String>> mapList) {
        this.mapList = mapList;
    }

    public Map<String, Integer> getWidthMap() {
        return widthMap;
    }

    public void setWidthMap(Map<String, Integer> widthMap) {
        this.widthMap = widthMap;
    }

    public Map<String, HorizontalAlignment> getAlignmentMap() {
        return alignmentMap;
    }

    public void setAlignmentMap(Map<String, HorizontalAlignment> alignmentMap) {
        this.alignmentMap = alignmentMap;
    }
}
