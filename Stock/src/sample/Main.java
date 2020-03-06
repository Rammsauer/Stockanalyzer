package sample;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{

        Controller controller = Controller.getInstance();

        Parent root = controller.getRoot();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Stockoverview");
        stage.setResizable(false);
        stage.setHeight(432);
        stage.setWidth(800);
        stage.setMinHeight(432);
        stage.setMinWidth(800);
        controller.init();

        Image icon = new Image("file:Stock/1.jpeg");
        stage.getIcons().add(icon);

        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
