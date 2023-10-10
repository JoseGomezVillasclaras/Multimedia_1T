package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//Creo la clase VentanaAhorcado que extiende de JFrame para que funcione con windowsBuilder
public class VentanaAhorcado extends JFrame {
	private Ahorcado juego;
	private JTextField letraTextField;
	private JTextArea palabraMostradaTextArea;
	private JLabel intentosRestantesLabel;

	public VentanaAhorcado() {
		juego = new Ahorcado();
		setTitle("Juego del Ahorcado");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 200);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		palabraMostradaTextArea = new JTextArea();
		palabraMostradaTextArea.setEditable(false);
		palabraMostradaTextArea.setFont(new Font("Monospaced", Font.PLAIN, 24));
		add(palabraMostradaTextArea, BorderLayout.CENTER);

		JPanel panelInferior = new JPanel();
		intentosRestantesLabel = new JLabel("Intentos restantes: " + juego.getIntentosRestantes());
		letraTextField = new JTextField(1);

		JButton adivinarButton = new JButton("Adivinar");
		adivinarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adivinarLetra();
			}
		});

		panelInferior.add(new JLabel("Ingresa una letra: "));
		panelInferior.add(letraTextField);
		panelInferior.add(adivinarButton);
		panelInferior.add(intentosRestantesLabel);
		add(panelInferior, BorderLayout.SOUTH);

		actualizarPalabraMostrada();
	}

	//Este metodo compara la letra ingresada con las letras de la palabra ha advinar
	private void adivinarLetra() {
		//Comvertimos la letra ingresada a mayuscula
		String letraIngresada = letraTextField.getText().toUpperCase();
		
		if (letraIngresada.length() == 1) {
			char letra = letraIngresada.charAt(0);
			// En estos if comprobamos los casos de manera si tenemos que actualizar la palabra sie l jugador a hacertado
			
			if (juego.adivinarLetra(letra)) {
				actualizarPalabraMostrada();
				//Si se le acaban los intentos lanzamos la condición de derrota
				if (juego.juegoTerminado()) {
					mostrarMensajeFinDeJuego();
				}
			} else {
				//Si falla
				JOptionPane.showMessageDialog(this, "Letra incorrecta. Intenta de nuevo.", "Error",
						JOptionPane.ERROR_MESSAGE);
				actualizarPalabraMostrada();
				juego.adivinarLetraIncorrecta(); // Decrementa los intentos
				//Si se le acaban los intentos
				if (juego.juegoTerminado()) {
					mostrarMensajeFinDeJuego();
				}
			}
			letraTextField.setText("");
			letraTextField.requestFocus();
		} else {
			//Si da error al ingresar la letra ya que es mas de una lanzamos un error con un mensaje
			JOptionPane.showMessageDialog(this, "Ingresa una sola letra.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Este metodo se encarga de finalizar el juego
	private void mostrarMensajeFinDeJuego() {
		//Comprobamos si se le han acabado lo intentos y lanzamos el mensaje de  derrota o si por los contrario
		if (juego.juegoTerminado()) {
			if (juego.getIntentosRestantes() == 0) {
				JOptionPane.showMessageDialog(this, "Perdiste. La palabra era: " + juego.getPalabraMostrada(),
						"Fin del juego", JOptionPane.INFORMATION_MESSAGE);
				//A ganado 
			} else {
				JOptionPane.showMessageDialog(this, "¡Ganaste!", "Fin del juego", JOptionPane.INFORMATION_MESSAGE);
			}
			//Por ultimo reiniciamos el juego
			juego.reiniciarJuego();
			actualizarPalabraMostrada();
			letraTextField.setText("");
			letraTextField.requestFocus();
		}
	}

	private void actualizarPalabraMostrada() {
		palabraMostradaTextArea.setText(juego.getPalabraMostrada());
		intentosRestantesLabel.setText("Intentos restantes: " + juego.getIntentosRestantes());
	}
}