package baylor.edu.cartuning.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import baylor.edu.cartuning.TuningOrder;
import baylor.edu.cartuning.data.TuningOrderRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tuningOrder")
public class OrderController {

	private TuningOrderRepository orderRepo;

	@Autowired
	JmsTemplate jmsTemplate;

	@Value("${jms.queue2}")
	String jmsQueue;

	public OrderController(TuningOrderRepository orderRepo) {
		this.orderRepo = orderRepo;
	}

	@GetMapping("/current")
	public String orderForm() {
		return "orderForm";
	}

	@PostMapping
	public String processOrder(@Valid TuningOrder order, Errors errors, SessionStatus sessionStatus) {
		if (errors.hasErrors()) {
			return "orderForm";
		}

		log.info("Order submitted: {}", order);
		//jmsTemplate.convertAndSend(jmsQueue, order);
		orderRepo.save(order);
		sessionStatus.setComplete();

		return "redirect:/";
	}
	
	@PostMapping(value = "/sendMessage")
	public ResponseEntity<String> send(@RequestBody String message){
		jmsTemplate.convertAndSend(message);
		return new ResponseEntity<>("message sent: "+ message , HttpStatus.OK);
	}
}
