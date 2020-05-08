package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AboutUs {

    public void backHomePage(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("HomePage.fxml"));
        Parent view1 = fxmlLoader.load();
        HomePage h = fxmlLoader.getController();
        Scene scene1 = new Scene(view1);
        scene1.getStylesheets().add(getClass().getResource("/resources/sample-css/homepage.css").toExternalForm());
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene1);
        window.show();
    }
}
