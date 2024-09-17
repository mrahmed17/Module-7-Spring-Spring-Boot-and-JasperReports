import { Component, OnInit } from '@angular/core';
import { UserModel } from '../../../models/user.model';
import { UserService } from '../../../services/user.service';
import { faCog, faEdit, faEnvelope, faIdBadge, faPhone, faTrash, faUser, faUserPlus, faUsers, faUserTag } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-list-user',
  templateUrl: './list-user.component.html',
  styleUrl: './list-user.component.css',
})
export class ListUserComponent implements OnInit {
  faUsers = faUsers;
  faIdBadge = faIdBadge;
  faUser = faUser;
  faEnvelope = faEnvelope;
  faPhone = faPhone;
  faUserTag = faUserTag;
  faCog = faCog;
  faTrash = faTrash;
  faEdit = faEdit;
  faUserPlus = faUserPlus;

  users: UserModel[] = [];

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.userService.getAllUsers().subscribe((data) => {
      this.users = data;
    });
  }

  deleteUser(id: number): void {
    confirm('Are you sure want to delete?');
    this.userService.deleteUser(id).subscribe(() => {
      this.users = this.users.filter((user) => user.id !== id);
    });
  }
}