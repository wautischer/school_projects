package POJO;

public class Participant {
    private String name;
    private String gender;
    private String country;
    private String category;
    private String finishTime;

    public Participant(String name, String gender, String country, String category, String finishTime) {
        this.name = name;
        this.gender = gender;
        this.country = country;
        this.category = category;
        this.finishTime = finishTime;
    }

    public Participant() {
    }

    public Participant getParticipantFromCSV(String line) {
        String [] tokens = line.split(";");
        Participant participant = new Participant();
        participant.setName(tokens[0]);
        participant.setGender(tokens[1]);
        participant.setCountry(tokens[2]);
        participant.setCategory(tokens[3]);
        participant.setFinishTime(tokens[4]);
        return participant;
    }

    @Override
    public String toString() {
        return name +";"+gender+";"+country+";"+category+";"+finishTime+";";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }
}
