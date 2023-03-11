import {Contract} from './Contract';
import {AttachFacility} from './AttachFacility';

export interface ContractDetail {
  id?: number;
  contract?: Contract;
  attachFacility?: AttachFacility;
  quantity?: number;
}
