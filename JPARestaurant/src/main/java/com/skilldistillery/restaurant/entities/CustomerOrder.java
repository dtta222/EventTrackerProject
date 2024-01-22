package com.skilldistillery.restaurant.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer_order")
public class CustomerOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "customer_id")
	private int customerID;
	@Column(name = "table_id")
    private int tableID;
	@CreationTimestamp
	@Column(name = "order_date")
	private LocalDateTime orderDate;
    @Column(name = "total_amount")
    private double totalAmount;
    private String status;
    @Column(name = "server_id")
    private int serverID;
    
    /*@OneToMany(mappedBy = "customerOrder", cascade = CascadeType.ALL)
    private List<CustomerOrderItem> orderItems;*/
    
    @OneToMany(mappedBy = "customerOrder", cascade = CascadeType.PERSIST)
    private List<CustomerOrderItem> orderItems;


    public CustomerOrder() {
        super();
        this.orderItems = new ArrayList<>();
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public int getTableID() {
		return tableID;
	}

	public void setTableID(int tableID) {
		this.tableID = tableID;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getServerID() {
		return serverID;
	}

	public void setServerID(int serverID) {
		this.serverID = serverID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerOrder other = (CustomerOrder) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CustomerOrder [id=").append(id).append(", customerID=").append(customerID).append(", tableID=")
				.append(tableID).append(", orderDate=").append(orderDate).append(", totalAmount=").append(totalAmount)
				.append(", status=").append(status).append(", serverID=").append(serverID).append("]");
		return builder.toString();
	}

	public void addItem(Menu menuItem, int quantity) {
        CustomerOrderItem orderItem = new CustomerOrderItem();
        orderItem.setMenuItem(menuItem);
        orderItem.setQuantity(quantity);
        orderItem.setSubtotal(menuItem.getPrice() * quantity);
        orderItem.setCustomerOrder(this); // Set the bidirectional relationship
        orderItems.add(orderItem);
    }

    public List<Menu> getItems() {
        List<Menu> items = new ArrayList<>();
        for (CustomerOrderItem orderItem : orderItems) {
            items.add(orderItem.getMenuItem());
        }
        return items;
    }
}
