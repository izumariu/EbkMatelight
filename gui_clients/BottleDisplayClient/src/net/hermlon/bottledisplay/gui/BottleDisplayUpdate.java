package net.hermlon.bottledisplay.gui;

import java.awt.*;

/**
 * Created by hermlon on 26.11.16.
 */
public interface BottleDisplayUpdate {

    public void update();

    public void setLastColor(Color c);

    public Color getLastColor();
}
