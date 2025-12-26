package site.donghyeon.bank.presentation.account.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.donghyeon.bank.application.account.AccountUseCase;
import site.donghyeon.bank.presentation.account.request.CloseAccountRequest;
import site.donghyeon.bank.presentation.account.request.DepositRequest;
import site.donghyeon.bank.presentation.account.request.OpenAccountRequest;
import site.donghyeon.bank.presentation.account.response.DepositResponse;
import site.donghyeon.bank.presentation.account.response.OpenAccountResponse;

import java.util.UUID;


@RestController
@RequestMapping("/account")
@Tag(name = "계좌", description = "계좌 관련 API 입니다.")
public class AccountController {

    private final AccountUseCase accountUseCase;

    public AccountController(AccountUseCase accountUseCase) {
        this.accountUseCase = accountUseCase;
    }

    @PostMapping()
    @Operation(
            summary = "계좌 개설",
            description = "<p>계좌를 개설합니다.</p>" +
                    "<p>TODO: keycloak 도입 시 인증을 통해 파라미터를 받도록 수정합니다.</p>"
    )
    public ResponseEntity<OpenAccountResponse> openAccount(
            @RequestBody OpenAccountRequest request
    ) {
        return ResponseEntity.ok(
                OpenAccountResponse.from(
                        accountUseCase.openAccount(request.toCommand())
                )
        );
    }

    @DeleteMapping()
    @Operation(
            summary = "계좌 해지",
            description = "<p>계좌를 해지합니다.</p>" +
                    "<p>TODO: keycloak 도입 시 인증을 통해 파라미터를 받도록 수정합니다.</p>"
    )
    public ResponseEntity<Void> closeAccount(
            @RequestBody CloseAccountRequest request
    ) {
        accountUseCase.closeAccount(request.toCommand());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{accountId}/deposit")
    @Operation(
            summary = "계좌 입금",
            description = "<p>계좌에 금액을 입금합니다.</p>" +
                    "<p> 입금에 성공한 경우, 거래 내역의 PK를 반환한다. </p>" +
                    "<p>TODO: 무통장 입금 등을 고려했을 때 잔고 표시 여부에 대한 논의</p>"
    )
    public ResponseEntity<DepositResponse> deposit(
            @PathVariable UUID accountId,
            @RequestBody DepositRequest request
    ) {
        return ResponseEntity.accepted().body(
                DepositResponse.from(
                    accountUseCase.deposit(request.toCommand(accountId))
                )
        );
    }
}
