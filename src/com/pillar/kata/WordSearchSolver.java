package com.pillar.kata;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordSearchSolver {

	List<String> wordBank;
	List<List<String>> puzzleMatrix;
	Map<String, List<Ordinal>> resultMap;
	
	public Map<String, List<String>> getResultMap() {
		Map<String, List<String>> friendlyMap = new HashMap<String, List<String>>();
		List<String> friendlyList = new ArrayList<String>();
		for(Map.Entry<String, List<Ordinal>> ords : resultMap.entrySet())
		{
			ords.getValue().forEach(v-> friendlyList.add(v.toString()));
			friendlyMap.put(ords.getKey(), friendlyList);
		}
		return friendlyMap;
	}


	public WordSearchSolver(List<String> wordBank, List<List<String>> puzzleMatrix)
	{
		this.wordBank = wordBank;
		this.puzzleMatrix =puzzleMatrix;
		resultMap = new HashMap<String, List<Ordinal>>();
	}


	public void LeftToRightMatch(String string) {

		String line = new String();
		for(int r=0; r<puzzleMatrix.size(); r++)
		{
			line = LeftToRightBuilder(r);
			for(int c=0; c<=puzzleMatrix.get(r).size()-string.length(); c++)
			{
				if(line.substring(c, c+string.length()).equalsIgnoreCase(string))
				{
					
					List<Ordinal> ords = new ArrayList<Ordinal>();
					for(int i=c; i<c+string.length(); i++)
					{
						ords.add(new Ordinal(r,i));
					}
					 resultMap.put(string, ords);
				}
			}
		}
		
	}
	
	private String LeftToRightBuilder(int row)
	{
		String line = new String();
		for(String c : puzzleMatrix.get(row))
		{
			line += c;
		}
		return line;
	}
	
	public void RightToLeftMatch(String string) {
		String line = new String();
		String revString = new StringBuilder(string).reverse().toString();
		for(int r=0; r<puzzleMatrix.size(); r++)
		{
			line = LeftToRightBuilder(r);
			for(int c=0; c<=puzzleMatrix.get(r).size()-revString.length(); c++)
			{
				if(line.substring(c, c+revString.length()).equalsIgnoreCase(revString))
				{
					
					List<Ordinal> ords = new ArrayList<Ordinal>();
					for(int i=c+string.length(); i>=c; i++)
					{
						ords.add(new Ordinal(r,i));
					}
					 resultMap.put(string, ords);
				}
			}
		}
		
	}
	
	
	
	private class Ordinal {
	    public Integer row;
	    public Integer column;
	    
	    public Ordinal(Integer row, Integer column)
	    {
	    	this.row =row;
	    	this.column=column;
	    }
	    public Ordinal()
	    {
	    	this.row = null;
	    	this.column = null;
	    }
	    
	    public String toString()
	    {
	    	return "("+row.toString()+", "+column.toString()+")";
	    }
	}

	
}
