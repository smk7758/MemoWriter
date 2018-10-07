package com.github.smk7758.MemoWriter;

import java.util.ArrayList;
import java.util.List;

public class Utilities {

	public static List<String> convertLine(String text) {
		List<String> textLine = new ArrayList<>();
		String[] separatedText = text.split(System.lineSeparator());
		for (int i = 0; i < separatedText.length; i++) {
			textLine.add(separatedText[i]);
		}
		return textLine;
	}
}
