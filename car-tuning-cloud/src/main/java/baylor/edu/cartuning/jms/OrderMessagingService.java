package baylor.edu.cartuning.jms;

import baylor.edu.cartuning.TuningOrder;

public interface OrderMessagingService {

  void sendOrder(TuningOrder order);
  
}
