package at.htlklu.pojo;

public class DisplayEntry {
    private String departureTime;
    private String source;
    private String train;
    private String arrivalTime;
    private String destination;

    public DisplayEntry(String departureTime, String source, String train, String arrivalTime, String destination) {
        this.departureTime = departureTime;
        this.source = source;
        this.train = train;
        this.arrivalTime = arrivalTime;
        this.destination = destination;
    }

    @Override
    public String toString() {
        return departureTime + " " + source + " " + train + " " + arrivalTime + " " + destination;
    }

    public DisplayEntry() {}

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getTrain() {
        return train;
    }

    public void setTrain(String train) {
        this.train = train;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
