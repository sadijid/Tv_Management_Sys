public class CartoonChannel extends TvChannel{
    int price = 20;
    public CartoonChannel(String channelName, String category, String language) {
        super(channelName, category, language);
    }

    @Override
    public int getPrice() {
        return super.getPrice() + price;
    }
}
