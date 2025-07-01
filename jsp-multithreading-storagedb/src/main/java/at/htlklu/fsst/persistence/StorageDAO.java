package at.htlklu.fsst.persistence;

import at.htlklu.fsst.entities.Part;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class StorageDAO {

    public static List<Part> getAllParts() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        return em.createQuery("select p from Part p", Part.class).getResultList();
    }

    private static Part findPartBySerialNr(String serialNr) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<Part> query = em.createQuery("select p from Part p where p.serialnr like ?1", Part.class);
        return query.setParameter(1, serialNr).getSingleResult();
    }

    public static String addPart(String serianNr, int count) {
        String returnMessage = "Message not set yet";

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();

        try {
            et.begin();
            Part part;
            try {
                part = findPartBySerialNr(serianNr);
            } catch (Exception e) {
                return "NAK";
            }

            if (count >= 0) {
                part.setCount(part.getCount() + count);
            } else {
                return "NAK";
            }

            em.merge(part);
            et.commit();
        } catch (Exception e) {
            return "NAK";
        } finally {
            returnMessage = "OK";
            if (et.isActive()) {
                et.rollback();
            }
            em.close();
        }
        return returnMessage;
    }

    public static String reducePart(String serianNr, int count) {
        String returnMessage = "Message not set yet";

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();

        try {
            et.begin();
            Part part;
            try {
                part = findPartBySerialNr(serianNr);
            } catch (Exception e) {
                return "NAK";
            }

            if (count >= 0) {
                if (count > part.getCount()) {
                    part.setCount(0);
                    return "NAK";
                } else {
                    part.setCount(part.getCount() - count);
                }
            } else {
                return "NAK";
            }

            em.merge(part);
            et.commit();
        } catch (Exception e) {
            return "NAK";
        } finally {
            returnMessage = "OK";
            if (et.isActive()) {
                et.rollback();
            }
            em.close();
        }
        return returnMessage;
    }

    public static List<Part> searchParts(String searchContent) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<Part> query = em.createQuery("select p from Part p where p.serialnr like ?1 or p.box like ?2 or p.partname like ?3", Part.class);
        return query.setParameter(1, "%" + searchContent + "%").setParameter(2,"%" + searchContent + "%").setParameter(3,"%" + searchContent + "%").getResultList();
    }
}
