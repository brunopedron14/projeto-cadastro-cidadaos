package br.gov.prefeitura.cadastro.entidades;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cidadoes")
public class Cidadao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    @Column(unique = true)
    private String cpf;

    private String rg;
    private Date dataNascimento;
    private String email;
    private String telefone;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataUltimaAlteracao;

    // O atributo "mappedBy" indica que o dono do relacionamento é o atributo "cidadao" dentro da classe Endereco.
    // "cascade = CascadeType.ALL" faz com que operações como persistir ou remover um cidadão afetem seus endereços também.
    // "orphanRemoval = true" faz com que, ao remover um endereço da lista, ele também seja removido do banco automaticamente.

    @OneToMany(mappedBy = "cidadao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "cidadao_servico",
            joinColumns = @JoinColumn(name = "cidadao_id"),
            inverseJoinColumns = @JoinColumn(name = "servico_publico_id")
    )
    private List<ServicoPublico> servicos = new ArrayList<>();

    // Método de utilidade para adicionar um endereço à lista de endereços do cidadão.
    // Além de adicionar o endereço na lista, ele também define o cidadão dono do endereço.

    public void adicionarEndereco(Endereco endereco) {
        enderecos.add(endereco);
        endereco.setCidadao(this); // Garante que o relacionamento fique sincronizado dos dois lados.
    }

    // Método de utilidade para adicionar um serviço público à lista de serviços do cidadão.
    // Também adiciona este cidadão à lista de cidadãos do serviço público, garantindo consistência bidirecional.
    public void adicionarServicos(ServicoPublico servicoPublico) {
        servicos.add(servicoPublico);
        servicoPublico.adicionarCidadao(this); // Atualiza o outro lado da relação (dentro de ServicoPublico).
    }

    public Cidadao() {
    }

    public Cidadao(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public List<ServicoPublico> getServicos() {
        return servicos;
    }

    public void setServicos(List<ServicoPublico> servicos) {
        this.servicos = servicos;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public List<ServicoPublico> getServicosPublicos() {
        return servicos;
    }

    public void setServicosPublicos(List<ServicoPublico> servicosPublicos) {
        this.servicos = servicosPublicos;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDataUltimaAlteracao() {
        return dataUltimaAlteracao;
    }

    public void setDataUltimaAlteracao(Date dataUltimaAlteracao) {
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }
}
