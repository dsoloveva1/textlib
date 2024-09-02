import { Component } from '@angular/core';
import { RegistrationService } from '../services/registration.service';
import { User } from '../models/user';

@Component({
  selector: 'app-registration-form',
  standalone: true,
  imports: [],
  templateUrl: './registration-form.component.html',
  styleUrl: './registration-form.component.css'
})
export class RegistrationFormComponent {
  user!: User;
  submitted: boolean = false;
  errorMessage!: string;

  constructor(private registrationService: RegistrationService) {
    this.user = new User();
   }

  createUser() {
    this.registartionService.register(this.user).subscribe({
      next: (res) => {
        this.submitted = true;
        this.errorMessage = '';
        console.log(res);
      },
      
      error: this.errorMessage = "Вы уже зарегистрированы или вы ввели пустые данные";
    });
  }
}
