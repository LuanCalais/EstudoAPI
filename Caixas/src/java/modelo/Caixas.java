package modelo;

public class Caixas {
    
    private int id;
    private String descricao;
    private int quantidade;
    private String responsavel;

    public Caixas(int id, String descricao, int quantidade, String responsavel) {
        this.id = id;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.responsavel = responsavel;
    }
    
    public Caixas(){
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }
    
    
    
}
