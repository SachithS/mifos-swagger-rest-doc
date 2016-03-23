package org.mifos.todo.repository;

import org.mifos.todo.domain.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Sachith Senarathne
 *
 */
public interface TodoRepository extends MongoRepository<Todo, String> {

}
