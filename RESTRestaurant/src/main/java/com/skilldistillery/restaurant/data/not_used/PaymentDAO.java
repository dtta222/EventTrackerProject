package com.skilldistillery.restaurant.data.not_used;

import java.util.List;

import com.skilldistillery.restaurant.entities.Payment;

public interface PaymentDAO {

	Payment createPayment(Payment payment);
	Payment updatePayment(Payment payment);
	void deletePayment(int paymentId);
	Payment getPaymentById(int paymentId);
	List<Payment> getAllPayments();
}
