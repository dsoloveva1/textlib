import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-login-form',
  standalone: true,
  imports: [],
  templateUrl: './login-form.component.html',
  styleUrl: './login-form.component.css'
})
export class LoginFormComponent  {
  errorMessage?: string;

  constructor(private loginService: LoginService, private router: Router) {
	this.user = new User();
  }

  login() {
	  this
     .loginService
     .login(this.user)
     .subscribe(next => {
        this.router.navigateByUrl("/records");
     }, error => {
        this.errorMessage = "Ошибочные данные";
     });
    
  }
  
}
