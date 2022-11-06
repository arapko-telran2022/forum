package telran.java2022.forum.controller;

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
import telran.java2022.forum.dto.CommentCreateDto;
import telran.java2022.forum.dto.PeriodDto;
import telran.java2022.forum.dto.PostCreateDto;
import telran.java2022.forum.dto.PostDto;
import telran.java2022.forum.dto.PostUpdateDto;
import telran.java2022.forum.service.PostService;

@RestController
@RequiredArgsConstructor
@RequestMapping(name = "/forum")
public class ForumController {

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

//------------------------------------------------------------------------------------------	
	@GetMapping(endPointForList + "/author/{author}")
	public List<PostDto> findPostsByAuthor(@PathVariable String author) {
		return postService.findPostsByAuthor(author);
//		List<PostDto> res = postService.findPostsByAuthor(author);
//		return res.size() == 0 ? findPostsByAuthorEnpty(res) : findPostsByAuthorFull(res);
	}

//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public List<PostDto> findPostsByAuthorEnpty(List<PostDto> res) {
//		return res;
//	}
//
//	@ResponseStatus(HttpStatus.OK)
//	public List<PostDto> findPostsByAuthorFull(List<PostDto> res) {
//		return res;
//	}
//------------------------------------------------------------------------------------------		

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
