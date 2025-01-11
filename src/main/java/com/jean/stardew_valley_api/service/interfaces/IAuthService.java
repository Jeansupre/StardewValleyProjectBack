package com.jean.stardew_valley_api.service.interfaces;

import com.jean.stardew_valley_api.dto.LoginRequestDTO;

public interface IAuthService {

    String login(LoginRequestDTO loginRequestDTO);
}
