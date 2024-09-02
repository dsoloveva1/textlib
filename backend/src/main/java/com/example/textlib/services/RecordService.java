package com.example.textlib.services;

import com.example.textlib.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.textlib.models.Record;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class RecordService {
    private final RecordRepository recordRepository;

    @Autowired
    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public List<Record> findAll() {
        return recordRepository.findAll();
    }
    public Record findById(Long id) {
        return recordRepository.findById(id);
    }

    public Record createRecord(Record record) {
        return recordRepository.createRecord(record);
    }

    public Record updateRecord(Record record) {
        return recordRepository.updateRecord(record);
    }

    public void deleteRecord(Long id) {
        recordRepository.deleteRecord(id);
    }

    public Record findByIdAndData(Record record) {
        return recordRepository.findByIdAndData(record);
    }
}
