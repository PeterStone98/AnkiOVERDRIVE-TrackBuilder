package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Controller {

    private final Logger log = Logger.getLogger("Controller");
    /*	private final int NUM_ROWS = 8;
        private final int NUM_COLS = 8;
        private final double PADDING = 40;
    */
    @FXML
    VBox vbox;
    //new code
    Generator generator = new Generator();
    Track track = new Track();
    Piece chosenPiece;
    int previousX;
    int previousY;
    ArrayList<Track> generatedTracks = new ArrayList<>();
    boolean creative = false;
    boolean generated = false;
    int listIndex;
    //
    int layer = 0;

    @FXML
    Pane boardPane;
    @FXML
    AnchorPane poppingUpZone;
    @FXML
    Rectangle Rect;
    @FXML
    Rectangle Rect2;

    //@FXML Rectangle chosenRect;


    @FXML
    ImageView intersectionTrackBtn;
    @FXML
    ImageView straightTrackBtn;
    @FXML
    ImageView curveTrackBtn;
    @FXML
    ImageView jumpTrackBtn;
    @FXML
    ImageView speedTrackBtn;
    @FXML
    ImageView chosenTrack;
    @FXML
    TextField numOfIntersection;
    @FXML
    TextField numOfStraight;
    @FXML
    TextField numOfCurves;
    @FXML
    TextField numOfJump;
    @FXML
    TextField numOfSpeed;
    @FXML
    TextField warningText;

    @FXML
    Button btnRotateRight;

    // @FXML
    // ScrollPane scrollPaneCollection;
    @FXML
    Group rectangleGroup;
    @FXML
    Stage primaryStage;
    //
    // bottom row
    //
    @FXML
    Pane row0;
    @FXML
    Rectangle sq0_0;
    @FXML
    Rectangle sq1_0;
    @FXML
    Rectangle sq2_0;
    @FXML
    Rectangle sq3_0;
    @FXML
    Rectangle sq4_0;
    @FXML
    Rectangle sq5_0;
    @FXML
    Rectangle sq6_0;
    @FXML
    Rectangle sq7_0;

    //
    // 2nd from bottom
    //
    @FXML
    Pane row1;
    @FXML
    Rectangle sq0_1;
    @FXML
    Rectangle sq1_1;
    @FXML
    Rectangle sq2_1;
    @FXML
    Rectangle sq3_1;
    @FXML
    Rectangle sq4_1;
    @FXML
    Rectangle sq5_1;
    @FXML
    Rectangle sq6_1;
    @FXML
    Rectangle sq7_1;

    //
    // 3rd from bottom
    //
    @FXML
    Pane row2;
    @FXML
    Rectangle sq0_2;
    @FXML
    Rectangle sq1_2;
    @FXML
    Rectangle sq2_2;
    @FXML
    Rectangle sq3_2;
    @FXML
    Rectangle sq4_2;
    @FXML
    Rectangle sq5_2;
    @FXML
    Rectangle sq6_2;
    @FXML
    Rectangle sq7_2;

    //
    // 4th from bottom
    //
    @FXML
    Pane row3;
    @FXML
    Rectangle sq0_3;
    @FXML
    Rectangle sq1_3;
    @FXML
    Rectangle sq2_3;
    @FXML
    Rectangle sq3_3;
    @FXML
    Rectangle sq4_3;
    @FXML
    Rectangle sq5_3;
    @FXML
    Rectangle sq6_3;
    @FXML
    Rectangle sq7_3;

    //
    // 4th from top
    //
    @FXML
    Pane row4;
    @FXML
    Rectangle sq0_4;
    @FXML
    Rectangle sq1_4;
    @FXML
    Rectangle sq2_4;
    @FXML
    Rectangle sq3_4;
    @FXML
    Rectangle sq4_4;
    @FXML
    Rectangle sq5_4;
    @FXML
    Rectangle sq6_4;
    @FXML
    Rectangle sq7_4;

    //
    // 3rd from top
    //
    @FXML
    Pane row5;
    @FXML
    Rectangle sq0_5;
    @FXML
    Rectangle sq1_5;
    @FXML
    Rectangle sq2_5;
    @FXML
    Rectangle sq3_5;
    @FXML
    Rectangle sq4_5;
    @FXML
    Rectangle sq5_5;
    @FXML
    Rectangle sq6_5;
    @FXML
    Rectangle sq7_5;

    //
    // 2nd from top
    //
    @FXML
    Pane row6;
    @FXML
    Rectangle sq0_6;
    @FXML
    Rectangle sq1_6;
    @FXML
    Rectangle sq2_6;
    @FXML
    Rectangle sq3_6;
    @FXML
    Rectangle sq4_6;
    @FXML
    Rectangle sq5_6;
    @FXML
    Rectangle sq6_6;
    @FXML
    Rectangle sq7_6;

    //
    // top row
    //
    @FXML
    Pane row7;
    @FXML
    Rectangle sq0_7;
    @FXML
    Rectangle sq1_7;
    @FXML
    Rectangle sq2_7;
    @FXML
    Rectangle sq3_7;
    @FXML
    Rectangle sq4_7;
    @FXML
    Rectangle sq5_7;
    @FXML
    Rectangle sq6_7;
    @FXML
    Rectangle sq7_7;
    //name
    @FXML
    TextField nameText;


    // Music
    int countClick;
    MediaPlayer mediaPlayer;
    @FXML
    Button btnPlay;
    boolean playSong; // true is play, fasle is pause
    @FXML
    ImageView iconPlay;
    @FXML
    Slider volumeSlider;
    private final List<Pane> panes = new ArrayList<>();
    private final List<ImageView> trackLists = new ArrayList<ImageView>();

    @FXML
    public void initialize(boolean playSong) {
        panes.add(row0);
        panes.add(row1);
        panes.add(row2);
        panes.add(row3);
        panes.add(row4);
        panes.add(row5);
        panes.add(row6);
        panes.add(row7);
        countClick = 0;
        playSoundBackGround(playSong);
        mediaPlayer.setVolume(0.3);
        volumeSlider.setValue(mediaPlayer.getVolume() * 100);
        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mediaPlayer.setVolume(volumeSlider.getValue() / 1000);
            }
        });

        ObservableList<Node> childrens = boardPane.getChildren();
        for (Node node : childrens) {
            node.setId("node");
        }
        boardPane.addEventFilter(MouseEvent.MOUSE_EXITED, this::leaveBoard);
        boardPane.addEventFilter(MouseEvent.MOUSE_RELEASED, this::checkReleaseOutOfBoard);
        //scrollPaneCollection.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
        vbox.setMaxWidth(800.0d);

        //new code
        Piece startPiece = new Piece(5);
        this.track.map[this.layer].grid[2][1] = startPiece;
        this.track.addPiece(startPiece);

        Rectangle r = pickRectangle(2, 1);


        ImageView trackPiece = new ImageView(new Image("resources/Images/start.png"));
        trackPiece.setFitWidth(75);
        trackPiece.setFitHeight(75);


        trackPiece.setId("startTrack");
        trackPiece.setLayoutX(150);
        trackPiece.setLayoutY(135);
        trackPiece.setOnMousePressed(this::startMovingPiece);
        trackPiece.setOnMouseDragged(this::movePiece);
        trackPiece.setOnMouseReleased(this::finishMovingPiece);
        trackPiece.setPickOnBounds(true);
        trackPiece.setPreserveRatio(true);
        boardPane.getChildren().add(trackPiece);

        final Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);
        if (r != null) {
            if (log.isLoggable(Level.FINE)) {
                log.fine("[FINISH] r=" + r.getId());
            }

            Point2D rectScene = r.localToScene(r.getX(), r.getY());
            Point2D parent = boardPane.sceneToLocal(rectScene.getX(), rectScene.getY());

            timeline.getKeyFrames().add(
                    new KeyFrame(Duration.millis(100),
                            new KeyValue(chosenTrack.layoutXProperty(), parent.getX()),
                            new KeyValue(chosenTrack.layoutYProperty(), parent.getY()),
                            new KeyValue(chosenTrack.opacityProperty(), 1.0d)
                    )
            );

        } else {
            timeline.getKeyFrames().add(
                    new KeyFrame(Duration.millis(100),
                            new KeyValue(trackPiece.opacityProperty(), 1.0d)
                    )
            );
        }

        timeline.play();
        this.nameText.setText("Unnamed Track");
        this.nameText.setAlignment(Pos.CENTER);

    }

    public void backHomePage(ActionEvent event) throws IOException {
        mediaPlayer.stop();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("HomePage.fxml"));
        Parent view1 = fxmlLoader.load();
        HomePage h = fxmlLoader.getController();
        Scene scene1 = new Scene(view1);
        scene1.getStylesheets().add(getClass().getResource("/resources/sample-css/homepage.css").toExternalForm());
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene1);
        window.show();
    }

    public void openIntroduction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(Main.class.getResource("introduction.fxml"));
        Parent view4 = fxmlLoader1.load();
        Scene scene4 = new Scene(view4);
        Stage window1 = new Stage();
        window1.setScene(scene4);
        window1.show();
    }

    public void playSoundBackGround(boolean playSong) {
        Random randomSongs = new Random();
        int indexSong = randomSongs.nextInt(3);
        Media sound;
        if (indexSong == 1) {
            sound = new Media(new File("sound1.mp3").toURI().toString());
        } else if (indexSong == 2) {
            sound = new Media(new File("sound2.mp3").toURI().toString());
        } else {
            sound = new Media(new File("sound3.mp3").toURI().toString());
        }
        mediaPlayer = new MediaPlayer(sound);
        if (playSong == true) {
            iconPlay.setImage(new Image("resources/Images/homepage/pauseicon2.png"));
            mediaPlayer.play();
        } else {
            iconPlay.setImage(new Image("resources/Images/homepage/playicon.png"));
            mediaPlayer.pause();
        }
    }

    public void playandpauseSong() {
        countClick++;
        if (countClick % 2 == 0) {
            iconPlay.setImage(new Image("resources/Images/homepage/pauseicon2.png"));
            playSong = true;
            mediaPlayer.play();
        } else {
            iconPlay.setImage(new Image("resources/Images/homepage/playicon.png"));
            playSong = true;
            mediaPlayer.pause();
        }
    }

    public void nextSong() {
        MediaPlayer mediaPlayer_temp;
        Media sound;
        Random randomSongs = new Random();
        int indexSong = randomSongs.nextInt(3);

        if (indexSong == 1) {
            sound = new Media(new File("sound1.mp3").toURI().toString());
        } else if (indexSong == 2) {
            sound = new Media(new File("sound2.mp3").toURI().toString());
        } else {
            sound = new Media(new File("sound3.mp3").toURI().toString());
        }
        mediaPlayer.stop();
        mediaPlayer_temp = new MediaPlayer(sound);
        mediaPlayer = mediaPlayer_temp;
        iconPlay.setImage(new Image("resources/Images/homepage/pauseicon2.png"));
        mediaPlayer.play();
    }


    public void createStraightTrack(MouseEvent evt) {
        if (Integer.parseInt(numOfStraight.getText()) > 0) {
            ImageView trackPiece;
            if (layer == 0) {
                trackPiece = new ImageView(new Image("resources/Images/straight.png"));
            } else {
                trackPiece = new ImageView(new Image("resources/Images/straight2.png"));
            }
            trackPiece.setFitWidth(75);
            trackPiece.setFitHeight(75);
            boardPane.getChildren().add(trackPiece);
            trackPiece.setId("straightTrack");
            trackPiece.setLayoutX(885);
            trackPiece.setLayoutY(410);
            trackPiece.setOnMousePressed(this::startMovingPiece);
            trackPiece.setOnMouseDragged(this::movePiece);
            trackPiece.setOnMouseReleased(this::finishMovingPiece);
            numOfStraight.setText(Integer.toString(Integer.parseInt(numOfStraight.getText()) - 1));
        }

    }

    public void createCurvesTrack(MouseEvent evt) {
        if (Integer.parseInt(numOfCurves.getText()) > 0) {
            ImageView trackPiece;
            if (layer == 0) {
                trackPiece = new ImageView(new Image("resources/Images/curves.png"));
            } else {
                trackPiece = new ImageView(new Image("resources/Images/curves2.png"));
            }
            trackPiece.setFitWidth(75);
            trackPiece.setFitHeight(75);
            boardPane.getChildren().add(trackPiece);
            trackPiece.setId("curvesTrack");
            trackPiece.setLayoutX(885);
            trackPiece.setLayoutY(410);
            trackPiece.setOnMousePressed(this::startMovingPiece);
            trackPiece.setOnMouseDragged(this::movePiece);
            trackPiece.setOnMouseReleased(this::finishMovingPiece);
            numOfCurves.setText(Integer.toString(Integer.parseInt(numOfCurves.getText()) - 1));
        }

    }

    public void createIntersectionTrack(MouseEvent evt) {
        if (Integer.parseInt(numOfIntersection.getText()) > 0) {
            ImageView trackPiece;
            if (layer == 0) {
                trackPiece = new ImageView(new Image("resources/Images/intersection.png"));
            } else {
                trackPiece = new ImageView(new Image("resources/Images/intersection2.png"));
            }
            trackPiece.setFitWidth(75);
            trackPiece.setFitHeight(75);
            boardPane.getChildren().add(trackPiece);
            trackPiece.setId("intersectionTrack");
            trackPiece.setLayoutX(885);
            trackPiece.setLayoutY(410);
            trackPiece.setOnMousePressed(this::startMovingPiece);
            trackPiece.setOnMouseDragged(this::movePiece);
            trackPiece.setOnMouseReleased(this::finishMovingPiece);
            numOfIntersection.setText(Integer.toString(Integer.parseInt(numOfIntersection.getText()) - 1));
        }

    }

    public void createJumpTrack(MouseEvent evt) {
        if (Integer.parseInt(numOfJump.getText()) > 0) {
            ImageView trackPiece;
            if (layer == 0) {
                trackPiece = new ImageView(new Image("resources/Images/jump.png"));
            } else {
                trackPiece = new ImageView(new Image("resources/Images/jump2.png"));
            }
            trackPiece.setFitWidth(150);
            trackPiece.setFitHeight(75);
            boardPane.getChildren().add(trackPiece);
            trackPiece.setId("jumpTrack");
            trackPiece.setLayoutX(850);
            trackPiece.setLayoutY(410);
            trackPiece.setOnMousePressed(this::startMovingPiece);
            trackPiece.setOnMouseDragged(this::movePiece);
            trackPiece.setOnMouseReleased(this::finishMovingPiece);
            numOfJump.setText(Integer.toString(Integer.parseInt(numOfJump.getText()) - 1));
        }
    }

    public void createSpeedTrack(MouseEvent evt) {
        if (Integer.parseInt(numOfSpeed.getText()) > 0) {
            ImageView trackPiece;
            if (layer == 0) {
                trackPiece = new ImageView(new Image("resources/Images/speed.png"));
            } else {
                trackPiece = new ImageView(new Image("resources/Images/speed2.png"));
            }
            trackPiece.setFitWidth(75);
            trackPiece.setFitHeight(75);
            boardPane.getChildren().add(trackPiece);
            trackPiece.setId("speedTrack");
            trackPiece.setLayoutX(885);
            trackPiece.setLayoutY(410);
            trackPiece.setOnMousePressed(this::startMovingPiece);
            trackPiece.setOnMouseDragged(this::movePiece);
            trackPiece.setOnMouseReleased(this::finishMovingPiece);
            numOfSpeed.setText(Integer.toString(Integer.parseInt(numOfSpeed.getText()) - 1));
        }
    }

    public void generate(MouseEvent evt) throws InterruptedException, IOException {

        this.warningText.setText("");

        if (this.creative == false) {
            int intersection = 0; int straight = 0;int curves = 0;int jump = 0;int speed = 0;
            try {
                intersection = Integer.parseInt(this.numOfIntersection.getText()) + this.track.numOfPieces(0);
                straight = Integer.parseInt(this.numOfStraight.getText()) + this.track.numOfPieces(1);
                curves = Integer.parseInt(this.numOfCurves.getText()) + this.track.numOfPieces(2);
                jump = Integer.parseInt(this.numOfJump.getText()) + this.track.numOfPieces(3);
                speed = Integer.parseInt(this.numOfSpeed.getText()) + this.track.numOfPieces(4);
            }catch(Exception e){

            }
            if(intersection>0){
                this.warningText.setText("**Intersections not supported**");
            }else if (intersection + straight + curves + jump + speed + 1 >= 6 & intersection >=0& curves >=0& straight >=0& jump >=0& speed >=0&intersection + straight + curves + jump + speed + 1 < 125) {
                this.generatedTracks = this.generator.generate(intersection, straight, curves, jump, speed);
            } else {
                this.warningText.setText("Invalid amount of pieces");
            }
            if (!this.generatedTracks.isEmpty()) {
                this.empty();
                this.clear(0, "");
                this.track = this.generatedTracks.get(0);
                this.displayTrack();
                this.listIndex = 0;
                int num = listIndex + 1;
                this.nameText.setText("Generated Track #" + Integer.toString(num));
                this.warningText.setText(Integer.toString(this.generatedTracks.size()) + " Tracks Generated");
                this.generated = true;
            }
        } else {
            this.warningText.setText("Exit creative mode to generate");
        }
    }


    @FXML
    public void clearPiece(MouseEvent evt) {
        //new code
        if (chosenTrack != null && this.chosenPiece.type != 5) {
            this.track.removePiece(layer, this.chosenPiece.xValue, this.chosenPiece.yValue, this.chosenPiece);
            if (chosenTrack.getId().equals("straightTrack")) {
                numOfStraight.setText(Integer.toString(Integer.parseInt(numOfStraight.getText()) + 1));
            } else if (chosenTrack.getId().equals("intersectionTrack")) {
                numOfIntersection.setText(Integer.toString(Integer.parseInt(numOfIntersection.getText()) + 1));
            } else if (chosenTrack.getId().equals("curveTrack")) {
                numOfCurves.setText(Integer.toString(Integer.parseInt(numOfCurves.getText()) + 1));
            } else if (chosenTrack.getId().equals("speedTrack")) {
                numOfSpeed.setText(Integer.toString(Integer.parseInt(numOfSpeed.getText()) + 1));
            } else if (chosenTrack.getId().equals("jumpTrack")) {
                numOfJump.setText(Integer.toString(Integer.parseInt(numOfJump.getText()) + 1));
            }

            if (chosenTrack != null) {
                boardPane.getChildren().remove(chosenTrack);
                chosenTrack = null;
                System.gc();
            }
        }
    }


    @FXML
    public void rotateLeftPiece(MouseEvent evt) {

        //double value = chosenTrack.getRotate();
        double value = 0;
        Rotate rotation = new Rotate();
        rotation.setPivotX(37.5);
        rotation.setPivotY(37.5);
        rotation.setAngle(value - 90);
        chosenTrack.getTransforms().add(rotation);//Add the Rotate to the ImageView
        //new code
        Piece p = this.chosenPiece;
        p.rotate(1, p.type);
    }

    @FXML
    public void rotateRightPiece(MouseEvent evt) {
        // double value = chosenTrack.getRotate();
        double value = 0;
        Rotate rotation = new Rotate();
        rotation.setPivotX(37.5);
        rotation.setPivotY(37.5);
        rotation.setAngle(value + 90);
        chosenTrack.getTransforms().add(rotation);//Add the Rotate to the ImageView
        //new code
        Piece p = this.chosenPiece;
        p.rotate(-1, p.type);
    }

    @FXML
    public void upLayer(MouseEvent evt) {
        intersectionTrackBtn.setImage(new Image("resources/Images/intersection2.png"));
        straightTrackBtn.setImage(new Image("resources/Images/straight2.png"));
        curveTrackBtn.setImage(new Image("resources/Images/curves2.png"));
        speedTrackBtn.setImage(new Image("resources/Images/speed2.png"));
        jumpTrackBtn.setImage(new Image("resources/Images/jump2.png"));
        layer = 1;
    }

    @FXML
    public void dơwnLayer(MouseEvent evt) {
        intersectionTrackBtn.setImage(new Image("resources/Images/intersection.png"));
        straightTrackBtn.setImage(new Image("resources/Images/straight.png"));
        curveTrackBtn.setImage(new Image("resources/Images/curves.png"));
        speedTrackBtn.setImage(new Image("resources/Images/speed.png"));
        jumpTrackBtn.setImage(new Image("resources/Images/jump.png"));
        layer = 0;
    }

    @FXML
    public void openFile(ActionEvent evt) throws IOException, ClassNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open track File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            this.clear(0, "");
            FileInputStream fi = new FileInputStream(new File(selectedFile.getAbsolutePath()));
            ObjectInputStream oi = new ObjectInputStream(fi);
            this.track = (Track) oi.readObject();
            oi.close();
            fi.close();
            this.displayTrack();
            this.nameText.setText(selectedFile.getName().replaceAll(".txt", ""));
            numOfStraight.setText(Integer.toString(0));
            numOfCurves.setText(Integer.toString(0));
            numOfIntersection.setText(Integer.toString(0));
            numOfJump.setText(Integer.toString(0));
            numOfSpeed.setText(Integer.toString(0));
        }
    }

    @FXML
    void saveFile(ActionEvent event) throws IOException, IOException {
        this.track.name = this.nameText.getText();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Track File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        fileChooser.setInitialFileName(this.nameText.getText());
        File selectedFile = fileChooser.showSaveDialog(null);
        if (selectedFile != null) {
            FileOutputStream f = new FileOutputStream(new File(selectedFile.getAbsolutePath()));
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(this.track);
            o.close();
            f.close();
        }
    }

    @FXML
    public void clearAllPieces(ActionEvent evt) {
        this.clear(1, "");
    }

    private boolean clear(int value, String alert) {
        if (!this.track.pieces.isEmpty()) {
            int intersection = Integer.parseInt(this.numOfIntersection.getText()) + this.track.numOfPieces(0);
            int straight = Integer.parseInt(this.numOfStraight.getText()) + this.track.numOfPieces(1);
            int curves = Integer.parseInt(this.numOfCurves.getText()) + this.track.numOfPieces(2);
            int jump = Integer.parseInt(this.numOfJump.getText()) + this.track.numOfPieces(3);
            int speed = Integer.parseInt(this.numOfSpeed.getText()) + this.track.numOfPieces(4);
            boolean piecesLeft = true;
            while (piecesLeft) {
                try {
                    boolean passed = false;
                    for (Node node : this.boardPane.getChildren()) {
                        if (this.clearNode(node, value)) {
                            boardPane.getChildren().remove(node);
                            passed = true;
                        }
                        piecesLeft = passed;
                    }
                } catch (Exception e) {

                }
            }
            for (Grid level : this.track.map) {
                for (int y = 0; y < 10; y++) {
                    for (int x = 0; x < 10; x++) {
                        if (level.grid[x][y] != null) {
                            if (level.grid[x][y].type != 5) {
                                if (!alert.equals("safe")) {
                                    this.track.pieces.remove(level.grid[x][y]);
                                    level.grid[x][y] = null;
                                }
                            }
                        }
                    }
                }
            }
            numOfStraight.setText(Integer.toString(straight));
            numOfCurves.setText(Integer.toString(curves));
            numOfIntersection.setText(Integer.toString(intersection));
            numOfJump.setText(Integer.toString(jump));
            numOfSpeed.setText(Integer.toString(speed));
        }
        return true;
    }

    private boolean clearNode(Node node, int value) {
        boolean r = true;
        try {
            if (node.getId().equals("straightTrack")) {
            } else if (node.getId().equals("intersectionTrack")) {
            } else if (node.getId().equals("curvesTrack")) {
            } else if (node.getId().equals("speedTrack")) {
            } else if (node.getId().equals("jumpTrack")) {
            } else if (node.getId().equals("startTrack") && value == 0) {
            } else {
                r = false;
            }
        } catch (Exception e) {

        }
        return r;
    }

    private final InnerShadow shadow = new InnerShadow();

    private Rectangle currRect;

    private Point2D offset = new Point2D(0.0d, 0.0d);
    private boolean movingPiece = false;

    public void checkReleaseOutOfBoard(MouseEvent evt) {
        Point2D mousePoint_s = new Point2D(evt.getSceneX(), evt.getSceneY());
        if (!inBoard(mousePoint_s)) {
            leaveBoard(evt);
            evt.consume();
        }
    }

    public void leaveBoard(MouseEvent evt) {
        if (movingPiece) {

            final Timeline timeline = new Timeline();

            offset = new Point2D(0.0d, 0.0d);
            movingPiece = false;

            timeline.getKeyFrames().add(
                    new KeyFrame(Duration.millis(200),
                            new KeyValue(chosenTrack.opacityProperty(), 1.0d)
                    )
            );
            timeline.play();
        }
    }

    @FXML
    public void startMovingPiece(MouseEvent evt) {

        chosenTrack = (ImageView) evt.getSource();
        chosenTrack.setOpacity(1.0d);
        offset = new Point2D(evt.getX(), evt.getY());

        movingPiece = true;

        //new code
        Point2D mousePoint = new Point2D(evt.getX(), evt.getY());
        Point2D mousePointScene = chosenTrack.localToScene(mousePoint);

        Rectangle r = pickRectangle(mousePointScene.getX(), mousePointScene.getY());
        //moving an already placed piece
        if (r != null) {
            int xValue = Character.getNumericValue(r.toString().charAt(15));
            int yValue = Character.getNumericValue(r.toString().charAt(17));
            this.chosenPiece = this.track.map[layer].grid[xValue][yValue];
            this.previousX = xValue;
            this.previousY = yValue;
        } else {
            //placing a new piece
            //make new piece
            String piece = chosenTrack.idProperty().getValue();
            Piece p = null;
            if (piece.equals("straightTrack")) {
                p = new Piece(1);
                this.track.addPiece(p);
            } else if (piece.equals("curvesTrack")) {
                p = new Piece(2);
                this.track.addPiece(p);
            } else if (piece.equals("intersectionTrack")) {
                p = new Piece(0);
                this.track.addPiece(p);
            } else if (piece.equals("speedTrack")) {
                p = new Piece(4);
                this.track.addPiece(p);
            } else if (piece.equals("jumpTrack")) {
                p = new Piece(3);
                this.track.addPiece(p);
            }

            this.chosenPiece = p;
            this.previousX = -1;
            this.previousY = -1;

        }
    }

    @FXML
    public void movePiece(MouseEvent evt) {

        Point2D mousePoint = new Point2D(evt.getX(), evt.getY());
        Point2D mousePoint_s = new Point2D(evt.getSceneX(), evt.getSceneY());

        if (!inBoard(mousePoint_s)) {
            return;  // don't relocate() b/c will resize Pane
        }

        Point2D mousePoint_p = chosenTrack.localToParent(mousePoint);
        chosenTrack.relocate(mousePoint_p.getX() - offset.getX(), mousePoint_p.getY() - offset.getY());
    }

    private boolean inBoard(Point2D pt) {
        Point2D panePt = boardPane.sceneToLocal(pt);
        return panePt.getX() - offset.getX() >= 0.0d
                && panePt.getY() - offset.getY() >= 0.0d
                && panePt.getX() <= boardPane.getWidth()
                && panePt.getY() <= boardPane.getHeight();
    }

    @FXML
    //new function
    public boolean notMoved(int x, int y) {
        boolean sameX = (x == this.previousX);
        boolean sameY = (y == this.previousY);

        return sameX && sameY;
    }

    public boolean displayTrack() {


        int level = 0;
        for (Grid map : this.track.map) {
            for (int y = 0; y < 10; y++) {
                for (int x = 0; x < 10; x++) {
                    if (map.grid[x][y] != null) {
                        this.chosenPiece = map.grid[x][y];
                        Rectangle r = pickRectangle(x, y);
                        ImageView trackPiece = null;
                        try {
                            String name = "";
                            if (level == 0) {
                                if (chosenPiece.type == 0) {
                                    name = "intersection";
                                }
                                if (chosenPiece.type == 1) {
                                    name = "straight";
                                }
                                if (chosenPiece.type == 2) {
                                    name = "curves";
                                }
                                if (chosenPiece.type == 3) {
                                    name = "jump";
                                }
                                if (chosenPiece.type == 4) {
                                    name = "speed";
                                }
                                if (chosenPiece.type == 5) {
                                    name = "start";
                                }

                                if (name.equals("jump")) {
                                    trackPiece = new ImageView(new Image("resources/Images/jump.png"));
                                    trackPiece.setFitWidth(150);
                                    trackPiece.setFitHeight(75);
                                } else {
                                    trackPiece = new ImageView(new Image("resources/Images/" + name + ".png"));
                                    trackPiece.setFitWidth(75);
                                    trackPiece.setFitHeight(75);
                                }

                                trackPiece.setId(name + "Track");
                                trackPiece.setLayoutX(x * 75);
                                trackPiece.setLayoutY(60 + y * 75);
                                trackPiece.setOnMousePressed(this::startMovingPiece);
                                trackPiece.setOnMouseDragged(this::movePiece);
                                trackPiece.setOnMouseReleased(this::finishMovingPiece);
                                trackPiece.setPickOnBounds(true);
                                trackPiece.setPreserveRatio(true);
                                boardPane.getChildren().add(trackPiece);

                                final Timeline timeline = new Timeline();
                                timeline.setCycleCount(1);
                                timeline.setAutoReverse(false);
                                if (r != null) {
                                    if (log.isLoggable(Level.FINE)) {
                                        log.fine("[FINISH] r=" + r.getId());
                                    }

                                    Point2D rectScene = r.localToScene(r.getX(), r.getY());
                                    Point2D parent = boardPane.sceneToLocal(rectScene.getX(), rectScene.getY());

                                    timeline.getKeyFrames().add(
                                            new KeyFrame(Duration.millis(100),
                                                    new KeyValue(chosenTrack.layoutXProperty(), parent.getX()),
                                                    new KeyValue(chosenTrack.layoutYProperty(), parent.getY()),
                                                    new KeyValue(chosenTrack.opacityProperty(), 1.0d)
                                            )
                                    );

                                } else {
                                    timeline.getKeyFrames().add(
                                            new KeyFrame(Duration.millis(100),
                                                    new KeyValue(trackPiece.opacityProperty(), 1.0d)
                                            )
                                    );
                                }

                                timeline.play();

                            } else if (level == 1) {
                                if (chosenPiece.type == 0) {
                                    name = "intersection";
                                }
                                if (chosenPiece.type == 1) {
                                    name = "straight";
                                }
                                if (chosenPiece.type == 2) {
                                    name = "curves";
                                }
                                if (chosenPiece.type == 3) {
                                    name = "jump";
                                }
                                if (chosenPiece.type == 4) {
                                    name = "speed";
                                }
                                if (chosenPiece.type == 5) {
                                    name = "start";
                                }


                                if (name.equals("jump")) {
                                    trackPiece = new ImageView(new Image("resources/Images/jump2.png"));
                                    trackPiece.setFitWidth(150);
                                    trackPiece.setFitHeight(75);
                                } else {
                                    trackPiece = new ImageView(new Image("resources/Images/" + name + "2.png"));
                                    trackPiece.setFitWidth(75);
                                    trackPiece.setFitHeight(75);
                                }

                                trackPiece.setId(name + "Track");
                                trackPiece.setLayoutX(x * 75);
                                trackPiece.setLayoutY(60 + y * 75);
                                trackPiece.setOnMousePressed(this::startMovingPiece);
                                trackPiece.setOnMouseDragged(this::movePiece);
                                trackPiece.setOnMouseReleased(this::finishMovingPiece);
                                trackPiece.setPickOnBounds(true);
                                trackPiece.setPreserveRatio(true);
                                boardPane.getChildren().add(trackPiece);

                                final Timeline timeline = new Timeline();
                                timeline.setCycleCount(1);
                                timeline.setAutoReverse(false);
                                if (r != null) {
                                    if (log.isLoggable(Level.FINE)) {
                                        log.fine("[FINISH] r=" + r.getId());
                                    }

                                    Point2D rectScene = r.localToScene(r.getX(), r.getY());
                                    Point2D parent = boardPane.sceneToLocal(rectScene.getX(), rectScene.getY());

                                    timeline.getKeyFrames().add(
                                            new KeyFrame(Duration.millis(100),
                                                    new KeyValue(chosenTrack.layoutXProperty(), parent.getX()),
                                                    new KeyValue(chosenTrack.layoutYProperty(), parent.getY()),
                                                    new KeyValue(chosenTrack.opacityProperty(), 1.0d)
                                            )
                                    );

                                } else {
                                    timeline.getKeyFrames().add(
                                            new KeyFrame(Duration.millis(100),
                                                    new KeyValue(trackPiece.opacityProperty(), 1.0d)
                                            )
                                    );
                                }

                                timeline.play();

                            }
                        } catch (Exception e) {

                        }
                        //rotate if needed
                        Rotate rotation = new Rotate();
                        rotation.setPivotX(37.5);//Set the Pivot's X to be the same location as the Circle's X. This is only used to help you see the Pivot's point
                        rotation.setPivotY(37.5);//Set the Pivot's Y to be the same location as the Circle's Y. This is only used to help you see the Pivot's point
                        rotation.setAngle(0 - this.chosenPiece.direction * 90);
                        trackPiece.getTransforms().add(rotation);
                    }
                }
            }
            level += 1;
        }
        return true;
    }

    public void finishMovingPiece(MouseEvent evt) {

        offset = new Point2D(0.0d, 0.0d);

        Point2D mousePoint = new Point2D(evt.getX(), evt.getY());
        Point2D mousePointScene = chosenTrack.localToScene(mousePoint);

        Rectangle r = pickRectangle(mousePointScene.getX(), mousePointScene.getY());

        //new code
        //update grid spot for piece
        Piece p = this.chosenPiece;
        int x = Character.getNumericValue(r.toString().charAt(15));
        int y = Character.getNumericValue(r.toString().charAt(17));
        if (p != null) {
            p.setCoordinates(x, y);
        }
        this.track.map[this.layer].grid[x][y] = p;
        if (this.previousX != -1 && !notMoved(x, y)) {
            this.track.map[this.layer].grid[this.previousX][this.previousY] = null;
        }


        final Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);

        if (r != null) {
            if (log.isLoggable(Level.FINE)) {
                log.fine("[FINISH] r=" + r.getId());
            }

            Point2D rectScene = r.localToScene(r.getX(), r.getY());
            Point2D parent = boardPane.sceneToLocal(rectScene.getX(), rectScene.getY());

            timeline.getKeyFrames().add(
                    new KeyFrame(Duration.millis(100),
                            new KeyValue(chosenTrack.layoutXProperty(), parent.getX()),
                            new KeyValue(chosenTrack.layoutYProperty(), parent.getY()),
                            new KeyValue(chosenTrack.opacityProperty(), 1.0d)
                    )
            );

        } else {

            timeline.getKeyFrames().add(
                    new KeyFrame(Duration.millis(100),
                            new KeyValue(chosenTrack.opacityProperty(), 1.0d)
                    )
            );
        }

        timeline.play();

        movingPiece = false;
    }

    public void mouseMoved(MouseEvent evt) {

        Rectangle r = pickRectangle(evt);

        if (r == null) {

            if (currRect != null) {
                // deselect previous
                currRect.setEffect(null);
            }

            currRect = null;
            return;  // might be out of area but w/i scene
        }

        if (r != currRect) {

            if (currRect != null) {
                // deselect previous
                currRect.setEffect(null);
            }

            currRect = r;

            if (log.isLoggable(Level.FINE)) {
                log.fine("[MOVED] in " + currRect.getId());
            }

            if (currRect != null) {  // new selection
                currRect.setEffect(shadow);
            }
        }
    }

    private Rectangle pickRectangle(MouseEvent evt) {
        return pickRectangle(evt.getSceneX(), evt.getSceneY());
    }

    private Rectangle pickRectangle(double sceneX, double sceneY) {
        Rectangle pickedRectangle = null;
        for (Pane row : panes) {

            //
            // getX/Y == getSceneX/Y because handler registerd on Scene and
            // not node
            //

            Point2D mousePoint = new Point2D(sceneX, sceneY);
            Point2D mpLocal = row.sceneToLocal(mousePoint);

            if (row.contains(mpLocal)) {
                if (log.isLoggable(Level.FINEST)) {
                    log.finest("[PICK] selected row=" + row.getId());
                }

                for (Node cell : row.getChildrenUnmodifiable()) {

                    Point2D mpLocalCell = cell.sceneToLocal(mousePoint);

                    if (cell.contains(mpLocalCell)) {
                        if (log.isLoggable(Level.FINEST)) {
                            log.finest("[PICK] selected cell=" + cell.getId());
                        }
                        pickedRectangle = (Rectangle) cell;
                        break;
                    }
                }
                break;
            }
        }
        return pickedRectangle;
    }

    @FXML
    public void loadNext(MouseEvent evt) {
        this.warningText.setText(Integer.toString(this.generatedTracks.size()) + " Tracks Generated");
        if (this.generated) {
            if (listIndex + 1 < this.generatedTracks.size()) {
                this.listIndex += 1;
                this.clear(0, "safe");
                this.empty();
                this.track = this.generatedTracks.get(this.listIndex);
                this.displayTrack();
                int num = listIndex + 1;
                this.nameText.setText("Generated Track #" + Integer.toString(num));
            } else {
                this.warningText.setText("No next track");
            }
        } else {
            this.warningText.setText("Generate First");
        }

    }

    @FXML
    public void loadPrevious(MouseEvent evt) {
        this.warningText.setText(Integer.toString(this.generatedTracks.size()) + " Tracks Generated");
        if (this.generated) {
            if (listIndex - 1 >= 0 && listIndex < this.generatedTracks.size()) {
                this.nameText.setText("Generated Track #" + listIndex);
                this.listIndex -= 1;
                this.clear(0, "safe");
                this.empty();
                this.track = this.generatedTracks.get(this.listIndex);
                this.displayTrack();
                int num = listIndex + 1;
                this.nameText.setText("Generated Track #" + Integer.toString(num));
            } else {
                this.warningText.setText("No previous track");
            }
        } else {
            this.warningText.setText("Generate First");
        }
    }

    @FXML
    public void creative() {
        if (!this.creative) {
            this.creative = true;
            numOfIntersection.setText(Integer.toString(1000));
            numOfJump.setText(Integer.toString(1000));
            numOfCurves.setText(Integer.toString(1000));
            numOfStraight.setText(Integer.toString(1000));
            numOfSpeed.setText(Integer.toString(1000));
        } else {
            this.creative = false;
            numOfIntersection.setText(Integer.toString(0));
            numOfJump.setText(Integer.toString(0));
            numOfCurves.setText(Integer.toString(0));
            numOfStraight.setText(Integer.toString(0));
            numOfSpeed.setText(Integer.toString(0));
        }

    }

    void empty() {
        numOfStraight.setText(Integer.toString(0));
        numOfCurves.setText(Integer.toString(0));
        numOfIntersection.setText(Integer.toString(0));
        numOfJump.setText(Integer.toString(0));
        numOfSpeed.setText(Integer.toString(0));
    }

}

