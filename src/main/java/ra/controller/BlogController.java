package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BlogService blogService;

    @GetMapping("")
    public ResponseEntity<List<Blog>> findAll() {
        List<Blog> blogList = blogService.findAll();
        if (blogList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(blogList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Blog> add(@RequestBody Blog blog) {
        blog.setId(null);
        blogService.save(blog);
        Blog blog1 = blogService.findById(blog.getId());
        return new ResponseEntity<>(blog1, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Blog> update(@RequestBody Blog blog, @PathVariable Long id) {
        if (blogService.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        blog.setId(id);
        Blog blog1 = blogService.save(blog);
        return new ResponseEntity<>(blog1, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Blog> findById(@PathVariable Long id) {
       Blog blog =  blogService.findById(id);
       if (blog==null){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
        return new ResponseEntity<>(blog,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Blog blog = blogService.findById(id);
        blogService.delete(blog.getId());
        if (blog.getId()==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}