package websocket.sig2019.controller.msg;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import websocket.sig2019.data.model.Greeting;
import websocket.sig2019.data.model.HelloMessage;
import websocket.sig2019.data.repo.GreetingRepo;

@Controller
public class GreetingMsgController {

	@Autowired
	private GreetingRepo greetingRepo;

	@MessageMapping("/hello")
	@SendTo("/topic_toto/greetings")
	public Greeting greet(HelloMessage message,Principal p) throws Exception {
		Thread.sleep(1000); // simulated delay
		Greeting greeting = new Greeting(p.getName() , HtmlUtils.htmlEscape(message.getName()) + "!");
		greetingRepo.save(greeting);
		return greeting;
	}

	private void saveGreeting(Greeting greeting) {
		greetingRepo.save(greeting);
	}

}
