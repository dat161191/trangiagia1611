import {ClockDetail} from '../clock/clock-detail';

export interface CartCreate {
  id?: number;
  clock?: ClockDetail;
  idAccount?: number;
  quantityPurchased?: number
}
