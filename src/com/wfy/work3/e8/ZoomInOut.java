package com.wfy.work3.e8;

import java.applet.Applet;
import java.awt.*;

public class ZoomInOut extends Applet {
    public static final String text = "Hello WFY";
    private Button in, out;
    private int fontSize = 28;

    public void init() {
        setLayout(null);

        in = new Button("+");
        in.setBounds(110, 200, 40, 40);
        add(in);

        out = new Button("-");
        out.setBounds(155, 200, 40, 40);
        add(out);
    }

    @Override
    public boolean action(Event e, Object o) {
        if ((e.target == in)) {
            zoomIn();
        }
        if (e.target == out) {
            zoomOut();
        }
        return true;
    }

    private void zoomIn() {
        fontSize += 1;
        repaint();
    }

    private void zoomOut() {
        fontSize -= 1;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.setFont(new Font(text, Font.BOLD, fontSize));
        g.drawString(text, 25, 100);
    }
}
