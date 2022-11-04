package baylor.edu.cartuning;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Tune implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Date createdAt;
	
	@NotNull
	@Size(min = 5, message = "Name must be at least 5 characters long")
	private String name;
	
	private String notes;
	
	@ManyToMany()
	@NotNull
	@Size(min = 1, message = "You should select at least one tuning selection")
	private List<TuningSelection> tuningSelection;
	
	@PrePersist
	void createdAt() {
		this.createdAt = new Date();
	}
	
	public void addTuningSelection(TuningSelection tuningSelection) {
	    this.tuningSelection.add(tuningSelection);
	  }

}
