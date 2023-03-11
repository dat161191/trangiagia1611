import {Employee} from '../employee/Employee';
import {Customer} from '../customer/Customer';
import {Facility} from '../facility/Facility';

export interface Contract {
  id?: number;
  name?:string;
  startDate?: string;
  endDate?: string;
  deposit?: number;
  employee?: Employee;
  customer?: Customer;
  facility?: Facility;
}
