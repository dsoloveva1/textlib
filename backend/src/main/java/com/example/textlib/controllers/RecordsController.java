package com.example.textlib.controllers;

import com.example.textlib.models.Record;
import com.example.textlib.services.RecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class RecordsController {
    private final RecordService recordService;

    public RecordsController(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping("/records")
    public ResponseEntity<?> getAllRecords() {
        try {
            List<Record> records = recordService.findAll();
            return new ResponseEntity<>(records, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/records/{id}")
    public ResponseEntity<?> getRecordById(@PathVariable Long id) {
        try {
            Record record = recordService.findById(id);
            return new ResponseEntity<>(record, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/records/search")
    public ResponseEntity<?> getRecordsByIdAndData(@RequestBody Record record) {
        try {
            Record resultRecord = recordService.findByIdAndData(record);
            return new ResponseEntity<>(resultRecord, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/records/new")
    public ResponseEntity<?> createRecord(@RequestBody Record record) {
        try {
            Record newRecord = recordService.createRecord(record);
            return new ResponseEntity<>(newRecord, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/records")
    public ResponseEntity<?> updateRecord(@RequestBody Record record) {
        try {
            Record updatedRecord = recordService.updateRecord(record);
            return new ResponseEntity<>(updatedRecord, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/records/delete-record/{id}")
    public ResponseEntity<?> deleteRecord(@RequestBody Long id) {
        try {
            recordService.deleteRecord(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
