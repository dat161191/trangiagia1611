import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Trademark} from '../../enity/clock/trademark';
import {MachineType} from '../../enity/clock/machine-type';
import {ClockService} from '../../service/clock.service';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {applySourceSpanToExpressionIfNeeded} from '@angular/compiler/src/output/output_ast';

@Component({
  selector: 'app-clock-create',
  templateUrl: './clock-create.component.html',
  styleUrls: ['./clock-create.component.css']
})
export class ClockCreateComponent implements OnInit {
  // @ts-ignore
  clockForm: FormGroup;
  trademarks: Trademark[] = [];
  machineTypes: MachineType[] = [];

  @Output()
  emit = new EventEmitter();

  constructor(private clockService: ClockService, private router: Router, private toastrService: ToastrService) {
    this.clockService.getTrademarks().subscribe(trademarks => {
      this.trademarks = trademarks;
    });
    this.clockService.getMachineType().subscribe(machines => {
      this.machineTypes = machines;
    });
  }

  ngOnInit(): void {
    this.clockForm = new FormGroup({
      name: new FormControl('', [Validators.required, Validators.pattern('[A-Za-z -_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪ' +
        'ếễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ0-9]+'), Validators.minLength(3), Validators.maxLength(100)]),
      price: new FormControl('', [Validators.required, Validators.min(1)]),
      quanlity: new FormControl('', [Validators.required, Validators.min(1)]),
      origin: new FormControl('', [Validators.required, Validators.pattern('[A-Za-z _ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪ' +
        'ếễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+'), Validators.minLength(1), Validators.maxLength(100)]),
      faceDiameter: new FormControl('', [Validators.required, Validators.pattern('[A-Za-z ,_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪ' +
        'ếễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ0-9]+'), Validators.minLength(3), Validators.maxLength(100)]),
      thick: new FormControl('', [Validators.required, Validators.pattern('[A-Za-z _ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪ' +
        'ếễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ0-9]+'), Validators.minLength(3), Validators.maxLength(100)]),
      shellMaterial: new FormControl('', [Validators.required, Validators.pattern('[A-Za-z _ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪ' +
        'ếễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+'), Validators.minLength(3), Validators.maxLength(100)]),
      ropeMaterial: new FormControl('', [Validators.required, Validators.pattern('[A-Za-z _ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪ' +
        'ếễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+'), Validators.minLength(3), Validators.maxLength(100)]),
      glassMaterial: new FormControl('', [Validators.required, Validators.pattern('[A-Za-z _ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪ' +
        'ếễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+'), Validators.minLength(3), Validators.maxLength(100)]),
      resistanceWater: new FormControl('', [Validators.required, Validators.pattern('[A-Za-z _ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪ' +
        'ếễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ0-9]+'), Validators.minLength(3), Validators.maxLength(100)]),
      note: new FormControl(''),
      countryRegistration: new FormControl('', [Validators.required, Validators.pattern('[A-Za-z _ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪ' +
        'ếễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ0-9]+'), Validators.minLength(3), Validators.maxLength(100)]),
      gender: new FormControl('', [Validators.required]),
      trademark: new FormControl('', [Validators.required]),
      machineType: new FormControl('', [Validators.required]),
    });
  }

  createClock(): void {
    let clock = this.clockForm.value;
    this.clockService.saveClock(clock).subscribe(data => {
      clock=data;
      console.log(clock);
      if (clock != null) {
        this.toastrService.success('Thêm mới thành công', 'Thông báo', {timeOut: 2000});
        this.ngOnInit();
      }
    });
    this.emit.emit('');
  }
}
