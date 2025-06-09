package br.gov.prefeitura.cadastro.storage;

import br.gov.prefeitura.cadastro.entidades.Cidadao;
import br.gov.prefeitura.cadastro.entidades.ServicoPublico;

import javax.persistence.EntityManager;

public class ServicoPublicoStorage {
    private EntityManager em;

    public ServicoPublicoStorage(EntityManager em){
        this.em = em;
    }

    public void cadastrar(ServicoPublico servicoPublico){
        em.getTransaction().begin();
        em.persist(servicoPublico);
        em.getTransaction().commit();
    }

    public void atualizar(Cidadao cidadao){
        this.em.merge(cidadao);

    }

    public void deletar(ServicoPublico servicoPublico){
        em.getTransaction().begin();
        this.em.merge(servicoPublico);
        this.em.remove(servicoPublico);
        em.getTransaction().commit();
    }
}
