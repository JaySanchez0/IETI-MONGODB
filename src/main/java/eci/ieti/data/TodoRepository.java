package eci.ieti.data;

import eci.ieti.data.model.Todo;
import eci.ieti.data.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoRepository extends CrudRepository<Todo,String> {

    List<Todo> findByResponsible(User responsible);

}
