package cn.com.infaith.module.controller;

import cn.com.infaith.module.mapper.TableDataMapper;
import cn.com.infaith.module.model.TableData;
import cn.com.infaith.module.util.ResponseJsonUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class BJLController {
    @Autowired
    private TableDataMapper tableDataMapper;

    @GetMapping("/getAccount")
    public JSONObject getAccount() {
        JSONObject json = new JSONObject();
        json.put("username", "");
        return json;
    }

    @PostMapping("/saveCardRecord")
    public JSONObject saveCardRecord(@ModelAttribute TableData tableData) {
        int result = tableDataMapper.insert(tableData);
        if (result == 1) {
            return ResponseJsonUtil.getResponseJson(1, "存储信息成功！", null);
        }
        return ResponseJsonUtil.getResponseJson(1, "存储信息失败！", null);
    }

}