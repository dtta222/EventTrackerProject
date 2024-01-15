package com.skilldistillery.restaurant.data;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skilldistillery.restaurant.entities.Payment;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PaymentDAOImpl implements PaymentDAO {

	@PersistenceContext
    private EntityManager em;
	
	@Override
	public Payment createPayment(Payment payment) {
		em.persist(payment);
        return payment;
	}

	@Override
	public Payment updatePayment(Payment payment) {
		return em.merge(payment);
	}

	@Override
	public void deletePayment(int paymentId) {
		Payment payment = em.find(Payment.class, paymentId);
        if (payment != null) {
            em.remove(payment);
        }
	}

	@Override
	public Payment getPaymentById(int paymentId) {
		return em.find(Payment.class, paymentId);
	}

	@Override
	public List<Payment> getAllPayments() {
		return em.createQuery("SELECT p FROM Payment p", Payment.class).getResultList();
	}

}
