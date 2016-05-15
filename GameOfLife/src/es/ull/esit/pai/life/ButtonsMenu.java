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
 * Clase que forma un panel con los botones para interactuar
 * con ellos.
 */
package es.ull.esit.pai.life;

import java.awt.Color;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ButtonsMenu extends JPanel {
	
	private final static String INIT = "Inicio";												// Valores inicializados.
	private final static String END = "Fin";														// En ejecución.
	private final static String PAUSE = "Pausa";												// Programa pausado.
	private final static String STEPTOSTEP = "Paso";										// Ejecución paso a paso.
	private static final String PATHICONINFO = "images/info.gif";				// Dirección icono información.
	private static final String ICONINFO = "Icono con información";			// Nombre icono de la información.
	private final static int SCROLLVISIBILITY = 5;											// Porción visible del área desplazable.
	private final static int SCROLLVALUEDEFAUL = 50;										// Valor por defecto de la barra.
	private final static int SCROLLSTART = 0;														// Valor inicial de la barra.
	private final static int SCROLLEND = 1000;													// Valor final de la barra.
	private static final String SCROLLBARVEL = "VelScrollbar";					// Nombre scrollbar.
	
	private JButton buttonInit; 																				// Botón para inicializar la ejecución.
  private JButton buttonEnd; 																					// Botón para ejecutar el algoritmo.
  private JButton buttonPause;																				// Botón para pausar el algoritmo.
  private JButton buttonStep;																					// Botón para ir paso a paso.
  private JButton buttonIcon;																					// Botón con la información.
	private Scrollbar scrollbarSpeed;																		// Barra de velocidad.
	private GameOfLife gameLife; 																					// Juego a interactuar.

	/**
   * Constructor que implementa el panel
   * de los botones.
   */
	public ButtonsMenu (Color background, GameOfLife gameL) {
		setBackground(background);
		gameLife = gameL;
  	buttonInit = new JButton(INIT);
    buttonInit.setName(INIT);
    buttonEnd = new JButton(END);
    buttonEnd.setName(END);
  	buttonPause = new JButton(PAUSE);
    buttonPause.setName(PAUSE);
    buttonStep = new JButton(STEPTOSTEP);
    buttonStep.setName(STEPTOSTEP);
    URL url = this.getClass().getResource(PATHICONINFO);
    buttonIcon = new JButton(new ImageIcon(url));
    buttonIcon.setName(ICONINFO);
    scrollbarSpeed = new Scrollbar(Scrollbar.HORIZONTAL, SCROLLVALUEDEFAUL,
    															 SCROLLVISIBILITY, SCROLLSTART, SCROLLEND);
    scrollbarSpeed.setName(SCROLLBARVEL);
    
    setButtons(background);
    addActionListenerToButtons();
  }
	
	/**
	 * Función que crea los paneles de botones y los añade al
	 * panel principal.
	 */
	private void setButtons(Color background) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panelButtons = new JPanel (new FlowLayout());
		panelButtons.setBackground(background);
		panelButtons.add(getButtonInit());
		panelButtons.add(getButtonEnd());
		panelButtons.add(getButtonPause());
		panelButtons.add(getButtonStep());
		panelButtons.add(getButtonIcon());
		
		JPanel panelScroll = new JPanel (new FlowLayout());
		panelScroll.setBackground(background);
		panelScroll.add(new JLabel("Vel: "));
		panelScroll.add(getScrollbarSpeed());
		
		add(panelButtons);
		add(panelScroll);
	}
	
	/**
	 * Función que añade cada una de las funcionalidades
	 * a los botones correspondientes.
	 */
	private void addActionListenerToButtons() {
    getButtonInit().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getGameOfLife().changeDelay(getScrollbarSpeed().getValue());
				getGameOfLife().start();
      }
    });
    
    getButtonEnd().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getGameOfLife().suspend();
      }
    });
    
    getButtonPause().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getGameOfLife().paused();
      }
    });
    
    getButtonStep().addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				getGameOfLife().newStep();
      }
    });
	}
	
	/**
	 * Función que captura el evento de barra de velocidad.
	 */
	@SuppressWarnings("deprecation")
	public boolean handleEvent(Event evt) {
		getGameOfLife().changeDelay(getScrollbarSpeed().getValue());
    return super.handleEvent(evt);
}
	
	/**
	 * Getter botón Init.
	 * @return buttonInit.
	 */
	public JButton getButtonInit() {
		return buttonInit;
	}

	/**
	 * Setter botón Init.
	 * @param newButtonInit.
	 */
	public void setButtonInit(JButton newButtonInit) {
		buttonInit = newButtonInit;
	}

	/**
	 * Getter botón End.
	 * @return buttonEnd.
	 */
	public JButton getButtonEnd() {
		return buttonEnd;
	}

	/**
	 * Setter botón Run.
	 * @param newButtonEnd.
	 */
	public void setButtonEnd(JButton newButtonEnd) {
		buttonEnd = newButtonEnd;
	}

	/**
	 * Getter botón Pause.
	 * @return buttonPause.
	 */
	public JButton getButtonPause() {
		return buttonPause;
	}

	/**
	 * Setter botón Pause.
	 * @param newButtonPause.
	 */
	public void setButtonPause(JButton newButtonPause) {
		buttonPause = newButtonPause;
	}

	/**
	 * Getter botón Step.
	 * @return buttonStep.
	 */
	public JButton getButtonStep() {
		return buttonStep;
	}

	/**
	 * Setter botón Step.
	 * @param newButtonStep.
	 */
	public void setButtonStep(JButton newButtonStep) {
		buttonStep = newButtonStep;
	}
	
	/**
	 * Getter scrollbar de la velocidad.
	 * @return
	 */
	public Scrollbar getScrollbarSpeed() {
		return scrollbarSpeed;
	}

	/**
	 * Setter scrollbar de la velocidad.
	 * @param newScrollbarSpeed
	 */
	public void setScrollbarSpeed(Scrollbar newScrollbarSpeed) {
		scrollbarSpeed = newScrollbarSpeed;
	}
	
	/**
	 * Getter botón de icono.
	 * @return buttonIcon
	 */
  public JButton getButtonIcon() {
		return buttonIcon;
	}

  /**
   * Setter botón de icono.
   * @param newButtonIcon
   */
	public void setButtonIcon(JButton newButtonIcon) {
		buttonIcon = newButtonIcon;
	}
	
	/**
	 * Getter juego de la vida.
	 * @return
	 */
	public GameOfLife getGameOfLife() {
		return gameLife;
	}

	/**
	 * Setter juego de la vida.
	 * @param newGameLife
	 */
	public void setGameOfLife(GameOfLife newGameLife) {
		gameLife = newGameLife;
	}
}
