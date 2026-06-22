import { ApplicationConfig } from '@angular/core';
import { provideHttpClient, withFetch } from '@angular/common/http';
import { provideRouter } from '@angular/router';
import { ShowList } from './components/show-list/show-list';
import { ShowDetail } from './components/show-detail/show-detail';
import { ShowForm} from './components/show-form/show-form';

export const appConfig: ApplicationConfig = {
  providers: [
    provideHttpClient(withFetch()),
    provideRouter([
      { path: '', redirectTo: 'shows', pathMatch: "full" },
      { path: 'shows', component: ShowList },
      { path: 'shows/new', component: ShowForm },
      { path: 'shows/:id', component: ShowDetail},
      { path: 'shows/:id/edit', component: ShowForm},
    ]),
  ],
};
