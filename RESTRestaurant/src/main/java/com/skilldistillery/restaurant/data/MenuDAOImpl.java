package com.skilldistillery.restaurant.data;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skilldistillery.restaurant.entities.Category;
import com.skilldistillery.restaurant.entities.Menu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class MenuDAOImpl implements MenuDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Menu addMenuItem(Menu menu) {
		em.persist(menu);
		return menu;
	}

	@Override
	public Category addCategory(Category category) {
		em.persist(category);
		return category;
	}

	@Override
	public Menu findMenuItemById(int itemId) {
		return em.find(Menu.class, itemId);
	}

	@Override
	public Category findCategoryById(int categoryId) {
		return em.find(Category.class, categoryId);
	}

	@Override
	public List<Menu> getAllMenuItems() {
		return em.createQuery("SELECT m FROM Menu m", Menu.class).getResultList();
	}

	@Override
	public List<Category> getAllCategories() {
		return em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
	}

	@Override
	public boolean deleteMenuItemById(int itemId) {
		boolean deleted = false;
		Menu toDelete = em.find(Menu.class, itemId);
		if (toDelete != null) {
			em.remove(toDelete);
			deleted = true;
		}
		return deleted;
	}

	@Override
	public boolean deleteCategoryById(int categoryId) {
		boolean deleted = false;
		Category toDelete = em.find(Category.class, categoryId);
		if (toDelete != null) {
			em.remove(toDelete);
			deleted = true;
		}
		return deleted;
	}

}
