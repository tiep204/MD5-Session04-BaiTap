package ra.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ra.model.entity.Blog;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    @Query(value = "select b.id,b.content,b.title,c.id,c.name,c.status\n" +
            "from blog b\n" +
            "         join cate_blog cb on b.id = cb.blog_id\n" +
            "         join category c on c.id = cb.category_id where c.id = :catId", nativeQuery = true)
    List<Blog> findAllByBlogByCategoryId(@Param("catId") Long catId);
}