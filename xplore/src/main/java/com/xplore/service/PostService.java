package com.xplore.service;

import com.xplore.payload.PostDetailsDto;
import com.xplore.payload.PostDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface PostService {
    PostDetailsDto addPost(PostDto postDto,List<MultipartFile> postImages);
    String deletePostDetails(Long postId);
    PostDetailsDto updatePost(Long postId, PostDto postDto,List<MultipartFile> postImages);
    PostDetailsDto findByPostId(Long postId);
    List<PostDetailsDto> listOfPosts();
}