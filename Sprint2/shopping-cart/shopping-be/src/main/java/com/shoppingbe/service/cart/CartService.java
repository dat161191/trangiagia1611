package com.shoppingbe.service.cart;

import com.shoppingbe.dto.cart.CartListByIdAccount;
import com.shoppingbe.entity.Cart;
import com.shoppingbe.repository.cart.ICartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService implements ICartService {
    @Autowired
    private ICartRepository cartRepository;

    @Override
    public List<CartListByIdAccount> getListByAccountId(Long idAccount) {

        return cartRepository.getListByAccountId(idAccount);
    }

    @Override
    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public Cart findById(Long id) {
        return cartRepository.findById(id).get();
    }

    @Override
    public Cart findByCustomer_IdAndClock_Id(Long idCustomer, Long idClock) {
        return cartRepository.findByCustomer_IdAndClock_Id(idCustomer, idClock);
    }

    @Override
    public void removeCart(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public void payCart(Long idCustomer) {
        cartRepository.payCart(idCustomer);
    }

    @Override
    public void changeQuanlityCart(Long idCart, Integer quanlityUpdate) {
        cartRepository.changeQuanlityCart(idCart, quanlityUpdate);
    }

    /**
     * 06/03/2023
     *
     * @param idCustomer
     * @return
     */
    @Override
    public List<Cart> findByCustomer_Id(Long idCustomer) {
        return cartRepository.findByCustomer_Id(idCustomer);
    }

    @Override
    public Cart findByCustomer_IdAndClock_IdAndStatus(Long idCustomer, Long idClock, boolean status) {
        return cartRepository.findByCustomer_IdAndClock_IdAndStatus(idCustomer, idClock, false);
    }

    @Override
    public List<Cart> findByCustomer_IdAndStatus(Long idCustomer, boolean status) {
        return cartRepository.findByCustomer_IdAndStatus(idCustomer, false);
    }

    @Override
    public List<CartListByIdAccount> historyCart(Long idCustomer) {
        return cartRepository.historyCart(idCustomer);
    }
}
