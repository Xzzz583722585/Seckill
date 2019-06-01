package com.xqz.seckill.utils.encryptor;

import org.springframework.util.DigestUtils;

import java.util.Random;

public class Encyptor {
    public static String md5(String str, String salt){
        StringBuffer sb = new StringBuffer(str);
        sb.reverse();
        sb.insert(0, salt.substring(3, 4));
        sb.insert(1, salt.substring(5, 6));
        sb.insert(1, salt.substring(5, 6));
        sb.append(salt.substring(2, 3));
        sb.append(salt.substring(0, 1));
        return DigestUtils.md5DigestAsHex(sb.toString().getBytes());
    }

    public static void main(String[] args) {
        Random r = new Random();
        StringBuffer ranSalt = new StringBuffer();
        for(int i = 0; i < 6; i++){
            ranSalt.append((char)(r.nextInt(58) + 65));
        }
        System.out.println(ranSalt);
        System.out.println(Encyptor.md5("744fea86f02af86435744fdfb869e747", ranSalt.toString()));
    }
}
