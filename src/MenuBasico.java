import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MenuBasico implements ActionListener {
	
	protected JFrame frame = new JFrame("Juego");
	protected JButton nuevo = new JButton("Nuevo Juego");
	protected JButton salir = new JButton("Salir");
	
	public MenuBasico() {
		
		frame.setBounds(300,300,300,180);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		
		nuevo.setName("Nuevo");
		nuevo.addActionListener(this);
		nuevo.setBounds(25,80,100,50);
		frame.add(nuevo);
		
		salir.setName("Salir");
		salir.addActionListener(this);
		salir.setBounds(175,80,100,50);
		frame.add(salir);
		
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton tmp = (JButton)e.getSource();
		
		if(tmp.getName()=="Salir") {
			System.exit(0);
		}
		else if(tmp.getName()=="Nuevo") {
			MenuSettings nuevoJuego = new MenuSettings();
			frame.dispose();
		}
	}
}
