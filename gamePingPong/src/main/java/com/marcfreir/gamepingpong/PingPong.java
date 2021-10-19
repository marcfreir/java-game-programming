package com.marcfreir.gamepingpong;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.skin.TextInputControlSkin;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Random;

public class PingPong extends Application {
    private static final int CANVAS_WIDTH = 640;
    private static final int CANVAS_HEIGHT = 480;
    private static final int PLAYER_WIDTH = 15;
    private static final int PLAYER_HEIGHT = 100;
    private static final double BALL_RADIUS = 15;
    private int ballXspeed = 1;
    private int ballYspeed = 1;
    private double playerOneYposition= CANVAS_HEIGHT/2;
    private double playerTwoYposition= CANVAS_HEIGHT/2;
    private double ballXposition = CANVAS_WIDTH/2;
    private double ballYposition = CANVAS_WIDTH/2;
    private int scorePlayerOne = 0;
    private int scorePlayerTwo = 0;
    private boolean gameStarted;
    private static final int playerOneXposition = 0;
    private static final double playerTwoXposition = CANVAS_WIDTH - PLAYER_WIDTH;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PingPong.class.getResource("pingpong-view.fxml"));
        Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        GraphicsContext graphics = canvas.getGraphicsContext2D();
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), event->run(graphics)));
        timeline.setCycleCount(Timeline.INDEFINITE);

        //Mouse controller
        canvas.setOnMouseMoved(mouseEvent -> playerOneYposition = mouseEvent.getY());
        canvas.setOnMouseClicked(mouseEvent -> gameStarted = true);

        stage.setTitle("Ping Pong Game");
        stage.setScene(new Scene(new StackPane(canvas)));
        stage.show();
        timeline.play();

        //
        //
    }

    private void run(GraphicsContext graphics) {
        //Set background color
        graphics.setFill(Color.BLACK);
        graphics.fillRect(0,0, CANVAS_WIDTH, CANVAS_HEIGHT);

        //Set text color
        graphics.setFill(Color.WHITE);
        graphics.setFont(Font.font(25));

        //Game logic
        if (gameStarted) {
            //Set ball movement
            ballXposition = ballXposition + ballXspeed;
            ballYposition = ballYposition + ballYspeed;

            if (ballXposition < (CANVAS_WIDTH - (CANVAS_WIDTH/4))) {
                playerTwoYposition = (ballYposition - (PLAYER_HEIGHT/2));
            } else {
                playerTwoYposition = (ballYposition > playerTwoYposition + (PLAYER_HEIGHT/2) ?playerTwoYposition = playerTwoYposition + 1: playerTwoYposition - 1);
            }

            //Draw the ball
            graphics.fillOval(ballXposition, ballYposition, BALL_RADIUS, BALL_RADIUS);
        } else {
            //Set the starting text
            graphics.setStroke(Color.WHITE);
            graphics.setTextAlign(TextAlignment.CENTER);
            graphics.strokeText("Click to start!", (CANVAS_WIDTH/2), (CANVAS_HEIGHT/2));

            //Reset the ball starting position
            ballXposition = (CANVAS_WIDTH/2);
            ballYposition = (CANVAS_HEIGHT/2);

            //Reset speed & direction
            ballXspeed = new Random().nextInt(2) == 0 ? 1: -1;
            ballYspeed = new Random().nextInt(2) == 0 ? 1: -1;
        }

        //Keep the ball inside the canvas
        if (ballYposition > CANVAS_HEIGHT || ballYposition < 0) {
            ballYspeed = (ballYspeed * (-1));
        }

        //Machine gets point
        if (ballXposition < (playerOneXposition - PLAYER_WIDTH)) {
            scorePlayerTwo++;
            gameStarted = false;
        }
        //Player gets point
        if (ballXposition > (playerTwoXposition + PLAYER_WIDTH)) {
            scorePlayerOne++;
            gameStarted = false;
        }

        //Make the ball faster
        if (((ballXposition + BALL_RADIUS) > playerTwoXposition) && (ballYposition >= playerTwoYposition) && (ballYposition <= (playerTwoYposition + PLAYER_HEIGHT)) ||
                ((ballXposition < (playerOneXposition + PLAYER_WIDTH)) && (ballYposition >= playerOneYposition) && (ballYposition <= (playerOneYposition + PLAYER_HEIGHT)))) {
            ballXspeed += 1 * Math.signum(ballXspeed);
            ballYspeed += 1 * Math.signum(ballYspeed);
            ballXspeed = (ballXspeed * (-1));
            ballYspeed = (ballYspeed * (-1));
        }

        //Draw the score
        graphics.setFill(Color.YELLOW);
        graphics.fillText(scorePlayerOne + "\t\t\t\t\t\t" + scorePlayerTwo, (CANVAS_WIDTH/2), 100);

        //Draw the players
        graphics.setFill(Color.BLUE);
        graphics.fillRect(playerOneXposition, playerOneYposition, PLAYER_WIDTH, PLAYER_HEIGHT);
        graphics.setFill(Color.RED);
        graphics.fillRect(playerTwoXposition, playerTwoYposition, PLAYER_WIDTH, PLAYER_HEIGHT);

    }


    public static void main(String[] args) {
        launch();
    }
}