package clasesJuego;

public class Piloto {
	
	protected static int numPilotos=0;
	
	// añadido dinero, id cambiado a String y numero de acciones realizadas.  
	
	protected int  numBase, dinero, accionesTotales;
	protected Nave nave;
	protected String id;
	protected boolean haComprado; // dice si ya ha comprado un porducto en la base actual
	
	public Piloto () {
		numPilotos++;
		id=numPilotos+"";
		numBase=0;
		dinero=0;
		accionesTotales=0;
		nave= new Nave ();
		haComprado=false;
	}
	public Piloto (String nombre) {
		this();
		id=nombre;
	}
	
	public String getId() {
		return id;
	}

	public int getNumBase() {
		return numBase;
	}
	
	public int getDinero() {
		return dinero;
	}
	public int getAccionesTotales() {
		return accionesTotales;
	}
	
	public boolean isNaveDestruida() {
		return nave.getDestruida();
	}
	public boolean haComprado() {
		return haComprado;
	}

	public String toStringNave() {
		return nave.toString();
	}
	@Override
	public String toString() {
		if (nave.getDestruida()) return "Piloto "+id+". Murio al abandonar la Base "+numBase+". Y tenia "+dinero+" Creditos.";
		else return "Piloto "+id+". Se encuentra en la Base "+numBase+". Y tiene "+dinero+" Creditos.";
	}
	
	public void realizarAccion(int accion) {
		switch(accion){
			case 1:
				if (nave.getCombustible()>=1) { 
					numBase+=nave.avanzaUno();
					if (!nave.getDestruida()) dinero+=Base.daPremio(numBase);
					accionesTotales++;
					haComprado=false;
				} else System.out.println("No Tiene Suficiente Combustible.");
				break;
			case 2:
				if (nave.getCombustible()>=2) { 
					numBase+=nave.avanzaVarios();
					if (!nave.getDestruida()) dinero+=(Base.daPremio(numBase)*2);
					accionesTotales++;
					haComprado=false;
				} else System.out.println("No Tiene Suficiente Combustible.");
				break;
			case 3:
				if (nave.getCombustible()>=2  && nave.getImpactos()>0) { 
					nave.repararGastandoCombustible();
					accionesTotales++;
				}else if (nave.getCombustible()>=2)  System.out.println("La Nave Esta Como Nueva.");				
				else System.out.println("No Tiene Suficiente Combustible.");
				break;			
			default:
				break;
		}		
	}
	// accesible para el paquete (para Las Bases)
	void pagar(int i) {
		dinero-=i;
		accionesTotales++;
		haComprado=true;
	}

	void intercambiarNave(Nave nave2) {
		nave=nave2;
	}

	void echarCombustible(int i) {
		nave.echarCombustible(i);	
	}

	void repararEscudo(int i) {
		nave.repararEscudo(i);	
	}

	void repararNave(int i) {
		nave.repararNave(i);	
	}
}
