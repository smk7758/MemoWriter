package com.github.smk7758.MemoWriter;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	public static final String SoftwareName = "MemoWriter_0.0.1";
	public static Stage primaryStage = null;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Main.primaryStage = primaryStage;
		try {
			Scene scene = new Scene(FXMLLoader.load(getClass().getResource("MemoWriter_0.0.1.fxml")));
			// Set ico
			// primaryStage.getIcons().add(new Image((getClass().getResource("OnlyDownloader_x16.ico").toString())));
			// primaryStage.getIcons().add(new Image((getClass().getResource("OnlyDownloader_x32.ico").toString())));
			// Set Title
			primaryStage.setTitle(SoftwareName);
			// // Set Window
			primaryStage.setResizable(false);
			// Set Scene
			primaryStage.setScene(scene);
			primaryStage.show();
			// primaryStage.onShownProperty();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
