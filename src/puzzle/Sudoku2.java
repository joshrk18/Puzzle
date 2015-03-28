package puzzle;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Sudoku2 solver    v2.03
 * date 3/21/2015
 * @author Josh Kertscher
 */


public class Sudoku2 
{
    static boolean stop = false;
    
    // Copy a 2d Matrix
    static int[][] copyMatrix(int[][] A)
    {
        int[][] Copy;                       //copy matrix in case guess is wrong and we will be able to recover progress and try another guess              
        Copy = new int[9][9];
        for(int i = 0 ; i < 9 ; i++)                         
        {
            for(int k = 0 ; k < 9 ; k++)
            {
                Copy[i][k]=A[i][k];
            }
        }
        return Copy;
    }
    
    // Copy a 3d Matrix
    static int[][][] copy3DMatrix(int[][][] A)
    {
        int[][][] Copy;                       //copy matrix in case guess is wrong and we will be able to recover progress and try another guess              
        Copy = new int[9][9][10];
        for(int i = 0 ; i < 9 ; i++)                         
        {
            for(int j = 0 ; j < 9 ; j++)
            {
                for(int k = 0; k < 10; k++)
                {
                    Copy[i][j][k]=A[i][j][k];
                }
            }
        }
        return Copy;
    }
    
    // Print a 9x9 matrix 
    static void matrixPrint(int[][] mat)
    {
            for(int i = 0 ; i < 9 ; i++)                                //row selection                 
                    for(int j = 0 ; j < 9 ; j++)                        //column selection
                    {
                            if(j < 8)
                                    System.out.print(mat[i][j] + " ");  //print cell ij
                            if(j == 8)
                                    System.out.println(mat[i][j] + " ");
                    }
            System.out.println();                                       //blank line
    }
    
    // Print a 9x9xnum matrix (marker)
    static void matrixPrint3D(int[][][] mat)
    {
            for(int i = 0 ; i < 9 ; i++)                                //row selection                 
                    for(int j = 0 ; j < 9 ; j++)                        //column selection
                    {
                            if(j < 8)
                                    System.out.print(mat[i][j][1] + "" + mat[i][j][2]*2 + "" + mat[i][j][3]*3 + "" + mat[i][j][4]*4 + "" + mat[i][j][5]*5 + "" + mat[i][j][6]*6 + "" + mat[i][j][7]*7 + "" + mat[i][j][8]*8 + "" + mat[i][j][9]*9 + " ");  //print cell ij
                            if(j == 8)
                                    System.out.println(mat[i][j][1] + "" + mat[i][j][2]*2 + "" + mat[i][j][3]*3 + "" + mat[i][j][4]*4 + "" + mat[i][j][5]*5 + "" + mat[i][j][6]*6 + "" + mat[i][j][7]*7 + "" + mat[i][j][8]*8 + "" + mat[i][j][9]*9 + " ");
                    }
            System.out.println();                                       //blank line
    }
    
    // Print a 9 element array
    static void arrayPrint(int[] array)
    {
            for(int i = 1 ; i < 10 ; i++)
                System.out.print(array[i] + " ");  //print element
            System.out.println();                  //blank line
    }

    // Check Sudoku puzzle for errors
    public static boolean SudokuCheck(int[][] A)
    {
        //check horizontal for copies
        for(int i = 0; i < 9; i++ )
        {
            int[] counter = new int[10]; //used to count number of 1's, 2's, 3's, 4's .... and 9's

            for(int j = 0; j < 9; j++)
            {
                counter[A[i][j]]++;
            }

            for(int j = 1; j < 10; j++)
            {
                if(counter[j]>1) 
                {
//                    System.out.println("Row: " + (i+1) + " is bad");   //print line
                    return false;
                }   
            }

//            System.out.println("Row: " + (i+1) + " is good");   //print line
        }
        System.out.println("All Rows are good");   

        //check vertical for copies
        for(int i = 0; i < 9; i++ )
        {
            int[] counter = new int[10]; //used to count number of 1's, 2's, 3's, 4's .... and 9's

            for(int j = 0; j < 9; j++)
            {
                counter[A[j][i]]++;
            }

            for(int j = 1; j < 10; j++)
            {
                if(counter[j]>1) 
                {
//                    System.out.println("Columns: " + (i+1) + " is bad");   //print line
                    return false;
                }   
            }

//            System.out.println("Columns: " + (i+1) + " is good");   //print line
        }
        System.out.println("All Columns are good");   

        //check squares for copies
        for(int x = 0; x < 9; x += 3)
        {
            for(int y = 0; y < 9; y += 3)
            {
                int[] counter = new int[10]; //used to count number of 1's, 2's, 3's, 4's .... and 9's

                for(int i = x; i < 3+x; i++ )
                {
                    for(int j = y; j < 3+y; j++)
                    {
                        counter[A[i][j]]++;
                    }

                for(int j = 1; j < 10; j++)
                {
                    if(counter[j]>1) 
                    {
//                    System.out.println("Square: " + (x/3+1) + "" + (y/3+1) + " is bad");   //print line
                    return false;
                    }   
                }

//                    System.out.println("Square: " + (x/3+1) + "" + (y/3+1) + " is good");   //print line
                }

            }
        }

        System.out.println("All Squares are good");
        return true;
    }
    static boolean matrixCheck(int[][] A)       //check if solution is valid 
	{
		int sumr = 0;                       //initilize variables 
		int sumd = 0;

		int sum11 = 0;
		int sum12 = 0;
		int sum13 = 0;

		int sum21 = 0;
		int sum22 = 0;
		int sum23 = 0;

		int sum31 = 0;
		int sum32 = 0;
		int sum33 = 0;

		for(int i = 0 ; i < 9 ; i++)        //return false if vertical rows or horizontal rows do not add up to 45                 
		{
			for(int k = 0 ; k < 9 ; k++)
			{
				sumr += A[i][k];
			}

			for(int k = 0 ; k < 9 ; k++)
			{
				sumd += A[k][i];
			}

			if(sumr != 45)
				return false; 

			if(sumd != 45)
				return false;

			sumr = 0;
			sumd = 0;
		}     
              
        for(int i = 0 ; i < 3 ; i++)        // 11|12|13    adds each of the 9 blocks              
            for(int j = 0 ; j < 3 ; j++)    // 21|22|23
                sum11 += A[i][j];           // 31|32|33

        for(int i = 0 ; i < 3 ; i++)                    
            for(int j = 3 ; j < 6 ; j++)
                sum12 += A[i][j];

        for(int i = 0 ; i < 3 ; i++)                    
            for(int j = 6 ; j < 9 ; j++)
                sum13 += A[i][j];

        for(int i = 3 ; i < 6 ; i++)                    
            for(int j = 6 ; j < 9 ; j++)
                sum21 += A[i][j];

        for(int i = 3 ; i < 6 ; i++)                    
            for(int j = 3 ; j < 6 ; j++)
                sum22 += A[i][j];

        for(int i = 3 ; i < 6 ; i++)                    
            for(int j = 0 ; j < 3 ; j++)
                sum23 += A[i][j];

        for(int i = 6 ; i < 9 ; i++)                    
            for(int j = 0 ; j < 3 ; j++)
                sum31 += A[i][j];

        for(int i = 6 ; i < 9 ; i++)                    
            for(int j = 3 ; j < 6 ; j++)
                sum32 += A[i][j];

        for(int i = 6 ; i < 9 ; i++)                    
            for(int j = 6 ; j < 9 ; j++)
                sum33 += A[i][j];

        if(sum11 != 45 && sum12 != 45 && sum13 != 45 && sum21 != 45 && sum22 != 45 && sum23 != 45 && sum31 != 45 && sum32 != 45 && sum33 != 45)
            return false;   //return false if one of the 9 blocks do not add up to 45

		return true;
	}

    // Initialize the Marker
    public static int[][][] Init_Mark()
    {
        int[][][] Mark = new int[9][9][10]; // used in solving process int[Y][X][Z] Y is row, X is column, and Z is marker
        //Put in markers
        for(int Y = 0; Y < 9; Y++ )
        {
            for(int X = 0; X < 9; X++)
            {
                for(int Z = 0; Z < 10; Z++)
                {
                    Mark[Y][X][Z] = 1;
                }   
            }
        }
        return Mark;
    }
    
    public static int[][][] marker(int A[][], int Mark[][][])
    {
        //Cross out markers
        for(int Y = 0; Y < 9; Y++ )
        {
            for(int X = 0; X < 9; X++)
            {
                if(A[Y][X]!=0)
                {
                    //kill row
                    for(int k = 0; k < 9; k++)
                    {
                        if(k!=X){ Mark[Y][k][A[Y][X]] = 0; }
                    }
                    
                    //kill column
                    for(int k = 0; k < 9; k++)
                    {
                        if(k!=Y){ Mark[k][X][A[Y][X]] = 0; }
                    }
                    
                    //kill square
                    if(X<3 && Y<3)  //square 11
                    {
                        for(int i = 0; i < 3; i++)
                        {
                            for(int j = 0; j < 3; j++)
                            {
                               if(Y!=i && X!=j){ Mark[i][j][A[Y][X]] = 0; }
                            }
                        }
                    }
                    
                    else if(X<3 && Y<6)  //square 21
                    {
                        for(int i = 3; i < 6; i++)
                        {
                            for(int j = 0; j < 3; j++)
                            {
                                if(Y!=i && X!=j){ Mark[i][j][A[Y][X]] = 0; }
                            }
                        }
                    }
                    
                    else if(X<3 && Y<9)  //square 31
                    {
                        for(int i = 6; i < 9; i++)
                        {
                            for(int j = 0; j < 3; j++)
                            {
                                if(Y!=i && X!=j){ Mark[i][j][A[Y][X]] = 0; }
                            }
                        }
                    }
                    
                    else if(X<6 && Y<3)  //square 12
                    {
                        for(int i = 0; i < 3; i++)
                        {
                            for(int j = 3; j < 6; j++)
                            {
                                if(Y!=i && X!=j){ Mark[i][j][A[Y][X]] = 0; }
                            }
                        }
                    }
                    
                    else if(X<6 && Y<6)  //square 22
                    {
                        for(int i = 3; i < 6; i++)
                        {
                            for(int j = 3; j < 6; j++)
                            {
                                if(Y!=i && X!=j){ Mark[i][j][A[Y][X]] = 0; }
                            }
                        }
                    }
                    
                    else if(X<6 && Y<9)  //square 32
                    {
                        for(int i = 6; i < 9; i++)
                        {
                            for(int j = 3; j < 6; j++)
                            {
                                if(Y!=i && X!=j){ Mark[i][j][A[Y][X]] = 0; }
                            }
                        }
                    }

                    else if(X<9 && Y<3)  //square 13
                    {
                        for(int i = 0; i < 3; i++)
                        {
                            for(int j = 6; j < 9; j++)
                            {
                                if(Y!=i && X!=j){ Mark[i][j][A[Y][X]] = 0; }
                            }
                        }
                    }
                    
                    else if(X<9 && Y<6)  //square 23
                    {
                        for(int i = 3; i < 6; i++)
                        {
                            for(int j = 6; j < 9; j++)
                            {
                                if(Y!=i && X!=j){ Mark[i][j][A[Y][X]] = 0; }
                            }
                        }
                    }
                    
                    else  //square 33
                    {
                        for(int i = 6; i < 9; i++)
                        {
                            for(int j = 6; j < 9; j++)
                            {
                                if(Y!=i && X!=j){ Mark[i][j][A[Y][X]] = 0; }
                            }
                        }
                    }
                    
                    //kill marker
                    for(int k = 0; k < 10; k++)
                    {
                        if(A[Y][X] != k){ Mark[Y][X][k] = 0; }
                    }
                }
            }
        }
        
        return Mark;
    }
        
    public static int[][] SudokuSolver(int[][] puzzle, int[][][] marks)
    {
        int sum = 0;
//        System.out.println();
//        matrixPrint(puzzle);         //print solved sudoku puzzle
        // solves when cell only has one marker
//        System.out.println("Check cells for one marker");
        for(int Y = 0; Y < 9; Y++ )
        {
            for(int X = 0; X < 9; X++)
            {
                sum = 0;
                int cell = 0;
                for(int Z = 1; Z < 10; Z++)
                {
                    sum += marks[Y][X][Z];
                    if(marks[Y][X][Z]==1) {cell = Z;} 
                }  
                
                if(sum==1)//if sum equals 1 than that means only one number was available and that number goes into the cell
                {
                    puzzle[Y][X] = cell;
//                    System.out.println("X: " + X + " Y: " + Y + " Cell: " + cell);
                }
            }
        }
        
//        System.out.println();
//        matrixPrint(puzzle);         //print solved sudoku puzzle
        // solves when there is only one marker available for a cell in a row 
//        System.out.println("Check rows for one marker");                  
        for(int Y = 0; Y < 9; Y++ )//rows
        {
            int[][] count = new int[2][10]; //count number of times each number has occured in 0 and store column in 1
            for(int X = 0; X < 9; X++)//column
            {
                for(int Z = 1; Z < 10; Z++)
                {
                    count[0][marks[Y][X][Z]*Z]++; //number of times each number has occured
                    if(marks[Y][X][Z]==1) {count[1][Z] = X; }
                } 
            }
//            arrayPrint(count[0]);
//            arrayPrint(count[1]);
                
            for(int num = 1; num < 10; num++) // go through each number
            {
                if(count[0][num]==1)//if count equals 1 than that means only one number was available and that number goes into the cell
                {
                    puzzle[Y][count[1][num]] = num; //put number into cell 
//                    System.out.println("X: " + count[1][num] + " Y: " + Y + " Cell: " + num);
                }
            }
        }
//        System.out.println();
//        matrixPrint(puzzle);         //print solved sudoku puzzle
        // solves when there is only one marker available for a cell in a column
//        System.out.println("Check columns for one marker");
        for(int Y = 0; Y < 9; Y++ )//rows
        {
            int[][] count = new int[2][10]; //count number of times each number has occured
            for(int X = 0; X < 9; X++)//column
            {
                for(int Z = 1; Z < 10; Z++)
                {
                    count[0][marks[X][Y][Z]*Z]++; //number of times each number has occured
                    if(marks[X][Y][Z]==1) {count[1][Z] = X; }
                } 
            }
//            arrayPrint(count[0]);
//            arrayPrint(count[1]);
                
            for(int num = 1; num < 10; num++) // go through each number
            {
                if(count[0][num]==1)//if count equals 1 than that means only one number was available and that number goes into the cell
                {
                    puzzle[count[1][num]][Y] = num; //put number into cell 
//                    System.out.println("X: " + Y + " Y: " + count[1][num] + " Cell: " + num);
                }
            }
        }
//        System.out.println();
//        matrixPrint(puzzle);         //print solved sudoku puzzle
        // solves when there is only one marker available for a cell in a square
//        System.out.println("Check squares for one marker");                     //check squares not sure
//        System.out.println("Checking square: 11");
        int[][] count = new int[3][10]; //count number of times each number has occured and store location
        for(int Y = 0; Y < 3; Y++ )
        {
            for(int X = 0; X < 3; X++)
            {
                for(int Z = 1; Z < 10; Z++)
                {
                    count[0][marks[X][Y][Z]*Z]++; //number of times each number has occured
                    if(marks[X][Y][Z]==1) 
                    {
                        count[2][Z] = X;
                        count[1][Z] = Y;
                    }
                }
            }
        }
//        arrayPrint(count[0]);
                
        for(int num = 1; num < 10; num++) // go through each number
        {
            if(count[0][num]==1)//if count equals 1 than that means only one number was available and that number goes into the cell
            {
                puzzle[count[2][num]][count[1][num]] = num; //put number into cell 
//                System.out.println("X: " + count[1][num] + " Y: " + count[2][num] + " Cell: " + num);
            }
        }
        
//        System.out.println("Checking square: 12");
        count = new int[3][10]; //count number of times each number has occured and store location
        for(int Y = 3; Y < 6; Y++ )
        {
            for(int X = 0; X < 3; X++)
            {
                for(int Z = 1; Z < 10; Z++)
                {
                    count[0][marks[X][Y][Z]*Z]++; //number of times each number has occured
                    if(marks[X][Y][Z]==1) 
                    {
                        count[2][Z] = X;
                        count[1][Z] = Y;
                    }
                }
            }
        }
//        arrayPrint(count[0]);
                
        for(int num = 1; num < 10; num++) // go through each number
        {
            if(count[0][num]==1)//if count equals 1 than that means only one number was available and that number goes into the cell
            {
                puzzle[count[2][num]][count[1][num]] = num; //put number into cell 
//                System.out.println("X: " + count[1][num] + " Y: " + count[2][num] + " Cell: " + num);
            }
        }
        
//        System.out.println("Checking square: 13");
        count = new int[3][10]; //count number of times each number has occured
        for(int Y = 6; Y < 9; Y++ )
        {
            for(int X = 0; X < 3; X++)
            {
                for(int Z = 1; Z < 10; Z++)
                {
                    count[0][marks[X][Y][Z]*Z]++; //number of times each number has occured
                    if(marks[X][Y][Z]==1) 
                    {
                        count[2][Z] = X;
                        count[1][Z] = Y;
                    }
                }
            }
        }
//        arrayPrint(count[0]);
                
        for(int num = 1; num < 10; num++) // go through each number
        {
            if(count[0][num]==1)//if count equals 1 than that means only one number was available and that number goes into the cell
            {
                puzzle[count[2][num]][count[1][num]] = num; //put number into cell 
//                System.out.println("X: " + count[1][num] + " Y: " + count[2][num] + " Cell: " + num);
            }
        }
        
//        System.out.println("Checking square: 21");
        count = new int[3][10]; //count number of times each number has occured
        for(int Y = 0; Y < 3; Y++ )
        {
            for(int X = 3; X < 6; X++)
            {
                for(int Z = 1; Z < 10; Z++)
                {
                    count[0][marks[X][Y][Z]*Z]++; //number of times each number has occured
                    if(marks[X][Y][Z]==1) 
                    {
                        count[2][Z] = X;
                        count[1][Z] = Y;
                    }
                }
            }
        }
//        arrayPrint(count[0]);
                
        for(int num = 1; num < 10; num++) // go through each number
        {
            if(count[0][num]==1)//if count equals 1 than that means only one number was available and that number goes into the cell
            {
                puzzle[count[2][num]][count[1][num]] = num; //put number into cell 
//                System.out.println("X: " + count[1][num] + " Y: " + count[2][num] + " Cell: " + num);
            }
        }
        
//        System.out.println("Checking square: 22");
        count = new int[3][10]; //count number of times each number has occured
        for(int Y = 3; Y < 6; Y++ )
        {
            for(int X = 3; X < 6; X++)
            {
                for(int Z = 1; Z < 10; Z++)
                {
                    count[0][marks[X][Y][Z]*Z]++; //number of times each number has occured
                    if(marks[X][Y][Z]==1) 
                    {
                        count[2][Z] = X;
                        count[1][Z] = Y;
                    }
                }
            }
        }
//        arrayPrint(count[0]);
                
        for(int num = 1; num < 10; num++) // go through each number
        {
            if(count[0][num]==1)//if count equals 1 than that means only one number was available and that number goes into the cell
            {
                puzzle[count[2][num]][count[1][num]] = num; //put number into cell 
//                System.out.println("X: " + count[1][num] + " Y: " + count[2][num] + " Cell: " + num);
            }
        }
    
//        System.out.println("Checking square: 23");
        count = new int[3][10]; //count number of times each number has occured
        for(int Y = 6; Y < 9; Y++ )
        {
            for(int X = 3; X < 6; X++)
            {
                for(int Z = 1; Z < 10; Z++)
                {
                    count[0][marks[X][Y][Z]*Z]++; //number of times each number has occured
                    if(marks[X][Y][Z]==1) 
                    {
                        count[2][Z] = X;
                        count[1][Z] = Y;
                    }
                }
            }
        }
//        arrayPrint(count[0]);
                
        for(int num = 1; num < 10; num++) // go through each number
        {
            if(count[0][num]==1)//if count equals 1 than that means only one number was available and that number goes into the cell
            {
                puzzle[count[2][num]][count[1][num]] = num; //put number into cell 
//                System.out.println("X: " + count[1][num] + " Y: " + count[2][num] + " Cell: " + num);
            }
        }

//        System.out.println("Checking square: 31");
        count = new int[3][10]; //count number of times each number has occured
        for(int Y = 0; Y < 3; Y++ )
        {
            for(int X = 6; X < 9; X++)
            {
                for(int Z = 1; Z < 10; Z++)
                {
                    count[0][marks[X][Y][Z]*Z]++; //number of times each number has occured
                    if(marks[X][Y][Z]==1) 
                    {
                        count[2][Z] = X;
                        count[1][Z] = Y;
                    }
                }
            }
        }
//        arrayPrint(count[0]);
                
        for(int num = 1; num < 10; num++) // go through each number
        {
            if(count[0][num]==1)//if count equals 1 than that means only one number was available and that number goes into the cell
            {
                puzzle[count[2][num]][count[1][num]] = num; //put number into cell 
//                System.out.println("X: " + count[1][num] + " Y: " + count[2][num] + " Cell: " + num);
            }
        }

//        System.out.println("Checking square: 32");
        count = new int[3][10]; //count number of times each number has occured
        for(int Y = 3; Y < 6; Y++ )
        {
            for(int X = 6; X < 9; X++)
            {
                for(int Z = 1; Z < 10; Z++)
                {
                    count[0][marks[X][Y][Z]*Z]++; //number of times each number has occured
                    if(marks[X][Y][Z]==1) 
                    {
                        count[2][Z] = X;
                        count[1][Z] = Y;
                    }
                }
            }
        }
//        arrayPrint(count[0]);
                
        for(int num = 1; num < 10; num++) // go through each number
        {
            if(count[0][num]==1)//if count equals 1 than that means only one number was available and that number goes into the cell
            {
                puzzle[count[2][num]][count[1][num]] = num; //put number into cell 
//                System.out.println("X: " + count[1][num] + " Y: " + count[2][num] + " Cell: " + num);
            }
        }
        
//        System.out.println("Checking square: 33");
        count = new int[3][10]; //count number of times each number has occured
        for(int Y = 6; Y < 9; Y++ )
        {
            for(int X = 6; X < 9; X++)
            {
                for(int Z = 1; Z < 10; Z++)
                {
                    count[0][marks[X][Y][Z]*Z]++; //number of times each number has occured
                    if(marks[X][Y][Z]==1) 
                    {
                        count[2][Z] = X;
                        count[1][Z] = Y;
                    }
                }
            }
        }
//        arrayPrint(count[0]);
                
        for(int num = 1; num < 10; num++) // go through each number
        {
            if(count[0][num]==1)//if count equals 1 than that means only one number was available and that number goes into the cell
            {
                puzzle[count[2][num]][count[1][num]] = num; //put number into cell 
//                System.out.println("X: " + count[1][num] + " Y: " + count[2][num] + " Cell: " + num);
            }
        }
        
        return puzzle;
    }
    
    
    public static void Guesser(int[][] puzzle, int[][][] marks)
    {
        /*
        finds the cell with the least amount of marks. This means their will be less guesses involved.
        */
          
        //guess location
        int guessX = -1;
        int guessY = -1;
        
        //number of guess to try 
        int guesses = 9;
    
        //Find cell with fewest markers
        for(int a = 0; a < 9; a++ )
        {
            for(int b = 0; b < 9; b++ )
            {
                int count = 0;
                for(int c = 1; c < 10; c++ )
                {
                    if(marks[a][b][c] > 0) 
                    { 
                        count++; 
                    }
                }
                        
                if(count < guesses && count > 1) 
                {
                    guesses = count;
                    guessX = b;
                    guessY = a;
                }
            }
        }
        
        for(int guess = 0; guess < guesses && !stop; guess ++)    
        {    
            //copy puzzle to work on and not ruin the original work
            int[][] work_puzzle = copyMatrix(puzzle);
            int[][][] work_marks = copy3DMatrix(marks);
            
            //make guess
            if(guessX > -1) {work_puzzle = Guesser8(work_puzzle, work_marks, guess, guessX, guessY); }
        
            //check if solved
            if( matrixCheck(work_puzzle) && SudokuCheck(work_puzzle) ) 
            {
                System.out.println("finished");
                matrixPrint3D(work_marks); //says which cell can take what number 
                matrixPrint(work_puzzle);         //print solved sudoku puzzle
                stop = true;
            }
            
            if(guessX != -1)
            {
                Guesser(work_puzzle, work_marks);
            }
                
        }
        
    }

    public static int[][] Guesser8(int[][] work_puzzle, int[][][] work_marks, int guess, int guessX, int guessY)
    {
        /*
        Find a cell with 8 or less marks. 0 is a low guess and 1 is a high guess
        take a guess than solve
        if solved return solution. if error try new guess sequence. 
        else find another cell with 8 or less marks and guess again.
        repeat till solved
        
        */
        
                //guess low high
                int guess1 = 0;
                int guess2 = 0;
                int guess3 = 0;
                int guess4 = 0;
                int guess5 = 0;
                int guess6 = 0;
                int guess7 = 0;
                int guess8 = 0;

                int a = guessY;
                int b = guessX;
                
                int count = 0;
                
                //Find cell 
                for(int c = 1; c < 10; c++ )
                {
                    if(work_marks[a][b][c] > 0) 
                    { 
                        count++;
                        if(count == 1) {guess1 = work_marks[a][b][c]*c; }
                        if(count == 2) {guess2 = work_marks[a][b][c]*c; }
                        if(count == 3) {guess3 = work_marks[a][b][c]*c; }
                        if(count == 4) {guess4 = work_marks[a][b][c]*c; }
                        if(count == 5) {guess5 = work_marks[a][b][c]*c; }
                        if(count == 6) {guess6 = work_marks[a][b][c]*c; }
                        if(count == 7) {guess7 = work_marks[a][b][c]*c; }
                        if(count == 8) {guess8 = work_marks[a][b][c]*c; }
                    }
                }
                
                //guess1 == 0
                if( guess == 0 && guessX != -1)
                {
                    work_puzzle[guessY][guessX] = guess1;
                }
                
                //guess2 if == 1
                else if( guess == 1 && guessX != -1)
                {
                    work_puzzle[guessY][guessX] = guess2;
                }
                
                //guess3 if == 2
                else if( guess == 2 && guessX != -1)
                {
                    work_puzzle[guessY][guessX] = guess3;
                }
                
                //guess4 if == 3
                else if( guess == 3 && guessX != -1)
                {
                    work_puzzle[guessY][guessX] = guess4;
                }
                
                //guess4 if == 4
                else if( guess == 4 && guessX != -1)
                {
                    work_puzzle[guessY][guessX] = guess5;
                }
                
                else if( guess == 5 && guessX != -1)
                {
                    work_puzzle[guessY][guessX] = guess6;
                }
                
                else if( guess == 6 && guessX != -1)
                {
                    work_puzzle[guessY][guessX] = guess7;
                }
                
                else if( guess == 7 && guessX != -1)
                {
                    work_puzzle[guessY][guessX] = guess8;
                }
                
                
                //solve
                for(int j = 0; j < 81; j++) // run through many times and solve as much as you can
                {
                    marker(work_puzzle,work_marks); //marks each cell with 1,2,3,4,5,6,7,8,9 and crosses out the ones that dont belong 
                    work_puzzle = SudokuSolver(work_puzzle,work_marks);        //solve sudoku puzzle
                }
        
        return work_puzzle;
    }
    
    public static void main(String[] args)
    {
        // Create matrix of sudoku puzzle to solve
        // zeros are the empty cells of sudoku puzzle
        int[][] A;                                    
        A = new int[9][9];  //example sudoku puzzle                              
        //claims to be worlds hardest
        A[0][0]= 8; A[0][1]= 0; A[0][2]= 0; A[0][3]= 0; A[0][4]= 0; A[0][5]= 0; A[0][6]= 0; A[0][7]= 0; A[0][8]= 0;
        A[1][0]= 0; A[1][1]= 0; A[1][2]= 0; A[1][3]= 0; A[1][4]= 0; A[1][5]= 0; A[1][6]= 0; A[1][7]= 0; A[1][8]= 0;
        A[2][0]= 0; A[2][1]= 0; A[2][2]= 0; A[2][3]= 0; A[2][4]= 0; A[2][5]= 0; A[2][6]= 0; A[2][7]= 0; A[2][8]= 0;
        A[3][0]= 0; A[3][1]= 0; A[3][2]= 0; A[3][3]= 0; A[3][4]= 0; A[3][5]= 0; A[3][6]= 0; A[3][7]= 0; A[3][8]= 0;
        A[4][0]= 0; A[4][1]= 0; A[4][2]= 0; A[4][3]= 0; A[4][4]= 0; A[4][5]= 0; A[4][6]= 0; A[4][7]= 0; A[4][8]= 0;
        A[5][0]= 0; A[5][1]= 0; A[5][2]= 0; A[5][3]= 0; A[5][4]= 0; A[5][5]= 0; A[5][6]= 0; A[5][7]= 0; A[5][8]= 0;
        A[6][0]= 0; A[6][1]= 0; A[6][2]= 0; A[6][3]= 0; A[6][4]= 0; A[6][5]= 0; A[6][6]= 0; A[6][7]= 0; A[6][8]= 0;
        A[7][0]= 0; A[7][1]= 0; A[7][2]= 0; A[7][3]= 0; A[7][4]= 0; A[7][5]= 0; A[7][6]= 0; A[7][7]= 0; A[7][8]= 0;
        A[8][0]= 0; A[8][1]= 0; A[8][2]= 0; A[8][3]= 0; A[8][4]= 0; A[8][5]= 0; A[8][6]= 0; A[8][7]= 0; A[8][8]= 0;
    
       
        int[][][] mark = new int[9][9][10]; //saves marks
        int[][] puzzle = new int[9][9]; //saves progress important when guessing is involved
        puzzle = copyMatrix(A);
        //////Solve///////
        matrixPrint(A);         //print unsolved sudoku puzzle
        System.out.println();   //print line
 
        if(SudokuCheck(A))  //check for errors
        {
            mark = Init_Mark();
            
            matrixPrint3D(mark);
            for(int i = 0; i < 81; i++) // run through many times and solve as much as you can
            {
                marker(puzzle,mark); //marks each cell with 1,2,3,4,5,6,7,8,9 and crosses out the ones that dont belong 
                puzzle = SudokuSolver(puzzle,mark);        //solve sudoku puzzle
            }
            
            matrixPrint3D(mark); //says which cell can take what number 
            matrixPrint(puzzle);         //print solved sudoku puzzle
            
            //check if solved
            if( !matrixCheck(puzzle)) 
            {
                System.out.println("guesser");
                Guesser(puzzle,mark);       //generates error if tries to solve a already solved puzzle 
            }

        }              
        
    }
}