package com.demo.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Git: jaeha-dev
 * @Name: 클라이언트 스크립트 클래스
 * @Memo: 클라이언트에 대한 출력용 스크립트 클래스이다.
 * (Model 객체 또는 RedirectAttributes 객체를 통해 View 에 메시지를 전송하기 어려울 때 사용한다.)
 */
public class ScriptUtil {

    /**
     * @Memo: 알림창 메시지
     * @param response: 응답
     * @param message: 알림 메시지
     */
    public static void alertScript(HttpServletResponse response, String message) throws IOException {
        // 스크립트 타입 정의 후, Alert 메시지를 출력한다.
        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();
        out.println("<script>alert('" + message + "');</script>");
        out.flush();
    }

    /**
     * @Memo: 알림창 메시지 + Redirect
     * @param response: 응답
     * @param message: 알림 메시지
     * @param uri: Redirect URI
     */
    public static void alertAndRedirectScript(HttpServletResponse response, String message, String uri) throws IOException {
        // 스크립트 타입 정의 후, Alert 메시지를 출력한다.
        response.setContentType("text/html; charset=UTF-8");

        // location.href 대신, Redirect 를 위해 location.replace 를 사용한다. (replace 사용 시, 괄호 주의)
        // 또한, replace 를 지원하지 않는 웹 브라우저에 대해 location 을 추가한다.
        // (https://enzycut.tistory.com/entry/javascript-리다이렉트-하는-방법)
        PrintWriter out = response.getWriter();
        out.println("<script>");
        out.println("alert('" + message + "'); ");
        out.println("try { window.location.replace('" + uri + "'); }");
        out.println("catch (e) { window.location = '" + uri + "'; }");
        out.println("</script>");
        out.flush();
    }

    /**
     * @Memo: 부트스트랩 알림창 메시지
     * @param response: 응답
     * @param message: 알림 메시지
     */
    public static void bootstrapAlertScript(HttpServletResponse response, String type, String title, String message) throws IOException {
        // 스크립트 타입 정의 후, Alert 메시지를 출력한다.
        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();
        out.println("div class=\"alert alert_\" + type");
        out.println("<div class=\"alert--icon\"><i class=\"fas fa-bell\"></i></div>");
        out.println("<div class=\"alert--content\">");
        out.println("<strong>" + title + "</strong> " + message);
        out.println("</div>");
        out.println("<div class=\"alert--close\"><i class=\"far fa-times-circle\"></i></div>");
        out.println("</div>");
        out.flush();
    }
}