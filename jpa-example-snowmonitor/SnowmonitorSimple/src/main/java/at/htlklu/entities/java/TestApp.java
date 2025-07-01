package at.htlklu.entities.java;

import at.htlklu.entities.Snowmeasurement;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.time.LocalDateTime;
import java.util.List;

public class TestApp {
    public static void main(String[] args) {
        /*
        Snowmeasurement s = SnowmeasurementHibernateDao.findById(1);
        System.out.println(s);
        System.out.println();
        System.out.println(s.getDatetimestamp());

        List<Snowmeasurement> c = SnowmeasurementHibernateDao.findAll();
        for (Snowmeasurement snow:c) {
            System.out.println(snow);
        }
         */

        //insert
        Snowmeasurement sNewTransient = new Snowmeasurement("sNewTransient",29.69, LocalDateTime.now());
        System.out.println("ID (sNewTransient): "+sNewTransient.getIdsnowmeasurement());
        SnowmeasurementHibernateDao.insert(sNewTransient);
        System.out.println("ID (sNewTransient): "+sNewTransient.getIdsnowmeasurement());
        System.out.println(sNewTransient);

        //update
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        Snowmeasurement s2 = em.find(Snowmeasurement.class, 3);
        System.out.println(s2);

        EntityTransaction et = em.getTransaction();
        et.begin();
            s2.setSnowheight(125.6);
            em.flush();
            et.commit();
        em.close();

        //delete Entity über DAO
        SnowmeasurementHibernateDao.delete(sNewTransient);

        //delete Entity by ID > in der Main: zuerst finbyID dann remove innerhabl des Context ... id=9
        em = JPAUtil.getEntityManagerFactory().createEntityManager();
        Snowmeasurement s3 = em.find(Snowmeasurement.class, 9);
        et = em.getTransaction();
        et.begin();
            em.remove(s3);
            et.commit();
        em.close();
    }
}

