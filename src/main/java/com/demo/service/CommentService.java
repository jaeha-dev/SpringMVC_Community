package com.demo.service;

import com.demo.model.Comment;
import com.demo.model.CommentLike;
import com.demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Git: jaeha-dev
 * @Name: 댓글 서비스 클래스
 */
@Service
public class CommentService {

    @Autowired private CommentRepository commentRepository;

    /**
     * @INSERT: 댓글 등록
     */
    public int insertComment(Comment comment) {
        return commentRepository.insertComment(comment);
    }

    /**
     * @SELECT: 전체 댓글 개수 조회
     */
    public int countAllComment() {
        return commentRepository.countAllComment();
    }

    /**
     * @SELECT: 댓글 개수 조회 메소드
     */
    public int countCommentById(String postId) {
        return commentRepository.countCommentById(postId);
    }

    /**
     * @SELECT: 댓글 목록 조회
     */
    public List<Comment> selectCommentList(String postId) {
        return commentRepository.selectCommentList(postId);
    }

    /**
     * @SELECT: 댓글 상세 조회
     */
    public Comment selectCommentById(String id) {
        return commentRepository.selectCommentById(id);
    }

    /**
     * @SELECT: 댓글 수정 및 삭제 본인 확인 검사
     */
    public int countCommentByIdAndNickname(String id, String userNickname) {
        return commentRepository.countCommentByIdAndNickname(id, userNickname);
    }

    /**
     * @UPDATE: 댓글 수정
     */
    public int updateComment(Comment comment) {
        return commentRepository.updateComment(comment);
    }

    /**
     * @DELETE: 댓글 삭제
     */
    public int deleteComment(String id, String userNickname) {
        return commentRepository.deleteComment(id, userNickname);
    }

    /**
     * @SELECT: 댓글 추천 여부 검사
     */
    public int countCommentLike(String postId, String commentId, String userNickname) {
        return commentRepository.countCommentLike(postId, commentId, userNickname);
    }

    /**
     * @SELECT: 댓글 추천 목록 조회
     */
    public List<CommentLike> selectCommentLikeList(String postId, String userNickname) {
        return commentRepository.selectCommentLikeList(postId, userNickname);
    }

    /**
     * @SELECT: 댓글 목록 - 댓글 추천 목록 매칭 조회
     */
    public List<CommentLike> matchList(List<Comment> commentList, List<CommentLike> commentLikeList) {
        List<CommentLike> matchedList = new ArrayList<>();

        for (Comment comment: commentList) {
            for (CommentLike commentLike: commentLikeList) {
                if (comment.getId() == commentLike.getCommentId()) {
                    matchedList.add(commentLike);
                }
            }
        }

        return matchedList;
    }

    /**
     * @CRUD: 댓글 추천
     */
    public int updateCommentLike(String postId, String commentId, String userNickname) {
        int result = this.countCommentLike(postId, commentId, userNickname);

        if (result == 0) {
            // 댓글을 추천하지 않은 상태
            this.commentRepository.insertCommentLike(postId, commentId, userNickname);
            this.commentRepository.updateCommentLike(postId, commentId, true); // 순서 주의

        } else if (result == 1) {
            // 댓글을 추천한 상태
            this.commentRepository.deleteCommentLike(postId, commentId, userNickname);
            this.commentRepository.updateCommentLike(postId, commentId, false);
        }

        return result;
    }
}