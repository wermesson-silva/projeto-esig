package br.com.esig.crud.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.esig.crud.model.Tarefa;

@Stateless
public class TarefaDAO {

    @PersistenceContext
    private EntityManager em;

    public void salvar(Tarefa tarefa) {
        em.persist(tarefa);
    }

    public void atualizar(Tarefa tarefa) {
        em.merge(tarefa);
    }

    public void excluir(Tarefa tarefa) {
        em.remove(em.merge(tarefa));
    }
    
    public List<Tarefa> listar() {
        return em.createQuery("SELECT t FROM Tarefa t", Tarefa.class).getResultList();
    }

    public List<Tarefa> filtrar(String responsavel, Boolean concluida, Long id, String tituloDescricao) {
        String jpql = "SELECT t FROM Tarefa t WHERE 1=1";

        if (responsavel != null && !responsavel.isEmpty()) {
            jpql += " AND t.responsavel LIKE :responsavel";
        }
        if (concluida != null) {
            jpql += " AND t.concluida = :concluida";
        }
        
        if(id != null) {
        	jpql += " AND t.id = :id";
        }
        
        if(tituloDescricao != null) {
        	jpql += " AND (t.titulo LIKE :tituloDescricao OR t.descricao LIKE :tituloDescricao)";
        }

        TypedQuery<Tarefa> query = em.createQuery(jpql, Tarefa.class);

        if (responsavel != null && !responsavel.isEmpty()) {
            query.setParameter("responsavel", "%" + responsavel + "%");
        }
        if (concluida != null) {
            query.setParameter("concluida", concluida);
        }
        if (id != null) {
        	query.setParameter("id", id);
        }
        
        if(tituloDescricao != null) {
        	query.setParameter("tituloDescricao", "%" + tituloDescricao + "%");
        }

        return query.getResultList();
    }
}
