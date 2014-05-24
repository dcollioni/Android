package com.dcollioni.projetocliente;

public class Cliente {

	private String nome;
	private EstadoCivil estadoCivil;
	private boolean contaCorrente;
	private boolean cartaoCredito;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	
	public boolean isContaCorrente() {
		return contaCorrente;
	}
	public void setContaCorrente(boolean contaCorrente) {
		this.contaCorrente = contaCorrente;
	}
	
	public boolean isCartaoCredito() {
		return cartaoCredito;
	}
	public void setCartaoCredito(boolean cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}
	
	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", estadoCivil=" + estadoCivil
				+ ", contaCorrente=" + contaCorrente + ", cartaoCredito="
				+ cartaoCredito + "]";
	}
}
