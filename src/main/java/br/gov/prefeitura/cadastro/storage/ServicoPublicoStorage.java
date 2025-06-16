package br.gov.prefeitura.cadastro.storage;

import br.gov.prefeitura.cadastro.entidades.ServicoPublico;

import javax.persistence.EntityManager;
import java.util.List;

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

    public void atualizar(ServicoPublico servicoPublico){
        this.em.merge(servicoPublico);

    }

    public void deletar(ServicoPublico servicoPublico){
        em.getTransaction().begin();
        this.em.merge(servicoPublico);
        this.em.remove(servicoPublico);
        em.getTransaction().commit();
    }

    public List<ServicoPublico> listarServicosAtivos() {
        return em.createQuery("SELECT s FROM ServicoPublico s WHERE s.ativo = true", ServicoPublico.class)
                .getResultList();
    }
}
