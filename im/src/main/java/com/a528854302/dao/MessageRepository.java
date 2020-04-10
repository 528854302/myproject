package com.a528854302.dao;

import com.a528854302.entity.Message;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface MessageRepository extends MongoRepository<Message,String> {


}
