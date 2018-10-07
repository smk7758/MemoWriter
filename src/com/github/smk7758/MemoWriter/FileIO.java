package com.github.smk7758.MemoWriter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class FileIO {
	public static void writeFile(Path filePath, List<String> textLine) {
		try (BufferedWriter bw = Files.newBufferedWriter(filePath)) {
			for (String text : textLine) {
				bw.write(text + System.lineSeparator());
			}
		} catch (IOException ex) {
			Main.printDebug(ex.getMessage());
		}
	}

	// public static String readFile(Path filePath, Charset charset) {
	// String textLine = "";
	// try {
	// List<String> textLines = Files.readAllLines(filePath, charset);
	// for (String text : textLines) {
	// textLine += text;
	// }
	// } catch (MalformedInputException ex) {
	// System.err.println("The charset is not correct.");
	// } catch (IOException ex) {
	// ex.printStackTrace();
	// }
	// return textLine;
	// }

	public static Optional<List<String>> readFile(Path filePath, Charset charset) {
		Optional<List<String>> textLine = Optional.empty();
		try {
			textLine = Optional.ofNullable(Files.readAllLines(filePath, charset));
		} catch (MalformedInputException ex) {
			System.err.println("The charset is not correct.");
			Main.printDebug(ex.getMessage());
		} catch (IOException ex) {
			Main.printDebug(ex.getMessage());
		}
		return textLine;
	}

	public static Path selectFile(Stage mainStage) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open File");
		fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("Text Files", "*.txt"),
				new ExtensionFilter("All Files", "*.*"));
		return fileChooser.showOpenDialog(mainStage).toPath();
	}

	public static Path selectSaveFile(Stage mainStage) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save File");
		fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("Text Files", "*.txt"),
				new ExtensionFilter("All Files", "*.*"));
		return fileChooser.showSaveDialog(mainStage).toPath();
	}
}
