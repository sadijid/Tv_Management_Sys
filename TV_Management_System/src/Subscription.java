import java.io.Serializable;

public class Subscription implements Serializable {
    private int installFee;

    private int nbTv;
     private Subscriber subscriber;
     private SubscriptionCycle subscriptionCycle;

     private String date;

     private int totalFee;

    public Subscription(int nbTv, Subscriber subscriber, SubscriptionCycle subscriptionCycle, String date) {
        this.installFee = nbTv * 10;
        this.nbTv = nbTv;
        this.subscriber = subscriber;
        this.subscriptionCycle = subscriptionCycle;
        this.date = date;
    }

    public int getInstallFee() {
        return installFee;
    }

    public void setInstallFee(int installFee) {
        this.installFee = installFee;
    }

    public int getNbTv() {
        return nbTv;
    }

    public void setNbTv(int nbTv) {
        this.nbTv = nbTv;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public SubscriptionCycle getSubscriptionCycle() {
        return subscriptionCycle;
    }

    public void setSubscriptionCycle(SubscriptionCycle subscriptionCycle) {
        this.subscriptionCycle = subscriptionCycle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTotalFee(){
        totalFee = installFee + 5;
        return totalFee;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "installFee=" + installFee +
                ", nbTv=" + nbTv +
                ", subscriber=" + subscriber +
                ", subscriptionCycle=" + subscriptionCycle +
                ", date='" + date + '\'' +
                '}';
    }
}
