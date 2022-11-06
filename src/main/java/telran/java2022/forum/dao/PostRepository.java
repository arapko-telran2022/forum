package telran.java2022.forum.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import telran.java2022.forum.model.Post;

public interface PostRepository extends CrudRepository<Post, String> {
	
	Stream<Post> findByAuthor(String author);
	
	Long countByAuthor(String author);
	
	@Query("{'tags': {$in: ?0}}")
	Stream<Post> findByTagsIn(List<String> tags);
	
	Stream<Post> findByDateCreatedBetween(LocalDate dateFrom, LocalDate dateTo);
	
}
