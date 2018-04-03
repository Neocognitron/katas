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
						ords.add(new Ordinal(i,r));
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
					for(int i=c+string.length()-1; i>=c; i--)
					{
						ords.add(new Ordinal(i,r));
					}
					 resultMap.put(string, ords);
				}
			}
		}
		
	}
	
	public void TopToBottomMatch(String string)
	{
		String line = new String();
		for(int c=0; c<puzzleMatrix.get(0).size();c++)
		{
			line = TopToBottomBuilder(c);
			for(int j=0; j<=puzzleMatrix.size()-string.length(); j++)
			{
				if(line.substring(j, j+string.length()).equalsIgnoreCase(string))
				{
					
					List<Ordinal> ords = new ArrayList<Ordinal>();
					for(int i=j; i<j+string.length(); i++)
					{
						ords.add(new Ordinal(c,i));
					}
					 resultMap.put(string, ords);
				}
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
			for(int j=0; j<=puzzleMatrix.size()-revString.length(); j++)
			{
				if(line.substring(j, j+revString.length()).equalsIgnoreCase(revString))
				{
					
					List<Ordinal> ords = new ArrayList<Ordinal>();
					for(int i=j+revString.length()-1; i>=j; i--)
					{
						ords.add(new Ordinal(c,i));
					}
					 resultMap.put(string, ords);
				}
			}
			
		}
		
	}
	
	public void DiagonalLeftToRightMatchForward(String string) {
		//if the length of the word we want is longer than the difference between
		//the start of the diagonal (on the matrix) and the end of that line,
		//it won't be found.
		String line=new String();
		boolean matched = false;
			for(int m=0;m<puzzleMatrix.size();m++)
		{
			
				if(puzzleMatrix.size()- m>=string.length() && !matched)
				{
					line = DiagonalLeftToRightLower(m);
					System.out.println("Test="+line);
				}
				else line = "";
				
				if(!line.equals(""))
				{
					for(int j=0; j<=puzzleMatrix.size()-string.length(); j++)
					{
						//System.out.println("Test="+line);
						if(line.substring(j).equalsIgnoreCase(string))
						{
							
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
			}
		if(!matched)
		{
			for(int m=0;m<puzzleMatrix.size();m++)
			{
				
					if(puzzleMatrix.size()- m>=string.length()&& !matched)
					{
						line = DiagonalLeftToRightUpper(m);
						System.out.println("Test="+line);
					}
					else line = "";
					
					if(!line.equals(""))
					{
						for(int j=0; j<=string.length(); j++)
						{
							System.out.println("Test="+line);
							if(line.substring(j).equalsIgnoreCase(string))
							{
								
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
