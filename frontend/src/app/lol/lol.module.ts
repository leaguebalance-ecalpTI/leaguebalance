import { NgModule } from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {HomeComponent} from "./componentes/home/home.component";
import {InicioComponent} from "./componentes/inicio/inicio.component";
import {LolRoutingModule} from "./lol-routing.module";
import {MatButtonToggle} from "@angular/material/button-toggle";
import {MatButton, MatIconButton} from "@angular/material/button";
import {MatIcon} from "@angular/material/icon";
import {DefaultNavbarComponent} from "../shared/components/default-menu/default-navbar/default-navbar.component";



@NgModule({
  declarations: [ HomeComponent,
    InicioComponent,],
  imports: [
    CommonModule,
    LolRoutingModule,
    MatButtonToggle,
    MatButton,
    MatIconButton,
    MatIcon,
    NgOptimizedImage,
    DefaultNavbarComponent
  ]
})
export class LolModule { }
