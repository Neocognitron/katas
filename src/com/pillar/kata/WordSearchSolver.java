package com.pillar.kata;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordSearchSolver {

	List<String> wordBank;
	List<List<String>> puzzleMatrix;
	Map<String, List<Ordinal>> resultMap;
	
	


	public WordSearchSolver(List<String> wordBank, List<List<String>> puzzleMatrix)
	{
		this.wordBank = wordBank;
		this.puzzleMatrix =puzzleMatrix;
		resultMap = new HashMap<String, List<Ordinal>>();
	}

	
	public void solve() {
		
		if(!wordBank.isEmpty())
		{
			for(String word : wordBank)
			{
				LeftToRightMatch(word);
				if(!resultMap.containsKey(word)) RightToLeftMatch(word);
				if(!resultMap.containsKey(word)) TopToBottomMatch(word);
				if(!resultMap.containsKey(word)) BottomToTopMatch(word);
				if(!resultMap.containsKey(word)) DiagonalLeftToRightMatchForward(word);
				if(!resultMap.containsKey(word)) DiagonalLeftToRightMatchBackward(word);
				if(!resultMap.containsKey(word)) DiagonalBottomUpMatchBackward(word);
				if(!resultMap.containsKey(word)) DiagonalBottomUpMatchForward(word); 
				
			}
		}
	}


	public void LeftToRightMatch(String string) {

		String line = new String();
		for(int r=0; r<puzzleMatrix.size(); r++)
		{
			line = LeftToRightBuilder(r);
			if(line.contains(string))
			{
				int j = line.indexOf(string);
					
				List<Ordinal> ords = new ArrayList<Ordinal>();
				for(int i=j; i<j+string.length(); i++)
				{
					ords.add(new Ordinal(i,r));
				}
				 resultMap.put(string, ords);
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
			if(line.contains(revString))
			{
				int j = line.indexOf(revString);
					
				List<Ordinal> ords = new ArrayList<Ordinal>();
				for(int i=j+revString.length()-1; i>=j; i--)
				{
					ords.add(new Ordinal(i,r));
				}
				 resultMap.put(string, ords);
			}
		}
		
	}
	
	public void TopToBottomMatch(String string)
	{
		String line = new String();
		for(int c=0; c<puzzleMatrix.get(0).size();c++)
		{
			line = TopToBottomBuilder(c);
			if(line.contains(string))
			{
				int j = line.indexOf(string);
				
				List<Ordinal> ords = new ArrayList<Ordinal>();
				for(int i=j; i<j+string.length(); i++)
				{
					ords.add(new Ordinal(c,i));
				}
				 resultMap.put(string, ords);
			}
		}
			
	}
	
	private String TopToBottomBuilder(int col) {
		String line = new String();
		
		for(int i=0; i<puzzleMatrix.size(); i++)
		{
			line += puzzleMatrix.get(i).get(col);
		}
		return line;
	}
	

	public void BottomToTopMatch(String string) {
		String line = new String();
		String revString = new StringBuilder(string).reverse().toString();
		for(int c=0; c<puzzleMatrix.get(0).size();c++)
		{
			line = TopToBottomBuilder(c);
			if(line.contains(revString)) {
				int j = line.indexOf(revString);
					
					List<Ordinal> ords = new ArrayList<Ordinal>();
					for(int i=j+revString.length()-1; i>=j; i--)
					{
						ords.add(new Ordinal(c,i));
					}
					 resultMap.put(string, ords);
			}
			
		}
		
	}
	
	public void DiagonalLeftToRightMatchForward(String string) {
		String line=new String();
		boolean matched = false;
			for(int m=0;m<puzzleMatrix.size();m++)
		{
			
				if(puzzleMatrix.size()- m>=string.length() && !matched)
				{
					line = DiagonalLeftToRightLower(m);
					
				}
				else line = "";
				
				if(!line.equals(""))
				{
					if(line.contains(string))
					{
						int j = line.indexOf(string);
							
							List<Ordinal> ords = new ArrayList<Ordinal>();
							for(int i=j+m; i<j+m+string.length(); i++)
							{
								
								ords.add(new Ordinal(i-m,i));
							}
							 resultMap.put(string, ords);
							 matched = true;
					}
				}
			}
		if(!matched)
		{
			for(int m=0;m<puzzleMatrix.size();m++)
			{
				
					if(puzzleMatrix.size()- m>=string.length()&& !matched)
					{
						line = DiagonalLeftToRightUpper(m);
					}
					else line = "";
					
					if(!line.equals(""))
					{
						if(line.contains(string)) {
							int j = line.indexOf(string);
								
								List<Ordinal> newOrds = new ArrayList<Ordinal>();
								
								for(int i=j+m; i<j+m+string.length(); i++)
								{
									
									System.out.println("Test="+i);
									newOrds.add(new Ordinal(i,i-m));
								}
								matched=true;
								resultMap.put(string, newOrds);
						}
					}
				}
			}
	}
	
	
	private String DiagonalLeftToRightLower(int r)
	{
		String line = new String();
		for(int i=0; i<puzzleMatrix.size(); i++)
		{
			if(r<puzzleMatrix.size())
				line += puzzleMatrix.get(r++).get(i);
		}
		return line;
	}
	
	
		private String DiagonalLeftToRightUpper(int r)
		{
			String line = new String();
			for(int i=0; i<puzzleMatrix.size(); i++)
			{
				if(r<puzzleMatrix.size())
					line += puzzleMatrix.get(i).get(r++);
			}
			return line;
		}
	
		public void DiagonalLeftToRightMatchBackward(String string) {

			String revString = new StringBuilder(string).reverse().toString();
			String line=new String();
			boolean matched = false;
				for(int m=0;m<puzzleMatrix.size();m++)
			{
				
					if(puzzleMatrix.size()- m>=revString.length() && !matched)
					{
						line = DiagonalLeftToRightLower(m);
					}
					else line = "";
					
					if(!line.equals(""))
					{
						
						if(line.contains(revString)) {
							int j = line.indexOf(revString);
		
							List<Ordinal> ords = new ArrayList<Ordinal>();
							for(int i=j+m+revString.length()-1; i>=j+m; i--)
							{
								
								ords.add(new Ordinal(i,i-m));
							}
							 resultMap.put(string, ords);
							 matched = true;
					}
				}
			}
			if(!matched)
			{
				for(int m=0;m<puzzleMatrix.size();m++)
				{
				
					if(puzzleMatrix.size()- m>=revString.length()&& !matched)
					{
						line = DiagonalLeftToRightUpper(m);
					}
					else line = "";
					
					if(!line.equals(""))
					{
						if(line.contains(revString)) 
						{
							int j = line.indexOf(revString);
								
							List<Ordinal> newOrds = new ArrayList<Ordinal>();
							
							for(int i=j+m+string.length()-1; i>=j+m; i--)
							{
								
								newOrds.add(new Ordinal(i,i-m));
							}
							matched=true;
							resultMap.put(string, newOrds);
						}
					}
				}
			}
		}
		
	public void DiagonalBottomUpMatchForward(String string) {
		boolean matched = false;
		for(int m=0;m<puzzleMatrix.size();m++)
		{
				if(puzzleMatrix.size()- m>=string.length() && !matched)
				{
					matched=DiagonalBottomUpLowerBuilder(m, string);
				}
				
		}
		if(!matched)
		{
			for(int m=puzzleMatrix.size()-1; m>0; m--)
			{
				matched=DiagonalBottomUpUpperBuilder(m, string);
					
			}
		}
	}
	
	public void DiagonalBottomUpMatchBackward(String string) {
		boolean matched = false;
		
		for(int m=0;m<puzzleMatrix.size();m++)
		{
				if(puzzleMatrix.size()- m>=string.length() && !matched)
				{
					matched=DiagonalBottomUpLowerBackwardBuilder(m, string);
				}
				
		}
		if(!matched)
		{
			for(int m=puzzleMatrix.size()-1; m>0; m--)
			{
				matched=DiagonalBottomUpUpperBackwardBuilder(m, string);
					
			}
		}
	}
	
	private boolean DiagonalBottomUpUpperBackwardBuilder(int m, String string) {
		String line = new String();
		String revString = new StringBuilder(string).reverse().toString();
		List<Ordinal> ords = new ArrayList<Ordinal>();
		
		boolean start=false;
		for(int i=0; i<puzzleMatrix.size(); i++)
		{
			if(m>-1) {
				
				if(revString.startsWith(puzzleMatrix.get(m).get(i)))
					start=true;
				if(start) {
					line += puzzleMatrix.get(m).get(i);
					ords.add(new Ordinal(i,m));
					if(line.equals(revString)) 
					{
						Collections.reverse(ords);//Hah! This amuses me greatly, for some reason.
						resultMap.put(string, ords);
						return true;
					}
				}
				m--;
			}
		}
		
		return false;
	}


	private boolean DiagonalBottomUpLowerBackwardBuilder(int m, String string) {
		String line = new String();
		String revString = new StringBuilder(string).reverse().toString();
		List<Ordinal> ords = new ArrayList<Ordinal>();
		boolean start=false;
		for(int i=puzzleMatrix.size()-1; i>-1; i--)
		{
			if(m<puzzleMatrix.size()) {
				
				if(revString.startsWith(puzzleMatrix.get(i).get(m)))
					start=true;
				if(start) {
					line += puzzleMatrix.get(i).get(m);
					ords.add(new Ordinal(m,i));
					if(line.equals(revString)) 
					{
						Collections.reverse(ords); //Hah! This amuses me greatly, for some reason.
						resultMap.put(string, ords);
						return true;
					}
				}
				m++;
			}
		}
		return false;
	}

	

	private boolean DiagonalBottomUpLowerBuilder(int m, String match)
	{
		String line = new String();
		List<Ordinal> ords = new ArrayList<Ordinal>();
		boolean start=false;
		for(int i=puzzleMatrix.size()-1; i>-1; i--)
		{
			if(m<puzzleMatrix.size()) {
				
				if(match.startsWith(puzzleMatrix.get(i).get(m)))
					start=true;
				if(start) {
					line += puzzleMatrix.get(i).get(m);
					ords.add(new Ordinal(m,i));
					if(line.equals(match)) 
					{
						resultMap.put(match, ords);
						return true;
					}
				}
				m++;
			}
		}
		
		return false;
	}
	
	private boolean DiagonalBottomUpUpperBuilder(int m, String match)
	{
		String line = new String();
		List<Ordinal> ords = new ArrayList<Ordinal>();
		
		boolean start=false;
		for(int i=0; i<puzzleMatrix.size(); i++)
		{
			if(m>-1) {
				
				if(match.startsWith(puzzleMatrix.get(m).get(i)))
					start=true;
				if(start) {
					line += puzzleMatrix.get(m).get(i);
					ords.add(new Ordinal(i,m));
					if(line.equals(match)) 
					{
						
						resultMap.put(match, ords);
						return true;
					}
				}
				m--;
			}
		}
		
		return false;
	}
	
	public Map<String, List<String>> getResultMap() {
		Map<String, List<String>> friendlyMap = new HashMap<String, List<String>>();
		
		for(String key : resultMap.keySet())
		{
			List<String> friendlyList = new ArrayList<String>();
			for(Ordinal ords : resultMap.get(key))
			{
				friendlyList.add(ords.toString());
			}
			friendlyMap.put(key, friendlyList);
		}
		return friendlyMap;
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
