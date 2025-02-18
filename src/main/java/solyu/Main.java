package solyu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The entry point of the JavaFX UI for Solyu.
 */
public class Main extends Application {
    private Solyu solyu = new Solyu();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane root = fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Solyu Chatbot");
            stage.show();

            MainWindow controller = fxmlLoader.getController();
            controller.setSolyu(solyu);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
