package com.shoppingbe.repository.cart;

import com.shoppingbe.dto.cart.CartListByIdAccount;
import com.shoppingbe.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ICartRepository extends JpaRepository<Cart, Long> {
    @Query(value = "select cart.id cartId," +
            "c.id clockId,c.name clockName," +
            "c.price clockPrince," +
            "c2.id customerId," +
            "c2.customer_name customerName," +
            "cart.quantity_purchased quanlityCart," +
            "c.quanlity quanlityClock," +
            "i.url url " +
            "from cart join clock c on c.id = cart.clock_id " +
            "join img i on c.id = i.clock_id " +
            "join customer c2 on c2.id = cart.customer_id " +
            "join account a on a.id_account = c2.account_id_account " +
            "where a.id_account=:idAccount " +
            "and c2.flag_delete = false " +
            "and c.flag = false " +
            "and cart.flag = false " +
            "and a.flag_delete = false " +
            "and cart.status = false group by cart.id", nativeQuery = true)
    List<CartListByIdAccount> getListByAccountId(@Param("idAccount") Long idAccount);

    /**
     * 04/03/2023
     *
     * @param idCustomer
     * @param idClock
     * @return
     */
    Cart findByCustomer_IdAndClock_Id(Long idCustomer, Long idClock);

    /**
     * 05/03/2023
     * @param idCustomer
     */
    @Query(value = "update cart set status=true where customer_id=:idCustomer and flag=false", nativeQuery = true)
    @Transactional
    @Modifying
    void payCart(@Param("idCustomer") Long idCustomer);

    /**
     * 06/03/2023
     * @param idCart
     * @param quanlityUpdate
     */
    @Query(value = "update cart set quantity_purchased=:quanlityUpdate where id=:idCart and flag=false",nativeQuery = true)
    @Transactional
    @Modifying
    void changeQuanlityCart(@Param("idCart") Long idCart,@Param("quanlityUpdate") Integer quanlityUpdate);
}
