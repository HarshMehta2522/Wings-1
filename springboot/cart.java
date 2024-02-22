import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Cart {

  @Id
  @Column
  private Integer cartId;

  @Column
  private Double totalAmount;

  @OneToOne
  @JoinColumn(name = "user_id", referencedColumnName = "userId")
  private User user;

  @OneToMany(
    mappedBy = "cart",
    fetch = FetchType.EAGER,
    cascade = { CascadeType.ALL }
  )
  private List<CartProduct> cartProducts;

  public Cart() {
    super();
  }

  public Cart(
    Integer cartId,
    Double totalAmount,
    User user,
    List<CartProduct> cartProducts
  ) {
    super();
    this.cartId = cartId;
    this.totalAmount = totalAmount;
    this.user = user;
    this.cartProducts = cartProducts;
  }

  public Integer getCartId() {
    return cartId;
  }

  public void setCartId(Integer cartId) {
    this.cartId = cartId;
  }

  public Double getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(Double totalAmount) {
    this.totalAmount = totalAmount;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<CartProduct> getCartProducts() {
    return cartProducts;
  }

  public void setCartProducts(List<CartProduct> cartProducts) {
    this.cartProducts = cartProducts;
  }

  @Override
  public String toString() {
    return (
      "Cart [cartId=" +
      cartId +
      ", totalAmount=" +
      totalAmount +
      ", user=" +
      user.getUserId() +
      ", cartProducts=" +
      cartProducts +
      "]"
    );
  }
}
