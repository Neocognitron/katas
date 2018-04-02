package com.pillar.kata.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.pillar.kata.WordSearchBuilder;

public class WordsearchTest {
	
	WordSearchBuilder builder;
	@Before
	public void setup() throws IOException
	{
	
		Path path = Paths.get("src/resources", "testTest");
        Reader reader = Files.newBufferedReader(
            path, Charset.forName("UTF-8"));
		builder = new WordSearchBuilder(reader);
	}

	@Test
	public void testBuildWordBankFromReader() 
	{

		List<String> expected = Arrays.asList("One", "Two", "Three");
		List<String> wordBankBuildResult = builder.getWordBank();
		assertEquals(wordBankBuildResult, expected);
	}
	

	@Test
	public void testBuildPuzzle() 
	{
		List<List<String>> expected = new ArrayList<List<String>>();
		expected.add(Arrays.asList("A","B","C","D"));
		expected.add(Arrays.asList("E","F","G","H")); 
		expected.add(Arrays.asList("I","J","K","L"));
		expected.add(Arrays.asList("M","N","O","P"));

		List<List<String>> wordBankBuildResult = builder.getPuzzleMatrix();
		assertEquals(wordBankBuildResult, expected);
	}

	@Test
	public void  testBuildLtoRLinesFromPuzzleMatrix()
	{
		
	}
}
