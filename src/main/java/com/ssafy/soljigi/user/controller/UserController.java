package com.ssafy.soljigi.user.controller;

import com.ssafy.soljigi.user.dto.request.AuthOneRequest;
import com.ssafy.soljigi.user.dto.response.Response;
import com.ssafy.soljigi.user.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {
    private final UserServiceImpl userService;
    @PostMapping("/account")
    public Response<?> findByAccountNumber(@RequestBody AuthOneRequest request){
        return Response.success(userService.findByAccount(request.getAccountNumber()));
    }

}
