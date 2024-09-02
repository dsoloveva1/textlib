import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Record } from '../model/record';
import { Observable } from 'rxjs/Observable';
import { ActivatedRoute, Router } from '@angular/router';

@Injectable()
export class RecordsService {
	private recordsUrl: string;

  constructor(private http: HttpClient) {
	this.recordsUrl = 'http://localhost:8080/records';
  }
  
  public findAll(): Observable<Record[]> {
    return this.http.get<Record[]>(this.recordsUrl);
  }
  public findById(id: number): Observable<Record> {
    return this.http.get<Record>(this.recordsUrl + '/'+id);
  }
  public findByIdAndData(record: Record): Observable<Record[]> {
    return this.http.get<Record[]>(this.recordsUrl+'/search');
  }
   
  public createRecord(record: Record): Observable<Record>{
    return this.http.post<Record>(this.recordsUrl+'/new', record);
  }
  
  public updateRecord(record: Record): Observable<Record>  {
    return this.http.post<Record>(this.recordsUrl);
  }
  
  public deleteRecord(id: number): Observable<any>  {
    return this.http.delete(this.recordsUrl+'/delete-record/'+id, { responseType: 'text' });
  }
}
