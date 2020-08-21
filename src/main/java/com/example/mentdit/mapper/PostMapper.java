package com.example.mentdit.mapper;


import com.example.mentdit.dto.PostRequest;
import com.example.mentdit.dto.PostResponse;
import com.example.mentdit.model.Post;
import com.example.mentdit.model.Submentdit;
import com.example.mentdit.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    Post map(PostRequest postRequest, Submentdit submentdit, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "submentditName", source = "submentdit.name")
    @Mapping(target = "userName", source = "user.username")
    PostResponse mapToDto(Post post);

}
