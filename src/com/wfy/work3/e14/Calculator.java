package com.wfy.work3.e14;

import java.applet.Applet;
import java.awt.*;

public class Calculator extends Applet {
    private TextField tfAnswer;
    private Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9;
    private Button bPoint, bEqual, bPlus, bMinus, bClear, bMulti, bDivision;
    private String OperatorCurrent, OperatorPre;
    private String ForeScreen, BackScreen;

    public void init() {
        OperatorCurrent = "";
        OperatorPre = "";
        ForeScreen = "0";
        BackScreen = "";
        setLayout(null);
        tfAnswer = new TextField();
        tfAnswer.setBounds(20, 20, 175, 40);
        tfAnswer.setFont(new Font(BackScreen, Font.BOLD, 28));
        add(tfAnswer);
        tfAnswer.setText(ForeScreen);
        bClear = new Button("C");
        bClear.setBounds(20, 70, 40, 40);
        add(bClear);

        bDivision = new Button("/");
        bDivision.setBounds(65, 70, 40, 40);
        add(bDivision);

        bMulti = new Button("*");
        bMulti.setBounds(110, 70, 40, 40);
        add(bMulti);

        bMinus = new Button("-");
        bMinus.setBounds(155, 70, 40, 40);
        add(bMinus);

        b7 = new Button("7");
        b7.setBounds(20, 115, 40, 40);
        add(b7);
        b8 = new Button("8");
        b8.setBounds(65, 115, 40, 40);
        add(b8);
        b9 = new Button("9");
        b9.setBounds(110, 115, 40, 40);
        add(b9);

        bPlus = new Button("+");
        bPlus.setBounds(155, 115, 40, 85);
        add(bPlus);

        b4 = new Button("4");
        b4.setBounds(20, 160, 40, 40);
        add(b4);
        b5 = new Button("5");
        b5.setBounds(65, 160, 40, 40);
        add(b5);
        b6 = new Button("6");
        b6.setBounds(110, 160, 40, 40);
        add(b6);

        b1 = new Button("1");
        b1.setBounds(20, 205, 40, 40);
        add(b1);
        b2 = new Button("2");
        b2.setBounds(65, 205, 40, 40);
        add(b2);
        b3 = new Button("3");
        b3.setBounds(110, 205, 40, 40);
        add(b3);

        bEqual = new Button("=");
        bEqual.setBounds(155, 205, 40, 85);
        add(bEqual);

        b0 = new Button("0");
        b0.setBounds(20, 250, 85, 40);
        add(b0);
        bPoint = new Button(".");
        bPoint.setBounds(110, 250, 40, 40);
        add(bPoint);

        Label label1 = new Label("2333");

    }

    public boolean action(Event e, Object o) {
        String s;
        if ((e.target == b0) || (e.target == b1) || (e.target == b2)
                || (e.target == b3) || (e.target == b4) || (e.target == b5)
                || (e.target == b6) || (e.target == b7) || (e.target == b8)
                || (e.target == b9)) {
            if (e.target != bPoint) {
                s = (String) o;
                doForeScreen(s);

            }
        }
        if ((e.target == bPoint) && !ForeScreen.contains(".")) {
            s = (String) o;
            if (ForeScreen.equals("")) {
                doForeScreen("0.");

            } else {
                doForeScreen(s);

            }
        }

        if (e.target == bClear) {
            doClear();
        }
        if ((e.target == bMulti) || (e.target == bDivision)
                || (e.target == bPlus) || (e.target == bMinus)
                || (e.target == bEqual)) {
            if (!ForeScreen.equals("")) {
                OperatorCurrent = ((String) o);
                doOperator();
            } else {
                OperatorPre = ((String) o);
            }
        }

        return true;
    }

    private void doOperator() {
        double dFore, dBack;
        Double d;
        if (OperatorPre.equals("")) {
            BackScreen = ForeScreen;
            ForeScreen = "";
            tfAnswer.setText(BackScreen);
            repaint();
        } else {
            dFore = new Double(ForeScreen);
            dBack = new Double(BackScreen);
            ForeScreen = "";
            BackScreen = tfAnswer.getText();

            if (OperatorPre.equals("+")) {
                d = dBack + dFore;
                BackScreen = d.toString();
            }

            if (OperatorPre.equals("-")) {
                d = dBack - dFore;
                BackScreen = d.toString();

            }

            if (OperatorPre.equals("*")) {
                d = dBack * dFore;
                BackScreen = d.toString();
            }

            if (OperatorPre.equals("/")) {
                d = dBack / dFore;
                BackScreen = d.toString();
            }
            tfAnswer.setText(BackScreen);
            repaint();
        }
        OperatorPre = OperatorCurrent;
        repaint();
    }

    private void doForeScreen(String s) {
        ForeScreen += s;
        if (ForeScreen.length() > 1 && ForeScreen.startsWith("0")
                && !ForeScreen.contains("."))
            ForeScreen = ForeScreen.substring(1);
        tfAnswer.setText(ForeScreen);
        repaint();

    }

    private void doClear() {// 清空操作
        OperatorCurrent = "";
        OperatorPre = "";
        ForeScreen = "0";
        BackScreen = "";
        tfAnswer.setText(ForeScreen);
        repaint();
    }
}
