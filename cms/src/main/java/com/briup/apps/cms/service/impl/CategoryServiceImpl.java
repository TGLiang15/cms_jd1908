package com.briup.apps.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.Category;
import com.briup.apps.cms.bean.CategoryExample;
import com.briup.apps.cms.dao.CategoryMapper;
import com.briup.apps.cms.service.ICategoryService;
import com.briup.apps.cms.utils.CustomerException;

@Service
public class CategoryServiceImpl implements ICategoryService{
	@Resource
	private CategoryMapper categoryMapper;
	@Override
	public List<Category> findAll() {
		return categoryMapper.selectByExample(new CategoryExample());
		
	}

	@Override
	public void saveOrUpdate(Category category) throws CustomerException {
		if(category.getId() != null) {
			categoryMapper.updateByPrimaryKey(category);
			
		}else {
			CategoryExample example = new CategoryExample();
			example.createCriteria().andNameEqualTo(category.getName());
			List<Category> list = categoryMapper.selectByExample(example);
			if(list.size() > 0) {
				throw new CustomerException();
			}
			
			categoryMapper.insert(category);
		}
	}

	@Override
	public void deleteById(long id) throws CustomerException {
		//System.out.println(id);
		categoryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void batchDelete(long[] ids) throws CustomerException {
		if(ids.length!=0) {
			
			for (long id : ids) {
				this.deleteById(id);
			}
		}else {		
			throw new CustomerException("请选择需要删除的栏目");
			}
	}


	

}
