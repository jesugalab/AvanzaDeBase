package clasesJuego;

public class Nave {

	// id ne nave no necesario, eliminado 
	protected int  combustible, impactos;
	protected boolean destruida;
// Añadido : Basica (impactos 5) -  Combate Intermedio (escudo=3) - Combate Pesado (impactos 7 , escudo=3) - Cazas Estelares  (impactos 10 , escudo=5) 
	protected String tipo="Basica";	
	
	public Nave() {
		combustible=20;
		impactos=0;
		destruida=false;
	}

	@Override
	public String toString() {
		if (destruida) return " Nave Tipo: "+tipo+". Ha sido DESTRUIDA. Tenia "+combustible+" Celulas de Combustible.";
		else return " Nave Tipo: "+tipo+". Tiene "+combustible+" Celulas de Combustible y " + impactos + " Impactos en su Casco.";
	}
	
	public String getTipo() {
		return tipo;
	}

	public int getCombustible() {
		return combustible;
	}

	public int getImpactos() {
		return impactos;
	}

	public boolean getDestruida() {
		return destruida;
	}
	
	void setDanio(int impact) { // Actualiza el estado de la Nave
		impactos += impact;
		if (this.impactos>=5) destruida=true; 
	}
	
	void repararGastandoCombustible() { // repara 2 de la nave a costa de 2 de combustible
		combustible-=2;	
		this.repararNave(2);
	}
	
	void repararNave(int i) { // repara i  impactos de la nave
		impactos -= i;
		if (impactos<0) impactos=0;
	}
	
	void echarCombustible(int i) { // echa i de combustible
		combustible+=i;
		if (combustible>20) combustible=20;
	}
	
	void repararEscudo(int i) {	} // repara el escudo (Esta no tiene)
	
	int avanzaUno() { // quita 1 combustible, llama a 1 lluvias y devuelve el avanze (1)
		combustible-=1;	
		this.setDanio (this.lluviaMeteroritos());
		if(!destruida) return 1;
		else return 0;
	}
	
	int avanzaVarios() { // quita 2 combustible, llama a 2 lluvias y devuelve el avanze (1 a 4)
		combustible-=2;	
		this.setDanio (this.lluviaMeteroritos());
		this.setDanio (this.lluviaMeteroritos());
		if(!destruida) return (int) Math.floor(Math.random()*4)+1;
		else return 0;
	}

	// Accasible solo para la clase simola los impacros de la llivia de meteoritos
	private int lluviaMeteroritos() { // genera daños de 0 a 3
		return (int) Math.floor(Math.random()*4);
	}
	
	// Metodos para la Interface Grafica a traves de PilotoInterfaceGrafica para no usar el metodo situado encima
	int avanzaUnoInterfaceGrafica(int numBase) { // quita 1 combustible, llama a 1 lluvias y devuelve el avanze (1)
		combustible-=1;	
		PantallaLLuviaMeteoritos lluvia = new PantallaLLuviaMeteoritos();
	    int va = lluvia.Iniciar(numBase);
		this.setDanio (va);
		if(!destruida) return 1;
		else return 0;
	}
	
	int avanzaVariosInterfaceGrafica(int numBase) { // quita 2 combustible, llama a 2 lluvias y devuelve el avanze (1 a 4)
		combustible-=2;	
		PantallaLLuviaMeteoritos lluvia = new PantallaLLuviaMeteoritos();
	    int va = lluvia.Iniciar(numBase);
		this.setDanio (va);
		lluvia = new PantallaLLuviaMeteoritos();
	    va = lluvia.Iniciar(numBase);
		this.setDanio (va);if(!destruida) return (int) Math.floor(Math.random()*4)+1;
		else return 0;
	}		
}
