package com.shoppingbe.controller.product;

import com.shoppingbe.commons.MyConstants;
import com.shoppingbe.dto.OrderRequest;
import com.shoppingbe.dto.cart.CartCreate;
import com.shoppingbe.dto.cart.CartListByIdAccount;
import com.shoppingbe.dto.cart.RequestCart;
import com.shoppingbe.entity.*;
import com.shoppingbe.service.cart.ICartService;
import com.shoppingbe.service.clock.IClockService;
import com.shoppingbe.service.customer.ICustomerService;
import com.shoppingbe.service.orders.IOrderDetailService;
import com.shoppingbe.service.orders.IOrdersService;
import com.shoppingbe.service.security.IAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@CrossOrigin("*")
@RequestMapping("api")
public class CartController {
    @Autowired
    private ICartService cartService;
    @Autowired
    private IClockService clockService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IOrdersService ordersService;
    @Autowired
    private IOrderDetailService orderDetailService;
    @Autowired
    JavaMailSender shopSender;


    /**
     * 04/03/2023 by BossTran
     *
     * @param cartCreate
     * @return
     */
    @PostMapping("/user/cart/create")
    public ResponseEntity<?> addProductToCart(@RequestBody CartCreate cartCreate) {
//        Clock clock = clockService.findById(cartCreate.getClock().getId());
        Clock clock = cartCreate.getClock();
        Customer customer = customerService.findByAccount_IdAccount(cartCreate.getIdAccount());
        if (clock != null && customer != null) {
//            Cart cart = cartService.findByCustomer_IdAndClock_Id(customer.getId(), clock.getId());
            Cart cart = cartService.findByCustomer_IdAndClock_IdAndStatus(customer.getId(), clock.getId(), false);
            if (cart != null) {
                cart.setQuantityPurchased(cart.getQuantityPurchased() + cartCreate.getQuantityPurchased());
                cartService.save(cart);
                List<CartListByIdAccount> cartListByIdAccounts = cartService.getListByAccountId(cartCreate.getIdAccount());
                return new ResponseEntity<>(cartListByIdAccounts, HttpStatus.OK);
            } else {
                Cart cartNew = new Cart();
                BeanUtils.copyProperties(cartCreate, cartNew);
                cartNew.setCustomer(customer);
                cartNew.setClock(clock);
                cartService.save(cartNew);
                List<CartListByIdAccount> cartListByIdAccounts = cartService.getListByAccountId(cartCreate.getIdAccount());
                return new ResponseEntity<>(cartListByIdAccounts, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/user/cart/list/{idAccount}")
    public ResponseEntity<List<CartListByIdAccount>> getListCart(@PathVariable("idAccount") Long idAccount) {
        List<CartListByIdAccount> cartListByIdAccounts = cartService.getListByAccountId(idAccount);
        if (cartListByIdAccounts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(cartListByIdAccounts);
    }

    @DeleteMapping("/user/cart/delete/{id}")
    public ResponseEntity<Cart> deletePackage(@PathVariable("id") Long id) {
        Cart cart = cartService.findById(id);
        if (cart == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        cartService.removeCart(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * 06/03/2023 update
     *
     * @param idAccount
     * @return
     */
    @PatchMapping("/user/cart/pay-cart/{idAccount}")
    public ResponseEntity<?> payCart(@PathVariable("idAccount") Long idAccount) {
        Customer customer = customerService.findByAccount_IdAccount(idAccount);
        List<Cart> carts = cartService.findByCustomer_IdAndStatus(customer.getId(), false);
        List<Clock> clocks = clockService.findByCustomerId(customer.getId());
        for (Cart cart : carts) {
            for (Clock clock : clocks) {
                if (Objects.equals(cart.getClock().getId(), clock.getId())) {
                    if (clock.getQuanlity() - cart.getQuantityPurchased() >= 0) {
                        int quanlity = clock.getQuanlity() - cart.getQuantityPurchased();
                        clock.setQuanlity(quanlity);
                    } else if (clock.getQuanlity() - cart.getQuantityPurchased() < 0) {
                        clock.setQuanlity(0);
                    }
                }
            }
        }
        cartService.payCart(customer.getId());
        /*sendmail 10/03/2023*/
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("quocdat.tran453@gmail.com");
        message.setTo(customer.getEmail());
        String mailSubject = MyConstants.SEND_MAIL_PAY_CART;
//        String mailContent = "<b>Người gửi: clockshop.com" + "</b>";
//        mailContent += "<p><b>Subject: </b>" + "Thư phản hồi" + "</p>";
//        mailContent += "<p><b>Content: </b>" + "Cảm ơn quý khách đã tin tưởng sản phẩm của shop" + "</p>";
//        mailContent += "<p><b>Content: </b>" + "Hân hạnh được phục vụ quý khách lần tiếp theo" + "</p>";
        String mailContent = "<a><b>Người gửi: </b>" + "clockshop.com" + "</a>";
        mailContent += "<p><b>Subject: </b>" + "Thư phản hồi" + "</p>";
        mailContent += "Content: Cảm ơn quý khách đã tin tưởng sản phẩm của shop" + "\n";
        mailContent += "Content: Hân hạnh được phục vụ quý khách lần tiếp theo" + "\n";
        message.setSubject(mailSubject);
        message.setText(mailContent);
        shopSender.send(message);
        List<CartListByIdAccount> cartListByIdAccounts = cartService.getListByAccountId(idAccount);
        return new ResponseEntity<>(cartListByIdAccounts, HttpStatus.OK);
    }

    /**
     * 06/03/2023 update
     *
     * @param requestCart
     * @return
     */
    @PatchMapping("/user/cart/change-quanlity")
    public ResponseEntity<?> changeQuanlityCart(@RequestBody RequestCart requestCart) {
        Cart cart = cartService.findById(requestCart.getIdCart());
        if (cart != null) {
            cartService.changeQuanlityCart(requestCart.getIdCart(), requestCart.getQuanlityUpdate());
            List<CartListByIdAccount> cartListByIdAccounts = cartService.getListByAccountId(requestCart.getIdAccount());
            return new ResponseEntity<>(cartListByIdAccounts, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/user/cart/order")
    public ResponseEntity<?> importOrder(@RequestBody OrderRequest orderRequest) {
        Orders orders = new Orders();
//        OrderDetail orderDetail = new OrderDetail();
        Customer customer = customerService.findById(orderRequest.getId());
//        if (customer != null) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
        orders.setCustomer(customerService.findById(orderRequest.getId()));
        orders.setCodeOrders(ThreadLocalRandom.current().nextLong(0, 999999));
        orders.setTotalOrder(orderRequest.getTotalOrder());
        orders.setDeliveryAddress(orderRequest.getDeliveryAddress());
        orders.setPhone(orderRequest.getPhone());
        ordersService.save(orders);
        List<Cart> carts = cartService.findByCustomer_IdAndStatus(customer.getId(), false);
        for (Cart iteam : carts) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setClock(clockService.findById(iteam.getClock().getId()));
            orderDetail.setOrders(orders);
            orderDetail.setQuantityOderDetail(iteam.getQuantityPurchased());
            orderDetail.setPriceClock(iteam.getClock().getPrice());
            orderDetailService.save(orderDetail);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/user/cart/history/{idAccount}")
    public ResponseEntity<List<CartListByIdAccount>> historyCart(@PathVariable("idAccount") Long idAccount) {
        Customer customer = customerService.findByAccount_IdAccount(idAccount);
        if (customer != null) {
            List<CartListByIdAccount> historyCarts = cartService.historyCart(customer.getId());
            if (historyCarts != null) {
                return new ResponseEntity<>(historyCarts, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
