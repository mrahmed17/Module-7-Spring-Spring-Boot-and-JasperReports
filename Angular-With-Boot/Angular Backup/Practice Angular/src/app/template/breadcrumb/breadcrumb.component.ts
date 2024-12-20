import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { faChevronRight, faFolder, faHome } from '@fortawesome/free-solid-svg-icons';
import { filter } from 'rxjs';

@Component({
  selector: 'app-breadcrumb',
  templateUrl: './breadcrumb.component.html',
  styleUrl: './breadcrumb.component.css',
})
export class BreadcrumbComponent implements OnInit {
  faHome = faHome;
  faFolder = faFolder;
  faChevronRight = faChevronRight;

  breadcrumbs: Array<{ label: string; url: string }> = [];

  constructor(private router: Router, private route: ActivatedRoute) {
    this.router.events
      .pipe(filter((event) => event instanceof NavigationEnd))
      .subscribe(() => {
        this.breadcrumbs = this.buildBreadcrumb(this.route.root);
      });
  }

  ngOnInit(): void {}

  buildBreadcrumb(
    route: ActivatedRoute,
    url: string = '',
    breadcrumbs: Array<{ label: string; url: string }> = []
  ): Array<{ label: string; url: string }> {
    const children: ActivatedRoute[] = route.children;

    if (children.length === 0) {
      return breadcrumbs;
    }

    for (const child of children) {
      const routeURL: string = child.snapshot.url
        .map((segment) => segment.path)
        .join('/');
      if (routeURL !== '') {
        url += `/${routeURL}`;
      }

      breadcrumbs.push({ label: routeURL, url });
      return this.buildBreadcrumb(child, url, breadcrumbs);
    }

    return breadcrumbs;
  }
}
