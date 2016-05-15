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
 * Clase frame donde se muestra toda la información.
 */
package es.ull.esit.pai.life;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Information extends JFrame {
	
	private final static int SIZE = 350;																																// Tamaño del jframe.
	private final static String AUTHOR = "<html><h3>Kevin Martín Chinea</h3></html>";										// Nombre autor
	private final static String DESCRIPTION = "<html><h4>PRACTICA 13: The fantastic combinations" +
																						" of John Conway’s new solitaire game “life”</h5>" +
																						"<hr><p>En esta práctica se propone desarrollar una" +
																						"aplicación Java que implemente eljuego de la vida." +
																						"\nEl juego de la vida es el mejor ejemplo de un " +
																						"autómata celular, un modelo matemático para un sistema" + 
																						"dinámico que evoluciona en pasos discretos. Desde un" +
																						"punto de vista teórico, el interés del juego de la vida" +
																						"procede de que es equivalente a una máquina universal de" +
																						"Turing, es decir, todo lo que se puede computar " +
																						"algorítmicamente se puede computar en el juego de la vida." +				
																						"</p><br></html>";
	private final static String EMAIL = "<html><b>Email:</b><br>alu0100774665@ull.edu.es</html>";				// Email.
	private static final String GENERALDATA = "<html><b>E.S.I.T.– INFORMÁ́TICA</b><br>Departamento " +		// Datos generales de la aplicación.
																						"de Ingeniería Informática y de Sistemas<br>" +
																						"Programación de Aplicaciones Interactivas<br><hr>" +
																						"</html>";
	
	private JLabel author;																			// Nombre del autor.
	private JLabel generalData;																	// Datos de la práctica.
	private JLabel description;																	// Descripción de la práctica.
	private JLabel email;																				// Email del autor.

	/**
	 * Constructor del frame con la información
	 * @param background
	 */
	public Information (Color background) {
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		getContentPane().setBackground(background);
		
		author = new JLabel (AUTHOR);
		generalData = new JLabel (GENERALDATA);
		description = new JLabel (DESCRIPTION);
		email = new JLabel (EMAIL);

		addDatas();
		
		setSize(SIZE, SIZE);
		setLocationRelativeTo(null);
	}

	/**
	 * Función que añade los datos
	 */
	private void addDatas() {
		add(getAuthor());
		add(getGeneralData());
		add(getDescription());
		add(getEmail());
	}
	
	/**
	 * Getter autor.
	 * @return author
	 */
	public JLabel getAuthor() {
		return author;
	}

	/**
	 * Setter autor.
	 * @param newAuthor
	 */
	public void setAuthor(JLabel newAuthor) {
		author = newAuthor;
	}

	/**
	 * Getter datos generales.
	 * @return generalData
	 */
	public JLabel getGeneralData() {
		return generalData;
	}

	/**
	 * Setter datos generales.
	 * @param newGeneralData
	 */
	public void setGeneralData(JLabel newGeneralData) {
		generalData = newGeneralData;
	}

	/**
	 * Getter descripción de la práctica.
	 * @return description
	 */
	public JLabel getDescription() {
		return description;
	}

	/**
	 * Setter descripción de la práctica.
	 * @param newDescription
	 */
	public void setDescription(JLabel newDescription) {
		description = newDescription;
	}

	/**
	 * Getter email
	 * @return email
	 */
	public JLabel getEmail() {
		return email;
	}

	/**
	 * Setter email
	 * @param newEmail
	 */
	public void setEmail(JLabel newEmail) {
		email = newEmail;
	}
}
