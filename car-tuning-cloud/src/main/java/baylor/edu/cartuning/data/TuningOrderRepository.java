package baylor.edu.cartuning.data;

import org.springframework.data.repository.CrudRepository;

import baylor.edu.cartuning.TuningOrder;

public interface TuningOrderRepository extends CrudRepository<TuningOrder, Long> {

}
