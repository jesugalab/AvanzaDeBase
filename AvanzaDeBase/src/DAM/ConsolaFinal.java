package DAM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Scanner;

public class ConsolaFinal {
	public static void main(String[] args) {
		ConsolaFinal miConsola=new ConsolaFinal();
		miConsola.Iniciar(false);
       Scanner miLector = miConsola.getScanner();
       //  Scanner miLector = new Scanner(System.in);
           // Aquí el programa   
        System.out.println("\nPuedes Escribir ñññ");       
        System.out.println(Charset.defaultCharset().displayName());
		if (miLector != null) {
            while (miLector.hasNext()) {
                String palabra = miLector.next();
                System.out.println("Lectura en Main: " + palabra);
            }
        }
    }
	
	private Scanner miLector;
    private JFrame frame;
    private JTextArea textAreaPrograma;
    private JTextArea textAreaErrores;
    private PipedOutputStream pipedOutputStreamPrograma;
    private PipedInputStream pipedInputStream;
    private JTabbedPane tabbedPane;
    private JTextField inputField;
    private JScrollPane scrollPane;
    
    public void Iniciar() {
    	Iniciar("Mi Pantalla Para Aplicaciones de Consola",false);	
    }
    public void Iniciar(String Titulo) {
    	Iniciar(Titulo,false);	
    }
    public void Iniciar(boolean ConCapturaDeErrores) {
    	Iniciar("Mi Pantalla Para Aplicaciones de Consola",ConCapturaDeErrores);	
    }
    public void Iniciar(String Titulo, boolean ConCapturaDeErrores) {
        final ConsolaFinal [] ventana = {null}; // Declarar miLector como un array TRUCO
        try {
            SwingUtilities.invokeAndWait(() -> {
                ventana[0] = new ConsolaFinal(Titulo,ConCapturaDeErrores);
                ventana[0].mostrarVentana();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        InputStream inp=ventana[0].getEntrada();
         miLector = new Scanner(inp);
    }
    
    public void mostrarVentana() {
        SwingUtilities.invokeLater(() -> {
            frame.setVisible(true);
        });     
    }
    public PipedInputStream getEntrada() {
    		return pipedInputStream;	
    }
    public Scanner getScanner() {
		return miLector;	
    }
    public Teclado getTeclado() {
		return new Teclado (miLector);	
    }
    
    public ConsolaFinal(boolean ConCapturaDeErrores) {
        this("Mi Pantalla Para Aplicaciones de Consola",ConCapturaDeErrores);
    }
    public ConsolaFinal(String Titulo) {
        this(Titulo,false);
    }
    public ConsolaFinal() {
        this("Mi Pantalla Para Aplicaciones de Consola",false);
    }
    public ConsolaFinal(String Titulo, boolean ConCapturaDeErrores) {
    	String a=Titulo+"   .... Autor: Jesús Galán Brasal";
    	frame = new JFrame(a);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setBounds(100, 100, 735, 400);
    	textAreaPrograma = new JTextArea();
    	textAreaPrograma.setEditable(false);
    	inputField = new JTextField();
    	frame.getContentPane().add(inputField, BorderLayout.SOUTH);
    	if (ConCapturaDeErrores) {
    		 // Redirigir la salida de la consola de errores 
    		 PrintStream printStreamErrores =  new PrintStream(new CustomOutputStream(textAreaErrores));
    	     System.setErr(printStreamErrores);
    	     
        	 textAreaErrores = new JTextArea();
        	 textAreaErrores.setEditable(false);
        	 tabbedPane = new JTabbedPane();
             tabbedPane.addTab("Aplicacion", new JScrollPane(textAreaPrograma));
             tabbedPane.addTab("Errores", new JScrollPane(textAreaErrores));
             frame.getContentPane().add(tabbedPane);
             
         }else {
	        textAreaPrograma.setFocusable(false);
	        scrollPane = new JScrollPane(textAreaPrograma);
	        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
	     
         }

    	// redirigir la salida de la consola
        PrintStream printStreamPrograma =  new PrintStream(new CustomOutputStream(textAreaPrograma));
        System.setOut(printStreamPrograma);
        // Redirigir la entrada estándar 
        // crea el ImpotStream para el Scanner del programa externo
        try {
            pipedOutputStreamPrograma = new PipedOutputStream();
            pipedInputStream = new PipedInputStream(pipedOutputStreamPrograma);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText();
           	 // Configurar la codificación al leer o escribir datos
                Charset charset = Charset.forName("UTF-8");
                byte[] bytes = input.getBytes();
             // Character.toString(e.getKeyChar()).getBytes(charset);

            	try {
					printStreamPrograma.write(new String(input).getBytes(charset)); // el tutimo en ecribir lo que escribes
					pipedOutputStreamPrograma.write(bytes);// Lo envia al Scanner del programa
					pipedOutputStreamPrograma.write(13);
					pipedOutputStreamPrograma.write(13);
	        		pipedOutputStreamPrograma.flush();

            	} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                inputField.setText(""); // Limpiar el campo de entrada después de enviar
            }
        });
    } 
    
    private class CustomOutputStream extends OutputStream {
        private JTextArea textArea;

        public CustomOutputStream(JTextArea textArea) {
            this.textArea = textArea;
        }

        @Override
        public void write(int b) throws IOException {
            // Redirigir la salida a JTextArea
            textArea.append(String.valueOf((char) b));
            // Hacer que el último caracter sea visible
        //    textArea.setCaretPosition(textArea.getDocument().getLength());
         // Establecer la posición del scrollbar horizontal a la izquierda
            textArea.setCaretPosition(textArea.getDocument().getLength()); // Coloca el cursor al final
         //   scrollPane.getHorizontalScrollBar().setAlignmentX(100);
	        
        }
    }   
}