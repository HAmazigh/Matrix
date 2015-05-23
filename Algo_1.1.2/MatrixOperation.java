package matrix;

public class MatrixOperation {

	/**
	 * Une classe qu'on n'instencie pas, faite pour faire les differents calculs mathématique
	 */
	private MatrixOperation(){}

	
	//transposer une Matrice
	
	public static Matrix transpose(Matrix matrix) {
		Matrix matriceTran = new Matrix(matrix.getNcols(), matrix.getNligne());
		for (int i=0;i<matrix.getNligne();i++) {
			for (int j=0;j<matrix.getNcols();j++) {
				matriceTran.setValueAt(j, i, matrix.getValueAt(i, j));
			}
		}
		return matriceTran;
	}
	
	
	//Une méthode pour inverser une matrice.
	/* 
	
	public static Matrix inverse(Matrix matrix) throws NoSquareException {
		return (transpose(cofactor(matrix)).multplierParCons(1.0/determinant(matrix)));
	}
	
	*/
	/** Le déterminant d'une matrice avec la méthode d'élimineur
	 */
	public static int determinant(Matrix matrix) throws NoSquareException {
		if (!matrix.isSquare())
			throw new NoSquareException("Matrice devrait être carrée");
		if (matrix.size() == 1){
			return matrix.getValueAt(0, 0);
		}
			
		if (matrix.size()==2) {
			return (matrix.getValueAt(0, 0) * matrix.getValueAt(1, 1)) - ( matrix.getValueAt(0, 1) * matrix.getValueAt(1, 0));
		}
		int sum = 0;
		for (int i=0; i<matrix.getNcols(); i++) {
					
			sum += changeSign(i) * matrix.getValueAt(0, i) * determinant(matrixExtraire(matrix, 0, i));
			
		}
		return sum;
	}

	/** Le déterminant d'une matrice avec la méthode d'élimineur sur l'ensemble Q
	 */

	public static Q determinant_(Matrix matrix) throws NoSquareException {
		if (!matrix.isSquare())
			throw new NoSquareException("Matrice devrait être carrée");
		if (matrix.size() == 1){
			return matrix.getValueAt_(0, 0);
		}
			
		if (matrix.size()==2) {
			Q mult1 = new Q();
			Q mult2 = new Q();
			Q sous1 = new Q();
			
			
			mult1.multiplication(matrix.getValueAt_(0, 0), matrix.getValueAt_(1, 1));
			mult2.multiplication(matrix.getValueAt_(0, 1) , matrix.getValueAt_(1, 0));
			sous1.soustraction(mult1, mult2);
			return sous1;
			
		}
		Q sum = new Q();
		for (int i=0; i<matrix.getNcols(); i++) {
			Q mult1 = new Q();
				mult1.multiplication(new Q(changeSign(i)), matrix.getValueAt_(0, i));
			Q mult2 = new Q();
			System.out.println("ça va entrer dans une boucle récursive");
				mult2.multiplication(mult1, determinant_(matrixExtraire_(matrix, 0, i)));
			
				sum.addition(sum, mult2);
				
		}
		
		return sum;
	}

	
	public static int changeSign(int i) {
		if (i%2==0)
			return 1;
		return -1;
	}
	
	/** Une méthode pour extraire une sous matrice, besoin pour calcule du determinant
	 */
	public static Matrix matrixExtraire(Matrix matrix, int ligneExclu, int colonneExclu) {
		Matrix mat = new Matrix(matrix.getNligne()-1, matrix.getNcols()-1);
		int r = -1;
		for (int i=0;i<matrix.getNligne();i++) {
			if (i==ligneExclu)
				continue;
				r++;
				int c = -1;
			for (int j=0;j<matrix.getNcols();j++) {
				if (j==colonneExclu)
					continue;
				mat.setValueAt(r, ++c, matrix.getValueAt(i, j));
			}
		}
		return mat;
	}
	
	public static Matrix matrixExtraire_(Matrix matrix, int ligneExclu, int colonneExclu) {
		Matrix mat = new Matrix(matrix.getNligne()-1, matrix.getNcols()-1);
	//	System.out.println("nombre ligne :\n"+mat.showMatrix());
	//	System.out.println("valeur de mat :\n"+mat.getData());
		
		int r = -1;
		for (int i=0;i<matrix.getNligne();i++) {
			if (i==ligneExclu)
				continue;
				r++;
				int c = -1;
			for (int j=0;j<matrix.getNcols();j++) {
				if (j==colonneExclu)
					continue;
			//	System.out.println("valeur de getvalueAt_ "+matrix.getValueAt_(i, j).showQ()+" \n la valeur de r et ++c :"+r+"_"+ ++c +"");
				System.out.println("valeur de mat :\n"+mat.getData());
				mat.setValueAt_(r, ++c, matrix.getValueAt_(i, j));
			}
		}
		
		System.out.println("je retourne le mat !");
		return mat;
	}
	
	
	
	/**
	 
	 **Additionner deux matrice, juste comme exemple pour tester les différente méthode
	 
	 */
	public static Matrix add(Matrix matrix1, Matrix matrix2) throws IllegalDimensionException {
		if (matrix1.getNcols() != matrix2.getNcols() || matrix1.getNligne() != matrix2.getNligne())
			throw new IllegalDimensionException("les deux matrices doivent avoir la même dimension");
		Matrix sumMatrix = new Matrix(matrix1.getNligne(), matrix1.getNcols());
		for (int i=0; i<matrix1.getNligne();i++) {
			for (int j=0;j<matrix1.getNcols();j++) 
				sumMatrix.setValueAt(i, j, matrix1.getValueAt(i, j) + matrix2.getValueAt(i,j));
			
		}
		return sumMatrix;
	}
	
	public static boolean premier(int n){
		boolean b =true;
		int i=2 ;
		
		while(b && i <n){
			if ( (n % i ) == 0 ){				
				b = false;	
			}
			i++;
	}
		return b;
	}
	


	

	

}
