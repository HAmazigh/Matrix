package matrix;

public class LUDecomposition  {
	
	private int [][] LU;   //un tableau pour le stockage interne 
	private int m,n, pivsign	;
	
	private int[] piv; // un tableau pour le stockage interne 
	
	/*
	public LUDecomposition (Matrix A) {
		
		
		 
		      LU = A.getArrayCopy();
		      
		      System.out.println("la valeur de LU : "+this.showArray2D());
		      
		      m = A.getligneDimension();
		      n = A.getColsDimension();
		      piv = new int[m];
		      
		      for (int i = 0; i < m; i++) {
		         piv[i] = i;
		      }
		      System.out.println("la valeur de piv est : "+this.showArray(piv));
		      
		      pivsign = 1;
		      int[] LUrowi;
		      int[] LUcolj = new int[m];

		      // Outer loop.

		      for (int j = 0; j < n; j++) {

		         // Make a copy of the j-th column to localize references.

		         for (int i = 0; i < m; i++) {
		            LUcolj[i] = LU[i][j];
		         }
		         System.out.println("la valeur de LUcolj est : "+this.showArray(LUcolj));

		         // Apply previous transformations.

		         for (int i = 0; i < m; i++) {
		            LUrowi = LU[i];   //On prend la ligne i 
		           
		            int kmax = Math.min(i,j);
		            int s = 0;
		            for (int k = 0; k < kmax; k++) {
		               s += LUrowi[k]*LUcolj[k];
		               System.out.println("la valeur de s à chaque itération : "+s);
		               
		            }
		           // System.out.println("la valeur de LUcolj[j]"+LUrowi[j]);  on prend chaque valeur de la colonne
		            System.out.println("la valeur de LUcolj[j]"+LUrowi[i]);
		            LUrowi[j] = LUcolj[i] -= s;
		         }
		   
		         // Find pivot and exchange if necessary.

		         int p = j;
		         for (int i = j+1; i < m; i++) {
		            if (Math.abs(LUcolj[i]) > Math.abs(LUcolj[p])) {
		               p = i;
		            }
		         }
		         if (p != j) {
		            for (int k = 0; k < n; k++) {
		               int t = LU[p][k]; LU[p][k] = LU[j][k]; LU[j][k] = t;
		            }
		            int k = piv[p]; piv[p] = piv[j]; piv[j] = k;
		            pivsign = -pivsign;
		         }

		         // Compute multipliers.
		         
		         if (j < m & LU[j][j] != 0) {
		            for (int i = j+1; i < m; i++) {
		               LU[i][j] /= LU[j][j];
		            }
		         }
		      }
		   }
	*/
	
	public LUDecomposition (Matrix ma){
		int  m = ma.getligneDimension();
		int n = ma.getColsDimension();
		
		 Matrix X = new Matrix(m,n);
	      int[][] l = X.getArray();  
		
		int u[][]= new int[m][n];
		int a[][] = ma.getArray();
		
		for (int i=0; i<m; i++)
			for (int j=0; j<n; j++){
				
				if (i==j){
					l[i][j]=1;
					u[i][j]=1;
				}else {
				l[i][j]=0;
				u[i][j]=0;
				}
			}
		/*
		Matrix lu = new Matrix(u);
		System.out.println(""+ma.showMatrix()); 
		*/
		
		u[0][0] = a[0][0];
		
		for (int j=1; j<n; j++){
			u[1][j]=a[1][j];
			l[j][1]=a[j][1]/a[1][1];      //à modifier lors de l'utilisation de la bibliothèque Q
		}
		
		for (int i=1; i<n-1; i++ ){
			int som = 0;
			
			for (int k=0; k<i-1; k++)
			{
				som = som + l[i][k] * u[k][i];
			}
			u[i][i]=a[i][i] - som;
			
			
			for (int j=i+1; j<n; j++)
			{
				int som1 = 0;
				for (int k = 0; k <i-1; k++)
				{
					som1 = som1 +l[i][k] *  u[k][j];
				}
				u[i][j]= a[i][j] - som1;
				
				int som2 = 0;
				for (int k = 0; k<i-1; k++)
				{
					som2= som2 +l[j][k]*u[k][j];
				}
				 l[j][i] = 1/u[i][i]*(a[j][i] - som2);
				
			}
		}
	int som =0;
	for (int k=0; k<n-1; k++)
	{
		som = som + l[n][k]*u[k][n];
	}
		u[n][n]=a[n][n] - som;
	}
	
	
	
	
	
	
	
	
	
	//avoir la matrice triangulaire inférieure
	public Matrix getL () {
	      Matrix X = new Matrix(m,n);
	      int[][] l = X.getArray();      
	      for (int i = 0; i < m; i++) {
	         for (int j = 0; j < n; j++) {
	        //	 System.out.println("valeur de LU[i][j] "+LU[i][j]);
	            if (i > j) {
	               l[i][j] = LU[i][j];
	             //  System.out.println("valeur de LU[i][j] "+LU[i][j]);
	            } else if (i == j) {
	               l[i][j] = 1;
	            } else {
	               l[i][j] = 0;
	            }
	         }
	      }
	      
	      Matrix L = new Matrix(l);
	    //  System.out.println("la valeur de L :\n"+L.showMatrix());
	      return L;
	      
	   }
	
	//avoir la matrice triangulaire supérieure
	public Matrix getU () {
	      Matrix X = new Matrix(n,n);
	      int[][] u = X.getArray();
	      for (int i = 0; i < n; i++) {
	         for (int j = 0; j < n; j++) {
	            if (i <= j) {
	        //   	System.out.println("valeur de LU[i][j] "+LU[i][j]);
	               u[i][j] = LU[i][j];
	            } else {
	               u[i][j] = 0;
	            }
	         }
	      }
	      Matrix U = new Matrix(u);
	      return U;
	   }
	
	
	//******************************* Méthodes pour des tests **********************************//
	public String showArray2D (){
		int m = LU.length;   
		int n =LU[0].length;
		String val="";
		for (int i = 0; i< m; i++)
			for (int j= 0; j<n; j++){
				val = val+LU[i][j]+",";
			//	System.out.println("val["+i+"]["+j+"]="+LU[i][j]);
			}
		return val;
	}
	
	public String showArray(int t[]){
		
		int m= t.length;
		String val = " ";
		for (int i = 0; i<m; i++){
			val = val + t[i]+",";
		}
		return val;
		
	}

}
