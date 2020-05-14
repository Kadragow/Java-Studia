import java.io.IOException;
import java.util.Map;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Node4 extends Application {

    private static final String APP_TITLE_PREFIX = "Simple node: ";
    private static final String ICON_PATH = "icon.png";
    private static final String CSS_PATH = "application.css";



    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/SoapAppWindow.fxml"));
            Pane pane = loader.load();

            SoapAppController simpleNodeController = (SoapAppController) loader.getController();

            int nodePort = 5003;
            int nextNodePort = 5000;
            String nodeId = "NodeD";

            SoapApp soapApp = new SoapApp(nodePort, nodeId, nextNodePort);
            simpleNodeController.setSimpleNode(soapApp);

            soapApp.setController(simpleNodeController);

            primaryStage.setOnCloseRequest(event -> {
                try {
                    soapApp.stopListening();
                    stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            soapApp.startListening();

            Scene scene = new Scene(pane);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}