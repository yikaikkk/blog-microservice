package com.blog.service;

import com.blog.entity.Category;
import com.blog.model.dto.CategoryAdminDTO;
import com.blog.model.dto.CategoryDTO;
import com.blog.model.dto.CategoryOptionDTO;
import com.blog.model.dto.PageResultDTO;
import com.blog.model.vo.CategoryVO;
import com.blog.model.vo.ConditionVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface CategoryService extends IService<Category> {

    List<CategoryDTO> listCategories();

    PageResultDTO<CategoryAdminDTO> listCategoriesAdmin(ConditionVO conditionVO);

    List<CategoryOptionDTO> listCategoriesBySearch(ConditionVO conditionVO);

    void deleteCategories(List<Integer> categoryIds);

    void saveOrUpdateCategory(CategoryVO categoryVO);

}
