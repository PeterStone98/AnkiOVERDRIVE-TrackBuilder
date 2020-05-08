package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.transform.Rotate;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.ScrollPane;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;



public class HomePage {
    @FXML
    ImageView imageSong;
    // Music
    MediaPlayer mediaPlayer;
    int countClick;
    @FXML
    ImageView iconPlay;
    boolean playSong; // true is play, false is pause
    @FXML
    Slider volumeSlider;
    private final Logger log = Logger.getLogger("HomePage");

    @FXML
    public void initialize() {
        RotateTransition rt = new RotateTransition(Duration.millis(20000), imageSong);
        rt.setByAngle(1440);
        rt.setCycleCount(1000);
        rt.setAutoReverse(false);
        rt.play();
        playSong = true;// true is play, false is pause
        countClick = 0;
        playSoundBackGround();
        mediaPlayer.setVolume(0.3);
        volumeSlider.setValue(mediaPlayer.getVolume() * 100);

        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mediaPlayer.setVolume(volumeSlider.getValue() / 100);
            }
        });

    }
    public void nextScene(ActionEvent event) throws IOException
    {
        mediaPlayer.stop();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("sample.fxml"));
        Parent view2 = fxmlLoader.load();
        Controller c = fxmlLoader.getController();
        c.initialize(playSong);// true is play, false is pause
        Scene scene2 = new Scene(view2);
        scene2.getStylesheets().add(getClass().getResource("/resources/sample-css/stylesheet.css").toExternalForm());
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();

        FXMLLoader fxmlLoader1 = new FXMLLoader(Main.class.getResource("introduction.fxml"));
        Parent view4 = fxmlLoader1.load();
        Scene scene4 = new Scene(view4);
        Stage window1 = new Stage();
        window1.setScene(scene4);
        window1.show();
    }
    public void goAboutUs(ActionEvent event) throws IOException
    {
        mediaPlayer.stop();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AboutUs.fxml"));
        Parent view3 = fxmlLoader.load();
        Scene scene3 = new Scene(view3);
        scene3.getStylesheets().add(getClass().getResource("/resources/sample-css/aboutus.css").toExternalForm());
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene3);
        window.show();
    }
    public void playSoundBackGround()
    {
        Media sound;
        Random randomSongs = new Random();
        int indexSong = randomSongs.nextInt(3);

        if (indexSong == 1)
        {
            sound = new Media(new File("sound1.mp3").toURI().toString());
        }
        else if (indexSong == 2)
        {
            sound = new Media(new File("sound2.mp3").toURI().toString());
        }
        else {
            sound = new Media(new File("sound3.mp3").toURI().toString());
        }
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }
    public void playandpauseSong()
    {
        countClick++;
        if (countClick % 2 == 0)
        {
            iconPlay.setImage(new Image("resources/Images/homepage/playicon2.png"));
            playSong = true;
            mediaPlayer.play();
        }
        else {
            iconPlay.setImage(new Image("resources/Images/homepage/playicon.png"));
            playSong = false;
            mediaPlayer.pause();
        }
    }
    public void nextSong()
    {
        MediaPlayer mediaPlayer_temp;
        Media sound;
        Random randomSongs = new Random();
        int indexSong = randomSongs.nextInt(3);

        if (indexSong == 1)
        {
            sound = new Media(new File("sound1.mp3").toURI().toString());
        }
        else if (indexSong == 2)
        {
            sound = new Media(new File("sound2.mp3").toURI().toString());
        }
        else {
            sound = new Media(new File("sound3.mp3").toURI().toString());
        }
        mediaPlayer.stop();
        mediaPlayer_temp = new MediaPlayer(sound);
        mediaPlayer = mediaPlayer_temp;
        mediaPlayer.play();
    }
}
