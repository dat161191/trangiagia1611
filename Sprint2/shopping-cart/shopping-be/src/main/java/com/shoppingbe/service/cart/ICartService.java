package com.shoppingbe.service.cart;

import com.shoppingbe.dto.cart.CartListByIdAccount;
import com.shoppingbe.entity.Cart;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICartService {
    /**
     * 04/03/2023
     *
     * @param idAccount
     * @return
     */
    List<CartListByIdAccount> getListByAccountId(@Param("idAccount") Long idAccount);

    /**
     * 04/03/2023
     *
     * @param cart
     */
    void save(Cart cart);

    Cart findById(Long id);

    /**
     * 04/03/2023
     *
     * @param idCustomer
     * @param idClock
     * @return
     */
    Cart findByCustomer_IdAndClock_Id(Long idCustomer, Long idClock);

    void removeCart(Long id);

    /**
     * 05/03/2023
     *
     * @param idCustomer
     */
    void payCart(@Param("idCustomer") Long idCustomer);

    /**
     * 06/03/2023
     *
     * @param idCart
     * @param quanlityUpdate
     */
    void changeQuanlityCart(@Param("idCart") Long idCart, @Param("quanlityUpdate") Integer quanlityUpdate);

    /**
     * 06/03/2023
     *
     * @param idCustomer
     * @return
     */
    List<Cart> findByCustomer_Id(Long idCustomer);

    Cart findByCustomer_IdAndClock_IdAndStatus(Long idCustomer, Long idClock, boolean status);

    List<Cart> findByCustomer_IdAndStatus(Long idCustomer, boolean status);

    List<CartListByIdAccount> historyCart(@Param("idCustomer") Long idCustomer);

}
