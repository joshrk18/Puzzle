package puzzle;

/**
 * Wordsearch solver    v2.00
 * Date 1/15/2015
 * @author Josh Kertscher
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class wordsearch
{
	
	public static void main( String [] args)
	{
		/////////variables//////
		int xcord = -1;
		int ycord = -1;
		String dir = "none";
		String strin = new String();
		String word = new String();
		Scanner scan = new Scanner(System.in);
		ArrayList<String> puzzle = new ArrayList<String>();
		
		////////////input file///////////
		try
		{
			Scanner file = new Scanner( new File("WordSearchFile.txt"));
			while(file.hasNext())
			{
				String line = file.nextLine();
				System.out.println(line);
				puzzle.add(new String(line));
			}
			file.close();
		}
		
		catch(FileNotFoundException fnfe)
		{
			System.out.println("cannot find file");
		}
		
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
		
                //////////arraylist to 2d chararray/////
		char charpuzzle[][] = new char [puzzle.size()][puzzle.size()];
		for(int row = 0; row < puzzle.size();row++)
		{
			for(int col = 0; col < puzzle.size(); col++)
		    {
				charpuzzle[row][col] = puzzle.get(row).charAt(col);
		    }
		}
                
		//////repeat//////
		while(true)
		{
		///////init////////
		xcord = -1;
		ycord = -1;
		dir = "none";
		
		////////////////input word////////////////
		System.out.print("Enter word > ");
		word = scan.next();
		System.out.println();
		
		///////print char array///////
		System.out.println();
		System.out.println("print char array");
		for(int row = 0; row < puzzle.size();row++)
		{
			for(int col = 0; col < puzzle.size(); col++)
		    {
				System.out.print(charpuzzle[row][col]);
		    }
			System.out.println();
	 	}
		
		///////search horizontally right/////
		for(int row = 0; row < puzzle.size();row++)
		{
			for(int col = 0; col < puzzle.size(); col++)
		    {
				strin = "";
				for(int i = col; (i < word.length()+col)&(i<puzzle.size()); i++)
				{
					strin += charpuzzle[row][i];
				}
				
				if(word.equals(strin))
				{
					xcord = col; 
					ycord = row;
					dir = "right";
					////////display coordinates//////
					System.out.println(xcord+" "+ycord+" "+dir);
				}
				
		    }
		}
		
		///////search horizontally left/////
		for(int row = 0; row < puzzle.size();row++)
		{
			for(int col = 0; col < puzzle.size(); col++)
		    {
				strin = "";
				for(int i = col; (i < word.length()+col)&(i<puzzle.size()); i++)
				{
					strin += charpuzzle[row][puzzle.size()-i-1];
				}

				if(word.equals(strin))
				{
					xcord = puzzle.size()-col-1; 
					ycord = row;
					dir = "left";
					////////display coordinates//////
					System.out.println(xcord+" "+ycord+" "+dir);
				}
		    }
		}
		
		///////search vertically down/////
		for(int col = 0; col < puzzle.size();col++)
		{
			for(int row = 0; row < puzzle.size(); row++)
		    {
				strin = "";
				for(int i = row; (i < word.length()+row)&(i<puzzle.size()); i++)
				{
					strin += charpuzzle[i][col];
				}

				if(word.equals(strin))
				{
					xcord = col; 
					ycord = row;
					dir = "down";
					////////display coordinates//////
					System.out.println(xcord+" "+ycord+" "+dir);
				}
		    }
		}
		
		///////search vertically up/////
		for(int col = 0; col < puzzle.size(); col++)
		{
			for(int row = 0; row < puzzle.size(); row++)
		    {
				strin = "";
				for(int i = row; (i < word.length()+row)&(i<puzzle.size()); i++)
				{
					strin += charpuzzle[puzzle.size()-i-1][col];
				}

				if(word.equals(strin))
				{
					xcord = col; 
					ycord = puzzle.size()-row-1;
					dir = "up";
					////////display coordinates//////
					System.out.println(xcord+" "+ycord+" "+dir);
				}
		    }
		}
		
		///////search diagonaly right down/////
		for(int row = 0; row < puzzle.size();row++)
		{
			for(int col = 0; col < puzzle.size(); col++)
		    {
				strin = "";
				for(int i = 0; (i < word.length())&(i+row<puzzle.size())&(i+col<puzzle.size()); i++)
				{
					strin += charpuzzle[i+row][i+col];
				}

				if(word.equals(strin))
				{
					xcord = col; 
					ycord = row;
					dir = "rdiagd";
					////////display coordinates//////
					System.out.println(xcord+" "+ycord+" "+dir);
				}
		    }
		}
		
		///////search diagonaly right up/////
		for(int row = 0; row < puzzle.size();row++)
		{
			for(int col = 0; col < puzzle.size(); col++)
		    {
				strin = "";
				for(int i = 0; (i < word.length())&(i+row<puzzle.size())&(i+col<puzzle.size()); i++)
				{
					strin += charpuzzle[puzzle.size()-i-row-1][i+col];
				}
				
				if(word.equals(strin))
				{
					xcord = col; 
					ycord = puzzle.size()-1-row;
					dir = "rdiagu";
					////////display coordinates//////
					System.out.println(xcord+" "+ycord+" "+dir);
				}
		    }
		}
		
		///////search diagonaly left up/////////
		for(int row = 0; row < puzzle.size();row++)
		{
			for(int col = 0; col < puzzle.size(); col++)
		    {
				strin = "";
				for(int i = 0; (i < word.length())&(i+row<puzzle.size())&(i+col<puzzle.size()); i++)
				{
					strin += charpuzzle[puzzle.size()-i-row-1][puzzle.size()-i-col-1];
				}
				
				if(word.equals(strin))
				{
					xcord = puzzle.size()-1-col; 
					ycord = puzzle.size()-1-row;
					dir = "ldiagu";
					////////display coordinates//////
					System.out.println(xcord+" "+ycord+" "+dir);
				}
		    }
		}
		
		///////search diagonaly left down/////
		for(int row = 0; row < puzzle.size();row++)
		{
			for(int col = 0; col < puzzle.size(); col++)
		    {
				strin = "";
				for(int i = 0; (i < word.length())&(i+row<puzzle.size())&(i+col<puzzle.size()); i++)
				{
					strin += charpuzzle[i+row][puzzle.size()-1-i-col];
				}

				if(word.equals(strin))
				{
					xcord = puzzle.size()-1-col; 
					ycord = row;
					dir = "ldiagd";
					////////display coordinates//////
					System.out.println(xcord+" "+ycord+" "+dir);
				}
		    }
		}
		if(xcord==-1) System.out.println("notfound");
		}//done
	}
}