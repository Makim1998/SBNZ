import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Transaction } from '../models/transaction';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  constructor(
    public http: HttpClient
  ) { }

  readonly API_TRANSACTIONS: string = `${environment.baseUrl}/${environment.apiTransactions}`;

  transaction(transaction: Transaction): Observable<Transaction>{
    return this.http.post<Transaction>(this.API_TRANSACTIONS, transaction).pipe(
      catchError(() => of(null))
    );
  }

  getAllForUser(): Observable<Transaction[]>{
    return this.http.get<Transaction[]>(this.API_TRANSACTIONS);
  }

}
