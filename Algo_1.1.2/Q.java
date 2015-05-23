package matrix;

public class Q {
	
	protected int nomi;  // le nominateur de la fraction 
	protected int deno;	// le denominateur de la fraction 
	int[] quot;  //Un tableau qui contient le resultat de la division et le reste de la division 
	
	public Q(){
		this.nomi =0;
		this.deno =1;
		this.quot=null;
				
	}
	
	public Q(int a, int b){
		nomi = a;
		deno = b;
		quot = euclide(a,b);
	}
	// faire une surcharge pour le constructeur : 
			//  								-tableau d'entier/long en entré
			//								
			//									-**** Ça va poser unprblème au niveau de la reconnaissance ! 
			// 									-
	
	public Q(int a ){
		nomi = a;
		deno = 1;
		quot = euclide(nomi,deno);
	}
	public int[] euclide(int a, int b) {
		
		  int r = a;
		  int q = 0;

		  while (r >= b) {
		    r = r - b;
		    q = q + 1;
		  }

		  int[] euclide = { q, r };
		  return euclide;
		}
	
	public void addition(Q q1, Q q2){
		
		int a  = ((q1.nomi * q2.deno) + (q2.nomi*q1.deno)) ;
		int b  = (q1.deno* q2.deno);
		int pgcd = pgcd(a, b);
		
		if (pgcd != 0)
		{
			this.nomi = this.euclide(a, pgcd)[0];
			this.deno = this.euclide(b, pgcd)[0];
		}
		else 
		{
			this.nomi = a ;
			this.deno = b ;
		}
		int[] resultat = euclide ( ((q1.nomi * q2.deno) + (q2.nomi*q1.deno)), (q1.deno* q2.deno));
		this.quot = resultat;     
	}
	
	public void division(Q q1, Q q2){       //=================> Pour division q1/q2
		
		int a = (q1.nomi * q2.deno);
		int b = (q1.deno * q2.nomi);
		int pgcd = pgcd(a, b);
		
	//	System.out.println(" a = "+a+ " b = "+b+" pgcd = "+pgcd);
		if (pgcd != 0){
			
			this.nomi=euclide(a, pgcd)[0];
	//		System.out.println("euclide(a,pgcd)"+euclide(a, pgcd)[0]);
			this.deno=euclide(b,pgcd)[0];
			
		}else {
			this.nomi=a;
			this.deno=b;
		}
		
		int[] resultat = euclide((q1.nomi * q2.deno),(q1.deno * q2.nomi));
		this.quot = resultat;
		
	}
	
	public void soustraction (Q q1, Q q2){
		int a  = ((q1.nomi * q2.deno) - (q2.nomi*q1.deno)) ;
		int b  = (q1.deno* q2.deno);
	
		int pgcd = pgcd(a, b);
		
		if (pgcd != 0)
		{
			this.nomi = this.euclide(a, pgcd)[0];
			this.deno = this.euclide(b, pgcd)[0];
		}
		else 
		{
			this.nomi = a ;
			this.deno = b ;
		}
		int[] resultat = euclide ( ((q1.nomi * q2.deno) - (q2.nomi*q1.deno)), (q1.deno* q2.deno));
		this.quot = resultat;    
	}
	
	public void multiplication(Q q1, Q q2){
		try {
		
		int a= q1.nomi * q2.nomi;
		int b = q1.deno * q2.deno;
		int pgcd = pgcd (a,b);
		
		if (pgcd != 0){
			this.nomi = this.euclide(a, pgcd)[0];
			this.deno = this.euclide(b, pgcd)[0];
		}
		else{
			this.nomi = a;
			this.deno = b;
		}
		
		}catch (ArithmeticException e){
			System.out.print("Exception ! Division par zéro");
		}
		
		
	}
	
	public int getNomi(){
		return this.nomi;
	}
	public int getDeno(){
		return this.deno;
	}
	
	void setNomi (int n){
		this.nomi=n;
	}
	void setDeno (int d){
		this.deno=d;
	}
	
	
	
	
	public String showQ(){
		if (deno == 1)
			return nomi+"";
		return  nomi+"/"+deno;
		
	}
	
	public int pgcd(int a, int b ){
		a = Math.abs(a); b= Math.abs(b);
	    while (a!=b)  
	    	if (a>b){a=a-b;}
	    else {b=b-a;};
	    return(a);
	}
	
}
