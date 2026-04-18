package com.clasedominio.controller;

import com.clasedominio.domain.Empleado;
import com.clasedominio.service.empleado.IEmpleadoService;
import com.clasedominio.service.producto.IProductoService;
import com.clasedominio.service.individuo.IIndividuoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vendedor")
public class VendedorController {

    @Autowired
    private IEmpleadoService empleadoService;

    @Autowired
    private IProductoService productoService;

    @Autowired
    private IIndividuoService individuoService;

    //=========================================
    // En Proceso de creación :v voy a colocar un inicio en ves del sashboard
    //=========================================
    @GetMapping("/dashboard")
    public String dashboardVendedor(Model model, Authentication authentication) {
        if (authentication != null) {
            String username = authentication.getName();
            Empleado empleado = empleadoService.buscarPorUsername(username);
            if (empleado != null) {
                model.addAttribute("nombreCompleto", empleado.getNombre() + " " + empleado.getApellido());
            }
        }
        
  
        model.addAttribute("totalProductos", productoService.listarProductos().size());
        model.addAttribute("totalClientes", individuoService.listarIndividuos().size());
        
        return "vista/vendedor/dashboard_vendedor";
    }

    @GetMapping("/productos")
    public String listarProductos(Model model) {
        model.addAttribute("listaProductos", productoService.listarProductos());
        return "vista/vendedor/productos_vendedor";
    }

    @GetMapping("/clientes")
    public String listarClientes(Model model) {
        model.addAttribute("lista", individuoService.listarIndividuos());
        return "vista/vendedor/clientes_vendedor";
    }
}