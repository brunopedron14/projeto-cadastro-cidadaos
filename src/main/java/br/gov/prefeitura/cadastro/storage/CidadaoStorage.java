package br.gov.prefeitura.cadastro.storage;

import br.gov.prefeitura.cadastro.entidades.Cidadao;

import javax.persistence.EntityManager;
import java.util.List;

public class CidadaoStorage {
    private EntityManager em;

    public CidadaoStorage(EntityManager em){
        this.em = em;
    }

    public void cadastrar(Cidadao cidadao){
        em.getTransaction().begin();
        this.em.persist(cidadao);
        em.getTransaction().commit();
    }

    public void atualizar(Cidadao cidadao){
        em.getTransaction().begin();
        this.em.merge(cidadao);
        em.getTransaction().commit();

    }

    public void deletar(Cidadao cidadao){
        em.getTransaction().begin();
        this.em.merge(cidadao);
        this.em.remove(cidadao);
        em.getTransaction().commit();
    }

    public Cidadao buscarPorId(Long id){
        return em.find(Cidadao.class, id);
    }

    public List<Cidadao> buscarTodos(){
        String jpql = "SELECT c FROM Cidadao c";
        return em.createQuery(jpql, Cidadao.class).getResultList();
    }

    public List<Cidadao> buscarPorNome(String nome){
        String jpql = "SELECT c FROM Cidadao c WHERE c.nome = :nome";
        return em.createQuery(jpql, Cidadao.class).setParameter("nome", nome).getResultList();
    }

    public Cidadao buscarEnderecosPorId(Long id) {
        return em.createQuery(
                        "SELECT c FROM Cidadao c LEFT JOIN FETCH c.enderecos WHERE c.id = :id", Cidadao.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public Cidadao buscarEnderecoServicoPorId(Long id) {
        Cidadao cidadao = em.createQuery(
                        "SELECT c FROM Cidadao c LEFT JOIN FETCH c.enderecos WHERE c.id = :id",
                        Cidadao.class)
                .setParameter("id", id)
                .getSingleResult();

        cidadao.getServicos().size();

        return cidadao;
    }






}
