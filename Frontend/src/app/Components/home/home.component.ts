import { Component } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { GpaService } from '../../Services/gpa/gpa.service';
import { GradeResponse } from '../../Models/grade-response';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  response: GradeResponse | null = null;
  apiError: any;
  showResult: boolean = false;
  errorFound: boolean = false;

  loginForm: FormGroup = new FormGroup({
    html: new FormControl(null),
    grades: new FormControl(null)
  });

  constructor(private gpaService: GpaService) { }

  ngOnInit() { }

  getGrades(loginForm: FormGroup) {
    if (loginForm.valid) {
      let g: string = '';
      if (loginForm.value.grades != null) {
        g = loginForm.value.grades.replace(/\s/g, '');
      }
      this.gpaService.scrapGrades(loginForm.value.html, g).subscribe({
        next: (response) => {
          this.showResult = true;
          this.errorFound = false;
          this.response = response;
        },
        error: (error) => {
          this.errorFound = true;
          this.apiError = error.error;
        }
      });
    }
  }

}
