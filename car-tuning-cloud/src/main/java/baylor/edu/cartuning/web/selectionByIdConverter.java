package baylor.edu.cartuning.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import baylor.edu.cartuning.TuningSelection;
import baylor.edu.cartuning.data.TuningSelectionRepository;


@Component
public class selectionByIdConverter implements Converter<String, TuningSelection> {
	
	private TuningSelectionRepository tuningSelectionRepo;
	
	@Autowired
	public selectionByIdConverter(TuningSelectionRepository tuningSelectionRepo) {
	   this.tuningSelectionRepo = tuningSelectionRepo;
	}

	@Override
	public TuningSelection convert(String id) {
	  return tuningSelectionRepo.findById(id).orElse(null);
	}

//  private Map<String, TuningSelection> tuningSelectionMap = new HashMap<>();
//  
//  public selectionByIdConverter() {
//	tuningSelectionMap.put("ECU", new TuningSelection("ECU", "Engine Control Unit", Type.ENGINE));
//    tuningSelectionMap.put("CRB", new TuningSelection("CRB", "Carburetor", Type.ENGINE));
//    tuningSelectionMap.put("INS", new TuningSelection("INS", "Ignition System", Type.ENGINE));
//    
//	
//    tuningSelectionMap.put("FRB", new TuningSelection("FRB", "Fron and Rear Bumber", Type.BODY));
//    tuningSelectionMap.put("BML", new TuningSelection("BML", "Bumper Lips", Type.BODY));
//    tuningSelectionMap.put("BMS", new TuningSelection("BMS", "Bumper Splitter", Type.BODY));
//    tuningSelectionMap.put("FWV", new TuningSelection("FWV", "Fenders with Vents", Type.BODY));
//    
//    tuningSelectionMap.put("NIW", new TuningSelection("NIW","Nice WAX", Type.INTERIOR));
//    tuningSelectionMap.put("ALS", new TuningSelection("ALS","Add Light Strips", Type.INTERIOR));
//    tuningSelectionMap.put("FTH", new TuningSelection("FTH","Add a Fake Tow Hook", Type.INTERIOR));
//    tuningSelectionMap.put("AFS", new TuningSelection("AFS","Add a Fake Scoop", Type.INTERIOR));        	
//    
//    tuningSelectionMap.put("LSP", new TuningSelection("LSP","Leaf Springs", Type.SUSPENSION));
//    tuningSelectionMap.put("CSP", new TuningSelection("CSP","Coil Springs", Type.SUSPENSION));
//    tuningSelectionMap.put("TOB", new TuningSelection("TOB","Torison Bars", Type.SUSPENSION));
//    tuningSelectionMap.put("ASP", new TuningSelection("ASP","Air Springs", Type.SUSPENSION));
//  }
//  
//  @Override
//  public TuningSelection convert(String id) {
//    return tuningSelectionMap.get(id);
//  }

}
