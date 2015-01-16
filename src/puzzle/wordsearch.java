package puzzle;

/**
 * Wordsearch solver    v1.01
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
		
		///////////example puzzle/////////
		/*
		String line1 = "dogh";
		puzzle.add(new String(line1));

		String line2 = "dooh";
		puzzle.add(new String(line2));

		String line3 = "doah";
		puzzle.add(new String(line3));

		String line4 = "toth";
		puzzle.add(new String(line4));
		*/
		//////////
		String line0 = "nnrqedocicnivadehtjrxwntwvkfmapoplkzottqkhznr";
		String line1 = "rffplmjvlgwrtqnkdnwnrvayjnknrhhtbltrdkmxzdxxf";
		String line2 = "supermanreturnsxgplzrdzrkkcmqkzpdmedzrrtrlpng";
		String line3 = "yrtdelgnatvthepolarexpressaprnmlqtawttxdmrutg";
		String line4 = "rnrjxelatkrahsrqwzckthayggxbngkcszorhfxexgktt";
		String line5 = "kcalbninemmrryxzbqjhwetvextfzybitcreheelpntqt";
		String line6 = "nttmsherlockholmesvgitrdkhpqglwpijwvrtlojmlnl";
		String line7 = "qlcnfrhdhnevelesnaecocdmennyptrncapitrtdbrzcv";
		String line8 = "onamredipswtypcrmbmzmoalicftqyaktfuhtighfoqny";
		String line9 = "lliarliarqqqdtlhktmgndigrnlnvtceegeoxnbbhtlfp";
		String line10= "rmnmkkdlmjurassicparkojnoraziorsafymrczlfsrcm";
		String line11= "tcjwvhmbbywtrgfgdwxlnkwmdbgtcbpmoslsnettntkfr";
		String line12= "wkccprzktrmtnthedarkknightonoiyctjgzcphktcblm";
		String line13= "piamlegendynbdmlycibkzlytsaylrkotntpzttbeevne";
		String line14= "tccpbcymthkrjegnknvmlwhkthqcreriihgryiidefmnl";
		String line15= "yrnfqrcftkvmgyvwghknmqmnwxeeryrrghrhaostfrtqb";
		String line16= "jtxnnpghostbustersikwretmfjstgeiorfvdnehyedti";
		String line17= "tkhmenoecrofriacrglazrttnqohehlsjlmkebhoppavs";
		String line18= "hrmgpfscarymoviehlrzachmprrutitncrstcptrpenzs";
		String line19= "eagritstjdmvgswtyoypjelrjerfwhqgrdntnrfnahczo";
		String line20= "ltrrhmlrldrprtawftehfgeletotkqcgodileholhtetp";
		String line21= "osdheplpeaoetthtqhiuitrondgsgmrlcplbdyealfswm";
		String line22= "nogngapaimwsthhetbgctllorddgjrqtommbnngsklwmi";
		String line23= "glxgansneorheeetbiryellowijnkazpdsefennozlijn";
		String line24= "eeyirtmepceowlektlwfkhlsantmntfiieroppephltko";
		String line25= "shnyrainwmuofebiaoikletmclutmaqrlpgrervoqzhhi";
		String line26= "ttvantiouprrmsvimrknhjsdgohpkvqaeawrdgerjbwrs";
		String line27= "yfywytasnlwybenadbatderknlpnsawtdecenvrplaods";
		String line28= "aorfsremdajrqmnazeftdsteqagpqqweuhysinjeztlni";
		String line29= "rsrufueseflxdvrhrgrieligtfxwdivsntltpnnhtmvqm";
		String line30= "drakmiqtghvtqtfijtrclkjdntqelmkodfkgknhttaeum";
		String line31= "retdntntaltxrmhrfbmknhiwemodslpfeolumkjctnsan";
		String line32= "cdzntqpdlvakleteoemvlildnxhpkwttetvmhlrytdjnn";
		String line33= "kiswanhgipidlfadsbhdmnewmoonywqhrebpacxjziltn";
		String line34= "ialtjwnamnorimpscirtttshgpkznrdernsknwmdkyhul";
		String line35= "nrkkalnnhngyparduvxahtwsthnrfcrczanlnletnptmr";
		String line36= "gkdlkrhetnxnqgtbwrrthlarushhouradlgnimnwqkhov";
		String line37= "kjwlqywzmcvlednollenhljytwjnygprhpizbqjkebefh";
		String line38= "ovkxzntaxotqjmtirhzxcsrdblpvdtqinwslavtrfmmsp";
		String line39= "nwqqmvcmrwwwfdonvqnydpeamwkljmfblwhllltlhkuot";
		String line40= "gtyxylzxvsptkdrrhatxwrdnemmhvkmbtnzjxrnvjkmlt";
		String line41= "vrevognahehtadywlpsntqtpspkgyvjermlxatpmlwmag";
		String line42= "elayoronisacyhhzbyawatsacemrtlgagcrtvhmfbjyct";
		String line43= "tqkbnnprpyyxqvwplpdabeautifulmindtskkrjlmvkel";
		String line44= "kpjreivomsnospmisehtchmfbkmkmnlpbrrobinhoodlb";
		puzzle.add(new String(line0));
		puzzle.add(new String(line1));
		puzzle.add(new String(line2));
		puzzle.add(new String(line3));
		puzzle.add(new String(line4));
		puzzle.add(new String(line5));
		puzzle.add(new String(line6));
		puzzle.add(new String(line7));
		puzzle.add(new String(line8));
		puzzle.add(new String(line9));
		puzzle.add(new String(line10));
		puzzle.add(new String(line11));
		puzzle.add(new String(line12));
		puzzle.add(new String(line13));
		puzzle.add(new String(line14));
		puzzle.add(new String(line15));
		puzzle.add(new String(line16));
		puzzle.add(new String(line17));
		puzzle.add(new String(line18));
		puzzle.add(new String(line19));
		puzzle.add(new String(line20));
		puzzle.add(new String(line21));
		puzzle.add(new String(line22));
		puzzle.add(new String(line23));
		puzzle.add(new String(line24));
		puzzle.add(new String(line25));
		puzzle.add(new String(line26));
		puzzle.add(new String(line27));
		puzzle.add(new String(line28));
		puzzle.add(new String(line29));
		puzzle.add(new String(line30));
		puzzle.add(new String(line31));
		puzzle.add(new String(line32));
		puzzle.add(new String(line33));
		puzzle.add(new String(line34));
		puzzle.add(new String(line35));
		puzzle.add(new String(line36));
		puzzle.add(new String(line37));
		puzzle.add(new String(line38));
		puzzle.add(new String(line39));
		puzzle.add(new String(line40));
		puzzle.add(new String(line41));
		puzzle.add(new String(line42));
		puzzle.add(new String(line43));
		puzzle.add(new String(line44));

		//////////arraylist to 2d chararray/////
		char charpuzzle[][] = new char [puzzle.size()][puzzle.size()];
		for(int row = 0; row < puzzle.size();row++)
		{
			for(int col = 0; col < puzzle.size(); col++)
		    {
				charpuzzle[row][col] = puzzle.get(row).charAt(col);
		    }
		}
		
		////////////input file///////////
		try
		{
			Scanner file = new Scanner( new File("File.txt"));
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
