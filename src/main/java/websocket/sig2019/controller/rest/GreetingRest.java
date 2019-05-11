package websocket.sig2019.controller.rest;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import websocket.sig2019.data.model.Greeting;
import websocket.sig2019.data.repo.GreetingRepo;

@RestController
@RequestMapping("/posts")
public class GreetingRest {

	@Autowired
	private GreetingRepo greetingRepo;

	@RequestMapping(value = "/old/{page}", method = RequestMethod.GET)
	public List<Greeting> greeting(@PathVariable Integer  page) {

		return greetingRepo.findAllByOrderByCreateDateTimeDesc(PageRequest.of(page, 2));
	}
}
