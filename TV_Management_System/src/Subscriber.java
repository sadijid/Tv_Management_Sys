import java.io.Serializable;

public class Subscriber implements Serializable {

    private String fName;
    private String lName;
    private int phoneNumber;

    private String city;

    public Subscriber(String fName, String lName, int phoneNumber, String city) {
        this.fName = fName;
        this.lName = lName;
        this.phoneNumber = phoneNumber;
        this.city = city;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Subscriber() {
        super();
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", city='" + city + '\'' +
                '}';
    }
}