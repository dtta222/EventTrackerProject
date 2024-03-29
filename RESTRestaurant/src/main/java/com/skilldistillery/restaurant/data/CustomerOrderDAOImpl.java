package com.skilldistillery.restaurant.data;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skilldistillery.restaurant.entities.Category;
import com.skilldistillery.restaurant.entities.CustomerOrder;
import com.skilldistillery.restaurant.entities.CustomerOrderItem;
import com.skilldistillery.restaurant.entities.Menu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerOrderDAOImpl implements CustomerOrderDAO {

    @PersistenceContext
    private EntityManager em;

    /*@Override
    public CustomerOrder createOrder(CustomerOrder order) {
        em.persist(order);
        return order;
    }*/
    public CustomerOrder createOrder(CustomerOrder order) {
        return em.merge(order);
    }


    @Override
    public CustomerOrder updateOrder(CustomerOrder order) {
        return em.merge(order);
    }

    @Override
    public boolean deleteOrderById(int orderId) {
        boolean deleted = false;
        CustomerOrder toDelete = em.find(CustomerOrder.class, orderId);
        if (toDelete != null) {
            em.remove(toDelete);
            deleted = true;
        }
        return deleted;
    }

    @Override
    public CustomerOrder getOrderById(int orderId) {
        return em.find(CustomerOrder.class, orderId);
    }

    @Override
    public List<CustomerOrder> getAllOrders() {
        return em.createQuery("SELECT o FROM CustomerOrder o", CustomerOrder.class).getResultList();
    }

    @Override
    public CustomerOrder updateOrderStatus(int orderId, String status) {
        CustomerOrder order = em.find(CustomerOrder.class, orderId);
        if (order != null) {
            order.setStatus(status);
            em.merge(order);
            return order;
        } else {
            return null;
        }
    }

    @Override
    public List<CustomerOrderItem> getOrderItems(int orderId) {
        return em.createQuery("SELECT c FROM CustomerOrderItem c WHERE c.order.id = :orderId", CustomerOrderItem.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }

    @Override
    public CustomerOrder addItemToOrder(int orderId, Menu menuItem) {
        CustomerOrder order = em.find(CustomerOrder.class, orderId);
        if (order != null) {
            order.addItem(menuItem, 1); // Assuming quantity is 1, modify as needed
            em.merge(order);
        }
        return order;
    }

    @Override
    public List<Menu> getItemsInOrder(int orderId) {
        CustomerOrder order = em.find(CustomerOrder.class, orderId);
        if (order != null) {
            return order.getItems();
        } else {
            return null;
        }
    }
}
