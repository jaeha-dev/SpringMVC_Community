package com.demo.service;

import com.demo.model.Criteria;
import com.demo.model.Post;
import com.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * @Git: jaeha-dev
 * @Name: 게시글 서비스 클래스
 */
@Service
public class PostService {

    @Autowired private PostRepository postRepository;

    /**
     * @INSERT: 게시글 등록
     */
    public int insertPost(Post post) {
        return postRepository.insertPost(post);
    }

    /**
     * @SELECT: 전체 게시글 개수 조회
     */
    public int countAllPost() {
        return postRepository.countAllPost();
    }

    /**
     * @SELECT: 검색 게시글 개수 조회
     * @param option: 검색 옵션
     * @param keyword: 검색 키워드
     */
    public int countSearchPost(String option, String keyword) {
        return postRepository.countSearchPost(option, keyword);
    }

    /**
     * @SELECT: 게시글 목록 조회
     * @param criteria: 페이징 개수
     */
    public List<Map<String, Object>> selectPostList(Criteria criteria) {
        return postRepository.selectPostList(criteria);
    }

    /**
     * @SELECT: 검색 게시글 목록 조회
     * @param criteria: 페이징 개수
     * @param option: 검색 옵션
     * @param keyword: 검색 키워드
     */
    public List<Map<String, Object>> selectSearchPostList(Criteria criteria, String option, String keyword) {
        return postRepository.selectSearchPostList(criteria, option, keyword);
    }

    /**
     * @SELECT: 게시글 상세 조회
     */
    public Post selectPostById(String id) {
        return postRepository.selectPostById(id);
    }

    /**
     * @SELECT: 게시글 수정 및 삭제 본인 확인 검사
     */
    public int countPostByIdAndNickname(String id, String userNickname) {
        return postRepository.countPostByIdAndNickname(id, userNickname);
    }

    /**
     * @UPDATE: 게시글 조회수 갱신
     */
    public int updatePostView(String id) {
        return postRepository.updatePostView(id);
    }

    /**
     * @UPDATE: 게시글 댓글수 갱신
     * @param isAdd: 댓글 증감 여부
     */
    public int updatePostComment(String id, boolean isAdd) {
        return postRepository.updatePostComment(id, isAdd);
    }

    /**
     * @UPDATE: 게시글 수정
     */
    public int updatePost(Post post) {
        return postRepository.updatePost(post);
    }

    /**
     * @DELETE: 게시글 삭제
     */
    public int deletePost(String id, String userNickname) {
        return postRepository.deletePost(id, userNickname);
    }

    /**
     * @SELECT: 게시글 추천 여부 검사
     */
    public int countPostLike(String id, String userNickname) {
        return postRepository.countPostLike(id, userNickname);
    }

    /**
     * @CRUD: 게시글 추천
     */
    public int updatePostLike(String postId, String userNickname) {
        int result = this.countPostLike(postId, userNickname);

        if (result == 0) {
            // 게시글을 추천하지 않은 상태
            this.postRepository.insertPostLike(postId, userNickname);
            this.postRepository.updatePostLike(postId, true);

        } else if (result == 1) {
            // 게시글을 추천한 상태
            this.postRepository.deletePostLike(postId, userNickname);
            this.postRepository.updatePostLike(postId, false);
        }

        return result;
    }
}