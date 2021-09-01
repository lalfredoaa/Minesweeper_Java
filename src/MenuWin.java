import java.awt.*;
import javax.swing.*;

public class MenuWin extends MenuBasico {

	private JLabel ganaste;
	
	public MenuWin() {
		
		super();
		
		ganaste = new JLabel("Has ganado :D");
		ganaste.setBounds(100,30,100,40);
		frame.add(ganaste);
	}
}
