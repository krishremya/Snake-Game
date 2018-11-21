import java.util.ArrayList;
import java.awt.Color;

public class DataOfSquare {
	//Array list containing colors
	ArrayList<Color> C =new ArrayList<Color>();
	int color;
	SPanel square;
	public DataOfSquare(int col){
		//Adding colors to the list
		C.add(Color.white);//color of snake, 0
		C.add(Color.green);//color of food, 1
		C.add(Color.black);//color of background, 2
		color=col;
		square = new SPanel(C.get(color));
	}
	public void lightMeUp(int c){
		square.ChangeColor(C.get(c));
	}
}
