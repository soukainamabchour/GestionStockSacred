package ma.sacred.gestionstock.Services;

import ma.sacred.gestionstock.Dao.*;
import ma.sacred.gestionstock.Entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.stream.Stream;

@Transactional
@Service
public class StockInitServiceImpl implements IStockInitService {

    @Autowired
    private MelangeEmplacementRepository melangeEmplacementRepository;
    @Autowired
    private MelangeRefRepository melangeRefRepository;
    @Autowired
    private MelangeRepository melangeRepository;


    @Override
    public void initMelangeEmplacement() {
        Stream.of("A1", "A2", "A3", "A4", "A5", "A6").forEach(v->{
            MelangeEmplacement melangeEmplacement =new MelangeEmplacement();
            melangeEmplacement.setEmplacement(v);
            melangeEmplacement.setEtat(false);
            melangeEmplacementRepository.save(melangeEmplacement);
        });
    }

    @Override
    public void initMelangeRef() {
        Stream.of("EPDM 8060", "EPDM 8052", "EPDM 868060", "EPDM 88423B", "EPDM 8370", "EPDM 862363",
                "EPDM 8374", "EPDM 8060GLI", "EPDM 8162", "EPDM 8760").forEach(v->{
            MelangeRef melangeRef=new MelangeRef();
            melangeRef.setReference(v);
            melangeRefRepository.save(melangeRef);
        });
    }

    @Override
    public void initMelange() {
        melangeRefRepository.findAll().forEach(melangeRef -> {
            //just testing this is wrong in reality
            melangeEmplacementRepository.findAll().forEach(emp -> {
                Melange melange = new Melange();
                melange.setReference(melangeRef);
                melange.setDateReception(new Date());
                melange.setDateFabrication(new Date());
                melange.setDateUtilisation(new Date());
                melange.setDimension(50);
                melange.getReference().getEmplacements().add(emp);
                melangeRepository.save(melange);
            });
        });
    }
}
