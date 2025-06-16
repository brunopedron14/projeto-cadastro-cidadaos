package br.gov.prefeitura.cadastro.storage;

import br.gov.prefeitura.cadastro.entidades.Endereco;

import javax.persistence.EntityManager;
import java.util.List;

public class EnderecoStorage {
    private EntityManager em;

    public EnderecoStorage (EntityManager em){
        this.em = em;
    }

    public void cadastrar(Endereco endereco){
        em.getTransaction().begin();
        this.em.persist(endereco);
        em.getTransaction().commit();
    }

    public void atualizar(Endereco endereco){
        this.em.merge(endereco);
    }

    public void deletar(Endereco endereco){
        em.getTransaction().begin();
        this.em.merge(endereco);
        this.em.remove(endereco);
        em.getTransaction().commit();
    }

    public List<Endereco> listarTodos(){
        return em.createQuery("SELECT e FROM Endereco e", Endereco.class).getResultList();
    }

}
