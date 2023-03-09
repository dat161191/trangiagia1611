package com.shoppingbe.controller.product;

import com.shoppingbe.dto.cart.CartCreate;
import com.shoppingbe.dto.cart.CartListByIdAccount;
import com.shoppingbe.dto.cart.RequestCart;
import com.shoppingbe.entity.Cart;
import com.shoppingbe.entity.Clock;
import com.shoppingbe.entity.Customer;
import com.shoppingbe.service.cart.ICartService;
import com.shoppingbe.service.clock.IClockService;
import com.shoppingbe.service.customer.ICustomerService;
import com.shoppingbe.service.security.IAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    private Object idCart;
    private Object object;

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
//        if (customer != null) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
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
