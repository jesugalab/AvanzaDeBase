package clasesJuego;
//Añadido : Basica (impactos 5) -  Combate Intermedio (escudo=3) - Combate Pesado (impactos 7 , escudo=3) - Cazas Estelares  (impactos 10 , escudo=5) 

public class NaveCombateIntermedia extends Nave {

	protected  int escudo, limiteCombustible, limiteEscudo, limiteImpactos ;
	
	public NaveCombateIntermedia() {
		super();
		tipo="Combate Intermedia";
		escudo =3;
		limiteEscudo=3;
		limiteCombustible=20;
		limiteImpactos=5;
	} // 2800 - 3000
	@Override
	public void setDanio(int imp) {  	// Actualiza el estado de la Nave
		if (escudo>0) {						// lo tengo que sobrescribir puesto que no tenia escudo ni limite de impactos
			if (escudo>=imp) escudo-=imp;
			else {
				imp-=escudo;
				escudo=0;
				impactos += imp;
			}
		}else {
			impactos += imp;
		}
	/*	if (this.impactos<0) this.impactos=0;
		else */ if (impactos>=limiteImpactos) destruida=true; 
	}	
	@Override
	public String toString() {  // Lo sobreescribo para mostrar todos los datos, limites y escudo
		if (destruida) return "Nave Tipo: "+tipo+". Ha sido DESTRUIDA. Tenia "+combustible+" (de "+limiteCombustible+") Celulas de Combustible.";
		else return "Nave Tipo: "+tipo+". Tiene "+combustible+" (de "+limiteCombustible+") Celulas de Combustible. Un Escudo con resistencia "+escudo+" (de "+limiteEscudo+"). Y  " + impactos + " (de "+limiteImpactos+") Impactos en su Casco.";
	}
	@Override
	void echarCombustible(int i) { // lo tengo que sobrescribir puesto que no tenia limite
		combustible+=i;
		if (combustible>limiteCombustible) combustible=limiteCombustible;
	}
	@Override
	void repararEscudo(int i) { // lo tengo que sobrescribir puesto que es vacio porque no tenia escudo
		escudo+=i;
		if (escudo>limiteEscudo) escudo=limiteEscudo;
	}
	
	
	
	
}
