package DAO;

import Models.FaixaSolo;
import jakarta.persistence.EntityManager;
import java.util.List;

public class FaixaSoloDAO {
    private EntityManager em;

    public FaixaSoloDAO(EntityManager em) {
        this.em = em;
    }

    public void salvar(FaixaSolo f) {
        em.getTransaction().begin();
        em.persist(f);
        em.getTransaction().commit();
    }

    public void atualizar(FaixaSolo f) {
        em.getTransaction().begin();
        em.merge(f);
        em.getTransaction().commit();
    }

    public void deletar(Integer id) {
        FaixaSolo f = buscarPorId(id);
        if (f != null) {
            em.getTransaction().begin();
            em.remove(f);
            em.getTransaction().commit();
        }
    }

    public FaixaSolo buscarPorId(Integer id) {
        return em.find(FaixaSolo.class, id);
    }

    public List<FaixaSolo> listarTodos() {
        return em.createQuery("SELECT f FROM FaixaSolo f", FaixaSolo.class).getResultList();
    }
}
