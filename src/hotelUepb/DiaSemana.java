package hotelUepb;

public enum DiaSemana {
	SEGUNDA(0), TERCA(0), QUARTA(0), QUINTA(0), SEXTA(25), SABADO(50), DOMINGO(50);
	
	private double Taxa;
	
	DiaSemana(double Taxa){
		this.Taxa = Taxa;
	}

	public double getTaxa() {
		return Taxa;
	}
}
