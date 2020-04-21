package com.a528854302.service;


import com.a528854302.entity.Message;
import com.a528854302.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService{
    @Autowired
    MessageRepository repository;
    @Autowired
    MongoTemplate template;

    public List<Message> findListByFromAndTo(String fromId, String toId, Integer page, Integer rows){
        Criteria fromCriterria =
                Criteria.where("from.id").is(fromId).and("to.id").is(toId);
        Criteria toCriterria =
                Criteria.where("to.id").is(fromId).and("from.id").is(toId);
        Criteria criteria = new Criteria().orOperator(fromCriterria, toCriterria);
        PageRequest pageRequest =
                PageRequest.of(page-1,rows, Sort.by(Sort.Direction.DESC,"send_date"));
        Query query = new Query(criteria).with(pageRequest);
        return template.find(query,Message.class);
    }



    public List<Message> selectAll() {
        return repository.saveAll(null);
    }


    public Message selectById(String id) {
        Optional<Message> optional = repository.findById(id);
        if (optional.isPresent()){
            return optional.get();
        }
        return null;
    }


    public boolean update(Message message) {
        return repository.save(message)!=null?true:false;
    }


    public boolean deleteById(String id) {
        repository.deleteById(id);
        return true;
    }


    public boolean insert(Message message) {
        message.setSendDate(new Date());
        message.setStatus(0);
        return repository.save(message)!=null?true:false;
    }

    public List<Message> readMessageList(String fromId, String toId, Integer page, Integer rows) {
        List<Message> list = this.findListByFromAndTo(fromId, toId, page, rows);
        for (Message message : list) {
            if (message.getStatus()==0 && message.getFrom().getId().equals(toId)){
                message.setStatus(1);
                this.update(message);
            }
        }
        return list;
    }
}
