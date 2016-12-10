package net.hermlon.bottledisplay.gui;

import net.hermlon.bottledisplay.socket.ClientSocket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by hermlon on 26.11.16.
 */
public class BottleDisplayLED extends JButton implements ActionListener {

    private Color color = Color.BLACK;
    private BottleDisplayUpdate update;

    public BottleDisplayLED(BottleDisplayUpdate update) {
        this.update = update;
        this.addActionListener(this);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (mouseEvent.getButton() == MouseEvent.BUTTON3) {
                    color = JColorChooser.showDialog(null, "Farbauswahl", color);
                    repaint();
                    update.setLastColor(color);
                    update.update();
                }
                else {
                    super.mouseClicked(mouseEvent);
                }
            }
        });
    }

    public String getHex() {
        String h = "";
        h += toHex(color.getRed());
        h += toHex(color.getGreen());
        h += toHex(color.getBlue());
        return h;
    }

    public void setColor(Color c) {
        this.color = c;
        repaint();
        update.update();
    }

    private String toHex(int i) {
        String g = Integer.toHexString(i);
        if(g.length() == 1) {
            return "0" + g;
        }
        else {
            return g;
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(50, 50);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillOval(0, 0, 50, 50);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        color = update.getLastColor();
        repaint();
        update.update();
    }
}
