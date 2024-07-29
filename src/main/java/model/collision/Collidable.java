package model.collision;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public interface Collidable{
    boolean isCircular();
    boolean isEnemy();
    ArrayList<Point2D> getVertices();
    Point2D getCenter();

    ArrayList<Collidable> collidables = new ArrayList<>();
}

