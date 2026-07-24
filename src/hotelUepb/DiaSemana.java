package hotelUepb;

public enum DiaSemana {
	SEGUNDA("Segunda", 0), TERCA("Terça", 0), QUARTA("Quarta", 0), QUINTA("Quinta", 0), SEXTA("Sexta", 25), SABADO("Sábado", 50), DOMINGO("Domingo", 50);
	
	private String diaSemana;
	private double Taxa;
	
	private DiaSemana(String diaSemana, double Taxa){
		this.diaSemana = diaSemana;
		this.Taxa = Taxa;
	}

	public double getTaxa() {
		return Taxa;
	}

	public String getDiaSemana() {
		return diaSemana;
	}
}