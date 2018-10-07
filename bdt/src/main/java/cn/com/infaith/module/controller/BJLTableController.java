package cn.com.infaith.module.controller;

import cn.com.infaith.module.model.*;
import cn.com.infaith.module.service.BJLDataService;
import cn.com.infaith.module.service.TableDataService;
import cn.com.infaith.module.util.ResponseJsonUtil;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
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

    @ApiOperation(value = "披露添加百家乐桌面信息", notes = "披露添加百家乐桌面信息", httpMethod = "POST")
    @PostMapping("/addTableDataList")
    public JSONObject addTableDataList(@RequestBody List<TableData> tableData) {

        Boolean result = tableDataService.addTableDataList(tableData);
        if (result) {
            return ResponseJsonUtil.getResponseJson(200, "success", null);
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

    @PostMapping("/tzSystemStarted")
    @ApiOperation(value = "投注系统开关", notes = "投注系统开关", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tzxt", value = "投注系统", required = true, paramType = "query"),
            @ApiImplicitParam(name = "started", value = "开关", required = true, paramType = "query"),
            @ApiImplicitParam(name = "fh", value = "fh值，当为关闭时可不填", paramType = "query"),
            @ApiImplicitParam(name = "xh", value = "xh值，当为关闭时可不填", paramType = "query"),
            @ApiImplicitParam(name = "list", value = "账号信息list，当为关闭时可不填", paramType = "query"),
    })
    public JSONObject tzSystemStarted(@RequestParam int tzxt, @RequestParam Boolean started,
                                      @RequestParam(required = false) Integer fh,
                                      @RequestParam(required = false) String xh,
                                      @RequestParam(required = false) List<DopeManage> list) {

        if (started) {
            if (fh == null || fh == 0 || StringUtils.isBlank(xh) || CollectionUtils.isEmpty(list)) {
                return ResponseJsonUtil.getResponseJson(400, "缺少fh或xh参数", null);
            }
        }
        Boolean result = tableDataService.updateTzStartOrClose(started, tzxt, fh, xh);
        if (started) {
            list.forEach(x -> {
                Integer id = tableDataService.getDopeManageIdByTzzh(x.getTzzh());
                if (id != null) {
                    tableDataService.updateDopeManage(x);
                } else {
                    tableDataService.addDopeManage(x);
                }
            });
        }
        if (result) {
            return ResponseJsonUtil.getResponseJson(200, "SUCCESS", null);
        } else {
            return ResponseJsonUtil.getResponseJson(-1, "fail", null);
        }
    }

    @ApiOperation(value = "获取投注系统信息", notes = "获取投注系统信息", httpMethod = "GET")
    @GetMapping("/getTzSystemInfo")
    public JSONObject getTzSystemInfo(@RequestParam int tzxt) {

        JSONObject json = new JSONObject();
        TzSystem tzSystem = tableDataService.getTzSystemInfo(tzxt);
        List<DopeManage> list = tableDataService.getDopeMangeList(tzxt);
        if (tzSystem != null) {
            json.put("tzSystem", tzSystem);
            json.put("list", list);
            return ResponseJsonUtil.getResponseJson(200, "find data", json);
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
            return ResponseJsonUtil.getResponseJson(500, "报错", e.getMessage());
        }
        return ResponseJsonUtil.getResponseJson(200, "success", null);
    }

    @GetMapping("/searchTableData")
    @ApiOperation(value = "获取桌面数据", notes = "获取桌面数据", httpMethod = "POST")
    public JSONObject searchTableData(@RequestParam(required = false) Long createTime,
                                      @RequestParam(required = false) Integer tableNo,
                                      @RequestParam(required = false) Integer battleNo) {

        List<TableData> list = tableDataService.searchTableData(createTime, tableNo, battleNo);
        return ResponseJsonUtil.getResponseJson(200, "SUCCESS", list);
    }

    @GetMapping("/searchDopeData")
    @ApiOperation(value = "获取投注结果数据", notes = "获取投注结果数据", httpMethod = "POST")
    public JSONObject searchDopeData(@RequestParam(required = false) Long createTime,
                                     @RequestParam(required = false) Integer tzxt,
                                     @RequestParam(required = false) String tzzh) {

        List<ResultData> list = tableDataService.searchResultData(createTime, tzxt, tzzh);
        return ResponseJsonUtil.getResponseJson(200, "SUCCESS", list);
    }

    @PostMapping("/bdtSystemStarted")
    @ApiOperation(value = "bdt系统开关", notes = "bdt系统开关", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "started", value = "开关", required = true, paramType = "query"),
            @ApiImplicitParam(name = "ps", value = "ps值，当为关闭时可不填", paramType = "query"),
            @ApiImplicitParam(name = "phxs", value = "phxs值，当为关闭时可不填", paramType = "query"),
    })
    public JSONObject bdtSystemStarted(@RequestParam Boolean started,
                                       @RequestParam(required = false) Integer ps,
                                       @RequestParam(required = false) BigDecimal phxs) {
        Boolean result = tableDataService.bdtSystemStarted(started, ps, phxs);
        if (result) {
            return ResponseJsonUtil.getResponseJson(200, "SUCCESS", null);
        } else {
            return ResponseJsonUtil.getResponseJson(-1, "fail", null);
        }
    }

    @GetMapping("/getBdtSystemInfo")
    @ApiOperation(value = "获取bdt系统信息", notes = "获取bdt系统信息", httpMethod = "GET")
    public JSONObject getBdtSystemInfo() {

        BdtSystem bdtSystem = tableDataService.getBdtSystem();
        return ResponseJsonUtil.getResponseJson(200, "SUCCESS", bdtSystem);
    }

}
