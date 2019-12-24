package com.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Git: jaeha-dev
 * @Name: 예외 컨트롤러 클래스
 */
@Controller
// @PreAuthorize("permitAll()")
public class ErrorController {
    private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);

    /**
     * @URI: /error/{code}
     * @REQUEST: HTTP 상태 에러 코드 처리
     */
    @RequestMapping("/error/{code}")
    public ModelAndView error_REQUEST(@PathVariable String code, HttpServletRequest request, HttpServletResponse response) {
        logger.info("error(" + code + ")");

        // @PathVariable: 경로(Path)에 변수(Variable)을 넣어주는 기능
        ModelAndView mv = new ModelAndView("error/error"); // 뷰 지정
        String message = (String) request.getAttribute("javax.servlet.error.message"); // 예외 메시지 초기화

        Map<String, Object> statusMap = new HashMap<>();
        statusMap.put("STATUS_CODE", request.getAttribute("javax.servlet.error.status_code")); // 발생한 에러의 코드(Integer)
        statusMap.put("MESSAGE", request.getAttribute("javax.servlet.error.message")); // 발생된 에러의 메시지 정보(String)
        statusMap.put("EXCEPTION_TYPE", request.getAttribute("javax.servlet.error.exception_type")); // 발생된 에러 객체의 타입 정보를 가지고 있는 클래스 타입 객체(Object)
        statusMap.put("EXCEPTION", request.getAttribute("javax.servlet.error.exception")); // 발생된 에러 객체(Throwable)
        statusMap.put("REQUEST_URI", request.getAttribute("javax.servlet.error.request_uri")); // 에러가 발생된 리소스의 URI(String)
        statusMap.put("SERVLET_NAME", request.getAttribute("javax.servlet.error.servlet_name")); // 에러가 발생된 서블릿 이름(String)

        logger.info("error(" + statusMap.entrySet() + ")");

        try {
            int statusCode = Integer.parseInt(code);

            // 상태 코드에 따라 사용자에게 보여질 에러 메시지로 변경한다.
            switch (statusCode) {
                case 400:
                    message = "잘못된 요청입니다.";
                    break;
                case 403:
                    message = "접근 권한이 없습니다.";
                    break;
                case 404:
                    message = "요청한 페이지를 찾을 수 없습니다.";
                    break;
                case 405:
                    message = "요청된 메소드가 허용되지 않습니다.";
                    break;
                case 500:
                    message = "서버에 오류가 발생하였습니다.";
                    break;
                case 503:
                    message = "서비스를 이용할 수 없습니다.";
                    break;
                default:
                    message = "알 수 없는 오류가 발생하였습니다.";
                    break;
            }

        } catch (Exception e) {
            logger.info(e.toString());
            message = "알 수 없는 오류가 발생하였습니다.";

        } finally {
            statusMap.put("RESULT_MESSAGE", message);
        }

        return mv.addObject("error", statusMap);
    }
}