package at.htlklu.entities.java;

import at.htlklu.entities.MangadetailsEntity;
import jakarta.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.List;

public class TestApp {
    public static void main(String[] args) {
        //findAll
        List<MangadetailsEntity> mangadetailsEntityList = MangadbHibernateDao.findAll();
        for (MangadetailsEntity m:mangadetailsEntityList) {
            System.out.println(m);
        }

        //insert
        MangadetailsEntity transientManga = new MangadetailsEntity("Alice in Borderland", 15.0, "Fantasy", 210, LocalDateTime.now());
        System.out.println("Not managed: "+transientManga);
        MangadbHibernateDao.insert(transientManga);
        System.out.println("managed: "+transientManga);

        //update
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        MangadetailsEntity updateManga = em.find(MangadetailsEntity.class, 3);
        System.out.println("Before Update: "+updateManga);
        MangadbHibernateDao.update(updateManga);
        System.out.println("After Update: "+updateManga);

        //delete
        //MangadbHibernateDao.delete(9);
    }
}
