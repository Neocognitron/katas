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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.pillar.kata.WordSearchBuilder;
import com.pillar.kata.WordSearchSolver;

public class WordsearchTest {
	
	WordSearchBuilder builder;
	WordSearchSolver solver;
	@Before
	public void setup() throws IOException
	{
	
		Path path = Paths.get("src/resources", "testTest");
        Reader reader = Files.newBufferedReader(
            path, Charset.forName("UTF-8"));
		builder = new WordSearchBuilder(reader);
		solver = new WordSearchSolver(builder.getWordBank(), builder.getPuzzleMatrix());
	}

	@Test
	public void testBuildWordBankFromReader() 
	{

		List<String> expected = Arrays.asList("ONE", "TWO", "THREE","FOUR");
		List<String> wordBankBuildResult = builder.getWordBank();
		assertEquals(expected, wordBankBuildResult);
	}
	

	@Test
	public void testBuildPuzzle() 
	{
		List<List<String>> expected = new ArrayList<List<String>>();
		expected.add(Arrays.asList("O","N","E","T","E"));
		expected.add(Arrays.asList("W","F","G","E","U")); 
		expected.add(Arrays.asList("T","E","R","O","O"));
		expected.add(Arrays.asList("F","H","U","R","F"));
		expected.add(Arrays.asList("T","F","O","U","R"));


		List<List<String>> wordBankBuildResult = builder.getPuzzleMatrix();
		assertEquals(expected, wordBankBuildResult);
	}

	@Test
	public void  testSolverLtoRStringBuild()
	{
		solver.LeftToRightMatch("ONETE");
		Map<String, List<String>> test = solver.getResultMap();
		List<String> expected = new ArrayList<String>();
		expected.add("(0, 0)");
		expected.add("(1, 0)");
		expected.add("(2, 0)");
		expected.add("(3, 0)");
		expected.add("(4, 0)");
		
		assertEquals(expected, test.get("ONETE"));	
	}
	
	@Test
	public void testSolverLtoRFind()
	{
		solver.LeftToRightMatch("FOUR");
		Map<String, List<String>> test = solver.getResultMap();
		List<String> expected = new ArrayList<String>();
		expected.add("(1, 4)");
		expected.add("(2, 4)");
		expected.add("(3, 4)");
		expected.add("(4, 4)");
		
		assertEquals(expected, test.get("FOUR"));
	}
	
	@Test
	public void testSolverRtoLBuild() {
		solver.RightToLeftMatch("ETENO");
		Map<String, List<String>> test = solver.getResultMap();
		List<String> expected = new ArrayList<String>();
		expected.add("(4, 0)");
		expected.add("(3, 0)");
		expected.add("(2, 0)");
		expected.add("(1, 0)");
		expected.add("(0, 0)");
		
		assertEquals(expected, test.get("ETENO"));
	}
	
	@Test
	public void testSolverRtoLFind() {
		solver.RightToLeftMatch("TEN");
		Map<String, List<String>> test = solver.getResultMap();
		List<String> expected = new ArrayList<String>();
		expected.add("(3, 0)");
		expected.add("(2, 0)");
		expected.add("(1, 0)");
		
		assertEquals(expected, test.get("TEN"));
	}
	
	@Test
	public void testTopToBottomBuild() {
		
		solver.TopToBottomMatch("OWTFT");
		Map<String, List<String>> test = solver.getResultMap();
		List<String> expected = new ArrayList<String>();
		expected.add("(0, 0)");
		expected.add("(0, 1)");
		expected.add("(0, 2)");
		expected.add("(0, 3)");
		expected.add("(0, 4)");
		
		assertEquals(expected, test.get("OWTFT"));
	}
	
	@Test
	public void testTopToBottomMatch()
	{

		solver.TopToBottomMatch("GRU");
		Map<String, List<String>> test = solver.getResultMap();
		List<String> expected = new ArrayList<String>();
		expected.add("(2, 1)");
		expected.add("(2, 2)");
		expected.add("(2, 3)");
		
		assertEquals(expected, test.get("GRU"));
	}
}
