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
	/** Le déterminant d'une matrice avec la méthode d'élimination de Gauss
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
	

	

}
