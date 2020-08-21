package com.example.mentdit.service;

import com.example.mentdit.dto.PostRequest;
import com.example.mentdit.dto.PostResponse;
import com.example.mentdit.exception.PostNotFoundException;
import com.example.mentdit.exception.SubmentditNotFoundException;
import com.example.mentdit.mapper.PostMapper;
import com.example.mentdit.model.Post;
import com.example.mentdit.model.Submentdit;
import com.example.mentdit.model.User;
import com.example.mentdit.repository.PostRepository;
import com.example.mentdit.repository.SubmentditRepository;
import com.example.mentdit.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final SubmentditRepository submentditRepository;
    private final AuthService authService;
    private final PostMapper postMapper;

    public void save(PostRequest postRequest) {

        Submentdit submentdit = submentditRepository.findByName(postRequest.getSubmentditName())
                .orElseThrow(() -> new SubmentditNotFoundException(postRequest.getSubmentditName()));

        postRepository.save(postMapper.map(postRequest, submentdit, authService.getCurrentUser()));

    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubmentdit(Long submentditId) {
        Submentdit submentdit = submentditRepository.findById(submentditId)
                .orElseThrow(() -> new SubmentditNotFoundException(submentditId.toString()));
        List<Post> posts = postRepository.findAllBySubmentdit(submentdit);
        return posts.stream().map(postMapper::mapToDto).collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }
    }

