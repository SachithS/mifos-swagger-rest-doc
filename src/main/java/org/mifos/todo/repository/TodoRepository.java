package org.mifos.todo.repository;

import org.mifos.todo.domain.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Sachith Senarathne
 * <p>Mongo repository for the app which deal with mongodb</p>
 */
public interface TodoRepository extends MongoRepository<Todo, String> {

}
