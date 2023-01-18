import {Component, OnInit} from '@angular/core';
import {Chart} from 'chart.js';
import {Post} from "./interface/post";
import {PostService} from "./post.service";

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {
  monthList: number[] = [];
  yearList: number[] = [];
  yearStart = 2022;
  currentYear: number;
  currentMonth: number;
  postList: Post[] = [];
  count1: number = 0;
  count2: number = 0;
  count3: number = 0;
  count4: number = 0;
  count5: number = 0;
  count6: number = 0;
  count7: number = 0;
  count8: number = 0;
  count9: number = 0;
  count10: number = 0;
  count11: number = 0;
  count12: number = 0;
  totalTransaction: number = 0;
  countSuccess: number = 0;
  countTotal: number = 0;


  constructor(private postService: PostService) {
    this.currentMonth = new Date().getMonth() + 1;
    this.currentYear = new Date().getFullYear();
    this.monthList = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];
    this.getYearList();
  }

  ngOnInit(): void {
    this.postService.display().subscribe(data => {
      this.postList = data;
      console.log(this.postList);
      this.getTotalTransaction();
      this.getTotalPostSuccess();
        let posts = this.postList.length;
        for (let i = 0; i < posts; i++) {
          if (this.postList[i].monthPost == 1 && this.postList[i].yearPost == this.currentYear) {
            this.count1 += 1;
          }
          if (this.postList[i].monthPost == 2 && this.postList[i].yearPost == this.currentYear) {
            this.count2 += 1;
          }
          if (this.postList[i].monthPost == 3 && this.postList[i].yearPost == this.currentYear) {
            this.count3 += 1;
          }
          if (this.postList[i].monthPost == 4 && this.postList[i].yearPost == this.currentYear) {
            this.count4 += 1;
          }
          if (this.postList[i].monthPost == 5 && this.postList[i].yearPost == this.currentYear) {
            this.count5 += 1;
          }
          if (this.postList[i].monthPost == 6 && this.postList[i].yearPost == this.currentYear) {
            this.count6 += 1;
          }
          if (this.postList[i].monthPost == 7 && this.postList[i].yearPost == this.currentYear) {
            this.count7 += 1;
          }
          if (this.postList[i].monthPost == 8 && this.postList[i].yearPost == this.currentYear) {
            this.count8 += 1;
          }
          if (this.postList[i].monthPost == 10 && this.postList[i].yearPost == this.currentYear) {
            this.count10 += 1;
          }
          if (this.postList[i].monthPost == 11 && this.postList[i].yearPost == this.currentYear) {
            this.count11 += 1;
          }
          if (this.postList[i].monthPost == 12 && this.postList[i].yearPost == this.currentYear) {
            this.count12 += 1;
          }
        }

        /*===CREATE CHART*/
        new Chart('myChart', {
          type: 'line',
          data: {
            labels: ['Tháng1', 'Tháng2', 'Tháng3', 'Tháng4', 'Tháng5', 'Tháng6',
              'Tháng7', 'Tháng8', 'Tháng9', 'Tháng10', 'Tháng11', 'Tháng12'],
            datasets: [{
              label: 'Tổng bài đăng',
              data: [this.count1, this.count2, this.count3, this.count4, this.count5, this.count6,
                this.count7, this.count8, this.count9, this.count10, this.count11, this.count12],
              backgroundColor: '#02165f',
              borderColor: '#02165f',
              borderWidth: 2,
              fill: false,
            }]
          },
        });
    })

  }

  onSearch(month: string, year: string) {
    console.log(month, year);
    this.postService.search(month, year).subscribe(data => {
      this.postList = data;
      this.totalTransaction = 0;
      this.countSuccess = 0;
      this.countTotal = 0;
      for (let i = 0; i < data.length; i++) {
        if (data[i].statusPost == 1) {
          // @ts-ignore
          this.totalTransaction += data[i].price;
          this.countSuccess += 1;
          this.countTotal = this.postList.length;
          console.log(this.totalTransaction);
        }
      }
    })
  }

  getYearList() {
    for (let i = this.yearStart; i <= this.currentYear; i++) {
      this.yearList.push(i);
    }
    return this.yearList;
  }

  getTotalTransaction() {
    for (let i = 0; i < this.postList.length; i++) {
      if (this.postList[i].statusPost == 1) {
        // @ts-ignore
        this.totalTransaction += this.postList[i].price
        console.log(this.totalTransaction);
      }
    }
  }

  getTotalPostSuccess() {
    for (let i = 0; i < this.postList.length; i++) {
      if (this.postList[i].statusPost == 1) {
        // @ts-ignore
        this.countSuccess += 1;
        this.countTotal = this.postList.length;
        console.log(this.totalTransaction, this.countTotal);
      }
    }
  }

  changeYear(value: string) {
    this.postService.display().subscribe(data => {
      this.postList = data;
      this.count1 = 0;
      this.count2 = 0;
      this.count3 = 0;
      this.count4 = 0;
      this.count5 = 0;
      this.count6 = 0;
      this.count7 = 0;
      this.count8 = 0;
      this.count9 = 0;
      this.count10 = 0;
      this.count11 = 0;
      this.count12 = 0;
      let posts = this.postList.length;
      for (let i = 0; i < posts; i++) {
        if (this.postList[i].monthPost == 1 && this.postList[i].yearPost == +value) {
          this.count1 += 1;
        }
        if (this.postList[i].monthPost == 2 && this.postList[i].yearPost == +value) {
          this.count2 += 1;
        }
        if (this.postList[i].monthPost == 3 && this.postList[i].yearPost == +value) {
          this.count3 += 1;
        }
        if (this.postList[i].monthPost == 4 && this.postList[i].yearPost == +value) {
          this.count4 += 1;
        }
        if (this.postList[i].monthPost == 5 && this.postList[i].yearPost == +value) {
          this.count5 += 1;
        }
        if (this.postList[i].monthPost == 6 && this.postList[i].yearPost == +value) {
          this.count6 += 1;
        }
        if (this.postList[i].monthPost == 7 && this.postList[i].yearPost == +value) {
          this.count7 += 1;
        }
        if (this.postList[i].monthPost == 8 && this.postList[i].yearPost == +value) {
          this.count8 += 1;
        }
        if (this.postList[i].monthPost == 10 && this.postList[i].yearPost == +value) {
          this.count10 += 1;
        }
        if (this.postList[i].monthPost == 11 && this.postList[i].yearPost == +value) {
          this.count11 += 1;
        }
        if (this.postList[i].monthPost == 12 && this.postList[i].yearPost == +value) {
          this.count12 += 1;
        }
      }

      /*===CREATE CHART*/
      new Chart('myChart', {
        type: 'bar',
        data: {
          labels: ['Tháng1', 'Tháng2', 'Tháng3', 'Tháng4', 'Tháng5', 'Tháng6',
            'Tháng7', 'Tháng8', 'Tháng9', 'Tháng10', 'Tháng11', 'Tháng12'],
          datasets: [{
            label: 'Tổng bài đăng năm ' + value,
            data: [this.count1, this.count2, this.count3, this.count4, this.count5, this.count6,
              this.count7, this.count8, this.count9, this.count10, this.count11, this.count12],
            backgroundColor: '#ec0326',
            borderColor: '#ec0326',
            borderWidth: 1,
            fill: false,
          }]
        },
      });
    })
  }
}
