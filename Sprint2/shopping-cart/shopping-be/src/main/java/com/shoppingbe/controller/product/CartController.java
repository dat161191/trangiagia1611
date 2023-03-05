package com.shoppingbe.controller.product;

import com.shoppingbe.dto.cart.CartCreate;
import com.shoppingbe.dto.cart.CartListByIdAccount;
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

import java.util.List;

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

    /**
     * 04/03/2023 by BossTran
     *
     * @param cartCreate
     * @return
     */
    @PostMapping("/user/cart/create")
    public ResponseEntity<Cart> addProductToCart(@RequestBody CartCreate cartCreate) {
//        Clock clock = clockService.findById(cartCreate.getClock().getId());
        Clock clock = cartCreate.getClock();
        Customer customer = customerService.findByAccount_IdAccount(cartCreate.getIdAccount());
        if (clock != null && customer != null) {
            Cart cart = cartService.findByCustomer_IdAndClock_Id(customer.getId(), clock.getId());
            if (cart != null) {
                cart.setQuantityPurchased(cart.getQuantityPurchased() + cartCreate.getQuantityPurchased());
                cartService.save(cart);
                return new ResponseEntity<>(cart, HttpStatus.OK);
            } else {
                Cart cartNew = new Cart();
                BeanUtils.copyProperties(cartCreate, cartNew);
                cartNew.setCustomer(customer);
                cartNew.setClock(clock);
//                if (cartNew.getQuantityPurchased() <= 0) {
//                    cartNew.setQuantityPurchased(1);
//                }
                cartService.save(cartNew);
                return new ResponseEntity<>(cartNew, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/user/cart/list/{idAcount}")
    public ResponseEntity<List<CartListByIdAccount>> getListCart(@PathVariable("idAcount") Long idAcount) {
        List<CartListByIdAccount> cartListByIdAccounts = cartService.getListByAccountId(idAcount);
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
     * 05/03/2023
     * @param idAcount
     * @return
     */
//    @PatchMapping("/user/cart/pay-cart/{idAcount}")
//    public ResponseEntity<?> payCart(@PathVariable("idAcount") Long idAcount) {
//        Customer customer = customerService.findByAccount_IdAccount(idAcount);
//        if (customer != null) {
//            cartService.payCart(customer.getId());
//            String mess = "";
//            return new ResponseEntity<>(mess, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }
    @PatchMapping("/user/cart/pay-cart/{idAcount}")
    public ResponseEntity<?> payCart(@PathVariable("idAcount") Long idAcount) {
        Customer customer = customerService.findByAccount_IdAccount(idAcount);
        if (customer != null) {
            cartService.payCart(customer.getId());
            List<CartListByIdAccount> cartListByIdAccounts = cartService.getListByAccountId(idAcount);
            return new ResponseEntity<>(cartListByIdAccounts, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
