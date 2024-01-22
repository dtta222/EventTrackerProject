package com.skilldistillery.restaurant.data;

import java.util.List;

import com.skilldistillery.restaurant.entities.CustomerOrder;
import com.skilldistillery.restaurant.entities.CustomerOrderItem;
import com.skilldistillery.restaurant.entities.Menu;

public interface CustomerOrderDAO {

	CustomerOrder createOrder(CustomerOrder order);

	CustomerOrder updateOrder(CustomerOrder order);

	boolean deleteOrderById(int orderId);

	CustomerOrder getOrderById(int orderId);

	List<CustomerOrder> getAllOrders();

	CustomerOrder updateOrderStatus(int orderId, String status);
	
	List<CustomerOrderItem> getOrderItems(int orderId);
	
	//CustomerOrder setOrderItems(orderItems);

	CustomerOrder addItemToOrder(int orderId, Menu menuItem);

	List<Menu> getItemsInOrder(int orderId);
}
