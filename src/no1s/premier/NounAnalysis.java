package no1s.premier;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static java.util.function.Function.*;
import static java.util.stream.Collectors.*;

import org.atilika.kuromoji.Token;
import org.atilika.kuromoji.Tokenizer;

/**
 * https://github.com/atilika/kuromoji/downloads
 * 上記URLからzipファイルをダウンロード、jarを持ってきた
 */
public class NounAnalysis {
	public static void main(String... args) throws IOException {
		final String noun = "名詞";
		
		String text = readTextFile("res/data.txt");
		
		Tokenizer tokenizer = Tokenizer.builder().build();
		List<Token> tokens = tokenizer.tokenize(text);
		List<String> wordList = new ArrayList<String>();
		
		for(Token token : tokens) {
			String[] analysisList = token.getAllFeaturesArray();
			// position 0 -> 品詞
			if(analysisList[0].equals(noun)) {
				wordList.add(token.getSurfaceForm());
			}
		}
		// グループ化
		Map<String, Long> map = wordList.stream()
									.collect(groupingBy(identity(), counting()));
		
		// ソート
		List<Entry<String, Long>> entryList = new ArrayList<Entry<String, Long>>(map.entrySet());
		Collections.sort(entryList, new Comparator<Entry<String, Long>>() {
			public int compare(Entry<String, Long> obj1, Entry<String, Long> obj2) {
				return obj2.getValue().compareTo(obj1.getValue());
			}
		});
		
		for(Entry<String, Long> entry : entryList) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}
	
	private static String readTextFile(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
	}
}
