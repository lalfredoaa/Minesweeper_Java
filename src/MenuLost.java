import java.awt.*;
import javax.swing.*;

public class MenuLost extends MenuBasico {

	private JLabel perdiste;
	
	public MenuLost() {
		
		super();
		
		perdiste = new JLabel("Has perdido :(");
		perdiste.setBounds(100,30,100,40);
		frame.add(perdiste);
	}
}
