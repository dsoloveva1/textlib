import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../model/user';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class RegistrationService {
	private registUrl: string;

  constructor(private http: HttpClient) {
    this.registUrl = 'http://localhost:8080/registration';
  }

  public register(user: User) {
    return this.http.post<User>(this.registUrl, user);
  }
}
