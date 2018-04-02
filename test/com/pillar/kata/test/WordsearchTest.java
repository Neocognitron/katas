package com.pillar.kata.test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

import com.pillar.kata.WordSearchBuilder;

public class WordsearchTest {

	@Test
	public void testBuildWordBankFromFileStream() throws IOException {

		List<String> expected = Arrays.asList("One", "Two", "Three");
		byte[] source = "One Two Three".getBytes();
        Reader reader = new StringReader(new String(source) );
		WordSearchBuilder wsb = new WordSearchBuilder(reader);
		
		List<String> wordBankBuildResult = wsb.getWordBank();
		assertEquals(wordBankBuildResult, expected);
	}

}
