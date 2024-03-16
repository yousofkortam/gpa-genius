import { Component } from '@angular/core';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { GpaService } from '../../Services/gpa/gpa.service';
import { GradeResponse } from '../../Models/grade-response';
import { Request } from '../../Models/request';

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
  coursesFormArray: FormArray;

  constructor(
    private gpaService: GpaService,
    private fb: FormBuilder,
  ) {
    this.coursesFormArray = this.fb.array([
      this.fb.group({
        grade: [''],
        creditHours: [3]
      })
    ]);
    this.coursesForm = this.fb.group({
      html: [''],
      courses: this.coursesFormArray
    }, { validators: this.htmlCoursesValidator });
  }

  ngOnInit() { }

  calcGPA(coursesForm: FormGroup) {
    if (coursesForm.valid) {
      console.log(coursesForm.value);
      let request: Request = new Request(
        this.html?.value,
        this.courses.value
      );
      this.gpaService.scrapGrades(request).subscribe({
        next: (response: any) => {
          console.log(response);
          this.showResult = true;
          this.errorFound = false;
          this.response = response;
        },
        error: (error: any) => {
          console.log(error);
          this.showResult = false;
          this.errorFound = true;
          this.apiError = error.error;
        }
      });
    }
  }

  get html() {
    return this.coursesForm.get('html');
  }

  get courses() {
    return this.coursesForm.get('courses') as FormArray;
  }

  addNewCourse() {
    this.courses.push(
      this.fb.group({
        grade: [''],
        creditHours: [3]
      })
    );
  }

  removeCourse() {
    if (this.courses.controls.length > 1) {
      this.courses.removeAt(this.courses.controls.length - 1);
    }
  }

  showMinusIcon(): boolean {
    return this.courses.length > 1;
  }

  getGradeColor(): string {
    if (this.response != null) {
      let gpa = this.response?.termGPA;
      if (gpa < 2) {
        return 'red';
      } else if (gpa < 2.5) {
        return 'orange';
      } else {
        return 'green';
      }
    }
    return 'black';
  }

  getCommulitiveColor(): string {
    if (this.response != null) {
      let gpa = this.response?.cumulativeGPA;
      if (gpa < 2) {
        return 'red';
      } else if (gpa < 2.5) {
        return 'orange';
      } else {
        return 'green';
      }
    }
    return 'black';
  }

  htmlCoursesValidator(formGroup: FormGroup) {
    const html = formGroup.get('html')!.value;
    const courses = formGroup.get('courses') as FormArray;

    if (!html) {
      for (let course of courses.controls) {
        if (!course.value.grade || !course.value.creditHours) {
          return { invalidGrade: true };
        }
      }
    }
    return null;
  }

  closeResultPopup() {
    this.showResult = false;
  }

}
