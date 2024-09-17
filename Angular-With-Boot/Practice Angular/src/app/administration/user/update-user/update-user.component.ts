import { Component, OnInit } from '@angular/core';
import { UserModel } from '../../../models/user.model';
import { RoleEnum } from '../../../models/role.enum';
import { UserService } from '../../../services/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NotificationService } from '../../../services/notification.service';
import { faEnvelope, faPhone, faSave, faUser, faUserEdit, faUserTag } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrl: './update-user.component.css',
})
export class UpdateUserComponent implements OnInit {
  faUserEdit = faUserEdit;
  faUser = faUser;
  faEnvelope = faEnvelope;
  faPhone = faPhone;
  faUserTag = faUserTag;
  faSave = faSave;

  user: UserModel = new UserModel();
  roles = Object.values(RoleEnum);
  id!: number;

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router,
    private notificationService: NotificationService
  ) {}

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.userService.getUserById(this.id).subscribe((data) => {
      this.user = data;
    });
  }

  updateUser(): void {
    this.userService.updateUser(this.id, this.user).subscribe(() => {
      this.router.navigate(['/user/list']);
      this.notificationService.showNotify('User data updated successfully.');
    });
  }
}