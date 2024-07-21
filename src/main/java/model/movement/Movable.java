package model.movement;

import model.charactersModel.BulletModel;
import model.movement.Direction;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public interface Movable {
    void move(Direction direction, double speed);
    void move(Direction direction);
    void move();
    ArrayList<Movable> moveAbles = new ArrayList<>();


}
