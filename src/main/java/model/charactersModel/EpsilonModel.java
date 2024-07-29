package model.charactersModel;

import model.collision.Collidable;
import model.impact.Impactable;
import model.movement.Direction;
import model.movement.Movable;
import view.panelsView.GamePanel;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import static controller.Constants.*;
import static controller.Utils.multiplyVector;
import static controller.Variables.*;

public class EpsilonModel implements Movable, Collidable, Impactable {
    private static EpsilonModel INSTANCE;
    private int radius, hp;
    private double x, y;

    double speed;



    private Point2D direction;
    private boolean impact;
    private JPanel panel;

    ;
    public static EpsilonModel getINSTANCE() {
        if(INSTANCE == null){
            INSTANCE = new EpsilonModel((double) frameWidth / 2 ,
                    (double) frameHeight / 2, EPSILON_RADIUS, 100, 0.5,
                    new Point2D.Double(0,0));
        }
        return INSTANCE;
    }


    private EpsilonModel(double x, double y, int radius , int hp, double speed,
                         Point2D direction){
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.hp = hp;
        this.speed = speed;
        this.direction = direction;
        this.panel = panel;
        Collidable.collidables.add(this);
        Impactable.impactables.add(this);
        Movable.moveAbles.add(this);
    }

    public static void setEpsilonSpeed(){
        if(getINSTANCE().getSpeed() < EPSILON_MAX_SPEED){
            getINSTANCE().setSpeed(getINSTANCE().getSpeed() + 2.5 + (double)sensitivity / 50);
        }

    }

    @Override
    public void move(Direction direction, double speed) {
    }

    @Override
    public void move(Direction direction){

    }

    @Override
    public void move() {
        double limit = EPSILON_MAX_SPEED + INSTANCE.getRadius();
        if((direction.getY() < 0 && INSTANCE.getY() > limit) ||
                (direction.getY() > 0 && INSTANCE.getY() < frameHeight - limit - 26 ) ) {
            INSTANCE.setY(INSTANCE.getY() +
                    multiplyVector(direction, INSTANCE.getSpeed()).getY());
            if(!impact) {
                setEpsilonSpeed();
            }
        }
        if((direction.getX() < 0 && INSTANCE.getX() > limit) ||
                (direction.getX() > 0 && INSTANCE.getX() < frameWidth - limit - 10 ) ) {
            INSTANCE.setX(INSTANCE.getX() +
                    multiplyVector(direction, INSTANCE.getSpeed()).getX());
            if(!impact) {
                setEpsilonSpeed();
            }
        }
    }

    public static void setINSTANCE(EpsilonModel INSTANCE) {
        EpsilonModel.INSTANCE = INSTANCE;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
    public Point2D getDirection() {
        return direction;
    }

    public void setDirection(Point2D direction) {
        this.direction = direction;
    }

    public boolean isImpact() {
        return impact;
    }

    public void setImpact(boolean impact) {
        this.impact = impact;
    }

    @Override
    public boolean getImpact() {
        return false;
    }

    public ArrayList<Point2D> getVertices() {
        ArrayList<Point2D> vertices = new ArrayList<>();
        for (int i = 0; i < epsilonVertices ; i++) {
            int x = (int) (getX() + (radius + VERTICES_DISTANCE) *
                    Math.cos(2 * Math.PI * i / epsilonVertices) - Math.PI / 2);
            int y = (int)(getY() + (radius + VERTICES_DISTANCE) *
                    Math.sin(2 * Math.PI * i / epsilonVertices) - Math.PI / 2);
            vertices.add(new Point2D.Double(x, y));
        }
        return vertices;
    }

    @Override
    public Point2D getCenter(){
        return new Point2D.Double(getX(), getY());
    }

    @Override
    public void setCenter(Point2D center) {

    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    @Override
    public boolean isCircular() {
        return true;
    }

    @Override
    public boolean isEnemy() {
        return false;
    }
}
