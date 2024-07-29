package view.panelsView;

import controller.Variables;

import javax.swing.*;

public class GameFrame extends JFrame {
    private static GameFrame INSTANCE;

    public static GameFrame getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new GameFrame();
        }
        return INSTANCE;
    }

    public static void setINSTANCE(GameFrame INSTANCE) {
        GameFrame.INSTANCE = INSTANCE;
    }


    private GameFrame() {

        this.setSize((int)Variables.frameWidth,(int)Variables.frameHeight);
        this.setLocation(300, 110);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
    }

}
