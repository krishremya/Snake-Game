import java.awt.Color;
import javax.swing.JPanel;

public class SPanel extends JPanel{


	public SPanel(Color d){
		this.setBackground(d);
	}
	
	public void ChangeColor(Color d){
		this.setBackground(d);
		this.repaint();
	}
	
}


