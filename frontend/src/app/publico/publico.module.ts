import { NgModule } from '@angular/core';
import {PageWelcomeComponent} from "./page-welcome/page-welcome.component";
import {PublicoRoutingModule} from "./publico-routing.module";

@NgModule({
  declarations: [PageWelcomeComponent],
  imports: [
    PublicoRoutingModule,
  ]
})
export class PublicoModule { }
