package modelo;

public class CaixasBuilder {

    private int id;
    private String descricao;
    private int quantidade;
    private String responsavel;

    public CaixasBuilder comId(int id){
        this.id = id;
        return this;
    }
    
    public CaixasBuilder comDescricao(String descricao){
        this.descricao = descricao;
        return this;
    }
    
    public CaixasBuilder comQuantidade(int quantidade){
        this.quantidade = quantidade;
        return this;
    }
    
    public CaixasBuilder comResponsavel(String responsavel){
        this.responsavel = responsavel;
        return this;
    }
    
    public Caixas constroi(){
        return new Caixas(id, descricao, quantidade, responsavel);
    }
    
}
