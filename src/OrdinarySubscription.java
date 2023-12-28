public class OrdinarySubscription implements Subscription {
    @Override
    public double calculateDiscount(double ticketPrice) {
        return ticketPrice;
    }
}
