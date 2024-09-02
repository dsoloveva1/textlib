package com.example.textlib;

import com.example.textlib.repositories.RecordRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import com.example.textlib.models.Record;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RecordsCrudTest {
    @Autowired
    private RecordRepository recordRepository;

    @Test
    @Order(1)
    @Commit
    public void createRecordTest(){
        Record record = new Record(1L, "Some data");
        recordRepository.createRecord(record);
        Assertions.assertThat(record.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void findRecordByIdTest(){
        Record record = recordRepository.findById(1L);
        Assertions.assertThat(record.getId()).isEqualTo(1L);
    }

    @Test
    @Order(3)
    public void findRecordByIdAndDataTest(){
        Record record = recordRepository.findByIdAndData(new Record(1L, "Some data"));
        Assertions.assertThat(record.getId()).isEqualTo(1L);
    }

    @Test
    @Order(4)
    public void findAllRecordsTest(){
        List<Record> records = recordRepository.findAll();
        Assertions.assertThat(records.size()).isGreaterThan(0);
    }

    @Test
    @Order(5)
    @Commit
    public void updateEmployeeTest(){
        Record record = recordRepository.findById(1L);
        record.setData("New data");
        Record recordUpdated =  recordRepository.updateRecord(record);
        Assertions.assertThat(recordUpdated.getData()).isEqualTo("New data");
    }

    @Test
    @Order(5)
    @Commit
    public void deleteRecordTest(){
        recordRepository.deleteRecord(1L);
        Record deletedRecord = recordRepository.findById(1L);
        Assertions.assertThat(deletedRecord).isNull();
    }
}