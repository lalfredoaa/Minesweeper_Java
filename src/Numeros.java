import java.util.Random;

public class Numeros {

	private int[][] numeros;
	private int minas;
	private int filas;
	private int columnas;
	private final int MINA = 10;
	
	public int[][] getNumeros(){
		return this.numeros;
	}
	
	public int getFilas() {
		return this.filas;
	}
	
	public int getColumnas() {
		return this.columnas;
	}
	
	public int getMinas() {
		return this.minas;
	}
	
	public Numeros (int filas, int columnas, int minas) {
		
		this.filas=filas;
		this.columnas=columnas;
		numeros = new int[filas][columnas];
		this.minas=minas;		
		asignarMinas();
		asignarNumeros();
	}
	
	private void asignarMinas() {
		
		int acomodados=0;
		
		while(acomodados<minas) {
			int ri = new Random().nextInt(filas);
			int rj = new Random().nextInt(columnas);
			if(numeros[ri][rj]==MINA) {
				acomodados+=0;
			}
			else {
				numeros[ri][rj]=MINA;
				acomodados+=1;
			}
		}
	}
	
	private void asignarNumeros() {
		
		for (int i = 0; i < filas; i++){
			for (int j = 0; j < columnas; j++){
				int hayMina = 0;
				if (numeros[i][j] != MINA){
					if (i > 0 && j > 0 && numeros[i-1][j-1] == MINA){
						hayMina++;
					}
					if (j > 0 && numeros[i][j-1] == MINA){
						hayMina++;
					}
					if (i < filas-1 && j > 0 && numeros[i+1][j-1] == MINA){
						hayMina++;
					}
					if (i > 0 && numeros[i-1][j] == MINA){
						hayMina++;
					}
					if (i < filas-1 && numeros[i+1][j] == MINA){
						hayMina++;
					}
					if (i > 0 && j < columnas-1 && numeros[i-1][j+1] == MINA){
						hayMina++;
					}
					if (j < columnas-1 &&  numeros[i][j+1] == MINA){
						hayMina++;
					}
					if (i < filas-1 && j < columnas-1 && numeros[i+1][j+1] == MINA){
						hayMina++;
					}
					numeros[i][j] = hayMina;
				}
			}
		}
	}
	
}
