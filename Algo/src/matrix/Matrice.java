package matrix;

public class Matrice {
	public static void main (String args[]){
		
		Matrix m1 = new Matrix (3,3);   //constructeur avec le nombre de ligne/colonne donné
		double m2_[][]= {{2,3,6,4},{1,6,5,7},{2,3,9,2},{0,8,3,7}};
		Matrix m2 = new Matrix (m2_);  //constructeur avec un tableau multiD en parametre.
		double det;
		
		System.out.println("Affichage, nbr cols : "+m2.getNcols());
		System.out.println("Affichage, nbr rows : "+m2.getNligne());
		System.out.println("Affichage de la matrice : "+m2.showMatrix());
		
		Matrix m_trans = MatrixOperation.transpose(m2);
	try {
		det = MatrixOperation.determinant(m2);
		System.out.println("Le determinant de la matrice m2 : "+det);
	} catch (Exception e){
		
		e.printStackTrace();
	}
		
	}

}
