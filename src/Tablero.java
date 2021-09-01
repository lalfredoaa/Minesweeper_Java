import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Tablero implements MouseListener, ActionListener {

	private JButton[][] botones;
	private JLabel[][] cuadritos;
	private Numeros valores;
	private JFrame frame = new JFrame();
	
	private int perdiste=0;
	
	public Tablero(int filas, int columnas, int minas, String nombre) {
		
		valores = new Numeros(filas, columnas, minas);
		//objeto de clase Numeros para manipular las coordenadas facilmente

		botones = new JButton [valores.getFilas()][valores.getColumnas()];
		cuadritos = new JLabel[valores.getFilas()][valores.getColumnas()];
		
		Container contenedor = new Container();
		
		int posx=0;
		int posy=0;
		//agregar labels
		for(int i = 0; i<valores.getFilas(); i++) {
			for (int j = 0; j<valores.getColumnas(); j++) {
				cuadritos[i][j] = new JLabel();
				Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY,1);
				cuadritos[i][j].setBorder(border);
				cuadritos[i][j].setBounds(posx,posy,25,25);
				cuadritos[i][j].setVisible(false);
				//no visibles para que se vean solo los botones
				contenedor.add(cuadritos[i][j]);
				posx+=25;
			}
			posy+=25;
			posx=0;
		}
		textoLabels();
		//agrega el texto o imagen de cada label
		
		//agregar botones
		posx=0;
		posy=0;
		for(int i = 0; i<valores.getFilas(); i++) {
			for (int j = 0; j<valores.getColumnas(); j++) {
				botones[i][j] = new JButton();
				Border border1 = BorderFactory.createRaisedBevelBorder();
				botones[i][j].setBorder(border1);
				botones[i][j].setBounds(posx,posy,25,25);
				botones[i][j].addMouseListener(this);
				contenedor.add(botones[i][j]);
				posx+=25;
			}
			posy+=25;
			posx=0;
		}
		
		JPanel arriba = new JPanel();
		
		JLabel label1 = new JLabel("Jugador: ");
		label1.setBounds(10,25*valores.getFilas()+5,80,30);
		frame.add(label1);
		
		JTextField texto = new JTextField();
		texto.setBounds(5,25*valores.getFilas()+35,80,30);
		texto.setText(nombre);
		texto.setEditable(false);
		frame.add(texto);
		
		JButton menu = new JButton("Menu");
		menu.setBounds(100,25*valores.getFilas()+5,80,30);
		menu.addActionListener(this);
		frame.add(menu);
		
		
		frame.setTitle("Buscaminas");
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		frame.add(contenedor,BorderLayout.CENTER);
		frame.getContentPane().setPreferredSize(new Dimension(25*valores.getColumnas(),25*valores.getFilas()+80));
		frame.pack();
		frame.setVisible(true);
	}


	private void textoLabels() {
		for(int i=0;i<valores.getFilas();i++) {
			for(int j=0;j<valores.getColumnas();j++) {
				switch (valores.getNumeros()[i][j]) {
				case 1:
					cuadritos[i][j].setText(" 1");
					cuadritos[i][j].setForeground(Color.BLUE);
					break;
				case 2:
					cuadritos[i][j].setText(" 2");
					cuadritos[i][j].setForeground(new Color(0,153,0));
					break;
				case 3:
					cuadritos[i][j].setText(" 3");
					cuadritos[i][j].setForeground(new Color(102,51,0));
					//no hay cafe
					break;
				case 4:
					cuadritos[i][j].setText(" 4");
					cuadritos[i][j].setForeground(new Color(0,255,51));
					//no hay verde claro
					break;
				case 5:
					cuadritos[i][j].setText(" 5");
					cuadritos[i][j].setForeground(new Color(51,204,255));
					break;
				case 6:
					cuadritos[i][j].setText(" 6");
					cuadritos[i][j].setForeground(Color.PINK);
					break;
				case 7:
					cuadritos[i][j].setText(" 7");
					cuadritos[i][j].setForeground(new Color(238,130,238));
					break;
				case 8:
					cuadritos[i][j].setText(" 8");
					cuadritos[i][j].setForeground(new Color(51,204,255));
					break;
				case 10:
					ImageIcon bomba = new ImageIcon(getClass().getResource("Imagenes/bomba.png"),"imagenBomba");
					Image image = bomba.getImage();
					Image newImg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
					bomba= new ImageIcon(newImg);
					cuadritos[i][j].setIcon(bomba);
					break;
				default:
					cuadritos[i][j].setText("");
					break;
				}
			}
		}
	}


	private void destapar(int x, int y) {
		try{
			if(valores.getNumeros()[x-1][y-1]==0 && botones[x-1][y-1].isEnabled()){
				botones[x-1][y-1].setEnabled(false);
				botones[x-1][y-1].setVisible(false);
				cuadritos[x-1][y-1].setVisible(true);
				destapar(x-1,y-1);
			}
			if(valores.getNumeros()[x-1][y-1]<10 && valores.getNumeros()[x-1][y-1]>0 && botones[x-1][y-1].isEnabled()){
				botones[x-1][y-1].setEnabled(false);
				botones[x-1][y-1].setVisible(false);
				cuadritos[x-1][y-1].setVisible(true);
			}
		}
		catch(ArrayIndexOutOfBoundsException e){
			
		}
		try{
			if(valores.getNumeros()[x-1][y]==0 && botones[x-1][y].isEnabled()){
				botones[x-1][y].setEnabled(false);
				botones[x-1][y].setVisible(false);
				cuadritos[x-1][y].setVisible(true);
				destapar(x-1,y);
			}
			if(valores.getNumeros()[x-1][y]<10 && valores.getNumeros()[x-1][y]>0 && botones[x-1][y].isEnabled()){
				botones[x-1][y].setEnabled(false);
				botones[x-1][y].setVisible(false);
				cuadritos[x-1][y].setVisible(true);
			}
		}
		catch(ArrayIndexOutOfBoundsException e){
			
		}
		try{
			if(valores.getNumeros()[x-1][y+1]==0 && botones[x-1][y+1].isEnabled()){
				botones[x-1][y+1].setEnabled(false);
				botones[x-1][y+1].setVisible(false);
				cuadritos[x-1][y+1].setVisible(true);
				destapar(x-1,y+1);
			}
			if(valores.getNumeros()[x-1][y+1]<10 && valores.getNumeros()[x-1][y+1]>0 && botones[x-1][y+1].isEnabled()){
				botones[x-1][y+1].setEnabled(false);
				botones[x-1][y+1].setVisible(false);
				cuadritos[x-1][y+1].setVisible(true);
			}
		}
		catch(ArrayIndexOutOfBoundsException e){
			
		}
		try{
			if(valores.getNumeros()[x][y-1]==0 && botones[x][y-1].isEnabled()){
				botones[x][y-1].setEnabled(false);
				botones[x][y-1].setVisible(false);
				cuadritos[x][y-1].setVisible(true);
				destapar(x,y-1);
			}
			if(valores.getNumeros()[x][y-1]<10 && valores.getNumeros()[x][y-1]>0 && botones[x][y-1].isEnabled()){
				botones[x][y-1].setEnabled(false);
				botones[x][y-1].setVisible(false);
				cuadritos[x][y-1].setVisible(true);
			}
		}
		catch(ArrayIndexOutOfBoundsException e){
			
		}
		try{
			if(valores.getNumeros()[x][y+1]==0 && botones[x][y+1].isEnabled()){
				botones[x][y+1].setEnabled(false);
				botones[x][y+1].setVisible(false);
				cuadritos[x][y+1].setVisible(true);
				destapar(x,y+1);
			}
			if(valores.getNumeros()[x][y+1]<10 && valores.getNumeros()[x][y+1]>0 && botones[x][y+1].isEnabled()){
				botones[x][y+1].setEnabled(false);
				botones[x][y+1].setVisible(false);
				cuadritos[x][y+1].setVisible(true);
			}
		}
		catch(ArrayIndexOutOfBoundsException e){
			
		}
		try{
			if(valores.getNumeros()[x+1][y-1]==0 && botones[x+1][y-1].isEnabled()){
				botones[x+1][y-1].setEnabled(false);
				botones[x+1][y-1].setVisible(false);
				cuadritos[x+1][y-1].setVisible(true);
				destapar(x+1,y-1);
			}
			if(valores.getNumeros()[x+1][y-1]<10 && valores.getNumeros()[x+1][y-1]>0 && botones[x+1][y-1].isEnabled()){
				botones[x+1][y-1].setEnabled(false);
				botones[x+1][y-1].setVisible(false);
				cuadritos[x+1][y-1].setVisible(true);
			}
		}
		catch(ArrayIndexOutOfBoundsException e){
			
		}
		try{
			if(valores.getNumeros()[x+1][y]==0 && botones[x+1][y].isEnabled()){
				botones[x+1][y].setEnabled(false);
				botones[x+1][y].setVisible(false);
				cuadritos[x+1][y].setVisible(true);
				destapar(x+1,y);
			}
			if(valores.getNumeros()[x+1][y]<10 && valores.getNumeros()[x+1][y]>0 && botones[x+1][y].isEnabled()){
				botones[x+1][y].setEnabled(false);
				botones[x+1][y].setVisible(false);
				cuadritos[x+1][y].setVisible(true);
			}
		}
		catch(ArrayIndexOutOfBoundsException e){
			
		}
		try{
			if(valores.getNumeros()[x+1][y+1]==0 && botones[x+1][y+1].isEnabled()){
				botones[x+1][y+1].setEnabled(false);
				botones[x+1][y+1].setVisible(false);
				cuadritos[x+1][y+1].setVisible(true);
				destapar(x+1,y+1);
			}
			if(valores.getNumeros()[x+1][y+1]<10 && valores.getNumeros()[x+1][y+1]>0 && botones[x+1][y+1].isEnabled()){
				botones[x+1][y+1].setEnabled(false);
				botones[x+1][y+1].setVisible(false);
				cuadritos[x+1][y+1].setVisible(true);
			}
		}
		catch(ArrayIndexOutOfBoundsException e){
			
		}
	}

	
	private void checarVictoria() {
		int check = 0;
		for (int i = 0; i < valores.getFilas(); i++){
			for (int j = 0; j < valores.getColumnas(); j++){
				if (valores.getNumeros()[i][j] != 10){
					if (botones[i][j].isEnabled()==false){
						check = check + 1;
					}
				}
			}	
		}
		if (check == valores.getFilas()*valores.getColumnas() - valores.getMinas() && perdiste==0){
			MenuWin youWin = new MenuWin();
		}
	}

	
	private void gameOver() {
		perdiste=1;
		for(int i=0;i<valores.getFilas();i++) {
			for(int j=0;j<valores.getColumnas();j++) {
				if(botones[i][j].isEnabled()) {
					botones[i][j].setEnabled(false);
					botones[i][j].setVisible(false);
					cuadritos[i][j].setVisible(true);
				}
				else if(valores.getNumeros()[i][j]!=10 && botones[i][j].getName()=="bandera") {
					
					//Implementar que ponga imagen 
					
					ImageIcon bombaError = new ImageIcon(getClass().getResource("Imagenes/bombaError.png"),"imagenBombaError");
					Image image = bombaError.getImage();
					Image newImg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
					bombaError= new ImageIcon(newImg);
					cuadritos[i][j].setIcon(bombaError);
					cuadritos[i][j].setVisible(true);
					botones[i][j].setVisible(false);
				}
			}
		}
		MenuLost youLost = new MenuLost();
	}

	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (SwingUtilities.isLeftMouseButton(e))
		{
			//Click izquierdo
			for(int i=0;i<valores.getFilas();i++) {
				for(int j=0;j<valores.getColumnas();j++) {
					if (e.getSource().equals(botones[i][j])) {
						if(valores.getNumeros()[i][j]==0 && botones[i][j].isEnabled()) {
							botones[i][j].setEnabled(false);
							botones[i][j].setVisible(false);
							cuadritos[i][j].setVisible(true);
							destapar(i,j);
						}
						else if (valores.getNumeros()[i][j]==10) {
							if(botones[i][j].isEnabled()) {
								botones[i][j].setEnabled(false);
								botones[i][j].setVisible(false);
								ImageIcon bombaFinal = new ImageIcon(getClass().getResource("Imagenes/bombaFinal.jpg"),"imagenBomba");
								Image image = bombaFinal.getImage();
								Image newImg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
								bombaFinal= new ImageIcon(newImg);
								cuadritos[i][j].setIcon(bombaFinal);
								cuadritos[i][j].setVisible(true);
								gameOver();
							}
						}
						else {
							if(botones[i][j].isEnabled()) {
								botones[i][j].setEnabled(false);
								botones[i][j].setVisible(false);
								cuadritos[i][j].setVisible(true);
							}
						}
					}
				}
			}
		}
		else if (SwingUtilities.isRightMouseButton(e)) 
		{
			//Click derecho
			for(int i=0;i<valores.getFilas();i++) {
				for(int j=0;j<valores.getColumnas();j++) {
					if (e.getSource().equals(botones[i][j])) {
						if (botones[i][j].isEnabled()) {
							ImageIcon bandera = new ImageIcon(getClass().getResource("Imagenes/bandera.png"),"imagenBandera");
							Image image = bandera.getImage();
							Image newImg = image.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
							bandera= new ImageIcon(newImg);
							botones[i][j].setIcon(bandera);
							botones[i][j].setDisabledIcon(bandera);
							botones[i][j].setEnabled(false);
							botones[i][j].setName("bandera");
						}
						else {
							botones[i][j].setIcon(null);
							botones[i][j].setEnabled(true);
							botones[i][j].setName("");
						}
					}
				}
			}
			
		}
		checarVictoria();
		
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		MenuBasico interrupcion = new MenuBasico();
	}
}


