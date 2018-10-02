package cn.com.infaith.module.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author syc
 * @date 2018/9/25 00:24
 */
@Service
public class CalcXGLZGLService {
    /**
     * @param fitNo    副号
     * @param ps       控制子系统取得参数
     * @param inputNum 牌面数据
     */
    public void calcXgl(int fitNo, int ps, String inputNum) {
        Map<String, Integer> numberMap = new HashMap<>();
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
        //步骤1
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
        //步骤2
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
        //步骤3
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
        //步骤4
        numberMap.put("s", numberMap.get("n0") + numberMap.get("n1") + numberMap.get("n2") + numberMap.get("n3") + numberMap.get("n4") + numberMap.get("n5") +
                numberMap.get("n6") + numberMap.get("n7") + numberMap.get("n8") + numberMap.get("n9"));
        /*
        步骤5，根据G、E、F，通过MA、MB、MC、MD、ME、MF计算P、W、Q。
        G为10000-19999的5位整数，A、B、C、D分别代表G的千位、百位、十位、个位上的数，E、F为0-9的1位整数，
        MA、MB、MC、MD、ME、MF为A、B、C、D、E、F的相关参数。（MA、MB、MC、MD、ME、MF均为整数）
        */
        //步骤5-1
        numberMap.put("g", 10000);
        numberMap.put("a", Integer.parseInt(String.valueOf(numberMap.get("g")).substring(1, 1)));
        numberMap.put("b", Integer.parseInt(String.valueOf(numberMap.get("g")).substring(2, 2)));
        numberMap.put("c", Integer.parseInt(String.valueOf(numberMap.get("g")).substring(3, 3)));
        numberMap.put("d", Integer.parseInt(String.valueOf(numberMap.get("g")).substring(4, 4)));
        numberMap.put("e", 0);
        numberMap.put("f", 0);
        numberMap.put("ma", 0);
        numberMap.put("mb", 0);
        numberMap.put("mc", 0);
        numberMap.put("md", 0);
        numberMap.put("me", 0);
        numberMap.put("mf", 0);
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
            } else if (right(numberMap.get("b") + numberMap.get("d")) == 0 || right(numberMap.get("b") + numberMap.get("d")) == 1 || right(numberMap.get("b") + numberMap.get("d")) == 2) {
                //5-4-5-1
                numberMap.put("e", 0);
                numberMap.put("me", x(numberMap.get("e"), numberMap));
                numberMap.put("f", 0);
                numberMap = step5_4_5_4TOstep5_4_5_7(numberMap);
            } else if (right(numberMap.get("b") + numberMap.get("d")) == 3) {
                //5-4-6-1
            } else if (right(numberMap.get("b") + numberMap.get("d")) == 4) {
                //5-4-7-1
            } else if (right(numberMap.get("b") + numberMap.get("d")) == 5) {
                //5-4-8-1
            } else if (right(numberMap.get("b") + numberMap.get("d")) == 6) {
                //5-4-9-1
            }
        } else if (right(numberMap.get("a") + numberMap.get("c")) == 6 || right(numberMap.get("a") + numberMap.get("c")) == 7) {
            //5-4-10-1
        }
        //步骤6
        /*
        步骤5-2，X(0)=N0,X(1)=N1,X(2)=N2,X(3)=N3,X(4)=N4,X(5)=N5,X(6)=N6,X(7)=N7,X(8)=N8,X(9)=N9。进入步骤5-3-1。
         */

    }

    public int right(int num) {
        String numStr = String.valueOf(num);
        return numStr.charAt(numStr.length() - 1);
    }

    public Integer x(Integer a, Map<String, Integer> numberMap) {
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

            } else if (numberMap.get("e") == 9) {
                //5-5
            }
        }
        return numberMap;
    }
}
