package com.github.smk7758.MemoWriter;

import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class Controller {
	@FXML
	TextArea textArea;

	@FXML
	public void save() {
		System.out.println("A");
		System.out.println(textArea.getText());
	}

	@FXML
	public void openFile() {
		Path path = FileIO.selectFile(Main.primaryStage);
		System.out.println(path.toString()); // TODO

		Optional<List<String>> textLine = FileIO.readFile(path, Charset.forName("Windows-31j"));
		if (textLine.isPresent()) {
			showTextLine(textLine.get());
		} else {
			System.err.println("Cannot open the file.");
		}
	}

	public void showText(String text) {
		textArea.setText(text);
	}

	public void showTextLine(List<String> textLine) {
		for (String text : textLine) {
			addTextLine(text);
		}
	}

	public void addTextLine(String text) {
		textArea.appendText(text + System.lineSeparator());
	}
}
