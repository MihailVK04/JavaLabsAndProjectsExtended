import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { ShowService } from '../../services/show';
import { Show } from '../../models/show'

@Component({
  selector: 'app-show-list',
  standalone: true,
  templateUrl: './show-list.html',
  styleUrl: './show-list.css',
})
export class ShowList {

  private readonly showService = inject(ShowService);
  private readonly router = inject(Router);

  readonly shows = this.showService.shows;
  readonly loading = this.showService.loading;
  readonly error = this.showService.error;

  goToDetail(show: Show): void {
    this.router.navigate(['/shows', show.id]);
  }

  goToAdd(): void {
    this.router.navigate(['/shows/new']);
  }

  goToEdit(show: Show): void {
    this.router.navigate(['/shows', show.id, 'edit']);
  }

  deleteShow(id: number): void {
    if (confirm('Are you sure you want to delete this show?')) {
      this.showService.delete(id);
    }
  }
}
