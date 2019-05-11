package websocket.sig2019.data.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import websocket.sig2019.data.model.Greeting;

@Repository
public interface GreetingRepo extends PagingAndSortingRepository<Greeting,UUID>{
	List<Greeting> findAllByOrderByCreateDateTimeDesc(Pageable pageable);
}
