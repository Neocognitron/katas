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
		expected.add("(0,0)");
		expected.add("(1,0)");
		expected.add("(2,0)");
		expected.add("(3,0)");
		expected.add("(4,0)");
		
		assertEquals(expected, test.get("ONETE"));	
	}
	
	@Test
	public void testSolverLtoRFind()
	{
		solver.LeftToRightMatch("FOUR");
		Map<String, List<String>> test = solver.getResultMap();
		List<String> expected = new ArrayList<String>();
		expected.add("(1,4)");
		expected.add("(2,4)");
		expected.add("(3,4)");
		expected.add("(4,4)");
		
		assertEquals(expected, test.get("FOUR"));
	}
	
	@Test
	public void testSolverRtoLBuild() {
		solver.RightToLeftMatch("ETENO");
		Map<String, List<String>> test = solver.getResultMap();
		List<String> expected = new ArrayList<String>();
		expected.add("(4,0)");
		expected.add("(3,0)");
		expected.add("(2,0)");
		expected.add("(1,0)");
		expected.add("(0,0)");
		
		assertEquals(expected, test.get("ETENO"));
	}
	
	@Test
	public void testSolverRtoLFind() {
		solver.RightToLeftMatch("TEN");
		Map<String, List<String>> test = solver.getResultMap();
		List<String> expected = new ArrayList<String>();
		expected.add("(3,0)");
		expected.add("(2,0)");
		expected.add("(1,0)");
		
		assertEquals(expected, test.get("TEN"));
	}
	
	@Test
	public void testTopToBottomBuild() {
		
		solver.TopToBottomMatch("OWTFT");
		Map<String, List<String>> test = solver.getResultMap();
		List<String> expected = new ArrayList<String>();
		expected.add("(0,0)");
		expected.add("(0,1)");
		expected.add("(0,2)");
		expected.add("(0,3)");
		expected.add("(0,4)");
		
		assertEquals(expected, test.get("OWTFT"));
	}
	
	@Test
	public void testTopToBottomMatch()
	{

		solver.TopToBottomMatch("GRU");
		Map<String, List<String>> test = solver.getResultMap();
		List<String> expected = new ArrayList<String>();
		expected.add("(2,1)");
		expected.add("(2,2)");
		expected.add("(2,3)");
		
		assertEquals(expected, test.get("GRU"));
	}
	
	@Test
	public void testBottomToTopBuild()
	{
		solver.BottomToTopMatch("RFOUE");
		Map<String, List<String>> test = solver.getResultMap();
		List<String> expected = new ArrayList<String>();
		expected.add("(4,4)");
		expected.add("(4,3)");
		expected.add("(4,2)");
		expected.add("(4,1)");
		expected.add("(4,0)");
		
		assertEquals(expected, test.get("RFOUE"));
	}
	
	@Test
	public void testBottomToTopMatch()
	{
		solver.BottomToTopMatch("TWO");
		Map<String, List<String>> test = solver.getResultMap();
		List<String> expected = new ArrayList<String>();
		expected.add("(0,2)");
		expected.add("(0,1)");
		expected.add("(0,0)");
		
		assertEquals(expected, test.get("TWO"));
	}
	
	@Test
	public void testDiagonalLtoRBuilder()
	{
		solver.DiagonalLeftToRightMatchForward("OFRRR");
		Map<String, List<String>> test = solver.getResultMap();
		List<String> expected = new ArrayList<String>();
		expected.add("(0,0)");
		expected.add("(1,1)");
		expected.add("(2,2)");
		expected.add("(3,3)");
		expected.add("(4,4)");
		
		assertEquals(expected, test.get("OFRRR"));
	}
	
	@Test
	public void testDiagonalLtoRMatch()
	{
		solver.DiagonalLeftToRightMatchForward("THO");
		Map<String, List<String>> test = solver.getResultMap();
		List<String> expected = new ArrayList<String>();
		expected.add("(0,2)");
		expected.add("(1,3)");
		expected.add("(2,4)");
		
		assertEquals(expected, test.get("THO"));
	
		solver.DiagonalLeftToRightMatchForward("EEO");
		test = solver.getResultMap();
		List<String> newExpected = new ArrayList<String>();
		newExpected.add("(2,0)");
		newExpected.add("(3,1)");
		newExpected.add("(4,2)");
		
		assertEquals(newExpected, test.get("EEO"));
	}
	
	@Test
	public void testUpperDiagonalLtoRBackwardMatch()
	{
		
		solver.DiagonalLeftToRightMatchBackward("FOG");
		Map<String, List<String>> test = solver.getResultMap();
		List<String> expected = new ArrayList<String>();
		expected.add("(4,3)");
		expected.add("(3,2)");
		expected.add("(2,1)");
		assertEquals(expected, test.get("FOG"));
	}
	@Test
	public void testLowerDiagonalLtoRBackwardMatch()
	{
		
		solver.DiagonalLeftToRightMatchBackward("UEW");
		Map<String, List<String>> test = solver.getResultMap();
		List<String> expected = new ArrayList<String>();
		expected.add("(3,2)");
		expected.add("(2,1)");
		expected.add("(1,0)");
		assertEquals(expected, test.get("UEW"));
	}
	
	@Test
	public void testBottomtoTopDiagonalLtoRReadOrderMatch()
	{
		//Finds full row
		solver.DiagonalBottomUpMatchForward("THREE");
		Map<String, List<String>> test = solver.getResultMap();
		List<String> expected = new ArrayList<String>();
		expected.add("(0,4)");
		expected.add("(1,3)");
		expected.add("(2,2)");
		expected.add("(3,1)");
		expected.add("(4,0)");
		assertEquals(expected, test.get("THREE"));
		
		//finds diagonal in right half of matrix
		solver.DiagonalBottomUpMatchForward("ORO");
		test = solver.getResultMap();
		expected = new ArrayList<String>();
		expected.add("(2,4)");
		expected.add("(3,3)");
		expected.add("(4,2)");
		
		assertEquals(expected, test.get("ORO"));
		
		//finds diagonal in left half of diagonal
		solver.DiagonalBottomUpMatchForward("FEG");
		test = solver.getResultMap();
		expected = new ArrayList<String>();
		expected.add("(0,3)");
		expected.add("(1,2)");
		expected.add("(2,1)");
		
		assertEquals(expected, test.get("FEG"));
	}
	
	@Test
	public void testBottomToTopDiagonalRtoLReadOrderMatch()
	{
		solver.DiagonalBottomUpMatchBackward("EERHT");
		Map<String, List<String>> test = solver.getResultMap();
		List<String> expected = new ArrayList<String>();
		expected.add("(4,0)");
		expected.add("(3,1)");
		expected.add("(2,2)");
		expected.add("(1,3)");
		expected.add("(0,4)");
		assertEquals(expected, test.get("EERHT"));
	}
	
	@Test
	public void matchWordsFromBank()
	{
		solver.solve();
		Map<String, List<String>> test = solver.getResultMap();
		List<String> expected = new ArrayList<String>();
		expected.add("(1,4)");
		expected.add("(2,4)");
		expected.add("(3,4)");
		expected.add("(4,4)");
		assertEquals(expected, test.get("FOUR"));
		
		expected = new ArrayList<String>();
		expected.add("(0,4)");
		expected.add("(1,3)");
		expected.add("(2,2)");
		expected.add("(3,1)");
		expected.add("(4,0)");
		assertEquals(expected, test.get("THREE"));
		
		expected = new ArrayList<String>();
		expected.add("(0,2)");
		expected.add("(0,1)");
		expected.add("(0,0)");
		assertEquals(expected, test.get("TWO"));
		
		expected = new ArrayList<String>();
		expected.add("(0,0)");
		expected.add("(1,0)");
		expected.add("(2,0)");	
		assertEquals(expected, test.get("ONE"));
	}

}
