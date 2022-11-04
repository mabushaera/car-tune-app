package baylor.edu.cartuning;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
@Entity
@Data
// This is a simulation of the Ingredients class
public class TuningSelection implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private final String id;
	private final String name;
	private final Type type;
	
	public enum Type{
		ENGINE, SUSPENSION, BODY, INTERIOR
	}
}
