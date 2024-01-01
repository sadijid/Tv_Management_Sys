public class MovieChannel extends TvChannel{
    int price=15;
    public MovieChannel(String channelName, String category, String language) {
        super(channelName, category, language);
    }

    @Override
    public int getPrice() {
        return super.getPrice() + price;
    }
}
