package ma.sacred.gestionstock.Web;

import ma.sacred.gestionstock.Dao.MelangeEmplacementRepository;
import ma.sacred.gestionstock.Dao.MelangeRefRepository;
import ma.sacred.gestionstock.Dao.MelangeRepository;
import ma.sacred.gestionstock.Entities.Melange;
import ma.sacred.gestionstock.Entities.MelangeRef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
public class StockController {

    @Autowired
    MelangeEmplacementRepository melangeEmplacementRepository;

    @Autowired
    MelangeRepository melangeRepository;

    @Autowired
    MelangeRefRepository melangeRefRepository;

    @RequestMapping(value="/theme", method = RequestMethod.GET)
    public String theme(){
        return "theme";
    }

   @GetMapping(path="/melanges")
    public String listMelange(Model model,
                              @RequestParam(name = "page", defaultValue = "0")int p,
                              @RequestParam(name = "size", defaultValue = "5")int s,
                              @RequestParam(name = "kw", defaultValue = "")String kw){
       Page<Melange> melanges=melangeRepository.findAll(PageRequest.of(p,s));
       model.addAttribute("listMelange", melanges.getContent());
       model.addAttribute("pages", new int[melanges.getTotalPages()]);
       model.addAttribute("currentPage", p);
       model.addAttribute("keyword", kw);
       return "listMelange";
   }

   /////////////////----------------------Reference Melange-----------------------////////////////
                   ////////---------------Lister références------------///////////
   @GetMapping(path= "/melangeRef")
   public String listRefMelange(Model model,
                                @RequestParam(name="page", defaultValue ="0")int p,
                                @RequestParam(name = "size", defaultValue = "5")int s,
                                @RequestParam(name = "keyword", defaultValue = "")String kw){
       Page<MelangeRef> melangesref=melangeRefRepository.findByReferenceContains(kw, PageRequest.of(p,s));
       model.addAttribute("melangeref", melangesref.getContent());
       model.addAttribute("pages", new int[melangesref.getTotalPages()]);
       model.addAttribute("currentPage", p);
       model.addAttribute("size", s);
       model.addAttribute("keyword", kw);
       return "listMelangeRef";
   }
                ////////------------------Ajouter référence------------////////////
    @RequestMapping(value="/formMelangeRef", method=RequestMethod.GET)
    public String formMelangeRef(Model model){
        model.addAttribute("melangeRef", new MelangeRef());
        return "formMelangeRef";
    }
    ////////------------------Enregistrer référence------------////////////
    @RequestMapping(value = "/addMelangeRef", method = RequestMethod.POST)
    public String addMelangeRef(@Valid MelangeRef melangeRef, BindingResult br, Model model){
        model.addAttribute("melangeRef", melangeRef);
        if(br.hasErrors()) return "formMelangeRef";
        melangeRefRepository.save(melangeRef);
        return "saveMelangeRef";
    }
    ////////------------------Modifier référence------------////////////
    @RequestMapping(value="/editMelangeRef", method = RequestMethod.GET)
    public String editMelangeRef(Model model, Long id){
        MelangeRef melangeRef=melangeRefRepository.findById(id).get();
        model.addAttribute("melangeRef", melangeRef);
        return "formMelangeRef";
    }

    ////////------------------Supprimer référence------------////////////
    @RequestMapping(value = "/deleteMelangeRef", method = RequestMethod.POST)
    public String deleteMelangeRef(Long id ,int page, int size){
        melangeRefRepository.deleteById(id);
        return "redirect:/melangeRef?page=" + page + "&size=" + size +"";
    }


}
