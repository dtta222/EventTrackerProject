package com.skilldistillery.restaurant.data;

import java.util.List;

import com.skilldistillery.restaurant.entities.Category;
import com.skilldistillery.restaurant.entities.Menu;

public interface MenuDAO {
	Menu addMenuItem(Menu menu);
	boolean deleteMenuItemById(int itemId);
	Category addCategory(Category category);
	boolean deleteCategoryById(int itemId);
	Menu findMenuItemById(int itemId);
	Category findCategoryById(int categoryId);
	List<Menu> getAllMenuItems();
	List<Category> getAllCategories();
}
