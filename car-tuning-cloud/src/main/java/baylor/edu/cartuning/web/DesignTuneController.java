package baylor.edu.cartuning.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import baylor.edu.cartuning.Tune;
import baylor.edu.cartuning.TuningOrder;
import baylor.edu.cartuning.TuningSelection;
import baylor.edu.cartuning.TuningSelection.Type;
import baylor.edu.cartuning.data.TuningSelectionRepository;

import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tuningOrder")
public class DesignTuneController {
	
	private final TuningSelectionRepository tuningSelectionRepo;

	@Autowired
	public DesignTuneController(TuningSelectionRepository tuningSelectionRepo) {
	  this.tuningSelectionRepo = tuningSelectionRepo;
	}
	
	
	  @ModelAttribute
	  public void addTuningSelectionsToModel(Model model) {
	    List<TuningSelection> tuningSelection = new ArrayList<>();
	    tuningSelectionRepo.findAll().forEach(i -> tuningSelection.add(i));

	    Type[] types = TuningSelection.Type.values();
	    for (Type type : types) {
	      model.addAttribute(type.toString().toLowerCase(),
	          filterByType(tuningSelection, type));
	    }
	  }
	
//	@ModelAttribute
//	public void addTuningSelectionsToModel(Model model) {
//		List<TuningSelection> tuningSelection = new ArrayList<>();
//		tuningSelection.add(new TuningSelection("ECU","Engine Control Unit",Type.ENGINE));
//		tuningSelection.add(new TuningSelection("CRB","Carburetor",Type.ENGINE));
//		tuningSelection.add(new TuningSelection("INS","Ignition System",Type.ENGINE));
//		
//		tuningSelection.add(new TuningSelection("FRB","Fron and Rear Bumber",Type.BODY));
//		tuningSelection.add(new TuningSelection("BML","Bumper Lips",Type.BODY));
//		tuningSelection.add(new TuningSelection("BMS","Bumper Splitter",Type.BODY));
//		tuningSelection.add(new TuningSelection("FWV","Fenders with Vents",Type.BODY));
//		
//		tuningSelection.add(new TuningSelection("NIW","Nice WAX",Type.INTERIOR));
//		tuningSelection.add(new TuningSelection("ALS","Add Light Strips",Type.INTERIOR));
//		tuningSelection.add(new TuningSelection("FTH","Add a Fake Tow Hook",Type.INTERIOR));
//		tuningSelection.add(new TuningSelection("AFS","Add a Fake Scoop",Type.INTERIOR));
//		
//		tuningSelection.add(new TuningSelection("LSP","Leaf Springs",Type.SUSPENSION));
//		tuningSelection.add(new TuningSelection("CSP","Coil Springs",Type.SUSPENSION));
//		tuningSelection.add(new TuningSelection("TOB","Torison Bars",Type.SUSPENSION));
//		tuningSelection.add(new TuningSelection("ASP","Air Springs",Type.SUSPENSION));
//		
//		Type[] types = TuningSelection.Type.values();
//		for(Type type: types) {
//			model.addAttribute(type.toString().toLowerCase(),filterByType(tuningSelection, type));
//		}
//		
//	}
	
	
	
	@ModelAttribute(name = "tuningOrder")
	public TuningOrder order() {
		return new TuningOrder();
	}

	@ModelAttribute(name = "tune")
	public Tune tune() {
	  return new Tune();
	}

	  @GetMapping
	  public String showDesignForm() {
	    return "design";
	  }



	@PostMapping
	public String processTune(@Valid Tune tune, Errors errors,
			  				  @ModelAttribute TuningOrder tuningOrder) {
	
	    if (errors.hasErrors()) {
	      return "design";
	    }
	
	    tuningOrder.addTuning(tune);
		log.info("Processing tune: {}", tune);
		
		return "redirect:/orders/current";
	}

	
	private Iterable<TuningSelection> filterByType(
		      List<TuningSelection> tuningSelection, Type type) {
		    return tuningSelection
		              .stream()
		              .filter(x -> x.getType().equals(type))
		              .collect(Collectors.toList());
	}

}
