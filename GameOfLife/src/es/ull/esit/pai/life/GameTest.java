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
 * Clase Test para el cubrimiento y el desarrollo de todo
 * nuestro código.
 */
package es.ull.esit.pai.life;

import static org.junit.Assert.*;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Scrollbar;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.assertj.swing.fixture.FrameFixture;
import org.junit.Before;
import org.junit.Test;

public class GameTest {
	
	private static final int SIZE = 600;																// Tamaño del frame.
	private static final String TITLEGAME = "Game life";								// Titulo del juego.
	private static final String ICONINFO = "Icono con información";			// Nombre icono de la información.
	private static final String BUTTONSMENU = "Menu Botones";						// Nombre icono de la botón.
	private static final String AUTHOR = "Autor";												// Constante nombre autor.
	private static final String GENERALDATA = "General data";						// Constante datos generales.
	private static final String DESCRIPTION = "Descripción";						// Constante descripción.
	private static final String INIT = "Inicio";												// Constante nombre botón Init.
	private static final String PAUSE = "Pausa";												// Constante nombre botón Pause.
	private static final String END = "Fin";														// Constante nombre botón Run.
	private static final String STEP = "Paso";													// Constante nombre botón Step.
	private static final String SCROLLBARVEL = "VelScrollbar";					// Nombre scrollbar.
	private static final String EMAIL = "Email";												// Constante email.
	private static final String SCROLLNAME = "Barra scroll";						// Nombre scrollbar.
	private static final int NUMONE = 1;																// Constante número uno.
	private static final Color BACKGROUND = Color.CYAN;									// Color de fondo.
	private static final int POINTTOMOVE = 0;														// Punto cero.
																																			// Tamaño en pixeles.
	private JFrame frame;																								// Frame donde estará nuestra applet.
	private Main game;																									// Programa a testear.
	
	/**
	 * Función que crea el frame añadiendole la applet.
	 * @return frame con la applet.
	 */
	public void createFrame(){
		frame = (new JFrame(TITLEGAME));
    frame.add(getGame());

    frame.setSize(SIZE, SIZE); 
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
	}
	
	/**
	 * Getter panel con la applet.
	 * @return game.
	 */
	public Main getGame() {
		return game;
	}
	
	/**
	 * Getter frame.
	 * @return frame
	 */
	public JFrame getFrame() {
		return frame;
	}
	
	/**
	 * Creamos nuestro programa previamente a los test.
	 */
	@Before
	public void setup() {
		game = new Main ();
    
		Main.setStandalone(true);

    game.init();
    game.start();
	}
	
	/**
	 * Test: comprobación main del applet.
	 */
	@Test
	public final void testMainApplet() {
		Main newGame = new Main ();
    
    String[] args = {};
    Main.main(args);

    assertTrue(newGame.isStandalone());
	}
	
	/**
	 * Test: nuestro programa (Applet) se crea correctamente.
	 */
	@Test
	public final void testMainQuickHull() {
		assertNotNull(getGame());		
	}
	
	/**
	 * Test: correcta creación del panel principal del Applet.
	 */
	@Test
	public final void testMainJPanel() {
		assertNotNull(getGame().getGame());
	}
	
	/**
	 * Test: el setter del panel principal funciona correctamente en el applet.
	 */
	@Test
	public final void testSetterMainPanelApplet() {		
		GameOfLife gamePanel = new GameOfLife (BACKGROUND);
		gamePanel.setName(TITLEGAME);
		getGame().setGame(gamePanel);
	  assertEquals(TITLEGAME, getGame().getGame().getName());
	}
	
	/**
	 * Test: mostrar el panel de información.
	 */
	@Test
	public final void testFrameInfo() {
		createFrame();
		FrameFixture frameFixture = new FrameFixture(getFrame());
				
		frameFixture.button(ICONINFO).click();
		assertTrue(getGame().getInfo().isVisible());

		frameFixture.cleanUp();
	}
	
	/**
	 * Test: setter el panel de información.
	 */
	@Test
	public final void testSetterFrameInfo() {
		Information newInfo = new Information (BACKGROUND);
		newInfo.setName(ICONINFO);
		getGame().setInfo(newInfo);
		assertEquals(ICONINFO, getGame().getInfo().getName());
	}
	
	/**
	 * Test: setter el panel del los botones.
	 */
	@Test
	public final void testSetterButtonsMenu() {
		ButtonsMenu newButtonsMenu = new ButtonsMenu (BACKGROUND, null);
		newButtonsMenu.setName(BUTTONSMENU);
		getGame().setButtonsMenu(newButtonsMenu);
		assertEquals(BUTTONSMENU, getGame().getButtonsMenu().getName());
	}
		
	/**
	 * Test: setter author en el Information frame.
	 */
	@Test
	public final void testSetterAuthorFrameInfo() {
		JLabel newAuthor = new JLabel ();
		newAuthor.setName(AUTHOR);
		getGame().getInfo().setAuthor(newAuthor);
		assertEquals(AUTHOR, getGame().getInfo().getAuthor().getName());
	}
	
	/**
	 * Test: setter datos generales en el Information frame.
	 */
	@Test
	public final void testSetterGeneralDataFrameInfo() {
		JLabel newGeneralData = new JLabel ();
		newGeneralData.setName(GENERALDATA);
		getGame().getInfo().setGeneralData(newGeneralData);
		assertEquals(GENERALDATA, getGame().getInfo().getGeneralData().getName());
	}
	
	/**
	 * Test: setter descripción en el Information frame.
	 */
	@Test
	public final void testSetterDescriptionFrameInfo() {
		JLabel newDescription = new JLabel ();
		newDescription.setName(DESCRIPTION);
		getGame().getInfo().setDescription(newDescription);
		assertEquals(DESCRIPTION, getGame().getInfo().getDescription().getName());
	}	
	
	/**
	 * Test: setter descripción en el Information frame.
	 */
	@Test
	public final void testSetterEmailFrameInfo() {
		JLabel newEmail = new JLabel ();
		newEmail.setName(EMAIL);
		getGame().getInfo().setEmail(newEmail);
		assertEquals(EMAIL, getGame().getInfo().getEmail().getName());
	}
	
	/**
	 * Test: setter delay.
	 */
	@Test
	public final void testSetterDelay() {
		getGame().getGame().setDelay(NUMONE);
		assertEquals(NUMONE, getGame().getGame().getDelay());
	}
	
	/**
	 * Test: setter timer.
	 */
	@Test
	public final void testSetterTimer() {
		getGame().getGame().setTimer(null);
		assertNull(getGame().getGame().getTimer());
	}
	
	/**
	 * Test: el setter del botón Init.
	 */
	@Test
	public final void testSetterButtonStart() {
		JButton newButtonStart = new JButton(INIT);
		newButtonStart.setName(INIT);
		getGame().getButtonsMenu().setButtonInit(newButtonStart);
		assertEquals(INIT, getGame().getButtonsMenu().getButtonInit().getName());
	}
	
	/**
	 * Test: el setter del botón Run.
	 */
	@Test
	public final void testSetterButtonEnd() {
		JButton newButtonRun = new JButton(END);
		newButtonRun.setName(END);
		getGame().getButtonsMenu().setButtonEnd(newButtonRun);
		assertEquals(END, getGame().getButtonsMenu().getButtonEnd().getName());
	}
	
	/**
	 * Test: el setter del botón Pause.
	 */
	@Test
	public final void testSetterButtonPause() {
		JButton newButtonPause = new JButton(PAUSE);
		newButtonPause.setName(PAUSE);
		getGame().getButtonsMenu().setButtonPause(newButtonPause);
		assertEquals(PAUSE, getGame().getButtonsMenu().getButtonPause().getName());
	}
	
	/**
	 * Test: el setter del botón Step.
	 */
	@Test
	public final void testSetterButtonStep() {
		JButton newButtonStep = new JButton(STEP);
		newButtonStep.setName(STEP);
		getGame().getButtonsMenu().setButtonStep(newButtonStep);
		assertEquals(STEP, getGame().getButtonsMenu().getButtonStep().getName());
	}
	
	/**
	 * Test: el setter del botón Step.
	 */
	@Test
	public final void testSetterButtonIcon() {
		JButton newButtonIcon = new JButton(ICONINFO);
		newButtonIcon.setName(ICONINFO);
		getGame().getButtonsMenu().setButtonIcon(newButtonIcon);
		assertEquals(ICONINFO, getGame().getButtonsMenu().getButtonIcon().getName());
	}
	
	/**
	 * Test: el setter del scrollbar.
	 */
	@Test
	public final void testSetterScrollbarSpeed() {
		Scrollbar newScrollbarSpeed = new Scrollbar ();
		newScrollbarSpeed.setName(SCROLLNAME);
		getGame().getButtonsMenu().setScrollbarSpeed(newScrollbarSpeed);
		assertEquals(SCROLLNAME, getGame().getButtonsMenu().getScrollbarSpeed().getName());
	}
	
	/**
	 * Test: setter gamelife en el panel de los botones.
	 */
	@Test
	public final void testSetterGamelife() {
		GameOfLife newGameLife = new GameOfLife (BACKGROUND);
		newGameLife.setName(TITLEGAME);
		getGame().getButtonsMenu().setGameOfLife(newGameLife);
		assertEquals(TITLEGAME, getGame().getButtonsMenu().getGameOfLife().getName());
	}
	
	/**
	 * Test: clickar para crear una célula.
	 */
	@Test
	public final void testCreateCell() {
		createFrame();
		FrameFixture frameFixture = new FrameFixture(getFrame());
				
		Point newPointMovement = new Point ();
		newPointMovement.setLocation(POINTTOMOVE, POINTTOMOVE);
		frameFixture.moveTo(newPointMovement);
		frameFixture.click();
		
		boolean result = false;
		
		for(int column = NUMONE; column <= getGame().getGame().getTable().getColumn(); column++) {
			for(int row = NUMONE; row <= getGame().getGame().getTable().getRow(); row++) {
				if(getGame().getGame().getTable().getCellByPos(row, column).isAlive()){
					result = true;
				}
			}
		}
				
		assertTrue(result);
		frameFixture.cleanUp();
		
	}
	
	/**
	 * Test: maximizar ventana.
	 */
	@Test
	public final void testMaximizeWindows() {
		createFrame();
		FrameFixture frameFixture = new FrameFixture(getFrame());

		int sizeTable = getGame().getGame().getTable().getTable().length;
		
		frameFixture.maximize();
		
		Point newPointMovement = new Point ();
		newPointMovement.setLocation(POINTTOMOVE, POINTTOMOVE);
		frameFixture.moveTo(newPointMovement);
				
		assertNotEquals(sizeTable, getGame().getGame().getTable().getTable().length);
		frameFixture.cleanUp();
	}
	
	/**
	 * Test: clickar para eliminar una célula.
	 */
	@Test
	public final void testSetterTable() {
		getGame().getGame().setTable(null);
		assertNull(getGame().getGame().getTable());
	}
	
	/**
	 * Test: clickar para eliminar una célula.
	 */
	@Test
	public final void testCreateAndDeleteCell() {
		createFrame();
		FrameFixture frameFixture = new FrameFixture(getFrame());
				
		Point newPointMovement = new Point ();
		newPointMovement.setLocation(POINTTOMOVE, POINTTOMOVE);
		frameFixture.moveTo(newPointMovement);
		frameFixture.click();		
		frameFixture.click();

		boolean result = getGame().getGame().getTable().getCellByPos(((int) (POINTTOMOVE + NUMONE) / Cell.SIZESQUARE + NUMONE),
																												((int) (POINTTOMOVE + NUMONE) / Cell.SIZESQUARE + NUMONE)).isAlive();
		assertFalse(result);

		frameFixture.cleanUp();
	}
	
	/**
	 * Test: clickar para iniciar el juego.
	 */
	@Test
	public final void testInitGame() {
		createFrame();
		FrameFixture frameFixture = new FrameFixture(getFrame());

		frameFixture.button(INIT).click();
						
		assertTrue(getGame().getGame().getTimer().isRunning());
		frameFixture.cleanUp();
	}
	
	/**
	 * Test: clickar para finalizar el juego.
	 */
	@Test
	public final void testEndGame() {
		createFrame();
		FrameFixture frameFixture = new FrameFixture(getFrame());

		frameFixture.button(INIT).click();
		frameFixture.button(END).click();
						
		assertFalse(getGame().getGame().getTimer().isRunning());
		frameFixture.cleanUp();
	}
	
	/**
	 * Test: clickar para pausar el juego.
	 */
	@Test
	public final void testPausedGame() {
		createFrame();
		FrameFixture frameFixture = new FrameFixture(getFrame());

		frameFixture.button(INIT).click();
		frameFixture.button(PAUSE).click();
						
		assertTrue(getGame().getGame().isPaused());
		frameFixture.button(PAUSE).click();
		assertFalse(getGame().getGame().isPaused());

		frameFixture.cleanUp();
	}
	
	/**
	 * Test: clickar para realizar el juego paso a paso.
	 */
	@Test
	public final void testStepToStepGame() {
		createFrame();
		FrameFixture frameFixture = new FrameFixture(getFrame());
		frameFixture.button(STEP).click();

		assertFalse(getGame().getGame().getTimer().isRunning());

		frameFixture.cleanUp();
	}
	
	/**
	 * Test: deslizar el scrollbar.
	 */
	/*@Test
	public final void testScrollbarGame() {
		createFrame();
		FrameFixture frameFixture = new FrameFixture(getFrame());
		
		int actualVel = getGame().getGame().getTimer().getDelay();
		
		frameFixture.scrollBar(SCROLLBARVEL).scrollToMaximum();
				
		assertNotEquals(actualVel, getGame().getGame().getTimer().getDelay());

		frameFixture.cleanUp();
	}*/
	
	/**
	 * Test: las células vive a una nueva generación.
	 */
	@Test
	public final void testLiveNewGeneration() {
		createFrame();
		FrameFixture frameFixture = new FrameFixture(getFrame());
		
		getGame().getGame().getTable().getCellByPos(NUMONE, NUMONE).bornCell(
				(Graphics2D) getGame().getGame().getGraphics(), NUMONE, NUMONE);
		getGame().getGame().getTable().getCellByPos(NUMONE, NUMONE + NUMONE).bornCell(
				(Graphics2D) getGame().getGame().getGraphics(), NUMONE, NUMONE + NUMONE);
		getGame().getGame().getTable().getCellByPos(NUMONE + NUMONE, NUMONE).bornCell(
				(Graphics2D) getGame().getGame().getGraphics(), NUMONE + NUMONE, NUMONE);
		getGame().getGame().getTable().getCellByPos(NUMONE + NUMONE, NUMONE + NUMONE).bornCell(
				(Graphics2D) getGame().getGame().getGraphics(), NUMONE + NUMONE, NUMONE + NUMONE);
		
		frameFixture.button(STEP).click();
		
		assertTrue(getGame().getGame().getTable().getCellByPos(NUMONE, NUMONE).isAlive());
		assertTrue(getGame().getGame().getTable().getCellByPos(NUMONE, NUMONE + NUMONE).isAlive());
		assertTrue(getGame().getGame().getTable().getCellByPos(NUMONE + NUMONE, NUMONE).isAlive());
		assertTrue(getGame().getGame().getTable().getCellByPos(NUMONE + NUMONE, NUMONE + NUMONE).isAlive());
		
		frameFixture.cleanUp();
	}
	
	/**
	 * Test: las células mueren por baja población.
	 */
	@Test
	public final void testDeadUnderPopulation() {
		createFrame();
		FrameFixture frameFixture = new FrameFixture(getFrame());
			
		getGame().getGame().getTable().getCellByPos(NUMONE, NUMONE).bornCell(
				(Graphics2D) getGame().getGame().getGraphics(), NUMONE, NUMONE);
		getGame().getGame().getTable().getCellByPos(NUMONE, NUMONE + NUMONE).bornCell(
				(Graphics2D) getGame().getGame().getGraphics(), NUMONE, NUMONE + NUMONE);
			
		frameFixture.button(STEP).click();
			
		assertFalse(getGame().getGame().getTable().getCellByPos(NUMONE, NUMONE).isAlive());
		assertFalse(getGame().getGame().getTable().getCellByPos(NUMONE, NUMONE + NUMONE).isAlive());
			
		frameFixture.cleanUp();
	}
		
	/**
	 * Test: las células mueren por mucha población.
	 */
	@Test
	public final void testDeadOverPopulation() {
		createFrame();
		FrameFixture frameFixture = new FrameFixture(getFrame());
				
		getGame().getGame().getTable().getCellByPos(NUMONE, NUMONE + NUMONE).bornCell(
				(Graphics2D) getGame().getGame().getGraphics(), NUMONE, NUMONE + NUMONE);
		
		getGame().getGame().getTable().getCellByPos(NUMONE + NUMONE, NUMONE + NUMONE).bornCell(
				(Graphics2D) getGame().getGame().getGraphics(), NUMONE + NUMONE, NUMONE + NUMONE);
		
		getGame().getGame().getTable().getCellByPos(NUMONE + NUMONE, NUMONE).bornCell(
				(Graphics2D) getGame().getGame().getGraphics(), NUMONE + NUMONE, NUMONE);
		
		getGame().getGame().getTable().getCellByPos(NUMONE + NUMONE, NUMONE + NUMONE + NUMONE).bornCell(
				(Graphics2D) getGame().getGame().getGraphics(), NUMONE + NUMONE, NUMONE + NUMONE + NUMONE);
		
		getGame().getGame().getTable().getCellByPos(NUMONE + NUMONE + NUMONE, NUMONE + NUMONE).bornCell(
				(Graphics2D) getGame().getGame().getGraphics(), NUMONE + NUMONE + NUMONE, NUMONE + NUMONE);
		
		
		frameFixture.button(STEP).click();
				
		assertFalse(getGame().getGame().getTable().getCellByPos(NUMONE + NUMONE, NUMONE + NUMONE).isAlive());
				
		frameFixture.cleanUp();
	}
	
	/**
	 * Test: una célula nace.
	 */
	@Test
	public final void testBornCell() {
		createFrame();
		FrameFixture frameFixture = new FrameFixture(getFrame());
				
		getGame().getGame().getTable().getCellByPos(NUMONE, NUMONE).bornCell(
				(Graphics2D) getGame().getGame().getGraphics(), NUMONE, NUMONE);
		getGame().getGame().getTable().getCellByPos(NUMONE, NUMONE + NUMONE).bornCell(
				(Graphics2D) getGame().getGame().getGraphics(), NUMONE, NUMONE + NUMONE);
		getGame().getGame().getTable().getCellByPos(NUMONE + NUMONE, NUMONE).bornCell(
				(Graphics2D) getGame().getGame().getGraphics(), NUMONE, NUMONE + NUMONE);
		
		frameFixture.button(STEP).click();
				
		assertTrue(getGame().getGame().getTable().getCellByPos(NUMONE + NUMONE, NUMONE + NUMONE).isAlive());
				
		frameFixture.cleanUp();
	}
}
