package com.github.smk7758.MemoWriter;

import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class Controller {
	Optional<Path> filePath = Optional.empty();

	@FXML
	TextArea textArea;

	@FXML
	public void save() {
		FileIO.writeFile(filePath.orElseGet(() -> FileIO.selectFile(Main.primaryStage).get()), getTextLine());
		Main.printDebug("Saved to: " + filePath.get().toString());
	}

	@FXML
	public void saveAs() {
		filePath = FileIO.selectSaveFile(Main.primaryStage);
		FileIO.writeFile(filePath.orElseGet(() -> Paths.get("")), getTextLine());
		Main.printDebug("Save As: " + filePath.get().toString());
	}

	@FXML
	public void openFile() {
		filePath = FileIO.selectFile(Main.primaryStage);
		Main.printDebug("Open File Path: " + filePath.orElseGet(() -> Paths.get("")).toString());

		Optional<List<String>> textLine = FileIO.readFile(filePath.orElseGet(() -> Paths.get("")),
				Charset.forName("Windows-31j"));
		if (textLine.isPresent()) {
			showTextLine(textLine.get());
		} else {
			System.err.println("Cannot open the file.");
		}
	}

	/**
	 * 未完成
	 *
	 * @param text
	 */
	@FXML
	public void showMessage(String text) {
	}

	/**
	 * テキストを取得します。
	 *
	 * @return 改行をListの要素として表現したテキスト
	 */
	public List<String> getTextLine() {
		return Utilities.convertLine(textArea.getText());
	}

	/**
	 * テキストを初期化します。
	 */
	public void clearText() {
		textArea.clear();
	}

	/**
	 * 改行コード付きのテキストを用いて、textAreaに表示をします。
	 *
	 * @param text 改行コード付きのテキスト
	 */
	public void setText(String text) {
		textArea.setText(text);
	}

	/**
	 * textAreaにテキストを表示します。
	 *
	 * @param textLine 改行をListの要素として表現したテキスト
	 */
	public void showTextLine(List<String> textLine) {
		clearText();
		addTextLine(textLine);
	}

	public void addTextLine(List<String> textLine) {
		for (String text : textLine) {
			addTextLine(text);
		}
	}

	/**
	 * textAreaにテキストを追加します。
	 *
	 * @param text 改行を補完して、textを一行、表示します。
	 */
	public void addTextLine(String text) {
		textArea.appendText(text + System.lineSeparator());
	}
}
