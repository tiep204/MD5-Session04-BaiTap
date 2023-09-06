package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.model.entity.Blog;
import ra.model.entity.Category;
import ra.model.service.BlogService;
import ra.model.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BlogService blogService;

    @GetMapping("")
    public ResponseEntity<List<Category>> findAll() {
        List<Category> categoryList = categoryService.findAll();
        if (categoryList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categoryList,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id){
        Category category = categoryService.findById(id);
        if (category==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        categoryService.delete(category.getId());
        if (category.getId()==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> doAdd(@RequestBody Category category){
        category.setId(null);
        Category userAdd= categoryService.save(category);
        return new ResponseEntity<>(userAdd,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> doUpdate(@RequestBody Category category,@PathVariable Long id){
        if(categoryService.findById(id)==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        category.setId(id);
        Category categoryUpp= categoryService.save(category);
        return new ResponseEntity<>(categoryUpp,HttpStatus.CREATED);
    }
    @GetMapping("/cat/{id}")
    public ResponseEntity<List<Blog>> findByCategoryId(@PathVariable Long id){
        List<Blog> blogList = blogService.findAllByBlogByCategoryId(id);
        return new ResponseEntity<>(blogList,HttpStatus.OK);
    }
}