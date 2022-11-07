package telran.java2022.forum.post.dto;

import java.util.List;

import lombok.Getter;

@Getter
public class PostUpdateDto {
	String title;
	List<String> tags;
}
