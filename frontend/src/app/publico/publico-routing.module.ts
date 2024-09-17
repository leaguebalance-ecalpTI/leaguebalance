import {PageWelcomeComponent} from "./page-welcome/page-welcome.component";
import {RouterModule, Routes} from "@angular/router";
import {NgModule, Output} from "@angular/core";

const routes: Routes = [
  {
    path: '',
    component: PageWelcomeComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PublicoRoutingModule {
}
