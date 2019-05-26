package com.xqz.seckill.security;

import com.xqz.seckill.utils.encryptor.Encyptor;
import com.xqz.seckill.utils.string.StringUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class LoginPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        if(rawPassword == null){
            return "";
        }
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String presentedPassword) {
        if(StringUtil.isEmpty(rawPassword.toString()) || StringUtil.isEmpty(presentedPassword)){
            return false;
        }

        String[] rawPwdAndSalt = rawPassword.toString().split("\0");
        String encodedPassword = Encyptor.md5(presentedPassword, rawPwdAndSalt[1]);

        if(rawPwdAndSalt[0].equals("{noop}" + encodedPassword)){
            return true;
        }
        return false;
    }
}
