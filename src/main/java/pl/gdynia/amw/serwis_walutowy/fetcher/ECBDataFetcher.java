package pl.gdynia.amw.serwis_walutowy.fetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import pl.gdynia.amw.serwis_walutowy.data.ECB;
import pl.gdynia.amw.serwis_walutowy.data.ECBRepo;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Component
@EnableScheduling
public class ECBDataFetcher {
    private static final String ECB_DATA_URL = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml?wsdl";
    private static final String CURRENCY_FROM = "EUR";

    private final ECBRepo ECBRepo;

    @Autowired
    public ECBDataFetcher(ECBRepo ECBRepo) {
        this.ECBRepo = ECBRepo;
    }

    @Scheduled(cron = "0 10 16 * * MON-FRI")
    public void getDataFromECB() {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(String.valueOf(new URI(ECB_DATA_URL)));

            doc.getDocumentElement().normalize();

            // get <Cube>
            NodeList list = doc.getElementsByTagName("Cube");
            String time =  doc.getElementsByTagName("Cube").item(1).getAttributes().getNamedItem("time").getNodeValue();
            for (int temp = 2; temp < list.getLength(); temp++) {

                Node node = list.item(temp);
                Element element = (Element) node;
                ECB ECB = new ECB(CURRENCY_FROM, element.getAttribute("currency"), Double.parseDouble(element.getAttribute("rate")), time);
                ECBRepo.save(ECB);
                }

        } catch (ParserConfigurationException | SAXException | IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
