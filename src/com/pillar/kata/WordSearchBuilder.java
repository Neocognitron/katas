package com.pillar.kata;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordSearchBuilder {

	List<String> wordBank;
	List<List<String>> puzzleMatrix;
	private Reader source;
	
	public WordSearchBuilder(Reader source) {
        this.source = source;
        try {
        	wordBank = new ArrayList<String>();
        	puzzleMatrix = new ArrayList<List<String>>();
			readPuzzleFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
    }
	
	public List<String> getWordBank() {
		return wordBank;
	}
	
	
    public List<List<String>> getPuzzleMatrix() {
		return puzzleMatrix;
	}

	void readPuzzleFile() throws IOException {
      BufferedReader reader = new BufferedReader(source);
      wordBank = Arrays.asList(reader.readLine().split(",")); 
      reader.lines().forEach(line -> buildPuzzle(line));
                  
    }
    
  
    
    private void buildPuzzle(String line) {
    	puzzleMatrix.add(new ArrayList<String>(Arrays.asList(line.split(","))));
	}

	public boolean find(String word) {
		// TODO Auto-generated method stub
		return false;
	}




}
