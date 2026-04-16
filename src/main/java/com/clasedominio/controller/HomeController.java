    package com.clasedominio.controller;

    import com.clasedominio.domain.Empleado;
    import com.clasedominio.service.empleado.IEmpleadoService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.security.core.Authentication;
    import org.springframework.web.bind.annotation.RequestParam;

    @Controller
    public class HomeController {

        @Autowired
        private IEmpleadoService empleadoService;

        @GetMapping("/")
        public String login(Model model, Authentication authentication) {
            if (authentication != null) {
                // Buscamos al empleado completo para tener nombre y apellido
                String username = authentication.getName();
                Empleado empleado = empleadoService.buscarPorUsername(username);
                
                if (empleado != null) {
                    model.addAttribute("nombreCompleto", empleado.getNombre() + " " + empleado.getApellido());
                    model.addAttribute("rolActual", empleado.getRol());
                }
                model.addAttribute("usuarioActual", username);
            } else {
                model.addAttribute("usuarioActual", "No autenticado");
            }
            
            return "vista/inicio";
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