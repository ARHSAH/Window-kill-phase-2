package controller;

import model.charactersModel.BulletModel;
import model.charactersModel.CollectibleModel;
import model.charactersModel.EpsilonModel;
import model.charactersModel.enemies.OmenoctModel;
import model.charactersModel.enemies.SquareModel;
import model.charactersModel.enemies.TriangleModel;
import model.collision.Collidable;
import model.collision.Collision;
import model.impact.Impactable;
import model.movement.Direction;
import view.charactersView.BulletView;
import view.charactersView.EpsilonView;
import view.panelsView.GameFrame;
import view.panelsView.GamePanel;
import view.panelsView.GlassFrame;
import view.panelsView.StorePanel;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import static controller.Constants.*;
import static controller.Controller.*;
import static controller.Utils.*;
import static controller.Variables.*;
import static java.nio.file.Files.move;
import static model.charactersModel.BulletModel.bulletModels;
import static model.charactersModel.CollectibleModel.collectibleModels;
import static model.charactersModel.enemies.OmenoctModel.omenoctModels;
import static model.charactersModel.enemies.SquareModel.squareModels;
import static model.charactersModel.enemies.TriangleModel.triangleModels;
import static model.collision.Collidable.collidables;
import static model.collision.Collision.*;
import static model.impact.Impactable.impactables;
import static model.sounds.Sounds.*;
import static view.charactersView.BulletView.bulletViews;
import static view.charactersView.enemies.SquareView.squareViews;


public class Update implements ActionListener, KeyListener, MouseMotionListener{
    Timer timer;
    Clip mainSong;
    EpsilonModel epsilon = EpsilonModel.getINSTANCE();
    private static  Update INSTANCE;

    public static Update getINSTANCE() {
        if(INSTANCE == null){
            INSTANCE = new Update();
        }
        return INSTANCE;
    }

    private Update(){
        try {
            File file = new File("gameMainSong.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            mainSong = AudioSystem.getClip();
            mainSong.open(audioInputStream);
            FloatControl gainControl = (FloatControl) mainSong.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue((int) (volume / 1.2) - 80);
            mainSong.loop(5);
            mainSong.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        reset();
        timer = new Timer(Constants.UPS, this);
        timer.start();
        GameFrame.getINSTANCE().addKeyListener(this);
        GameFrame.getINSTANCE().addMouseMotionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameFinished) {
            gameOver();
            mainSong.stop();
            INSTANCE = null;
            timer.stop();
        }

        if(athenaActive){
            athenaTimer ++;
            if(athenaTimer > 1000){
                athenaTimer = 0;
                athenaActive = false;
            }
        }
        if(squareModels.isEmpty() && triangleModels.isEmpty() && wave == 3 && waveTimer > 1900){
            if(frameWidth != 0 && frameHeight != 0) {
                EpsilonModel.getINSTANCE().setRadius(EpsilonModel.getINSTANCE().getRadius() + 1);
                frameHeight -= 1;
                frameWidth -= 1;
            }else{
                gameOver();
                gameFinished = true;
                INSTANCE = null;
                timer.stop();
            }
        }

        if (!gameFinished) {
            waveTimer++;
            elapsedTime++;
            bulletTimer++;
            if (frameExtendingTimer < 5 && !frameExtendingDirection.isEmpty()) {
                frameExtendingTimer++;
                frameExtending(frameExtendingDirection);
            } else {
                frameExtendingTimer = 0;
                frameExtendingDirection = "";
            }
            if (activeGAbility) {
                if (activeAbility.equals("ares")) {
                    damage += 2;
                    activeGAbility = false;
                } else if (activeAbility.equals("aceso")) {
                    if (abilityCoolDown % 100 == 0 && hp + acesoHp <= 100) {
                        hp += acesoHp;
                    } else if (abilityCoolDown % 100 == 0 && hp + acesoHp > 100) {
                        hp = 100;
                    }
                } else if (activeAbility.equals("proteus")) {
                    epsilonVertices++;
                    activeGAbility = false;
                }
            }
            abilityCoolDown++;
            if (!firstOfGame) {
                updateModel();
                if (elapsedTime % 10 == 0) {
                    frameShrinkAmount++;
                    if (frameShrinkAmount == 5) {
                        frameShrinkAmount = 1;
                    }
                    gameShrinkage();
                }
            }
            updateCollectiblesTimer();
            updateView();
        }
    }

    public void updateView(){
        if(firstOfGame){
            startShrinkage();
        }
        updateTimer();
        setViewLocation();
        GamePanel.getINSTANCE().repaint();
        GamePanel.getINSTANCE().revalidate();

    }
    public void updateModel() {
        wave();
        if(bulletTimer > 7 && bullet) {
            bulletTimer = 0;
            Direction bulletDirection = new Direction(new Point2D.Double(
                    (mouseLocation.getX() - EpsilonModel.getINSTANCE().getX()),
                    mouseLocation.getY() - EpsilonModel.getINSTANCE().getY()));
            new BulletModel(EpsilonModel.getINSTANCE().getX(),
                    EpsilonModel.getINSTANCE().getY(), 4, damage, BULLET_SPEED,
                    bulletDirection.getDirectionVector());
            if(athenaActive){
                new BulletModel(EpsilonModel.getINSTANCE().getX(),
                        EpsilonModel.getINSTANCE().getY() + 10, 4, damage, BULLET_SPEED,
                        bulletDirection.getDirectionVector());
                new BulletModel(EpsilonModel.getINSTANCE().getX(),
                        EpsilonModel.getINSTANCE().getY() - 10, 4, damage, BULLET_SPEED,
                        bulletDirection.getDirectionVector());
            }

        }
        if(squareModels.isEmpty() && triangleModels.isEmpty() && wave < 3 && waveTimer > 1700){
            waveSound();
            wave ++;
            waveTimer = 0;
        }

        removedSquares = new ArrayList<>();
        removedBullets = new ArrayList<>();
        removedTriangles = new ArrayList<>();
        removedCollectibles = new ArrayList<>();

        ArrayList<Collidable> collidables = Collidable.collidables;
        for (int i = 0 ; i < collidables.size() ; i++) {
            for (int j = i + 1; j < collidables.size() ; j++) {
                //EPSILON COLLISIONS
                if ((collidables.get(i) instanceof EpsilonModel &&
                        collidables.get(j) instanceof SquareModel squareModel)) {
                    Point2D point = circlePolygonCollision(new Point2D.Double(EpsilonModel.getINSTANCE().getX(), EpsilonModel.getINSTANCE().getY()),
                            squareModel.getVertices());
                    if (distance(point,
                            new Point2D.Double(EpsilonModel.getINSTANCE().getX(),
                                    EpsilonModel.getINSTANCE().getY())) <
                            EpsilonModel.getINSTANCE().getRadius()) {
                        if (verticesEpsilonCollision(point, squareModel.getVertices())) {
                            damageSound();
                            if (hp - squareModel.getDamage() > 0) {
                                hp -= squareModel.getDamage();
                            } else {
                                hp = 0;
                                gameFinished = true;
                            }
                        }
                        if (verticesEpsilonCollision(point, EpsilonModel.getINSTANCE().getVertices())) {
                            squareModel.setHp(squareModel.getHp() - 10);
                            damageSound();
                        }

                        impact1(point, (Impactable) collidables.get(i),
                                (Impactable) collidables.get(j));
                    }
                } else if ((collidables.get(i) instanceof EpsilonModel &&
                        collidables.get(j) instanceof TriangleModel triangleModel)) {
                    Point2D point = circlePolygonCollision(new Point2D.Double(EpsilonModel.getINSTANCE().getX(), EpsilonModel.getINSTANCE().getY()),
                            triangleModel.getVertices());
                    if (distance(point,
                            new Point2D.Double(EpsilonModel.getINSTANCE().getX(), EpsilonModel.getINSTANCE().getY())) <
                            EpsilonModel.getINSTANCE().getRadius()) {
                        if (verticesEpsilonCollision(point, triangleModel.getVertices())) {
                            damageSound();
                            if (hp - triangleModel.getDamage() > 0) {
                                hp -= triangleModel.getDamage();
                            } else {
                                hp = 0;
                                gameFinished = true;
                            }
                        }
                        if (verticesEpsilonCollision(point, EpsilonModel.getINSTANCE().getVertices())) {
                            triangleModel.setHp(triangleModel.getHp() - 10);
                            damageSound();
                        }
                        impact1(point, (Impactable) collidables.get(i), (Impactable) collidables.get(j));
                    }
                } else if ((collidables.get(i) instanceof EpsilonModel &&
                        collidables.get(j) instanceof CollectibleModel collectibleModel)) {
                    if (collectibleModel.getTimer() > 600) {
                        removedCollectibles.add(collectibleModel);
                    } else if (collectibleModel.getCenter().distance(EpsilonModel.getINSTANCE().getCenter()) <
                            EpsilonModel.getINSTANCE().getRadius() + collectibleModel.getRadius()) {
                        xp += collectibleModel.getXp();
                        removedCollectibles.add(collectibleModel);
                    }
                //BULLET ENEMIES
                } else if ((collidables.get(i) instanceof BulletModel bulletModel &&
                        collidables.get(j) instanceof SquareModel squareModel)) {
                    Point2D point = circlePolygonCollision(new Point2D.Double
                                    (bulletModel.getX(), bulletModel.getY()),
                            squareModel.getVertices());
                    if (distance(point,
                            new Point2D.Double(bulletModel.getX(), bulletModel.getY())) <
                            bulletModel.getRadius()) {

                        squareModel.setHp(squareModel.getHp() - bulletModel.getDamage());
                        damageSound();
                        removedBullets.add(bulletModel);
                        impact1(point);
                    }
                } else if ((collidables.get(i) instanceof SquareModel squareModel &&
                        collidables.get(j) instanceof BulletModel bulletModel)) {
                    Point2D point = circlePolygonCollision(
                            bulletModel.getCenter(),
                            squareModel.getVertices());
                    if (distance(point,
                            bulletModel.getCenter()) <
                            bulletModel.getRadius()) {

                        squareModel.setHp(squareModel.getHp() - bulletModel.getDamage());
                        damageSound();
                        removedBullets.add(bulletModel);
                        impact1(point);
                    }
                } else if ((collidables.get(i) instanceof TriangleModel triangleModel &&
                        collidables.get(j) instanceof BulletModel bulletModel)) {
                    Point2D point = circlePolygonCollision(new Point2D.Double
                                    (bulletModel.getX(), bulletModel.getY()),
                            triangleModel.getVertices());
                    if (distance(circlePolygonCollision(new Point2D.Double(bulletModel.getX(), bulletModel.getY()),
                                    triangleModel.getVertices()),
                            new Point2D.Double(bulletModel.getX(), bulletModel.getY())) <
                            bulletModel.getRadius()) {
                        triangleModel.setHp(triangleModel.getHp() - bulletModel.getDamage());
                        damageSound();
                        removedBullets.add(bulletModel);
                        impact1(point);
                    }
                } else if ((collidables.get(i) instanceof BulletModel bulletModel &&
                        collidables.get(j) instanceof TriangleModel triangleModel)) {
                    Point2D point = circlePolygonCollision(new Point2D.Double
                                    (bulletModel.getX(), bulletModel.getY()),
                            triangleModel.getVertices());
                    if (distance(circlePolygonCollision(new Point2D.Double(bulletModel.getX(), bulletModel.getY()),
                                    triangleModel.getVertices()),
                            new Point2D.Double(bulletModel.getX(), bulletModel.getY())) <
                            bulletModel.getRadius()) {
                        triangleModel.setHp(triangleModel.getHp() - bulletModel.getDamage());
                        damageSound();
                        removedBullets.add(bulletModel);
                        impact1(point);
                    }
                } else if ((collidables.get(i) instanceof SquareModel squareModel &&
                        collidables.get(j) instanceof TriangleModel triangleModel)) {
                    if (polygonsCollision(triangleModel.getVertices(),
                            squareModel.getVertices()) != null) {
                        Point2D point = polygonsCollision(triangleModel.getVertices(), squareModel.getVertices());
                        impact1(point, triangleModel, squareModel);
                    }
                } else if ((collidables.get(i) instanceof TriangleModel triangleModel &&
                        collidables.get(j) instanceof SquareModel squareModel)) {
                    if (polygonsCollision(triangleModel.getVertices(),
                            squareModel.getVertices()) != null) {
                        Point2D point = polygonsCollision(triangleModel.getVertices(), squareModel.getVertices());
                        impact1(point, triangleModel, squareModel);
                    }
                }
                else if((collidables.get(i) instanceof SquareModel squareModel1 &&
                        collidables.get(j) instanceof SquareModel squareModel2 )){
                    if(polygonsCollision(squareModel1.getVertices(), squareModel2.getVertices()) != null){
                        Point2D point = polygonsCollision(squareModel1.getVertices(),
                                squareModel2.getVertices());
                        impact1(point, squareModel1, squareModel2);
                    }
                }
                else if((collidables.get(i) instanceof TriangleModel triangleModel1 &&
                        collidables.get(j) instanceof TriangleModel triangleModel2 )){
                    if(polygonsCollision(triangleModel1.getVertices(), triangleModel2.getVertices()) != null){
                        Point2D point = polygonsCollision(
                                triangleModel1.getVertices(), triangleModel2.getVertices());
                        impact1(point, triangleModel1, triangleModel2);
                    }
                }
            }
        }


        //BULLET FRAME
        checkBulletFrame();

        //EPSILON MOVEMENT
        if(!EpsilonModel.getINSTANCE().isImpact()){
            Direction direction = new Direction(new Point2D.Double(eRight + eLeft, eUp + eDown));
            EpsilonModel.getINSTANCE().setDirection(direction.getDirectionVector());
        }
        else{
            if(EpsilonModel.getINSTANCE().getSpeed() > 0) {
                EpsilonModel.getINSTANCE().setSpeed(EpsilonModel.getINSTANCE().getSpeed() - 0.5);
            }else{
                EpsilonModel.getINSTANCE().setImpact(false);
                Direction direction = new Direction(new Point2D.Double(eRight + eLeft, eUp + eDown));
                EpsilonModel.getINSTANCE().setDirection(direction.getDirectionVector());
            }
        }

        EpsilonModel.getINSTANCE().move();


        //BULLET MOVEMENT
        for (BulletModel value : bulletModels) {
            value.move();
        }

        //SQUARE MOVEMENT
        for(SquareModel value : squareModels){
            if(value.getHp() > 0) {
                value.move();
            }else{
                removedSquares.add(value);
            }
        }

        //TRIANGLE MOVEMENT
        for(TriangleModel value : triangleModels){
            if(value.getHp() > 0) {
                value.move();
            }else{
                removedTriangles.add(value);
            }
        }

        //OMENOCT MOVEMENT
        for(OmenoctModel value : omenoctModels){
            value.move();

        }

        //SQUARE REMOVE
        for(SquareModel value : removedSquares){
            new CollectibleModel(value.getCenter(), COLLECTIBLE_RADIUS);
            squareModels.remove(value);
            Collidable.collidables.remove(value);
            Impactable.impactables.remove(value);
            collapseSound();
        }

        //TRIANGLE REMOVE
        for(TriangleModel value : removedTriangles){
            new CollectibleModel(value.getCenter(), COLLECTIBLE_RADIUS);
            new CollectibleModel(value.getA(), COLLECTIBLE_RADIUS);
            triangleModels.remove(value);
            Collidable.collidables.remove(value);
            Impactable.impactables.remove(value);
            collapseSound();

        }



        //BULLET REMOVE
        for(BulletModel value : removedBullets){
            value.clip.stop();
            bulletModels.remove(value);
            Collidable.collidables.remove(value);

        }

        //COLLECTIBLE REMOVE
        for(CollectibleModel collectibleModel : removedCollectibles){
            collectibleModels.remove(collectibleModel);
            Collidable.collidables.remove(collectibleModel);
        }




    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == 'w'){
            eUp = -1;
        }
        if(e.getKeyChar() == 's'){
            eDown = 1;
        }
        if(e.getKeyChar() == 'a'){
            eRight = -1;
        }
        if(e.getKeyChar() == 'd'){
            eLeft = 1;

        }
        if(e.getKeyChar() == 'x'){
            bullet = true;

        }

        if(e.getKeyChar() == 'p'){
            mainSong.stop();
            timer.stop();
            StorePanel.getINSTANCE();

        }

        if(!activeAbility.isEmpty() && e.getKeyChar() == 'k' && abilityCoolDown >= 30000 && xp >= 100){
            xp -= 100;
            activeGAbility = true;
            abilityCoolDown = 0;
            acesoHp ++;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyChar() == 'w'){
            eUp = 0;
            epsilon.setSpeed(0.5);
        }
        if(e.getKeyChar() == 's'){
            eDown = 0;
            epsilon.setSpeed(0.5);
        }
        if(e.getKeyChar() == 'a'){
            eRight = 0;
            epsilon.setSpeed(0.5);
        }
        if(e.getKeyChar() == 'd'){
            eLeft = 0;
            epsilon.setSpeed(0.5);
        }
        if(e.getKeyChar() == 'x'){
            bullet = false;
        }


    }
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseLocation = new Point2D.Double(e.getX(), e.getY());
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public Clip getMainSong() {
        return mainSong;
    }

    public void setMainSong(Clip mainSong) {
        this.mainSong = mainSong;
    }


    public static void setINSTANCE(Update INSTANCE) {
        Update.INSTANCE = INSTANCE;
    }
}