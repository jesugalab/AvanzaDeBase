package clasesJuego;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

public class PantallaLLuviaMeteoritos implements KeyListener {
	private final String nave = String.valueOf((char) 2);
	private final String ast = String.valueOf((char) 15);
	private final String explota=String.valueOf((char) 230);
    private int fila=20;
    private int columna=15;
    private int duracionActual=0;
    private int duracion=22;
    private int danio=0;
    private int velocidad=0;
    private JFrame frame;
    private JTextArea textArea;
    private String pantalla [] = {"                              \n","                              \n","                              \n",
    					"                              \n","                              \n","                              \n",
    					"                              \n","                              \n","                              \n",
    					"                              \n","                              \n","                              \n",
    					"                              \n","                              \n","                              \n",
    					"                              \n","                              \n","                              \n",
    "                              \n","                              \n","                              \n","                              \n"};
   
    public static void main(String[] args) {  // main para comprobar su uso correcto
        PantallaLLuviaMeteoritos lluvia = new PantallaLLuviaMeteoritos();
        int va = lluvia.Iniciar(1);        
        System.out.println(va);
    }
 
    public PantallaLLuviaMeteoritos() {  	
        // Cargar la fuente desde el archivo
    	Font customFont = cargarFuente("/Resources/Px437_DOS-V_TWN16.ttf");
        // Crear un nuevo JFrame
        frame = new JFrame("LLuvia de Asteroides");
        frame.setSize(394, 585);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Crear un nuevo JTextArea
        textArea = new JTextArea();
        // Establecer la fuente personalizada en el JTextArea
        textArea.setFont(customFont);
        textArea.setEditable(false);
        // Agregar el JTextArea al JFrame
        frame.add(textArea, BorderLayout.CENTER);
        // Añadir KeyListener al JTextArea
        textArea.addKeyListener(this);
        // Hacer visible el JFrame
        frame.setVisible(true);
    }
    
    public int Iniciar(int vel)  {  // Inicia el Juego velocidad del 0 al 100 valores nevarivos mas lento
    	velocidad=200-(vel*2);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // Llamar al método cada X Milisegundos
                for (int i=1;i<44;i++) {
                    try {
                        // Llamar al método
                        actualizar();
                        // Esperar X Milisegundos
                        if (velocidad>0) Thread.sleep(velocidad);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start(); // Iniciar el hilo       
        try {
            // Esperar a que el hilo termine
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        frame.dispose(); // Cerrar el Frame
        return danio;     
    }
    // Método para cargar una fuente desde el JAR
    private static Font cargarFuente(String path) {
        try {
            InputStream fontStream = PantallaLLuviaMeteoritos.class.getResourceAsStream(path);
            return Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(Font.PLAIN, 25);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            // En caso de error, Intentamos desde el archivo
            return cargarFuenteArchivo(path);
        }
    }
    // Método para cargar una fuente desde un archivo
    private static Font cargarFuenteArchivo(String path) {
        try {
            File fontFile = new File(path); // path
            return Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(Font.PLAIN, 25);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            // En caso de error, devolvemos una fuente predeterminada
            return new Font("Arial", Font.PLAIN, 25);
        }
    }
    // nuestra contenido de nu jar
    private void nuestraContenidoJar() {
    	try {
            // Reemplaza "nombre_del_archivo.jar" con el nombre de tu archivo JAR
            JarFile jarFile = new JarFile("AvanzaDeBase_V2.2.jar");
            
            // Obtén una enumeración de las entradas en el archivo JAR
            Enumeration<JarEntry> entries = jarFile.entries();
            
            // Itera sobre las entradas y muestra los nombres de los archivos
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                System.out.println(entry.getName());
            }
            
            // Cierra el archivo JAR
            jarFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    	
    @Override
    public void keyTyped(KeyEvent e) {
        // Manejar el evento de tecla presionada
    }
    @Override
    public void keyPressed(KeyEvent e) {
        // Manejar el evento de tecla presionada
    	if (e.getKeyCode()==37 || e.getKeyCode()==100) { // Puslación Izqierda de Flecha y teclado numerico
    		if (columna>1) columna--;
    	}else if (e.getKeyCode()==39 || e.getKeyCode()==102) { // Puslación Derecha de Flecha y teclado numerico
    		if (columna<29) columna++;
    	}else if (e.getKeyCode()==38 || e.getKeyCode()==104) {  // Puslación Arriba de Flecha y teclado numerico
  //  		if (fila>1) fila--;
    	}else if (e.getKeyCode()==40 || e.getKeyCode()==98) {  // Puslación Abajo de Flecha y teclado numerico
  //   		if (fila<20) fila++;
    	}else if (e.getKeyCode()==10 || e.getKeyCode()==32 || e.getKeyCode()==155) {  // Puslación Intro, Espacio y 0 del teclado numerico  		
    	}// else textArea.append("Tecla presionada: " + e.getKeyCode()+" -> "+((char) e.getKeyCode())+"\n");
    }
    @Override
    public void keyReleased(KeyEvent e) {
        // Manejar el evento de tecla liberada       	
    }   

	private void actualizar() {	// EL MOTOR DEL JUEGO. 	
		textArea.setText("");				// borra texarea
		for (int f=21; f>0 ; f--) {			// bucle baja una fila
			pantalla[f]=pantalla[f-1];		
		}
		if (duracionActual<duracion) pantalla[0]=GeneraFila(); // genera fila nueva o en blanco
		else pantalla[0]="                              \n";
		
		pintar(); //  pinta pantalla en textarea
		duracionActual++;
	}
	
	private void pintar() {	//  pinta pantalla en textarea
		for (int f=0; f<22 ; f++) {			// bucle pinta pantalla en textarea
			if (f!=fila) textArea.append(pantalla[f]); // si la nava no est en la fila
			else {
				for (int c=1; c<30; c++){	// Bucle recorre los caracteres de la fila
        			if ( c==columna) { // si esta la nave
        				if (pantalla[f].substring(c-1, c).equals(" ")) textArea.append(nave); // si  esta el espacio libre pinta la nave
        				else {	// pierde vida
        					danio++;
        					 textArea.append(explota);
        				}
        			}
        			else textArea.append(pantalla[f].substring(c-1, c));// si no esta la nave
        		}
			}
		}
	}

	private String GeneraFila() { // Genera una fila con Asteroides aleatoriamente
		int nivel=velocidad/100;
		String da="";
		for (int c=1; c<30; c++){
			if (Math.floor(Math.random()*(5-nivel))==1) da+=ast;
			else da+=" ";
		}
		return da+"\n";
	}
}
