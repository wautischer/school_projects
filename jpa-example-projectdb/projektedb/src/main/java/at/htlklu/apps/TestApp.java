package at.htlklu.apps;

import at.htlklu.entities.Arbeitspakete;
import at.htlklu.entities.MaApZuord;
import at.htlklu.entities.Mitarbeiter;
import at.htlklu.entities.Projekte;
import at.htlklu.persistence.JPAUtil;
import at.htlklu.persistence.ProjekteDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class TestApp {
    public static void main(String[] args) {
        List<Projekte> projekteList = ProjekteDAO.getAllProjekte();
        for (Projekte p : projekteList) {
            System.out.println(p);
        }

        //region Geben Sie alle Mitarbeiter aus.
        List<Mitarbeiter> mitarbeitersList = ProjekteDAO.getAllMitarbeiter();
        for (Mitarbeiter m : mitarbeitersList) {
            System.out.println(m);
        }
        //endregion

        //region Wo wohnt Mitarbeiterin X > soll als Übergabeparameter der Methode übergeben werden?
        List<Mitarbeiter> mitarbeiterAdress = ProjekteDAO.findAdress("Ollag");
        for (Mitarbeiter m : mitarbeiterAdress) {
            System.out.println(m.getStrasse());
        }
        //endregion

        //region Welche Vornamen kommen bei den Mitarbeitern öfter als einmal vor?
        List<String> mostFrequentFirstname = ProjekteDAO.getMostFrequentFirstname();
        for (String s : mostFrequentFirstname) {
            System.out.println(s);
        }
        //endregion

        //region Wer ist der jüngste Mitarbeiter, geben Sie dessen Daten aus?
        Mitarbeiter youngest = ProjekteDAO.getYoungestMitarbeiter();
        System.out.println(youngest);
        //endregion

        //region Welche Bezeichnung haben die Arbeitspakete des Projekts mit dem Code X > X soll als Übergabeparameter der Methode übergeben werden?
        List<Projekte> projekteByCodeList = ProjekteDAO.getProjectByCode("FSST");
        for (Projekte p : projekteByCodeList) {
            System.out.println(p.getArbeitspaketesByNr());
        }
        //endregion

        //region Geben Sie zu allen Mitarbeitern deren Arbeitspaketen aus, an denen Sie beteiligt sind.
        List<Mitarbeiter> ma_list_all = ProjekteDAO.getAllMitarbeiter();
        for (Mitarbeiter m:ma_list_all) {
            Set<MaApZuord> maApZuordSet = m.getMaApZuordsById();
            for (MaApZuord ma:maApZuordSet) {
                System.out.println(m.getId() + "|" + m.getNachname() + "|" + m.getVorname() + "|" + ma.getArbeitspaketeByApId());
            }
        }
        //endregion

        //region Geben Sie zu allen Mitarbeitern deren Projekte aus, an denen Sie beteiligt sind.
        List<Mitarbeiter> ma_list_all2 = ProjekteDAO.getAllMitarbeiter();
        for (Mitarbeiter m:ma_list_all2) {
            Set<MaApZuord> maApZuordSet = m.getMaApZuordsById();
            for (MaApZuord maap:maApZuordSet) {
                Arbeitspakete ap = maap.getArbeitspaketeByApId();
                System.out.println(m.getId() + "|" + m.getNachname() + "|" + m.getVorname() + "|" + ap.getProjekteByPrNr());
            }
        }
        //endregion

        //region Wie heißen die Projektmanager der Projekte und wie viel Zeit wurde für das Projektmanagement aufgewendet?
            //nativeQuery
        //endregion

        //region Fügen Sie einen neuen Mitarbeiter ein UND ordnen Sie (im state „managed“) ihm ein bestehendes Arbeitspaket zu.
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();

        Arbeitspakete ap_in_context;

        try {
            et.begin();

            //Arbeitspaket holen
            ap_in_context = em.find(Arbeitspakete.class, 4);

            //Mitarbeiter hinzufügen
            Mitarbeiter mitarbeiter = new Mitarbeiter("Wautischer via Add","Laurin","m",
                    LocalDate.parse("2006-04-19"), "Linsengasse", "9020", "Klagenfurt", "Kärnten");
            em.persist(mitarbeiter);

            //MaApZuord
            MaApZuord maApZuord = new MaApZuord(12,LocalDate.now(),"",mitarbeiter,ap_in_context);

            em.flush();
            et.commit();
        } finally {
            if (et.isActive()){
                et.rollback();
            }
            em.close();
        }
        //endregion

        //region Bringen Sie ein Projekt in den state „managed“ und fügen Sie ein neues Arbeitspaket ein.
        EntityManager em2 = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et2 = em2.getTransaction();

        Projekte p_in_context;

        try {
            et2.begin();
            //Manage Projekt
            p_in_context = em2.find(Projekte.class, 1);
            Arbeitspakete ap = new Arbeitspakete("FSST", "Programmieren",p_in_context);
            //em2.persist(ap); nicht notwendig wegen der addArbeitspaket Methode in Projekte!
            em2.flush();
            et2.commit();
        } finally {
            if (et2.isActive()) {
                et2.rollback();
            }
            em2.close();
        }
        //endregion

        //region get All Arbeitspakete
        List<Arbeitspakete> all_ap = ProjekteDAO.getAllArbeitspakete();
        for (Arbeitspakete ap : all_ap) {
            System.out.println(ap.toString());
        }
        //endregion
    }
}
