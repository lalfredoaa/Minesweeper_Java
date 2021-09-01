import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

public class MenuSettings extends JFrame implements  ActionListener  {

	private JRadioButton b1, b2, b3;
	private ButtonGroup grupo;
	private JTextField texto;
	private JButton aceptar;
	
	public MenuSettings() {
		
		this.setTitle("Nuevo juego");
		setBounds(300,300,300,400);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel dificultad = new JLabel("Seleccione la dificultad:");
		dificultad.setBounds(50,20,200,30);
		add(dificultad);
		
		b1 = new JRadioButton("Principiante");
		b1.setName("principiante");
		b1.setBounds(50,60,200,30);
		add(b1);
		
		b2 = new JRadioButton("Intermedio");
		b2.setName("intermedio");
		b2.setBounds(50,100,200,30);
		add(b2);
		
		b3 = new JRadioButton("Avanzado");
		b3.setName("avanzado");
		b3.setBounds(50,140,200,30);
		add(b3);
		
		grupo = new ButtonGroup();
		grupo.add(b1);
		grupo.add(b2);
		grupo.add(b3);
		
		JLabel nombre = new JLabel("Nombre:");
		nombre.setBounds(50,180,200,30);
		add(nombre);
		
		texto = new JTextField();
		texto.setBounds(50,220,200,30);
		texto.setEditable(true);
		add(texto);
		
		aceptar = new JButton("Aceptar");
		aceptar.setBounds(100,280,100,30);
		aceptar.addActionListener(this);
		add(aceptar);
				
		setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String nombre = texto.getText();
		if(b1.isSelected()) {
			Tablero juego = new Tablero(9,9,10,nombre);
			this.dispose();
		}
		else if(b2.isSelected()) {
			Tablero juego = new Tablero(12,12,15,nombre);
			this.dispose();
		}
		else if(b3.isSelected()) {
			Tablero juego = new Tablero(15,15,18,nombre);
			this.dispose();
		}
		else {
			JOptionPane.showMessageDialog(null,  "Elija una dificultad");
		}
	}

}
