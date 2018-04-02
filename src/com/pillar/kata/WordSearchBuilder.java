package com.pillar.kata;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordSearchBuilder {

	List<String> wordBank;
	Character[][] charMatrix;
	private Reader source;
	
	public WordSearchBuilder(Reader source) {
        this.source = source;
        wordBank = readHeader();
      
    }
	
	public List<String> getWordBank() {
		return wordBank;
	}
	
	
    public Character[][] getCharMatrix() {
		return charMatrix;
	}

	List<String> readHeader() {
      BufferedReader reader = new BufferedReader(source);
      return reader.lines()
                    .findFirst()
                    .map(mapper)
                    .get();
       
    }
    
 
    
    Function<String, List<String>> mapper  = line -> Arrays.asList(line.split(" "));
}
