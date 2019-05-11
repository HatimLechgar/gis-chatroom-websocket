package websocket.sig2019.data.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Greeting {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String content;
	private String userName;
	
	@CreationTimestamp
	private LocalDateTime createDateTime;

	public Greeting() {
	}

	public Greeting(String userName, String content) {
		this.content = content;
		this.userName = userName;
	}

	public String getContent() {
		return content;
	}

	public String getUserName() {
		return userName;
	}

	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}
}
