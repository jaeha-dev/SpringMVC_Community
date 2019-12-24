package com.demo.controller;

import com.demo.interceptor.Identification;
import com.demo.model.ChatRoom;
import com.demo.security.CustomUserDetails;
import com.demo.service.ChatRoomService;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.demo.util.Constant.FAILURE_MESSAGE;
import static com.demo.util.Constant.SUCCESS_MESSAGE;
import static com.demo.util.SecurityUtil.getAuthenticatedUser;

/**
 * @Git: jaeha-dev
 * @Name: 채팅방 컨트롤러 클래스
 */
@Controller
@RequestMapping("/chat/*")
public class ChatRoomController {
    private static final Logger logger = LoggerFactory.getLogger(ChatRoomController.class);

    @Autowired private ChatRoomService chatRoomService;

    // *---------------------------------------------------------------------------------------------------------------* [채팅방 등록]

    /**
     * @URI: /chat/room/new
     * @GET: 채팅방 등록 폼
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/room/new")
    public String roomNewGet(Model model) {
        logger.info("roomNewGet()");

        model.addAttribute("chatRoom", new ChatRoom());

        return "chat/room/new";
    }

    /**
     * @URI: /chat/room/new
     * @POST: 채팅방 등록
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/room/new")
    public String roomNewPost(@ModelAttribute("chatRoom") @Valid ChatRoom chatRoom, BindingResult result, Model model, RedirectAttributes rttr) {
        logger.info("roomNewPost()");

        if (result.hasErrors() || roomNameCheckPost(chatRoom) != 0) {
            model.addAttribute(FAILURE_MESSAGE, "채팅방 정보를 확인해주세요.");
            return "chat/room/new";

        } else {
            chatRoom.setUserNickname(getAuthenticatedUser().getNickname());
            chatRoomService.insertChatRoom(chatRoom);

            rttr.addFlashAttribute(SUCCESS_MESSAGE, "채팅방이 등록되었습니다.");
            return "redirect:/chat/room/list";
        }
    }

    // *---------------------------------------------------------------------------------------------------------------* [채팅방 조회]

    /**
     * @URI: /chat/room/list
     * @GET: 채팅방 목록 조회
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/room/list")
    public String roomListGet(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        logger.info("roomListGet()");

        model.addAttribute("roomList", chatRoomService.selectChatRoomList());
        model.addAttribute("userNickname", customUserDetails.getNickname());

        return "chat/room/list";
    }

    /**
     * @URI: /chat/room/{id}
     * @GET: 채팅방 상세 조회
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/room/{id}")
    public @ResponseBody Map<String, Object> roomGet(@PathVariable String id, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        logger.info("roomGet()");

        Map<String, Object> map = new HashMap<>();
        ChatRoom room = chatRoomService.selectChatRoomById(id);

        map.put("room", room);

        // /chat/room/4 접속 시, 계정 정보가 전체가 노출되므로 닉네임만 전달한다.
        // map.put("user", customUserDetails);
        map.put("userNickname", customUserDetails.getNickname());

        return map;
    }

    // *---------------------------------------------------------------------------------------------------------------* [채팅방 수정]

    /**
     * @URI: /chat/room/{id}/edit
     * @GET: 채팅방 수정 폼
     */
    @PreAuthorize("isAuthenticated()")
    @Identification(type = Identification.Type.CHAT_ROOM)
    @GetMapping("/room/{id}/edit")
    public String roomEditGet(@PathVariable("id") String id, Model model) {
        logger.info("roomEditGet()");

        model.addAttribute("chatRoom", chatRoomService.selectChatRoomById(id));

        return "chat/room/edit";
    }

    /**
     * @URI: /chat/room/{id}/edit
     * @POST: 채팅방 수정
     */
    @PreAuthorize("isAuthenticated()")
    @Identification(type = Identification.Type.CHAT_ROOM)
    @PostMapping("/room/{id}/edit")
    public String roomEditPost(@PathVariable("id") String id, @ModelAttribute("chatRoom") @Valid ChatRoom room, BindingResult result,
                               Model model, RedirectAttributes rttr) {
        logger.info("roomEditPost()");

        if (result.hasErrors()) {
            model.addAttribute(FAILURE_MESSAGE, "채팅방 정보를 확인해주세요.");
            return "chat/room/edit";

        } else {
            room.setUserNickname(getAuthenticatedUser().getNickname());
            chatRoomService.updateChatRoom(room);

            rttr.addFlashAttribute(SUCCESS_MESSAGE, "채팅방이 수정되었습니다.");
            return "redirect:/chat/room/list";
        }
    }

    // *---------------------------------------------------------------------------------------------------------------* [채팅방 삭제]

    /**
     * @URI: /chat/room/{id}/delete
     * @POST: 채팅방 삭제
     */
    @PreAuthorize("isAuthenticated()")
    @Identification(type = Identification.Type.CHAT_ROOM)
    @PostMapping("/room/{id}/delete")
    public @ResponseBody int roomDeletePost(@PathVariable("id") String id) {
        logger.info("roomDeletePost()");

        return chatRoomService.deleteChatRoom(id, getAuthenticatedUser().getNickname());
    }

    // *---------------------------------------------------------------------------------------------------------------* [채팅방 검사]

    /**
     * @URI: /chat/room/{id}/check
     * @POST: 채팅방 수정 및 삭제 본인 확인 검사
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/room/{id}/check")
    public @ResponseBody int roomCheckPost(@PathVariable("id") String id, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        logger.info("roomCheckPost()");

        // 채팅방 방장과 로그인 계정 일치 시, 1(True)
        return (chatRoomService.countChatRoomByIdAndUserNickname(id, customUserDetails.getNickname()) == 1) ? 1 : 0;
    }

    /**
     * @URI: /chat/room/{name}/check
     * @POST: 채팅방 이름 중복 검사
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/room/name/check")
    public @ResponseBody int roomNameCheckPost(@RequestBody ChatRoom chatRoom) {
        logger.info("roomNameCheckPost()");

        return chatRoomService.countChatRoomByName(chatRoom.getName());
    }
}