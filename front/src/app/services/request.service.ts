import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { catchError } from 'rxjs/operators';
import { RequestCredit } from '../models/request';


@Injectable({
  providedIn: 'root'
})
export class RequestService {

  constructor(
    private http: HttpClient
  ) { }

  readonly API_REQUEST: string = `${environment.baseUrl}/${environment.apiRequest}`;

  request(request: RequestCredit): Observable<RequestCredit>{
    return this.http.post<RequestCredit>(this.API_REQUEST, request).pipe(
      catchError(() => of(null))
    );
  }
}
