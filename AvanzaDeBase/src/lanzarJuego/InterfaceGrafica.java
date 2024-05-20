package lanzarJuego;

import java.util.Scanner;

import DAM.ConsolaFinal;
import DAM.Teclado;
import clasesJuego.Base;
import clasesJuego.PilotoInterfaceGrafica;

public class InterfaceGrafica {
		
	private  ConsolaFinal miConsola;
	private  Scanner miLector;
	private  Teclado tecla;
	private  String nom;
	private  int accion;
	private  int totalPilotos;
	public  PilotoInterfaceGrafica piloto []= new PilotoInterfaceGrafica [0] ;
	
	public static void main(String[] args) {
		// Lanza el programa
		new InterfaceGrafica();
	}
	public InterfaceGrafica () {
	// Lanza el programa
		miConsola=new ConsolaFinal();
		miConsola.Iniciar("Avanza de Base                       ",false);
        miLector = miConsola.getScanner();
		tecla=new Teclado(miLector);
		boolean masPartidas=true;
		while(masPartidas) {
			totalPilotos=tecla.leEntero("\n\n   Cuantos pilotos van a Participar? ");
			piloto = new PilotoInterfaceGrafica [totalPilotos] ;
			if (totalPilotos>0) {
				if (totalPilotos==1)  PartidaJugJug ();
				else {
					int tipoJuego=tecla.leMenu(2, "\n\n\tSeleccione el tipo de Juego:\n\n 1.- Por Turnos (Alternando Jugadores).\n 2.- Cuando Maten a Uno Empieza el Siguiente.\n\n");
					if (tipoJuego==2) PartidaJugJug ();
					else PartidaTurnos();
				}	
			}
			// nuestra los Resultados
			System.out.println("\n >>>>>>>> RESULTADOS FINALES <<<<<<<<\n");
			for (int i=0;i<totalPilotos;i++) { 
				System.out.println("\n ==>> "+piloto[i].toString()+"\n==>> "+piloto[i].toStringNave());
			}
			masPartidas=tecla.leLogico("\n\n\t¿ Desea Jugar Otra Partida ? ");
		}
		System.exit(0);
	}
	private  void PartidaTurnos() {
	    // Inicializar los pilotos
	    System.out.println("\n\n Bienvenidos a la Base 0. Nos han informado que se han unido a nuestras filas. Aquí tienen sus nuevas.\n Lamentablemente aquí solo disponemos de naves básicas nuevas.\n  ¡Que tengan suerte!\n");
	    for (int i = 0; i < totalPilotos; i++) {
	        String nom = tecla.leFrase("\n Introduce el Nombre del Piloto " + (i + 1) + " :  ");
	        piloto[i] = new PilotoInterfaceGrafica(nom);
	    }
	    boolean juegoActivo = true;
	    // Bucle para alternar los turnos de los pilotos
	    while (juegoActivo) {
	        juegoActivo = false; // El juego terminará cuando todos los pilotos NO esten jugando
	        for (int i = 0; i < totalPilotos; i++) {
	            if (!piloto[i].isNaveDestruida() && piloto[i].estaJugando) {
	            	System.out.println("\n");
	                JugarTurno(i);
	                if(piloto[i].isNaveDestruida() || !piloto[i].estaJugando) {
	                	System.out.println("\n ---->> FINAL:\n ==>> "+piloto[i].toString()+"\n ==>> "+piloto[i].toStringNave()+"\n");
	                	juegoActivo = false;
	                } else juegoActivo = true; // Si algún piloto todavía está jugando, mantenemos el juego activo		                
	            }
	        }
	    }
	}
	private  void PartidaJugJug() {
		for (int i=0;i<totalPilotos;i++) {
			nom=tecla.leFrase("\n\n\tIntroduce el Nombre del Piloto "+(i+1)+" :  ");
			piloto [i]=new PilotoInterfaceGrafica(nom);
			System.out.println("\n Bienvenido a la Base 0. Nos an Informado que Se ha Unido a Nuestras Filas.\n "+piloto[i].getId()+" Aqui Tiene Su Nueva "+piloto[i].toStringNave()+"\n Lamentablemente Aqui Solo Disponemos de Naves Basicas Nuevas.\n  Que Tenga Suerte !!!");
			do {
				JugarTurno(i);
			}while (!piloto[i].isNaveDestruida() && piloto[i].estaJugando);
			System.out.println("\n ---->> FINAL:\n ==>> "+piloto[i].toString()+"\n ==>> "+piloto[i].toStringNave()+"\n");
		}		
	}
	private  void JugarTurno(int pilo) {
		System.out.println("\n\n ==>> "+piloto[pilo].toString()+"\n ==>> "+piloto[pilo].toStringNave()+"\n");
		accion=tecla.leMenu(5, " 1.- Avanzar una base recibiendo una lluvia de meteoritos en su nave y gastando 1 Celula de combustible.\n"
				+ " 2.- Avanzar Multiples (entre 2 y 5) bases recibiendo dos lluvias de meteoritos y gastando 2 Celulas de combustible.\n"
				+ " 3.- Reparar tu mismo dos impactos gastando 2 Celulas de combustible y sin avanzar ninguna base (Sin lluvias de meteoritos).\n"
				+ " 4.- Entrar en la Base. Te Esperan Muchas Sorpresas.\n"
				+ " 5.- TERMINAR (Antes que te maten y no puedas disfrutar tus "+piloto[pilo].getDinero()+" Creditos).\n"
				+ "\n   "+piloto[pilo].getId()+", ");
		if (accion==4 ) {
			if (!piloto[pilo].haComprado()) {
			System.out.println("\n\n --> "+piloto[pilo].toString()+". Realizo "+piloto[pilo].getAccionesTotales()+" Acciones en total.\n--> "+piloto[pilo].toStringNave()+"\n");
			accion=tecla.leMenu(5,Base.menu(piloto[pilo].getNumBase()));
			System.out.println(Base.venderProducto(piloto[pilo],accion));
			accion=0;
			} else System.out.println("\n  Ya se ha Realizado una Compra En esta Base. Avance a la Siguiente Base si Quiere Seguir Comprando.");
		}else if (accion==5) {piloto[pilo].estaJugando=false;}
		else piloto[pilo].realizarAccion(accion);
	}
}
