package DAM;


import java.util.Scanner;

public class Teclado {
	public Scanner teclado;
	
	public Teclado () {
		teclado = new Scanner(System.in);
    }
	
	public Teclado(Scanner miScanner) {
		teclado=miScanner;
	}

	public void Cerrar () {
		teclado.close();
	}
	
	public int leEntero () {
		int devuelve=0;
		while (true) {
			try {
				devuelve = teclado.nextInt();
				return devuelve;
			} catch (Exception e){
				teclado.next();				
			}
		}
	}
	
	public int leEntero (String mensaje) {
		System.out.print(mensaje+" ");
		return leEntero ();
	}
	
	public double leDoble () {
		double devuelve=0;
		while (true) {
			try {
				devuelve = teclado.nextDouble();	
				return devuelve;
			} catch (Exception e){
				teclado.next();
			}
		}
	}
	
	public double leDoble (String mensaje) {
		System.out.print(mensaje);
		return leDoble ();
	}
	
	public boolean leLogico () {
		String lec="";
		while (true) {
			try {
				lec = teclado.next();
				if (lec.equalsIgnoreCase("SI") || lec.equalsIgnoreCase("S") || lec.equalsIgnoreCase("TRUE") || 
						lec.equalsIgnoreCase("T") || lec.equalsIgnoreCase("1") || lec.equalsIgnoreCase("YES") ||
						lec.equalsIgnoreCase("Y") ) return true;
			//	else if (lec=="NO" ||lec=="N" || lec=="FALSE" || lec=="F" || lec=="0") return false;				
				else return false;				
			} catch (Exception e){
				teclado.next();
			}
		}

	}
	
	public boolean leLogico (String mensaje) {
		System.out.print(mensaje);
		return leLogico();
	}
	
	public String lePalabra () {
		return teclado.next();
	}
	
	public String lePalabra (String mensaje) {
		System.out.print(mensaje+" ");
		return lePalabra();
	}
	
	public String leFrase () {
		String devuelve="\n";
		while (true) {
			try {
				devuelve = teclado.nextLine();
				if (devuelve.length()>0)return devuelve;
			} catch (Exception e){
				teclado.next();				
			}
		}
	}
	
	public String leFrase (String mensaje) {
		System.out.print(mensaje+" ");
		return leFrase();
	}
	
	public Byte leByte () {
		return teclado.nextByte();
	}
	
	public Byte leByte (String mensaje) {
		System.out.print(mensaje+" ");
		return leByte();
	}

	public int leMenu (int numero_total) {
		return leMenu (1,numero_total);
	}
	
	public int leMenu (int numero_total, String mensaje) {
		return leMenu (1,numero_total,mensaje);
	}
	
	public int leMenu (int numero_minimo,int numero_maximo) {
		int devuelve=0;
		while (true) {
			try {
				devuelve = teclado.nextInt();
				if (devuelve>=numero_minimo && devuelve<=numero_maximo) return devuelve;
			} catch (Exception e){
				teclado.next();				
			}
		}
	}
	
	public int leMenu (int numero_minimo,int numero_total, String mensaje) {
		System.out.print(mensaje+"Haga su Eleccion ("+numero_minimo+" , "+numero_total+"): ");
		return leMenu (numero_minimo,numero_total);		
	}

}
