package cn.com.infaith.module.controller;

import cn.com.infaith.module.enums.TableStatusEnum;
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
import org.docx4j.wml.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import scala.Int;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bjlTable")
@CrossOrigin
public class BJLTableController {

    @Autowired
    private TableDataService tableDataService;
    @Autowired
    private BJLDataService bjlDataService;

    @ApiOperation(value = "读取百家乐桌面信息", notes = "读取百家乐桌面信息", httpMethod = "POST")
    @PostMapping("/addTableData")
    public JSONObject addTableData(@ModelAttribute TableData tableData) {
        synchronized (this) {
            BdtSystem bdtSystem = tableDataService.getBdtSystem();
            if (!bdtSystem.getStarted()) {
                return ResponseJsonUtil.getResponseJson(-1, "系统暂未启动", null);
            }
            if (!tableData.getFitNo().equals(1)) {
                int count = tableDataService.getCountFirstFitByTable(tableData.getTableNo(), tableData.getBattleNo());
                if (count == 0) {
                    return ResponseJsonUtil.getResponseJson(-2, "未获取到当前桌当前局的第一副牌信息", null);
                }
            }
            if (tableData.getStatus().equals(TableStatusEnum.KP.getIndex()) && tableData.getResult() == null) {
                return ResponseJsonUtil.getResponseJson(-1, "未获取开牌结果", null);
            }
            try {
                bjlDataService.JudgeState(tableData);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseJsonUtil.getResponseJson(500, "fail", e.getMessage());
            }
            return ResponseJsonUtil.getResponseJson(200, "success", 1);
        }
    }
//
//    @ApiOperation(value = "披露添加百家乐桌面信息", notes = "披露添加百家乐桌面信息", httpMethod = "POST")
//    @PostMapping("/addTableDataList")
//    public JSONObject addTableDataList(@RequestBody List<TableData> tableData) {
//
//        Boolean result = tableDataService.addTableDataList(tableData);
////        tableData.stream().forEach(x -> {
////            bjlDataService.JudgeState(x);
////        });
//        if (result != null && result) {
//            return ResponseJsonUtil.getResponseJson(200, "success", null);
//        }
//        return ResponseJsonUtil.getResponseJson(-1, "fail", null);
//    }
//
//    @PostMapping("/initTest")
//    public JSONObject initTest() {
//
//        bjlDataService.initTableData(1);
//        return ResponseJsonUtil.getResponseJson(200, "SUCCESS", null);
//    }

    @ApiOperation(value = "获取百家乐牌桌情况", notes = "获取百家乐牌桌情况", httpMethod = "GET")
    @GetMapping("/getTableInfo")
    public JSONObject getTableInfo() {

        List<StatusData> list = tableDataService.selectStatusAll();
        TzSystem tzSystem1 = tableDataService.getTzSystemInfo(1);
        TzSystem tzSystem2 = tableDataService.getTzSystemInfo(2);
        long date = 15 * 60 * 1000;
        String tz = "TZ1(" + (tzSystem1.getStarted()?"开启":"关闭") + ")" + " " + "TZ2(" + (tzSystem2.getStarted()?"开启":"关闭") + ")";
        if (CollectionUtils.isNotEmpty(list)) {
            list.stream().forEach(statusData -> {
                //如果15分钟未进入读牌接口里
                if ((Calendar.getInstance().getTime().getTime() - statusData.getUpdateTime().getTime() > date)) {
                    statusData.setResult("正在等待读取新局第一副牌...");
                } else {
                    if (statusData.getStatus().equals(TableStatusEnum.NEW.getIndex())) {
                        statusData.setResult("无");
                    } else if (statusData.getStatus().equals(TableStatusEnum.TZ.getIndex())) {
                        statusData.setResult(tz);
                    } else {
                        String card = tableDataService.getCardTable(statusData.getTableNo(), statusData.getBattleNo(), statusData.getFitNo());
                        statusData.setResult(StringUtils.isBlank(card) ? "" : card);
                    }
                }
            });
        }
        if (list == null) {
            list = new ArrayList<>();
        }
        List<Integer> tableNoList = list.stream().map(StatusData::getTableNo).collect(Collectors.toList());
        Integer[] tableNoArr = {1,2,3,4,5,6,7,8,9,10,11,12};
        for (int i = 0; i < tableNoArr.length; i++) {
            if (tableNoList.contains(tableNoArr[i])) {
                continue;
            } else {
                StatusData statusData = new StatusData();
                statusData.setTableNo(tableNoArr[i]);
                statusData.setResult("正在等待读取新局第一副牌...");
                list.add(statusData);
            }
        }
        list = list.stream().sorted(Comparator.comparing(StatusData::getTableNo)).collect(Collectors.toList());
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
    public JSONObject tzSystemStarted(@RequestBody TzInfo tzInfo) {

        if (tzInfo.getStarted()) {
            if (tzInfo.getFh() == null || tzInfo.getFh() == 0 || StringUtils.isBlank(tzInfo.getXh()) || CollectionUtils.isEmpty(tzInfo.getList())) {
                return ResponseJsonUtil.getResponseJson(400, "缺少fh或xh参数", null);
            }
        }
        Boolean result = tableDataService.updateTzStartOrClose(tzInfo.getStarted(), tzInfo.getTzxt(), tzInfo.getFh(), tzInfo.getXh());
        if (tzInfo.getStarted()) {
            tzInfo.getList().forEach(x -> {
                Integer id = tableDataService.getDopeManageIdByTzzh(x.getTzzh(), tzInfo.getTzxt());
                if (id != null) {
                    x.setId(id);
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tzxt", value = "投注系统", required = true, paramType = "query"),
    })
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

    @GetMapping("/searchTableData")
    @ApiOperation(value = "获取桌面数据", notes = "获取桌面数据", httpMethod = "POST")
    public JSONObject searchTableData(@RequestParam(required = false) Long createTime,
                                      @RequestParam(required = false) Integer tableNo,
                                      @RequestParam(required = false) Integer battleNo,
                                      Integer pageNum, Integer pageSize) {

        return tableDataService.searchTableData(createTime, tableNo, battleNo, pageNum, pageSize);
    }

    @GetMapping("/searchDopeData")
    @ApiOperation(value = "获取投注结果数据", notes = "获取投注结果数据", httpMethod = "POST")
    public JSONObject searchDopeData(@RequestParam(required = false) Long createTime,
                                     @RequestParam(required = false) Integer tzxt,
                                     @RequestParam(required = false) String tzzh,
                                     Integer pageNum, Integer pageSize) {

        return tableDataService.searchResultData(createTime, tzxt, tzzh, pageNum, pageSize);
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
        BigDecimal yxje = tableDataService.getTotalYxje(null,null,null);
        BigDecimal yssy = tableDataService.getTotalYssy(null,null,null);
        BigDecimal sjsy = tableDataService.getTotalSjsy(null,null,null);
        bdtSystem.setYxje(yxje);
        bdtSystem.setYsje(yssy);
        bdtSystem.setSjje(sjsy);
        return ResponseJsonUtil.getResponseJson(200, "SUCCESS", bdtSystem);
    }

    @GetMapping("/getLJInfo")
    @ApiOperation(value = "获取累计数据xjz、zjz", notes = "获取累计数据xjz、zjz", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "开始时间，不传则默认当天", paramType = "query"),
            @ApiImplicitParam(name = "endTime", value = "结束时间，不传则默认当天", paramType = "query"),
    })
    public JSONObject getLJInfo(@RequestParam(required = false) Long startTime,
                                @RequestParam(required = false) Long endTime) {

        JSONObject json = new JSONObject();
        List<Map<Integer, String>> ljxjz = tableDataService.getLJXJZ(startTime, endTime);
        List<Map<Integer, String>> ljzjz = tableDataService.getLJZJZ(startTime, endTime);
        json.put("ljxjz", ljxjz);
        json.put("ljzjz", ljzjz);
        return ResponseJsonUtil.getResponseJson(200, "success", json);
    }

    @PostMapping("/addResultData")
    @ApiOperation(value = "添加投注结果信息", notes = "添加投注信息", httpMethod = "POST")
    public JSONObject addResultData(@ModelAttribute ResultData resultData) {

        Boolean result = tableDataService.addResultData(resultData);
        if (result) {
            ResponseJsonUtil.getResponseJson(200, "SUCCESS", null);
        }
        return ResponseJsonUtil.getResponseJson(-1, "fail", null);
    }

    @GetMapping("/getNeedTzDataList")
    @ApiOperation(value = "获取需要投注的投注信息", notes = "获取需要投注的投注信息", httpMethod = "POST")
    public JSONObject getNeedTzDataList(@RequestParam(required = false) Integer tableNo) {

        List<ResultData> list = tableDataService.getNeedTzDataList(tableNo);
        if (CollectionUtils.isEmpty(list)) {
            return ResponseJsonUtil.getResponseJson(-1, "没有需要投注的信息", null);
        } else {
            return ResponseJsonUtil.getResponseJson(200, "find list", list);
        }
    }

    @PostMapping("/updateTzztList")
    @ApiOperation(value = "批量更新投注状态", notes = "批量更新投注状态", httpMethod = "POST")
    public JSONObject updateTzztList(@RequestBody List<ResultData> list) {
        int count = tableDataService.updateTzztList(list);
        if (count == 0) {
            return ResponseJsonUtil.getResponseJson(-1, "fail", null);
        } else {
            return ResponseJsonUtil.getResponseJson(200, "SUCCESS", null);
        }
    }
}
