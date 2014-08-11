package collioni.douglas.projetobanco;

public class Tarefa {
	private int codigo;
	private String descricao;
	private boolean concluida;
	
	public int getCodigo() {
		return this.hashCode();
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public boolean isConcluida() {
		return concluida;
	}
	public void setConcluida(boolean concluida) {
		this.concluida = concluida;
	}
	
	public Tarefa(int codigo, String descricao) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.concluida = false;
	}
	
	public Tarefa(String descricao) {
		super();
		this.descricao = descricao;
		this.concluida = false;
	}
	
	@Override
	public String toString() {
		return descricao;
	}
}
