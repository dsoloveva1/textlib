import { Component, OnInit } from '@angular/core';  
import { RecordService } from '../records.service';  
import { Record } from '../models/record';  
import { Observable,Subject } from "rxjs";  
  
import {FormControl,FormGroup} from '@angular/forms';  
  
@Component({
  selector: 'app-records',
  standalone: true,
  imports: [],
  templateUrl: './records.component.html',
  styleUrl: './records.component.css'
}) 
export class RecordsComponent implements OnInit {  
  
 constructor(private recordService:RecordService) { }  
  
  recordsArray: any[] = [];  
  dtOptions: DataTables.Settings = {};  
  dtTrigger: Subject<any>= new Subject();  
  
  records: Observable<Record[]>;  
  record : Record=new Record();  
  deleteMessage=false;  
  recordlist:any;  
  isupdated = false;      
   
  
  ngOnInit() {  
    this.isupdated=false;  
    this.dtOptions = {  
      pageLength: 6,  
      stateSave:true,  
      lengthMenu:[[6, 16, 20, -1], [6, 16, 20, "All"]],  
      processing: true  
    };     
    this.recordService.findAll().subscribe(data =>{  
    this.records =data;  
    this.dtTrigger.next();  
    })  
  }  
    
  deleteRecord(id: number) {  
    this.recordService.deleteRecord(id)  
      .subscribe(  
        data => {  
          console.log(data);  
          this.deleteMessage=true;  
          this.recordService.findAll().subscribe(data =>{  
            this.records =data  
            })  
        },  
        error => console.log(error));  
  }  
  
  updateRecord(id: number){  
    this.recordService.findById(id)  
      .subscribe(  
        data => {  
          this.recordlist=data             
        },  
        error => console.log(error));  
  }  
  
  recordupdateform=new FormGroup({  
    record_data:new FormControl(),
	record_id:new FormControl()
  });  
  recordsearch=new FormGroup({  
    record_data:new FormControl(),
	record_id:new FormControl()
  });
  
  updateRec(updrec){  
    this.record=new Record();  
    this.record.id=this.RecordId.value;	
   this.record.data=this.RecordData.value;
  
   this.recordService.updateRecord(this.record).subscribe(  
    data => {       
      this.isupdated=true;  
      this.recordService.findAll().subscribe(data =>{  
        this.records =data  
        })  
    },  
    error => console.log(error));  
  }    
  searchRec(searchrec){  
    this.record=new Record();  
    this.record.id=this.RecordSearchId.value;	
   this.record.data=this.RecordSearchData.value;
  
   this.recordService.findByIdAndData(this.record).subscribe(  
    data => {           
        this.records = data
    },  
    error => console.log(error));  
  } 
  get RecordId(){  
    return this.recordupdateform.get('record_id');  
  } 
  get RecordData(){  
    return this.recordupdateform.get('record_data');  
  }  
  
 get RecordSearchId(){  
    return this.recordsearch.get('record_id');  
  } 
  get RecordSearchData(){  
    return this.recordsearch.get('record_data');  
  } 
  changeisUpdate(){  
    this.isupdated=false;  
  }  
}  
