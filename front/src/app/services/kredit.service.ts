import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Credit } from '../models/credit';
import { Offer } from '../models/offer';
import { RequestCredit } from '../models/request';

@Injectable({
  providedIn: 'root'
})
export class KreditService {

  constructor(
    private http: HttpClient
  ) { }

  readonly API_REQUEST: string = `${environment.baseUrl}/${environment.apiRequest}`;
  readonly API_CREDIT: string = `${environment.baseUrl}/${environment.apiCredits}`;

  kamata(id: number): Observable<Offer>{
    return this.http.post(`${this.API_REQUEST}/${id}`, null).pipe(
      catchError(() => of(null))
    );
  }

  getAllForUser(): Observable<Credit[]>{
    return this.http.get<Credit[]>(this.API_CREDIT);
  }

  declineOffer(id: number): Observable<void>{
    return this.http.delete<void>(`${this.API_CREDIT}/${id}`);
  }
}
