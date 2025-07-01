package at.htlklu.entities.java;

import at.htlklu.entities.Snowmeasurement;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class SnowmeasurementHibernateDao {
    public static Snowmeasurement findById(Integer idToFind) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        return em.find(Snowmeasurement.class, idToFind);
    }

    //Muss nicht zwischengespeichert werden. .getResultList liefert eine Liste zurück.
    public static List<Snowmeasurement> findAll() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        return em.createQuery("SELECT a FROM Snowmeasurement a", Snowmeasurement.class).getResultList();
    }

    //INSERT
    public static void insert(Snowmeasurement sTransient) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();

        try {
            et.begin();
            em.persist(sTransient);
            em.flush();
            et.commit();
        } finally { //finally wird immer ausgeführt nach einem try. Ein catch wird nur ausgeführt wenn ein fehler aufgetreten ist.
            if (et.isActive()){
                et.rollback();
            }
            em.close();
        }
    }

    //DELETE
    public static void delete(Snowmeasurement s) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();

        try {
            et.begin();
            //Frage an em: "gibt es Entity s in deinem Context? >> wenn ja, alles gut / wenn neinm dann zuerst Mergen"
            //Wenn Entity nich im managed-context >> und es folgt ein remove >> dann gibt es eine JAVA Exception:
            // ... illegalArgumentException, cannot remove detached object
            //em.remove(s);
            em.remove(em.contains(s) ? s : em.merge(s));
            em.flush();
            et.commit();
        } finally {
            if (et.isActive()){
                et.rollback();
            }
            em.close();
        }
    }

    //UPDATE
    public static void update (Snowmeasurement s){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();

        try {
            et.begin();
            em.merge(s);
            em.flush();
            et.commit();
        } finally {
            if (et.isActive()){
                et.rollback();
            }
            em.close();
        }
    }
    public static Snowmeasurement findByName() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        return null; //em.createQuery("SELECT a FROM Snowmeasurement a", Snowmeasurement.class);
    }
}
