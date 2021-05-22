package com.springcourse.jwd.hardziyevich.app.repos;

import com.springcourse.jwd.hardziyevich.app.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {

    List<Message> findByTag(String tag);

}
