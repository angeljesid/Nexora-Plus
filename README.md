# 🚀 Nexora Plus - Sistema de Gestión Empresarial

**Nexora Plus** es una aplicación web desarrollada con **Spring Boot** que permite gestionar inventario, ventas y acceso de usuarios mediante roles.

El objetivo del proyecto es simular una solución real para pequeñas empresas, aplicando buenas prácticas de desarrollo backend.

---

## 🛠️ Tecnologías Utilizadas

### 🔹 Backend
- Java 17  
- Spring Boot  
- Spring Security (autenticación y roles)  
- Spring Data JPA  
- MySQL  

### 🔹 Frontend
- Thymeleaf  
- Tailwind CSS  
- FontAwesome  

### 🔹 Herramientas
- Maven  

---

## ✨ Funcionalidades

- 📦 Gestión de productos (crear, editar, eliminar)  
- 🛒 Registro de ventas  
- 📊 Control básico de inventario  
- 🔐 Sistema de login con roles:
  - ADMIN  
  - VENDEDOR  
  - BODEGUERO  
  - SECRETARIA  

---

## 🔐 Seguridad

El sistema implementa seguridad con **Spring Security**:

- Contraseñas encriptadas con BCrypt  
- Autenticación con `UserDetailsService`  
- Control de acceso por rutas según el rol  
- Redirección automática después del login según el tipo de usuario  

---

## 🏗️ Estructura del proyecto

El proyecto sigue una arquitectura por capas:

- `controller` → Manejo de peticiones HTTP  
- `service` → Lógica de negocio  
- `repository` → Acceso a datos  
- `domain` → Entidades  

---

## 🚀 Instalación y ejecución

### 1. Clonar repositorio

```bash
git clone https://github.com/angeljesid/Nexora-Plus.git
