package spring_blogs.spring_blogs.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_blogs.spring_blogs.dto.blogDto.*;
import spring_blogs.spring_blogs.exception.exceptions.BlogNotFoundException;
import spring_blogs.spring_blogs.exception.exceptions.InternalServerException;
import spring_blogs.spring_blogs.model.Blog;
import spring_blogs.spring_blogs.repository.BlogRepository;
import spring_blogs.spring_blogs.dto.blogDto.CommentResponseDto;

import java.util.*;

@Service
public class BlogService {
    private final BlogRepository blogRepository;

    @Autowired
    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public BlogResponseDto createBlog(CreateBlogDto blogDto) {
       try {
           Blog blog = new Blog();
           blog.setTitle((blogDto.getTitle()));
           blog.setDescription(blogDto.getDescription());
           blog.setStatus((blogDto.getStatus()));
           Blog savedBlog = blogRepository.save(blog);

           return new BlogResponseDto(savedBlog.getId(), savedBlog.getTitle(), savedBlog.getDescription(), savedBlog.getStatus(), savedBlog.getCreated_at(), savedBlog.getUpdated_at(), savedBlog.getDeleted_at());
       } catch (Exception exception) {
           throw new InternalServerException("Some error occurred while creating a new Blog" + exception);
       }
    }

    public List<BlogResponseDto> getAllBlogs() {
       try {
           List<Blog> blogs = blogRepository.findAll();
           return blogs.stream().map(blog -> {
               return new BlogResponseDto(
                       blog.getId(),
                       blog.getTitle(),
                       blog.getDescription(),
                       blog.getStatus(),
                       blog.getCreated_at(),
                       blog.getUpdated_at(),
                       blog.getDeleted_at()
               );
           }).toList();
       } catch (Exception exception) {
           throw new RuntimeException("Some error occurred while fetching blogs", exception);
       }
    }

    public List<BlogWithCommentsDto> getAllBlogsWithComments() {
        List<Blog> blogs = blogRepository.findAll();

        return blogs.stream().map(blog -> {
            List<CommentResponseDto> comments = blog.getComments().stream().map(comment -> {
                return new CommentResponseDto(
                        comment.getId(),
                        comment.getComment(),
                        comment.getBlog().getId(),
                        comment.getCreated_at(),
                        comment.getUpdated_at(),
                        comment.getDeleted_at()
                );
            }).toList();

            return new BlogWithCommentsDto(
                    blog.getTitle(),
                    blog.getDescription(),
                    blog.getStatus(),
                    comments
            );
        }).toList();
    }

    public BlogResponseDto getBlogById(UUID id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new BlogNotFoundException("Blog with " + id + " not found"));
        return new BlogResponseDto(blog.getId(), blog.getTitle(), blog.getDescription(), blog.getStatus(), blog.getCreated_at(), blog.getUpdated_at(), blog.getDeleted_at());
    }

    public BlogResponseDto updateBlog(UUID id, UpdateBlogDto blogDto) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new BlogNotFoundException("Blog with " + id + " not found"));
        blog.setTitle((blogDto.getTitle()));
        blog.setDescription(blogDto.getDescription());
        blog.setStatus(blogDto.getStatus());
        blog.setUpdated_at(new Date());

        Blog savedBlog = blogRepository.save(blog);
        return new BlogResponseDto(savedBlog.getId(), savedBlog.getTitle(), savedBlog.getDescription(), savedBlog.getStatus(), savedBlog.getCreated_at(), savedBlog.getUpdated_at(), savedBlog.getDeleted_at());
    }

    public Map<String, String> deleteBlog(UUID id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new BlogNotFoundException("Blog with " + id + " not found"));
        blogRepository.deleteById(id);

        Map<String, String> map = new HashMap<>();
        map.put("id", id.toString());
        map.put("message", "Blog has been deleted!");

        return map;
    }
}
