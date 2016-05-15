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
 * Clase tablero donde se desarrolla el juego.
 */
package es.ull.esit.pai.life;

import java.awt.Color;
import java.awt.Graphics2D;

public class Table {
	
	private static final int VALUEZERO = 0;													// Valor cero por defecto.
	private static final int VALUEONE = 1;													// Valor uno.
	
	private int row;																								// Número de filas del tablero.
	private int column;																							// Número de columnas del tablero.
	private Cell [] table;																					// Tablero con las celdas.
	
	/**
	 * Constructor
	 */
	public Table (){
		row = VALUEZERO;
		column = VALUEZERO;
		table = null;
	}
	
	/**
	 * Función que redimensiona el tablero.
	 * @param width
	 * @param height
	 */
	public void resize (double width, double height){
		setRow((int) width / Cell.SIZESQUARE);
		setColumn((int) height / Cell.SIZESQUARE);
		
		setTable(new Cell [getRow() * getColumn()]);
		
		for(int column = VALUEONE; column <= getColumn(); column++) {
			 for(int row = VALUEONE; row <= getRow(); row++) {
				getTable()[getPos(row, column)] = new Cell ();
			}
		}		
	}
	
	/**
	 * Función que redimensiona el tamaño del tablero.
	 */
	public void resizeTable() {
		Cell[] newTable = new Cell [getRow() * getColumn()];
		
		for(int column = VALUEONE; column <= getColumn(); column++) {
			for(int row = VALUEONE; row <= getRow(); row++) {
				if(getPos(row, column) < getTable().length) {
					Cell cell = new Cell ();
					cell.setAlive(cellIsAlive(row, column));
					newTable[getPos(row, column)] = cell;
							
				} else {
					newTable[getPos(row, column)] = new Cell ();
				}	
			}
		}
		
		setTable(newTable);
	}

	/**
	 * Función que optiene la posición en la matriz.
	 * @param row
	 * @param column
	 * @return pos
	 */
	public int getPos(int row, int column) {
		if ((row < VALUEONE) || (row > getRow()) ||
				(column < VALUEONE) || (column > getColumn())) {
			
			System.out.println("Error accediendo a matriz" + row + " " + column);
			return VALUEZERO;
			
		}else{
			return (row - VALUEONE) * getColumn() + column - VALUEONE;
		}
	}
	
	/**
	 * Función que devuelve si la celda seleccionada esta viva.
	 * @param row
	 * @param column
	 * @return isAlive
	 */
	public boolean cellIsAlive(int row, int column) {
		return getTable()[getPos(row, column)].isAlive();
	}
	
	/**
	 * Función que pinta el tablero.
	 * @param graphics
	 */
	public void paintTable(Graphics2D graphics) {				
		for(int column = VALUEONE; column <= getColumn(); column++){
			for(int row = VALUEONE; row <= getRow(); row++){
												
				if (cellIsAlive(row, column)) {
					graphics.setColor(Cell.COLORLIFE);
					graphics.fillRect((row - VALUEONE) * Cell.SIZESQUARE, (column - VALUEONE) * Cell.SIZESQUARE,
														Cell.SIZESQUARE, Cell.SIZESQUARE);
					graphics.setColor(Color.BLACK);
					graphics.drawRect((row - VALUEONE) * Cell.SIZESQUARE, (column - VALUEONE) * Cell.SIZESQUARE,
														Cell.SIZESQUARE, Cell.SIZESQUARE);
					
				} else {
					graphics.setColor(Cell.COLORDEAD);
					graphics.fillRect((row - VALUEONE) * Cell.SIZESQUARE, (column - VALUEONE) * Cell.SIZESQUARE,
														Cell.SIZESQUARE, Cell.SIZESQUARE);
					graphics.setColor(Color.BLACK);
					graphics.drawRect((row - VALUEONE) * Cell.SIZESQUARE, (column - VALUEONE) * Cell.SIZESQUARE,
														Cell.SIZESQUARE, Cell.SIZESQUARE);
				}
				// graphics.drawString(row + " " + column, (row - VALUEONE) * Cell.SIZESQUARE, column * Cell.SIZESQUARE);

			}
		}	
	}
	
	/**
	 * Función que nos calcula el número de vecinos de una celda
	 * @return
	 */
	public int getNumNeighbours(int row, int column) {
		int numNeighbours = VALUEZERO;
		
		if(cellIsAlive(row, column)) {
			numNeighbours--;
		}
								
		for(int posColumn = column - VALUEONE; posColumn <= column + VALUEONE; posColumn++) {
			for(int posRow = row - VALUEONE; posRow <= row + VALUEONE; posRow++) {
				
				if(posRow != VALUEZERO && posColumn != VALUEZERO &&
					 posRow <= getRow() && posColumn <= getColumn()){
										
					if(cellIsAlive(posRow, posColumn)) {
						numNeighbours ++;
					}
				}
			}
		}
				
		return numNeighbours;
	}
	
	/**
	 * Función que obtiene una celda según la posición.
	 * @param row
	 * @param column
	 * @return Cell
	 */
	public Cell getCellByPos(int row, int column) {
		return getTable()[getPos(row, column)];
	}
	
	/**
	 * Getter número de filas.
	 * @return row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Setter número de filas.
	 * @param newRow
	 */
	public void setRow(int newRow) {
		row = newRow;
	}

	/**
	 * Getter número de columnas.
	 * @return column
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * Setter número de columnas.
	 * @param newColumn
	 */
	public void setColumn(int newColumn) {
		column = newColumn;
	}

	/**
	 * Getter tablero.
	 * @return table
	 */
	public Cell[] getTable() {
		return table;
	}

	/**
	 * Setter tablero.
	 * @param newTable
	 */
	public void setTable(Cell[] newTable) {
		table = newTable;
	}
}
