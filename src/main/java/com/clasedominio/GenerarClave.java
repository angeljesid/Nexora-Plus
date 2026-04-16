package com.clasedominio;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class GenerarClave {
    public static void main(String[]args){
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        System.out.println("Clave Administrador:" + encoder.encode("12345"));
        System.out.println("Clave Secretaria:" + encoder.encode("12345"));
        System.out.println("Clave Vendedor:" + encoder.encode("12345"));
    }
}
