import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FileService {
  private baseUrl = 'http://localhost:8080/list/persons';

  constructor(private http: HttpClient) { }

  getFiles(page:Number , size: Number): Observable<any> {
    return this.http.get<any>(this.baseUrl+`?page=${page}&size=${size}`);
  }
}