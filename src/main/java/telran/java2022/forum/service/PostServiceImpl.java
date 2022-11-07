package telran.java2022.forum.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java2022.forum.post.dao.PostRepository;
import telran.java2022.forum.post.dto.CommentCreateDto;
import telran.java2022.forum.post.dto.PeriodDto;
import telran.java2022.forum.post.dto.PostCreateDto;
import telran.java2022.forum.post.dto.PostDto;
import telran.java2022.forum.post.dto.PostUpdateDto;
import telran.java2022.forum.post.dto.exceptions.PostAuthorNotFoundException;
import telran.java2022.forum.post.dto.exceptions.PostNotFoundException;
import telran.java2022.forum.post.model.Comment;
import telran.java2022.forum.post.model.Post;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

	final PostRepository postRepository;
	final ModelMapper modelMapper;

	@Override
	public PostDto addPost(String author, PostCreateDto postCreateDto) {
		Post post = modelMapper.map(postCreateDto, Post.class);
		post.setAuthor(author);
		return modelMapper.map(postRepository.save(post), PostDto.class);
	}

	@Override
	public PostDto findPostById(String id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public void addLike(String id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		post.setLikes(post.getLikes() + 1);
		postRepository.save(post);
	}

	@Override
	public List<PostDto> findPostsByAuthor(String author) {
		if (postRepository.countByAuthor(author) == 0) {
			new PostAuthorNotFoundException(author);
		}
		return postRepository.findByAuthor(author)
				.map(p -> modelMapper.map(p, PostDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public PostDto addComment(String id, String user, CommentCreateDto commentCreateDto) {		
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		Comment comment = new Comment(user, commentCreateDto.getMessage());
		post.addComment(user, comment);
		return modelMapper.map(postRepository.save(post), PostDto.class);
	}

	@Override
	public PostDto removePost(String id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));		
		postRepository.deleteById(id);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> findPostsByTags(List<String> tags) {
		return postRepository.findByTagsIn(tags)
				.map(p -> modelMapper.map(p, PostDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<PostDto> findPostsByPeriod(PeriodDto periodDto) {
		return postRepository.findByDateCreatedBetween(periodDto.getDateFrom(), periodDto.getDateTo())
				.map(p -> modelMapper.map(p, PostDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public PostDto updatePost(String id, PostUpdateDto postUpdateDto) {
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));	
		post.setTitle(postUpdateDto.getTitle());
		post.setTags(postUpdateDto.getTags());
		return modelMapper.map(postRepository.save(post), PostDto.class);
	}

}
