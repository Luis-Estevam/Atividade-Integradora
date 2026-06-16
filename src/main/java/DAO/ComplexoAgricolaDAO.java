package DAO;

import Models.ComplexoAgricola;
import jakarta.persistence.EntityManager;
import java.util.List;

public class ComplexoAgricolaDAO {
    private EntityManager em;

    public ComplexoAgricolaDAO(EntityManager em) {
        this.em = em;
    }

    public void salvar(ComplexoAgricola c) {
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
    }

    public void atualizar(ComplexoAgricola c) {
        em.getTransaction().begin();
        em.merge(c);
        em.getTransaction().commit();
    }

    public void deletar(int id) {
        ComplexoAgricola c = buscarPorId(id);
        if (c != null) {
            em.getTransaction().begin();
            em.remove(c);
            em.getTransaction().commit();
        }
    }

    public ComplexoAgricola buscarPorId(int id) {
        return em.find(ComplexoAgricola.class, id);
    }

    public List<ComplexoAgricola> listarTodos() {
        return em.createQuery("SELECT c FROM ComplexoAgricola c", ComplexoAgricola.class).getResultList();
    }
}
