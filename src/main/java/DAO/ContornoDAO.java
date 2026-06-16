package DAO;

import Models.Contorno;
import jakarta.persistence.EntityManager;
import java.util.List;

public class ContornoDAO {
    private EntityManager em;

    public ContornoDAO(EntityManager em) {
        this.em = em;
    }

    public void salvar(Contorno c) {
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
    }

    public void atualizar(Contorno c) {
        em.getTransaction().begin();
        em.merge(c);
        em.getTransaction().commit();
    }

    public void deletar(Integer id) {
        Contorno c = buscarPorId(id);
        if (c != null) {
            em.getTransaction().begin();
            em.remove(c);
            em.getTransaction().commit();
        }
    }

    public Contorno buscarPorId(Integer id) {
        return em.find(Contorno.class, id);
    }

    public List<Contorno> listarTodos() {
        return em.createQuery("SELECT c FROM Contorno c", Contorno.class).getResultList();
    }
}
