import { Component, OnInit } from '@angular/core';
import { NotificationService } from '../../services/notification.service';
import { faCheckCircle } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrl: './notification.component.css',
})
export class NotificationComponent implements OnInit {
  faCheckCircle = faCheckCircle;

  message: string = '';

  constructor(private notificationService: NotificationService) {}

  ngOnInit(): void {
    this.notificationService.notification$.subscribe((message) => {
      this.message = message;
      setTimeout(() => (this.message = ''), 3000); // Clear the message after 3 seconds
    });
  }
}
