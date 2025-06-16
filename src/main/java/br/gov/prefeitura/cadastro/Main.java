package br.gov.prefeitura.cadastro;

import br.gov.prefeitura.cadastro.entidades.Cidadao;
import br.gov.prefeitura.cadastro.entidades.Endereco;
import br.gov.prefeitura.cadastro.entidades.ServicoPublico;
import br.gov.prefeitura.cadastro.service.Categoria;
import br.gov.prefeitura.cadastro.storage.CidadaoStorage;
import br.gov.prefeitura.cadastro.storage.EnderecoStorage;
import br.gov.prefeitura.cadastro.storage.ServicoPublicoStorage;
import br.gov.prefeitura.cadastro.util.JPAUtil;

import javax.persistence.EntityManager;
import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        cadastro();

        // Cria EntityManager e instancias de storage para acessar o banco
        EntityManager em = JPAUtil.getEntityManager();
        CidadaoStorage cidadaoStorage = new CidadaoStorage(em);
        EnderecoStorage enderecoStorage = new EnderecoStorage(em);
        ServicoPublicoStorage servicoStorage = new ServicoPublicoStorage(em);

        // Consulta por ID do cidadão
        System.out.println("******** CONSULTA 1 ********");
        Cidadao c = cidadaoStorage.buscarPorId(1L);
        System.out.println(c.getCpf());
        System.out.println("-------------------------");

        // Consulta cidadãos pelo nome
        List<Cidadao> todos = cidadaoStorage.buscarPorNome("Bruno");
        System.out.println("******** CONSULTA 2 ********");
        todos.forEach(c2 -> System.out.println(c2.getCpf()));
        System.out.println("-------------------------");

        // Lista todos os endereços cadastrados
        List<Endereco> todosEndereco = enderecoStorage.listarTodos();
        System.out.println("******** CONSULTA 3 ********");
        for (Endereco e : todosEndereco) {
            System.out.println("Endereço:");
            System.out.println("Logradouro: " + e.getLogradouro());
            System.out.println("Bairro: " + e.getBairro());
            System.out.println("Cidade: " + e.getCidade());
            System.out.println("CEP: " + e.getCep());
            System.out.println("Estado: " + e.getEstado());
            System.out.println("-------------------------");
        }

        // Lista serviços ativos
        List<ServicoPublico> servicosAtivos = servicoStorage.listarServicosAtivos();
        System.out.println("******** CONSULTA 4 ********");
        for (ServicoPublico s : servicosAtivos) {
            System.out.println("Serviço: " + s.getNome());
            System.out.println("Categoria: " + s.getCategoria());
            System.out.println("Data de Início: " + s.getDataInicio());
            System.out.println("-------------------------");
        }

        // Busca cidadão com seus endereços
        Cidadao buscarEnderecosPorId = cidadaoStorage.buscarEnderecosPorId(1L);
        System.out.println("******** CONSULTA 5 ********");
        System.out.println("Nome: " + buscarEnderecosPorId.getNome());
        System.out.println("CPF: " + buscarEnderecosPorId.getCpf());

        buscarEnderecosPorId.getEnderecos().forEach(e -> {
            System.out.println("Endereço: " + e.getLogradouro() + ", " + e.getBairro() + ", " + e.getCidade());
            System.out.println("-------------------------");
        });
        // Busca cidadão com seus endereços e serviços
        Cidadao cidadaoEnderecoServico = cidadaoStorage.buscarEnderecoServicoPorId(1L);
        System.out.println("******** CONSULTA 6 ********");
        System.out.println("Nome: " + cidadaoEnderecoServico.getNome());
        System.out.println("CPF: " + cidadaoEnderecoServico.getCpf());

        cidadaoEnderecoServico.getEnderecos().forEach(e -> System.out.println("Endereço: " + e.getLogradouro() +
                ", " + e.getBairro() + ", " + e.getCidade() + ", " + e.getCep() + ", " + e.getEstado()));
        cidadaoEnderecoServico.getServicos().forEach(s -> System.out.println("Serviços: " + s.getNome() + " (" + s.getCategoria() + ") " + s.getDataInicio()));
        System.out.println("-------------------------");
        em.close();
    }

    private static void cadastro() {
        try {
            // Criando objetos para cadastrar
            Endereco endereco1 = new Endereco("Rua Edith Gaertner", "Vila Nova", "01010010", "Blumenau", "SC");
            Endereco endereco2 = new Endereco("Rua XV de novembro", "Centro", "20202020", "Blumenau", "SC");
            Cidadao cidadao1 = new Cidadao("Bruno", "10110110100");
            Cidadao cidadao2 = new Cidadao("Pedro", "12312312312");
            Date dataInicio = new SimpleDateFormat("ddMMyyyy").parse("02062025");
            Date dataInicio2 = new SimpleDateFormat("ddMMyyyy").parse("09062025");
            ServicoPublico servico1 = new ServicoPublico("Hospitais", Categoria.SAUDE, dataInicio);
            ServicoPublico servico2 = new ServicoPublico("Educação Básica", Categoria.EDUCACAO, dataInicio2);

            // Relacionando endereços e serviços ao cidadão
            cidadao1.adicionarEndereco(endereco1);
            cidadao1.adicionarEndereco(endereco2);
            cidadao1.adicionarServicos(servico1);
            cidadao1.adicionarServicos(servico2);

            // Criando EntityManager e storages para persistir
            EntityManager em = JPAUtil.getEntityManager();
            CidadaoStorage storageCidadao = new CidadaoStorage(em);
            ServicoPublicoStorage storageServico = new ServicoPublicoStorage(em);
            EnderecoStorage storageEndereco = new EnderecoStorage(em);

            // Persistindo os dados
            storageServico.cadastrar(servico1);
            storageServico.cadastrar(servico2);
            storageCidadao.cadastrar(cidadao1);
            storageCidadao.cadastrar(cidadao2);
            storageEndereco.cadastrar(endereco2);

            // Removendo um cidadão e atualizando CPF de outro
            storageCidadao.deletar(cidadao2);
            cidadao1.setCpf("99999999999");
            storageCidadao.atualizar(cidadao1);
            em.close();

        } catch (Exception e) {
            // Captura exceções como erro de parse de data ou falha no JPA
            e.printStackTrace();
        }
    }
}
