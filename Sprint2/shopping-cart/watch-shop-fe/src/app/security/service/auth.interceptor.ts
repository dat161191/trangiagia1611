import {Injectable} from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpHeaders
} from '@angular/common/http';
import {Observable} from 'rxjs';
import {TokenService} from './token.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private tokenService: TokenService) {
  }

  /**
   * Phương thức này là một interceptor trong Angular, được sử dụng để thêm thông tin xác thực vào header của một request.
   * Nếu người dùng đã đăng nhập và token đã được lưu trữ trong ứng dụng,
         + Interceptor sẽ thêm token vào header của request bằng cách sử dụng lớp HttpHeaders và trả về request đã được xác thực.
   * Nếu không, interceptor sẽ trả về request ban đầu mà không có thay đổi.
   * Cụ thể, đoạn mã này sẽ:
        + Nhận vào một HttpRequest không xác định kiểu dữ liệu.
        + Kiểm tra xem token đã được lưu trữ trong ứng dụng hay chưa.
        + Nếu có, interceptor sẽ sao chép request gốc và thêm token vào header của request mới. Sau đó, request mới được gửi đến next handler.
        + Nếu không, interceptor sẽ trả về request gốc mà không có thay đổi.
   * @param request
   * @param next
   */
  intercept(request: HttpRequest<unknown>,
            next: HttpHandler): Observable<HttpEvent<unknown>> {
    if (this.tokenService.getToken() != null) {
      const token = this.tokenService.getToken();
      const headers = new HttpHeaders().set('Authorization', 'Bearer ' + token);
      const AuthRequest = request.clone({headers});
      return next.handle(AuthRequest);
    } else {
      return next.handle(request);
    }
  }
}
