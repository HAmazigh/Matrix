package matrix;





public class Main {
	
	@SuppressWarnings("unused")
	
	public static void main (String args[]){
		
		//*************Déclaration de tableaux d'entiers pour 'data' matrice***************************//
		
		Matrix m = new Matrix (3,3);   //constructeur avec le nombre de ligne/colonne donné  rempli de 0
		
		//les tableau de matrice 
		int m2_[][]= {{2,3,6,4},{1,6,5,7},{2,3,9,2},{0,8,3,7}}; 
		int m3_[][]= {{5,-6,2,0},{9,0,5,6},{2,3,0,2},{8,9,3,7}};
		int m4_[][]= {{2,1,-2},{4,5,-3},{-2,5,3}};
		int m1_[][]={{1,3,2,13},{3,10,11,5},{9,6,7,12},{4,15,14,1}};  // <--- l'image de test sur l'application 
		
		//***************Construction de matrice à partir des tableaux ******************************//
		Matrix m1 = new Matrix (m1_);
		Matrix m2 = new Matrix (m2_);  
		Matrix m3 = new Matrix (m3_);
		Matrix m4 = new Matrix (m4_);
		
		int det_m1,det_m2,det_m4;
		
		System.out.println("Affichage, nbr cols : "+m2.getNcols());
		System.out.println("Affichage, nbr rows : "+m2.getNligne());
	//	System.out.println("Affichage de la matrice m4 : \n"+m4.showMatrix());
		
		//transposer une matrice
		//Matrix m_trans = MatrixOperation.transpose(m2);
		//System.out.println("Affichage de la matrice transposée : \n"+m_trans.showMatrix());
		
	try {
		
		det_m2 = MatrixOperation.determinant(m2);
		det_m4 = MatrixOperation.determinant(m4);
		det_m1 = MatrixOperation.determinant(m1);
		
		System.out.println("le détérminant de la matrice m1 ===> "+det_m1);
		System.out.println("Le determinant de la matrice m2 = \n"+m1.showMatrix()+"  === "+det_m2);
		System.out.println("Le determinant de la matrice m2 = \n"+m4.showMatrix()+"  === "+det_m4);
		
		System.out.println("\n\n\n************************* Tests de la decomposition LU ************************ \n \n");
	//	LUDecomposition lu = new LUDecomposition(m4);
	//	Matrix L = lu.getL();
	//	Matrix U = lu.getU();
	//	System.out.println("Affichage de la matrice L : \n"+L.showMatrix());
	//	System.out.println("Affichage de la matrice U :: \n"+U.showMatrix());
	
		/**
		 *  calculs sur des corps fini avec la méthode : MatrixOperation.corps_fini(matrice, nombre_premier)
		 */
	Matrix ma_matrice = MatrixOperation.corps_fini(m1, 11);
	System.out.println(" la matrice m1 dans le corps fini 11 est :\n "+ma_matrice.showMatrix());
	

	
	Q q = new Q(5,6);
	System.out.println(" q = "+q.showQ()+" ");
	
	LUDecomposition lu = new LUDecomposition(m1);
	
	
	} catch (Exception e){
		
		e.printStackTrace();
	}
		
	}

}
