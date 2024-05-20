package clasesJuego;
/* Clase padre Piloto Solo usado interface.
 *  Y PilotoInterfaceGrafica por InterfaceGrafica.
 *  Pues necesita cambiar el metodo realizarAccion para La Pantalla de Asteroides. */
public class PilotoInterfaceGrafica extends Piloto { 

		public boolean estaJugando=true;
	
	public PilotoInterfaceGrafica() {
		super();
	}

	public PilotoInterfaceGrafica(String nombre) {
		super(nombre);
	}
	@Override
	public void realizarAccion(int accion) {
		switch(accion){
			case 1:
				if (nave.getCombustible()>=1) { 
					numBase+=nave.avanzaUnoInterfaceGrafica(numBase);
					if (!nave.getDestruida()) dinero+=Base.daPremio(numBase);
					accionesTotales++;
					haComprado=false;
				} else System.out.println(" No Tiene Suficiente Combustible.");
				break;
			case 2:
				if (nave.getCombustible()>=2) { 
					numBase+=nave.avanzaVariosInterfaceGrafica(numBase);
					if (!nave.getDestruida()) dinero+=(Base.daPremio(numBase)*2);
					accionesTotales++;
					haComprado=false;
				} else System.out.println(" No Tiene Suficiente Combustible.");
				break;
			case 3:
				if (nave.getCombustible()>=2 && nave.getImpactos()>0) { 
					nave.repararGastandoCombustible();
					accionesTotales++;
				} else if (nave.getCombustible()>=2)  System.out.println(" La Nave Esta Como Nueva.");				
				else System.out.println(" No Tiene Suficiente Combustible.");
				break;
			default:
				break;
		}		
	}
}
