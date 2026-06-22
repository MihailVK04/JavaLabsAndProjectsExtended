import { Injectable, inject, signal } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { catchError, of, tap, finalize, Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Show, PageResponse } from '../models/show';

@Injectable({
  providedIn: 'root',
})
export class ShowService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = environment.apiUrl;

  private readonly _shows = signal<Show[]>([]);
  readonly shows = this._shows.asReadonly();

  readonly loading = signal(false);
  readonly error = signal<string | null>(null);

  loadAll(): void {
    this.loading.set(true);
    this.error.set(null);

    const params = new HttpParams().set('page', 0).set('size', 50);

    this.http.get<PageResponse<Show>>(this.baseUrl, { params }).pipe(
      tap(res => this._shows.set(res.content)),
      catchError(() => {
        this.error.set('Failed to load shows.');
        return of([]);
      }),
      finalize(() => this.loading.set(false)),
    ).subscribe();
  }

  getById(id: number): Show | undefined {
    return this._shows().find(s => s.id === id);
  }

  create(show: Omit<Show, 'id'>): Observable<Show> {
    return this.http.post<Show>(this.baseUrl, show).pipe(
      tap(created => this._shows.update(list => [...list, created])),
    );
  }

  update(id: number, show: Omit<Show, 'id'>): Observable<Show> {
    return this.http.put<Show>(`${this.baseUrl}/${id}`, show).pipe(
      tap(updated => this._shows.update(list => list.map(s => s.id === id ? updated : s))),
    );
  }

  delete(id: number): void {
    this.http.delete<void>(`${this.baseUrl}/${id}`).pipe(
      tap(() => this._shows.update(list => list.filter(s => s.id !== id))),
      catchError(() => {
        this.error.set('Failed to delete show.');
        return of(null);
      }),
    ).subscribe();
  }
}
