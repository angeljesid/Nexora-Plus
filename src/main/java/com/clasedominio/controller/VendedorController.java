package com.clasedominio.controller;

import com.clasedominio.domain.*;
import com.clasedominio.service.empleado.IEmpleadoService;
import com.clasedominio.service.producto.IProductoService;
import com.clasedominio.service.individuo.IIndividuoService;
import com.clasedominio.service.venta.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/vendedor")
public class VendedorController {

    @Autowired
    private IEmpleadoService empleadoService;
    @Autowired
    private IProductoService productoService;
    @Autowired
    private IIndividuoService individuoService;
    @Autowired
    private IVentaService ventaService;

    @GetMapping("/inicio")
    public String puntoDeVenta(Model model, Authentication authentication) {
        if (authentication != null) {
            String username = authentication.getName();
            Empleado empleado = empleadoService.buscarPorUsername(username);
            if (empleado != null) {
                model.addAttribute("vendedor", empleado.getNombre() + " " + empleado.getApellido());
            }
        }
        return "vendedor/punto_venta";
    }

    // BUSCAR CLIENTE
    @GetMapping("/buscar-cliente/{cedula}")
    @ResponseBody
    public ResponseEntity<Individuo> buscarCliente(@PathVariable String cedula) {
        Individuo cliente = individuoService.buscarPorCedula(cedula);
        if (cliente == null) {
            Individuo anonimo = new Individuo();
            anonimo.setIdIndividuo(null);
            anonimo.setNombre("Cliente");
            anonimo.setApellido("Final");
            anonimo.setCedula(cedula);
            return ResponseEntity.ok(anonimo);
        }
        return ResponseEntity.ok(cliente);
    }

    // BUSCAR POR CÓDIGO DE BARRAS (Para el botón "Agregar" y escáner)
    @GetMapping("/buscar-producto/{codigo}")
    @ResponseBody
    public ResponseEntity<Producto> buscarPorCodigo(@PathVariable String codigo) {
        // Usamos el método de tu ProductoService
        Producto p = productoService.buscarPorCodigo(codigo); 
        if (p != null) {
            return ResponseEntity.ok(p);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // BUSCAR POR NOMBRE (Para el buscador predictivo)
    @GetMapping("/buscar-nombre")
    @ResponseBody
    public List<Producto> buscarPorNombre(@RequestParam ("term") String term) {
        // IMPORTANTE: Este método debe devolver una lista filtrada por el nombre
        // usando findByNombreContaining en tu DAO
        return productoService.buscarProducto(term);
    }

    // GUARDAR VENTA
    @PostMapping("/guardar-venta")
    @ResponseBody
    public ResponseEntity<?> registrarVenta(@RequestBody Venta venta) {
        try {
            if (venta.getDetalles() == null || venta.getDetalles().isEmpty()) {
                return ResponseEntity.badRequest().body("{\"mensaje\": \"Carrito vacío\"}");
            }
            ventaService.guardar(venta);
            return ResponseEntity.ok("{\"mensaje\": \"Venta registrada y stock actualizado\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("{\"mensaje\": \"" + e.getMessage() + "\"}");
        }
    }
}