package lanzarJuego;

import DAM.Teclado;
import clasesJuego.Base;
import clasesJuego.Piloto;

public class Interface {

	public static void main(String[] args) {
		// Lanza el programa
		String nom;
		int accion;
		Teclado tecla=new Teclado();
		int totalPilotos=tecla.leEntero("Cuantos pilotos van a Participar? ");
		Piloto piloto [] = new Piloto [totalPilotos] ;
		for (int i=0;i<totalPilotos;i++) {
		//	tecla.leFrase();
			nom=tecla.leFrase("Introduce el Nombre del Piloto: ");
			if(nom=="\n") piloto [i]=new Piloto();
			else piloto [i]=new Piloto(nom);
			System.out.println("\nBienvenido a la Base 0. Nos an Informado que Se ha Unido a Nuestras Filas.\n"+piloto[i].getId()+" Aqui Tiene Su Nueva "+piloto[i].toStringNave()+"\nLamentablemente Aqui Solo Disponemos de Naves Basicas Nuevas.\n Que Tenga Suerte !!!");
			do {
				System.out.println("\n\n==>> "+piloto[i].toString()+"\n==>> "+piloto[i].toStringNave()+"\n");
				accion=tecla.leMenu(5, "1.- Avanzar una base recibiendo una lluvia de meteoritos (entre 0 y 3 impactos) en su nave y gastando 1 Celula de combustible.\n"
						+ "2.- Avanzar Multiples (entre una y cuatro) bases recibiendo dos lluvias de meteoritos (entre 0 y 3 impactos dos veces) y gastando 2 Celulas de combustible.\n"
						+ "3.- Reparar tu mismo dos impactos gastando 2 Celulas de combustible y sin avanzar ninguna base (Sin lluvias de meteoritos).\n"
						+ "4.- Entrar en la Base. Te Esperan Muchas Sorpresas.\n"
						+ "5.- TERMINAR (Antes que te maten y no puedas disfrutar tus "+piloto[i].getDinero()+" Creditos).\n"
						+ "\n  "+piloto[i].getId()+", ");
				if (accion==4 ) {
					if (!piloto[i].haComprado()) {
					System.out.println("\n\n--> "+piloto[i].toString()+". Realizó "+piloto[i].getAccionesTotales()+" Acciones en total.\n--> "+piloto[i].toStringNave()+"\n");
					accion=tecla.leMenu(5,Base.menu(piloto[i].getNumBase()));
					System.out.println(Base.venderProducto(piloto[i],accion));
					accion=0;
					} else System.out.println("\n Ya Realizó una Compra En esta Base. Avance a la Siguiente Base si Quiere Seguir Comprando.");
				}
				else piloto[i].realizarAccion(accion);
			}while (!piloto[i].isNaveDestruida() && accion!=5);
			System.out.println("\n---->> FINAL:\n==>> "+piloto[i].toString()+"\n==>> "+piloto[i].toStringNave()+"\n");
		}
		// nuestra los Resultados
		System.out.println("\n>>>>>>>> RESULTADOS FINALES <<<<<<<<\n");
		for (int i=0;i<totalPilotos;i++) { 
			System.out.println("\n==>> "+piloto[i].toString()+"\n==>> "+piloto[i].toStringNave());
		}
	}

}
