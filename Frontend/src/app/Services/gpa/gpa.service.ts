import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GpaService {

  baseUrl: string = "http://localhost:8080";

  constructor(private httpClient: HttpClient) { }

  scrapGrades(html: string, grades: string): Observable<any> {
    const headers = new HttpHeaders().set('Content-Type', 'text/plain');
    const params = { grades: grades };
    return this.httpClient.post(`${this.baseUrl}/scrap-grades`, html, { headers, params });
  }
}
