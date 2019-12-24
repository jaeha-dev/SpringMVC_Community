package com.demo.controller;

import com.demo.model.User;
import com.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import static com.demo.util.Constant.*;
import static com.demo.util.SecurityUtil.convertUserDetailsToUser;
import static com.demo.util.SecurityUtil.getAuthenticatedUser;

/**
 * @Git: jaeha-dev
 * @Name: 계정 컨트롤러 클래스
 */
@Controller
@RequestMapping("/user/*")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired private UserService userService;

    // *---------------------------------------------------------------------------------------------------------------* [계정 등록]

    /**
     * @URI: /user/home
     * @GET: 계정 홈
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/home")
    public String homeGet(Model model) throws Exception {
        logger.info("homeGet()");

        User user = convertUserDetailsToUser(getAuthenticatedUser());
        model.addAttribute("user", user);

        return "user/home";
    }

    // *---------------------------------------------------------------------------------------------------------------* [계정 등록]

    /**
     * @URI: /user/consent
     * @GET: 계정 등록 이용약관 동의
     */
    @PreAuthorize("isAnonymous()")
    @GetMapping("/consent")
    public String consentGet() throws Exception {
        logger.info("consentGet()");

        return "user/consent";
    }

    /**
     * @URI: /user/join
     * @GET: 계정 등록 폼
     */
    @PreAuthorize("isAnonymous()")
    @GetMapping("/join")
    public String joinGet(Model model) throws Exception {
        logger.info("joinGet()");

        model.addAttribute("user", new User());

        return "user/join";
    }

    /**
     * @URI: /user/join
     * @POST: 계정 등록
     */
    @PreAuthorize("isAnonymous()")
    @PostMapping("/join")
    public String joinPost(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) throws Exception {
        logger.info("joinPost()");

        if (result.hasErrors()
                || emailCheckPost(user) != 0
                || phoneCheckPost(user) != 0
                || nicknameCheckPost(user) != 0) {
            logger.info(result.toString());

            model.addAttribute(FAILURE_MESSAGE, "계정 등록 정보를 확인해주세요.");
            return "user/join";

        } else if (user.getNickname().equalsIgnoreCase("익명")) {
            model.addAttribute(FAILURE_MESSAGE, "해당 닉네임은 사용할 수 없습니다.");
            return "user/join";

        } else {
            userService.insertUser(user);

            // 뒤로가기/새로고침 시, 쿼리 재실행 등의 문제를 방지하기 위해 Redirect 를 사용한다.
            return "redirect:/user/complete";
        }
    }

    /**
     * @URI: /user/complete
     * @GET: 계정 등록 완료
     */
    @PreAuthorize("isAnonymous()")
    @GetMapping("/complete")
    public String completeGet() throws Exception {
        logger.info("completeGet()");

        return "user/complete";
    }

    // *---------------------------------------------------------------------------------------------------------------* [계정 로그인]

    /**
     * @URI: /user/login
     * @REQUEST: 계정 로그인 폼 및 로그인 요청
     *
     * @Memo: 스프링 시큐리티 문제로 접근 권한을 지정하지 않는다.
     */
    @RequestMapping("/login")
    public String loginRequest() throws Exception {
        logger.info("loginRequest()");

        return "user/login";
    }

    // *---------------------------------------------------------------------------------------------------------------* [중복 검사]

    /**
     * @URI: /user/email/check
     * @POST: 이메일 중복 검사
     * @Memo: @RequestBody + Map/VO(User)
     * @Memo: 매개변수를 @PathVariable 로 지정한 후, AJAX 코드를 POST, MIME 타입으로 전송하도록 지정해도 받을 수 있다.
     * @Memo: 매개변수를 @RequestParam 로 지정한 후, AJAX 코드를 GET, MIME 타입으로 전송하도록 지정해도 받을 수 있다.
     */
    @PreAuthorize("permitAll()")
    @PostMapping("/email/check")
    public @ResponseBody int emailCheckPost(@RequestBody User user) throws Exception {
        logger.info("emailCheckPost()");

        // 중복 이메일이 있을 경우 1, 없을 경우 0
        return userService.countUserByEmail(user.getEmail());
    }

    /**
     * @URI: /user/phone/check
     * @POST: 연락처 중복 검사
     */
    @PreAuthorize("permitAll()")
    @PostMapping("/phone/check")
    public @ResponseBody int phoneCheckPost(@RequestBody User user) throws Exception {
        logger.info("phoneCheckPost()");

        return userService.countUserByPhone(user.getPhone());
    }

    /**
     * @URI: /user/nickname/check
     * @POST: 닉네임 중복 검사
     */
    @PreAuthorize("permitAll()")
    @PostMapping("/nickname/check")
    public @ResponseBody int nicknameCheckPost(@RequestBody User user) throws Exception {
        logger.info("nicknameCheckPost()");

        return userService.countUserByNickname(user.getNickname());
    }

    // *---------------------------------------------------------------------------------------------------------------* [계정 수정]

    /**
     * @URI: /user/edit
     * @GET: 계정 수정 폼
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/edit")
    public String editGet(Model model) throws Exception {
        logger.info("editGet()");

        User user = convertUserDetailsToUser(getAuthenticatedUser());
        model.addAttribute("user", user);

        return "user/edit";
    }

    /**
     * @URI: /user/edit/email, /edit/name, /edit/phone, /edit/nickname
     * @POST: 계정 정보 수정
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = {"/edit/email", "/edit/name", "/edit/phone", "/edit/nickname"})
    public String editUser(@ModelAttribute("user") @Valid User user, BindingResult result, Model model, RedirectAttributes rttr) throws Exception {
        logger.info("editUser()");

        if (result.hasErrors()
                || emailCheckPost(user) != 0
                || phoneCheckPost(user) != 0
                || nicknameCheckPost(user) != 0) {
            logger.info(result.toString());
            model.addAttribute(FAILURE_MESSAGE, "계정 정보를 확인해주세요.");
            return "user/edit";

        } else {
            userService.updateUser(user, getAuthenticatedUser().getEmail());
            rttr.addFlashAttribute(SUCCESS_MESSAGE, "계정 정보가 수정되었습니다.");
            return "redirect:/user/edit";
        }
    }

    /**
     * @URI: /user/edit/password
     * @POST: 계정 비밀번호 수정
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/edit/password")
    public String editPassword(@ModelAttribute("user") @Valid User user, BindingResult result, Model model, RedirectAttributes rttr) throws Exception {
        logger.info("editPassword()");

        if (result.hasErrors()) {
            logger.info(result.toString());
            model.addAttribute(FAILURE_MESSAGE, "비밀번호를 확인해주세요.");
            return "user/edit";

        } else {
            user.setEmail(getAuthenticatedUser().getEmail());
            userService.updateUserPassword(user);
            rttr.addFlashAttribute(SUCCESS_MESSAGE, "비밀번호가 수정되었습니다.");
            return "redirect:/user/edit";
        }
    }

    /**
     * @URI: /user/delete
     * @GET: 계정 활성 폼
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete")
    public String deleteGet(Model model) throws Exception {
        logger.info("deleteGet()");

        User user = convertUserDetailsToUser(getAuthenticatedUser());
        model.addAttribute("user", user);

        return "user/delete";
    }

    /**
     * @URI: /user/delete
     * @POST: 계정 활성 수정
     */

    // *---------------------------------------------------------------------------------------------------------------* [...]
}