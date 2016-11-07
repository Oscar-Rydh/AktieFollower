/**
 * Created by calle on 2016-11-07.
 */
public class SharevilleUser {
    private String user;
    private String aktie;
    private double price;

    public SharevilleUser(String user, String aktie, double price){
        this.user = user;
        this.aktie = aktie;
        this.price = price;
    }

    public String getUser(){
        return user;
    }

    public String getAktie(){
        return aktie;
    }

    public Double getPrice(){
        return price;
    }
}
