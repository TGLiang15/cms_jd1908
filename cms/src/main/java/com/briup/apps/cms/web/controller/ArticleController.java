package com.briup.apps.cms.web.controller;

import com.briup.apps.cms.bean.Article;
import com.briup.apps.cms.bean.extend.ArticleExtend;
import com.briup.apps.cms.service.IArticleService;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: cms
 * @description: 文章控制器类
 * 
 **/
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    @GetMapping("findAll")
    public Message findAll(){
        List<Article> list = articleService.findAll();
        return MessageUtil.success(list);
    }

    @GetMapping("cascadeFindAll")
    public Message cascadeFindAll(){
        List<ArticleExtend> list = articleService.cascadeFindAll();
        return MessageUtil.success(list);
    }
    @GetMapping("cascadeFindAll2")
    public Message cascadeFindAll2(){
    	List<ArticleExtend> list =articleService.cascadeFindAll2();
    	return MessageUtil.success(list);
    }
    @PostMapping("saveOrUpdate")
    public Message saveOrUpdate(
    		@ApiParam(value = "编号") @RequestParam(value = "id",required = false)Long id,
    		@ApiParam(value = "标题",required = true) @RequestParam(value = "title")String title,
    		@ApiParam(value = "内容",required = true) @RequestParam(value = "content")String content,
    		@ApiParam(value = "源内容",required = true) @RequestParam(value = "source",required = false)String source,
    		@ApiParam(value = "栏目id",required = true) @RequestParam(value = "categoryId")long categoryId,
    		@ApiParam(value = "作者id",required = true) @RequestParam(value = "authorId",required = false)long authorId){
    	Article article = new Article();
    	article.setId(id);
    	article.setAuthorId(authorId);
    	article.setTitle(title);
    	article.setSource(source);
    	article.setCategoryId(categoryId);
    	article.setContent(content);
    	articleService.saveOrUpdate(article);
    	return MessageUtil.success("更新成功");
    }

    @GetMapping("findById")
    public Message findById(long id){
        ArticleExtend articleExtend = articleService.findById(id);
        return MessageUtil.success(articleExtend);
    }
    @PostMapping("insert")
    public void insert(Article article){
    	articleService.saveOrUpdate(article);
    }
    @DeleteMapping("deleteById")
    @ApiImplicitParams(@ApiImplicitParam(name = "id",value = "编号",required = true,paramType = "query"))
    public Message delete(long id){
    	articleService.delete(id);
		return MessageUtil.success("删除成功");
    }
    @DeleteMapping("deleteByTitle")
    public void delete(String title){
    	articleService.delete(title);
    }
    
}
