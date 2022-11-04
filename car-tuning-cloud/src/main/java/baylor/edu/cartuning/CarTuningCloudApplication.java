package baylor.edu.cartuning;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import baylor.edu.cartuning.TuningSelection.Type;
import baylor.edu.cartuning.data.TuningSelectionRepository;
import baylor.edu.cartuning.data.UserRepository;


@SpringBootApplication
public class CarTuningCloudApplication {

  public static void main(String[] args) {
    SpringApplication.run(CarTuningCloudApplication.class, args);
  }
    
    
    @Bean
    public CommandLineRunner dataLoader(TuningSelectionRepository repo, UserRepository userRepo) {
      return new CommandLineRunner() {		
		@Override
		public void run(String... args) throws Exception {
			
			repo.save( new TuningSelection("ECU", "Engine Control Unit", Type.ENGINE));
			repo.save( new TuningSelection("CRB", "Carburetor", Type.ENGINE));
			repo.save( new TuningSelection("INS", "Ignition System", Type.ENGINE));
		    
			
			repo.save( new TuningSelection("FRB", "Front and Rear Bumber", Type.BODY));
			repo.save( new TuningSelection("BML", "Bumper Lips", Type.BODY));
			repo.save( new TuningSelection("BMS", "Bumper Splitter", Type.BODY));
			repo.save( new TuningSelection("FWV", "Fenders with Vents", Type.BODY));
		    
			repo.save( new TuningSelection("NIW","Nice WAX", Type.INTERIOR));
			repo.save( new TuningSelection("ALS","Add Light Strips", Type.INTERIOR));
			repo.save( new TuningSelection("FTH","Add a Fake Tow Hook", Type.INTERIOR));
			repo.save( new TuningSelection("AFS","Add a Fake Scoop", Type.INTERIOR));        	
		    
			repo.save( new TuningSelection("LSP","Leaf Springs", Type.SUSPENSION));
			repo.save( new TuningSelection("CSP","Coil Springs", Type.SUSPENSION));
			repo.save( new TuningSelection("TOB","Torison Bars", Type.SUSPENSION));
			repo.save( new TuningSelection("ASP","Air Springs", Type.SUSPENSION));
			
			User user = new User("moha", "1", "mohammad abu shaira", "sss", "ccc", "TX", "777", "999");
			userRepo.save(user);
			
		}
	};
    
  }

}
