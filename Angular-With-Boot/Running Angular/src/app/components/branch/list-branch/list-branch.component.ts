import { Component, OnInit } from '@angular/core';
import { BranchModel } from '../../../models/branch.model';
import { BranchService } from '../../../services/branch.service';


@Component({
  selector: 'app-list-branch',
  templateUrl: './list-branch.component.html',
  styleUrls: ['./list-branch.component.css'],
})
export class ListBranchComponent implements OnInit {
  branches: BranchModel[] = [];

  constructor(private branchService: BranchService) {}

  ngOnInit(): void {
    this.branchService.getAllBranches().subscribe((data) => {
      this.branches = data;
    });
  }
}
