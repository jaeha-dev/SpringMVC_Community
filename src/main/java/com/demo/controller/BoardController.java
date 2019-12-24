package com.demo.controller;

import com.demo.model.Comment;
import com.demo.model.Criteria;
import com.demo.model.Post;
import com.demo.security.CustomUserDetails;
import com.demo.service.CommentService;
import com.demo.service.PageMaker;
import com.demo.interceptor.Identification;
import com.demo.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;
import static com.demo.util.Constant.*;
import static com.demo.util.SecurityUtil.getAuthenticatedUser;

/**
 * @Git: jaeha-dev
 * @Name: 게시판(게시글, 댓글) 컨트롤러 클래스
 */
@Controller
@RequestMapping("/board/*")
public class BoardController {
    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    @Autowired private PostService postService;
    @Autowired private CommentService commentService;

    // *---------------------------------------------------------------------------------------------------------------* [게시글 등록]

    /**
     * @URI: /board/post/new
     * @GET: 게시글 등록 폼
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/post/new")
    public String postNewGet(Model model) {
        logger.info("postNewGet()");

        model.addAttribute("post", new Post());

        return "board/post/new";
    }

    /**
     * @URI: /board/post/new
     * @POST: 게시글 등록
     * @Memo: 매개변수 BindingResult 의 위치는 @Valid 다음에 위치시켜 400 에러가 발생하지 않도록 한다. (참고: https://stackoverflow.com/questions/25125981/error-400-valiting-form-the-request-sent-was-syntactically-incorrect)
     * @Memo: 매개변수 @AuthenticationPrincipal 의 위치는 가장 뒤에 위치시켜 400 에러가 발생하지 않도록 한다. (참고: https://stackoverflow.com/questions/27014583/spring-validation-with-javax-validation-redirects-to-400)
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/post/new")
    public String postNewPost(@ModelAttribute("post") @Valid Post post, BindingResult result, Model model, RedirectAttributes rttr) {
        logger.info("postNewPost()");

        if (result.hasErrors()) {
            // Redirect 시, 내용이 입력된 상태로 적용되지 않기 때문에 Forward 방식을 사용한다.
            model.addAttribute(FAILURE_MESSAGE, "게시글 제목 또는 내용을 확인해주세요.");

            return "board/post/new";

        } else {
            post.setUserNickname(getAuthenticatedUser().getNickname());
            postService.insertPost(post);

            // 뒤로가기/새로고침 시, 쿼리 재실행 등의 문제를 방지하기 위해 Redirect 를 사용한다.
            return "redirect:/board/post/list";
        }
    }

    // *---------------------------------------------------------------------------------------------------------------* [게시글 조회]

    /**
     * @URI: /board/post/list
     * @GET: 게시글 목록 조회 및 검색
     */
    @PreAuthorize("permitAll()")
    @GetMapping("/post/list")
    public String postListGet(Criteria criteria, Model model,
                              @RequestParam(value = "option", required = false) String option,
                              @RequestParam(value = "keyword", required = false) String keyword) {
        logger.info("postListGet()");

        PageMaker pageMaker = new PageMaker();
        pageMaker.setCriteria(criteria);

        model.addAttribute("currentPage", criteria.getPage());
        model.addAttribute("pageMaker", pageMaker);
        model.addAttribute("baseUri", "/board/post/list"); // 페이징 뷰의 재사용을 위해 URI 를 모델에 담는다.

        if (option == null && keyword == null) {
            // 검색 옵션 및 검색 키워드가 없을 경우,
            pageMaker.setTotalCount(postService.countAllPost());
            model.addAttribute("postList", postService.selectPostList(criteria));

        } else {
            // 검색 옵션 및 검색 키워드가 있을 경우,
            pageMaker.setTotalCount(postService.countSearchPost(option, keyword));
            model.addAttribute("postList", postService.selectSearchPostList(criteria, option, keyword));
            model.addAttribute("option", option);
            model.addAttribute("keyword", keyword);
        }

        return "board/post/list";
    }

    /**
     * @URI: /board/post/{id}
     * @GET: 게시글 상세 조회
     */
    @PreAuthorize("permitAll()")
    @GetMapping("/post/{id}")
    public String postGet(@PathVariable("id") String id, Model model,
                          @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        logger.info("postGet()");

        model.addAttribute("post", postService.selectPostById(id));

        List<Comment> commentList = commentService.selectCommentList(id);
        model.addAttribute("commentList", commentList);

        if (! model.containsAttribute("comment")) {
            model.addAttribute("comment", new Comment());
        }

        // 로그인 사용자일 경우, 게시글/댓글 추천 여부를 추가한다.
        if (customUserDetails != null) {
            String userNickname = getAuthenticatedUser().getNickname();

            // 게시글 추천 여부(0, 1)
            int isPostLike = postService.countPostLike(id, userNickname);
            model.addAttribute("isPostLike", isPostLike);

            // 댓글 추천 여부
            model.addAttribute("isCommentLikeList", commentService.selectCommentLikeList(id, userNickname));
        }

        // 게시글 조회수 갱신
        postService.updatePostView(id);

        return "board/post";
    }

    // *---------------------------------------------------------------------------------------------------------------* [게시글 수정]

    /**
     * @URI: /board/post/{id}/edit
     * @GET: 게시글 수정 폼
     * @Memo: 인터셉터 및 어노테이션 추가로 수정/삭제 로직에 본인 확인을 수행하는 코드를 제외하였다.
     */
    @PreAuthorize("isAuthenticated()")
    @Identification(type = Identification.Type.POST)
    @GetMapping("/post/{id}/edit")
    public String postEditGet(@PathVariable("id") String id, Model model) throws IOException {
        logger.info("postEditGet()");

        model.addAttribute("post", postService.selectPostById(id));

        return "board/post/edit";
    }

    /**
     * @URI: /board/post/{id}/edit
     * @POST: 게시글 수정
     */
    @PreAuthorize("isAuthenticated()")
    @Identification(type = Identification.Type.POST)
    @PostMapping("/post/{id}/edit")
    public String postEditPost(@PathVariable("id") String id,
                               @ModelAttribute("post") @Valid Post post, BindingResult result,
                               Model model, RedirectAttributes rttr) throws IOException {
        logger.info("postEditPost()");

        if (result.hasErrors()) {
            model.addAttribute(FAILURE_MESSAGE, "게시글 제목 또는 내용을 확인해주세요.");
            return "board/post/edit";

        } else {
            post.setUserNickname(getAuthenticatedUser().getNickname()); // View 에서 input hidden 대신, 서버에서 스프링 시큐리티를 통해 닉네임을 획득한다.
            postService.updatePost(post);

            rttr.addFlashAttribute(SUCCESS_MESSAGE, "게시글이 수정되었습니다.");
            return "redirect:/board/post/" + id;
        }
    }

    // *---------------------------------------------------------------------------------------------------------------* [게시글 삭제]

    /**
     * @URI: /board/post/{id}/delete
     * @POST: 게시글 삭제
     */
    @PreAuthorize("isAuthenticated()")
    @Identification(type = Identification.Type.POST)
    @PostMapping("/post/{id}/delete")
    public String postDeleteRequest(@PathVariable("id") String id, RedirectAttributes rttr) throws IOException {
        logger.info("postDeleteRequest()");

        postService.deletePost(id, Optional.ofNullable(getAuthenticatedUser()
                .getNickname())
                .orElse("익명"));

        rttr.addFlashAttribute(SUCCESS_MESSAGE, "게시글이 삭제되었습니다.");

        return "redirect:/board/post/list";
    }

    // *---------------------------------------------------------------------------------------------------------------* [게시글 추천]

    /**
     * @URI: /board/post/{id}/like
     * @POST: 게시글 추천
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/post/{id}/like")
    public @ResponseBody int postLikePost(@PathVariable("id") String id) {
        logger.info("postLikePost()");

        // 이러한 경우, Null 검사가 필요하다. (https://jdm.kr/blog/234)
        return postService.updatePostLike(id, Optional.ofNullable(getAuthenticatedUser()
                .getNickname())
                .orElse("익명"));
    }

    // *---------------------------------------------------------------------------------------------------------------* [댓글 등록]

    /**
     * @URI: /board/comment/new
     * @POST: 댓글 등록
     * @Memo: 익명 댓글 포함
     */
    @PreAuthorize("permitAll()")
    @PostMapping("/comment/new")
    public String commentNewPost(@ModelAttribute("comment") @Valid Comment comment, BindingResult result, RedirectAttributes rttr) {
        logger.info("commentNewPost()");

        if (result.hasErrors()) {
            // 유효성 검증 실패
            // Redirect 시, 유효성 검사 결과를 보여준다. (게시글 상세 조회 시, 댓글 등록 폼을 지정하는 코드도 수정하였다.)
            // https://gerrytan.wordpress.com/2013/07/11/preserving-validation-error-messages-on-spring-mvc-form-post-redirect-get/
            rttr.addFlashAttribute("org.springframework.validation.BindingResult.comment", result);
            rttr.addFlashAttribute("comment", comment);
            rttr.addFlashAttribute(FAILURE_MESSAGE, "댓글 내용을 확인해주세요.");

            // Forward 방식을 사용할 경우, /board/post/{id} 가 아닌 /board/comment/new 로 이동되므로 Redirect 방식을 사용한다.
            // /board/post/{id} 는 Hidden 값으로 id 값을 전달하였으므로 가능하다.
            return "redirect:/board/post/" + comment.getPostId();

        } else {
            comment.setUserNickname(Optional.ofNullable(getAuthenticatedUser()
                    .getNickname())
                    .orElse("익명"));

            commentService.insertComment(comment);
            postService.updatePostComment(String.valueOf(comment.getPostId()), true);
            return "redirect:/board/post/" + comment.getPostId();
        }
    }

    // *---------------------------------------------------------------------------------------------------------------* [댓글 수정]

    /**
     * @URI: /board/comment/{id}/edit
     * @GET: 댓글 수정 폼
     * @Memo: 인터셉터 및 어노테이션 추가로 수정/삭제 로직에 본인 확인을 수행하는 코드를 제외하였다.
     * @Memo: 모달창에 값 세팅을 위해 Map(JSON) 타입을 반환한다.
     */
    @PreAuthorize("permitAll()")
    @Identification(type = Identification.Type.COMMENT)
    @GetMapping("/comment/{id}/edit")
    public @ResponseBody Map<String, Object> commentEditGet(@PathVariable("id") String id) {
        logger.info("commentEditGet()");

        Map<String, Object> map = new HashMap<>();
        Comment comment = commentService.selectCommentById(id);
        comment.setUserNickname(Optional.ofNullable(getAuthenticatedUser()
                .getNickname())
                .orElse("익명"));
        map.put("comment", comment);

        return map;
    }

    /**
     * @URI: /board/comment/{id}/edit
     * @POST: 댓글 수정
     * @Memo: 익명 댓글 포함
     * @Memo: JSON 타입으로 값이 전송되어도 형식이 일치하는 VO 객체와 @RequestBody 를 사용하면 VO 객체로 매핑된다.
     */
    @PreAuthorize("permitAll()")
    @Identification(type = Identification.Type.COMMENT)
    @PostMapping("/comment/{id}/edit")
    public @ResponseBody Map<String, Object> commentEditPost(@PathVariable("id") String id, @RequestBody @Valid Comment comment, BindingResult result) {
        logger.info("commentEditPost()");

        Map<String, Object> map = new HashMap<>();

        if (result.hasErrors()) {
            // 유효성 검증 실패
            map.put("error", result.getFieldError().getDefaultMessage());
            map.put("result", 0);
        } else {
            // 댓글 수정 성공 (본인 확인은 인터셉터에서)
            comment.setUserNickname(Optional.ofNullable(getAuthenticatedUser()
                    .getNickname())
                    .orElse("익명"));

            map.put("result", commentService.updateComment(comment));
        }

        return map;
    }

    // *---------------------------------------------------------------------------------------------------------------* [댓글 삭제]

    /**
     * @URI: /board/comment/{id}/delete
     * @POST: 댓글 삭제
     */
    @PreAuthorize("permitAll()")
    @Identification(type = Identification.Type.COMMENT)
    @PostMapping("/comment/{id}/delete")
    public @ResponseBody int commentDeletePost(@PathVariable("id") String id, @RequestBody Comment comment) {
        logger.info("commentDeletePost()");

        postService.updatePostComment(String.valueOf(comment.getPostId()), false);
        return commentService.deleteComment(String.valueOf(id), Optional.ofNullable(getAuthenticatedUser()
                .getNickname())
                .orElse("익명"));
    }

    // *---------------------------------------------------------------------------------------------------------------* [댓글 추천]

    /**
     * @URI: /board/comment/{id}/like
     * @POST: 댓글 추천
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/comment/{id}/like")
    public @ResponseBody int commentLikePost(@PathVariable("id") String id,
                                             @RequestBody Map<String, Object> map) {
        logger.info("commentLikePost()");

        String postId = String.valueOf(map.get("postId"));
        String commentId = String.valueOf(map.get("commentId"));

        // 이러한 경우, Null 검사가 필요하다. (https://jdm.kr/blog/234)
        return commentService.updateCommentLike(postId, commentId, Optional.ofNullable(getAuthenticatedUser()
                .getNickname())
                .orElse("익명"));
    }

    // *---------------------------------------------------------------------------------------------------------------* [게시글 및 댓글 본인 확인]

    /**
     * @URI: /board/post/{id}/check
     * @POST: 게시글 수정 및 삭제 본인 확인 검사
     */
    @PreAuthorize("permitAll()")
    @PostMapping("/post/{id}/check")
    public @ResponseBody int postCheckPost(@PathVariable("id") String id,
                                           @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        logger.info("postCheckPost()");

        // 게시글 작성자와 로그인 계정 일치 시, 1(T)
        return (postService.countPostByIdAndNickname(id, Optional.ofNullable(getAuthenticatedUser()
                .getNickname())
                .orElse("익명")) == 1) ? 1 : 0;
    }

    /**
     * @URI: /board/comment/{id}/check
     * @POST: 댓글 수정 및 삭제 본인 확인 검사
     * @Memo: 익명 댓글 포함
     */
    @PreAuthorize("permitAll()")
    @PostMapping("/comment/{id}/check")
    public @ResponseBody int commentCheckPost(@PathVariable("id") String id,
                                              @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        logger.info("commentCheckPost()");

        // 댓글 작성자와 로그인 계정 일치 시, 1(T)
        return (commentService.countCommentByIdAndNickname(id, Optional.ofNullable(getAuthenticatedUser()
                .getNickname())
                .orElse("익명")) == 1) ? 1 : 0;
    }
}