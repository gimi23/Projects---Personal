package TetrisFiles;

import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class PaneOrganizer {
    private Pane root;
    private int score;
    private int highScore;
    private Button scorePanel;
    private Button highScorePanel;
    private Game game;
    private Font pixel;
    private Button resume;
    private Rectangle pauseBackground;
    private double scoreX;
    private double scoreY;
    private double highX;
    private double highY;
    private Timeline timeline;
    private Node[] controlList;
    private Button controls;
    private Button quit;
    private Button newGame;
    private Button gameOver;
    private int pauseCounter;
    public PaneOrganizer() {
        this.pixel = Font.loadFont("file:/C:/Users/porte/IdeaProjects/Tetris/src/TetrisFiles/Broken Console Bold.ttf", 23);
        this.pauseCounter = 0;
        this.pauseBackground = new Rectangle(1200, 1000);
        this.pauseBackground.setLayoutX(0);
        this.pauseBackground.setLayoutY(0);
        this.pauseBackground.setFill(Color.rgb(0, 0, 0, 0.85));
        this.root = new Pane();
        this.score = 0;
        this.highScore = 0;
        this.controlList = new Node[2];
        this.creatGameBoard();
        this.createResume();
        this.createScore();
        this.createButtons();
        this.createNewGameButton();
        this.createControls();
        this.createTimeline();
        this.game = new Game(this.root, this);
    }

    public void createTimeline() {
        this.timeline = new Timeline();
        this.timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame keyframe = new KeyFrame(Duration.millis(800), event-> {
            this.game.moveCurrentDown();
        });
        KeyFrame keyframe2 = new KeyFrame(Duration.millis(1), event-> {
            this.game.clearRow();
        });

        KeyFrame keyframe3 = new KeyFrame(Duration.millis(1), event-> {
            this.checkGameOver();
        });
        this.timeline.getKeyFrames().addAll(keyframe, keyframe2, keyframe3);
        this.timeline.play();
    }

    public Pane getRoot() {
        return root;
    }

    public void creatGameBoard() {
        Rectangle background = new Rectangle(500, 900);
        background.setFill(Color.web("#030303"));
        background.setLayoutX(50);
        background.setLayoutY(0);
        this.root.getChildren().add(background);
        for (int i = 1; i <= 10; i++) {
            for (int j = 0; j <= 17; j++) {
                Rectangle rectangle = new Rectangle(48, 48);
                rectangle.setFill(Color.web("#000000"));
                rectangle.setLayoutX(50 * i + 1 );
                rectangle.setLayoutY(50 * j + 1);
                this.root.getChildren().add(rectangle);
            }
        }


        Rectangle background2 = new Rectangle(50, 1000);
        background2.setFill(Color.web("#414141"));
        background2.setLayoutX(0);
        background2.setLayoutY(0);
        Rectangle background3 = new Rectangle(650, 1000);
        background3.setFill(Color.web("#414141"));
        background3.setLayoutX(550);
        background3.setLayoutY(0);
        Rectangle background4 = new Rectangle(600, 100);
        background4.setFill(Color.web("#414141"));
        background4.setLayoutX(0);
        background4.setLayoutY(900);
        this.root.getChildren().addAll(background2, background3, background4);

        for (int i = 0; i <= 23; i++) {
            for (int j = 0; j <= 19; j++) {
                if ((i == 0 && j <= 19) || (i >= 11 && j <= 19 ) || (i > 0 && i < 11 && j >= 18 && j <= 19)) {
                    Rectangle rectangle = new Rectangle(36, 36);
                    rectangle.setFill(Color.web("#494949"));
                    rectangle.setLayoutX(50 * i + 7);
                    rectangle.setLayoutY(50 * j + 7);
                    Line line1 = new Line(50 * i, 50 * j, 50 * i + 7, 50 * j + 7);
                    line1.setStroke(Color.web("#323131"));
                    Line line2 = new Line(50 * i + 50, 50 * j + 50, 50 * i + 43, 50 * j + 43);
                    line2.setStroke(Color.web("#323131"));
                    Line line3 = new Line(50 * i, 50 * j + 50, 50 * i + 7, 50 * j + 43);
                    line3.setStroke(Color.web("#323131"));
                    Line line4 = new Line(50 * i + 50, 50 * j, 50 * i + 43, 50 * j + 7);
                    line4.setStroke(Color.web("#323131"));
                    Line line5 = new Line(50 * i, 50 * j, 50 * i + 50, 50 * j);
                    line5.setStroke(Color.web("#323131"));
                    Line line6 = new Line(50 * i , 50 * j, 50 * i, 50 * j + 50);
                    line6.setStroke(Color.web("#323131"));
                    Line line7 = new Line(50 * i + 50, 50 * j, 50 * i + 50, 50 * j + 50);
                    line7.setStroke(Color.web("#323131"));
                    Line line8 = new Line(50 * i , 50 * j + 50, 50 * i + 50, 50 * j + 50);
                    line8.setStroke(Color.web("#323131"));
                    Line line9 = new Line(50 * i + 7, 50 * j + 7, 50 * i + 43, 50 * j + 7);
                    line9.setStroke(Color.web("#323131"));
                    Line line10 = new Line(50 * i + 7, 50 * j + 7, 50 * i + 7, 50 * j + 43);
                    line10.setStroke(Color.web("#323131"));
                    Line line11 = new Line(50 * i + 7, 50 * j + 43, 50 * i + 43, 50 * j + 43);
                    line11.setStroke(Color.web("#323131"));
                    Line line12 = new Line(50 * i + 43, 50 * j + 7, 50 * i + 43, 50 * j + 43);
                    line12.setStroke(Color.web("#323131"));


                    this.root.getChildren().addAll(rectangle, line1, line2, line3, line4,
                            line5, line6, line7, line8,
                            line9, line10, line11, line12);
                }
            }
        }
    }

    public void createNewGameButton() {
        this.newGame = new Button("NEW GAME");
        this.newGame.setLayoutX(380);
        this.newGame.setLayoutY(475);
        this.newGame.setPrefSize(200, 50);

        this.newGame.setStyle("-fx-background-color: #6675ff; " +
                " -fx-border-width:4;" +
                " -fx-border-color: #1c2ccc;" +
                " -fx-text-fill: white;" +
                " -fx-font-size: 25px;" +
                "-fx-background-insets: 1px;" +
                "-fx-border-radius: 16;" +
                "-fx-font-family: '" + this.pixel.getFamily() + "';" +
                "-fx-background-radius: 16;");

        ScaleTransition scaleTransition3 = new ScaleTransition(Duration.millis(1), this.newGame);
        scaleTransition3.setByX(-0.14);
        scaleTransition3.setByY(-0.14);


        ScaleTransition reverseTransition3 = new ScaleTransition(Duration.millis(1), this.newGame);
        reverseTransition3.setByX(0.14);
        reverseTransition3.setByY(0.14);
        reverseTransition3.play();

        this.newGame.setOnMouseEntered(e -> {this.newGame.setStyle("-fx-background-color: #2a2a2a; " +
                " -fx-border-width:4;" +
                " -fx-border-color: #1c2ccc;" +
                " -fx-text-fill: white;" +
                " -fx-font-size: 25px;" +
                "-fx-background-insets: 1px;" +
                "-fx-border-radius: 16;" +
                "-fx-font-family: '" + this.pixel.getFamily() + "';" +
                "-fx-background-radius: 16;"); reverseTransition3.play();
        });

        this.newGame.setOnMouseExited(e -> {this.newGame.setStyle("-fx-background-color: #6675ff; " +
                " -fx-border-width:4;" +
                " -fx-border-color: #1c2ccc;" +
                " -fx-text-fill: white;" +
                " -fx-font-size: 25px;" +
                "-fx-background-insets: 1px;" +
                "-fx-border-radius: 16;" +
                "-fx-font-family: '" + this.pixel.getFamily() + "';" +
                "-fx-background-radius: 16;"); scaleTransition3.play();
        });

        this.newGame.setOnMouseClicked(e -> {
            scaleTransition3.play();
            this.root.getChildren().removeAll(this.newGame, this.gameOver, this.pauseBackground);
            double quitX = 750;
            double quitY = 925;
            this.quit.setLayoutX(quitX);
            this.quit.setLayoutY(quitY);
            this.createNewGame();
            this.timeline.play();
        });
    }

    public void createNewGame() {
        this.game.removeCurrentPiece();
        this.score = 0;
        this.scorePanel.setText("SCORE:" + this.score);
        this.root.requestFocus();
        this.scorePanel.setLayoutX(this.scoreX);
        this.scorePanel.setLayoutY(this.scoreY);
        this.highScorePanel.setLayoutX(this.highX);
        this.highScorePanel.setLayoutY(this.highY);
        this.game.clearBoard();
        this.timeline.play();
        this.game.gameStart();
    }

    public void createButtons() {
        this.gameOver = new Button("GAME OVER!");
        this.gameOver.setAlignment(Pos.CENTER);
        this.gameOver.setLayoutX(335);
        this.gameOver.setLayoutY(100);
        this.gameOver.setPrefSize(530, 72);

        this.gameOver.setStyle("-fx-background-color: #cf78c3; " +
                " -fx-border-width:4;" +
                " -fx-border-color: #a40e90;" +
                " -fx-text-fill: white;" +
                " -fx-font-size: 35px;" +
                "-fx-background-insets: 1px;" +
                "-fx-border-radius: 16;" +
                "-fx-font-family: '" + this.pixel.getFamily() + "';" +
                "-fx-background-radius: 16;");

        this.quit = new Button("QUIT");
        this.quit.setPrefSize(150, 50);
        this.quit.setLayoutX(750);
        this.quit.setLayoutY(925);
        this.quit.setStyle("-fx-background-color: #e77a7a; " +
                " -fx-border-width:4;" +
                " -fx-border-color: #7a3c3c;" +
                " -fx-text-fill: white;" +
                " -fx-font-size: 25px;" +
                "-fx-background-insets: 1px;" +
                "-fx-border-radius: 16;" +
                "-fx-font-family: '" + this.pixel.getFamily() + "';" +
                "-fx-background-radius: 16;");
        Button reset = new Button("RESET");
        reset.setPrefSize(150, 50);
        reset.setStyle("-fx-background-color: #7ba2de; " +
                "-fx-border-width: 4;" +
                " -fx-border-color: #4f699b; " +
                "-fx-text-fill: white;" +
                " -fx-font-size: 25px;" +
                "-fx-background-insets: 1px;" +
                "-fx-border-radius: 16;" +
                "-fx-font-family: '" + this.pixel.getFamily() + "';" +
                "-fx-background-radius: 16;");
        reset.setLayoutX(525);
        reset.setLayoutY(925);


        Button pause = new Button("PAUSE");
        pause.setPrefSize(150, 50);
        pause.setStyle("-fx-background-color: #bb9ebd; " +
                "-fx-border-width: 4;" +
                " -fx-border-color: #895a8c; " +
                "-fx-text-fill: white;" +
                " -fx-font-size: 25px;" +
                "-fx-background-insets: 1px;" +
                "-fx-border-radius: 16;" +
                "-fx-font-family: '" + this.pixel.getFamily() + "';" +
                "-fx-background-radius: 16;");
        pause.setLayoutX(300);
        pause.setLayoutY(925);

        ScaleTransition scaleTransition7 = new ScaleTransition(Duration.millis(55), pause);
        scaleTransition7.setByX(-0.1);
        scaleTransition7.setByY(-0.1);


        ScaleTransition reverseTransition7 = new ScaleTransition(Duration.millis(55), pause);
        reverseTransition7.setByX(0.1);
        reverseTransition7.setByY(0.1);
        reverseTransition7.play();

        ScaleTransition scaleTransition8 = new ScaleTransition(Duration.millis(1), pause);
        scaleTransition8.setByX(-0.14);
        scaleTransition8.setByY(-0.14);


        ScaleTransition reverseTransition8 = new ScaleTransition(Duration.millis(1), pause);
        reverseTransition8.setByX(0.14);
        reverseTransition8.setByY(0.14);
        reverseTransition8.play();

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(55), this.quit);
        scaleTransition.setByX(-0.1);
        scaleTransition.setByY(-0.1);


        ScaleTransition reverseTransition = new ScaleTransition(Duration.millis(55), this.quit);
        reverseTransition.setByX(0.1);
        reverseTransition.setByY(0.1);
        reverseTransition.play();

        ScaleTransition scaleTransition3 = new ScaleTransition(Duration.millis(1), this.quit);
        scaleTransition3.setByX(-0.14);
        scaleTransition3.setByY(-0.14);


        ScaleTransition reverseTransition3 = new ScaleTransition(Duration.millis(1), this.quit);
        reverseTransition3.setByX(0.14);
        reverseTransition3.setByY(0.14);
        reverseTransition3.play();



        ScaleTransition scaleTransition2 = new ScaleTransition(Duration.millis(55), reset);
        scaleTransition2.setByX(-0.1);
        scaleTransition2.setByY(-0.1);


        ScaleTransition reverseTransition2 = new ScaleTransition(Duration.millis(55), reset);
        reverseTransition2.setByX(0.1);
        reverseTransition2.setByY(0.1);
        reverseTransition2.play();

        ScaleTransition scaleTransition4 = new ScaleTransition(Duration.millis(1), reset);
        scaleTransition4.setByX(-0.14);
        scaleTransition4.setByY(-0.14);


        ScaleTransition reverseTransition4 = new ScaleTransition(Duration.millis(1), reset);
        reverseTransition4.setByX(0.14);
        reverseTransition4.setByY(0.14);
        reverseTransition4.play();


        ScaleTransition scaleTransition11 = new ScaleTransition(Duration.millis(1), this.resume);
        scaleTransition11.setByX(-0.14);
        scaleTransition11.setByY(-0.14);


        ScaleTransition reverseTransition11 = new ScaleTransition(Duration.millis(1), this.resume);
        reverseTransition11.setByX(0.14);
        reverseTransition11.setByY(0.14);
        reverseTransition11.play();



        this.quit.setOnAction(e -> {
            this.quit.setDisable(true);
            scaleTransition.play();
            scaleTransition.setOnFinished(event -> {
                reverseTransition.play();
                reverseTransition.setOnFinished(exitEvent -> {
                    System.exit(0);
                });
            });
            this.quit.setDisable(false);
        });

        reset.setOnAction(e -> {
            reset.setDisable(true);
            scaleTransition2.play();
            scaleTransition2.setOnFinished(event -> {
            reverseTransition2.play();
            });
            this.createNewGame();
            reset.setDisable(false);
        });

        double quitX = 750;
        double quitY = 925;
        this.resume.setOnAction(e -> {
            scaleTransition11.play();
            this.pauseCounter = 0;
            this.resume.setDisable(true);
            this.quit.setLayoutX(quitX);
            this.quit.setLayoutY(quitY);
            this.scorePanel.setLayoutX(this.scoreX);
            this.scorePanel.setLayoutY(this.scoreY);
            this.highScorePanel.setLayoutX(this.highX);
            this.highScorePanel.setLayoutY(this.highY);
            pause.setDisable(false);
            reset.setDisable(false);
            this.timeline.play();
            this.root.getChildren().removeAll(this.resume, this.pauseBackground);
        });

        pause.setOnAction(e -> {
            reset.setDisable(true);
                    this.root.getChildren().addAll(this.pauseBackground, this.resume);
                    this.quit.toFront();
                    this.controls.toFront();
                    this.resume.setDisable(false);
                    this.quit.setLayoutX(650);
                    this.quit.setLayoutY(475);
                    this.scorePanel.toFront();
                    this.highScorePanel.toFront();
                    this.scorePanel.setLayoutX(335);
                    this.scorePanel.setLayoutY(200);
                    this.highScorePanel.setLayoutX(335);
                    this.highScorePanel.setLayoutY(300);
                    this.timeline.stop();
                    this.pauseCounter++;
        });



        this.quit.setOnMouseEntered(mouseEvent -> {quit.setStyle("-fx-background-color: #2a2a2a; " +
                " -fx-border-width:4;" +
                " -fx-border-color: #7a3c3c;" +
                " -fx-text-fill: white;" +
                " -fx-font-size: 25px;" +
                "-fx-background-insets: 1px;" +
                "-fx-border-radius: 16;" +
                "-fx-font-family: '" + this.pixel.getFamily() + "';" +
                "-fx-background-radius: 16;"); reverseTransition3.play();});

        reset.setOnMouseEntered(mouseEvent -> {reset.setStyle("-fx-background-color: #2a2a2a; " +
                "-fx-border-width: 4;" +
                " -fx-border-color: #4f699b; " +
                "-fx-text-fill: white;" +
                " -fx-font-size: 25px;" +
                "-fx-background-insets: 1px;" +
                "-fx-border-radius: 16;" +
                "-fx-font-family: '" + this.pixel.getFamily() + "';" +
                "-fx-background-radius: 16;"); reverseTransition4.play();});

        this.quit.setOnMouseExited(mouseEvent -> {this.quit.setStyle("-fx-background-color: #e77a7a; " +
                " -fx-border-width:4;" +
                " -fx-border-color: #7a3c3c;" +
                " -fx-text-fill: white;" +
                " -fx-font-size: 25px;" +
                "-fx-background-insets: 1px;" +
                "-fx-border-radius: 16;" +
                "-fx-font-family: '" + this.pixel.getFamily() + "';" +
                "-fx-background-radius: 16;"); scaleTransition3.play();});

        reset.setOnMouseExited(mouseEvent -> {reset.setStyle("-fx-background-color: #7ba2de; " +
                "-fx-border-width: 4;" +
                " -fx-border-color: #4f699b; " +
                "-fx-text-fill: white;" +
                " -fx-font-size: 25px;" +
                "-fx-background-insets: 1px;" +
                "-fx-border-radius: 16;" +
                "-fx-font-family: '" + this.pixel.getFamily() + "';" +
                "-fx-background-radius: 16;"); scaleTransition4.play();});


        pause.setOnMouseEntered(mouseEvent -> {pause.setStyle("-fx-background-color: #2a2a2a; " +
                " -fx-border-width:4;" +
                " -fx-border-color: #895a8c;" +
                " -fx-text-fill: white;" +
                " -fx-font-size: 25px;" +
                "-fx-background-insets: 1px;" +
                "-fx-border-radius: 16;" +
                "-fx-font-family: '" + this.pixel.getFamily() + "';" +
                "-fx-background-radius: 16;"); reverseTransition8.play();});

        pause.setOnMouseExited(mouseEvent -> {pause.setStyle("-fx-background-color: #bb9ebd; " +
                "-fx-border-width: 4;" +
                " -fx-border-color: #895a8c; " +
                "-fx-text-fill: white;" +
                " -fx-font-size: 25px;" +
                "-fx-background-insets: 1px;" +
                "-fx-border-radius: 16;" +
                "-fx-font-family: '" + this.pixel.getFamily() + "';" +
                "-fx-background-radius: 16;"); scaleTransition8.play();});

        this.resume.setOnMouseEntered(mouseEvent -> {this.resume.setStyle("-fx-background-color: #2a2a2a; " +
                " -fx-border-width:4;" +
                " -fx-border-color: #515988;" +
                " -fx-text-fill: white;" +
                " -fx-font-size: 25px;" +
                "-fx-background-insets: 1px;" +
                "-fx-border-radius: 16;" +
                "-fx-font-family: '" + this.pixel.getFamily() + "';" +
                "-fx-background-radius: 16;"); reverseTransition11.play();});

        this.resume.setOnMouseExited(mouseEvent -> {this.resume.setStyle("-fx-background-color: #9aa0c3; " +
                "-fx-border-width: 4;" +
                " -fx-border-color: #515988; " +
                "-fx-text-fill: white;" +
                " -fx-font-size: 25px;" +
                "-fx-background-insets: 1px;" +
                "-fx-border-radius: 16;" +
                "-fx-font-family: '" + this.pixel.getFamily() + "';" +
                "-fx-background-radius: 16;");; scaleTransition11.play();});

        this.quit.setFocusTraversable(false);
        reset.setFocusTraversable(false);
        pause.setFocusTraversable(false);

        this.root.getChildren().addAll(this.quit, reset, pause);
    }

    public void createResume() {
        this.resume = new Button("RESUME");
        this.resume.setPrefSize(150, 50);
        this.resume.setLayoutX(400);
        this.resume.setLayoutY(475);

        this.resume.setStyle("-fx-background-color: #9aa0c3; " +
                "-fx-border-width: 4;" +
                " -fx-border-color: #515988; " +
                "-fx-text-fill: white;" +
                " -fx-font-size: 25px;" +
                "-fx-background-insets: 1px;" +
                "-fx-border-radius: 16;" +
                "-fx-font-family: '" + this.pixel.getFamily() + "';" +
                "-fx-background-radius: 16;");

        this.resume.setFocusTraversable(false);
        this.resume.setDisable(true);
    }

    public void createControls() {
        this.controls = new Button("?");
        this.controls.setPrefSize(50, 50);
        this.controls.setLayoutX(1120);
        this.controls.setLayoutY(20);

        this.controls.setStyle("-fx-background-color: #6ea097; " +
                "-fx-border-width: 4;" +
                " -fx-border-color: #1b7162; " +
                "-fx-text-fill: white;" +
                " -fx-font-size: 25px;" +
                "-fx-background-insets: 1px;" +
                "-fx-border-radius: 16;" +
                "-fx-font-family: '" + this.pixel.getFamily() + "';" +
                "-fx-background-radius: 16;");

        this.controls.setFocusTraversable(false);
        this.root.getChildren().add(controls);

        ScaleTransition scaleTransition2 = new ScaleTransition(Duration.millis(55), this.controls);
        scaleTransition2.setByX(-0.1);
        scaleTransition2.setByY(-0.1);


        ScaleTransition reverseTransition2 = new ScaleTransition(Duration.millis(55), this.controls);
        reverseTransition2.setByX(0.1);
        reverseTransition2.setByY(0.1);
        reverseTransition2.play();

        ScaleTransition scaleTransition4 = new ScaleTransition(Duration.millis(1), this.controls);
        scaleTransition4.setByX(-0.14);
        scaleTransition4.setByY(-0.14);


        ScaleTransition reverseTransition4 = new ScaleTransition(Duration.millis(1), this.controls);
        reverseTransition4.setByX(0.14);
        reverseTransition4.setByY(0.14);
        reverseTransition4.play();


        this.controls.setOnMouseEntered(mouseEvent -> {this.controls.setStyle("-fx-background-color: #2a2a2a; " +
                "-fx-border-width: 4;" +
                " -fx-border-color: #1b7162; " +
                "-fx-text-fill: white;" +
                " -fx-font-size: 25px;" +
                "-fx-background-insets: 1px;" +
                "-fx-border-radius: 16;" +
                "-fx-font-family: '" + this.pixel.getFamily() + "';" +
                "-fx-background-radius: 16;"); reverseTransition4.play();});

        this.controls.setOnMouseExited(mouseEvent -> {this.controls.setStyle("-fx-background-color: #6ea097; " +
                "-fx-border-width: 4;" +
                " -fx-border-color: #1b7162; " +
                "-fx-text-fill: white;" +
                " -fx-font-size: 25px;" +
                "-fx-background-insets: 1px;" +
                "-fx-border-radius: 16;" +
                "-fx-font-family: '" + this.pixel.getFamily() + "';" +
                "-fx-background-radius: 16;"); scaleTransition4.play();});

        Button exitControls = new Button("X");
        exitControls.setPrefSize(50, 50);
        exitControls.setLayoutX(905);
        exitControls.setLayoutY(174);

        exitControls.setStyle("-fx-background-color: #e77a7a; " +
                " -fx-border-width:4;" +
                " -fx-border-color: #7a3c3c;" +
                " -fx-text-fill: white;" +
                " -fx-font-size: 25px;" +
                "-fx-background-insets: 1px;" +
                "-fx-border-radius: 16;" +
                "-fx-font-family: '" + this.pixel.getFamily() + "';" +
                "-fx-background-radius: 16;");

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(55), exitControls);
        scaleTransition.setByX(-0.1);
        scaleTransition.setByY(-0.1);


        ScaleTransition reverseTransition = new ScaleTransition(Duration.millis(55), exitControls);
        reverseTransition.setByX(0.1);
        reverseTransition.setByY(0.1);
        reverseTransition.play();

        ScaleTransition scaleTransition3 = new ScaleTransition(Duration.millis(1), exitControls);
        scaleTransition3.setByX(-0.14);
        scaleTransition3.setByY(-0.14);


        ScaleTransition reverseTransition3 = new ScaleTransition(Duration.millis(1), exitControls);
        reverseTransition3.setByX(0.14);
        reverseTransition3.setByY(0.14);
        reverseTransition3.play();

        exitControls.setOnAction(e -> {
            exitControls.setDisable(true);
            if (this.pauseCounter == 0) {
                this.root.getChildren().removeAll(this.pauseBackground, exitControls);
                this.timeline.play();
            }
            else {
                this.root.getChildren().remove(exitControls);
            }
            this.directions(2);
            exitControls.setDisable(false);
        });

        exitControls.setOnMouseEntered(mouseEvent -> {exitControls.setStyle("-fx-background-color: #2a2a2a; " +
                " -fx-border-width:4;" +
                " -fx-border-color: #7a3c3c;" +
                " -fx-text-fill: white;" +
                " -fx-font-size: 25px;" +
                "-fx-background-insets: 1px;" +
                "-fx-border-radius: 16;" +
                "-fx-font-family: '" + this.pixel.getFamily() + "';" +
                "-fx-background-radius: 16;"); reverseTransition3.play();});

        exitControls.setOnMouseExited(mouseEvent -> {exitControls.setStyle("-fx-background-color: #e77a7a; " +
                " -fx-border-width:4;" +
                " -fx-border-color: #7a3c3c;" +
                " -fx-text-fill: white;" +
                " -fx-font-size: 25px;" +
                "-fx-background-insets: 1px;" +
                "-fx-border-radius: 16;" +
                "-fx-font-family: '" + this.pixel.getFamily() + "';" +
                "-fx-background-radius: 16;"); scaleTransition3.play(); this.root.requestFocus();});



        Button controlPanel = new Button();
        controlPanel.setLayoutX(270);
        controlPanel.setLayoutY(170);
        controlPanel.setPrefSize(700, 400);
        controlPanel.setStyle("-fx-font-family: '" + this.pixel.getFamily() +
                "'; -fx-font-size: 27px;" +
                "-fx-border-width: 5;" +
                "-fx-text-fill: white;"
                + "-fx-background-insets: 1px;"
                + "-fx-border-radius: 16;"
                + "-fx-background-radius: 16;"
                        + "-fx-border-color: #48216f;" +
                        "-fx-background-color: #7e5f9c");

        controlPanel.setText("""
                  J: MOVE PIECE LEFT\s
                  K: MOVE PIECE DOWN\s
                  L: MOVE PIECE RIGHT\s
                  I: ROTATE PIECE CLOCKWISE\s
                  W: ROTATE PIECE COUNTER-CLOCKWISE\s
                  SPACE BAR: MOVE PIECE ALL THE WAY DOWN\s
                 \s""");
        controlPanel.setAlignment(Pos.BOTTOM_LEFT);
        this.controlList[0] = controlPanel;
        Label control = new Label("CONTROLS");
        this.controlList[1] = control;
        control.setStyle("-fx-font-family: '" + this.pixel.getFamily() +
                "'; -fx-font-size: 60px;" +
                "-fx-text-fill: white;");
        control.setLayoutX(450);
        control.setLayoutY(190);


        this.controls.setOnAction(e -> {
            this.timeline.stop();
            scaleTransition2.play();
            scaleTransition2.setOnFinished(event -> {
                reverseTransition2.play();
                reverseTransition2.setOnFinished(event1 -> {
                    if (!this.root.getChildren().contains(this.pauseBackground)) {
                        this.root.getChildren().addAll(this.pauseBackground);
                    }
                    this.directions(1);
                    this.root.getChildren().add(exitControls);
                });
            });
        });
    }



    public void createScore() {
        this.scorePanel = new Button("SCORE: " + this.score);
        this.scorePanel.setAlignment(Pos.CENTER);
        this.scorePanel.setLayoutX(610);
        this.scorePanel.setLayoutY(309);
        this.scoreX = 610;
        this.scoreY = 309;
        this.scorePanel.setPrefSize(530, 72);
        this.scorePanel.setStyle("-fx-font-family: '" + this.pixel.getFamily() +
                "'; -fx-font-size: 35px;" +
                "-fx-border-width: 5;" +
                "-fx-text-fill: white;"
        + "-fx-background-insets: 1px;"
        + "-fx-border-radius: 16;"
        + "-fx-background-radius: 16;"
        + "-fx-border-color: #4a7641;" +
                "-fx-background-color: #86b582");



        this.highScorePanel = new Button("HIGH SCORE: " + this.highScore);
        this.highScorePanel.setAlignment(Pos.CENTER);
        this.highScorePanel.setLayoutX(610);
        this.highScorePanel.setLayoutY(409);
        this.highX = 610;
        this.highY = 409;
        this.highScorePanel.setPrefSize(530, 72);
        this.highScorePanel.setStyle("-fx-font-family: '" + this.pixel.getFamily() +
                "'; -fx-font-size: 35px;" +
                "-fx-border-width: 5;" +
                "-fx-text-fill: white;"
                + "-fx-background-insets: 1px;"
                + "-fx-border-radius: 16;"
                + "-fx-background-radius: 16;"
                + "-fx-border-color: #c5a100;" +
                "-fx-background-color: #e1cb69");

        this.root.getChildren().addAll(scorePanel, highScorePanel);
    }

    public void updateScore() {
        this.score = this.score + 100;
        this.scorePanel.setText("SCORE: " + this.score);
    }

    public void directions(int a) {
        switch (a) {
            case 1:
                this.root.getChildren().addAll(this.controlList);
            break;

            case 2:
                this.root.getChildren().removeAll(this.controlList);
                break;
        }
    }

    public void checkGameOver() {
        if (this.game.checkGameOver()) {
            this.timeline.stop();
            if (this.score > this.highScore) {
                this.highScore = this.score;
            }
            this.highScorePanel.setText("NEW HIGH SCORE: " + this.highScore);
            this.pauseCounter++;
            this.root.getChildren().addAll(this.pauseBackground, this.newGame, this.gameOver);
            this.quit.toFront();
            this.controls.toFront();
            this.resume.setDisable(false);
            this.quit.setLayoutX(650);
            this.quit.setLayoutY(475);
            this.scorePanel.toFront();
            this.highScorePanel.toFront();
            this.scorePanel.setLayoutX(335);
            this.scorePanel.setLayoutY(200);
            this.highScorePanel.setLayoutX(335);
            this.highScorePanel.setLayoutY(300);
        }
    }
}
