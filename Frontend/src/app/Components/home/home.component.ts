import { Component } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup } from '@angular/forms';
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

  coursesForm: FormGroup;

  constructor(
    private gpaService: GpaService,
    private fb: FormBuilder
    ) { 
      this.coursesForm = this.fb.group({
        html: [''],
        grades: this.fb.array([this.fb.control('')])
      });
    }

  ngOnInit() { }

  calcGPA(coursesForm: FormGroup) {
    if (coursesForm.valid) {
      let html = coursesForm.value.html,
      coursesDegrees = coursesForm.value.grades.join(',');
      this.gpaService.scrapGrades(html, coursesDegrees).subscribe({
        next: (response) => {
          this.showResult = true;
          this.errorFound = false;
          this.response = response;
        },
        error: (error) => {
          this.showResult = false;
          this.errorFound = true;
          this.apiError = error.error;
        }
      });
    }
  }

  get grades() {
    return this.coursesForm.get('grades') as FormArray;
  }

  addNewCourse() {
    this.grades.push(this.fb.control(''));
  }

  removeCourse() {
    if (this.grades.controls.length > 1) {
      this.grades.removeAt(this.grades.controls.length - 1);
    }
  }

  showMinusIcon(): boolean {
    return this.grades.length > 1;
  }

}
