package pl.gdynia.amw.serwis_walutowy.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ECBRepo extends CrudRepository<ECB, Long> {
    ECB findByCurrencyToAndDate(String currencyTo, String date);
}
