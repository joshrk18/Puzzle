package puzzle;

/**
 * Sudoku solver    v1.01
 * @author Josh Kertscher
 */
public class Sudoku 
{

	static void matrixINIT(int[][] mat)
	{
		for(int i = 0 ; i < 9 ; i++)                    // initialize Matrix
			for(int j = 0 ; j < 9 ; j++)
			{
				mat[i][j] = 0;
			}
	}

	static void matrixPrint(int[][] mat)
	{
		for(int i = 0 ; i < 9 ; i++)                    // Print Matrix
			for(int j = 0 ; j < 9 ; j++)
			{
				if(j < 8)
					System.out.print(mat[i][j] + " ");
				if(j == 8)
					System.out.println(mat[i][j] + " ");
			}
		System.out.println();                           //blank line
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

	static boolean matrixCHECK(int[][] A)
	{
		int sumr = 0;
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

		for(int i = 0 ; i < 9 ; i++)                    
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

        for(int i = 0 ; i < 3 ; i++)                    
            for(int j = 0 ; j < 3 ; j++)
                sum11 += A[i][j];

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
            return false;

		return true;
	}

	public static int[][] SudokuSolved(int[][] A)
	{
		int[][] A1;                                     // create matrix1 to break up solve
		A1 = new int[9][9];
		matrixINIT(A1);

		int[][] A2;                                     // create matrix2 to break up solve
		A2 = new int[9][9];
		matrixINIT(A2);

		int[][] A3;                                     // create matrix3 to break up solve
		A3 = new int[9][9];
		matrixINIT(A3);

		int[][] A4;                                     // create matrix4 to break up solve
		A4 = new int[9][9];
		matrixINIT(A4); 

		int[][] A5;                                     // create matrix5 to break up solve
		A5 = new int[9][9];
		matrixINIT(A5); 

		int[][] A6;                                     // create matrix6 to break up solve
		A6 = new int[9][9];
		matrixINIT(A6); 

		int[][] A7;                                     // create matrix7 to break up solve
		A7 = new int[9][9];
		matrixINIT(A7); 

		int[][] A8;                                     // create matrix8 to break up solve
		A8 = new int[9][9];
		matrixINIT(A8); 

		int[][] A9;                                     // create matrix9 to break up solve
		A9 = new int[9][9];
		matrixINIT(A9); 

		for(int i = 0; i < 100; i++)
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

		if(matrixCHECK(A))
			return A;
		else
		{
			System.out.println("error");
			return A;
		}
	}

	public static void main(String[] args)
	{
		int[][] A;                                      // create matrix to solve
		A = new int[9][9];
		A[0][0]= 8; A[0][1]= 0; A[0][2]= 0; A[0][3]= 0; A[0][4]= 0; A[0][5]= 0; A[0][6]= 0; A[0][7]= 0; A[0][8]= 2;
		A[1][0]= 0; A[1][1]= 7; A[1][2]= 0; A[1][3]= 0; A[1][4]= 0; A[1][5]= 0; A[1][6]= 1; A[1][7]= 4; A[1][8]= 8;
		A[2][0]= 4; A[2][1]= 3; A[2][2]= 2; A[2][3]= 1; A[2][4]= 8; A[2][5]= 5; A[2][6]= 6; A[2][7]= 0; A[2][8]= 0;
		A[3][0]= 0; A[3][1]= 0; A[3][2]= 0; A[3][3]= 2; A[3][4]= 1; A[3][5]= 8; A[3][6]= 4; A[3][7]= 0; A[3][8]= 0;
		A[4][0]= 7; A[4][1]= 0; A[4][2]= 0; A[4][3]= 0; A[4][4]= 0; A[4][5]= 0; A[4][6]= 0; A[4][7]= 0; A[4][8]= 6;
		A[5][0]= 0; A[5][1]= 0; A[5][2]= 4; A[5][3]= 5; A[5][4]= 6; A[5][5]= 7; A[5][6]= 0; A[5][7]= 0; A[5][8]= 0;
		A[6][0]= 0; A[6][1]= 0; A[6][2]= 9; A[6][3]= 3; A[6][4]= 5; A[6][5]= 1; A[6][6]= 7; A[6][7]= 2; A[6][8]= 4;
		A[7][0]= 5; A[7][1]= 4; A[7][2]= 7; A[7][3]= 0; A[7][4]= 0; A[7][5]= 0; A[7][6]= 0; A[7][7]= 8; A[7][8]= 0;
		A[8][0]= 2; A[8][1]= 0; A[8][2]= 0; A[8][3]= 0; A[8][4]= 0; A[8][5]= 0; A[8][6]= 0; A[8][7]= 0; A[8][8]= 5;

        matrixPrint(A);
		System.out.println();
		SudokuSolved(A);
		matrixPrint(A);



	}
}

