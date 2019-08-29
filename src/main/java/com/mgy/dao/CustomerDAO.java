package com.mgy.dao;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.mgy.model.Customer;

@Component
public class CustomerDAO {

	// Dummy database. Initialize with some dummy values.
	private static List<Customer> customers;
	{
		customers = new ArrayList<>();
		customers.add(new Customer(1, "张一培", "小萌", "djohn@gmail.com", "10086"));
		customers.add(new Customer(2, "张建发", "逼发", "sruss@gmail.com", "10010"));
		customers.add(new Customer(3, "吴坤", "逼坤", "kwilliams@gmail.com", "10001"));
		customers.add(new Customer(LocalTime.now().toNanoOfDay(), "马光耀", "小马哥", "vpatel@gmail.com", "8888888"));
	}

	/**
	 * Returns list of customers from dummy database.
	 * 
	 * @return list of customers
	 */
	public List<Customer> list() {
		return customers;
	}

	/**
	 * Return customer object for given id from dummy database. If customer is
	 * not found for id, returns null.
	 * 
	 * @param id
	 *            customer id
	 * @return customer object for given id
	 */
	public Customer get(Long id) {

		for (Customer c : customers) {
			if (c.getId().equals(id)) {
				return c;
			}
		}
		return null;
	}

	/**
	 * Create new customer in dummy database. Updates the id and insert new
	 * customer in list.
	 * 
	 * @param customer
	 *            Customer object
	 * @return customer object with updated id
	 */
	public Customer create(Customer customer) {
		customer.setId(System.currentTimeMillis());
		customers.add(customer);
		return customer;
	}

	/**
	 * Delete the customer object from dummy database. If customer not found for
	 * given id, returns null.
	 * 
	 * @param id
	 *            the customer id
	 * @return id of deleted customer object
	 */
	public Long delete(Long id) {

		for (Customer c : customers) {
			if (c.getId().equals(id)) {
				customers.remove(c);
				return id;
			}
		}

		return null;
	}

	/**
	 * Update the customer object for given id in dummy database. If customer
	 * not exists, returns null
	 * 
	 * @param id
	 * @param customer
	 * @return customer object with id
	 */
	public Customer update(Long id, Customer customer) {

		for (Customer c : customers) {
			if (c.getId().equals(id)) {
				customer.setId(c.getId());
				customers.remove(c);
				customers.add(customer);
				return customer;
			}
		}

		return null;
	}

}