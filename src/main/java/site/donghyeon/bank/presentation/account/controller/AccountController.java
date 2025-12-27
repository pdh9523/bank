package site.donghyeon.bank.presentation.account.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.donghyeon.bank.application.account.AccountUseCase;
import site.donghyeon.bank.application.account.AccountOperationUseCase;
import site.donghyeon.bank.presentation.account.request.*;
import site.donghyeon.bank.presentation.account.response.DepositResponse;
import site.donghyeon.bank.presentation.account.response.OpenAccountResponse;
import site.donghyeon.bank.presentation.account.response.TransferResponse;
import site.donghyeon.bank.presentation.account.response.WithdrawalResponse;

import java.util.UUID;


@RestController
@RequestMapping("/account")
@Tag(name = "계좌", description = "계좌 관련 API 입니다.")
public class AccountController {

    private final AccountUseCase accountUseCase;
    private final AccountOperationUseCase accountOperationUseCase;

    public AccountController(
            AccountUseCase accountUseCase,
            AccountOperationUseCase accountOperationUseCase
    ) {
        this.accountUseCase = accountUseCase;
        this.accountOperationUseCase = accountOperationUseCase;
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
                    "<p> 입금에 성공한 경우, 거래 내역의 PK를 반환합니다. </p>" +
                    "<p>TODO: 무통장 입금 등을 고려했을 때 잔고 표시 여부에 대한 논의</p>"
    )
    public ResponseEntity<DepositResponse> deposit(
            @PathVariable UUID accountId,
            @RequestBody DepositRequest request
    ) {
        return ResponseEntity.accepted().body(
                DepositResponse.from(
                    accountOperationUseCase.deposit(request.toCommand(accountId))
                )
        );
    }

    @PostMapping("/{accountId}/withdrawal")
    @Operation(
            summary = "계좌 출금",
            description = "<p>계좌에서 금액을 출금합니다.</p>" +
                    "<p> 출금에 성공한 경우, 거래 내역의 PK를 반환합니다. </p>"
    )
    public ResponseEntity<WithdrawalResponse> withdrawal(
            @PathVariable UUID accountId,
            @RequestBody WithdrawalRequest request
    ) {
        return ResponseEntity.accepted().body(
                WithdrawalResponse.from(
                        accountOperationUseCase.withdrawal(request.toCommand(accountId))
                )
        );
    }

    @PostMapping("/{accountId}/transfer")
    @Operation(
            summary = "계좌 이체",
            description = "<p>내 계좌에서 상대 계좌로 금액을 이체합니다.</p>" +
                    "<p> 이체에 성공한 경우, 거래 내역의 PK를 반환합니다. </p>"
    )
    public ResponseEntity<TransferResponse> transfer(
            @PathVariable UUID accountId,
            @RequestBody TransferRequest request
    ) {
        return ResponseEntity.accepted().body(
                TransferResponse.from(
                        accountOperationUseCase.transfer(request.toCommand(accountId))
                )
        );
    }
}
