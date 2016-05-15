/**
 * PRACTICA 13: The fantastic combinations of John Conway’s new solitaire game “life”
 * 
 * E.S.I.T.– INFORMÁ́TICA
 * Departamento de Ingeniería Informática y de Sistemas
 * Programación de Aplicaciones Interactivas
 * 
 * En esta práctica se propone desarrollar una aplicación
 * Java que implemente el juego de la vida.
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
 * Clase applet que generará la interfaz para nuestro 
 * programa.
 */
package es.ull.esit.pai.life;

import javax.swing.JApplet;
import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JApplet {
	
	private static final String TITLEGAME = "Game Life";								// Titulo del juego
	private final static int SIZE = 800;																// Tamaño del jframe.
	private static final Color BACKGROUND = Color.GRAY;									// Color de fondo.
																																			// Tamaños en PIXELES.
	private GameOfLife game;																							// Panel principal del JFrame.
	private static Information info;																		// Panel de información.
	private static ButtonsMenu buttonsMenu;															// Menú con los botones.
	private static boolean isStandalone = false;												// Determina si es una aplicación o no.

	/**
	 * Constructor
	 */
	public Main () {
    game = new GameOfLife (BACKGROUND);
    info = new Information (BACKGROUND);
    buttonsMenu = new ButtonsMenu (BACKGROUND, game);
	}
	
  /**
   * Initialize the applet
   */
	@Override
  public void init () {
    
    getContentPane().setLayout(new BorderLayout());
    add(getButtonsMenu(), BorderLayout.NORTH);
    add(getGame(), BorderLayout.CENTER);
    
    getButtonsMenu().getButtonIcon().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				getInfo().setVisible(true);
			}
    });
  }

	/**
   * Main method.
   */
  public static void main(String[] args) {
    JFrame frame = (new JFrame(TITLEGAME));    
    Main applet = new Main ();

    setStandalone(true);

    frame.add(applet);

    applet.init();
    applet.start();

    frame.setSize(SIZE, SIZE); 
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  /**
   * Getter standalone
   * @return isStandalone
   */
  public boolean isStandalone() {
		return isStandalone;
	}

  /**
   * Setter standalone.
   * @param newIsStandalone
   */
	public static void setStandalone(boolean newIsStandalone) {
		isStandalone = newIsStandalone;
	}
	
	/**
	 * Getter panel del juego.
	 * @return game.
	 */
	public GameOfLife getGame() {
		return game;
	}

	/**
	 * Setter panel del juego.
	 * @param newGame
	 */
	public void setGame(GameOfLife newGame) {
		game = newGame;
	}
	
	/**
	 * Getter frame de información.
	 * @return info
	 */
	public static Information getInfo() {
		return info;
	}

	/**
	 * Setter frame de información.
	 * @param newInfo
	 */
	public void setInfo(Information newInfo) {
		info = newInfo;
	}
	
	/**
	 * Getter menú de botones
	 * @return buttonsMenu
	 */
	public static ButtonsMenu getButtonsMenu() {
		return buttonsMenu;
	}

	/**
	 * Setter menú de botones.
	 * @param newButtonsMenu
	 */
	public void setButtonsMenu(ButtonsMenu newButtonsMenu) {
		buttonsMenu = newButtonsMenu;
	}
}
