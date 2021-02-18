package lab1;

import javafx.scene.paint.Color;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.shape.*;

public class TestFX extends Application {
       
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
//        primaryStage.setTitle("Hello World!");
        
        Group root = new Group();
        Scene scene = new Scene(root, 620, 380);
        scene.setFill(Color.rgb(51, 128, 246));

        Rectangle r = new Rectangle(125,50,250,250);
        r.setFill(Color.WHITE);
        root.getChildren().add(r);
        
        Polyline l1 = new Polyline(110.0f, 70.0f, 110.0f, 30.0f, 390.0f, 30.0f, 390.0f, 70.0f);
        l1.setStroke(Color.rgb(255, 254, 84));
        l1.setStrokeWidth(7.0f);
        root.getChildren().add(l1);
        
        Polygon p1 = new Polygon(50, 170, 270, 180, 125, 360, 25, 300);
        p1.setFill(Color.rgb(117, 250, 142));
        root.getChildren().add(p1);
        
        Polygon p2 = new Polygon(305, 140, 570, 330, 290, 350);
        p2.setFill(Color.rgb(192, 192, 192));
        root.getChildren().add(p2);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
