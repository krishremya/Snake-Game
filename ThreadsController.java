import java.util.ArrayList;


//Controls all the game logic .. most important class in this project.
public class ThreadsController extends Thread {
	 ArrayList<ArrayList<DataOfSquare>> Squares= new ArrayList<ArrayList<DataOfSquare>>();
	 Movement headSnakePos;
	 int sizeSnake=4;
	 long speed = 150;
	 public static int directionSnake ;

	 ArrayList<Movement> positions = new ArrayList<Movement>();
	 Movement foodPosition;
	 
	 //Constructor of ControlleurThread 
	 ThreadsController(Movement positionDepart){
		//Get all the threads
		Squares=Window.Grid;
		
		headSnakePos=new Movement(positionDepart.a,positionDepart.b);
		directionSnake = 1;

		//!!! Pointer !!!!
		Movement headPos = new Movement(headSnakePos.getX(),headSnakePos.getY());
		positions.add(headPos);
		
		foodPosition= new Movement(Window.height-1,Window.width-1);
		spawnFood(foodPosition);

	 }
	 
	 //Important part :
	 public void run() {
		 while(true){
			 moveInterne(directionSnake);
			 checkCollision();
			 moveExterne();
			 deleteTail();
			 pauser();
		 }
	 }
	 
	 //delay between each move of the snake
	 private void pauser(){
		 try {
				sleep(speed);
		 } catch (InterruptedException e) {
				e.printStackTrace();
		 }
	 }
	 
	 //Checking if the snake bites itself or is eating
	 private void checkCollision() {
		 Movement posCritique = positions.get(positions.size()-1);
		 for(int i = 0;i<=positions.size()-2;i++){
			 boolean biteItself = posCritique.getX()==positions.get(i).getX() && posCritique.getY()==positions.get(i).getY();
			 if(biteItself){
				stopTheGame();
			 }
		 }
		 
		 boolean eatingFood = posCritique.getX()==foodPosition.b && posCritique.getY()==foodPosition.a;
		 if(eatingFood){
			 System.out.println("Yummy!");
			 sizeSnake=sizeSnake+1;
			 	foodPosition = getValAleaNotInSnake();

			 spawnFood(foodPosition);	
		 }
	 }
	 
	 //Stops The Game
	 private void stopTheGame(){
		 System.out.println("You Lost \n");
		 while(true){
			 pauser();
		 }
	 }
	 
	 //Put food in a position and displays it
	 private void spawnFood(Movement foodPositionIn){
		 	Squares.get(foodPositionIn.a).get(foodPositionIn.b).lightMeUp(1);
	 }
	 
	 //return a position not occupied by the snake
	 private Movement getValAleaNotInSnake(){
		 Movement p ;
		 int ranX= 0 + (int)(Math.random()*19); 
		 int ranY= 0 + (int)(Math.random()*19); 
		 p=new Movement(ranX,ranY);
		 for(int i = 0;i<=positions.size()-1;i++){
			 if(p.getY()==positions.get(i).getX() && p.getX()==positions.get(i).getY()){
				 ranX= 0 + (int)(Math.random()*19); 
				 ranY= 0 + (int)(Math.random()*19); 
				 p=new Movement(ranX,ranY);
				 i=0;
			 }
		 }
		 return p;
	 }
	 
	 //Moves the head of the snake and refreshes the positions in the arraylist
	 //1:right 2:left 3:top 4:bottom 0:nothing
	 private void moveInterne(int dir){
		 switch(dir){
		 	case 4:
				 headSnakePos.ChangeData(headSnakePos.a,(headSnakePos.b+1)%20);
				 positions.add(new Movement(headSnakePos.a,headSnakePos.b));
		 		break;
		 	case 3:
		 		if(headSnakePos.b-1<0){
		 			 headSnakePos.ChangeData(headSnakePos.a,19);
		 		 }
		 		else{
				 headSnakePos.ChangeData(headSnakePos.a,Math.abs(headSnakePos.b-1)%20);
		 		}
				 positions.add(new Movement(headSnakePos.a,headSnakePos.b));
		 		break;
		 	case 2:
		 		 if(headSnakePos.a-1<0){
		 			 headSnakePos.ChangeData(19,headSnakePos.b);
		 		 }
		 		 else{
		 			 headSnakePos.ChangeData(Math.abs(headSnakePos.a-1)%20,headSnakePos.b);
		 		 } 
		 		positions.add(new Movement(headSnakePos.a,headSnakePos.b));

		 		break;
		 	case 1:
				 headSnakePos.ChangeData(Math.abs(headSnakePos.a+1)%20,headSnakePos.b);
				 positions.add(new Movement(headSnakePos.a,headSnakePos.b));
		 		 break;
		 }
	 }
	 
	 //Refresh the squares that needs to be 
	 private void moveExterne(){
		 for(Movement t : positions){
			 int y = t.getX();
			 int x = t.getY();
			 Squares.get(x).get(y).lightMeUp(0);
			 
		 }
	 }
	 
	 //Refreshes the tail of the snake, by removing the superfluous data in positions arraylist
	 //and refreshing the display of the things that is removed
	 private void deleteTail(){
		 int cmpt = sizeSnake;
		 for(int i = positions.size()-1;i>=0;i--){
			 if(cmpt==0){
				 Movement t = positions.get(i);
				 Squares.get(t.b).get(t.a).lightMeUp(2);
			 }
			 else{
				 cmpt--;
			 }
		 }
		 cmpt = sizeSnake;
		 for(int i = positions.size()-1;i>=0;i--){
			 if(cmpt==0){
				 positions.remove(i);
			 }
			 else{
				 cmpt--;
			 }
		 }
	 }
}
