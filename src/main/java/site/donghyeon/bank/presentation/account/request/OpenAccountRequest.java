package site.donghyeon.bank.presentation.account.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import site.donghyeon.bank.application.account.command.OpenAccountCommand;

import java.util.UUID;

@Schema(description = "계좌 등록 요청")
public record OpenAccountRequest(
        @Schema(description = "요청한 사용자 ID", example = "00000000-0000-0000-0000-000000000000")
        @NotBlank(message = "ID는 필수입니다.")
        UUID userId
) {
    public OpenAccountCommand toCommand() {
        return new OpenAccountCommand(this.userId);
    }
}
