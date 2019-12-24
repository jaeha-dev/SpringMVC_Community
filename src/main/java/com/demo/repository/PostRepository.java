package com.demo.repository;

import com.demo.model.Criteria;
import com.demo.model.Post;
import com.demo.model.PostLike;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Git: jaeha-dev
 * @Name: 게시글 레포지토리 클래스
 * @Memo: 메소드 순서는 INSERT, SELECT, UPDATE, DELETE 순서로 작성한다.
 * @Memo: 매개변수가 2개 이하 또는 모델 객체의 크기가 클 경우, Primitive 타입을 사용한다. 그외의 경우는 모델 또는 Map 객체를 사용한다.
 */
@Repository
@Transactional
public class PostRepository {
    private static final String namespace = "mappers.PostMapper";

    @Autowired private SqlSessionTemplate sql;

    // *---------------------------------------------------------------------------------------------------------------* [INSERT]

    /**
     * @TABLE: POST
     * @INSERT: 게시글 등록
     */
    public int insertPost(Post post) {
        return sql.insert(namespace + ".insertPost", post);
    }

    /**
     * @TABLE: POST_LIKE
     * @INSERT: 게시글 추천
     */
    public int insertPostLike(String postId, String userNickname) {
        return sql.insert(namespace + ".insertPostLike", new PostLike(Long.parseLong(postId), userNickname));
    }

    // *---------------------------------------------------------------------------------------------------------------* [SELECT]

    /**
     * @TABLE: POST
     * @SELECT: 전체 게시글 개수 조회
     */
    public int countAllPost() {
        return sql.selectOne(namespace + ".countAllPost");
    }

    /**
     * @TABLE: POST
     * @SELECT: 검색 게시글 개수 조회
     * @param option: 검색 옵션
     * @param keyword: 검색 키워드
     */
    public int countSearchPost(String option, String keyword) {
        Map<String, Object> map = new HashMap<>();
        map.put("option", option);
        map.put("keyword", keyword);
        return sql.selectOne(namespace + ".countSearchPost", map);
    }

    /**
     * @TABLE: POST
     * @SELECT: 게시글 목록 조회
     */
    public List<Map<String, Object>> selectPostList(Criteria criteria) {
        Map<String, Object> map = new HashMap<>();
        map.put("pageStart", criteria.getPageStart());
        map.put("perPageNumber", criteria.getPerPageNumber());
        return sql.selectList(namespace + ".selectPostList", map);
    }

    /**
     * @TABLE: POST
     * @SELECT: 검색 게시글 목록 조회
     * @param option: 검색 옵션
     * @param keyword: 검색 키워드
     */
    public List<Map<String, Object>>  selectSearchPostList(Criteria criteria, String option, String keyword) {
        Map<String, Object> map = new HashMap<>();
        map.put("option", option);
        map.put("keyword", keyword);
        map.put("pageStart", criteria.getPageStart());
        map.put("perPageNumber", criteria.getPerPageNumber());
        return sql.selectList(namespace + ".selectSearchPostList", map);
    }

    /**
     * @TABLE: POST
     * @SELECT: 게시글 상세 조회
     */
    public Post selectPostById(String id) {
        return sql.selectOne(namespace + ".selectPostById", id);
    }

    /**
     * @TABLE: POST
     * @SELECT: 게시글 수정 및 삭제 본인 확인 검사
     */
    public int countPostByIdAndNickname(String id, String userNickname) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("userNickname", userNickname);
        return sql.selectOne(namespace + ".countPostByIdAndNickname", map);
    }

    /**
     * @TABLE: POST_LIKE
     * @SELECT: 게시글 추천 여부 검사
     */
    public int countPostLike(String id, String userNickname) {
        return sql.selectOne(namespace + ".countPostLike", new PostLike(Long.parseLong(id), userNickname));
    }

    // *---------------------------------------------------------------------------------------------------------------* [UPDATE]

    /**
     * @TABLE: POST
     * @UPDATE: 게시글 수정
     */
    public int updatePost(Post post) {
        return sql.update(namespace + ".updatePost", post);
    }

    /**
     * @TABLE: POST
     * @UPDATE: 게시글 조회수 갱신
     */
    public int updatePostView(String id) {
        return sql.update(namespace + ".updatePostView", id);
    }

    /**
     * @TABLE: POST
     * @UPDATE: 게시글 댓글수 갱신
     * @param isAdd: 댓글수 증감 여부
     */
    public int updatePostComment(String id, boolean isAdd) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("isAdd", isAdd);
        return sql.update(namespace + ".updatePostComment", map);
    }

    /**
     * @TABLE: POST
     * @UPDATE: 게시글 추천수 갱신
     * @param isAdd: 추천수 증감 여부
     */
    public int updatePostLike(String id, boolean isAdd) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("isAdd", isAdd);
        return sql.update(namespace + ".updatePostLike", map);
    }

    // *---------------------------------------------------------------------------------------------------------------* [DELETE]

    /**
     * @TABLE: POST
     * @DELETE: 게시글 삭제
     */
    public int deletePost(String id, String userNickname) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("userNickname", userNickname);
        return sql.delete(namespace + ".deletePost", map);
    }

    /**
     * @TABLE: POST_LIKE
     * @DELETE: 게시글 추천 취소
     */
    public int deletePostLike(String postId, String userNickname) {
        return sql.delete(namespace + ".deletePostLike", new PostLike(Long.parseLong(postId), userNickname));
    }
}