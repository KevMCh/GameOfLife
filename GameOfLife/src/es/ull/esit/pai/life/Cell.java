/**
 * PRACTICA 13: The fantastic combinations of John Conway’s new solitaire game “life”
 * 
 * E.S.I.T.– INFORMÁ́TICA
 * Departamento de Ingeniería Informática y de Sistemas
 * Programación de Aplicaciones Interactivas
 * 
 * En esta práctica se propone desarrollar una aplicación
 * Java que implemente eljuego de la vida.
 * El juego de la vida es el mejor ejemplo de un autómata celular,
 * un modelo matemático para un sistema dinámico que evoluciona en
 * pasos discretos. Desde un punto de vista teórico, el interés del
 * juego de la vida procede de que es equivalente a una máquina 
 * universal de Turing, es decir, todo lo que se puede computar
 * algorítmicamente se puede computar en el juego de la vida.
 * 
 * @author	Kevin Martín Chinea
 * @version	0.0.0
 * @since 2016-05-11
 * @email: alu0100774665@ull.edu.es
 *
 * Clase celda representa las celdas de nuestro tablero.
 */
package es.ull.esit.pai.life;

import java.awt.Color;
import java.awt.Graphics2D;

public class Cell {
	
	public static final int SIZESQUARE = 10;								// Tamaño del cuadrado de la celda.			
	public static final Color COLORLIFE = Color.RED;				// Color de las células vivas.
	public static final Color COLORDEAD = Color.WHITE;			// Color de las células muertas.
	
	private boolean isAlive;																// Booleano que representa si la celda esta viva o no.
	
	/**
	 * Constructor por defecto.
	 */
	public Cell (){
		isAlive = false;
	}
	
	/**
	 * Getter isAlive.
	 * @return isAlive
	 */
	public boolean isAlive() {
		return isAlive;
	}
	
	/**
	 * Setter is alive.
	 * @param newIsAlive
	 */
	public void setAlive(boolean newIsAlive) {
		isAlive = newIsAlive;
	}

	/**
	 * Función que crea una nueva célula y la pinta. 
	 * @param graphics2d 
	 * @param column 
	 * @param row 
	 */
	public void bornCell(Graphics2D graphics2d, int row, int column) {
		setAlive(true);
		graphics2d.setColor(COLORLIFE);
		graphics2d.fillRect(row * SIZESQUARE, column * SIZESQUARE, SIZESQUARE, SIZESQUARE);
		graphics2d.setColor(Color.BLACK);
		graphics2d.drawRect(row * SIZESQUARE, column * SIZESQUARE, SIZESQUARE, SIZESQUARE);
	}	
	
	/**
	 * Función que mata una célula y la borra. 
	 * @param graphics2d 
	 * @param column 
	 * @param row 
	 */
	public void deadCell(Graphics2D graphics2d, int row, int column) {
		setAlive(false);
		graphics2d.setColor(Cell.COLORDEAD);
		graphics2d.fillRect(row * SIZESQUARE, column * SIZESQUARE, SIZESQUARE, SIZESQUARE);
		graphics2d.setColor(Color.BLACK);
		graphics2d.drawRect(row * SIZESQUARE, column * SIZESQUARE, SIZESQUARE, SIZESQUARE);		
	}	
}
