import java.util.ArrayList;

public class CinemaSystem {
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Movie> movies = new ArrayList<>();
    private ArrayList<Ticket> soldTickets = new ArrayList<>();
    private int ticketIdCounter = 1;

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    private User currentUser;


    public void addMovie(String title, String genre, int ageRestriction, int duration, int releaseYear, String director) {
        Movie movie = new Movie(title, genre, ageRestriction, duration, releaseYear, director);
        movies.add(movie);
        System.out.println("Movie added: " + title);
    }


    public void showAllMovies() {
        System.out.println("Available Movies:");
        for (Movie movie : movies) {
            String movieInfo = String.format("'%s' - %s, dir. %s, %d minutes, %d, %d+",
                    movie.getTitle(), movie.getGenre(), movie.getDirector(),
                    movie.getDuration(), movie.getReleaseYear(), movie.getAgeRestriction());

            System.out.println(movieInfo);
        }
    }


    public void addUser(String name, int age, double balance, boolean isStudent, Subscription subscription) {
        User user = new User(name, age, balance, isStudent, subscription);
        users.add(user);
        System.out.println("User added. Hello, " + name + "!");
    }


    private double calculateTicketPrice(User user, Movie movie) {
        int basePrice = 15;
        int childDiscountAge = 13;
        int studentDiscountPrice = 12;

        double ticketPrice;

        if (user.getAge() < childDiscountAge) {
            ticketPrice = 9.0;
        } else if (user.isStudent()) {
            ticketPrice = studentDiscountPrice;
        } else {
            ticketPrice = basePrice;
        }

        ticketPrice = user.getSubscription().calculateDiscount(ticketPrice);

        return ticketPrice;

    }

    public void buyTicket(User user, Movie movie, String date, String time) {
        double ticketPrice = calculateTicketPrice(user, movie);
        if (user.getBalance() >= ticketPrice) {
            Ticket ticket = new Ticket(ticketIdCounter++, movie.getTitle(), date, time, ticketPrice);
            soldTickets.add(ticket);
            user.addToOrderHistory(ticket);
            user.setBalance(user.getBalance() - ticketPrice);
            System.out.println("Ticket purchased successfully!");
            System.out.println("New Balance: $" + user.getBalance());
        } else {
            System.out.println("Insufficient funds. Unable to purchase a ticket.");
        }
    }

    public void showUserOrderHistory() {
        if (currentUser != null) {
            System.out.println("Order History for " + currentUser.getName() + ":");
            for (Ticket ticket : currentUser.getOrderHistory()) {
                System.out.println("Ticket ID: " + ticket.getId() +
                        ", Movie: " + ticket.getMovieName() +
                        ", Date: " + ticket.getDate() +
                        ", Time: " + ticket.getTime() +
                        ", Price: $" + ticket.getPrice());
            }
        } else {
            System.out.println("No user selected. Please select a user first.");
        }
    }


    public User getCurrentUser() {
        return currentUser;
    }

    public User getUserByName(String name) {
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    public boolean refundTicket(int ticketId) {
        User currentUser = getCurrentUser();

        if (currentUser != null) {
            for (Ticket ticket : soldTickets) {
                if (ticket.getId() == ticketId && !ticket.isRefunded()) {
                    double refundAmount = ticket.getPrice();
                    currentUser.setBalance(currentUser.getBalance() + refundAmount);
                    ticket.setRefunded(true);
                    System.out.println("Refund processed. Amount refunded: $" + refundAmount);
                    System.out.println("New Balance: $" + currentUser.getBalance());
                    return true;
                }
            }
        }
        System.out.println("Ticket not found or already refunded. Please check the ticket ID and try again.");
        return false;
    }

}




