package at.htlklu.fsst.persistence;

import at.htlklu.fsst.entities.Comment;
import at.htlklu.fsst.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class LoginDAO {

    public static List<User> getAllUsers() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        return em.createQuery("select u from User u order by u.username asc", User.class).getResultList();
    }

    public static List<User> getAllUsersWithHTLMail() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<User> query = em.createQuery("select u from User u where u.email like ?1", User.class);
        return query.setParameter(1, "%@htl-klu.at").getResultList();
    }

    public static List<Comment> getCommentToUserID(Integer id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        List<Comment> comments = new ArrayList<>();

        for (User user : getAllUsers()) {
            for (Comment comment : user.getCommentsByID()) {
                if (comment.getUser().getId().equals(id)) {
                    comments.add(comment);
                }
            }
        }
        return comments;
    }

    private static List<User> getUserIDToName(String name) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        String[] nameParts = name.split(" ");
        String firstName = nameParts[0].trim();
        String surname = nameParts[1].trim();
        TypedQuery<User> query = em.createQuery("select u from User u where u.firstname like ?1 and u.surname like ?2", User.class);
        return query.setParameter(1, firstName).setParameter(2, surname).getResultList();
    }

    public static void insertCommentToUserName(String name, String comment) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        Integer userID = getUserIDToName(name).get(0).getId();

        try {
            et.begin();
            User u_in_context = em.find(User.class, userID);
            Comment c = new Comment(comment, u_in_context);
            em.flush();
            et.commit();
        } finally {
            if (et.isActive()) {
                et.rollback();
            }
            em.close();
        }
    }

    public static void deleteCommentsToUserID(Integer id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();

        try {
            et.begin();
            User u_in_context = em.find(User.class, id);
            if (u_in_context != null) {
                //geht wegen orphanRemoval = true in User sonst:
                /*
                for (Comment comment : comments) {
                    em.remove(comment);
                }
                 */
                u_in_context.getCommentsByID().clear();
            }
            em.flush();
            et.commit();
        } finally {
            if (et.isActive()) {
                et.rollback();
            }
            em.close();
        }
    }

    public static boolean checkPassword(String user, String password) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            TypedQuery<User> query = em.createQuery("select u from User u where u.username like ?1", User.class);
            User temp_user = query.setParameter(1, user).getSingleResult();
            return temp_user.getPassword().equals(password);
        } catch (NoResultException e) {
            return false;
        } finally {
            em.close();
        }
    }


    public static User getUserByUsername(String username) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<User> query = em.createQuery("select u from User u where u.username = ?1", User.class);
        return query.setParameter(1, username).getSingleResult();
    }
}
