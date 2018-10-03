package cn.com.infaith.module.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/**
 * @author syc
 * @date 2018/9/25 00:24
 */
@Service
public class CalcXGLZGLService {

    public static void main(String[] args) {
        CalcXGLZGLService service = new CalcXGLZGLService();
        service.calcXgl(1, 8, "5307--");
    }

    /**
     * @param fitNo    副号
     * @param ps       控制子系统取得参数
     * @param inputNum 牌面数据
     */
    public void calcXgl(int fitNo, int ps, String inputNum) {
        Map<String, Integer> numberMap = new HashMap<>();
        numberMap = initParam(fitNo, ps, numberMap);
        //步骤1
        numberMap = step1(numberMap);
        //步骤2
        numberMap = step2(inputNum, numberMap);
        //步骤3
        numberMap = step3(numberMap);
        //步骤4
        numberMap = step4(numberMap);
        /*
        步骤5，根据G、E、F，通过MA、MB、MC、MD、ME、MF计算P、W、Q。
        G为10000-19999的5位整数，A、B、C、D分别代表G的千位、百位、十位、个位上的数，E、F为0-9的1位整数，
        MA、MB、MC、MD、ME、MF为A、B、C、D、E、F的相关参数。（MA、MB、MC、MD、ME、MF均为整数）
        */
        //步骤5-1
        numberMap = step5_1(numberMap);
        numberMap = step5_2(numberMap);

    }

    private Map<String, Integer> step5_4_10_3(Map<String, Integer> numberMap) {
        //5-4-10-3
        numberMap.put("mf", x(numberMap.get("f"), numberMap));
        //5-4-10-4
        if (numberMap.get("ma") > 0 && numberMap.get("mb") > 0 && numberMap.get("mc") > 0 && numberMap.get("md") > 0 && numberMap.get("me") > 0) {
            numberMap.put("j5", numberMap.get("ma") * numberMap.get("mb") * numberMap.get("mc") * numberMap.get("md") * numberMap.get("me"));
        } else if (numberMap.get("ma") <= 0 || numberMap.get("mb") <= 0 || numberMap.get("mc") <= 0 || numberMap.get("md") <= 0 || numberMap.get("me") <= 0) {
            numberMap.put("j5", 0);
        }
        //5-4-10-5
        if (right(numberMap.get("a") + numberMap.get("c") + numberMap.get("e")) > right(numberMap.get("b") + numberMap.get("d"))) {
            numberMap.put("p5", numberMap.get("p5") + numberMap.get("j5"));
        }
        if (right(numberMap.get("a") + numberMap.get("c") + numberMap.get("e")) == right(numberMap.get("b") + numberMap.get("d"))) {
            numberMap.put("w5", numberMap.get("w5") + numberMap.get("j5"));
        }
        if (right(numberMap.get("a") + numberMap.get("c") + numberMap.get("e")) < right(numberMap.get("b") + numberMap.get("d"))) {
            numberMap.put("q5", numberMap.get("q5") + numberMap.get("j5"));
        }
        //5-4-10-6
        if (numberMap.get("f") < 9) {
            //5-4-10-7
            numberMap.put("f", numberMap.get("f") + 1);
            //5-4-10-3
            numberMap = step5_4_10_3(numberMap);
        } else if (numberMap.get("f") == 9) {
            //5-5
            step5_5(numberMap);
        }
        return numberMap;
    }

    private Map<String, BigDecimal> step5_5(Map<String, Integer> numberMap) {
        if (numberMap.get("g") < 19999) {
            //5-6
            numberMap.put("g", numberMap.get("g") + 1);
            numberMap = step5_2(numberMap);
        } else if (numberMap.get("g") == 19999) {
            //6
            Map<String, BigDecimal> calcResultMap = step6(numberMap);
        }
        return null;
    }

    private Map<String, BigDecimal> step6(Map<String, Integer> numberMap) {
        Map<String, BigDecimal> calcResultMap = new HashMap<>();
        BigDecimal s3AddResult = new BigDecimal(numberMap.get("s")).multiply(new BigDecimal(numberMap.get("s") - 1)).multiply(new BigDecimal(numberMap.get("s") - 2)).multiply(new BigDecimal(numberMap.get("s") - 3));
        BigDecimal s4AddResult = new BigDecimal(numberMap.get("s")).multiply(new BigDecimal(numberMap.get("s") - 1)).multiply(new BigDecimal(numberMap.get("s") - 2)).multiply(new BigDecimal(numberMap.get("s") - 3)).multiply(new BigDecimal(numberMap.get("s") - 4));
        BigDecimal s5AddResult = new BigDecimal(numberMap.get("s")).multiply(new BigDecimal(numberMap.get("s") - 1)).multiply(new BigDecimal(numberMap.get("s") - 2)).multiply(new BigDecimal(numberMap.get("s") - 3)).multiply(new BigDecimal(numberMap.get("s") - 4)).multiply(new BigDecimal(numberMap.get("s") - 5));
        BigDecimal p4CalcResult = new BigDecimal(numberMap.get("p4")).multiply(new BigDecimal(100000)).divide(s3AddResult, 5, RoundingMode.HALF_UP).divide(new BigDecimal(100000), 5, RoundingMode.HALF_UP);
        BigDecimal p5CalcResult = new BigDecimal(numberMap.get("p4")).multiply(new BigDecimal(100000)).divide(s4AddResult, 5, RoundingMode.HALF_UP).divide(new BigDecimal(100000), 5, RoundingMode.HALF_UP);
        BigDecimal p6CalcResult = new BigDecimal(numberMap.get("p4")).multiply(new BigDecimal(100000)).divide(s5AddResult, 5, RoundingMode.HALF_UP).divide(new BigDecimal(100000), 5, RoundingMode.HALF_UP);
        BigDecimal q4CalcResult = new BigDecimal(numberMap.get("q4")).multiply(new BigDecimal(100000)).divide(s3AddResult, 5, RoundingMode.HALF_UP).divide(new BigDecimal(100000), 5, RoundingMode.HALF_UP);
        BigDecimal q5CalcResult = new BigDecimal(numberMap.get("q5")).multiply(new BigDecimal(100000)).divide(s4AddResult, 5, RoundingMode.HALF_UP).divide(new BigDecimal(100000), 5, RoundingMode.HALF_UP);
        BigDecimal q6CalcResult = new BigDecimal(numberMap.get("q6")).multiply(new BigDecimal(100000)).divide(s5AddResult, 5, RoundingMode.HALF_UP).divide(new BigDecimal(100000), 5, RoundingMode.HALF_UP);
        calcResultMap.put("xgl", p4CalcResult.add(p5CalcResult).add(p6CalcResult));
        calcResultMap.put("zgl", q4CalcResult.add(q5CalcResult).add(q6CalcResult));
        BigDecimal zglSubtractxglResult = calcResultMap.get("zgl").subtract(calcResultMap.get("xgl"));
        BigDecimal xglAddzgl = calcResultMap.get("xgl").add(calcResultMap.get("zgl"));
        BigDecimal xglPoint = calcResultMap.get("xgl").subtract((calcResultMap.get("zgl").multiply(new BigDecimal(0.95))));
        calcResultMap.put("xtsl", zglSubtractxglResult.divide(xglAddzgl, 5, RoundingMode.HALF_UP));
        calcResultMap.put("ztsl", xglPoint.divide(xglAddzgl));
        System.out.println(calcResultMap.get("xgl"));
        System.out.println(calcResultMap.get("zgl"));
        System.out.println(calcResultMap.get("xtsl"));
        System.out.println(calcResultMap.get("ztsl"));
        return calcResultMap;
    }

    private Map<String, Integer> step5_4_2(Map<String, Integer> numberMap) {
        //5-4-2
        if (numberMap.get("ma") > 0 && numberMap.get("mb") > 0 && numberMap.get("mc") > 0 && numberMap.get("md") > 0) {
            numberMap.put("j4", numberMap.get("ma") * numberMap.get("mb") * numberMap.get("mc") * numberMap.get("md"));
        } else if (numberMap.get("ma") <= 0 || numberMap.get("mb") <= 0 || numberMap.get("mc") <= 0 || numberMap.get("md") <= 0) {
            numberMap.put("j4", 0);
        }
        //5-4-3
        if (right(numberMap.get("a") + numberMap.get("c")) > right(numberMap.get("b") + numberMap.get("d"))) {
            numberMap.put("p4", numberMap.get("p4") + numberMap.get("j4"));
        } else if (right(numberMap.get("a") + numberMap.get("c")) == right(numberMap.get("b") + numberMap.get("d"))) {
            numberMap.put("w4", numberMap.get("w4") + numberMap.get("j4"));
        } else if (right(numberMap.get("a") + numberMap.get("c")) < right(numberMap.get("b") + numberMap.get("d"))) {
            numberMap.put("q4", numberMap.get("q4") + numberMap.get("j4"));
        }
        //5-5
        step5_5(numberMap);
        return numberMap;
    }

    private Map<String, Integer> step5_4_9_2(Map<String, Integer> numberMap) {
        numberMap.put("me", x(numberMap.get("e"), numberMap));
        //5-4-9-3
        if (numberMap.get("e") == 0 || numberMap.get("e") == 1 || numberMap.get("e") == 2 || numberMap.get("e") == 3 || numberMap.get("e") == 4 || numberMap.get("e") == 5 || numberMap.get("e") == 8 || numberMap.get("e") == 9) {
            //5-4-9-4
            if (numberMap.get("ma") > 0 && numberMap.get("mb") > 0 && numberMap.get("mc") > 0 && numberMap.get("md") > 0 && numberMap.get("me") > 0) {
                numberMap.put("j5", numberMap.get("ma") * numberMap.get("mb") * numberMap.get("mc") * numberMap.get("md") * numberMap.get("me"));
            } else if (numberMap.get("ma") <= 0 || numberMap.get("mb") <= 0 || numberMap.get("mc") <= 0 || numberMap.get("md") <= 0 || numberMap.get("me") <= 0) {
                numberMap.put("j5", 0);
            }
            //5-4-9-5
            if (right(numberMap.get("a") + numberMap.get("c") + numberMap.get("e")) > right(numberMap.get("b") + numberMap.get("d"))) {
                numberMap.put("p5", numberMap.get("p5") + numberMap.get("j5"));
            }
            if (right(numberMap.get("a") + numberMap.get("c") + numberMap.get("e")) == right(numberMap.get("b") + numberMap.get("d"))) {
                numberMap.put("w5", numberMap.get("w5") + numberMap.get("j5"));
            }
            if (right(numberMap.get("a") + numberMap.get("c") + numberMap.get("e")) < right(numberMap.get("b") + numberMap.get("d"))) {
                numberMap.put("q5", numberMap.get("q5") + numberMap.get("j5"));
            }
            //5-4-9-12
            if (numberMap.get("e") < 9) {
                //5-4-9-13
                numberMap = step5_4_9_13(numberMap);
            } else if (numberMap.get("e") == 9) {
                //5-5
                step5_5(numberMap);
            }
        } else if (numberMap.get("e") == 6 || numberMap.get("e") == 7) {
            //5-4-9-6
            numberMap.put("f", 0);
            numberMap = step5_4_9_7(numberMap);
        }
        return numberMap;
    }

    private Map<String, Integer> step5_4_9_13(Map<String, Integer> numberMap) {
        numberMap.put("e", numberMap.get("e") + 1);
        //5-4-9-2
        numberMap = step5_4_9_2(numberMap);
        return numberMap;
    }

    private Map<String, Integer> step5_4_9_7(Map<String, Integer> numberMap) {
        if (numberMap.get("f").equals(numberMap.get("e"))) {
            numberMap.put("mf", x(numberMap.get("e"), numberMap) - 1);
        } else {
            numberMap.put("mf", x(numberMap.get("f"), numberMap));
        }
        //5-4-9-8
        if (numberMap.get("ma") > 0 && numberMap.get("mb") > 0 && numberMap.get("mc") > 0 && numberMap.get("md") > 0 && numberMap.get("me") > 0 && numberMap.get("mf") > 0) {
            numberMap.put("j6", numberMap.get("ma") * numberMap.get("mb") * numberMap.get("mc") * numberMap.get("md") * numberMap.get("me") * numberMap.get("mf"));
        } else if (numberMap.get("ma") <= 0 || numberMap.get("mb") <= 0 || numberMap.get("mc") <= 0 || numberMap.get("md") <= 0 || numberMap.get("me") <= 0 || numberMap.get("mf") <= 0) {
            numberMap.put("j6", 0);
        }
        //5-4-9-9
        if (right(numberMap.get("a") + numberMap.get("c") + numberMap.get("e")) > right(numberMap.get("b") + numberMap.get("d") + numberMap.get("f"))) {
            numberMap.put("p6", numberMap.get("p6") + numberMap.get("j6"));
        }
        if (right(numberMap.get("a") + numberMap.get("c") + numberMap.get("e")) == right(numberMap.get("b") + numberMap.get("d") + numberMap.get("f"))) {
            numberMap.put("w6", numberMap.get("w6") + numberMap.get("j6"));
        }
        if (right(numberMap.get("a") + numberMap.get("c") + numberMap.get("e")) < right(numberMap.get("b") + numberMap.get("d") + numberMap.get("f"))) {
            numberMap.put("q6", numberMap.get("q6") + numberMap.get("j6"));
        }
        //5-4-9-10
        if (numberMap.get("f") < 9) {
            //5-4-9-11
            numberMap.put("f", numberMap.get("f") + 1);
            //5-4-9-7
            numberMap = step5_4_9_7(numberMap);
        } else if (numberMap.get("f") == 9) {
            //5-4-9-13
            numberMap = step5_4_9_13(numberMap);
        }
        return numberMap;
    }

    private Map<String, Integer> step5_4_8_2(Map<String, Integer> numberMap) {
        numberMap.put("me", x(numberMap.get("e"), numberMap));
        //5-4-8-3
        if (numberMap.get("e") == 0 || numberMap.get("e") == 1 || numberMap.get("e") == 2 || numberMap.get("e") == 3 || numberMap.get("e") == 8 || numberMap.get("e") == 9) {
            //5-4-8-4
            if (numberMap.get("ma") > 0 && numberMap.get("mb") > 0 && numberMap.get("mc") > 0 && numberMap.get("md") > 0 && numberMap.get("me") > 0) {
                numberMap.put("j5", numberMap.get("ma") * numberMap.get("mb") * numberMap.get("mc") * numberMap.get("md") * numberMap.get("me"));
            } else if (numberMap.get("ma") <= 0 || numberMap.get("mb") <= 0 || numberMap.get("mc") <= 0 || numberMap.get("md") <= 0 || numberMap.get("me") <= 0) {
                numberMap.put("j5", 0);
            }
            //5-4-8-5
            if (right(numberMap.get("a") + numberMap.get("c") + numberMap.get("e")) > right(numberMap.get("b") + numberMap.get("d"))) {
                numberMap.put("p5", numberMap.get("p5") + numberMap.get("j5"));
            }
            if (right(numberMap.get("a") + numberMap.get("c") + numberMap.get("e")) == right(numberMap.get("b") + numberMap.get("d"))) {
                numberMap.put("w5", numberMap.get("w5") + numberMap.get("j5"));
            }
            if (right(numberMap.get("a") + numberMap.get("c") + numberMap.get("e")) < right(numberMap.get("b") + numberMap.get("d"))) {
                numberMap.put("q5", numberMap.get("q5") + numberMap.get("j5"));
            }
            //5-4-8-12
            if (numberMap.get("e") < 9) {
                //5-4-8-13
                numberMap = step5_4_8_13(numberMap);
            } else if (numberMap.get("e") == 9) {
                //5-5
            }

        } else if (numberMap.get("e") == 4 || numberMap.get("e") == 5 || numberMap.get("e") == 6 || numberMap.get("e") == 7) {
            //5-4-8-6
            numberMap.put("f", 0);
            //5-4-8-7
            numberMap = step5_4_8_7(numberMap);
        }
        return numberMap;
    }

    private Map<String, Integer> step5_4_8_13(Map<String, Integer> numberMap) {
        numberMap.put("e", numberMap.get("e") + 1);
        numberMap = step5_4_8_2(numberMap);
        return numberMap;
    }

    private Map<String, Integer> step5_4_8_7(Map<String, Integer> numberMap) {
        if (numberMap.get("f").equals(numberMap.get("e"))) {
            numberMap.put("mf", x(numberMap.get("e") - 1, numberMap));
        } else {
            numberMap.put("mf", x(numberMap.get("f"), numberMap));
        }
        //5-4-8-8
        if (numberMap.get("ma") > 0 && numberMap.get("mb") > 0 && numberMap.get("mc") > 0 && numberMap.get("md") > 0 && numberMap.get("me") > 0 && numberMap.get("mf") > 0) {
            numberMap.put("j6", numberMap.get("ma") * numberMap.get("mb") * numberMap.get("mc") * numberMap.get("md") * numberMap.get("me") * numberMap.get("mf"));
        } else if (numberMap.get("ma") <= 0 || numberMap.get("mb") <= 0 || numberMap.get("mc") <= 0 || numberMap.get("md") <= 0 || numberMap.get("me") <= 0 || numberMap.get("mf") <= 0) {
            numberMap.put("j6", 0);
        }
        //5-4-8-9
        if (right(numberMap.get("a") + numberMap.get("c") + numberMap.get("e")) > right(numberMap.get("b") + numberMap.get("d") + numberMap.get("f"))) {
            numberMap.put("p6", numberMap.get("p6") + numberMap.get("j6"));
        }
        if (right(numberMap.get("a") + numberMap.get("c") + numberMap.get("e")) == right(numberMap.get("b") + numberMap.get("d") + numberMap.get("f"))) {
            numberMap.put("w6", numberMap.get("w6") + numberMap.get("j6"));
        }
        if (right(numberMap.get("a") + numberMap.get("c") + numberMap.get("e")) < right(numberMap.get("b") + numberMap.get("d") + numberMap.get("f"))) {
            numberMap.put("q6", numberMap.get("q6") + numberMap.get("j6"));
        }
        //5-4-8-10
        if (numberMap.get("f") < 9) {
            //5-4-8-11
            numberMap.put("f", numberMap.get("f") + 1);
            numberMap = step5_4_8_7(numberMap);
        } else if (numberMap.get("f") == 9) {
            //5-4-8-13
            numberMap = step5_4_8_13(numberMap);
        }
        return numberMap;
    }

    private Map<String, Integer> step5_4_7_3(Map<String, Integer> numberMap) {
        if (numberMap.get("e") == 0 || numberMap.get("e") == 8 || numberMap.get("e") == 9) {
            //5-4-7-4
            numberMap = step5_4_7_4(numberMap);
        } else if (numberMap.get("e") == 2 || numberMap.get("e") == 3 || numberMap.get("e") == 4 || numberMap.get("e") == 5 || numberMap.get("e") == 6 || numberMap.get("e") == 7) {
            //5-4-7-6
            numberMap = step5_4_7_6(numberMap);
        }
        return numberMap;
    }

    private Map<String, Integer> step5_4_7_6(Map<String, Integer> numberMap) {
        numberMap.put("f", 0);
        numberMap = step5_4_7_7TO5_4_7_11(numberMap);
        return numberMap;
    }

    private Map<String, Integer> step5_4_7_7TO5_4_7_11(Map<String, Integer> numberMap) {
        //5-4-7-7
        if (numberMap.get("f").equals(numberMap.get("e"))) {
            numberMap.put("mf", x(numberMap.get("e") - 1, numberMap));
        } else {
            numberMap.put("mf", x(numberMap.get("f"), numberMap));
        }
        //5-4-7-8
        if (numberMap.get("ma") > 0 && numberMap.get("mb") > 0 && numberMap.get("mc") > 0 && numberMap.get("md") > 0 && numberMap.get("me") > 0 && numberMap.get("mf") > 0) {
            numberMap.put("j6", numberMap.get("ma") * numberMap.get("mb") * numberMap.get("mc") * numberMap.get("md") * numberMap.get("me") * numberMap.get("mf"));
        } else if (numberMap.get("ma") <= 0 || numberMap.get("mb") <= 0 || numberMap.get("mc") <= 0 || numberMap.get("md") <= 0 || numberMap.get("me") <= 0 || numberMap.get("mf") <= 0) {
            numberMap.put("j6", 0);
        }
        //5-4-7-9
        if (right(numberMap.get("a") + numberMap.get("c") + numberMap.get("e")) > right(numberMap.get("b") + numberMap.get("d") + numberMap.get("f"))) {
            numberMap.put("p6", numberMap.get("p6") + numberMap.get("j6"));
        }
        if (right(numberMap.get("a") + numberMap.get("c") + numberMap.get("e")) == right(numberMap.get("b") + numberMap.get("d") + numberMap.get("f"))) {
            numberMap.put("w6", numberMap.get("w6") + numberMap.get("j6"));
        }
        if (right(numberMap.get("a") + numberMap.get("c") + numberMap.get("e")) < right(numberMap.get("b") + numberMap.get("d") + numberMap.get("f"))) {
            numberMap.put("q6", numberMap.get("q6") + numberMap.get("j6"));
        }
        //5-4-7-10
        if (numberMap.get("f") < 9) {
            //5-4-7-11
            numberMap.put("f", numberMap.get("f") + 1);
            numberMap = step5_4_7_7TO5_4_7_11(numberMap);
        } else if (numberMap.get("f") == 9) {
            //5-4-7-13
            numberMap = step5_4_7_13(numberMap);
        }
        return numberMap;
    }

    private Map<String, Integer> step5_4_7_4(Map<String, Integer> numberMap) {
        if (numberMap.get("ma") > 0 && numberMap.get("mb") > 0 && numberMap.get("mc") > 0 && numberMap.get("md") > 0 && numberMap.get("me") > 0) {
            numberMap.put("j5", numberMap.get("ma") * numberMap.get("mb") * numberMap.get("mc") * numberMap.get("md") * numberMap.get("me"));
        } else if (numberMap.get("ma") <= 0 || numberMap.get("mb") <= 0 || numberMap.get("mc") <= 0 || numberMap.get("md") <= 0 || numberMap.get("me") <= 0) {
            numberMap.put("j5", 0);
        }
        //5-4-7-5
        if (right(numberMap.get("a") + numberMap.get("c") + numberMap.get("e")) > right(numberMap.get("b") + numberMap.get("d"))) {
            numberMap.put("p5", numberMap.get("p5") + numberMap.get("j5"));
        }
        if (right(numberMap.get("a") + numberMap.get("c") + numberMap.get("e")) == right(numberMap.get("b") + numberMap.get("d"))) {
            numberMap.put("w5", numberMap.get("w5") + numberMap.get("j5"));
        }
        if (right(numberMap.get("a") + numberMap.get("c") + numberMap.get("e")) < right(numberMap.get("b") + numberMap.get("d"))) {
            numberMap.put("q5", numberMap.get("q5") + numberMap.get("j5"));
        }
        //5-4-7-12
        if (numberMap.get("e") < 9) {
            //5-4-7-13
            numberMap = step5_4_7_13(numberMap);
        } else if (numberMap.get("e") == 9) {
            //5-5
            step5_5(numberMap);
        }

        return numberMap;
    }

    private Map<String, Integer> step5_4_7_13(Map<String, Integer> numberMap) {
        numberMap.put("e", numberMap.get("e") + 1);
        //5-4-7-2
        numberMap = step5_4_7_2(numberMap);
        return numberMap;
    }


    private Map<String, Integer> step5_4_7_2(Map<String, Integer> numberMap) {
        numberMap.put("me", x(numberMap.get("e"), numberMap));
        //5-4-7-3
        numberMap = step5_4_7_3(numberMap);
        return numberMap;
    }

    private Map<String, Integer> step5_4_7_1(Map<String, Integer> numberMap) {
        numberMap.put("e", 0);
        return numberMap;
    }

    private Map<String, Integer> step5_4_6_2TOstep5_4_6_6(Map<String, Integer> numberMap) {
        numberMap.put("me", x(numberMap.get("e"), numberMap));
        //5-4-6-3
        if (numberMap.get("e") == 8) {
            //5-4-6-4
            if (numberMap.get("ma") > 0 && numberMap.get("mb") > 0 && numberMap.get("mc") > 0 && numberMap.get("md") > 0 && numberMap.get("me") > 0) {
                numberMap.put("j5", numberMap.get("ma") * numberMap.get("mb") * numberMap.get("mc") * numberMap.get("md") * numberMap.get("me"));
            } else if (numberMap.get("ma") <= 0 || numberMap.get("mb") <= 0 || numberMap.get("mc") <= 0 || numberMap.get("md") <= 0 || numberMap.get("me") <= 0) {
                numberMap.put("j5", 0);
            }
            //5-4-6-5
            if (right(numberMap.get("a") + numberMap.get("c") + numberMap.get("e")) > right(numberMap.get("b") + numberMap.get("d"))) {
                numberMap.put("p5", numberMap.get("p5") + numberMap.get("j5"));
            }
            if (right(numberMap.get("a") + numberMap.get("c") + numberMap.get("e")) == right(numberMap.get("b") + numberMap.get("d"))) {
                numberMap.put("w5", numberMap.get("w5") + numberMap.get("j5"));
            }
            if (right(numberMap.get("a") + numberMap.get("c") + numberMap.get("e")) < right(numberMap.get("b") + numberMap.get("d"))) {
                numberMap.put("q5", numberMap.get("q5") + numberMap.get("j5"));
            }
            //5-4-6-13
            numberMap.put("e", numberMap.get("e") + 1);
            //5-4-6-2
            numberMap = step5_4_6_2TOstep5_4_6_6(numberMap);
        } else if (numberMap.get("e") == 0 || numberMap.get("e") == 1 || numberMap.get("e") == 2 || numberMap.get("e") == 3 || numberMap.get("e") == 4 || numberMap.get("e") == 5 || numberMap.get("e") == 6 || numberMap.get("e") == 7 || numberMap.get("e") == 9) {
            //5-4-6-6
            numberMap.put("f", 0);
            numberMap = step5_4_6_7TOstep5_4_6_10(numberMap);
        }
        return numberMap;
    }

    private Map<String, Integer> step5_2(Map<String, Integer> numberMap) {
        //步骤5-2
        numberMap.put("x0", numberMap.get("n0"));
        numberMap.put("x1", numberMap.get("n1"));
        numberMap.put("x2", numberMap.get("n2"));
        numberMap.put("x3", numberMap.get("n3"));
        numberMap.put("x4", numberMap.get("n4"));
        numberMap.put("x5", numberMap.get("n5"));
        numberMap.put("x6", numberMap.get("n6"));
        numberMap.put("x7", numberMap.get("n7"));
        numberMap.put("x8", numberMap.get("n8"));
        numberMap.put("x9", numberMap.get("n9"));
        //步骤5-3
        //步骤5-3-1
        //5-3-2
        numberMap.put("ma", x(numberMap.get("a"), numberMap));
        //5-3-3
        //5-3-4
        numberMap.put("mb", x(numberMap.get("b"), numberMap));
        //5-3-5
        //5-3-6
        numberMap.put("mc", x(numberMap.get("c"), numberMap));
        //5-3-7
        //5-3-8
        numberMap.put("md", x(numberMap.get("d"), numberMap));
        //步骤5-4
        //5-4-1
        if (right(numberMap.get("a") + numberMap.get("c")) == 8 || right(numberMap.get("a") + numberMap.get("c")) == 9) {
            numberMap = step5_4_2(numberMap);
        } else if (right(numberMap.get("a") + numberMap.get("c")) == 0 || right(numberMap.get("a") + numberMap.get("c")) == 1 || right(numberMap.get("a") + numberMap.get("c")) == 2 || right(numberMap.get("a") + numberMap.get("c")) == 3 || right(numberMap.get("a") + numberMap.get("c")) == 4 || right(numberMap.get("a") + numberMap.get("c")) == 5) {
            //5-4-4
            if (right(numberMap.get("b") + numberMap.get("d")) == 7 || right(numberMap.get("b") + numberMap.get("d")) == 8 || right(numberMap.get("b") + numberMap.get("d")) == 9) {
                //5-4-2
                if (numberMap.get("ma") > 0 && numberMap.get("mb") > 0 && numberMap.get("mc") > 0 && numberMap.get("md") > 0) {
                    numberMap.put("j4", numberMap.get("ma") * numberMap.get("mb") * numberMap.get("mc") * numberMap.get("md"));
                } else if (numberMap.get("ma") <= 0 || numberMap.get("mb") <= 0 || numberMap.get("mc") <= 0 || numberMap.get("md") <= 0) {
                    numberMap.put("j4", 0);
                }
                //5-4-3
                if (right(numberMap.get("a") + numberMap.get("c")) > right(numberMap.get("b") + numberMap.get("d"))) {
                    numberMap.put("p4", numberMap.get("p4") + numberMap.get("j4"));
                } else if (right(numberMap.get("a") + numberMap.get("c")) == right(numberMap.get("b") + numberMap.get("d"))) {
                    numberMap.put("w4", numberMap.get("w4") + numberMap.get("j4"));
                } else if (right(numberMap.get("a") + numberMap.get("c")) < right(numberMap.get("b") + numberMap.get("d"))) {
                    numberMap.put("q4", numberMap.get("q4") + numberMap.get("j4"));
                }
                //5-5
                step5_5(numberMap);
            } else if (right(numberMap.get("b") + numberMap.get("d")) == 0 || right(numberMap.get("b") + numberMap.get("d")) == 1 || right(numberMap.get("b") + numberMap.get("d")) == 2) {
                //5-4-5-1
                numberMap.put("e", 0);
                numberMap = step5_4_5_2TOstep5_4_5_3(numberMap);
                numberMap = step5_4_5_4TOstep5_4_5_7(numberMap);
            } else if (right(numberMap.get("b") + numberMap.get("d")) == 3) {
                //5-4-6-1
                numberMap.put("e", 0);
                //5-4-6-2
                numberMap = step5_4_6_2TOstep5_4_6_6(numberMap);
            } else if (right(numberMap.get("b") + numberMap.get("d")) == 4) {
                //5-4-7-1
                numberMap = step5_4_7_1(numberMap);
                //5-4-7-2
                numberMap = step5_4_7_2(numberMap);

            } else if (right(numberMap.get("b") + numberMap.get("d")) == 5) {
                //5-4-8-1
                numberMap.put("e", 0);
                //5-4-8-2
                numberMap = step5_4_8_2(numberMap);
            } else if (right(numberMap.get("b") + numberMap.get("d")) == 6) {
                //5-4-9-1
                numberMap.put("e", 0);
                numberMap = step5_4_9_2(numberMap);
            }
        } else if (right(numberMap.get("a") + numberMap.get("c")) == 6 || right(numberMap.get("a") + numberMap.get("c")) == 7) {
            //5-4-10-1
            if (right(numberMap.get("b") + numberMap.get("d")) == 6 || right(numberMap.get("b") + numberMap.get("d")) == 7 || right(numberMap.get("b") + numberMap.get("d")) == 8 || right(numberMap.get("b") + numberMap.get("d")) == 9) {
                //5-4-2
                numberMap = step5_4_2(numberMap);
            } else if (right(numberMap.get("b") + numberMap.get("d")) == 0 || right(numberMap.get("b") + numberMap.get("d")) == 1 || right(numberMap.get("b") + numberMap.get("d")) == 2 || right(numberMap.get("b") + numberMap.get("d")) == 3 || right(numberMap.get("b") + numberMap.get("d")) == 4 || right(numberMap.get("b") + numberMap.get("d")) == 5) {
                //5-4-10-2
                numberMap.put("f", 0);
                numberMap = step5_4_10_3(numberMap);
            }
        }
//        //步骤6
//        step6(numberMap);
        return numberMap;
    }

    private Map<String, Integer> step5_1(Map<String, Integer> numberMap) {
        numberMap.put("g", 10000);
        numberMap.put("a", Integer.parseInt(String.valueOf(numberMap.get("g")).substring(1, 2)));
        numberMap.put("b", Integer.parseInt(String.valueOf(numberMap.get("g")).substring(2, 3)));
        numberMap.put("c", Integer.parseInt(String.valueOf(numberMap.get("g")).substring(3, 4)));
        numberMap.put("d", Integer.parseInt(String.valueOf(numberMap.get("g")).substring(4)));
        numberMap.put("e", 0);
        numberMap.put("f", 0);
        numberMap.put("ma", 0);
        numberMap.put("mb", 0);
        numberMap.put("mc", 0);
        numberMap.put("md", 0);
        numberMap.put("me", 0);
        numberMap.put("mf", 0);
        return numberMap;
    }

    private Map<String, Integer> step4(Map<String, Integer> numberMap) {
        numberMap.put("s", numberMap.get("n0") + numberMap.get("n1") + numberMap.get("n2") + numberMap.get("n3") + numberMap.get("n4") + numberMap.get("n5") +
                numberMap.get("n6") + numberMap.get("n7") + numberMap.get("n8") + numberMap.get("n9"));
        return numberMap;
    }

    private Map<String, Integer> step3(Map<String, Integer> numberMap) {
        numberMap.put("n0", numberMap.get("n0") - numberMap.get("k0"));
        numberMap.put("n1", numberMap.get("n1") - numberMap.get("k1"));
        numberMap.put("n2", numberMap.get("n2") - numberMap.get("k2"));
        numberMap.put("n3", numberMap.get("n3") - numberMap.get("k3"));
        numberMap.put("n4", numberMap.get("n4") - numberMap.get("k4"));
        numberMap.put("n5", numberMap.get("n5") - numberMap.get("k5"));
        numberMap.put("n6", numberMap.get("n6") - numberMap.get("k6"));
        numberMap.put("n7", numberMap.get("n7") - numberMap.get("k7"));
        numberMap.put("n8", numberMap.get("n8") - numberMap.get("k8"));
        numberMap.put("n9", numberMap.get("n9") - numberMap.get("k9"));
        return numberMap;
    }

    private Map<String, Integer> step2(String inputNum, Map<String, Integer> numberMap) {
        numberMap.put("k0", StringUtils.countMatches(inputNum, "0"));
        numberMap.put("k1", StringUtils.countMatches(inputNum, "1"));
        numberMap.put("k2", StringUtils.countMatches(inputNum, "2"));
        numberMap.put("k3", StringUtils.countMatches(inputNum, "3"));
        numberMap.put("k4", StringUtils.countMatches(inputNum, "4"));
        numberMap.put("k5", StringUtils.countMatches(inputNum, "5"));
        numberMap.put("k6", StringUtils.countMatches(inputNum, "6"));
        numberMap.put("k7", StringUtils.countMatches(inputNum, "7"));
        numberMap.put("k8", StringUtils.countMatches(inputNum, "8"));
        numberMap.put("k9", StringUtils.countMatches(inputNum, "9"));
        return numberMap;
    }

    private Map<String, Integer> step1(Map<String, Integer> numberMap) {
        numberMap.put("j4", 0);
        numberMap.put("j5", 0);
        numberMap.put("j6", 0);
        numberMap.put("p4", 0);
        numberMap.put("p5", 0);
        numberMap.put("p6", 0);
        numberMap.put("q4", 0);
        numberMap.put("q5", 0);
        numberMap.put("q6", 0);
        numberMap.put("w4", 0);
        numberMap.put("w5", 0);
        numberMap.put("w6", 0);
        return numberMap;
    }

    private Map<String, Integer> initParam(int fitNo, int ps, Map<String, Integer> numberMap) {
        numberMap.put("n0", 0);
        numberMap.put("n1", 0);
        numberMap.put("n2", 0);
        numberMap.put("n3", 0);
        numberMap.put("n4", 0);
        numberMap.put("n5", 0);
        numberMap.put("n6", 0);
        numberMap.put("n7", 0);
        numberMap.put("n8", 0);
        numberMap.put("n9", 0);
        //副号=1
        if (fitNo == 1) {
            numberMap.put("n0", ps * 16);
            numberMap.put("n1", ps * 4);
            numberMap.put("n2", ps * 4);
            numberMap.put("n3", ps * 4);
            numberMap.put("n4", ps * 4);
            numberMap.put("n5", ps * 4);
            numberMap.put("n6", ps * 4);
            numberMap.put("n7", ps * 4);
            numberMap.put("n8", ps * 4);
            numberMap.put("n9", ps * 4);
        }
        return numberMap;
    }

    private Map<String, Integer> step5_4_5_2TOstep5_4_5_3(Map<String, Integer> numberMap) {
        //5-4-5-2
        numberMap.put("me", x(numberMap.get("e"), numberMap));
        //5-4-5-3
        numberMap.put("f", 0);
        return numberMap;
    }

    public int right(int num) {
        String numStr = String.valueOf(num);
        return Integer.parseInt(String.valueOf(numStr.charAt(numStr.length() - 1)));
    }

    private Integer x(Integer a, Map<String, Integer> numberMap) {
        int num = 0;
        if (a != null) {
            switch (a) {
                case 0:
                    num = numberMap.get("n0");
                    return num;
                case 1:
                    num = numberMap.get("n1");
                    return num;
                case 2:
                    num = numberMap.get("n2");
                    return num;
                case 3:
                    num = numberMap.get("n3");
                    return num;
                case 4:
                    num = numberMap.get("n4");
                    return num;
                case 5:
                    num = numberMap.get("n5");
                    return num;
                case 6:
                    num = numberMap.get("n6");
                    return num;
                case 7:
                    num = numberMap.get("n7");
                    return num;
                case 8:
                    num = numberMap.get("n8");
                    return num;
                case 9:
                    num = numberMap.get("n9");
                    return num;
            }
        }
        return num;
    }
    private Integer xAndUpdate(Integer a, Map<String, Integer> numberMap) {
        int num = 0;
        if (a != null) {
            switch (a) {
                case 0:
                    num = numberMap.get("n0");
                    numberMap.put("n0", num - 1);
                    return num;
                case 1:
                    num = numberMap.get("n1");
                    numberMap.put("n1", num - 1);
                    return num;
                case 2:
                    num = numberMap.get("n2");
                    numberMap.put("n2", num - 1);
                    return num;
                case 3:
                    num = numberMap.get("n3");
                    numberMap.put("n3", num - 1);
                    return num;
                case 4:
                    num = numberMap.get("n4");
                    numberMap.put("n4", num - 1);
                    return num;
                case 5:
                    num = numberMap.get("n5");
                    numberMap.put("n5", num - 1);
                    return num;
                case 6:
                    num = numberMap.get("n6");
                    numberMap.put("n6", num - 1);
                    return num;
                case 7:
                    num = numberMap.get("n7");
                    numberMap.put("n7", num - 1);
                    return num;
                case 8:
                    num = numberMap.get("n8");
                    numberMap.put("n8", num - 1);
                    return num;
                case 9:
                    num = numberMap.get("n9");
                    numberMap.put("n9", num - 1);
                    return num;
            }
        }
        return num;
    }

    public Map<String, Integer> step5_4_5_4TOstep5_4_5_7(Map<String, Integer> numberMap) {
        //5-4-5-4
        if (numberMap.get("f").equals(numberMap.get("e"))) {
            numberMap.put("mf", x(numberMap.get("e"), numberMap) - 1);
        } else if (numberMap.get("f") > numberMap.get("e") || numberMap.get("f") < numberMap.get("e")) {
            numberMap.put("mf", x(numberMap.get("f"), numberMap));
        }
        //5-4-5-5
        if (numberMap.get("ma") > 0 && numberMap.get("mb") > 0 && numberMap.get("mc") > 0 && numberMap.get("md") > 0 && numberMap.get("me") > 0 && numberMap.get("mf") > 0) {
            numberMap.put("j6", numberMap.get("ma") * numberMap.get("mb") * numberMap.get("mc") * numberMap.get("md") * numberMap.get("me") * numberMap.get("mf"));
        } else if (numberMap.get("ma") <= 0 && numberMap.get("mb") <= 0 && numberMap.get("mc") <= 0 && numberMap.get("md") <= 0 && numberMap.get("me") <= 0 && numberMap.get("mf") <= 0) {
            numberMap.put("j6", 0);
        }
        //5-4-5-6
        if (right(numberMap.get("a") + numberMap.get("c") + numberMap.get("e")) > right(numberMap.get("b") + numberMap.get("d") + numberMap.get("f"))) {
            numberMap.put("p6", numberMap.get("p6") + numberMap.get("j6"));
        }
        if (right(numberMap.get("a") + numberMap.get("c") + numberMap.get("e")) == right(numberMap.get("b") + numberMap.get("d") + numberMap.get("f"))) {
            numberMap.put("w6", numberMap.get("w6") + numberMap.get("j6"));
        }
        if (right(numberMap.get("a") + numberMap.get("c") + numberMap.get("e")) < right(numberMap.get("b") + numberMap.get("d") + numberMap.get("f"))) {
            numberMap.put("q6", numberMap.get("q6") + numberMap.get("j6"));
        }
        //5-4-5-7
        if (numberMap.get("f") < 9) {
            //5-4-5-8
            numberMap.put("f", numberMap.get("f") + 1);
            //5-4-5-4
            numberMap = step5_4_5_4TOstep5_4_5_7(numberMap);
        } else if (numberMap.get("f") == 9) {
            //5-4-5-9
            if (numberMap.get("e") < 9) {
                //5-4-5-10
                numberMap.put("e", numberMap.get("e") + 1);
                //5-4-5-2
                numberMap = step5_4_5_2TOstep5_4_5_3(numberMap);
            } else if (numberMap.get("e") == 9) {
                //5-5
                step5_5(numberMap);
            }
        }
        return numberMap;
    }

    public Map<String, Integer> step5_4_6_7TOstep5_4_6_10(Map<String, Integer> numberMap) {
        //5-4-6-7
        if (numberMap.get("f").equals(numberMap.get("e"))) {
            numberMap.put("mf", x(numberMap.get("e"), numberMap) - 1);
        } else {
            numberMap.put("mf", x(numberMap.get("f"), numberMap));
        }
        //5-4-6-8
        if (numberMap.get("ma") > 0 && numberMap.get("mb") > 0 && numberMap.get("mc") > 0 && numberMap.get("md") > 0 && numberMap.get("me") > 0 && numberMap.get("mf") > 0) {
            numberMap.put("j6", numberMap.get("ma") * numberMap.get("mb") * numberMap.get("mc") * numberMap.get("md") * numberMap.get("me"));
        } else if (numberMap.get("ma") <= 0 || numberMap.get("mb") <= 0 || numberMap.get("mc") <= 0 || numberMap.get("md") <= 0 || numberMap.get("me") <= 0 || numberMap.get("mf") <= 0) {
            numberMap.put("j6", 0);
        }
        //5-4-6-9
        if (right(numberMap.get("a") + numberMap.get("c") + numberMap.get("e")) > right(numberMap.get("b") + numberMap.get("d") + numberMap.get("f"))) {
            numberMap.put("p6", numberMap.get("p6") + numberMap.get("j6"));
        }
        if (right(numberMap.get("a") + numberMap.get("c") + numberMap.get("e")) == right(numberMap.get("b") + numberMap.get("d") + numberMap.get("f"))) {
            numberMap.put("w6", numberMap.get("w6") + numberMap.get("j6"));
        }
        if (right(numberMap.get("a") + numberMap.get("c") + numberMap.get("e")) < right(numberMap.get("b") + numberMap.get("d") + numberMap.get("f"))) {
            numberMap.put("q6", numberMap.get("q6") + numberMap.get("j6"));
        }
        //5-4-6-10
        if (numberMap.get("f") < 9) {
            //5-4-6-11
            numberMap.put("f", numberMap.get("f") + 1);
            numberMap = step5_4_6_7TOstep5_4_6_10(numberMap);
        } else if (numberMap.get("f") == 9) {
            //5-4-6-12
            if (numberMap.get("e") < 9) {
                //5-4-6-13
                numberMap.put("e", numberMap.get("e") + 1);
                //5-4-6-2
                numberMap = step5_4_6_2TOstep5_4_6_6(numberMap);
            } else if (numberMap.get("e") == 9) {
                //5-5
                step5_5(numberMap);
            }
        }
        return numberMap;
    }
}
