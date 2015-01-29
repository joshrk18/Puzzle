package puzzle;

/**
 * Sudoku solver    v1.01
 * date 1/15/2015
 * @author Josh Kertscher
 */
public class Sudoku 
{
        // create a 9x9 zero matrix
	static void matrixINIT(int[][] mat)
	{
		for(int i = 0 ; i < 9 ; i++)                    
			for(int j = 0 ; j < 9 ; j++)
			{
				mat[i][j] = 0;
			}
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

	static void matrixSu(int row, int col, int[][] mat, int num)
	{    
		if(row > 5)
		{
			if(col > 5)
				for(int i = 6 ; i < 9 ; i++)                    
					for(int j = 6 ; j < 9 ; j++)
						mat[i][j] = num;
			else if(col > 2)
				for(int i = 6 ; i < 9 ; i++)                    
					for(int j = 3 ; j < 6 ; j++)
						mat[i][j] = num;
			else
				for(int i = 6 ; i < 9 ; i++)                    
					for(int j = 0 ; j < 3 ; j++)
						mat[i][j] = num;
		}

		else if(row > 2)
		{
			if(col > 5)
				for(int i = 3 ; i < 6 ; i++)                    
					for(int j = 6 ; j < 9 ; j++)
						mat[i][j] = num;
			else if(col > 2)
				for(int i = 3 ; i < 6 ; i++)                    
					for(int j = 3 ; j < 6 ; j++)
						mat[i][j] = num;
			else
				for(int i = 3 ; i < 6 ; i++)                    
					for(int j = 0 ; j < 3 ; j++)
						mat[i][j] = num;
		}

		else
		{
			if(col > 5)
				for(int i = 0 ; i < 3 ; i++)                    
					for(int j = 6 ; j < 9 ; j++)
						mat[i][j] = num;
			else if(col > 2)
				for(int i = 0 ; i < 3 ; i++)                    
					for(int j = 3 ; j < 6 ; j++)
						mat[i][j] = num;
			else
				for(int i = 0 ; i < 3 ; i++)                    
					for(int j = 0 ; j < 3 ; j++)
						mat[i][j] = num;
		}     

	}

	static void matrixC(int[][] mats, int[][] A, int num)
	{
		for(int i = 0 ; i < 9 ; i++)                    // copy 1's into Matrix1
			for(int j = 0 ; j < 9 ; j++)
			{
				if(A[i][j] == num)
				{    

					for(int a = 0 ; a < 9 ; a++)
						mats[i][a] = num + 10;
					for(int a = 0 ; a < 9 ; a++)
						mats[a][j] = num + 10;
					mats[i][j] = num;  
				}
			}

		for(int i = 0 ; i < 9 ; i++)                    // copy 1's into Matrix1
			for(int j = 0 ; j < 9 ; j++)
				if(A[i][j] == num)
				{
					matrixSu(i,j,mats,num + 10);
					mats[i][j] = num;
				}
	}

	static void matrixCalc(int[][] mat, int[][] A, int num)
	{
		int times;

		for(int i = 0 ; i < 9 ; i++)                    
			for(int j = 0 ; j < 9 ; j++)
			{
				times = 0;

				if(mat[i][j] == 0)
				{
					for(int k = 0 ; k < 9 ; k++)
					{
						if(mat[i][k] == 0)
							times++;
					}

					for(int k = 0 ; k < 9 ; k++)
					{
						if(mat[k][j] == 0)
							times++;
					}
				}

				if(times == 2)
					A[i][j] = num;
			}    
	}

	static void matrixSpot(int[][] mat1, int[][] mat2, int[][] mat3, int[][] mat4, int[][] mat5, int[][] mat6, int[][] mat7, int[][] mat8, int[][] A , int num)
	{
		for(int i = 0 ; i < 9 ; i++)                    
			for(int j = 0 ; j < 9 ; j++)
			{
				if(A[i][j] == 0)
					if( (mat1[i][j] != 0) && (mat2[i][j] != 0) && (mat3[i][j] != 0) && (mat4[i][j] != 0) && (mat5[i][j] != 0) && (mat6[i][j] != 0) && (mat7[i][j] != 0) && (mat8[i][j] != 0))
						A[i][j] = num;
			}    
	}

	static boolean matrixCHECK(int[][] A)       //check if solution is valid 
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

        static boolean matrixCHECKZero(int[][] A)       //check if multiple solutions exist. Determined by if there is a zero left over
	{
		for(int i = 0 ; i < 9 ; i++)        //return false if no zeros exist                 
		{
			for(int k = 0 ; k < 9 ; k++)
			{
				if(A[i][k]==0) return true;
			}
		}
                return false;
        }
        
        static int[][] CopyMatrix(int[][] A)
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
        
        static int[][] MultipleSolutionSolver(int[][] A)       //Solve MultipleSolutionSolver
	{
            int[][] Copy;                       //copy matrix in case guess is wrong and we will be able to recover progress and try another guess              
            Copy = new int[9][9];
            Copy = CopyMatrix(A);
            
            for(int i = 0 ; i < 9 ; i++)        //find zero or empty cell and make guess.                 
            {
		for(int k = 0 ; k < 9 ; k++)
		{
                    if(Copy[i][k]==0)           //if cell is zero or empy put one solve check and increment till solution is found
                    {
                       for(int j = 1 ; j <= 9 ; j++)
                       {
                           Copy[i][k] = j;
                           SudokuSolver(Copy);        //solve sudoku puzzle
                           if(matrixCHECK(Copy))
                           {
                               System.out.println( (i+1) + " " + (k+1) + " " + j);
                               return Copy;
                           }
                           Copy = CopyMatrix(A);
                       } 
                    }
		}
            }
            return Copy;
        }
        
	public static int[][] SudokuSolver(int[][] A)
	{
                //create 9 9x9 zero matrixes
		int[][] A1;                                     
		A1 = new int[9][9];
		matrixINIT(A1);

		int[][] A2;                              
		A2 = new int[9][9];
		matrixINIT(A2);

		int[][] A3;
		A3 = new int[9][9];
		matrixINIT(A3);

		int[][] A4;                           
		A4 = new int[9][9];
		matrixINIT(A4); 

		int[][] A5;                       
		A5 = new int[9][9];
		matrixINIT(A5); 

		int[][] A6;               
		A6 = new int[9][9];
		matrixINIT(A6); 

		int[][] A7;                
		A7 = new int[9][9];
		matrixINIT(A7); 

		int[][] A8;                      
		A8 = new int[9][9];
		matrixINIT(A8); 

		int[][] A9;                          
		A9 = new int[9][9];
		matrixINIT(A9); 

		for(int i = 0; i < 81; i++)
		{
			matrixC(A1,A,1);
			matrixCalc(A1,A,1); 
			matrixSpot(A2,A3,A4,A5,A6,A7,A8,A9,A,1); 

			matrixC(A2,A,2); 
			matrixCalc(A2,A,2);
			matrixSpot(A3,A4,A5,A6,A7,A8,A9,A1,A,2);

			matrixC(A3,A,3);
			matrixCalc(A3,A,3);
			matrixSpot(A4,A5,A6,A7,A8,A9,A1,A2,A,3);

			matrixC(A4,A,4);
			matrixCalc(A4,A,4);
			matrixSpot(A5,A6,A7,A8,A9,A1,A2,A3,A,4);

			matrixC(A5,A,5);
			matrixCalc(A5,A,5);
			matrixSpot(A6,A7,A8,A9,A1,A2,A3,A4,A,5);

			matrixC(A6,A,6);
			matrixCalc(A6,A,6);
			matrixSpot(A7,A8,A9,A1,A2,A3,A4,A5,A,6);

			matrixC(A7,A,7);
			matrixCalc(A7,A,7);
			matrixSpot(A8,A9,A1,A2,A3,A4,A5,A6,A,7);

			matrixC(A8,A,8);
			matrixCalc(A8,A,8);
			matrixSpot(A9,A1,A2,A3,A4,A5,A6,A7,A,8);

			matrixC(A9,A,9);
			matrixCalc(A9,A,9);
			matrixSpot(A1,A2,A3,A4,A5,A6,A7,A8,A,9);
		}  
			return A;
	}

	public static void main(String[] args)
	{
                // Create matrix of sudoku puzzle to solve
                // zeros are the empty cells of sudoku puzzle
		int[][] A;                                    
		A = new int[9][9];  //example sudoku puzzle
		A[0][0]= 8; A[0][1]= 0; A[0][2]= 0; A[0][3]= 0; A[0][4]= 0; A[0][5]= 0; A[0][6]= 0; A[0][7]= 0; A[0][8]= 2;
		A[1][0]= 0; A[1][1]= 7; A[1][2]= 0; A[1][3]= 0; A[1][4]= 0; A[1][5]= 0; A[1][6]= 1; A[1][7]= 4; A[1][8]= 8;
		A[2][0]= 4; A[2][1]= 3; A[2][2]= 2; A[2][3]= 1; A[2][4]= 8; A[2][5]= 5; A[2][6]= 6; A[2][7]= 0; A[2][8]= 0;
		A[3][0]= 0; A[3][1]= 0; A[3][2]= 0; A[3][3]= 2; A[3][4]= 1; A[3][5]= 8; A[3][6]= 4; A[3][7]= 0; A[3][8]= 0;
		A[4][0]= 7; A[4][1]= 0; A[4][2]= 0; A[4][3]= 0; A[4][4]= 0; A[4][5]= 0; A[4][6]= 0; A[4][7]= 0; A[4][8]= 6;
		A[5][0]= 0; A[5][1]= 0; A[5][2]= 4; A[5][3]= 5; A[5][4]= 6; A[5][5]= 7; A[5][6]= 0; A[5][7]= 0; A[5][8]= 0;
		A[6][0]= 0; A[6][1]= 0; A[6][2]= 9; A[6][3]= 3; A[6][4]= 5; A[6][5]= 1; A[6][6]= 7; A[6][7]= 2; A[6][8]= 4;
		A[7][0]= 5; A[7][1]= 4; A[7][2]= 7; A[7][3]= 0; A[7][4]= 0; A[7][5]= 0; A[7][6]= 0; A[7][7]= 8; A[7][8]= 0;
		A[8][0]= 2; A[8][1]= 0; A[8][2]= 0; A[8][3]= 0; A[8][4]= 0; A[8][5]= 0; A[8][6]= 0; A[8][7]= 0; A[8][8]= 5;
                
                int[][] B;                                    
		B = new int[9][9];  //example sudoku puzzle mulitple solutions
		B[0][0]= 8; B[0][1]= 0; B[0][2]= 0; B[0][3]= 0; B[0][4]= 0; B[0][5]= 0; B[0][6]= 0; B[0][7]= 0; B[0][8]= 2;
		B[1][0]= 0; B[1][1]= 7; B[1][2]= 0; B[1][3]= 0; B[1][4]= 0; B[1][5]= 0; B[1][6]= 1; B[1][7]= 4; B[1][8]= 8;
		B[2][0]= 4; B[2][1]= 3; B[2][2]= 2; B[2][3]= 1; B[2][4]= 8; B[2][5]= 5; B[2][6]= 6; B[2][7]= 0; B[2][8]= 0;
		B[3][0]= 0; B[3][1]= 0; B[3][2]= 0; B[3][3]= 2; B[3][4]= 1; B[3][5]= 8; B[3][6]= 4; B[3][7]= 0; B[3][8]= 0;
		B[4][0]= 7; B[4][1]= 0; B[4][2]= 0; B[4][3]= 0; B[4][4]= 0; B[4][5]= 0; B[4][6]= 0; B[4][7]= 0; B[4][8]= 6;
		B[5][0]= 0; B[5][1]= 0; B[5][2]= 0; B[5][3]= 5; B[5][4]= 6; B[5][5]= 7; B[5][6]= 0; B[5][7]= 0; B[5][8]= 0;
		B[6][0]= 0; B[6][1]= 0; B[6][2]= 9; B[6][3]= 3; B[6][4]= 5; B[6][5]= 1; B[6][6]= 7; B[6][7]= 2; B[6][8]= 4;
		B[7][0]= 0; B[7][1]= 4; B[7][2]= 7; B[7][3]= 0; B[7][4]= 0; B[7][5]= 0; B[7][6]= 0; B[7][7]= 8; B[7][8]= 0;
		B[8][0]= 2; B[8][1]= 0; B[8][2]= 0; B[8][3]= 0; B[8][4]= 0; B[8][5]= 0; B[8][6]= 0; B[8][7]= 0; B[8][8]= 0;
                
                matrixPrint(B);         //print unsolved sudoku puzzle
		System.out.println();   //print line
		SudokuSolver(B);        //solve sudoku puzzle
                
                if(matrixCHECKZero(B))
                { 
                    System.out.println("Multiple Solutions exist"); 
                    B=MultipleSolutionSolver(B);
                    matrixPrint(B);
                }
                
                else if(matrixCHECK(B)){ System.out.println("ERROR"); }
                else
                { 
                    System.out.println("Solution found"); 
                    matrixPrint(B);         //print solved sudoku puzzle
                }
	}
}

