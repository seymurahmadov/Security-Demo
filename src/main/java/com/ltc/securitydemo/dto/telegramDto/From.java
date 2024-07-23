package com.ltc.securitydemo.dto.telegramDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class From {
    public int id;
    public boolean is_bot;
    public String first_name;
    public String last_name;
    public String username;
    public String language_code;
}
