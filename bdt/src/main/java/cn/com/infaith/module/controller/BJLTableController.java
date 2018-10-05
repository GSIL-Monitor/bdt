package cn.com.infaith.module.controller;

import cn.com.infaith.module.model.DopeData;
import cn.com.infaith.module.model.TableData;
import cn.com.infaith.module.model.TzSystem;
import cn.com.infaith.module.service.BJLDataService;
import cn.com.infaith.module.service.TableDataService;
import cn.com.infaith.module.util.ResponseJsonUtil;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/bjlTable")
@CrossOrigin
public class BJLTableController {

    @Autowired
    private TableDataService tableDataService;
    @Autowired
    private BJLDataService bjlDataService;

    @ApiOperation(value = "添加百家乐桌面信息", notes = "添加百家乐桌面信息", httpMethod = "POST")
    @PostMapping("/addTableData")
    public JSONObject addTableData(@ModelAttribute TableData tableData) {

        int id = tableDataService.addTableData(tableData);
        if (id != 0) {
            tableData.setId(id);
            return ResponseJsonUtil.getResponseJson(200, "success", tableData);
        }
        return ResponseJsonUtil.getResponseJson(-1, "fail", null);
    }

    @PostMapping("/initTest")
    public JSONObject initTest() {

        bjlDataService.initTableData();
        return ResponseJsonUtil.getResponseJson(200, "SUCCESS", null);
    }

    @ApiOperation(value = "获取百家乐牌桌情况", notes = "获取百家乐牌桌情况", httpMethod = "GET")
    @GetMapping("/getTableInfo")
    public JSONObject getTableInfo() {

        List<TableData> list = tableDataService.getTableInfo();
        return ResponseJsonUtil.getResponseJson(200, "SUCCESS", list);
    }

    @ApiOperation(value = "投注系统开关", notes = "投注系统开关", httpMethod = "POST")
    @PostMapping("/tzSystemStarted")
    public JSONObject tzSystemStarted(@RequestParam int tzxt, @RequestParam Boolean started,
                                      @RequestParam(required = false) Integer fh,
                                      @RequestParam(required = false) String xh) {

        if (started) {
            if (fh == null || fh == 0 || StringUtils.isBlank(xh)) {
                return ResponseJsonUtil.getResponseJson(400,"缺少fh或xh参数",null);
            }
        }
        Boolean result = tableDataService.updateTzStartOrClose(started, tzxt, fh, xh);
        if (result) {
            return ResponseJsonUtil.getResponseJson(200, "SUCCESS", null);
        } else {
            return ResponseJsonUtil.getResponseJson(-1, "fail", null);
        }
    }

    @ApiOperation(value = "获取投注系统信息", notes = "获取投注系统信息", httpMethod = "GET")
    @GetMapping("/getTzSystemInfo")
    public JSONObject getTzSystemInfo(int tzxt) {

        TzSystem tzSystem = tableDataService.getTzSystemInfo(tzxt);
        if (tzSystem != null) {
            return ResponseJsonUtil.getResponseJson(200, "find data", tzSystem);
        } else {
            return ResponseJsonUtil.getResponseJson(404, "not find", null);
        }
    }

    @ApiOperation(value = "开牌状态接口", notes = "开牌状态接口", httpMethod = "POST")
    @PostMapping("/openCard")
    public JSONObject openCard(@RequestParam TableData tableData,
                               @RequestParam BigDecimal phxs,
                               @RequestParam List<DopeData> list) {

        try {
            bjlDataService.openCard(tableData, phxs, list);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseJsonUtil.getResponseJson(500,"报错",e.getMessage());
        }
        return ResponseJsonUtil.getResponseJson(200,"success",null);
    }
}