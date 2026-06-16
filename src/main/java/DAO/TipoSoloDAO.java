package DAO;

import Models.TipoSolo;
import jakarta.persistence.EntityManager;
import java.util.List;

public class TipoSoloDAO {
    private EntityManager em;

    public TipoSoloDAO(EntityManager em) {
        this.em = em;
    }

    public void salvar(TipoSolo t) {
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
    }

    public void atualizar(TipoSolo t) {
        em.getTransaction().begin();
        em.merge(t);
        em.getTransaction().commit();
    }

    public void deletar(Integer id) {
        TipoSolo t = buscarPorId(id);
        if (t != null) {
            em.getTransaction().begin();
            em.remove(t);
            em.getTransaction().commit();
        }
    }

    public TipoSolo buscarPorId(Integer id) {
        return em.find(TipoSolo.class, id);
    }

    public List<TipoSolo> listarTodos() {
        return em.createQuery("SELECT t FROM TipoSolo t", TipoSolo.class).getResultList();
    }
}
