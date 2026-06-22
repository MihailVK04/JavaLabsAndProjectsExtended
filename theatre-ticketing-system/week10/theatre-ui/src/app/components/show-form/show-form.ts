import { Component, inject, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { ShowService } from '../../services/show';
import { Genre, AgeRating } from '../../models/show';

@Component({
  selector: 'app-show-form',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './show-form.html',
  styleUrl: './show-form.css',
})
export class ShowForm implements OnInit {

  private readonly showService = inject(ShowService);
  private readonly router = inject(Router);
  private readonly route = inject(ActivatedRoute);

  private editId: number | null = null;

  readonly genres = Object.values(Genre);
  readonly ageRatings = Object.values(AgeRating);

  formData = {
    title: '',
    description: '',
    genre: Genre.DRAMA,
    durationMinutes: 90,
    ageRating: AgeRating.ALL,
  };

  get isEditMode(): boolean {
    return this.editId !== undefined;
  }

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam) {
      this.editId = Number(idParam);
      const show = this.showService.getById(this.editId);
      if (show) {
        this.formData = {
          title: show.title,
          description: show.description,
          genre: show.genre,
          durationMinutes: show.durationMinutes,
          ageRating: show.ageRating,
        };
      }
    }
  }

  onSubmit(isValid: boolean | null): void {
    if (!isValid) return;

    if (this.editId !== null) {
      this.showService.update(this.editId, this.formData).subscribe({
        next: updated => this.router.navigate(['/shows', updated.id]),
        error: () => this.showService.error.set('Failed to update show.'),
      });
    } else {
      this.showService.create(this.formData).subscribe({
        next: created => this.router.navigate(['/shows', created.id]),
        error: () => this.showService.error.set('Failed to create show.'),
      });
    }
  }

  cancel(): void {
    this.router.navigate(['/shows']);
  }
}
