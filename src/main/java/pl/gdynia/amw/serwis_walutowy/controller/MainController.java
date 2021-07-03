package pl.gdynia.amw.serwis_walutowy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gdynia.amw.serwis_walutowy.converter.Convert;
import pl.gdynia.amw.serwis_walutowy.data.ECBRepo;


@RestController
public class MainController {
   private final ECBRepo ECBRepo;
    @Autowired
    public MainController(ECBRepo ECBRepo) {
        this.ECBRepo = ECBRepo;
    }

    @GetMapping("/")
    @ResponseBody
    public String index(){
        return "Welcome to my Currency Converter Service. <br>"+
        "If u want to use it write in adrress barr:<br>"+
                "/convert/currency_from/currency_to/date<br>"+
                "date format = year-month-day";

    }
    @GetMapping("/convert/{from}/{to}/{date}")
    public ResponseEntity<String> convertion(@PathVariable String from, @PathVariable String to, @PathVariable String date)
    {
        Convert convert = new Convert(ECBRepo);
        double rate = convert.getRate(from, to, date);
        if(rate==0)
            return new ResponseEntity<>("U should check your variables", HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<> ("from: "+from+" to:"+to+" date:"+date + " rate:"+rate, HttpStatus.OK);
    }
}
