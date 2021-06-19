import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
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

  acceptOffer(id: number, dan: number): Observable<boolean>{
    return this.http.post(`${this.API_CREDIT}/${id}/${dan}`, null).pipe(
      map(() => true),
      catchError(() => of(false))
    );
  }

  declineOffer(id: number): Observable<boolean>{
    return this.http.delete<null>(`${this.API_CREDIT}/${id}`).pipe(
      map(() => true),
      catchError(() => of(false))
    );
  }
}
