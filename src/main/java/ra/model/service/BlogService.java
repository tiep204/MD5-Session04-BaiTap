package ra.model.service;

import org.springframework.data.repository.query.Param;
import ra.model.entity.Blog;

import java.util.List;

public interface BlogService extends IGenericService<Blog,Long> {
    List<Blog> findAllByBlogByCategoryId(Long catId);

}
