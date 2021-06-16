import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { environment } from 'src/environments/environment';
import { catchError } from 'rxjs/operators';
import { RequestCredit } from '../models/request';
import { Nekretnina } from '../models/nekretnina';
import { Zirant } from '../models/zirant';
import { Hipoteka } from '../models/hipoteka';

@Injectable({
  providedIn: 'root'
})
export class RequestSequelService {

  constructor(
    private http: HttpClient
  ) { }

  readonly API_HIPOTEKA: string = `${environment.baseUrl}/${environment.apiHipoteka}`;
  readonly API_ZIRANT: string = `${environment.baseUrl}/${environment.apiUser}`;

  hipoteka(hipoteka: Hipoteka,id: number): Observable<RequestCredit>{
    return this.http.post<Hipoteka>(`${this.API_HIPOTEKA}/${id}`, hipoteka).pipe(
      catchError(() => of(null))
    );
  }

  zirant(zirant: Zirant): Observable<RequestCredit>{
    return this.http.post<Zirant>(this.API_ZIRANT, zirant).pipe(
      catchError(() => of(null))
    );
  }
}
