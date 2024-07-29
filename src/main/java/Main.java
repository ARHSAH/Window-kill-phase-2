import controller.Update;
import model.charactersModel.CollectibleModel;
import model.charactersModel.enemies.OmenoctModel;
import model.charactersModel.enemies.SquareModel;
import model.charactersModel.enemies.TriangleModel;
import model.movement.Direction;
import view.panelsView.GlassFrame;
import view.panelsView.MenuPanel;

import java.awt.*;
import java.awt.geom.Point2D;

import static controller.Constants.*;
import static model.sounds.Sounds.enemySpawnSound;

public class Main {
    public static void main(String[] args) {
        Point2D point = new Point2D.Double(200, 0);
        new OmenoctModel(point,10,2,10);
        GlassFrame.getINSTANCE();
        MenuPanel.getINSTANCE();


    }
}