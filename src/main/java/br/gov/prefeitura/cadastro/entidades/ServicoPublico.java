package br.gov.prefeitura.cadastro.entidades;


import br.gov.prefeitura.cadastro.service.Categoria;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table (name = "serviços_publicos")
public class ServicoPublico {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    private Boolean ativo = true;
    private Date dataInicio;
    private Date dataFim;

    @ManyToMany(mappedBy = "servicos")
    private List<Cidadao> cidadao = new ArrayList<>();

    public ServicoPublico() {
    }

    public ServicoPublico(String nome, Categoria categoria, Date dataInicio) {
        this.nome = nome;
        this.categoria = categoria;
        this.dataInicio = dataInicio;
    }

    public List<Cidadao> getCidadaos() {
        return cidadao;
    }

    public void adicionarCidadao(Cidadao cidadao) {
        this.cidadao.add(cidadao);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }
}
