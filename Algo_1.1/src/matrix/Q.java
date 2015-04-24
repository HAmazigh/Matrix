package matrix;

public class Q {
	
	long nomi;  // le nominateur de la fraction 
	long deno;	// le denominateur de la fraction 
	long[] quot;  //Un tableau qui contient le resultat de la division et le reste de la division 
	
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
	
	public Q(long a ){
		nomi = a;
		deno = 1;
		quot = euclide(nomi,deno);
	}
	public long[] euclide(long a, long b) {
		
		  long r = a;
		  long q = 0;

		  while (r >= b) {
		    r = r - b;
		    q = q + 1;
		  }

		  long[] euclide = { q, r };
		  return euclide;
		}
	
	public void plus(Q q1, Q q2){
		
		long a  = ((q1.nomi * q2.deno) + (q2.nomi*q1.deno)) ;
		long b  = (q1.deno* q2.deno);
		long pgcd = pgcd(a, b);
		
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
		long[] resultat = euclide ( ((q1.nomi * q2.deno) + (q2.nomi*q1.deno)), (q1.deno* q2.deno));
		this.quot = resultat;     
	}
	
	public void division(Q q1, Q q2){       //=================> Pour division q1/q2
		
		long a = (q1.nomi * q2.deno);
		long b = (q1.deno * q2.nomi);
		long pgcd = pgcd(a, b);
		
		System.out.println(" a = "+a+ " b = "+b+" pgcd = "+pgcd);
		if (pgcd != 0){
			
			this.nomi=euclide(a, pgcd)[0];
			System.out.println("euclide(a,pgcd)"+euclide(a, pgcd)[0]);
			this.deno=euclide(b,pgcd)[0];
			
		}else {
			this.nomi=a;
			this.deno=b;
		}
		
		long[] resultat = euclide((q1.nomi * q2.deno),(q1.deno * q2.nomi));
		this.quot = resultat;
		
	}
	
	public void soustraction (Q q1, Q q2){
		long a  = ((q1.nomi * q2.deno) - (q2.nomi*q1.deno)) ;
		long b  = (q1.deno* q2.deno);
	
		long pgcd = pgcd(a, b);
		
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
		long[] resultat = euclide ( ((q1.nomi * q2.deno) - (q2.nomi*q1.deno)), (q1.deno* q2.deno));
		this.quot = resultat;    
	}
	
	
	
	
	
	
	public String showQ(){
		if (deno == 1)
			return nomi+"";
		return  nomi+"/"+deno;
		
	}
	
	public long pgcd(long a, long b ){
		a = Math.abs(a); b= Math.abs(b);
	    while (a!=b)  
	    	if (a>b){a=a-b;}
	    else {b=b-a;};
	    return(a);
	}
	
}
