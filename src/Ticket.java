public class Ticket {
    private int id;
    private String movieName;
    private String date;
    private String time;
    private double price;
    private boolean isRefunded;

    public Ticket(int id, String movieName, String date, String time, double price) {
        this.id = id;
        this.movieName = movieName;
        this.date = date;
        this.time = time;
        this.price = price;
        this.isRefunded = false;

    }

    public int getId() {
        return id;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public double getPrice() {
        return price;
    }

    public boolean isRefunded() {
        return isRefunded;
    }

    public void setRefunded(boolean refunded) {
        isRefunded = refunded;
    }
}
