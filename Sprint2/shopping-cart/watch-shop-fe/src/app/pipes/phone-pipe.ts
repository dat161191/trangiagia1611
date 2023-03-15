import {Pipe, PipeTransform} from '@angular/core';
@Pipe({
  name: 'phoneNumber'
})
export class PhonePipe implements PipeTransform{
  transform(value: string): string {
    const phoneNumberPattern = /(\d{3})(\d{3})(\d{4})/;
    const match = value.match(phoneNumberPattern);

    if (match) {
      return `${match[1]}.${match[2]}.${match[3]}`;
    }

    return value;
  }
}
