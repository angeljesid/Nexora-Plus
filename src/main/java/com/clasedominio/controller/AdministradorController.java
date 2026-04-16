package com.clasedominio.controller;

// Modelos
import com.clasedominio.domain.Individuo;
import com.clasedominio.domain.Producto;
import com.clasedominio.domain.Empleado;
import com.clasedominio.domain.Proveedor;

// Servicios
import com.clasedominio.service.individuo.IIndividuoService;
import com.clasedominio.service.producto.IProductoService;
import com.clasedominio.service.empleado.IEmpleadoService;
import com.clasedominio.service.proveedor.IProveedorService;

// Spring y Seguridad
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

// Excel y Servlet
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import java.io.IOException;


@Controller
@RequestMapping("/admin")
public class AdministradorController {

    @Autowired
    private IIndividuoService individuoService;

    @Autowired
    private IProductoService productoService;

    @Autowired
    private IEmpleadoService empleadoService;

    @Autowired
    private IProveedorService proveedorService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ==========================================
    // SECCIÓN 1: INDIVIDUOS (CLIENTES/GENERAL)
    // ==========================================

    @GetMapping("/individuos")
    public String listarIndividuos(Model model, Authentication authentication) {
        var individuos = individuoService.listarIndividuos();
        model.addAttribute("lista", individuos);
        
        boolean esAdmin = authentication != null && authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().toLowerCase().contains("admin"));
        model.addAttribute("esAdmin", esAdmin);
        
        return "vista/individuo";
    }

    @GetMapping("/nuevo_i")
    public String nuevoIndividuo(Model model) {
        model.addAttribute("individuo", new Individuo());
        return "formul/formIndividuo";
    }

    @PostMapping("/guardar_i")
    public String guardarIndividuo(@Valid @ModelAttribute("individuo") Individuo individuo, BindingResult result) {
        if (result.hasErrors()) return "formul/formIndividuo";
        individuoService.guardarIndividuo(individuo);
        return "redirect:/admin/individuos";
    }
    
    @GetMapping("/editar_i")
    public String editar(@RequestParam("id") Long id, Model model) {
        Individuo individuo = new Individuo();
        individuo.setIdIndividuo(id);
        individuo = individuoService.buscarIndividuo(individuo);
        model.addAttribute("individuo", individuo);
        return "/formul/formIndividuo";
    }

    @GetMapping("/eliminar_i")
    public String eliminar(@RequestParam("id") Long id) {
        individuoService.eliminarIndividuo(id);
        return "redirect:/individuos";
    }

    @GetMapping("/ver_i")
    public String detalle(@RequestParam("id") Long id, Model model) {
        Individuo individuo = new Individuo();
        individuo.setIdIndividuo(id);
        individuo = individuoService.buscarIndividuo(individuo);
        model.addAttribute("individuo", individuo);
        return "/ver/verIndividuo";
    }
    

    // ==========================================
    // SECCIÓN 2: EMPLEADOS (CARGOS)
    // ==========================================

    @GetMapping("/empleados")
    public String listarEmpleados(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        Iterable<Empleado> empleados = (keyword != null && !keyword.isEmpty()) 
                ? empleadoService.buscarPorNombre(keyword) 
                : empleadoService.listarEmpleados();

        model.addAttribute("listaEmpleados", empleados);
        model.addAttribute("keyword", keyword);
        return "vista/empleados";
    }

    @GetMapping("/nuevo_e")
    public String nuevoEmpleado(Model model) {
        var roles = java.util.Arrays.stream(Empleado.Rol.values())
            .filter(r -> !r.name().equals("ADMIN")) 
            .toList();
        model.addAttribute("Roles", roles);
        model.addAttribute("empleado", new Empleado());
        return "formul/formEmpleado";
    }

    @PostMapping("/guardar_e")
    public String guardarEmpleado(@Valid @ModelAttribute("empleado") Empleado empleado, BindingResult result) {
        if (result.hasErrors()) return "formul/formEmpleado";

        if (empleado.getPassword() != null && !empleado.getPassword().isEmpty()) {
        empleado.setPassword(passwordEncoder.encode(empleado.getPassword()));
    }
        empleadoService.guardarEmpleado(empleado);
        return "redirect:/admin/empleados";
    }

    @GetMapping("/editar_e")
    public String editarEmpleado(@RequestParam("id")Integer id, Model model) {
        Empleado empleado = empleadoService.buscarEmpleado(id);
        model.addAttribute("empleado", empleado);
        model.addAttribute("Roles", Empleado.Rol.values());
        return "/formul/formEmpleado";
    }

    @GetMapping("/desactivar_e")
    public String desactivarEmpleado(@RequestParam("id") Integer id) {
        Empleado emp = empleadoService.buscarEmpleado(id);
        if (emp != null) {
            emp.setActivo(false);
            empleadoService.guardarEmpleado(emp);
        }
        return "redirect:/admin/empleados";
    }

    @GetMapping("/ver_e")
    public String verEmpleado(@RequestParam("id") Integer id, Model model) {
        Empleado emp = empleadoService.buscarEmpleado(id);
        if (emp == null) return "redirect:/admin/empleados";
        model.addAttribute("empleado", emp);
        return "ver/verEmpleado";
    }

    // ==========================================
    // SECCIÓN 3: PRODUCTOS (INVENTARIO)
    // ==========================================

    @GetMapping("/productos")
public String listarProductos(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
    Iterable<Producto> productos = (keyword != null && !keyword.isEmpty()) 
            ? productoService.buscarProducto(keyword) 
            : productoService.listarProductos();

    model.addAttribute("listaProductos", productos);
    model.addAttribute("keyword", keyword);
    return "vista/productos";
}

   @GetMapping("/nuevo_p")
public String nuevoProducto(Model model) {
    model.addAttribute("producto", new Producto());
    // ESTA LÍNEA ES LA QUE LLENA EL SELECT:
    model.addAttribute("listaProveedores", proveedorService.listarProveedores()); 
    return "formul/formProducto";
}

    @PostMapping("/guardar_p")
    public String guardarProducto(@Valid @ModelAttribute("producto") Producto producto, BindingResult result) {
        if (result.hasErrors()) return "formul/formProducto";
        productoService.guardarProducto(producto);
        return "redirect:/admin/productos";
    }

       @GetMapping("/editar_p")
public String editarProducto(@RequestParam("id") Integer id, Model model) {
    model.addAttribute("producto", productoService.buscarProducto(id));
    // También la necesitas al editar para poder cambiar el proveedor
    model.addAttribute("listaProveedores", proveedorService.listarProveedores());
    return "formul/formProducto";
}

    @GetMapping("/eliminar_p")
    public String eliminarProducto(@RequestParam("id") Integer id) {
        productoService.eliminarProducto(id);
        return "redirect:/admin/productos";
    }

        @GetMapping("/ver_P")
    public String verProducto(@RequestParam("id") Integer id, Model model) {
        Producto producto = productoService.buscarProducto(id);
        model.addAttribute("producto", producto);
        return "/ver/verProducto";
    }

//============================================
// SECCIÓN 4: PROVEEDORES (GESTIÓN DE PROVEEDORES)
// ==========================================

   @GetMapping("/proveedores")
public String listarProveedor(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
    Iterable<Proveedor> proveedores = (keyword != null && !keyword.isEmpty()) 
            ? proveedorService.buscarPorNombre(keyword) 
            : proveedorService.listarProveedores();

    model.addAttribute("listaProveedores", proveedores);
    model.addAttribute("keyword", keyword);
    return "vista/proveedores"; // OK
}

@GetMapping("/nuevo_prov")
public String nuevoProveedor(Model model) {
    model.addAttribute("proveedor", new Proveedor());
    return "formul/formProveedor"; // QUITADA LA BARRA INICIAL
}

@PostMapping("/guardar_prov")
public String guardarProveedor(@Valid @ModelAttribute("proveedor") Proveedor proveedor, BindingResult result) {
    if (result.hasErrors()) {
        return "formul/formProveedor"; // QUITADA LA BARRA INICIAL
    }
    proveedorService.guardarProveedor(proveedor);
    return "redirect:/admin/proveedores"; // Los redirect SÍ llevan barra porque son URLs, no archivos
}

@GetMapping("/editar_prov")
public String editarProveedor(@RequestParam("id") Integer id, Model model) {
    Proveedor proveedor = proveedorService.buscarProveedor(id);
    model.addAttribute("proveedor", proveedor);
    return "formul/formProveedor"; // QUITADA LA BARRA INICIAL
}

@GetMapping("/eliminar_prov")
public String eliminarProveedor(@RequestParam("id") Integer id) {
    proveedorService.eliminarProveedor(id);
    return "redirect:/admin/proveedores"; 
}

@GetMapping("/ver_prov") 
public String verProveedor(@RequestParam("id") Integer id, Model model) {
    Proveedor proveedor = proveedorService.buscarProveedor(id);
    model.addAttribute("proveedor", proveedor);
    return "ver/verProveedor"; // QUITADA LA BARRA INICIAL
}

// ==========================================
    // SECCIÓN 5: panel de control (DASHBOARD)
    // ==========================================

        @GetMapping("/dashboard")
        public String dashboard(Model model) {
           long alertaStock = productoService.contarStockBajo();

            model.addAttribute("alertaStock", alertaStock);
            model.addAttribute("productosCriticos", productoService.obtenerTopStockBajo());
            
            model.addAttribute("totalP", productoService.listarProductos().size());
            model.addAttribute("totalProv", proveedorService.listarProveedores().size());
            model.addAttribute("totalC", individuoService.listarIndividuos().size());

            return "vista/dashboard";
        }
    // ==========================================
    // SECCIÓN 6: EXPORTACIÓN EXCEL
    // ==========================================

    @GetMapping("/exportar-excel")
    public void exportarAExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=Reporte_Nexora_Plus.xlsx");

        var individuos = individuoService.listarIndividuos();

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Reporte General");
            Row header = sheet.createRow(0);
            String[] cols = {"ID", "Nombre", "Apellido", "Correo"};
            
            for (int i = 0; i < cols.length; i++) header.createCell(i).setCellValue(cols[i]);

            int rowIdx = 1;
            for (var ind : individuos) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(ind.getIdIndividuo());
                row.createCell(1).setCellValue(ind.getNombre());
                row.createCell(2).setCellValue(ind.getApellido());
                row.createCell(3).setCellValue(ind.getCorreo());
            }
            workbook.write(response.getOutputStream());
        }
    }
}
// FIN DE CLASE