package matrix;





public class Main {
	
	@SuppressWarnings("unused")
	
	public static void main (String args[]){
		
		//*************Déclaration de tableaux d'entiers pour 'data' matrice***************************//
		
		Matrix m = new Matrix (3,3);   //constructeur avec le nombre de ligne/colonne donné  rempli de 0
		
		//les tableau de matrice 
		int m2_[][]= {{2,3,6,4},{1,6,5,7},{2,3,9,2}}; 
		int m3_[][]= {{5,-6,2,0},{9,0,5,6},{2,3,0,2},{8,9,3,7}};
		Q m4_[][]= {{new Q(2,3), new Q(4,6)},{new Q(4,5), new Q(5,3)}};
		int m1_[][]={{1,3,2,13},{3,10,11,5},{9,6,7,12},{4,15,14,1}};  // <--- l'image de test sur l'application 
		Q m5_[][] = {{new Q(1,2), new Q(3,5), new Q(5,4)},{new Q(6,2), new Q(7,5), new Q(2,4)},{new Q(6,2), new Q(7,5), new Q(2,4)}};
		
		
		//***************Construction de matrice à partir des tableaux ******************************//
		Matrix m1 = new Matrix (m1_);
		Matrix m2 = new Matrix (m2_);  
		Matrix m3 = new Matrix (m3_);
		Matrix m4 = new Matrix (m4_);
		Matrix m5 = new Matrix (m5_);
		
		
		int det_m2;
		Q det_m5 = new Q();
		Q det_m4= new Q();
		
		
	try {
		
	
		det_m4 = MatrixOperation.determinant_(m4);

		
		det_m5 = MatrixOperation.determinant_(m5);
		
		System.out.println(" la matrice : \n"+m5.showMatrix_());
		System.out.println("le détérminant de la matrice m1 ===> "+det_m4.showQ());
	//	System.out.println("Le determinant de la matrice m2 = \n"+m1.showMatrix()+"  === "+det_m2);
	//	System.out.println("Le determinant de la matrice m2 = \n"+m4.showMatrix()+"  === "+det_m4);
		
		
		
		
		System.out.println("\n\n\n************************* Tests de la decomposition LU ************************ \n \n");
	//	LUDecomposition lu = new LUDecomposition(m4);
	//	Matrix L = lu.getL();
	//	Matrix U = lu.getU();
	//	System.out.println("Affichage de la matrice L : \n"+L.showMatrix());
	//	System.out.println("Affichage de la matrice U :: \n"+U.showMatrix());
	
		/**
		 *  calculs sur des corps fini avec la méthode : MatrixOperation.corps_fini(matrice, nombre_premier)
		 */
	Matrix ma_matrice = CorpsFinis.corps_fini(m1, 11);
	int det_m1 = CorpsFinis.determinant_CorpsFinis(ma_matrice, 11);
	System.out.println(" la matrice m1 dans le corps fini 11 est :\n"+ma_matrice.showMatrix());
	System.out.println("le determinant de m1 dans 11 est : "+det_m1);
	

	Q q = new Q(8,1);
	Q q1 = new Q(-5,6);
	Q q2= new Q(6,7); 
	Q re = new Q();
	
	re.division(q1, q2);
//	System.out.println("resultat div : "+re.showQ()+"= "+re.quot[0]+" le reste de la division : "+re.quot[1]);
	
//	LUDecomposition lu = new LUDecomposition(m1);
	
	
	} catch (Exception e){
		
		e.printStackTrace();
	}
		
	}

}
