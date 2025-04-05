package com.seoki.api.member.api;

import com.seoki.api.common.api.dto.response.ApiResponse;
import com.seoki.api.member.api.dto.request.MemberCreateRequest;
import com.seoki.api.member.application.command.MemberCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    public final MemberCommandService memberCommandService;

    @PostMapping
    public ApiResponse<Long> create(
            @Valid @RequestBody MemberCreateRequest request
    ) {
        long memberId = memberCommandService.create(request.email());
        return ApiResponse.success(memberId);
    }
}
