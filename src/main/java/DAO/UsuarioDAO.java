package DAO;

import Models.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import java.util.List;

public class UsuarioDAO {
    private EntityManager em;

    public UsuarioDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Usuario u) {
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
    }

    public boolean autenticar(String email, String senha) {
        try {
            Usuario u = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email AND u.senha = :senha", Usuario.class)
                    .setParameter("email", email)
                    .setParameter("senha", senha)
                    .getSingleResult();
            return u != null;
        } catch (NoResultException e) {
            return false;
        }
    }

    public List<Usuario> listarUsuarios() {
        return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
    }
}
