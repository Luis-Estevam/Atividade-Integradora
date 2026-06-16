package DAO;

import Models.ClasseSolo;
import jakarta.persistence.EntityManager;
import java.util.List;

public class ClasseSoloDAO {
    private EntityManager em;

    public ClasseSoloDAO(EntityManager em) {
        this.em = em;
    }

    public void salvar(ClasseSolo c) {
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
    }

    public void atualizar(ClasseSolo c) {
        em.getTransaction().begin();
        em.merge(c);
        em.getTransaction().commit();
    }

    public void deletar(Integer id) {
        ClasseSolo c = buscarPorId(id);
        if (c != null) {
            em.getTransaction().begin();
            em.remove(c);
            em.getTransaction().commit();
        }
    }

    public ClasseSolo buscarPorId(Integer id) {
        return em.find(ClasseSolo.class, id);
    }

    public List<ClasseSolo> listarTodos() {
        return em.createQuery("SELECT c FROM ClasseSolo c", ClasseSolo.class).getResultList();
    }
}
