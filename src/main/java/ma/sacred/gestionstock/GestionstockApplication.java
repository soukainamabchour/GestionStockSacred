package ma.sacred.gestionstock;

import ma.sacred.gestionstock.Dao.MelangeRepository;
import ma.sacred.gestionstock.Entities.Melange;
import ma.sacred.gestionstock.Services.IStockInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class GestionstockApplication implements CommandLineRunner {

    @Autowired
    private IStockInitService service;

    @Autowired
    private MelangeRepository melangeRepository;

    public static void main(String[] args) {
        SpringApplication.run(GestionstockApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        service.initMelangeRef();
        service.initMelangeEmplacement();
        service.initMelange();
      /* melangeRepository.findByLotContains("2").forEach(v->{
           System.out.println(v.getLot());
       });*/
        /*melangeRepository.findByReference_IdAndLotContains((long) 1, "2").forEach(n->{
            System.out.println(n.getId());
        });
        //11*/
    }
}
