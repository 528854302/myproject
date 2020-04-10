package com.a528854302.service;

import com.a528854302.dao.MessageRepository;
import com.a528854302.entity.Message;
import com.a528854302.entity.ResponseResult;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class MessageService implements BaseService<Message>{
    @Autowired
    MessageRepository repository;
    @Autowired
    MongoTemplate template;

    public List<Message> findListByFromAndTo(Long fromId, Long toId, Integer page, Integer rows){
        Criteria fromCriterria =
                Criteria.where("from.id").is(fromId).and("to.id").is(toId);
        Criteria toCriterria =
                Criteria.where("to.id").is(fromId).and("from.id").is(toId);
        Criteria criteria = new Criteria().orOperator(fromCriterria, toCriterria);
        PageRequest pageRequest =
                PageRequest.of(page-1,rows, Sort.by(Sort.Direction.ASC,"send_date"));
        Query query = new Query(criteria).with(pageRequest);
        return template.find(query,Message.class);

    }

    @Override
    public List<Message> selectAll() {
        return repository.saveAll(null);
    }

    @Override
    public Message selectById(String id) {
        return repository.findById(id).get();
    }

    @Override
    public boolean update(Message message) {
        return repository.save(message)!=null?true:false;
    }

    @Override
    public boolean deleteById(String id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public boolean insert(Message message) {
        message.setId(ObjectId.get());
        message.setSendDate(new Date());
        message.setStatus(0);
        return repository.save(message)!=null?true:false;
    }

    public List<Message> readMessageList(Long fromId, Long toId, Integer page, Integer rows) {
        List<Message> list = this.findListByFromAndTo(fromId, toId, page, rows);
        for (Message message : list) {
            if (message.getStatus()==1){
                message.setStatus(1);
                this.update(message);
            }
        }
        return list;
    }
}
