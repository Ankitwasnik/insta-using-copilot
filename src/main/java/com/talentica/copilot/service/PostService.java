package com.talentica.copilot.service;

import com.talentica.copilot.dto.CreatePostRequest;
import com.talentica.copilot.dto.PostDto;
import com.talentica.copilot.enums.ReactionType;
import org.springframework.data.domain.Page;

public interface PostService {

  PostDto createPost(CreatePostRequest request);

  PostDto getPostById(Long id);

  Page<PostDto> getAllPosts(int page, int size);

}
