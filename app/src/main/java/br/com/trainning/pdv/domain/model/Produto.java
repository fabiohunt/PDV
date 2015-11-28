package br.com.trainning.pdv.domain.model;

import se.emilsjolander.sprinkles.Model;
import se.emilsjolander.sprinkles.annotations.AutoIncrement;
import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.Key;
import se.emilsjolander.sprinkles.annotations.Table;

/**
 * Created by fabiogomes on 11/28/15.
 */

@Table("Produto")
public class Produto extends Model{

    @Key
    @AutoIncrement
    @Column("id")
    private Long id; //O sprinkles pede que o ID seja do tipo Long

    @Column("descricao")
    private String descricao;

    @Column("unidade")
    private String unidade;

    @Column("codigo_barras")
    private String codigoBarras;

    @Column("preco")
    private Double preco;

    @Column("foto")
    private String foto;

    @Column("ativo")
    private int ativo;

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }


    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", unidade='" + unidade + '\'' +
                ", codigoBarras='" + codigoBarras + '\'' +
                ", preco=" + preco +
                ", ativo=" + ativo +
                '}';
    }
}
