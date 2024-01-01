public class SportChannel extends TvChannel {

    int price = 10;
    public SportChannel(String channelName, String category, String language) {
        super(channelName, category, language);
    }

    @Override
    public int getPrice() {
        return super.getPrice() + price;
    }
}
