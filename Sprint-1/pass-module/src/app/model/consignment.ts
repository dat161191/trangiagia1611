import {Product} from './product';

export interface Consignment {
  id?: number;
  consignmentCode?: string;
  quantity?: number,
  importDate?: string;
  productionDate?: string;
  expirationDate?: string;
  product?: Product;
}
