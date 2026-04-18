package com.clasedominio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    // Esta es la landing page con Tailwind
    @GetMapping("/")
    public String loading() {
        return "vista/loading"; 
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, 
                        Model model) {
        if (error != null) {
            model.addAttribute("mensajeError", "Usuario o contraseña incorrectos.");
        }
        return "vista/login";
    }
}
//HomeController (El Jefe): Es quien recibe la orden del navegador y llama al Service.
//  Si el Controller intenta usar una función que no está en la Interfaz, te dará un 
// error de compilación rojo en VS Code porque estás pidiendo algo que "no existe en el contrato".