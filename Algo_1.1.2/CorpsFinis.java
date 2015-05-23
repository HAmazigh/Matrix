package matrix;

public class CorpsFinis {
	
	/**
	 
	 **faire des calculs sur des corps fini n avec n est un nombre premier
	 
	 */

	public static Matrix corps_fini(Matrix matrice, int n){
		int [][] m;
		m = matrice.getArrayCopy();
		
		if (premier(n)) {
			for (int i=0; i <matrice.getNligne(); i++){
				for (int j=0; j<matrice.getNcols(); j++) {
					m[i][j]=m[i][j] % n;
				}
			}
		}
		Matrix matrice_corp = new Matrix (m);
		return matrice_corp;
		
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
	
	
	/** Le déterminant d'une matrice avec la méthode d'élimineur sur un corps finis 'n'
	 */
	public static int determinant_CorpsFinis(Matrix m, int n) throws NoSquareException {
		if (!m.isSquare())
			throw new NoSquareException("Matrice devrait être carrée");
		
		Matrix matrix = CorpsFinis.corps_fini(m, n);
		
		if (matrix.size() == 1){
			return matrix.getValueAt(0, 0);
		}
			
		if (matrix.size()==2) {
			return ((matrix.getValueAt(0, 0) * matrix.getValueAt(1, 1)) % n - ( matrix.getValueAt(0, 1) * matrix.getValueAt(1, 0)) % n) % n;
		}
		int sum = 0;
		for (int i=0; i<matrix.getNcols(); i++) {
					
			sum += (MatrixOperation.changeSign(i) * matrix.getValueAt(0, i) * determinant_CorpsFinis(MatrixOperation.matrixExtraire(matrix, 0, i),n)) % n;
			
		}
		return sum;
	}

	
	
	
	
}
