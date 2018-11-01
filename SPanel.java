import java.awt.Color;
import javax.swing.JPanel;

public class SPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;

	public SPanel(Color d){
		this.setBackground(d);
	}
	
	public void ChangeColor(Color d){
		this.setBackground(d);
		this.repaint();
	}
	
}


