package model.impact;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public interface Impactable {
    ArrayList<Impactable> impactables = new ArrayList<>();
    public Point2D getCenter();
    public void setCenter(Point2D center);
    public Point2D getDirection();
    public void setDirection(Point2D direction);
    public double getSpeed();
    public void setSpeed(double speed);
    public void setImpact(boolean impact);
    public boolean getImpact();
}
