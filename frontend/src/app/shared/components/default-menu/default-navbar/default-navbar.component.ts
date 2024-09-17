import {Component, Input, Renderer2} from '@angular/core';
import {RouterLink, RouterLinkActive} from "@angular/router";
import {DefaultMenuList} from "../../../types/default-menu-list";
import {MatIcon} from "@angular/material/icon";
import {NgClass} from "@angular/common";

@Component({
  selector: 'app-default-navbar',
  standalone: true,
  imports: [
    RouterLink,
    MatIcon,
    RouterLinkActive,
    NgClass
  ],
  templateUrl: './default-navbar.component.html',
  styleUrl: './default-navbar.component.scss'
})
export class DefaultNavbarComponent {
  @Input() menuLists!: DefaultMenuList[];
  mode: 'light' | 'dark' = 'light';

  constructor(private renderer: Renderer2) {}

  toggleBackground() {
    if (this.mode === 'light') {
      this.mode = 'dark';
      this.renderer.addClass(document.body, 'dark-mode');
      this.renderer.removeClass(document.body, 'light-mode');
    } else {
      this.mode = 'light';
      this.renderer.addClass(document.body, 'light-mode');
      this.renderer.removeClass(document.body, 'dark-mode');
    }
  }
}
