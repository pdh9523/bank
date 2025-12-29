package site.donghyeon.bank.presentation.user.request;

import io.swagger.v3.oas.annotations.media.Schema;
import site.donghyeon.bank.application.user.command.GetUserInfoCommand;
import site.donghyeon.bank.presentation.common.resolver.CurrentUser;

import java.util.UUID;

@Schema(description = "회원 조회 요청 - 토큰에서 추출")
public record GetUserInfoRequest(
        UUID userId,
        String email
) {
    public static GetUserInfoRequest from(CurrentUser currentUser) {
        return new GetUserInfoRequest(
                currentUser.userId(),
                currentUser.email()
        );
    }

    public GetUserInfoCommand toCommand() {
        return new GetUserInfoCommand(
                this.userId,
                this.email
        );
    }
}
