package com.wfy.work3.e8;

import java.applet.Applet;
import java.awt.*;

public class ZoomInOut extends Applet {
    public static final String text = "Hello WFY";
    private Label textLabel;
    private Button in, out;
    private int fontSize = 28;

    public void init() {
        setLayout(null);
        textLabel = new Label();
        textLabel.setBounds(20, 20, 400, 200);
        textLabel.setFont(new Font(text, Font.BOLD, fontSize));
        add(textLabel);
        textLabel.setText(text);

        in = new Button("+");
        in.setBounds(110, 230, 40, 40);
        add(in);

        out = new Button("-");
        out.setBounds(155, 230, 40, 40);
        add(out);
    }

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
        textLabel.setFont(new Font(text, Font.BOLD, fontSize));
        repaint();
    }

    private void zoomOut() {
        fontSize -= 1;
        textLabel.setFont(new Font(text, Font.BOLD, fontSize));
        repaint();
    }
}
