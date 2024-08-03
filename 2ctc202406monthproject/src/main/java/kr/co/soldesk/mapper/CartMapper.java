package kr.co.soldesk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.soldesk.beans.CartBean;

public interface CartMapper {
    
    // ����ڿ� ��ǰ�� ���� īƮ �׸��� �����ϴ��� Ȯ��
    @Select("select count(*) from cart where userID=#{userID} and productID=#{productID} ")
    int checkCartIDExists(@Param("userID") String userID, @Param("productID") String productID);
    
    // ����ڿ� ��ǰ�� ���� īƮ ID�� ������ 
    @Select("select cartID from cart where userID=#{userID} and Rownum= 1 ")
    String getCartIDByUserIDAndProductID(@Param("userID") String userID);


    // ��ǰ ���� ������Ʈ detailâ������ ����
    @Update("update cart set quantity = quantity + #{quantity} where userID = #{userID} and productID = #{productID}")
    void updateCartQuantity(CartBean cart);
    
    //��ǰ �߰�
    @Update("update cart set productID=#{productID}, quantity=#{quantity} where cartID=#{cartID}")
    void addproduct(CartBean cart);
    
    // īƮ�� ��ǰ �߰�
    @Insert("insert into cart (cartID ,userID, productID, quantity) values (#{cartID,jdbcType=VARCHAR},#{userID}, #{productID}, #{quantity})")
    void addCart(CartBean cart);
    
    
    // īƮ �׸� ��ȸ
    @Select("select c.cartID, c.userID, c.productID, c.quantity, p.color, p.product_name, p.price, p.product_img " +
            "from cart c, product p " +
            "where c.productID = p.productID and c.userID = #{userID}")
    List<CartBean> getAllCartItems(@Param("userID") String userID);

    // īƮ �׸� ����
    @Delete("DELETE FROM cart WHERE userID = #{userID} AND productID = #{productID}")
    void deleteCartItem(@Param("userID") String userID, @Param("productID") String productID);
    
    //��ǰ ���� ���� ���� ������Ʈ
    @Update("update cart set quantity = #{quantity} where userID = #{userID} and productID = #{productID}")
    void updateCartItemQuantity(@Param("userID") String userID, @Param("productID") String productID, @Param("quantity") int quantity);

}
