package view.panelsView;

import javax.swing.*;
import java.awt.*;

import static controller.Constants.*;
import static controller.Variables.*;

public class SkillTreePanel extends JPanel {
    private static SkillTreePanel INSTANCE;
    public static SkillTreePanel getINSTANCE() {
        if(INSTANCE == null){
            INSTANCE = new SkillTreePanel();
        }
        return INSTANCE;
    }

    private static boolean aresUnlock, astrapeUnlock, cerberusUnlock;
    private static boolean acesoUnlock, melampusUnlock, chironUnlock;
    private static boolean proteusUnlock, empusaUnlock, dolusUnlock;
    private static boolean aresActive, astrapeActive, cerberusActive ;

    private static boolean acesoActive, melampusActive, chironActive;
    private static boolean proteusActive,empusaActive, dolusActive;
    JButton aresButton, astrapeButton, cerberusButton  ;
    JButton acesoButton, melampusButton, chironButton ;
    JButton proteusButton, empusaButton, dolusButton;
    JLabel attackLabel, defendLabel, shapeLabel;
    JLabel xpLabel;
    JButton exit;
    JLabel helloSkillTreeLabel;
    private SkillTreePanel(){;
        ImageIcon lockIcon = new ImageIcon("lock.png");
        this.setSize(SKILLTREE_FRAME_WIDTH, SKILLTREE_FRAME_HEIGHT);
        this.setLocation(0, 0);
        this.setLayout(null);


        helloSkillTreeLabel = new JLabel("SKILL TREE");
        helloSkillTreeLabel.setSize(200, 50);
        helloSkillTreeLabel.setFont(new Font(null, Font.PLAIN,35));
        helloSkillTreeLabel.setForeground(Color.BLACK);
        helloSkillTreeLabel.setLocation(250, 0);
        this.add(helloSkillTreeLabel);

        xpLabel = new JLabel(String.valueOf(allXp));
        ImageIcon xpIcon = new ImageIcon("xpimage.png");
        xpLabel.setIcon(xpIcon);
        xpLabel.setSize(90, 20);
        xpLabel.setFont(new Font(null, Font.PLAIN,20));
        xpLabel.setForeground(Color.BLACK);
        xpLabel.setLocation(580, 10);
        this.add(xpLabel);

        //ATTACK
        attackLabel = new JLabel("ATTACK");
        attackLabel.setSize(200, 50);
        attackLabel.setFont(new Font(null, Font.PLAIN,33));
        attackLabel.setForeground(Color.BLACK);
        attackLabel.setLocation(70, 100);
        this.add(attackLabel);


        //ares
        aresButton = new JButton("Writ of ares");
        aresButton.setIcon(lockIcon);
        aresButton.setSize(150, 50);
        aresButton.setFont(new Font(null, Font.PLAIN,20));
        aresButton.setBackground(SKILLTREE_UNLOCKED_BUTTONS_BACKGROUND_COLOR);
        aresButton.setForeground(Color.BLACK);
        aresButton.setLocation(60, 170);
        aresButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        aresButton.setFocusable(false);
        aresButton.addActionListener(e -> {
            if(!aresUnlock) {
                int result = JOptionPane.showConfirmDialog(null,
                        "Sure you want to unlock writ of ares ?", "title",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    if (allXp >= 750) {
                        aresUnlock = true;
                        aresButton.setIcon(null);
                        aresButton.setBackground(SKILLTREE_DIACTIVE_BUTTONS_BACKGROUND_COLOR);
                        allXp -= 750;
                        xpLabel.setText(String.valueOf(allXp));
                    }else{
                        JOptionPane.showMessageDialog(null,
                                "You need 750 xp to unlock writ of ares !", "title",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }else{
                if(!aresActive){
                    if(activeAbility.isEmpty()) {
                        aresActive = true;
                        activeAbility = "ares";
                        aresButton.setBackground(SKILLTREE_ACTIVE_BUTTONS_BACKGROUND_COLOR);
                    }else{
                        JOptionPane.showMessageDialog(null,
                                "Sorry you've already had active skill");
                    }
                }else{
                    aresActive = false;
                    activeAbility = "";
                    aresButton.setBackground(SKILLTREE_DIACTIVE_BUTTONS_BACKGROUND_COLOR);
                }
            }
        });
        this.add(aresButton);


        //astrape
        astrapeButton = new JButton("Writ of astrape");
        astrapeButton.setIcon(lockIcon);
        astrapeButton.setSize(160, 50);
        astrapeButton.setFont(new Font(null, Font.PLAIN,20));
        astrapeButton.setBackground(SKILLTREE_UNLOCKED_BUTTONS_BACKGROUND_COLOR);
        astrapeButton.setForeground(Color.BLACK);
        astrapeButton.setLocation(55, 270);
        astrapeButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        astrapeButton.setFocusable(false);
        astrapeButton.addActionListener(e -> {
                    if (aresUnlock) {
                        if (!astrapeUnlock) {
                            int result = JOptionPane.showConfirmDialog(null,
                                    "Sure you want to unlock writ of astrape ?", "title",
                                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if (result == JOptionPane.YES_OPTION) {
                                if (allXp >= 1000) {
                                    astrapeUnlock = true;
                                    astrapeButton.setIcon(null);
                                    astrapeButton.setBackground(SKILLTREE_DIACTIVE_BUTTONS_BACKGROUND_COLOR);
                                    allXp -= 1000;
                                    xpLabel.setText(String.valueOf(allXp));
                                } else {
                                    JOptionPane.showMessageDialog(null,
                                            "You need 1000 xp to unlock writ of astrape !", "title",
                                            JOptionPane.INFORMATION_MESSAGE);
                                }
                            }
                        } else {
                            if (!astrapeActive) {
                                if (activeAbility.isEmpty()) {
                                    astrapeActive = true;
                                    activeAbility = "astrape";
                                    astrapeButton.setBackground(SKILLTREE_ACTIVE_BUTTONS_BACKGROUND_COLOR);
                                } else {
                                    JOptionPane.showMessageDialog(null,
                                            "You've already had active skill");
                                }
                            } else {
                                astrapeActive = false;
                                activeAbility = "";
                                astrapeButton.setBackground(SKILLTREE_DIACTIVE_BUTTONS_BACKGROUND_COLOR);
                            }
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,
                                "You should unlock previous skills first");
                    }
                }
        );
        this.add(astrapeButton);

        //Cerberus
        cerberusButton = new JButton("Writ of cerberus");
        cerberusButton.setIcon(lockIcon);
        cerberusButton.setSize(170, 50);
        cerberusButton.setFont(new Font(null, Font.PLAIN,20));
        cerberusButton.setBackground(SKILLTREE_UNLOCKED_BUTTONS_BACKGROUND_COLOR);
        cerberusButton.setForeground(Color.BLACK);
        cerberusButton.setLocation(50, 370);
        cerberusButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cerberusButton.setFocusable(false);
        cerberusButton.addActionListener(e -> {
                    if (astrapeUnlock && aresUnlock) {
                        if (!cerberusUnlock) {
                            int result = JOptionPane.showConfirmDialog(null,
                                    "Sure you want to unlock writ of cerberus ?", "title",
                                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if (result == JOptionPane.YES_OPTION) {
                                if (allXp >= 1500) {
                                    cerberusUnlock = true;
                                    cerberusButton.setIcon(null);
                                    cerberusButton.setBackground(SKILLTREE_DIACTIVE_BUTTONS_BACKGROUND_COLOR);
                                    allXp -= 1500;
                                    xpLabel.setText(String.valueOf(allXp));
                                } else {
                                    JOptionPane.showMessageDialog(null,
                                            "You need 1500 xp to unlock writ of cerberus !",
                                            "title",
                                            JOptionPane.INFORMATION_MESSAGE);
                                }
                            }
                        } else {
                            if (!cerberusActive) {
                                if (activeAbility.isEmpty()) {
                                    cerberusActive = true;
                                    activeAbility = "cerberus";
                                    cerberusButton.setBackground(SKILLTREE_ACTIVE_BUTTONS_BACKGROUND_COLOR);
                                } else {
                                    JOptionPane.showMessageDialog(null,
                                            "You've already had active skill");
                                }
                            } else {
                                cerberusActive = false;
                                activeAbility = "";
                                cerberusButton.setBackground(SKILLTREE_DIACTIVE_BUTTONS_BACKGROUND_COLOR);
                            }
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,
                                "You should unlock previous skills first");
                    }
                }
        );
        this.add(cerberusButton);




        //DEFEND
        defendLabel = new JLabel("DEFEND");
        defendLabel.setSize(200, 50);
        defendLabel.setFont(new Font(null, Font.PLAIN,33));
        defendLabel.setForeground(Color.BLACK);
        defendLabel.setLocation(300, 100);
        this.add(defendLabel);


        //aceso
        acesoButton = new JButton("Writ of aceso");
        acesoButton.setIcon(lockIcon);
        acesoButton.setSize(150, 50);
        acesoButton.setFont(new Font(null, Font.PLAIN,20));
        acesoButton.setBackground(SKILLTREE_UNLOCKED_BUTTONS_BACKGROUND_COLOR);
        acesoButton.setForeground(Color.BLACK);
        acesoButton.setLocation(290, 170);
        acesoButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        acesoButton.setFocusable(false);
        acesoButton.addActionListener(e -> {
            if(!acesoUnlock) {
                int result = JOptionPane.showConfirmDialog(null,
                        "Sure you want to unlock writ of aceso ?", "title",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    if (allXp >= 500) {
                        acesoUnlock = true;
                        acesoButton.setIcon(null);
                        acesoButton.setBackground(SKILLTREE_DIACTIVE_BUTTONS_BACKGROUND_COLOR);
                        allXp -= 500;
                        xpLabel.setText(String.valueOf(allXp));
                    }else{
                        JOptionPane.showMessageDialog(null,
                                "You need 500 xp to unlock writ of aceso !",
                                "title",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }else{
                if(!acesoActive){
                    if(activeAbility.isEmpty()) {
                        acesoActive = true;
                        activeAbility = "aceso";
                        acesoButton.setBackground(SKILLTREE_ACTIVE_BUTTONS_BACKGROUND_COLOR);
                    }else{
                        JOptionPane.showMessageDialog(null,
                                "Sorry you've already had active skill");
                    }
                }else{
                    acesoActive = false;
                    activeAbility = "";
                    acesoButton.setBackground(SKILLTREE_DIACTIVE_BUTTONS_BACKGROUND_COLOR);
                }
            }
        });
        this.add(acesoButton);


        //melapmpus
        melampusButton = new JButton("Writ of melampus");
        melampusButton.setIcon(lockIcon);
        melampusButton.setSize(184, 50);
        melampusButton.setFont(new Font(null, Font.PLAIN,20));
        melampusButton.setBackground(SKILLTREE_UNLOCKED_BUTTONS_BACKGROUND_COLOR);
        melampusButton.setForeground(Color.BLACK);
        melampusButton.setLocation(273, 270);
        melampusButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        melampusButton.setFocusable(false);
        melampusButton.addActionListener(e -> {
            if(acesoUnlock) {
                if (!melampusUnlock) {
                    int result = JOptionPane.showConfirmDialog(null,
                            "Sure you want to unlock writ of melampus ?", "title",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (result == JOptionPane.YES_OPTION) {
                        if (allXp >= 750) {
                            melampusUnlock = true;
                            melampusButton.setIcon(null);
                            melampusButton.setBackground(SKILLTREE_DIACTIVE_BUTTONS_BACKGROUND_COLOR);
                            allXp -= 750;
                            xpLabel.setText(String.valueOf(allXp));
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "You need 750 xp to unlock writ of melampus !",
                                    "title", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } else {
                    if (!melampusActive) {
                        if (activeAbility.isEmpty()) {
                            melampusActive = true;
                            activeAbility = "melampus";
                            melampusButton.setBackground(SKILLTREE_ACTIVE_BUTTONS_BACKGROUND_COLOR);
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "Sorry you've already had active skill");
                        }
                    } else {
                        melampusActive = false;
                        activeAbility = "";
                        melampusButton.setBackground(SKILLTREE_DIACTIVE_BUTTONS_BACKGROUND_COLOR);
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null,
                        "You should unlock previous skills first");
            }
        });
        this.add(melampusButton);

        //chiron
        chironButton = new JButton("Writ of chiron");
        chironButton.setIcon(lockIcon);
        chironButton.setSize(150, 50);
        chironButton.setFont(new Font(null, Font.PLAIN,20));
        chironButton.setBackground(SKILLTREE_UNLOCKED_BUTTONS_BACKGROUND_COLOR);
        chironButton.setForeground(Color.BLACK);
        chironButton.setLocation(290, 370);
        chironButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        chironButton.setFocusable(false);
        chironButton.addActionListener(e -> {
            if(acesoUnlock && melampusUnlock) {
                if (!chironUnlock) {
                    int result = JOptionPane.showConfirmDialog(null,
                            "Sure you want to unlock writ of chiron ?", "title",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (result == JOptionPane.YES_OPTION) {
                        if (allXp >= 900) {
                            chironUnlock = true;
                            chironButton.setIcon(null);
                            chironButton.setBackground(SKILLTREE_DIACTIVE_BUTTONS_BACKGROUND_COLOR);
                            allXp -= 900;
                            xpLabel.setText(String.valueOf(allXp));
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "You need 500 xp to unlock writ of aceso !",
                                    "title", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } else {
                    if (!chironActive) {
                        if (activeAbility.isEmpty()) {
                            chironActive = true;
                            activeAbility = "chiron";
                            acesoButton.setBackground(SKILLTREE_ACTIVE_BUTTONS_BACKGROUND_COLOR);
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "Sorry you've already had active skill");
                        }
                    } else {
                        chironActive = false;
                        activeAbility = "";
                        chironButton.setBackground(SKILLTREE_DIACTIVE_BUTTONS_BACKGROUND_COLOR);
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null,
                        "You should unlock previous skills first");
            }
        });
        this.add(chironButton);

        //SHAPE
        shapeLabel = new JLabel("SHAPE");
        shapeLabel.setSize(200, 50);
        shapeLabel.setFont(new Font(null, Font.PLAIN,33));
        shapeLabel.setForeground(Color.BLACK);
        shapeLabel.setLocation(530, 100);
        this.add(shapeLabel);

        proteusButton = new JButton("Writ of proteus");
        proteusButton.setIcon(lockIcon);
        proteusButton.setSize(160, 50);
        proteusButton.setFont(new Font(null, Font.PLAIN,20));
        proteusButton.setBackground(SKILLTREE_UNLOCKED_BUTTONS_BACKGROUND_COLOR);
        proteusButton.setForeground(Color.BLACK);
        proteusButton.setLocation(505, 170);
        proteusButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        proteusButton.setFocusable(false);
        proteusButton.addActionListener(e -> {
            if(!proteusUnlock) {
                int result = JOptionPane.showConfirmDialog(null,
                        "Sure you want to unlock writ of proteus ?", "title",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    if (allXp >= 1000) {
                        proteusUnlock = true;
                        proteusButton.setIcon(null);
                        proteusButton.setBackground(SKILLTREE_DIACTIVE_BUTTONS_BACKGROUND_COLOR);
                        allXp -= 1000;
                        xpLabel.setText(String.valueOf(allXp));
                    }else{
                        JOptionPane.showMessageDialog(null,
                                "You need 1000 xp to unlock writ of proteus !",
                                "title",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }else{
                if(!proteusActive){
                    if(activeAbility.isEmpty()) {
                        proteusActive = true;
                        activeAbility = "proteus";
                        proteusButton.setBackground(SKILLTREE_ACTIVE_BUTTONS_BACKGROUND_COLOR);
                    }else{
                        JOptionPane.showMessageDialog(null,
                                "Sorry you've already had active skill");
                    }
                }else{
                    proteusActive = false;
                    activeAbility = "";
                    proteusButton.setBackground(SKILLTREE_DIACTIVE_BUTTONS_BACKGROUND_COLOR);
                }
            }
        });
        this.add(proteusButton);

        //empusa
        empusaButton = new JButton("Writ of empusa");
        empusaButton.setIcon(lockIcon);
        empusaButton.setSize(170, 50);
        empusaButton.setFont(new Font(null, Font.PLAIN,20));
        empusaButton.setBackground(SKILLTREE_UNLOCKED_BUTTONS_BACKGROUND_COLOR);
        empusaButton.setForeground(Color.BLACK);
        empusaButton.setLocation(505, 270);
        empusaButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        empusaButton.setFocusable(false);
        empusaButton.addActionListener(e -> {
            if(proteusUnlock) {
                if (!empusaUnlock) {
                    int result = JOptionPane.showConfirmDialog(null,
                            "Sure you want to unlock writ of empusa ?", "title",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (result == JOptionPane.YES_OPTION) {
                        if (allXp >= 750) {
                            empusaUnlock = true;
                            empusaButton.setIcon(null);
                            empusaButton.setBackground(SKILLTREE_DIACTIVE_BUTTONS_BACKGROUND_COLOR);
                            allXp -= 750;
                            xpLabel.setText(String.valueOf(allXp));
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "You need 750 xp to unlock writ of empusa !",
                                    "title", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } else {
                    if (!empusaActive) {
                        if (activeAbility.isEmpty()) {
                            empusaActive = true;
                            activeAbility = "empusa";
                            empusaButton.setBackground(SKILLTREE_ACTIVE_BUTTONS_BACKGROUND_COLOR);
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "Sorry you've already had active skill");
                        }
                    } else {
                        empusaActive = false;
                        activeAbility = "";
                        empusaButton.setBackground(SKILLTREE_DIACTIVE_BUTTONS_BACKGROUND_COLOR);
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null,
                        "You should unlock previous skills first");
            }
        });
        this.add(empusaButton);

        //dolus
        dolusButton = new JButton("Writ of dolus");
        dolusButton.setIcon(lockIcon);
        dolusButton.setSize(160, 50);
        dolusButton.setFont(new Font(null, Font.PLAIN,20));
        dolusButton.setBackground(SKILLTREE_UNLOCKED_BUTTONS_BACKGROUND_COLOR);
        dolusButton.setForeground(Color.BLACK);
        dolusButton.setLocation(505, 370);
        dolusButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        dolusButton.setFocusable(false);
        dolusButton.addActionListener(e -> {
            if(acesoUnlock && empusaUnlock) {
                if (!dolusUnlock) {
                    int result = JOptionPane.showConfirmDialog(null,
                            "Sure you want to unlock writ of dolus ?", "title",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (result == JOptionPane.YES_OPTION) {
                        if (allXp >= 750) {
                            dolusUnlock = true;
                            dolusButton.setIcon(null);
                            dolusButton.setBackground(SKILLTREE_DIACTIVE_BUTTONS_BACKGROUND_COLOR);
                            allXp -= 750;
                            xpLabel.setText(String.valueOf(allXp));
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "You need 1000 xp to unlock writ of dolus !",
                                    "title", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } else {
                    if (!dolusActive) {
                        if (activeAbility.isEmpty()) {
                            dolusActive = true;
                            activeAbility = "empusa";
                            dolusButton.setBackground(SKILLTREE_ACTIVE_BUTTONS_BACKGROUND_COLOR);
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "Sorry you've already had active skill");
                        }
                    } else {
                        dolusActive = false;
                        activeAbility = "";
                        dolusButton.setBackground(SKILLTREE_DIACTIVE_BUTTONS_BACKGROUND_COLOR);
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null,
                        "You should unlock previous skills first");
            }
        });
        this.add(dolusButton);

        //EXIT
        exit = new JButton("MAIN MENU");
        exit.setSize(150, 40);
        exit.setFont(new Font(null, Font.PLAIN,20));
        exit.setBackground(SETTING_SLIDERS_BACKGROUND_COLOR);
        exit.setForeground(Color.BLACK);
        exit.setLocation(0, 0);
        exit.setBorderPainted(false);
        exit.addActionListener(e -> {
            this.setVisible(false);
            MenuPanel.getINSTANCE();
        });
        this.add(exit);

        this.repaint();
        this.revalidate();
        MainView.mainPanel.add(this);
        MainView.mainPanel.repaint();
        MainView.mainPanel.revalidate();


    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        ImageIcon imageIcon = new ImageIcon("skilltreebackground.jpg");
        Image img = imageIcon.getImage();
        Graphics2D g2D = (Graphics2D)g;
        g2D.drawImage(img, 0 , 0, this);
        g2D.setColor(Color.BLACK);

        g2D.drawLine(135,66, 585, 66);
        g2D.drawLine(365,66, 365, 107);
        g2D.drawLine(135,66, 135, 107);
        g2D.drawLine(365,66, 365, 107);
        g2D.drawLine(585,66, 585, 107);
        //ATTACK
        g2D.drawRect(45,107,180,40);
        g2D.drawLine(135,148, 135, 170);
        g2D.drawLine(135,220, 135, 270);
        g2D.drawLine(135,320, 135, 370);
        //DEFEND
        g2D.drawRect(275,107,180,40);
        g2D.drawLine(365,148, 365, 170);
        g2D.drawLine(365,220, 365, 270);
        g2D.drawLine(365,320, 365, 370);
        //SHAPE
        g2D.drawRect(505,107,160,40);
        g2D.drawLine(585,148, 585, 170);
        g2D.drawLine(585,220, 585, 270);
        g2D.drawLine(585,320, 585, 370);
    }


}