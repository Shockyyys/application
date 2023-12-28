public class PremiumSubscription implements Subscription {
    @Override
    public double calculateDiscount(double ticketPrice) {
        return ticketPrice * 0.8;
    }
}
