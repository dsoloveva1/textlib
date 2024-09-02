import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../model/user';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class LoginService {
	private loginUrl: string;

  constructor(private http: HttpClient) {
	this.loginUrl = 'http://localhost:8080/login';
  }

  public login(user: User) {
	 let headers = new Headers({ 
      'Authorization': 'Basic ' + btoa(loginDetails.login + ':' + loginDetails.pass),
      'X-Requested-With': 'XMLHttpRequest'
    });

    let options = new RequestOptions({ 
      headers: headers 
    });

    return this.http.post<User>(this.registUrl, user);
  }
}
