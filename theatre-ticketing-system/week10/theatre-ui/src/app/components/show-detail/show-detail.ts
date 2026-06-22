import { Component, inject, signal, OnInit } from '@angular/core';
import { Router, ActivatedRoute} from '@angular/router';
import { ShowService } from '../../services/show';
import { Show } from '../../models/show';

@Component({
  selector: 'app-show-detail',
  standalone: true,
  templateUrl: './show-detail.html',
  styleUrl: './show-detail.css',
})
export class ShowDetail implements OnInit {
  private readonly router = inject(Router);
  private readonly route = inject(ActivatedRoute);
  private readonly showService = inject(ShowService);

  readonly show = signal<Show | undefined>(undefined);

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.show.set(this.showService.getById(id));
  }

  getBack(): void {
    this.router.navigate(['/shows']);
  }

  goToEdit(show: Show):void {
    this.router.navigate(['/shows', show.id, 'edit']);
  }
}
