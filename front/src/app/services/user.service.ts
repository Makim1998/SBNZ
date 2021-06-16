import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Login } from 'src/app/models/login';
import { User } from 'src/app/models/user';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private http: HttpClient
  ) { }

  readonly API_AUTH: string = `${environment.baseUrl}/${environment.apiAuth}`;

  login(login: Login): Observable<User>{
    return this.http.post<User>(`${this.API_AUTH}/login`, login).pipe(
      catchError(() => of(null))
    );
  }

  register(user: User): Observable<User>{
    return this.http.post<User>(`${this.API_AUTH}/register`, user).pipe(
      catchError(() => of(null))
    );
  }

  getAllClients(): Observable<User[]>{
    return this.http.get<User[]>(`${environment.baseUrl}/users`);
  }

}