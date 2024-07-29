package view.charactersView.enemies;

import model.charactersModel.enemies.OmenoctModel;
import view.panelsView.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import static controller.Constants.OMENOCT_SIDE_LENGTH;
import static controller.Variables.allXp;

public class OmenoctView {
    private Point2D center = new Point2D.Double(0, 0);
    private int id;
    public static ArrayList<OmenoctView> omenoctViews = new ArrayList<>();
    public OmenoctView(int id){
        this.id = id;
        omenoctViews.add(this);
    }

    public Point2D getCenter() {
        return center;
    }

    public void setCenter(Point2D center) {
        this.center = center;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static ArrayList<OmenoctView> getOmenoctViews() {
        return omenoctViews;
    }

    public static void setOmenoctViews(ArrayList<OmenoctView> omenoctViews) {
        OmenoctView.omenoctViews = omenoctViews;
    }

    public Point2D[] findOmenoctVertices(){
        Point2D[] vertices = new Point2D.Double[8];
        double angle = Math.PI / 4.0;
        for(int i = 0 ; i < 8 ; i++){
            double x = center.getX() + OMENOCT_SIDE_LENGTH * (Math.cos(angle * i) );
            double y = center.getY() + OMENOCT_SIDE_LENGTH * (Math.sin(angle * i) );
            vertices[i] = new Point2D.Double(x, y);
        }
        return vertices;
    }
    public void draw(Graphics2D g2D){
        int[] xVertices = new int[8];
        int[] yVertices = new int[8];
        for(int i = 0 ; i < 8 ; i++){
            xVertices[i] = (int)findOmenoctVertices()[i].getX();
            yVertices[i] = (int)findOmenoctVertices()[i].getY();
        }

        g2D.setColor(Color.RED);
        g2D.fillPolygon(xVertices, yVertices, 8);
    }
}
