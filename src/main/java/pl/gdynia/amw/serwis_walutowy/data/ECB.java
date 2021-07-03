package pl.gdynia.amw.serwis_walutowy.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ECB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String currencyFrom;
    private String currencyTo;
    private double rate;
    private String date;

    public ECB(String currencyFrom, String currencyTo, double rate, String date) {
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.rate = rate;
        this.date = date;
    }

    public ECB() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(String currency_from) {
        this.currencyFrom = currency_from;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(String currency_to) {
        this.currencyTo = currency_to;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", currency_from='" + currencyFrom + '\'' +
                ", currency_to='" + currencyTo + '\'' +
                ", rate=" + rate +
                ", date='" + date + '\'' +
                '}';
    }
}
