package cn.com.infaith.module.controller;

import cn.com.infaith.module.service.CalcXGLZGLServiceNotMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author syc
 * @date 2018/9/12 11:47
 */
@RestController
public class TestController {
    private CalcXGLZGLServiceNotMap table1Calc = new CalcXGLZGLServiceNotMap();
    private CalcXGLZGLServiceNotMap table2Calc = new CalcXGLZGLServiceNotMap();
    private CalcXGLZGLServiceNotMap table3Calc = new CalcXGLZGLServiceNotMap();

    @RequestMapping("/test")
    public String test() {
        return "test";
    }

    @RequestMapping("/testCalc")
    public void testCalc(@RequestParam("fitNo") int fitNo, @RequestParam("inputNum") String inputNum, @RequestParam("tableNo") int tableNo) {
        if (tableNo == 1) {
            table1Calc.calcXgl(fitNo, 8, inputNum);
        } else if (tableNo == 2) {
            table2Calc.calcXgl(fitNo, 4, inputNum);
        } else if (tableNo == 3) {
            table3Calc.calcXgl(fitNo, 6, inputNum);
        }

    }
}
