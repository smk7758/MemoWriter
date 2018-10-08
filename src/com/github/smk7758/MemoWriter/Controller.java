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

	public void initialize() {
		Main.primaryStage.showingProperty().addListener((observable, oldValue, newValue) -> {
			if (oldValue == true && newValue == false) {
				// TODO
				System.out.println("Close.");
			}
		});
	}

	@FXML
	public void save() {
		// 空ならファイル名をつけて保存。
		if (isEmpty(filePath)) {
			saveAs();
			return;
		}
		FileIO.writeFile(filePath.get(), getTextLine());
		Main.printDebug("Saved to: " + filePath.get().toString());
	}

	@FXML
	public void saveAs() {
		// 空ならSaveしない。
		if (isEmpty(filePath = FileIO.selectSaveFile(Main.primaryStage))) {
			return;
		}

		FileIO.writeFile(filePath.orElseGet(() -> Paths.get("")), getTextLine());
		Main.printDebug("Save As: " + filePath.get().toString());
	}

	@FXML
	public void openFile() {
		// 空なら再帰的に。
		if (isEmpty(filePath = FileIO.selectFile(Main.primaryStage))) {
			openFile();
			return;
		}

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

	public boolean isEmpty(Optional<Path> filePath) {
		return !filePath.isPresent();
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
