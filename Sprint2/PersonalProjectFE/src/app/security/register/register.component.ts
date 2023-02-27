import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {SecurityService} from '../service/security.service';
import {Router} from '@angular/router';
import {TokenService} from '../service/token.service';
import {ToastrService} from 'ngx-toastr';
import {Title} from '@angular/platform-browser';
import {differenceInYears} from 'date-fns';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  customerForm: FormGroup = new FormGroup({});

  constructor(private securityService: SecurityService,
              private router: Router,
              private formBuilder: FormBuilder,
              private tokenService: TokenService,
              private toast: ToastrService,
              private title: Title) {
    this.title.setTitle('Trang Đăng Ký Tài Khoản');
    this.customerForm = new FormGroup({
      id: new FormControl(),
      customerName: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z _ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪ' +
        'ếễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+'), Validators.minLength(3), Validators.maxLength(100)]),
      email: new FormControl('', [Validators.required, Validators.pattern('^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$'),
        Validators.minLength(5), Validators.maxLength(100)]),
      idCard: new FormControl('', [Validators.required, Validators.pattern('^\\d{12}$')]),
      phone: new FormControl('', [Validators.required, Validators.pattern('(((\\+|)84)|0)(3|5|7|8|9)+([0-9]{8})')]),
      addressCustomer: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z _ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪ' +
        'ếễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+'), Validators.minLength(5), Validators.maxLength(100)]),
      gender: new FormControl('', Validators.required),
      birthday: new FormControl('', [Validators.required, this.checkBirthDay]),
      encryptPassword: new FormControl('', [Validators.required, Validators.minLength(6), Validators.maxLength(100)])
    });
  }

  ngOnInit(): void {
  }

  checkBirthDay(check: AbstractControl): any {
    const date = new Date(check.value);
    const age = differenceInYears(new Date(), date);
    return (age <= 18) ? {greaterThan18: true} : null;
  }

  register() {
    const customer = this.customerForm.value;
    console.log(this.customerForm);
    this.securityService.resister(customer).subscribe(data => {
      this.toast.success('Thêm mới thành công.');
      this.router.navigateByUrl('/login');
    }, error => {
      if (error.status === 409) {
        this.toast.error('Thêm mới thất bại, em mail đã tồn tại');
      }
      this.toast.error('Thêm mới không thành công.');
    });
  }
}
