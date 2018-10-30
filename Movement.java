public class Movement {
	  public  int a; 
	  public  int b; 
	  public int af;
	  public int bf;
	  
	  public Movement(int a, int b) { 
	    this.a = a; 
	    this.b = b; 
	  } 
	  public void ChangeData(int a, int b){
		    this.a = a; 
		    this.b = b; 
	  }
	  public int getX(){
		  return a;
	  }
	  public int getY(){
		  return b;
	  }
	  public int getXf(){
		  return af;
	  }
	  public int getYf(){
		  return bf;
	  }
}
