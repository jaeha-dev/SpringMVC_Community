package com.demo.repository;

import com.demo.model.Comment;
import com.demo.model.CommentLike;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Git: jaeha-dev
 * @Name: 댓글 레포지토리 클래스
 * @Memo: 메소드 순서는 INSERT, SELECT, UPDATE, DELETE 순서로 작성한다.
 * @Memo: 매개변수가 2개 이하 또는 모델 객체의 크기가 클 경우, Primitive 타입을 사용한다. 그외의 경우는 모델 또는 Map 객체를 사용한다.
 */
@Repository
@Transactional
public class CommentRepository {
    private static final String namespace = "mappers.CommentMapper";

    @Autowired private SqlSessionTemplate sql;

    // *---------------------------------------------------------------------------------------------------------------* [INSERT]

    /**
     * @TABLE: COMMENT
     * @INSERT: 댓글 등록
     */
    public int insertComment(Comment comment) {
        return sql.insert(namespace + ".insertComment", comment);
    }

    /**
     * @TABLE: COMMENT_LIKE
     * @INSERT: 댓글 추천
     */
    public int insertCommentLike(String postId, String commentId, String userNickname) {
        Map<String, Object> map = new HashMap<>();
        map.put("postId", postId);
        map.put("commentId", commentId);
        map.put("userNickname", userNickname);
        return sql.insert(namespace + ".insertCommentLike", map);
    }

    // *---------------------------------------------------------------------------------------------------------------* [SELECT]

    /**
     * @TABLE: COMMENT
     * @SELECT: 전체 댓글 개수 조회 메소드
     */
    public int countAllComment() {
        return sql.selectOne(namespace + ".countAllComment");
    }

    /**
     * @TABLE: COMMENT
     * @SELECT: 댓글 개수 조회 메소드
     */
    public int countCommentById(String postId) {
        return sql.selectOne(namespace + ".countCommentById", postId);
    }

    /**
     * @TABLE: COMMENT
     * @SELECT: 댓글 목록 조회
     */
    public List<Comment> selectCommentList(String postId) {
        return sql.selectList(namespace + ".selectCommentList", postId);
    }

    /**
     * @TABLE: COMMENT
     * @SELECT: 댓글 상세 조회
     */
    public Comment selectCommentById(String id) {
        return sql.selectOne(namespace + ".selectCommentById", id);
    }

    /**
     * @TABLE: COMMENT
     * @SELECT: 댓글 수정 및 삭제 본인 확인 검사
     */
    public int countCommentByIdAndNickname(String id, String userNickname) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("userNickname", userNickname);
        return sql.selectOne(namespace + ".countCommentByIdAndNickname", map);
    }

    /**
     * @TABLE: COMMENT_LIKE
     * @SELECT: 댓글 추천 여부 검사
     */
    public int countCommentLike(String postId, String commentId, String userNickname) {
        Map<String, Object> map = new HashMap<>();
        map.put("postId", postId);
        map.put("commentId", commentId);
        map.put("userNickname", userNickname);
        return sql.selectOne(namespace + ".countCommentLike", map);
    }

    /**
     * @TABLE: COMMENT_LIKE
     * @SELECT: 댓글 추천 목록 조회
     */
    public List<CommentLike> selectCommentLikeList(String postId, String userNickname) {
        Map<String, Object> map = new HashMap<>();
        map.put("postId", postId);
        map.put("userNickname", userNickname);
        return sql.selectList(namespace + ".selectCommentLikeList", map);
    }

    // *---------------------------------------------------------------------------------------------------------------* [UPDATE]

    /**
     * @TABLE: COMMENT
     * @UPDATE: 댓글 수정
     */
    public int updateComment(Comment comment) {
        return sql.update(namespace + ".updateComment", comment);
    }

    /**
     * @TABLE: COMMENT
     * @UPDATE: 댓글 추천 개수 갱신
     */
    public int updateCommentLike(String postId, String id, boolean isAdd) {
        Map<String, Object> map = new HashMap<>();
        map.put("postId", postId);
        map.put("id", id);
        map.put("isAdd", isAdd);
        return sql.update(namespace + ".updateCommentLike", map);
    }

    // *---------------------------------------------------------------------------------------------------------------* [DELETE]

    /**
     * @TABLE: COMMENT
     * @DELETE: 댓글 삭제
     */
    public int deleteComment(String id, String userNickname) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("userNickname", userNickname);
        return sql.delete(namespace + ".deleteComment", map);
    }

    /**
     * @TABLE: COMMENT_LIKE
     * @DELETE: 댓글 추천 취소
     */
    public int deleteCommentLike(String postId, String commentId, String userNickname) {
        Map<String, Object> map = new HashMap<>();
        map.put("postId", postId);
        map.put("commentId", commentId);
        map.put("userNickname", userNickname);
        return sql.delete(namespace + ".deleteCommentLike", map);
    }

}