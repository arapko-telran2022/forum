package telran.java2022.forum.post.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java2022.forum.post.dto.CommentCreateDto;
import telran.java2022.forum.post.dto.PeriodDto;
import telran.java2022.forum.post.dto.PostCreateDto;
import telran.java2022.forum.post.dto.PostDto;
import telran.java2022.forum.post.dto.PostUpdateDto;
import telran.java2022.forum.post.service.PostService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/forum")
public class PostController {

	final String endPoint = "/post";
	final String endPointForList = "/posts";

	final PostService postService;

	@PostMapping(endPoint + "/{author}")
	public PostDto addPost(@PathVariable String author, @RequestBody PostCreateDto postCreateDto) {
		return postService.addPost(author, postCreateDto);
	}

	@GetMapping(endPoint + "/{id}")
	public PostDto findPostById(@PathVariable String id) {
		return postService.findPostById(id);
	}

	@PutMapping(endPoint + "/{id}/like")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void addLike(@PathVariable String id) {
		postService.addLike(id);
	}
	
	@GetMapping(endPointForList + "/author/{author}")
	public List<PostDto> findPostsByAuthor(@PathVariable String author) {
		return postService.findPostsByAuthor(author);
	}

	@PutMapping(endPoint + "/{id}/comment/{user}")
	public PostDto addComment(@PathVariable String id, @PathVariable String user, @RequestBody CommentCreateDto commentCreateDto) {
		return postService.addComment(id, user, commentCreateDto);
	}

	@DeleteMapping(endPoint + "/{id}")
	public PostDto removePost(@PathVariable String id) {
		return postService.removePost(id);
	}

	@PostMapping(endPointForList + "/tags")
	public List<PostDto> findPostsByTags(@RequestBody List<String> tags) {
		return postService.findPostsByTags(tags);
	}

	@PostMapping(endPointForList + "/period")
	public List<PostDto> findPostsByPeriod(@RequestBody PeriodDto periodDto) {
		return postService.findPostsByPeriod(periodDto);
	}

	@PutMapping(endPoint + "/{id}")
	public PostDto updatePost(@PathVariable String id, @RequestBody PostUpdateDto studentUpdateDto) {
		return postService.updatePost(id, studentUpdateDto);
	}

}
