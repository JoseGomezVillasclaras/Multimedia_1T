package main;
import java.util.Random;

public class Ahorcado {
	//Creamos el array de palabras a adivinar
    private String[] palabras = { "JAVA", "PROGRAMACION", "AHORCADO", "COMPUTADORA", "DESARROLLO" };
    //Los intentos que tiene para acertar las letras
    private int intentosRestantes = 6;
    //Creamos la palabra a adivinar y la palabra que vamos a mostrar durante el juego
    private String palabraAdivinar;
    private String palabraMostrada;

    public Ahorcado() {
    	//Declaramos e inicializamos el random
        Random rand = new Random();
        //Igualamos la palabra a adivinar de manera que la ponemos en mayuscula para evitar errores
        palabraAdivinar = palabras[rand.nextInt(palabras.length)].toUpperCase();
        //Sustituamos las letras de la palabra mostrada por guiones en su longitud
        palabraMostrada = "_".repeat(palabraAdivinar.length());
    }

    //Este metodo se encarga de comprobar si se ha adivinado una letra
    public boolean adivinarLetra(char letra) {
        boolean adivinado = false;
        StringBuilder nuevaPalabraMostrada = new StringBuilder(palabraMostrada);
        //Este bucle for itera de manera que comprueba letra por letra si coincide la letra ingresada con alguna de la palabra
        for (int i = 0; i < palabraAdivinar.length(); i++) {
        	//En el caso de que este la letra en la palabra cambiamos el booleano
            if (palabraAdivinar.charAt(i) == letra) {
                nuevaPalabraMostrada.setCharAt(i, letra);
                adivinado = true;
            }
        }
        //Sino esta quitamos un intento
        if (!adivinado) {
            intentosRestantes--;
        }
        //Si la palabra se completa devolvemos adivinado
        palabraMostrada = nuevaPalabraMostrada.toString();
        return adivinado;
    }
    
    //Esta función que devuelve un booleano sirve terminar el juego de manera que
    //devuelve una de las dos condiciones de que se acabe el juego
    public boolean juegoTerminado() {
        return palabraMostrada.equals(palabraAdivinar) || intentosRestantes == 0;
    }

    //Esta función reinicia el juego, de manera que elige una nueva palabra, reinicia los intentos y sustituye los simbolos
    public void reiniciarJuego() {
        Random rand = new Random();
        palabraAdivinar = palabras[rand.nextInt(palabras.length)].toUpperCase();
        palabraMostrada = "_".repeat(palabraAdivinar.length());
        intentosRestantes = 6;
    }
    //Esta función devuelve la palabra mostrada
    public String getPalabraMostrada() {
        return palabraMostrada;
    }

    //Esta función devuelve los intentos que quedan
    public int getIntentosRestantes() {
        return intentosRestantes;
    }
    //Esta función resta intentos en el caso de que se falle la letra
    public void adivinarLetraIncorrecta() {
        intentosRestantes--;
    }
}