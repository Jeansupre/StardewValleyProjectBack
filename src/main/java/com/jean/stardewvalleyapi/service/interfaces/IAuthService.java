package com.jean.stardewvalleyapi.service.interfaces;

import com.jean.stardewvalleyapi.dto.LoginRequestDTO;

public interface IAuthService {

    String login(LoginRequestDTO loginRequestDTO);
}
