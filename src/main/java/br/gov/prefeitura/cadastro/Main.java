package br.gov.prefeitura.cadastro;

import br.gov.prefeitura.cadastro.entidades.Cidadao;
import br.gov.prefeitura.cadastro.entidades.Endereco;
import br.gov.prefeitura.cadastro.entidades.ServicoPublico;
import br.gov.prefeitura.cadastro.service.Categoria;
import br.gov.prefeitura.cadastro.storage.CidadaoStorage;
import br.gov.prefeitura.cadastro.storage.ServicoPublicoStorage;
import br.gov.prefeitura.cadastro.util.JPAUtil;

import javax.persistence.EntityManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        cadastro();

        EntityManager em = JPAUtil.getEntityManager();
        CidadaoStorage cidadaoStorage = new CidadaoStorage(em);

        Cidadao c = cidadaoStorage.buscarPorId(1L);
        System.out.println(c.getCpf());

        List<Cidadao> todos = cidadaoStorage.buscarPorNome("Bruno");
        todos.forEach(c2 -> System.out.println(c2.getCpf()));

        Cidadao cidadaoComEndereco = cidadaoStorage.buscarComEnderecosPorId(1L);
        System.out.println("CPF: " + cidadaoComEndereco.getCpf());

        cidadaoComEndereco.getEnderecos().forEach(e ->
                System.out.println("Endereço: " + e.getLogradouro() + ", " + e.getCidade())
        );

        Cidadao cidadaoEnderecoServico = cidadaoStorage.buscarEnderecoServicoPorId(1L);
        System.out.println("Nome: " + cidadaoEnderecoServico.getNome());
        System.out.println("CPF: " + cidadaoEnderecoServico.getCpf());

        cidadaoEnderecoServico.getEnderecos().forEach(e -> System.out.println("Endereço: " + e.getLogradouro() + ", " + e.getBairro() + ", " + e.getCidade() + ", " + e.getCep() + ", " + e.getEstado()));
        cidadaoEnderecoServico.getServicos().forEach(s -> System.out.println("Serviços: " + s.getNome() + " (" + s.getCategoria() + ") " + s.getDataInicio()));
        em.close();
    }

    private static void cadastro() {
        try {
            Endereco endereco1 = new Endereco("Rua Edith Gaertner", "Vila Nova", "01010010", "Blumenau", "SC");
            Cidadao cidadao1 = new Cidadao("Bruno", "10110110100");
            Date dataInicio = new SimpleDateFormat("ddMMyyyy").parse("02062025");
            Date dataInicio2 = new SimpleDateFormat("ddMMyyyy").parse("09062025");
            ServicoPublico servico1 = new ServicoPublico("Hospitais", Categoria.SAUDE, dataInicio);
            ServicoPublico servico2 = new ServicoPublico("Educação Básica", Categoria.EDUCACAO, dataInicio2);
            cidadao1.adicionarEndereco(endereco1);
            cidadao1.adicionarServicos(servico1);
            cidadao1.adicionarServicos(servico2);

            EntityManager em = JPAUtil.getEntityManager();
            CidadaoStorage storageCidadao = new CidadaoStorage(em);
            ServicoPublicoStorage storageServico = new ServicoPublicoStorage(em);

            storageServico.cadastrar(servico1);
            storageServico.cadastrar(servico2);
            storageCidadao.cadastrar(cidadao1);

            cidadao1.setCpf("99999999999");
            storageCidadao.atualizar(cidadao1);
            em.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
