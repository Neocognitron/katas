package com.pillar.kata;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws IOException
	{
		 Path path = Paths.get("src/resources", "puzzleSample");
         Reader reader = Files.newBufferedReader(
             path, Charset.forName("UTF-8"));
		WordSearchBuilder builder = new WordSearchBuilder(reader);
		WordSearchSolver solver = new WordSearchSolver(builder.getWordBank(), builder.getPuzzleMatrix());
		solver.solve();
		Map<String, List<String>> results = solver.getResultMap();
		for(String word: builder.getWordBank())
		{
			
			printItPretty(word, results.get(word));
		}
		
	}

	private static void printItPretty(String word, List<String> results) {
		StringBuilder sbuilt = new StringBuilder(word+": ");
		for(int i = 0; i<results.size();i++)
		{
			sbuilt.append(results.get(i));
			if(i>=0 && i<results.size()-1) sbuilt.append(",");
		}
		System.out.println(sbuilt);
	}
}
