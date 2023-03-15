export interface OrdersRequest {
  id?: number;
  customerName?: string;
  phone?: string;
  email?: string;
  addressCustomer?: string;
  deliveryAddress?: string;
  totalOrder?:number
}
