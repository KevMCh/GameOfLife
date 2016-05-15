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
 * Clase de panel donde se realizará el juego.
 */
package es.ull.esit.pai.life;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GameOfLife extends JPanel {
	
  private static final int NUMONE = 1;																	// Constante número uno.
	private static final int DELAYDEFAULT = 0;														// Velocidad por defecto.
	private static final int DEFAULTNEIGHBOURS = 3;												// Número de vecinos para cumplir las reglas.
	
	private int delay;																										// Tiempo de espera.
  private Timer timer;																									// Timer de la ejecución.
  private Table table;																									// Tablero 
  private boolean isInitialised;																				// Booleano que nos dice si el tablero esta inicializado.
  private boolean isPaused;																							// Booleano que nos dice si esta pausado o no.
  private boolean isStepToStep;																					// Booleano que nos dice si es paso a paso.

	/**
   * Constructor.
   */
  public GameOfLife (Color background) {
  	delay = DELAYDEFAULT;
  	timer = new Timer(delay, new TimerListener());
  	table = new Table ();
  	isInitialised = false;
  	isPaused = false;
  	isStepToStep = false;
  	
  	setBackground(background);
  	CustomMouseListener listener = new CustomMouseListener();
  	addMouseListener(listener);
    addMouseMotionListener(listener);
  }
  
	/**
   * Función para pintar el panel.
   * @param Graphics gráficos.
   */
  public void paint(Graphics graphics) {
  	super.paintComponent(graphics);

  	if(!isInitialised()){
  		getTable().resize(getSize().getWidth(), getSize().getHeight());
  		setInitialised(true);
  	}
  	
  	if((getTable().getRow() != ((int) getSize().getWidth() / Cell.SIZESQUARE)) ||
  		 (getTable().getColumn() != ((int) getSize().getHeight() / Cell.SIZESQUARE))){
  		
	  	getTable().setRow((int) getSize().getWidth() / Cell.SIZESQUARE);
	  	getTable().setColumn((int) getSize().getHeight() / Cell.SIZESQUARE);
	  	
	  	getTable().resizeTable();
  	}
  	
  	getTable().paintTable((Graphics2D) graphics);
  }
  
  /**
   * Clase encargada de la escucha del ratón
   * @author Kevin Martín Chinea
   * @version	0.0.0
   * @since 2016-05-11
   * @email: alu0100774665@ull.edu.es
   */
  private class CustomMouseListener implements MouseListener, MouseMotionListener {

		public void mouseClicked(MouseEvent e) {
    	if(!getTimer().isRunning()){
    	    		
    		int row = ((int) e.getX() / Cell.SIZESQUARE + NUMONE);
    		int column = ((int) e.getY() / Cell.SIZESQUARE + NUMONE);

      	if(!getTable().cellIsAlive(row, column)){
      		getTable().getCellByPos(row, column).bornCell((Graphics2D) getGraphics(),
      																									row - NUMONE, column - NUMONE);
      	} else {
      		getTable().getCellByPos(row, column).deadCell((Graphics2D) getGraphics(),
      																									row - NUMONE, column - NUMONE);
      	}
    	}
    }

    public void mousePressed(MouseEvent e) {
      // System.out.println("Mouse Pressed: (" + e.getX() + ", " + e.getY() + ")");
    }

    public void mouseReleased(MouseEvent e) {
      // System.out.println("Mouse Pressed: (" + e.getX() + ", " + e.getY() + ")");
    }

    public void mouseEntered(MouseEvent e) {
      // System.out.println("Mouse Pressed: (" + e.getX() + ", " + e.getY() + ")");
    }

    public void mouseExited(MouseEvent e) {
      // System.out.println("Mouse Exited: (" + e.getX() + ", " + e.getY() + ")");
    }

		@Override
		public void mouseDragged(MouseEvent e) {
      // System.out.println("Mouse Dragged: ("+e.getX()+", "+e.getY() +")");
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// System.out.println("Mouse Dragged: ("+e.getX()+", "+e.getY() +")");
		}
	}
  
  /**
   * Clase encargada del timer.
   * @author Kevin Martín Chinea
   * @version	0.0.0
   * @since 2016-05-11
   * @email: alu0100774665@ull.edu.es
   */
  private class TimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Table newTable = new Table ();
			newTable.resize(getSize().getWidth(), getSize().getHeight());
			
			newGeneration(newTable);
			setTable(newTable);

			repaint();

			if (isStepToStep()) {
				getTimer().stop();
			}
		}
  }
  
  /**
   * Función que realiza la ejecución del juego de la vida.
   */
	private void newGeneration(Table newTable) {
		for(int column = NUMONE; column <= getTable().getRow(); column++) {
			for(int row = NUMONE; row <= getTable().getColumn(); row++) {
				checkRules(newTable, column, row);
			}
		}
	}
  
	/**
	 * Función que ejecuta cada una de las reglas.
	 * @param row
	 * @param column
	 */
	private void checkRules(Table newTable, int row, int column) {
		int numNeighbours = getTable().getNumNeighbours(row, column);
						
		if(getTable().getCellByPos(row, column).isAlive()) {
			deadUnderPopulation(newTable, numNeighbours, row, column);
			liveNewGeneration(newTable, numNeighbours, row, column);
			deadOverPopulation(newTable, numNeighbours, row, column);
			
		} else {
			bornCell(newTable, numNeighbours, row, column);
		}
	}

	/**
	 * Función en donde cualquier célula viva con dos o tres vecinos vivos
	 * vive a la siguiente generación.
	 * @param numNeighbours
	 * @param row
	 * @param column 
	 */
	private void liveNewGeneration(Table newTable, int numNeighbours, int row, int column) {
		if(numNeighbours == DEFAULTNEIGHBOURS || numNeighbours == (DEFAULTNEIGHBOURS - NUMONE)) {
			// System.out.println("La célula de la celda [" + column + ", " + row + "] sigue viva (Vecinos: " + numNeighbours + ").");
			newTable.getCellByPos(row, column).bornCell((Graphics2D) getGraphics(), row, column);
		}
	}

	/**
	 * Función en donde cualquier célula viva con menos de dos vecinos vivos muere,
	 * como un causada por la subpoblación.
	 * @param numNeighbours
	 * @param row
	 * @param column 
	 */
	private void deadUnderPopulation(Table newTable, int numNeighbours, int row, int column) {
		if(numNeighbours < (DEFAULTNEIGHBOURS - NUMONE)) {
			// System.out.println("La célula de la celda [" + column + ", " + row + "] muere por baja población (Vecinos: " + numNeighbours + ").");
			newTable.getCellByPos(row, column).deadCell((Graphics2D) getGraphics(), row, column);
		}
	}
	
	/**
	 * Función en donde cualquier célula viva con más de tres vecinos vivos muere,
	 * como si por un exceso de población.
	 * @param numNeighbours
	 * @param row
	 * @param column 
	 */
	private void deadOverPopulation(Table newTable, int numNeighbours, int row, int column) {
		if(numNeighbours > DEFAULTNEIGHBOURS) {
			// System.out.println("La célula de la celda [" + column + ", " + row + "] muere por mucha población (Vecinos: " + numNeighbours + ").");
			newTable.getCellByPos(row, column).deadCell((Graphics2D) getGraphics(), row, column);
		}
	}
	
	/**
	 * Función en donde cualquier célula muerta con exactamente tres vecinos vivos
	 * se convierte en una célula viva, como si por reproducción.
	 * @param numNeighbours
	 * @param row
	 * @param column 
	 */
	private void bornCell(Table newTable, int numNeighbours, int row, int column) {
		if(numNeighbours == DEFAULTNEIGHBOURS) {
			// System.out.println("En la celda [" + column + ", " + row + "] nace una nueva (Vecinos: " + numNeighbours + ").");
			newTable.getCellByPos(row, column).bornCell((Graphics2D) getGraphics(), row, column);
		}
	}
	

	/**
	 * Función que cambia la velocidad de ejecuión.
	 */
	public void changeDelay(int newDelay) {
		setDelay(newDelay);
		getTimer().setDelay(newDelay);
	}
	
	/**
	 * Función para parar la ejecución.
	 */
	public void suspend () {
		getTimer().stop();
	}
	
	/**
	 * Función para reanudar la ejecución.
	 */
	public void start () {
		setStepToStep(false);
		getTimer().start();
	}
	
	/**
	 * Función que pausa la ejecución.
	 */
	public void paused() {
		if(isPaused()){
			getTimer().start();
			setPaused(false);	  	
  	} else {
  		getTimer().stop();
			setPaused(true);
  	}
	}
	
	/**
	 * Función que calcula un nuevo paso.
	 */
	public void newStep() {
		setStepToStep(true);
		getTimer().start();
	}
	
	/**
	 * Getter delay.
	 * @return delay
	 */
	public int getDelay() {
		return delay;
	}

	/**
	 * Setter delay
	 * @param newDelay
	 */
	public void setDelay(int newDelay) {
		delay = newDelay;
	}

	/**
	 * Getter timer.
	 * @return timer
	 */
	public Timer getTimer() {
		return timer;
	}

	/**
	 * Setter timer.
	 * @param newTimer
	 */
	public void setTimer(Timer newTimer) {
		timer = newTimer;
	}
	
	/**
	 * Getter tablero.
	 * @return table
	 */
	public Table getTable() {
		return table;
	}

	/**
	 * Setter tablero.
	 * @param newTable
	 */
	public void setTable(Table newTable) {
		table = newTable;
	}
	
	/**
	 * Getter está inicializado
	 * @return isInitialised
	 */
	public boolean isInitialised() {
		return isInitialised;
	}

	/**
	 * Setter está inicializado.
	 * @param newIsInitialised
	 */
	public void setInitialised(boolean newIsInitialised) {
		isInitialised = newIsInitialised;
	}
	
	/**
	 * Getter está pausado.
	 * @return isPaused
	 */
	public boolean isPaused() {
		return isPaused;
	}

	/**
	 * Setter está pausado.
	 * @param newIsPaused
	 */
	public void setPaused(boolean newIsPaused) {
		isPaused = newIsPaused;
	}
	
	/**
	 * Getter booleano paso a paso.
	 * @return isStepToStep
	 */
	public boolean isStepToStep() {
		return isStepToStep;
	}

	/**
	 * Setter booleano paso a paso.
	 * @param newIsStepToStep
	 */
	public void setStepToStep(boolean newIsStepToStep) {
		isStepToStep = newIsStepToStep;
	}
}
