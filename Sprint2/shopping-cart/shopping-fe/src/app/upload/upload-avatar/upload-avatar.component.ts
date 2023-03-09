import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {AngularFireStorage, AngularFireStorageReference} from '@angular/fire/storage';
import {formatDate} from '@angular/common';

@Component({
  selector: 'app-upload-avatar',
  templateUrl: './upload-avatar.component.html',
  styleUrls: ['./upload-avatar.component.css']
})
export class UploadAvatarComponent implements OnInit {
  selectedFire: File | undefined;
  ref: AngularFireStorageReference | undefined;
  downloadURL: string | undefined;
  checkUpload = false;
  @Output() urlCreate = new EventEmitter<string>();

  constructor(private angularFireStorage: AngularFireStorage) {
  }

  ngOnInit(): void {
  }

  /**
   * upload file luôn trả về 1 target...trong targert là 1 mảng
   * @param $event
   */
  // @ts-ignore
  fileChange($event): void {
    this.selectedFire = $event.target.files[0];
  }

  onUpload(): void {
    this.checkUpload = true;
    const nameUrl = 'NameURL: ' + Math.random().toString(36).substring(2) + ' ' + this.getCurrentDateTime(); // Không trùng tên file trên FBase
    this.ref = this.angularFireStorage.ref(nameUrl); //Tạo name trên Fbase
    this.ref.put(this.selectedFire).then(snapshot => { // put file vào ref
      return snapshot.ref.getDownloadURL(); // trả về chuổi siêu văn bản trên FBase
    }).then(downloadURL => { // chuyển link đến component khác nhau khi upload
      this.downloadURL = downloadURL;
      this.urlCreate.emit(this.downloadURL);//thông qua emit để gởi đến component khác
      this.checkUpload = false;
      return downloadURL;
    }).catch(error => {
      console.log('failed to upload avatar and get link ${error}', error);
    });
  }

  getCurrentDateTime(): string {
    return formatDate(new Date(), 'dd-MM-yyyy hh:mm:ssa', 'en-US');
  }
}
