package at.htlklu.entities.java;

import at.htlklu.entities.MangadetailsEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class MangadbHibernateDao {
    public static List<MangadetailsEntity> findAll(){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        return em.createQuery("select e from MangadetailsEntity e").getResultList();
    }

    public static void insert(MangadetailsEntity m){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();

        try {
            et.begin();
            em.persist(m);
            em.flush();
            et.commit();
        } finally {
            if (et.isActive()) {
                et.rollback();
            }
            em.close();
        }
    }

    public static void update(MangadetailsEntity m){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();

        try {
            et.begin();
            m.setPages(200);
            em.merge(m);
            em.flush();
            et.commit();
        } finally {
            if (et.isActive()) {
                et.rollback();
            }
            em.close();
        }
    }

    public static void delete(int id){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();

        MangadetailsEntity tmpManga = em.find(MangadetailsEntity.class, id);

        try {
            et.begin();
            em.remove(em.contains(tmpManga) ? tmpManga : em.merge(tmpManga));
            em.flush();
            et.commit();
        } finally {
            if (et.isActive()){
                et.rollback();
            }
            em.close();
        }

    }
}
