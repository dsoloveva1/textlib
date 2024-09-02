package com.example.textlib.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import com.example.textlib.models.Record;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class RecordRepository {
    private MongoTemplate mongoTemplate;

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Record> findAll() {
        return mongoTemplate.findAll(Record.class);
    }
    public Record findById(Long id) {
        Criteria criteria = new Criteria();
        criteria = criteria.where("id").is(id);
        Query query = new Query(criteria);
        return mongoTemplate.findOne(query, Record.class);
    }

    public Record createRecord(Record record) {
        return mongoTemplate.insert(record);
    }

    public Record updateRecord(Record record) {
        return mongoTemplate.save(record);
    }

    public void deleteRecord(Long id) {
        mongoTemplate.remove(findById(id));
    }

    public Record findByIdAndData(Record record) {
        Criteria criteria = new Criteria();
        if (record.getId() != null) {
            criteria = criteria.and("id").is(record.getId());
        }
        if (record.getData() != null && !record.getData().isBlank()) {
            criteria = criteria.and("data").is(record.getData());
        }
        Query query = new Query(criteria);
        return mongoTemplate.findOne(query, Record.class);
    }
}
