import {Component, OnInit} from '@angular/core';
import {Chart} from 'chart.js';

@Component({
  selector: 'app-statistical',
  templateUrl: './statistical.component.html',
  styleUrls: ['./statistical.component.css']
})
export class StatisticalComponent implements OnInit {
  chart = null;

  constructor() {
  }

  ngOnInit(): void {
    // @ts-ignore
    this.chart = new Chart('myChart1', {
      type: 'bar',
      data: {
        labels: ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6',
          'Tháng 7', 'Tháng 8', 'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12'],
        datasets: [{
          label: 'Tổng thu nhập',
          data: [25000000, 36500000, 5500000, 50000000, 28000000, 16500000,
            9000000, 65500000, 60000000, 72000000, 64500000, 60500000],
          backgroundColor: '#fa0234',
          borderColor: '#fa0234',
          borderWidth: 2,
          fill: false,
        }]
      }
    })
  }

}
