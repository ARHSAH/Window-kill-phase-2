package view.panelsView;

import javax.swing.*;
import java.awt.*;

import static controller.Constants.GLASS_FRAME_DIMENSION;

public final class GlassFrame extends JFrame {
    private static GlassFrame INSTANCE;
    private GlassFrame() throws HeadlessException {
        setUndecorated(true);
        setSize(GLASS_FRAME_DIMENSION);
        setBackground(new Color(0,0,0,0));
        setLocation(new Point(0, 0));
        setVisible(true);
        setLayout(null);
        this.repaint();
        this.revalidate();
    }

    public static GlassFrame getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new GlassFrame();
        return INSTANCE;
    }

    public static void setINSTANCE(GlassFrame INSTANCE) {
        GlassFrame.INSTANCE = INSTANCE;
    }
}