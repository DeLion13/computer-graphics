package sample;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.*;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;

public class Main extends Application{
    Color appBack = Color.rgb(255,128,64);
    Color bodyAndHead = Color.rgb(66, 135, 245);
    Color bellyAndEyes = Color.rgb(255, 255, 255);
    Color wings = Color.rgb(7, 0, 224);
    Color feetAndBeak = Color.rgb(255, 221, 0);
    Color pupils = Color.rgb(0, 0, 0);

    int width = 700;
    int height = 700;
    int cx = width / 2;
    int cy = height / 2;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, width, height);
        Stop[] stops = new Stop[] {
                new Stop(0, Color.BLUE),
                new Stop(1, Color.YELLOW),
        };
        LinearGradient lg = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
        scene.setFill(lg);

        //draw penguin
        drawPenguin(root);

        //animation
        int time = 3000;

        //rotate
        RotateTransition rotate = new RotateTransition(Duration.millis(time), root);
        rotate.setByAngle(360f);
        rotate.setCycleCount(Timeline.INDEFINITE);

        //scale
        ScaleTransition scaleFrom = new ScaleTransition(Duration.millis(time), root);
        scaleFrom.setToX(1);
        scaleFrom.setToY(1);

        ScaleTransition scaleTo = new ScaleTransition(Duration.millis(time), root);
        scaleTo.setToX(0);
        scaleTo.setToY(0);

        SequentialTransition scale = new SequentialTransition();
        scale.getChildren().addAll(
                scaleTo,
                scaleFrom
        );
        scale.setCycleCount(Timeline.INDEFINITE);

        //translate
        TranslateTransition translateTo = new TranslateTransition(Duration.millis(time), root);
        translateTo.setFromY(0);
        translateTo.setToY(250);
        translateTo.setCycleCount(Timeline.INDEFINITE);
        translateTo.setAutoReverse(true);

        TranslateTransition translateFrom = new TranslateTransition(Duration.millis(time), root);
        translateFrom.setFromY(250);
        translateFrom.setToY(0);
        translateFrom.setCycleCount(Timeline.INDEFINITE);
        translateFrom.setAutoReverse(true);

        SequentialTransition translate = new SequentialTransition();
        translate.getChildren().addAll(
                translateTo,
                translateFrom
        );
        translate.setCycleCount(Timeline.INDEFINITE);

        ParallelTransition animation = new ParallelTransition();
        animation.getChildren().addAll(
                rotate,
                scale,
                translate
        );
        animation.play();
        //

        primaryStage.setTitle("Тиблочко");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void drawPenguin(Group root) {
        Ellipse body = new Ellipse(cx, cy, 80, 75);
        Stop[] stops = new Stop[] {
                new Stop(0, Color.RED),
                new Stop(0.5, Color.YELLOW),
                new Stop(0.8, Color.GREENYELLOW),
                new Stop(1, Color.LIGHTGREEN)
        };
        LinearGradient lg = new LinearGradient(0, 1, 1, 0, true, CycleMethod.NO_CYCLE, stops);
        body.setFill(lg);
        body.setStroke(Color.BLACK);
        body.setStrokeWidth(2);



        Ellipse leftEye = new Ellipse(cx + 10, cy + 15, 8, 12);
        leftEye.setFill(bellyAndEyes);
        leftEye.setStroke(Color.BLACK);
        leftEye.setStrokeWidth(2);
        Ellipse rightEye = new Ellipse(cx - 10, cy + 15, 8, 12);
        rightEye.setFill(bellyAndEyes);
        rightEye.setStroke(Color.BLACK);
        rightEye.setStrokeWidth(2);

        Circle leftPupil = new Circle(cx + 13, cy + 17, 4, pupils);
        Circle rightPupil = new Circle(cx - 7, cy + 17, 4, pupils);

        Arc mouth = new Arc();
        mouth.setCenterX(cx);
        mouth.setCenterY(cy + 40);
        mouth.setRadiusX(10);
        mouth.setRadiusY(10);
        mouth.setStartAngle(200);
        mouth.setLength(120);
        mouth.setType(ArcType.OPEN);
        mouth.setStroke(Color.BLACK);
        mouth.setStrokeWidth(2);
        mouth.setFill(Color.TRANSPARENT);

        Arc nose = new Arc();
        nose.setCenterX(cx);
        nose.setCenterY(cy + 35);
        nose.setRadiusX(4.5);
        nose.setRadiusY(5);
        nose.setStartAngle(220);
        nose.setLength(250);
        nose.setType(ArcType.OPEN);
        nose.setStroke(Color.BLACK);
        nose.setStrokeWidth(2);
        nose.setFill(Color.TRANSPARENT);

        Arc right_brow = new Arc();
        right_brow.setCenterX(cx + 15);
        right_brow.setCenterY(cy - 10);
        right_brow.setRadiusX(5);
        right_brow.setRadiusY(5);
        right_brow.setStartAngle(45);
        right_brow.setLength(70);
        right_brow.setType(ArcType.OPEN);
        right_brow.setStroke(Color.BLACK);
        right_brow.setStrokeWidth(2);
        right_brow.setFill(Color.TRANSPARENT);

        Arc left_brow = new Arc();
        left_brow.setCenterX(cx - 15);
        left_brow.setCenterY(cy - 10);
        left_brow.setRadiusX(5);
        left_brow.setRadiusY(5);
        left_brow.setStartAngle(75);
        left_brow.setLength(70);
        left_brow.setType(ArcType.OPEN);
        left_brow.setStroke(Color.BLACK);
        left_brow.setStrokeWidth(2);
        left_brow.setFill(Color.TRANSPARENT);

        Arc left_hand1 = new Arc();
        left_hand1.setCenterX(cx - 80);
        left_hand1.setCenterY(cy + 10);
        left_hand1.setRadiusX(10);
        left_hand1.setRadiusY(10);
        left_hand1.setStartAngle(298);
        left_hand1.setLength(140);
        left_hand1.setType(ArcType.OPEN);
        left_hand1.setStroke(Color.BLACK);
        left_hand1.setStrokeWidth(2);
        left_hand1.setFill(Color.TRANSPARENT);

        Arc left_hand2 = new Arc();
        left_hand2.setCenterX(cx - 77);
        left_hand2.setCenterY(cy + 34);
        left_hand2.setRadiusX(15);
        left_hand2.setRadiusY(15);
        left_hand2.setStartAngle(92);
        left_hand2.setLength(250);
        left_hand2.setType(ArcType.OPEN);
        left_hand2.setStroke(Color.BLACK);
        left_hand2.setStrokeWidth(2);
        left_hand2.setFill(Color.TRANSPARENT);

        Ellipse left_hand3 = new Ellipse(cx - 65, cy + 35, 7, 12);
        left_hand3.setFill(Color.LAWNGREEN);
        left_hand3.setStroke(Color.BLACK);
        left_hand3.setStrokeWidth(2);

        Arc right_hand1 = new Arc();
        right_hand1.setCenterX(cx + 78);
        right_hand1.setCenterY(cy + 10);
        right_hand1.setRadiusX(10);
        right_hand1.setRadiusY(10);
        right_hand1.setStartAngle(298);
        right_hand1.setLength(140);
        right_hand1.setType(ArcType.OPEN);
        right_hand1.setStroke(Color.BLACK);
        right_hand1.setStrokeWidth(2);
        right_hand1.setFill(Color.TRANSPARENT);

        Arc right_hand2 = new Arc();
        right_hand2.setCenterX(cx + 83.5);
        right_hand2.setCenterY(cy + 33.5);
        right_hand2.setRadiusX(15);
        right_hand2.setRadiusY(15);
        right_hand2.setStartAngle(92);
        right_hand2.setLength(250);
        right_hand2.setType(ArcType.OPEN);
        right_hand2.setStroke(Color.BLACK);
        right_hand2.setStrokeWidth(2);
        right_hand2.setFill(Color.TRANSPARENT);

        Ellipse right_hand3 = new Ellipse(cx + 93, cy + 35, 7, 12);
        right_hand3.setFill(Color.LAWNGREEN);
        right_hand3.setStroke(Color.BLACK);
        right_hand3.setStrokeWidth(2);

        Polygon cherenok = new Polygon(
                cx, cy - 75,
                cx - 15, cy - 75,
                cx - 30, cy - 105,
                cx - 15, cy - 105
        );
        cherenok.setFill(Color.BROWN);
        cherenok.setStroke(Color.BLACK);
        cherenok.setStrokeWidth(2);

        Polygon cherenok2 = new Polygon(
                cx - 30, cy - 105,
                cx - 15, cy - 105,
                cx - 18, cy - 98
        );
        cherenok2.setFill(Color.DARKORANGE);
        cherenok2.setStroke(Color.BLACK);
        cherenok2.setStrokeWidth(2);

        Ellipse leaf_back1 = new Ellipse(cx + 32.5, cy - 90, 40, 25);
        leaf_back1.setFill(Color.GREEN);
        leaf_back1.setStroke(Color.BLACK);
        leaf_back1.setStrokeWidth(2);

        Line leaf_back2 = new Line(cx - 7.5, cy - 90, cx + 62.5, cy - 90);
        leaf_back2.setStroke(Color.BLACK);
        leaf_back2.setStrokeWidth(1);

        Line leaf_back3 = new Line(cx + 7, cy - 90, cx + 14, cy - 100);
        leaf_back3.setStroke(Color.BLACK);
        leaf_back3.setStrokeWidth(1);

        Line leaf_back4 = new Line(cx + 16, cy - 90, cx + 24, cy - 100);
        leaf_back4.setStroke(Color.BLACK);
        leaf_back4.setStrokeWidth(1);

        Line leaf_back5 = new Line(cx + 25, cy - 90, cx + 33, cy - 100);
        leaf_back5.setStroke(Color.BLACK);
        leaf_back5.setStrokeWidth(1);

        Line leaf_back6 = new Line(cx + 34, cy - 90, cx + 42, cy - 100);
        leaf_back6.setStroke(Color.BLACK);
        leaf_back6.setStrokeWidth(1);

        Line leaf_back7 = new Line(cx + 43, cy - 90, cx + 51, cy - 100);
        leaf_back7.setStroke(Color.BLACK);
        leaf_back7.setStrokeWidth(1);

        Ellipse leaf_front1 = new Ellipse(cx + 7.5, cy - 57, 40, 25);
        leaf_front1.setFill(Color.GREEN);
        leaf_front1.setStroke(Color.BLACK);
        leaf_front1.setRotate(60);
        leaf_front1.setStrokeWidth(2);

        Line leaf_front2 = new Line(cx - 15, cy - 90, cx + 30, cy - 25);
        leaf_front2.setStroke(Color.BLACK);
        leaf_front2.setStrokeWidth(2);

        root.getChildren().add(leaf_back1);
        root.getChildren().add(leaf_back2);
        root.getChildren().add(leaf_back3);
        root.getChildren().add(leaf_back4);
        root.getChildren().add(leaf_back5);
        root.getChildren().add(leaf_back6);
        root.getChildren().add(leaf_back7);
        root.getChildren().add(body);
        root.getChildren().add(leftEye);
        root.getChildren().add(rightEye);
        root.getChildren().add(leftPupil);
        root.getChildren().add(rightPupil);
        root.getChildren().add(mouth);
        root.getChildren().add(nose);
        root.getChildren().add(right_brow);
        root.getChildren().add(left_brow);
        root.getChildren().add(left_hand1);
        root.getChildren().add(left_hand2);
        root.getChildren().add(left_hand3);
        root.getChildren().add(right_hand1);
        root.getChildren().add(right_hand2);
        root.getChildren().add(right_hand3);
        root.getChildren().add(cherenok);
        root.getChildren().add(cherenok2);
        root.getChildren().add(leaf_front1);
        root.getChildren().add(leaf_front2);
    }
}