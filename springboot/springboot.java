DROP TABLE IF EXISTS User_Role;
DROP TABLE IF EXISTS Role;
DROP TABLE IF EXISTS Cart_Product;
DROP TABLE IF EXISTS Cart;
DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Category;

CREATE TABLE IF NOT EXISTS Role(
    role_id bigint AUTO_INCREMENT PRIMARY KEY,
    role varchar(255)
);

CREATE TABLE IF NOT EXISTS User(
    user_id bigint AUTO_INCREMENT PRIMARY KEY,
    username varchar(255),
    password varchar(255)
   
);

CREATE TABLE IF NOT EXISTS Category(
    category_id bigint AUTO_INCREMENT PRIMARY KEY, 
    category_name varchar(255)
);

CREATE TABLE IF NOT EXISTS Product(
    product_id bigint AUTO_INCREMENT PRIMARY KEY,
    product_name varchar(255),
    price Double,
    seller_id bigint,
    category_id bigint,
    FOREIGN KEY (seller_id) REFERENCES User(user_id), 
    FOREIGN KEY (category_id) REFERENCES Category(category_id) 
);

CREATE TABLE IF NOT EXISTS Cart(
    cart_id bigint AUTO_INCREMENT PRIMARY KEY,
    total_amount Double,
    user_id bigint,
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);

CREATE TABLE IF NOT EXISTS Cart_product(
    cp_id bigint AUTO_INCREMENT PRIMARY KEY,
    cart_id bigint,
    product_id bigint,
    quantity bigint,
    FOREIGN KEY (cart_id) REFERENCES Cart(cart_id), 
    FOREIGN KEY (product_id) REFERENCES Product(product_id) 
);

CREATE TABLE User_role(
    user_id bigint,
    role_id bigint,
    FOREIGN KEY (user_id) REFERENCES User(user_id), 
    FOREIGN KEY (role_id) REFERENCES Role(role_id) 
);

INSERT INTO Category (category_name)
VALUES ('Fashion'),('Electronics'),('Books'),('Groceries'),('Medicines');

INSERT INTO User (username,password)
VALUES ('jack','pass_word'),('bob','pass_word'),('apple','pass_word'),('glaxo','pass_word');

INSERT INTO Role (role)
VALUES ('CONSUMER'),('SELLER');

INSERT INTO Cart (total_amount,user_id)
VALUES (20,1),(0,2);

INSERT INTO User_role (user_id,role_id)
VALUES (1,1),(2,1),(3,2),(4,2);

INSERT INTO Product (price,product_name,category_id,seller_id)
VALUES (29190,'Apple iPad 10.2 8th Gen WiFi iOS Tablet',2,3),(10,'Crocin pain relief tablet',5,4);

INSERT INTO Cart_product (cart_id,product_id,quantity)
VALUES (1,2,2);

///cart.java

//cart.java
package com.fresco.ecommerce.models;

import java.util.List;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	@JoinColumn(
		name="user_id",referencedColumnName = "userId"
	)
	private User user;
	@OneToMany(
		mappedBy="cart",
		fetch=FetchType.EAGER,cascade={CascadeType.ALL}
	)
	private List<CartProduct> cartProducts;

	public Cart() {
		super();
	}

	public Cart(Integer cartId, Double totalAmount, User user, List<CartProduct> cartProducts) {
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
		return "Cart [cartId=" + cartId + ", totalAmount=" + totalAmount + ", user=" + user.getUserId()
				+ ", cartProducts=" + cartProducts + "]";
	}
}


//cartproduct.java

//Cartproduct.java
package com.fresco.ecommerce.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class CartProduct {
	@Id
	@Column
	private Integer cpId;
	@ManyToOne
	@JoinColumn(
		name="cartId"
	)
	private Cart cart;
	@ManyToOne
	@JoinColumn(
		name="productId"
	)
	private Product product;
	@Column
	private Integer quantity = 1;

	public CartProduct() {
		super();
	}

	public CartProduct(Cart cart, Product product, Integer quantity) {
		super();
		this.cart = cart;
		this.product = product;
		this.quantity = quantity;
	}

	public Integer getCpId() {
		return cpId;
	}

	public void setCpId(Integer cpId) {
		this.cpId = cpId;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CartProduct [cpId=" + cpId + ", cart=" + cart.getCartId() + ", product=" + product.getProductId()
				+ ", quantity=" + quantity + "]";
	}

}
//category

//category.java
package com.fresco.ecommerce.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Category {
	@Id
	@Column
	private Integer categoryId;
	@Column
	private String categoryName;

	public Category() {
		super();
	}

	public Category(String categoryName) {
		super();
		this.categoryName = categoryName;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + "]";
	}

}


//product.java


//Product.java
package com.fresco.ecommerce.models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Product {
	@Id
	@Column
	private Integer productId;
	@Column
	private String productName;
	@Column
	private Double price;
	@ManyToOne
	@JoinColumn(
		name="sellerId"
	)
	private User seller;
	@ManyToOne
	@JoinColumn(
		name="categoryId"
	)
	private Category category;

	public Product() {
		super();
	}
	public Product(Integer productId, String productName, Double price, User seller, Category category) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.seller = seller;
		this.category = category;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [productId=" + 0 + ", productName=" + productName + ", price=" + price + ", seller="
				+ seller.getUserId() + ", category=" + category + "]";
	}

}


//role


//Role.java
//role.java
package com.fresco.ecommerce.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
//Implement User Roles
@Entity
@Table
public class Role{
    @Id
    @Column
    private Integer roleId;
    @Column
    private String role;


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Role(Integer roleId, String role) {
        super();
		this.roleId = roleId;
        this.role = role;
	}
    public Role(){
        super();
    }

    @Override
	public String toString() {
		return "Role [roleId=" + roleId + ", role=" + role + "]";
	}

}


//user

//user.java
package com.fresco.ecommerce.models;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Table;

@Entity
@Table
public class User {
	@Id
	@Column
	private Integer userId;
	@Column
	private String username;
	@Column
	private String password;

	// Implement Roles
	@ManyToMany(fetch=FetchType.EAGER,cascade={CascadeType.ALL})
	@JoinTable (
		name="User_role",
		joinColumns = @JoinColumn(
			name="user_id"
		),
		inverseJoinColumns = @JoinColumn(
			name="role_id"
		)
	)
	private Set<Role> roles;

	public User() {
		super();
	}

	public User(String username, String password, Set<Role> roles) {
		super();
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", roles=" + roles
				+ "]";
	}

}

