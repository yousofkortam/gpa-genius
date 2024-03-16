import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Request } from '../../Models/request';

@Injectable({
  providedIn: 'root'
})
export class GpaService {

  baseUrl: string = "http://localhost:8080";

  constructor(private httpClient: HttpClient) { }

  scrapGrades(request: Request): Observable<any> {
    return this.httpClient.post(`${this.baseUrl}/scrap-grades`, request);
  }
}
