package spring_blogs.spring_blogs.controller;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring_blogs.spring_blogs.dto.ApiResponse;
import spring_blogs.spring_blogs.dto.blogDto.BlogResponseDto;
import spring_blogs.spring_blogs.dto.blogDto.BlogWithCommentsDto;
import spring_blogs.spring_blogs.dto.blogDto.CreateBlogDto;
import spring_blogs.spring_blogs.dto.blogDto.UpdateBlogDto;
import spring_blogs.spring_blogs.service.BlogService;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/blogs")
@RestController
public class BlogController {
    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/create-blog")
    public ResponseEntity<BlogResponseDto> createBlog(@Valid @RequestBody CreateBlogDto blogDto) {
        BlogResponseDto blog = this.blogService.createBlog(blogDto);
        return new ResponseEntity<>(blog, HttpStatus.CREATED);
    }

    @GetMapping("/all-blogs")
    public ResponseEntity<ApiResponse<List<BlogResponseDto>>> getAllBlogs() {
        List<BlogResponseDto> blogs = this.blogService.getAllBlogs();
        ApiResponse<List<BlogResponseDto>> response = new ApiResponse<>(blogs);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/blogs-with-comments")
    public ResponseEntity<ApiResponse<List<BlogWithCommentsDto>>> getAllBlogsWithComments() {
        List<BlogWithCommentsDto> blogsWithCommentRes = this.blogService.getAllBlogsWithComments();
        ApiResponse<List<BlogWithCommentsDto>> response = new ApiResponse<>(blogsWithCommentRes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogResponseDto> getBlogById(@PathVariable UUID id) {
        BlogResponseDto blog = this.blogService.getBlogById(id);
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogResponseDto> updateBlog(@PathVariable UUID id, @Valid @RequestBody UpdateBlogDto blogDto) {
        BlogResponseDto blog = this.blogService.updateBlog(id, blogDto);
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteBlog(@PathVariable UUID id) {
        Map<String, String> blog = this.blogService.deleteBlog(id);
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }
}
