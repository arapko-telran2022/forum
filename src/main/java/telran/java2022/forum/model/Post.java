package telran.java2022.forum.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@ToString
@Document(collection = "post")
public class Post {
	@Id
	String id;
	@Setter
    String title;
    String content;	
    @Setter
    String author;
    LocalDateTime dateCreated = LocalDateTime.now();   
    @Setter
    List<String> tags;
    
    @Setter
    Integer likes = 0;
    
    List<Comment> comments = new ArrayList<>();

	public Post(String title, String content, List<String> tags) {
		this.title = title;
		this.content = content;
		this.tags = tags;
	}
	
	public void addComment(String user, Comment comment) {
		comments.add(comment);
		
	}

}
