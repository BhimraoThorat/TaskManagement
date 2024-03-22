package com.task.DAO;

import com.task.Entity.Category;

public interface CategoryDAO
{
    void addCategory(Category category);
    Category getCategoryById(int categoryId);
    void updateCategory(Category category);
    void deleteCategory(int categoryId);
}
