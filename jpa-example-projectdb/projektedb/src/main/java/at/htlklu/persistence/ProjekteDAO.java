package at.htlklu.persistence;

import at.htlklu.entities.Arbeitspakete;
import at.htlklu.entities.Mitarbeiter;
import at.htlklu.entities.Projekte;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ProjekteDAO {
    public static List<Projekte> getAllProjekte() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        return em.createQuery("select e from Projekte e").getResultList();
    }

    public static List<Mitarbeiter> getAllMitarbeiter() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        return em.createQuery("select e from Mitarbeiter e").getResultList();
    }

    public static List<Arbeitspakete> getAllArbeitspakete() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        return em.createQuery("select e from Arbeitspakete e").getResultList();
    }

    public static List<Mitarbeiter> findAdress(String name) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<Mitarbeiter> query = em.createQuery("select e from Mitarbeiter e where e.nachname = ?1 ", Mitarbeiter.class);
        List<Mitarbeiter> mlist = query.setParameter(1, name).getResultList();
        em.close();
        return mlist;
    }

    public static List<String> getMostFrequentFirstname() {
        List<String> temp = null;
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        temp = em.createQuery("SELECT e.vorname FROM Mitarbeiter e GROUP BY e.vorname HAVING COUNT(e.vorname) > 1 ORDER BY COUNT(e.vorname) DESC").getResultList();
        em.close();
        return temp;
    }

    public static Mitarbeiter getYoungestMitarbeiter() {
        List<Mitarbeiter> tempList = null;
        Mitarbeiter tempM = null;
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        tempList = em.createQuery("select e from Mitarbeiter e order by e.gebdat DESC").getResultList();
        tempM = tempList.get(0);
        em.close();
        return tempM;
    }

    public static List<Projekte> getProjectByCode(String code) {
        List<Projekte> tempP = null;
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<Projekte> query = em.createQuery("select e from Projekte e where e.code = ?1", Projekte.class);
        tempP = query.setParameter(1, code).getResultList();
        em.close();
        return tempP;
    }
}
