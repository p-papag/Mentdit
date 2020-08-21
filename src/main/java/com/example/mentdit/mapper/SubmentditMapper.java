package com.example.mentdit.mapper;

import com.example.mentdit.dto.SubmentditDto;
import com.example.mentdit.model.Post;
import com.example.mentdit.model.Submentdit;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubmentditMapper {
    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(submentdit.getPosts()))")
    SubmentditDto mapSubmentditToDto(Submentdit submentdit);

    default Integer mapPosts(List<Post> numberOfPosts) {
        return numberOfPosts.size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    Submentdit mapDtoToSubmentdit(SubmentditDto submentditDto);
}
