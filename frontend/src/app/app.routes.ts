import { Routes } from '@angular/router';
import {LayoutComponent} from "./core/layout/layout.component";
import {PlayersComponent} from "./pages/players/players.component";
export const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children: [
      {path: 'team/generation', component: PlayersComponent},
    ]
  }
];
