package net.hermlon.bottledisplay.gui;

import net.hermlon.bottledisplay.socket.ClientSocket;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by hermlon on 26.11.16.
 */
public class BottleDisplayWindow extends JFrame implements BottleDisplayUpdate {

    private JPanel mainPanel;
    private ClientSocket socket;
    private String cmd;
    public Color lastColor = Color.RED;
    private JTextField text;
    private JComboBox<String> oldCmds;
    private JSlider timeSlider;

    private BottleDisplayLED[] bottleDisplayLEDS;

    public BottleDisplayWindow() {
        this.setSize(50 * 5 + 40, 50 * 8 + 200);
        this.setTitle("BottleDisplay");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initGUI();
        this.setVisible(true);

        socket = new ClientSocket("mariuszero", 1337);
    }

    private void initGUI() {
        mainPanel = new JPanel();
        JPanel leds = new JPanel();
        leds.setLayout(new GridLayout(8, 5));
        bottleDisplayLEDS = new BottleDisplayLED[40];
        for(int i = 0; i < bottleDisplayLEDS.length; i ++) {
            bottleDisplayLEDS[i] = new BottleDisplayLED(this);
            leds.add(bottleDisplayLEDS[i]);
        }
        JButton send = new JButton("Send");
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String c = "";
                if(text != null && !text.getText().equals("")) {
                    c = text.getText();
                    if(!c.startsWith("d=") && c.length() < 60) {
                        boolean valid = true;
                        for(int i = 0; i < c.length(); i ++) {
                            if((int)c.charAt(i) > 127) {
                                valid = false;
                            }
                        }
                        if(valid) {
                            socket.send(c);
                            oldCmds.addItem(c);
                            System.out.println(c);
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Nur ASCII Eingaben!");
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Unerlaubte Eingabe.");
                    }
                }
                else {
                    c = cmd;
                    socket.send(c);
                    oldCmds.addItem(c);
                    System.out.println(c);
                }
            }
        });
        oldCmds = new JComboBox<>();
        oldCmds.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
               socket.send(oldCmds.getSelectedItem().toString());
                System.out.println(oldCmds.getSelectedItem().toString());
            }
        });
        JButton clear = new JButton("Clear");
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                text.setText("");
                for(int i = 0; i < bottleDisplayLEDS.length; i ++) {
                    bottleDisplayLEDS[i].setColor(Color.BLACK);
                }
            }
        });
        text = new JTextField("", 20);
        timeSlider = new JSlider(JSlider.HORIZONTAL, 0, 10000, 1000);
        timeSlider.setMinorTickSpacing(1000);
        timeSlider.setPaintTicks(true);
        timeSlider.setPaintLabels(true);
        timeSlider.setLabelTable(timeSlider.createStandardLabels(5000));
        timeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                update();
            }
        });
        mainPanel.add(leds);
        mainPanel.add(send);
        mainPanel.add(clear);
        mainPanel.add(text);
        mainPanel.add(timeSlider);
        mainPanel.add(oldCmds);
        this.setContentPane(mainPanel);
        update();
    }

    public void update() {
        cmd = "d=" + Integer.toString(timeSlider.getValue()) + ";";

        for(int i = 0; i < bottleDisplayLEDS.length; i ++) {
            cmd += bottleDisplayLEDS[i].getHex() + ";";
        }/*
        for(int y = 0; y < 8; y ++) {
            if(y % 2 == 0) {
                //Umkehren
                for(int x = 5; x > 0; x --) {
                    cmd += bottleDisplayLEDS[(y * 5) + x].getHex() + ";";
                }
            }
            else {
                for(int x = 0; x < 5; x ++) {
                    cmd += bottleDisplayLEDS[(y * 5) + x].getHex() + ";";
                }
            }
        }*/
    }

    public void setLastColor(Color c) {
        lastColor = c;
    }

    @Override
    public Color getLastColor() {
        return lastColor;
    }
}