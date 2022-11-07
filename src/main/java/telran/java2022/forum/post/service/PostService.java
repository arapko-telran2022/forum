package telran.java2022.forum.post.service;

import java.util.List;

import telran.java2022.forum.post.dto.CommentCreateDto;
import telran.java2022.forum.post.dto.PeriodDto;
import telran.java2022.forum.post.dto.PostCreateDto;
import telran.java2022.forum.post.dto.PostDto;
import telran.java2022.forum.post.dto.PostUpdateDto;

public interface PostService {

	PostDto addPost(String author, PostCreateDto postCreateDto);

	PostDto findPostById(String id);

	void addLike(String id);

	List<PostDto> findPostsByAuthor(String author);

	PostDto addComment(String id, String user, CommentCreateDto commentCreateDto);

	PostDto removePost(String id);

	List<PostDto> findPostsByTags(List<String> tags);

	List<PostDto> findPostsByPeriod(PeriodDto periodDto);

	PostDto updatePost(String id, PostUpdateDto postUpdateDto);

}
