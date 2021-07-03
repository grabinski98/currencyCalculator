package pl.gdynia.amw.serwis_walutowy.converter;
import pl.gdynia.amw.serwis_walutowy.data.ECBRepo;


public class Convert {
    private final ECBRepo ECBRepo;

    public Convert(ECBRepo ECBRepo) {
        this.ECBRepo = ECBRepo;
    }

    public double getRate(String from, String to, String date) {
        try {

            from = from.toUpperCase();
            to = to.toUpperCase();

            if (from.equals("EUR"))
                return ECBRepo.findByCurrencyToAndDate(to.toUpperCase(), date).getRate();

            double fromRate = ECBRepo.findByCurrencyToAndDate(from, date).getRate();
            if(to.equals("EUR"))
                return 1/fromRate;

            double toRate = ECBRepo.findByCurrencyToAndDate(to, date).getRate();
            return toRate / fromRate;
        }
        catch (NullPointerException e) {
            return 0;
        }
    }
}
