package com.briup.apps.cms.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.Article;
import com.briup.apps.cms.bean.ArticleExample;
import com.briup.apps.cms.bean.extend.ArticleExtend;
import com.briup.apps.cms.dao.ArticleMapper;
import com.briup.apps.cms.dao.extend.ArticleExtendMapper;
import com.briup.apps.cms.service.IArticleService;
import com.briup.apps.cms.utils.CustomerException;

/**
 * @program: cms
 * @description: 文章的业务实现类

 * @create: 2019-11-12 14:59
 **/
@Service
public class ArticleServiceImpl implements IArticleService {

    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private ArticleExtendMapper articleExtendMapper;

    @Override
    public List<Article> findAll() {
        return articleMapper.selectByExample(new ArticleExample());
    }

    @Override
    public void saveOrUpdate(Article article) throws CustomerException{
    	
    	if(article.getId() != null) {
    		articleMapper.updateByPrimaryKey(article);
    	}else {
    		ArticleExample example = new ArticleExample();
    		example.createCriteria().andTitleEqualTo(article.getTitle());
    		List<Article> list = articleMapper.selectByExample(example);
    		if(list.size()>0) {
    			throw new CustomerException("文章标题重复");
    		}
			article.setPublishTime(new Date().getTime());
			article.setReadTimes(1l);
			article.setThumpDown(1l);
			article.setThumpUp(1l);
			article.setStatus(ArticleExtend.STATUS_CHECK_NOPASS);
    		articleMapper.insert(article);		
		}
    }

    @Override
    public List<ArticleExtend> cascadeFindAll() {
        return articleExtendMapper.selectAll();
    }

    @Override
    public ArticleExtend findById(long id) {
        return articleExtendMapper.selectById(id);
    }

	@Override
	public void delete(long id) throws CustomerException{
		Article byPrimaryKey = articleMapper.selectByPrimaryKey(id);
		if(byPrimaryKey==null) {
			throw new CustomerException();
			
		}
		articleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void delete(String title) {
		ArticleExample example = new ArticleExample();
		example.createCriteria().andTitleEqualTo(title);
		if(articleMapper.selectByExample(example)!=null) {
			articleMapper.deleteByExample(example);
			
		}
		//articleMapper.deleteByExample(example)
	}

	@Override
	public List<ArticleExtend> cascadeFindAll2() {
		return articleExtendMapper.selectAll2();
	}
}
