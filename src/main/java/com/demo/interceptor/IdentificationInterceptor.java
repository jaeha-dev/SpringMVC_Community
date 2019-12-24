package com.demo.interceptor;

import com.demo.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

import static com.demo.interceptor.Identification.Type.*;
import static com.demo.util.ScriptUtil.alertAndRedirectScript;
import static com.demo.util.ScriptUtil.alertScript;
import static com.demo.util.SecurityUtil.getAuthenticatedUser;

/**
 * @Git: jaeha-dev
 * @Name: 본인 확인 인터셉터 클래스
 * @Memo:
 */
public class IdentificationInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(IdentificationInterceptor.class);

    @Autowired private UserService userService;
    @Autowired private PostService postService;
    @Autowired private CommentService commentService;
    @Autowired private ChatRoomService chatRoomService;
    @Autowired private ChatMessageService chatMessageService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (checkType(handler) == null) {
            // @Identification 으로 지정된 본인 확인 검사가 없을 시, 요청을 컨트롤러로 전달한다.
            return true;

        } else {
            // 본인 확인 검사 타입
            Identification idType = (Identification) checkType(handler);

            // 로그인 계정 정보
            String nickname = Optional.ofNullable(getAuthenticatedUser()
                    .getNickname())
                    .orElse("익명");

            switch (idType.type()) {
                case USER:
                    logger.info("ID Type(USER)");
                    return true;

                case POST:
                case COMMENT:
                case CHAT_ROOM:
                    logger.info("ID Type(POST/COMMENT/CHAT_ROOM)");

                    // 인터셉터에서 PathVariable 값 얻기
                    // 참고: https://codeday.me/ko/qa/20190517/569433.html, https://m.blog.naver.com/moonv11/60196284476
                    Map<?, ?> pathVariables = (Map<?, ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

                    // @PathVariable 값이 키로 사용된다.
                    // 즉, 게시글 번호와 로그인 계정으로 작성자 본인 확인을 수행한다.
                    String id = Optional.ofNullable(String.valueOf(pathVariables.get("id"))).orElse("0");
                    int result = 0;
                    String redirectMessage = "";
                    String redirectUri = "";

                    // 타입별 분기
                    if (idType.type().equals(POST)) {
                        result = postService.countPostByIdAndNickname(id, nickname);
                        redirectMessage = "해당 게시글에 대한 수정/삭제 권한이 없습니다.";
                        redirectUri = "/board/post/" + id;

                    } else if (idType.type().equals(COMMENT)) {
                        result = commentService.countCommentByIdAndNickname(id, nickname);
                        long redirectPostId = commentService.selectCommentById(id).getPostId(); // 해당 댓글이 작성된 게시글 번호
                        redirectMessage = "해당 댓글에 대한 수정/삭제 권한이 없습니다.";
                        redirectUri = "/board/post/" + redirectPostId;

                    } else if (idType.type().equals(CHAT_ROOM)) {
                        result = chatRoomService.countChatRoomByIdAndUserNickname(id, nickname);
                        redirectMessage = "해당 채팅방에 대한 수정/삭제 권한이 없습니다.";
                        redirectUri = "/chat/room/list";
                    }

                    if (result == 1) {
                        // 본인 확인 성공
                        return true;

                    } else {
                        // 수정 및 삭제 권한 없음
                        // response.sendRedirect("/error/403");
                        // 스크립트 출력 후, 빈 페이지가 출력되므로 Redirect 한다.
                        alertAndRedirectScript(response, redirectMessage, redirectUri);
                        return false;
                    }

                default:
                    return false;
            }
        }
    }

    /**
     * @Memo: handler 객체의 타입을 확인하는 메소드
     */
    private Identification checkType(Object handler) {
        if (handler instanceof HandlerMethod) {
            // handler 객체가 HandlerMethod 타입일 경우, 메소드에 선언된 IDCheck 어노테이션을 가져온다.
            return ((HandlerMethod) handler).getMethodAnnotation(Identification.class);

        } else {
            return null;
        }
    }
}