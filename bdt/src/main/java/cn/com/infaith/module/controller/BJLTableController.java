package cn.com.infaith.module.controller;

import cn.com.infaith.module.model.TableData;
import cn.com.infaith.module.service.BJLDataService;
import cn.com.infaith.module.service.TableDataService;
import cn.com.infaith.module.util.ResponseJsonUtil;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
