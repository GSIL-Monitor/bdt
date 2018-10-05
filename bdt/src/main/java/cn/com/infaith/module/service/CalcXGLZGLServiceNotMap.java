package cn.com.infaith.module.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

/**
 * @author syc
 * @date 2018/9/25 00:24
 */
@Service
public class CalcXGLZGLServiceNotMap {
    private int n0 = 0, n1 = 0, n2 = 0, n3 = 0, n4 = 0, n5 = 0, n6 = 0, n7 = 0, n8 = 0, n9 = 0;
    private int k0 = 0, k1 = 0, k2 = 0, k3 = 0, k4 = 0, k5 = 0, k6 = 0, k7 = 0, k8 = 0, k9 = 0;
    private int x0 = 0, x1 = 0, x2 = 0, x3 = 0, x4 = 0, x5 = 0, x6 = 0, x7 = 0, x8 = 0, x9 = 0;
    private BigDecimal j4 = BigDecimal.ZERO, j5 = BigDecimal.ZERO, j6 = BigDecimal.ZERO, p4 = BigDecimal.ZERO, p5 = BigDecimal.ZERO, p6 = BigDecimal.ZERO, q4 = BigDecimal.ZERO, q5 = BigDecimal.ZERO, q6 = BigDecimal.ZERO, w4 = BigDecimal.ZERO, w5 = BigDecimal.ZERO, w6 = BigDecimal.ZERO;
    private int s = 0, e = 0, f = 0;
    private BigDecimal ma = BigDecimal.ZERO, mb = BigDecimal.ZERO, mc = BigDecimal.ZERO, md = BigDecimal.ZERO, me = BigDecimal.ZERO, mf = BigDecimal.ZERO;
    private int g = 10000;
    //    private int xa = 0, xb = 0, xc = 0, xd = 0,xe = 0, xf = 0,;
    private int a = 0, b = 0, c = 0, d = 0;
    private int[] X = new int[10];


    public static void main(String[] args) {
        CalcXGLZGLServiceNotMap service = new CalcXGLZGLServiceNotMap();
        synchronized (service) {
            service.calcXgl(1, 8, "5307--");
            service.calcXgl(2, 8, "5090--");
            service.calcXgl(3, 8, "009640");
            service.calcXgl(4, 8, "204693");
            service.calcXgl(5, 8, "50052");
        }
//        System.out.println(new BigDecimal(127).multiply(new BigDecimal(126)).multiply(new BigDecimal(125)).multiply(new BigDecimal(124)).multiply(new BigDecimal(32)).multiply(new BigDecimal(31)));
//        System.out.println(127 * 126 * 125 * 124 * 32 * 31);
//        System.out.println(12345 % 10000 / 1000);
    }

    /**
     * @param fitNo    副号
     * @param ps       控制子系统取得参数
     * @param inputNum 牌面数据
     */
    public void calcXgl(int fitNo, int ps, String inputNum) {
        synchronized (this) {
            startCalcSystem(fitNo, ps, inputNum);
        }
    }

    private void startCalcSystem(int fitNo, int ps, String inputNum) {
        //副号=1
        if (fitNo == 1) {
            n0 = ps * 16;
            n1 = ps * 4;
            n2 = ps * 4;
            n3 = ps * 4;
            n4 = ps * 4;
            n5 = ps * 4;
            n6 = ps * 4;
            n7 = ps * 4;
            n8 = ps * 4;
            n9 = ps * 4;
        }
        step1(inputNum);
    }

    private void step1(String inputNum) {
        j4 = BigDecimal.ZERO;
        j5 = BigDecimal.ZERO;
        j6 = BigDecimal.ZERO;
        p4 = BigDecimal.ZERO;
        p5 = BigDecimal.ZERO;
        p6 = BigDecimal.ZERO;
        q4 = BigDecimal.ZERO;
        q5 = BigDecimal.ZERO;
        q6 = BigDecimal.ZERO;
        w4 = BigDecimal.ZERO;
        w5 = BigDecimal.ZERO;
        w6 = BigDecimal.ZERO;
        step2(inputNum);
    }

    private void step2(String inputNum) {
        k0 = StringUtils.countMatches(inputNum, "0");
        k1 = StringUtils.countMatches(inputNum, "1");
        k2 = StringUtils.countMatches(inputNum, "2");
        k3 = StringUtils.countMatches(inputNum, "3");
        k4 = StringUtils.countMatches(inputNum, "4");
        k5 = StringUtils.countMatches(inputNum, "5");
        k6 = StringUtils.countMatches(inputNum, "6");
        k7 = StringUtils.countMatches(inputNum, "7");
        k8 = StringUtils.countMatches(inputNum, "8");
        k9 = StringUtils.countMatches(inputNum, "9");
        step3();
    }

    private void step3() {
        n0 = n0 - k0;
        n1 = n1 - k1;
        n2 = n2 - k2;
        n3 = n3 - k3;
        n4 = n4 - k4;
        n5 = n5 - k5;
        n6 = n6 - k6;
        n7 = n7 - k7;
        n8 = n8 - k8;
        n9 = n9 - k9;
        step4();
    }

    private void step4() {
        s = n0 + n1 + n2 + n3 + n4 + n5 +
                n6 + n7 + n8 + n9;
        step5_1();
    }

    private void step5_1() {
        g = 10000;
        ma = BigDecimal.ZERO;
        mb = BigDecimal.ZERO;
        mc = BigDecimal.ZERO;
        md = BigDecimal.ZERO;
        me = BigDecimal.ZERO;
        mf = BigDecimal.ZERO;
        step5_2();
    }

    private void step5_2() {
        //步骤5-2
        X[0] = n0;
        X[1] = n1;
        X[2] = n2;
        X[3] = n3;
        X[4] = n4;
        X[5] = n5;
        X[6] = n6;
        X[7] = n7;
        X[8] = n8;
        X[9] = n9;
        a = g % 10000 / 1000;
        b = g % 1000 / 100;
        c = g % 100 / 10;
        d = g % 10;
        //步骤5-3
        step5_3();
    }

    private void step5_3() {
        //步骤5-3-1
        //5-3-2
        ma = new BigDecimal(X[a]);
        X[a]--;
        //5-3-3
        //5-3-4
        mb = new BigDecimal(X[b]);
        X[b]--;
        //5-3-5
        //5-3-6
        mc = new BigDecimal(X[c]);
        X[c]--;
        //5-3-7
        //5-3-8
        md = new BigDecimal(X[d]);
        X[d]--;
        step5_4_1();
    }

    private void step5_4_1() {
        //步骤5-4
        //5-4-1
        if (right(a + c) == 8 || right(a + c) == 9) {
            step5_4_2();
        } else if (right(a + c) == 0 || right(a + c) == 1 || right(a + c) == 2 || right(a + c) == 3 || right(a + c) == 4 || right(a + c) == 5) {
            step5_4_4();
        } else if (right(a + c) == 6 || right(a + c) == 7) {
            step5_4_10_1();
        }
    }

    private void step5_4_2() {
        //5-4-2
        if (ma.intValue() > 0 && mb.intValue() > 0 && mc.intValue() > 0 && md.intValue() > 0) {
            j4 = ma.multiply(mb).multiply(mc).multiply(md);
        } else if (ma.intValue() <= 0 || mb.intValue() <= 0 || mc.intValue() <= 0 || md.intValue() <= 0) {
            j4 = BigDecimal.ZERO;
        }
        step_5_4_3();
    }

    private void step_5_4_3() {
        //5-4-3
        if (right(a + c) > right(b + d)) {
            p4 = p4.add(j4);
        } else if (right(a + c) == right(b + d)) {
            w4 = w4.add(j4);
        } else if (right(a + c) < right(b + d)) {
            q4 = q4.add(j4);
        }
        //5-5
        step5_5();
    }

    private void step5_4_4() {
        //5-4-4
        if (right(b + d) == 8 || right(b + d) == 9) {
            step5_4_2();
        } else if (right(b + d) == 0 || right(b + d) == 1 || right(b + d) == 2) {
            step5_4_5_1();
        } else if (right(b + d) == 3) {
            step_5_4_6_1();
        } else if (right(b + d) == 4) {
            //5-4-7-1
            step5_4_7_1();
        } else if (right(b + d) == 5) {
            step5_4_8_1();
        } else if (right(b + d) == 6) {
            step5_4_9_1();
        } else if (right(b + d) == 7) {
            step5_4_11_1();
        }
    }

    private void step5_4_5_1() {
        //5-4-5-1
        e = 0;
        step5_4_5_2();
    }


    private void step5_4_5_2() {
        //5-4-5-2
        me = new BigDecimal(X[e]);
        step5_4_5_3();
    }

    private void step5_4_5_3() {
        //5-4-5-3
        f = 0;
        step5_4_5_4();
    }

    private void step5_4_5_4() {
        //5-4-5-4
        if (f == e) {
            mf = new BigDecimal(X[e] - 1);
        } else if (f > e || f < e) {
            mf = new BigDecimal(X[f]);
        }
        step5_4_5_5();
    }

    private void step5_4_5_5() {
        //5-4-5-5
        if (ma.intValue() > 0 && mb.intValue() > 0 && mc.intValue() > 0 && md.intValue() > 0 && me.intValue() > 0 && mf.intValue() > 0) {
            j6 = ma.multiply(mb).multiply(mc).multiply(md).multiply(me).multiply(mf);
        } else if (ma.intValue() <= 0 && mb.intValue() <= 0 && mc.intValue() <= 0 && md.intValue() <= 0 && me.intValue() <= 0 && mf.intValue() <= 0) {
            j6 = BigDecimal.ZERO;
        }
        step5_4_5_6();
    }

    private void step5_4_5_6() {
        //5-4-5-6
        if (right(a + c + e) > right(b + d + f)) {
            p6 = p6.add(j6);
        }
        if (right(a + c + e) == right(b + d + f)) {
            w6 = w6.add(j6);
        }
        if (right(a + c + e) < right(b + d + f)) {
            q6 = q6.add(j6);
        }
        step5_4_5_7();
    }

    private void step5_4_5_7() {
        //5-4-5-7
        if (f < 9) {
            step5_4_5_8();
        } else if (f == 9) {
            step5_4_5_9();
        }
    }

    private void step5_4_5_8() {
        //5-4-5-8
        f = f + 1;
        step5_4_5_4();
    }

    private void step5_4_5_9() {
        //5-4-5-9
        if (e < 9) {
            step5_4_5_10();
        } else if (e == 9) {
            //5-5
            step5_5();
        }
    }

    private void step5_4_5_10() {
        //5-4-5-10
        e = e + 1;
        //5-4-5-2
        step5_4_5_2();
    }

    private void step_5_4_6_1() {
        //5-4-6-1
        e = 0;
        //5-4-6-2
        step5_4_6_2();
    }

    private void step5_4_6_2() {
        //5-4-6-2
        me = new BigDecimal(X[e]);
        step5_4_6_3();
    }

    private void step5_4_6_3() {
        //5-4-6-3
        if (e == 8) {
            step5_4_6_4();
        } else if (e == 0 || e == 1 || e == 2 || e == 3 || e == 4 || e == 5 || e == 6 || e == 7 || e == 9) {
            step5_4_6_6();
        }

    }

    private void step5_4_6_4() {
        //5-4-6-4
        if (ma.intValue() > 0 && mb.intValue() > 0 && mc.intValue() > 0 && md.intValue() > 0 && me.intValue() > 0) {
            j5 = ma.multiply(mb).multiply(mc).multiply(md).multiply(me);
        } else if (ma.intValue() <= 0 || mb.intValue() <= 0 || mc.intValue() <= 0 || md.intValue() <= 0 || me.intValue() <= 0) {
            j5 = BigDecimal.ZERO;
        }
        step5_4_6_5();
    }

    private void step5_4_6_5() {
        //5-4-6-5
        if (right(a + c + e) > right(b + d)) {
            p5 = p5.add(j5);
        }
        if (right(a + c + e) == right(b + d)) {
            w5 = w5.add(j5);
        }
        if (right(a + c + e) < right(b + d)) {
            q5 = q5.add(j5);
        }
//        if (g == 10060) {
//            System.out.println("step5_4_6_5");
//            System.out.println("a" + a + "b" + b + "c" + c + "d" + d);
//            System.out.println(g);
//            System.out.println("ma" + ma + "mb" + mb + "mc" + mc + "md" + md + "me" + me + "mf" + mf);
//            System.out.println(ma.multiply(mb).multiply(mc).multiply(md).multiply(me).multiply(mf));
//            System.out.println("p4:" + p4 + "p5:" + p5 + "p6:" + p6);
//            System.out.println("q4:" + q4 + "q5:" + q5 + "q6:" + q6);
//            System.out.println(s);
//        }
        step5_4_6_13();
    }

    private void step5_4_6_6() {
        //5-4-6-6
        f = 0;
        step5_4_6_7();
    }

    private void step5_4_6_7() {
        //5-4-6-7
        if (f == e) {
            mf = new BigDecimal(X[e] - 1);
        } else {
            mf = new BigDecimal(X[f]);
        }
        step5_4_6_8();
    }

    private void step5_4_6_8() {
        //5-4-6-8
        if (ma.intValue() > 0 && mb.intValue() > 0 && mc.intValue() > 0 && md.intValue() > 0 && me.intValue() > 0 && mf.intValue() > 0) {
            j6 = ma.multiply(mb).multiply(mc).multiply(md).multiply(me).multiply(mf);
        } else if (ma.intValue() <= 0 || mb.intValue() <= 0 || mc.intValue() <= 0 || md.intValue() <= 0 || me.intValue() <= 0 || mf.intValue() <= 0) {
            j6 = BigDecimal.ZERO;
        }
        step5_4_6_9();
    }

    private void step5_4_6_9() {
        //5-4-6-9
        if (right(a + c + e) > right(b + d + f)) {
            p6 = p6.add(j6);
        }
        if (right(a + c + e) == right(b + d + f)) {
            w6 = w6.add(j6);
        }
        if (right(a + c + e) < right(b + d + f)) {
            q6 = q6.add(j6);
        }
        step5_4_6_10();
    }

    private void step5_4_6_10() {
        //5-4-6-10
        if (f < 9) {
            step5_4_6_11();
        } else if (f == 9) {
            step5_4_6_12();
        }
    }

    private void step5_4_6_11() {
        //5-4-6-11
        f = f + 1;
        step5_4_6_7();
    }

    private void step5_4_6_12() {
        //5-4-6-12
        if (e < 9) {
            step5_4_6_13();
        } else if (e == 9) {
            //5-5
            step5_5();
        }
    }

    private void step5_4_6_13() {
        //5-4-6-13
        e = e + 1;
        //5-4-6-2
        step5_4_6_2();
    }

    private void step5_4_7_1() {
        e = 0;
        //5-4-7-2
        step5_4_7_2();
    }

    private void step5_4_7_2() {
        me = new BigDecimal(X[e]);
        //5-4-7-3
        step5_4_7_3();
    }

    private void step5_4_7_3() {
        if (e == 0 || e == 1 || e == 8 || e == 9) {
            //5-4-7-4
            step5_4_7_4();
        } else if (e == 2 || e == 3 || e == 4 || e == 5 || e == 6 || e == 7) {
            //5-4-7-6
            step5_4_7_6();
        }
    }

    private void step5_4_7_4() {
        if (ma.intValue() > 0 && mb.intValue() > 0 && mc.intValue() > 0 && md.intValue() > 0 && me.intValue() > 0) {
            j5 = ma.multiply(mb).multiply(mc).multiply(md).multiply(me);
        } else if (ma.intValue() <= 0 || mb.intValue() <= 0 || mc.intValue() <= 0 || md.intValue() <= 0 || me.intValue() <= 0) {
            j5 = BigDecimal.ZERO;
        }
        step5_4_7_5();
    }

    private void step5_4_7_5() {
        //5-4-7-5
        if (right(a + c + e) > right(b + d)) {
            p5 = p5.add(j5);
        }
        if (right(a + c + e) == right(b + d)) {
            w5 = w5.add(j5);
        }
        if (right(a + c + e) < right(b + d)) {
            q5 = q5.add(j5);
        }
//        if (g == 10060) {
//            System.out.println("step5_4_7_5");
//            System.out.println("a" + a + "b" + b + "c" + c + "d" + d);
//            System.out.println(g);
//            System.out.println("ma" + ma + "mb" + mb + "mc" + mc + "md" + md + "me" + me + "mf" + mf);
//            System.out.println(ma.multiply(mb).multiply(mc).multiply(md).multiply(me).multiply(mf));
//            System.out.println("p4:" + p4 + "p5:" + p5 + "p6:" + p6);
//            System.out.println("q4:" + q4 + "q5:" + q5 + "q6:" + q6);
//            System.out.println(s);
//        }
        step5_4_7_12();
    }

    private void step5_4_7_6() {
        f = 0;
        step5_4_7_7();
    }

    private void step5_4_7_7() {
        //5-4-7-7
        if (f == e) {
            mf = new BigDecimal(X[e] - 1);
        } else {
            mf = new BigDecimal(X[f]);
        }
        step5_4_7_8();
    }

    private void step5_4_7_8() {
        //5-4-7-8
        if (ma.intValue() > 0 && mb.intValue() > 0 && mc.intValue() > 0 && md.intValue() > 0 && me.intValue() > 0 && mf.intValue() > 0) {
            j6 = ma.multiply(mb).multiply(mc).multiply(md).multiply(me).multiply(mf);
        } else if (ma.intValue() <= 0 || mb.intValue() <= 0 || mc.intValue() <= 0 || md.intValue() <= 0 || me.intValue() <= 0 || mf.intValue() <= 0) {
            j6 = BigDecimal.ZERO;
        }
        step5_4_7_9();

    }

    private void step5_4_7_9() {
        //5-4-7-9
        if (right(a + c + e) > right(b + d + f)) {
            p6 = p6.add(j6);
        }
        if (right(a + c + e) == right(b + d + f)) {
            w6 = w6.add(j6);
        }
        if (right(a + c + e) < right(b + d + f)) {
            q6 = q6.add(j6);
        }
        step5_4_7_10();
    }

    private void step5_4_7_10() {
        //5-4-7-10
        if (f < 9) {
            step5_4_7_11();
        } else if (f == 9) {
            //5-4-7-13
            step5_4_7_13();
        }
    }

    private void step5_4_7_11() {
        //5-4-7-11
        f = f + 1;
        step5_4_7_7();
    }


    private void step5_4_7_12() {
        //5-4-7-12
        if (e < 9) {
            //5-4-7-13
            step5_4_7_13();
        } else if (e == 9) {
            //5-5
            step5_5();
        }
    }

    private void step5_4_7_13() {
        e = e + 1;
        //5-4-7-2
        step5_4_7_2();
    }

    private void step5_4_8_1() {
        //5-4-8-1
        e = 0;
        //5-4-8-2
        step5_4_8_2();
    }

    private void step5_4_8_2() {
        me = new BigDecimal(X[e]);
        step5_4_8_3();
    }

    private void step5_4_8_3() {//not
        //5-4-8-3
        if (e == 0 || e == 1 || e == 2 || e == 3 || e == 8 || e == 9) {
            step5_4_8_4();
        } else if (e == 4 || e == 5 || e == 6 || e == 7) {
            step5_4_8_6();
        }
    }

    private void step5_4_8_4() {
        //5-4-8-4
        if (ma.intValue() > 0 && mb.intValue() > 0 && mc.intValue() > 0 && md.intValue() > 0 && me.intValue() > 0) {
            j5 = ma.multiply(mb).multiply(mc).multiply(md).multiply(me);
        } else if (ma.intValue() <= 0 || mb.intValue() <= 0 || mc.intValue() <= 0 || md.intValue() <= 0 || me.intValue() <= 0) {
            j5 = BigDecimal.ZERO;
        }
        step5_4_8_5();

    }

    private void step5_4_8_5() {
        //5-4-8-5
        if (right(a + c + e) > right(b + d)) {
            p5 = p5.add(j5);
        }
        if (right(a + c + e) == right(b + d)) {
            w5 = w5.add(j5);
        }
        if (right(a + c + e) < right(b + d)) {
            q5 = q5.add(j5);
        }
//        if (g == 10060) {
//            System.out.println("step5_4_8_5");
//            System.out.println("a" + a + "b" + b + "c" + c + "d" + d);
//            System.out.println(g);
//            System.out.println("ma" + ma + "mb" + mb + "mc" + mc + "md" + md + "me" + me + "mf" + mf);
//            System.out.println(ma.multiply(mb).multiply(mc).multiply(md).multiply(me).multiply(mf));
//            System.out.println("p4:" + p4 + "p5:" + p5 + "p6:" + p6);
//            System.out.println("q4:" + q4 + "q5:" + q5 + "q6:" + q6);
//            System.out.println(s);
//        }
        step5_4_8_12();
    }

    private void step5_4_8_6() {
        //5-4-8-6
        f = 0;
        //5-4-8-7
        step5_4_8_7();
    }

    private void step5_4_8_7() {
        if (f == e) {
            mf = new BigDecimal(X[e] - 1);
        } else {
            mf = new BigDecimal(X[f]);
        }
        step5_4_8_8();
    }

    private void step5_4_8_8() {
        //5-4-8-8
        if (ma.intValue() > 0 && mb.intValue() > 0 && mc.intValue() > 0 && md.intValue() > 0 && me.intValue() > 0 && mf.intValue() > 0) {
            j6 = ma.multiply(mb).multiply(mc).multiply(md).multiply(me).multiply(mf);
        } else if (ma.intValue() <= 0 || mb.intValue() <= 0 || mc.intValue() <= 0 || md.intValue() <= 0 || me.intValue() <= 0 || mf.intValue() <= 0) {
            j6 = BigDecimal.ZERO;
        }
        step5_4_8_9();
    }

    private void step5_4_8_9() {
        //5-4-8-9
        if (right(a + c + e) > right(b + d + f)) {
            p6 = p6.add(j6);
        }
        if (right(a + c + e) == right(b + d + f)) {
            w6 = w6.add(j6);
        }
        if (right(a + c + e) < right(b + d + f)) {
            q6 = q6.add(j6);
        }
        step5_4_8_10();
    }

    private void step5_4_8_10() {
        //5-4-8-10
        if (f < 9) {
            step_5_4_8_11();
        } else if (f == 9) {
            //5-4-8-13
            step5_4_8_13();
        }
    }

    private void step_5_4_8_11() {
        //5-4-8-11
        f = f + 1;
        step5_4_8_7();
    }

    private void step5_4_8_12() {
        //5-4-8-12
        if (e < 9) {
            //5-4-8-13
            step5_4_8_13();
        } else if (e == 9) {
            //5-5
            step5_5();
        }
    }

    private void step5_4_8_13() {
        e = e + 1;
        step5_4_8_2();
    }

    private void step5_4_9_1() {
        //5-4-9-1
        e = 0;
        step5_4_9_2();
    }

    private void step5_4_9_2() {
        me = new BigDecimal(X[e]);
        step5_4_9_3();
    }

    private void step5_4_9_3() {
        //5-4-9-3
        if (e == 0 || e == 1 || e == 2 || e == 3 || e == 4 || e == 5 || e == 8 || e == 9) {
            step5_4_9_4();
        } else if (e == 6 || e == 7) {
            step5_4_9_6();
        }
    }

    private void step5_4_9_4() {
        //5-4-9-4
        if (ma.intValue() > 0 && mb.intValue() > 0 && mc.intValue() > 0 && md.intValue() > 0 && me.intValue() > 0) {
            j5 = ma.multiply(mb).multiply(mc).multiply(md).multiply(me);
        } else if (ma.intValue() <= 0 || mb.intValue() <= 0 || mc.intValue() <= 0 || md.intValue() <= 0 || me.intValue() <= 0) {
            j5 = BigDecimal.ZERO;
        }
        step5_4_9_5();
    }

    private void step5_4_9_5() {
        //5-4-9-5
        if (right(a + c + e) > right(b + d)) {
            p5 = p5.add(j5);
        }
        if (right(a + c + e) == right(b + d)) {
            w5 = w5.add(j5);
        }
        if (right(a + c + e) < right(b + d)) {
            q5 = q5.add(j5);
        }
//        if (g == 10060) {
//            System.out.println("step5_4_9_5");
//            System.out.println("a" + a + "b" + b + "c" + c + "d" + d);
//            System.out.println(g);
//            System.out.println("ma" + ma + "mb" + mb + "mc" + mc + "md" + md + "me" + me + "mf" + mf);
//            System.out.println(ma.multiply(mb).multiply(mc).multiply(md).multiply(me).multiply(mf));
//            System.out.println("p4:" + p4 + "p5:" + p5 + "p6:" + p6);
//            System.out.println("q4:" + q4 + "q5:" + q5 + "q6:" + q6);
//            System.out.println(s);
//        }
        step5_4_9_12();
    }

    private void step5_4_9_6() {
        //5-4-9-6
        f = 0;
        step5_4_9_7();
    }

    private void step5_4_9_7() {
        if (f == e) {
            mf = new BigDecimal(X[e] - 1);
        } else {
            mf = new BigDecimal(X[f]);
        }
        step5_4_9_8();
    }

    private void step5_4_9_8() {
        //5-4-9-8
        if (ma.intValue() > 0 && mb.intValue() > 0 && mc.intValue() > 0 && md.intValue() > 0 && me.intValue() > 0 && mf.intValue() > 0) {
            j6 = ma.multiply(mb).multiply(mc).multiply(md).multiply(me).multiply(mf);
        } else if (ma.intValue() <= 0 || mb.intValue() <= 0 || mc.intValue() <= 0 || md.intValue() <= 0 || me.intValue() <= 0 || mf.intValue() <= 0) {
            j6 = BigDecimal.ZERO;
        }
        step5_4_9_9();
    }

    private void step5_4_9_9() {
        //5-4-9-9
        if (right(a + c + e) > right(b + d + f)) {
            p6 = p6.add(j6);
        }
        if (right(a + c + e) == right(b + d + f)) {
            w6 = w6.add(j6);
        }
        if (right(a + c + e) < right(b + d + f)) {
            q6 = q6.add(j6);
        }
        step5_4_9_10();
    }

    private void step5_4_9_10() {
        //5-4-9-10
        if (f < 9) {
            step5_4_9_11();
        } else if (f == 9) {
            //5-4-9-13
            step5_4_9_13();
        }
    }

    private void step5_4_9_11() {
        //5-4-9-11
        f = f + 1;
        //5-4-9-7
        step5_4_9_7();
    }

    private void step5_4_9_12() {
        //5-4-9-12
        if (e < 9) {
            //5-4-9-13
            step5_4_9_13();
        } else if (e == 9) {
            //5-5
            step5_5();
        }
    }

    private void step5_4_9_13() {
        e = e + 1;
        //5-4-9-2
        step5_4_9_2();
    }

    private void step5_4_10_1() {
        //5-4-10-1
        if (right(b + d) == 6 || right(b + d) == 7 || right(b + d) == 8 || right(b + d) == 9) {
            //5-4-2
            step5_4_2();
        } else if (right(b + d) == 0 || right(b + d) == 1 || right(b + d) == 2 || right(b + d) == 3 || right(b + d) == 4 || right(b + d) == 5) {
            step5_4_10_2();
        }
    }

    private void step5_4_10_2() {
        //5-4-10-2
        f = 0;
        step5_4_10_3();
    }

    private void step5_4_10_3() {
        //5-4-10-3
        mf = new BigDecimal(X[f]);
        step5_4_10_4();

    }

    private void step5_4_10_4() {
        //5-4-10-4
        if (ma.intValue() > 0 && mb.intValue() > 0 && mc.intValue() > 0 && md.intValue() > 0 && mf.intValue() > 0) {
            j5 = ma.multiply(mb).multiply(mc).multiply(md).multiply(mf);
        } else if (ma.intValue() <= 0 || mb.intValue() <= 0 || mc.intValue() <= 0 || md.intValue() <= 0 || mf.intValue() <= 0) {
            j5 = BigDecimal.ZERO;
        }
        step5_4_10_5();
    }

    private void step5_4_10_5() {
//        if (g == 10060) {
//            System.out.println("step5_4_10_5");
//            System.out.println("j5=" + j5);
//            System.out.println("a" + a + "b" + b + "c" + c + "d" + d);
//            System.out.println(g);
//            System.out.println("ma" + ma + "mb" + mb + "mc" + mc + "md" + md + "me" + me + "mf" + mf);
//            System.out.println(ma.multiply(mb).multiply(mc).multiply(md).multiply(me).multiply(mf));
//            System.out.println("p4:" + p4 + "p5:" + p5 + "p6:" + p6);
//            System.out.println("q4:" + q4 + "q5:" + q5 + "q6:" + q6);
//            System.out.println(s);
//        }
        //5-4-10-5
        if (right(a + c) > right(b + d + f)) {
            p5 = p5.add(j5);
        }
        if (right(a + c) == right(b + d + f)) {
            w5 = w5.add(j5);
        }
        if (right(a + c) < right(b + d + f)) {
            q5 = q5.add(j5);
        }

        step5_4_10_6();
    }

    private void step5_4_10_6() {
        //5-4-10-6
        if (f < 9) {
            step_5_4_10_7();
        } else if (f == 9) {
            //5-5
            step5_5();
        }
    }

    private void step_5_4_10_7() {
        //5-4-10-7
        f = f + 1;
        //5-4-10-3
        step5_4_10_3();
    }

    private void step5_4_11_1() {
        e = 0;
        step5_4_11_2();
    }

    private void step5_4_11_2() {
        me = new BigDecimal(X[e]);
        step5_4_11_3();
    }

    private void step5_4_11_3() {
        if (ma.intValue() > 0 && mb.intValue() > 0 && mc.intValue() > 0 && md.intValue() > 0 && me.intValue() > 0) {
            j5 = ma.multiply(mb).multiply(mc).multiply(md).multiply(me);
        } else {
            j5 = BigDecimal.ZERO;
        }
        step5_4_11_4();
    }

    private void step5_4_11_4() {
        if (right(a + c + e) > right(b + d)) {
            p5 = p5.add(j5);
        } else if (right(a + c + e) == right(b + d)) {
            w5 = w5.add(j5);
        } else if (right(a + c + e) < right(b + d)) {
            q5 = q5.add(j5);
        }
//        if (g == 10060) {
//            System.out.println("step5_4_11_4");
//            System.out.println("a" + a + "b" + b + "c" + c + "d" + d);
//            System.out.println(g);
//            System.out.println("ma" + ma + "mb" + mb + "mc" + mc + "md" + md + "me" + me + "mf" + mf);
//            System.out.println(ma.multiply(mb).multiply(mc).multiply(md).multiply(me).multiply(mf));
//            System.out.println("p4:" + p4 + "p5:" + p5 + "p6:" + p6);
//            System.out.println("q4:" + q4 + "q5:" + q5 + "q6:" + q6);
//            System.out.println(s);
//        }
        step5_4_11_5();
    }

    private void step5_4_11_5() {
        if (e < 9) {
            step5_4_11_6();
        } else if (e == 9) {
            step5_5();
        }
    }

    private void step5_4_11_6() {
        e = e + 1;
        step5_4_11_2();
    }

    private void step5_5() {
//        if (g == 10060) {
//            System.out.println("a" + a + "b" + b + "c" + c + "d" + d);
//            System.out.println(g);
//            System.out.println("ma" + ma + "mb" + mb + "mc" + mc + "md" + md + "me" + me + "mf" + mf);
//            System.out.println(ma.multiply(mb).multiply(mc).multiply(md).multiply(me).multiply(mf));
//            System.out.println("p4:" + p4 + "p5:" + p5 + "p6:" + p6);
//            System.out.println("q4:" + q4 + "q5:" + q5 + "q6:" + q6);
//            System.out.println(s);
//        }
        if (g < 19999) {
            step5_6();
        } else if (g == 19999) {
            //6
            step6();
        }
    }

    private void step5_6() {
        //5-6
        g = g + 1;
        step5_2();
    }

    private void step6() {
        System.out.println("=====");
//        System.out.println(g);
//        System.out.println("ma" + ma + "mb" + mb + "mc" + mc + "md" + md + "me" + me + "mf" + mf);
//        System.out.println(ma.multiply(mb).multiply(mc).multiply(md).multiply(me).multiply(mf));
//        System.out.println("p4:" + p4 + "p5:" + p5 + "p6:" + p6);
//        System.out.println("q4:" + q4 + "q5:" + q5 + "q6:" + q6);
//        System.out.println(s);
        BigDecimal s3AddResult = new BigDecimal(s).multiply(new BigDecimal(s - 1)).multiply(new BigDecimal(s - 2)).multiply(new BigDecimal(s - 3));
//        System.out.println("s3AddResult" + s3AddResult);
        BigDecimal s4AddResult = new BigDecimal(s).multiply(new BigDecimal(s - 1)).multiply(new BigDecimal(s - 2)).multiply(new BigDecimal(s - 3)).multiply(new BigDecimal(s - 4));
//        System.out.println("s4AddResult" + s4AddResult);
        BigDecimal s5AddResult = new BigDecimal(s).multiply(new BigDecimal(s - 1)).multiply(new BigDecimal(s - 2)).multiply(new BigDecimal(s - 3)).multiply(new BigDecimal(s - 4)).multiply(new BigDecimal(s - 5));
//        System.out.println("s5AddResult" + s5AddResult);
        BigDecimal p4CalcResult = p4.multiply(new BigDecimal(100000)).divide(s3AddResult, 5, RoundingMode.HALF_UP).divide(new BigDecimal(100000), 5, RoundingMode.HALF_UP);
//        System.out.println("p4CalcResult" + p4CalcResult);
        BigDecimal p5CalcResult = p5.multiply(new BigDecimal(100000)).divide(s4AddResult, 5, RoundingMode.HALF_UP).divide(new BigDecimal(100000), 5, RoundingMode.HALF_UP);
//        System.out.println("p5CalcResult" + p5CalcResult);
        BigDecimal p6CalcResult = p6.multiply(new BigDecimal(100000)).divide(s5AddResult, 5, RoundingMode.HALF_UP).divide(new BigDecimal(100000), 5, RoundingMode.HALF_UP);
//        System.out.println("p6CalcResult" + p6CalcResult);
        BigDecimal q4CalcResult = q4.multiply(new BigDecimal(100000)).divide(s3AddResult, 5, RoundingMode.HALF_UP).divide(new BigDecimal(100000), 5, RoundingMode.HALF_UP);
//        System.out.println("q4CalcResult" + q4CalcResult);
        BigDecimal q5CalcResult = q5.multiply(new BigDecimal(100000)).divide(s4AddResult, 5, RoundingMode.HALF_UP).divide(new BigDecimal(100000), 5, RoundingMode.HALF_UP);
//        System.out.println("q5CalcResult" + q5CalcResult);
        BigDecimal q6CalcResult = q6.multiply(new BigDecimal(100000)).divide(s5AddResult, 5, RoundingMode.HALF_UP).divide(new BigDecimal(100000), 5, RoundingMode.HALF_UP);
        BigDecimal w4CalcResult = w4.multiply(new BigDecimal(100000)).divide(s3AddResult, 5, RoundingMode.HALF_UP).divide(new BigDecimal(100000), 5, RoundingMode.HALF_UP);
//        System.out.println("q4CalcResult" + q4CalcResult);
        BigDecimal w5CalcResult = w5.multiply(new BigDecimal(100000)).divide(s4AddResult, 5, RoundingMode.HALF_UP).divide(new BigDecimal(100000), 5, RoundingMode.HALF_UP);
//        System.out.println("q5CalcResult" + q5CalcResult);
        BigDecimal w6CalcResult = w6.multiply(new BigDecimal(100000)).divide(s5AddResult, 5, RoundingMode.HALF_UP).divide(new BigDecimal(100000), 5, RoundingMode.HALF_UP);
//        System.out.println("q6CalcResult" + q6CalcResult);
        BigDecimal xgl = p4CalcResult.add(p5CalcResult).add(p6CalcResult);
        BigDecimal zgl = q4CalcResult.add(q5CalcResult).add(q6CalcResult);
        BigDecimal hgl = w4CalcResult.add(w5CalcResult).add(w6CalcResult);
//        BigDecimal zglSubtractxglResult = zgl.subtract(xgl);
//        BigDecimal xglAddzgl = xgl.add(zgl);
//        BigDecimal xglPoint = xgl.subtract((new BigDecimal(0.95).multiply(zgl)));
        System.out.println("xgl" + xgl);
        System.out.println("zgl" + zgl);
        System.out.println("hgl" + hgl);
//        System.out.println(zglSubtractxglResult);
//        System.out.println(xglAddzgl);
//        System.out.println(xglPoint);
//        BigDecimal xtsl = zglSubtractxglResult.divide(xglAddzgl, 5, RoundingMode.HALF_UP);
//        BigDecimal ztsl = xglPoint.divide(xglAddzgl, 5, RoundingMode.HALF_UP);
        BigDecimal xsy = xgl.subtract(zgl).add((xgl.add(zgl).multiply(new BigDecimal(0.001))));
        BigDecimal zsy = new BigDecimal(0.95).multiply(zgl).subtract(xgl).add((xgl.add(zgl).multiply(new BigDecimal(0.001))));

        System.out.println(xsy);
        System.out.println(zsy);
        System.out.println("==========");
    }


    public int right(int num) {
        return num % 10;
    }


}
