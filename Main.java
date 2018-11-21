import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		//Creating the window with all its awesome snaky features
		Window W= new Window();
		
		//Setting up the window settings
		W.setTitle("Snake");
		W.setSize(500,500);
		W.setVisible(true);
		W.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             

	}
}
