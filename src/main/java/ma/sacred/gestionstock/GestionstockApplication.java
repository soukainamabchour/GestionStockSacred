package ma.sacred.gestionstock;

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

    public static void main(String[] args) {
        SpringApplication.run(GestionstockApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        service.initMelangeRef();
        service.initMelangeEmplacement();
        service.initMelange();

    }
}
