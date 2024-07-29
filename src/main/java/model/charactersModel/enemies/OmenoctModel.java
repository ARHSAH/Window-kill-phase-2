package model.charactersModel.enemies;

import controller.Utils;
import model.charactersModel.EpsilonModel;
import model.collision.Collidable;
import model.impact.Impactable;
import model.movement.Direction;
import model.movement.Movable;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

import static controller.Controller.createOmenoctView;
import static controller.Utils.addVectors;
import static controller.Utils.multiplyVector;
import static controller.Variables.*;

public class OmenoctModel implements Movable, Collidable, Impactable {
    private Point2D center;
    private int id;
    private int hp;
    private int damage;
    private double speed;
    private Direction direction;
    public static ArrayList<OmenoctModel> omenoctModels = new ArrayList<>();
    private boolean impact;
    private boolean movement = true;
    String bord;
    int y;
    public OmenoctModel(Point2D center, int damage, double speed, int hp){
        omenoctNumbers ++;
        this.center = center;
        this.hp = hp;
        this.damage = damage;
        this.speed = speed;
        y = new Random().nextInt(30,
                175);
        this.id = omenoctNumbers;
        omenoctModels.add(this);
        Collidable.collidables.add(this);
        Impactable.impactables.add(this);
        createOmenoctView(id);
    }

    @Override
    public void move(Direction direction, double speed) {

    }

    @Override
    public void move(Direction direction) {

    }

    @Override
    public void move() {
        if(movement && Math.abs(center.getX() - 25 ) > 2 && Math.abs(center.getX() - (
                frameWidth
                - 30)) > 2) {
            if (Math.abs(center.getX() - 25) <
                    Math.abs(center.getX() - frameWidth + 25)) {
                direction = new Direction(new Point2D.Double(25 - center.getX(),
                        y - center.getY()));
                bord = "l";
            } else {
                direction = new Direction(new Point2D.Double(Math.abs(frameWidth
                        - 30 - center.getX())
                        , y - center.getY()));
                bord = "r";
            }

            Point2D vector = multiplyVector(direction.getDirectionVector(),
                    getSpeed());
            setCenter(addVectors(center, vector));
        }else{
            if(bord.equals("r")) {
                setCenter(new Point2D.Double(frameWidth - 30, y));
            }
            movement = false;
        }
    }

    public Point2D getCenter() {
        return center;
    }

    public void setCenter(Point2D center) {
        this.center = center;
    }

    @Override
    public Point2D getDirection() {
        return direction.getDirectionVector();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }


    @Override
    public void setDirection(Point2D direction) {

    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public static ArrayList<OmenoctModel> getOmenoctModels() {
        return omenoctModels;
    }

    public static void setOmenoctModels(ArrayList<OmenoctModel> omenoctModels) {
        OmenoctModel.omenoctModels = omenoctModels;
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

    public boolean isMovement() {
        return movement;
    }

    public void setMovement(boolean movement) {
        this.movement = movement;
    }

    @Override
    public boolean isCircular() {
        return false;
    }

    @Override
    public boolean isEnemy() {
        return true;
    }

    @Override
    public ArrayList<Point2D> getVertices() {
        return null;
    }
}
