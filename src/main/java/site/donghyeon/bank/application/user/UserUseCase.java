package site.donghyeon.bank.application.user;

import site.donghyeon.bank.application.user.command.GetUserInfoCommand;
import site.donghyeon.bank.application.user.result.GetUserInfoResult;

public interface UserUseCase {
    GetUserInfoResult getUserInfo(GetUserInfoCommand command);
}
