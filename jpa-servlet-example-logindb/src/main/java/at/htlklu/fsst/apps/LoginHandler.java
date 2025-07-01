package at.htlklu.fsst.apps;

import at.htlklu.fsst.entities.Comment;
import at.htlklu.fsst.entities.User;
import at.htlklu.fsst.persistence.LoginDAO;

public class LoginHandler {
    public static void main(String[] args) {

        //Gib alle User nach Namen sortiert aus
        for (User user : LoginDAO.getAllUsers()) {
            System.out.println(user);
        }

        //Gib alle User aus, die eine HTL-Emailadresse haben (d.h. …@htl-klu.at)
        for (User user : LoginDAO.getAllUsersWithHTLMail()) {
            System.out.println(user.getEmail());
        }

        //Gib alle Kommentare des Users mit der id ==8 aus
        for (Comment comment : LoginDAO.getCommentToUserID(8)) {
            System.out.println(comment);
        }

        //Erstelle in der DB einen neuen Kommentar für den User Victor Mühleder mit einem beliebigen, aber sinnvollen Text
        LoginDAO.insertCommentToUserName("Victor Mühleder", "Fix typo in documentation and update README.md");

        //Lösche alle Kommentare zu einem User unter Angabe seiner ID
        LoginDAO.deleteCommentsToUserID(3);

        //checkPassword
        //true
        System.out.println(LoginDAO.checkPassword("ABrünner","jyfsj447"));
        //false
        System.out.println(LoginDAO.checkPassword("ABrünner","root"));
    }
}
