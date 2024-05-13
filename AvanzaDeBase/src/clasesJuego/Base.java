package clasesJuego;

public class Base {
	
	private static Nave [] naveVenta = {new Nave(), new NaveCombateIntermedia(), new NaveCombatePesada(), new NaveCazaEstelar(), new NaveCruzeroEstelar()};
	private static int [] precioNaveVenta= {3000, 4000, 5000, 6000, 10000 };
	
	private static int [] combustible= {1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2}; 
	private static int [] precioCombustible= {100, 200, 300, 100, 200, 300, 100, 200, 300, 100, 200, 300, 100, 200, 300, 100, 200, 300, 100, 200};
	
	private static int [] reparacion= {3, 2, 1, 3, 2, 1, 3, 2, 1, 3, 2, 1, 3, 2, 1, 3, 2, 1, 3, 2};
	private static int [] precioReparacion= {300, 200, 100, 300, 200, 100, 300, 200, 100, 300, 200, 100, 300, 200, 100, 300, 200, 100, 300, 200};
							
	private static int [] escudo= {1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2}; 
	private static int [] precioEscudo= {100, 200, 300, 100, 200, 300, 100, 200, 300, 100, 200, 300, 100, 200, 300, 100, 200, 300, 100, 200};
	
	public static int daPremio(int base) {
		return base*40;
	}
	public static String menu(int base) {
		return  "\n     >>>>> Suministros de la Base "+base+" <<<<<\n"
				+ " 1.-"+naveVenta[base%5].toString()+" Por: "+precioNaveVenta[base%5]+" Creditos\n"
				+ " 2.- Combustible: "+combustible[base%20]+" Celulas de Combustible por "+precioCombustible[base%20]+" Creditos\n"
				+ " 3.- Reparacion: "+reparacion[base%20]+" Impactos por "+precioReparacion[base%20]+" Creditos\n"
				+ " 4.- Escudo: "+escudo[base%20]+" de Resistencia por "+precioEscudo[base%20]+" Creditos\n"
				+ " 5.- Salir de la Base\n\n  ";
	}
	public static String venderProducto(Piloto piloto, int accion) {
		String Da="\n Gracias. ";
		switch(accion) {
		case 1:
			if (piloto.getDinero()>=precioNaveVenta[piloto.getNumBase()%5]) {
				piloto.pagar (precioNaveVenta[piloto.getNumBase()%5]);
				piloto.intercambiarNave(naveVenta[piloto.getNumBase()%5]);
				Da+=" Compra Realizada con Exito.";
			}else Da+=" No Tiene Suficientes Creditos.";			
			break;
		case 2:
			if (piloto.getDinero()>=precioCombustible[piloto.getNumBase()%20]) {
				piloto.pagar(precioCombustible[piloto.getNumBase()%20]);
				piloto.echarCombustible(combustible[piloto.getNumBase()%20]);
				Da+=" Compra Realizada con Exito.";
			}else Da+=" No Tiene Suficientes Creditos.";
			break;
		case 3:
			if (piloto.getDinero()>=precioReparacion[piloto.getNumBase()%20]) {
				piloto.pagar (precioReparacion[piloto.getNumBase()%20]);
				piloto.repararNave(reparacion[piloto.getNumBase()%20]);
				Da+=" Compra Realizada con Exito.";
			}else Da+=" No Tiene Suficientes Creditos.";
			break;
		case 4:
			if (piloto.getDinero()>=precioEscudo[piloto.getNumBase()%20]) {
				piloto.pagar (precioEscudo[piloto.getNumBase()%20]);
				piloto.repararEscudo(escudo[piloto.getNumBase()%20]);
				Da+="Compra Realizada con Exito.";
			}else Da+="No Tiene Suficientes Creditos.";
			break;		
		}
		return Da;
	}
}
