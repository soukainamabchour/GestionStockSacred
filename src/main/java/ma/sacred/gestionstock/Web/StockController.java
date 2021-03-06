package ma.sacred.gestionstock.Web;

import ma.sacred.gestionstock.Dao.MelangeEmplacementRepository;
import ma.sacred.gestionstock.Dao.MelangeRefRepository;
import ma.sacred.gestionstock.Dao.MelangeRepository;
import ma.sacred.gestionstock.Entities.Melange;
import ma.sacred.gestionstock.Entities.MelangeEmplacement;
import ma.sacred.gestionstock.Entities.MelangeRef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class StockController {

    @Autowired
    MelangeEmplacementRepository melangeEmplacementRepository;

    @Autowired
    MelangeRepository melangeRepository;

    @Autowired
    MelangeRefRepository melangeRefRepository;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String theme() {
        return "homepage";
    }


    /////////////////----------------------Reference Melange-----------------------////////////////
    ////////---------------Lister références------------///////////
    @GetMapping(path = "/melangeRef")
    public String listRefMelange(Model model,
                                 @RequestParam(name = "page", defaultValue = "0") int p,
                                 @RequestParam(name = "size", defaultValue = "5") int s,
                                 @RequestParam(name = "keyword", defaultValue = "") String kw) {
        Page<MelangeRef> melangesref = melangeRefRepository.findByReferenceContains(kw, PageRequest.of(p, s));
        model.addAttribute("melangeref", melangesref.getContent());
        model.addAttribute("pages", new int[melangesref.getTotalPages()]);
        model.addAttribute("currentPage", p);
        model.addAttribute("size", s);
        model.addAttribute("keyword", kw);
        return "listMelangeRef";
    }

    ////////------------------Ajouter référence------------////////////
    @RequestMapping(value = "/formMelangeRef", method = RequestMethod.GET)
    public String formMelangeRef(Model model) {
        model.addAttribute("melangeRef", new MelangeRef());
        return "formMelangeRef";
    }

    ////////------------------Enregistrer référence------------////////////
    @RequestMapping(value = "/addMelangeRef", method = RequestMethod.POST)
    public String addMelangeRef(@Valid MelangeRef melangeRef, BindingResult br, Model model) {
        model.addAttribute("melangeRef", melangeRef);
        if (br.hasErrors()) return "formMelangeRef";
        melangeRefRepository.save(melangeRef);
        return "saveMelangeRef";
    }

    ////////------------------Modifier référence------------////////////
    @RequestMapping(value = "/editMelangeRef", method = RequestMethod.GET)
    public String editMelangeRef(Model model, Long id) {
        MelangeRef melangeRef = melangeRefRepository.findById(id).get();
        model.addAttribute("melangeRef", melangeRef);
        return "formMelangeRef";
    }

    ////////------------------Supprimer référence------------////////////
    @RequestMapping(value = "/deleteMelangeRef", method = RequestMethod.POST)
    public String deleteMelangeRef(Long id, int page, int size) {
        melangeRefRepository.deleteById(id);
        return "redirect:/melangeRef?page=" + page + "&size=" + size + "";
    }

    /////////////////----------------------Reference Melange-----------------------////////////////
    /////////////////////////////////-----Lister mélanges----------////////////////////////////////
    @RequestMapping(value = "/melange", method = GET)
    public String listMelange(Model model,
                              @RequestParam(name = "page", defaultValue = "0") int p,
                              @RequestParam(name = "size", defaultValue = "5") int s,
                              @RequestParam(name = "ref_id") Long ref_id,
                              @RequestParam(name = "ref")String ref,
                              @RequestParam(name = "keyword", defaultValue ="") String kw
                              ) {
        Page<Melange> melange = melangeRepository.findByReference_IdAndLotContains(ref_id,kw, PageRequest.of(p, s));
        model.addAttribute("result", melange.getTotalElements());
        model.addAttribute("listMelange", melange.getContent());
        model.addAttribute("pages", new int[melange.getTotalPages()]);
        model.addAttribute("currentPage", p);
        model.addAttribute("size", p);
        model.addAttribute("ref_id", ref_id);
        model.addAttribute("ref", ref);
        model.addAttribute("keyword", kw);
        return "listMelange";
    }

    ////////------------------Ajouter mélange------------////////////
    @RequestMapping(value = "/formMelange", method = RequestMethod.GET)
    public String formMelange(Model model,
                              @RequestParam(name = "ref_id")Long id,
                             @RequestParam(name = "ref")String ref
                             // @RequestParam(name = "emp_id")Long emp_id
                              ) {
        List<MelangeEmplacement> emplacements=melangeEmplacementRepository.findByReference_Id(id);
        Melange melange=new Melange();
        model.addAttribute("melange", melange);
        model.addAttribute("ref_id", id);
        model.addAttribute("ref", ref);
        model.addAttribute("emplacements", emplacements);
        return "formMelange";
    }

    ////////------------------Enregistrer mélange------------////////////
    @RequestMapping(value = "/addMelange", method = RequestMethod.POST)
    public String addMelange(@ModelAttribute("selected_emp")MelangeEmplacement emp,
                             @Valid Melange melange, BindingResult br, Model model,
                             @RequestParam(name="ref_id")Long id,
                             @RequestParam(name = "s_emp")MelangeEmplacement s_emp) {
        MelangeRef reference=melangeRefRepository.findById(id).get();
        melange.setReference(reference);
        melange.setEmplacements(emp);
        model.addAttribute("melange", melange);
        model.addAttribute("ref_id", id);
        model.addAttribute("selected_emp", emp);
        melangeRepository.save(melange);
        return "saveMelange";
    }

    ////////------------------Modifier mélange------------////////////
    @RequestMapping(value = "/editMelange", method = RequestMethod.GET)
    public String editMelange(Model model, Long id,
                              @RequestParam(name = "ref_id")Long ref_id) {
        Melange melange= melangeRepository.findByIdAndReference_Id(id, ref_id);
        model.addAttribute("melange", melange);
        return "formMelange";
    }

    ////////------------------Supprimer mélange------------////////////
    @RequestMapping(value = "/deleteMelange", method = RequestMethod.POST)
    public String deleteMelange(Long id, int page, int size) {
        melangeRepository.deleteById(id);
        return "redirect:/melange?page=" + page + "&size=" + size + "";
    }

}
