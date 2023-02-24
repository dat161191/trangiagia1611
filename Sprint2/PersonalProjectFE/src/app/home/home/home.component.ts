import {Component, OnInit} from '@angular/core';
import {ClockService} from '../../service/clock.service';
import {ClockInfoJson} from '../../clock-info-json';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  clockList!: ClockInfoJson;
  request = {page: 0, size: 8};
  pageNumber = 0;

  constructor(private clockService: ClockService) {
  }

  ngOnInit(): void {
    this.getAllList(this.request);

  }

  private getAllList(request: { page: number; size: number } | undefined): void {
    this.clockService.getListClock(request).subscribe(data => {
      this.clockList = data;
    });

  }
}
