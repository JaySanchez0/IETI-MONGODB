package eci.ieti.data;

import eci.ieti.data.model.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo,String> {
}
