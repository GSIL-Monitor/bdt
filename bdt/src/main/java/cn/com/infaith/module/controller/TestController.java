package cn.com.infaith.module.controller;

import cn.com.infaith.module.service.CalcXGLZGLServiceNotMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author syc
 * @date 2018/9/12 11:47
 */
@RestController
public class TestController {
    public static List<CalcXGLZGLServiceNotMap> calcList = new ArrayList<>();
//    private CalcXGLZGLServiceNotMap table1Calc = new CalcXGLZGLServiceNotMap();
//    private CalcXGLZGLServiceNotMap table2Calc = new CalcXGLZGLServiceNotMap();
//    private CalcXGLZGLServiceNotMap table3Calc = new CalcXGLZGLServiceNotMap();

    static {
        calcList.add(new CalcXGLZGLServiceNotMap());
        calcList.add(new CalcXGLZGLServiceNotMap());
        calcList.add(new CalcXGLZGLServiceNotMap());
        calcList.add(new CalcXGLZGLServiceNotMap());
        calcList.add(new CalcXGLZGLServiceNotMap());
        calcList.add(new CalcXGLZGLServiceNotMap());
        calcList.add(new CalcXGLZGLServiceNotMap());
        calcList.add(new CalcXGLZGLServiceNotMap());
        calcList.add(new CalcXGLZGLServiceNotMap());
        calcList.add(new CalcXGLZGLServiceNotMap());
        calcList.add(new CalcXGLZGLServiceNotMap());
        calcList.add(new CalcXGLZGLServiceNotMap());
    }

    @RequestMapping("/test")
    public String test() {
        return "test";
    }

    @RequestMapping("/testCalc")
    public Map<String, BigDecimal> testCalc(@RequestParam("fitNo") int fitNo, @RequestParam("inputNum") String inputNum, @RequestParam("tableNo") int tableNo, @RequestParam("pshx") BigDecimal pshx) {
        if (fitNo == 1) {
            calcList.set(tableNo, new CalcXGLZGLServiceNotMap());
        }
        return calcList.get(tableNo).calcXgl(fitNo, 8, inputNum, pshx);
//        if (tableNo == 1) {
//            table1Calc.calcXgl(fitNo, 8, inputNum, pshx);
//        } else if (tableNo == 2) {
//            table2Calc.calcXgl(fitNo, 4, inputNum, pshx);
//        } else if (tableNo == 3) {
//            table3Calc.calcXgl(fitNo, 6, inputNum, pshx);
//        }

    }
}
