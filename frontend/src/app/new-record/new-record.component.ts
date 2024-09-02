import { Component } from '@angular/core';
import { RecordsService } from '../services/records.service';
import { Record } from '../models/record';

@@Component({
  selector: 'app-new-record',
  standalone: true,
  imports: [],
  templateUrl: './new-record.component.html',
  styleUrl: './new-record.component.css'
})
export class NewRecordComponent {
  record!: Record;
  errorMessage!: string;

  constructor(private recordsService: RecordsService) {
    this.record = new Record();
   }

  createRecord() {
    this.registartionService.register(this.record).subscribe({
      next: (res) => {
        this.errorMessage = '';
        console.log(res);
      },
      
      error: this.errorMessage = "Поле не должно быть пустым";
    });
  }
}
