# 🌐 Gateway Service

**Gateway central para el sistema de microservicios de bombas de insulina.**

## 🚀 Descripción

Este servicio actúa como punto de entrada único al sistema, ofreciendo:
- Enrutamiento inteligente
- Balanceo de carga
- Seguridad
- Logging centralizado
- Monitoreo

---

## 🛠️ Tecnologías

- Java 21  
- Spring Boot 3.4.5  
- Spring Cloud Gateway  
- Spring Cloud Netflix Eureka  
- Spring Cloud LoadBalancer  
- Lombok  

---

## 📋 Funcionalidades

### ✅ Enrutamiento Inteligente
- Proxy de peticiones a microservicios
- Balanceo de carga automático
- Descubrimiento de servicios (Eureka)
- Failover y Circuit Breaker (futuro)

### ✅ Funcionalidades Transversales
- Logging centralizado
- Rate limiting *(futuro)*
- Autenticación *(futuro)*

### ✅ Monitoreo
- Health checks
- Métricas de latencia y throughput
- Logging de rutas y errores
- Integración con Eureka Dashboard

---

## 🌐 Rutas Configuradas

| Servicio         | Método | Ruta Gateway              | Destino            | Descripción                |
|------------------|--------|----------------------------|---------------------|----------------------------|
| **Patient**      | GET/POST | `/api/patients/**`       | `patient-service:8081` | Gestión de pacientes       |
| **Device**       | GET/POST | `/api/devices/**`        | `device-service:8082` | Gestión de dispositivos    |
| **Reading**      | GET/POST | `/api/readings/**`       | `reading-service:8083` | Monitoreo de glucosa       |

---

## ⚙️ Configuración

### 🔌 Puerto
```properties
server.port=8087
```

### 🧭 Eureka Client
```properties
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
eureka.instance.prefer-ip-address=true
```

---

## ▶️ Ejecución

1. Iniciar **Eureka Server** (puerto 8761)
2. Iniciar los microservicios:
   - Patient Service (8081)
   - Device Service (8082)
   - Reading Service (8083)
3. Ejecutar el Gateway:
```bash
mvn spring-boot:run
```
4. Acceder a: [http://localhost:8087](http://localhost:8087)

---

## 🔗 Endpoints

### Patient Service
```
GET http://localhost:8087/api/patients
GET http://localhost:8087/api/patients/1
GET http://localhost:8087/api/patients/medical/MED123456
```

### Device Service
```
GET http://localhost:8087/api/devices
GET http://localhost:8087/api/devices/1
GET http://localhost:8087/api/devices/serial/DEV001
```

### Reading Service
```
GET http://localhost:8087/api/readings
GET http://localhost:8087/api/readings/device/1/statistics
GET http://localhost:8087/api/readings/requiring-action
```

---

## 🔄 Load Balancing

- **Round Robin** por defecto  
- Descubrimiento con Eureka  
- Health Check automático  
- Soporte para múltiples instancias:

```bash
java -jar patient-service.jar --server.port=8081
java -jar patient-service.jar --server.port=8091
java -jar patient-service.jar --server.port=8092
```

---

## 🛡️ Seguridad

### Rate Limiting (futuro)
```yaml
filters:
  - name: RequestRateLimiter
    args:
      redis-rate-limiter.replenishRate: 10
      redis-rate-limiter.burstCapacity: 20
```

---

## 📊 Métricas y Monitoreo

- Health Check:  
  `GET /actuator/health`

- Métricas:  
  `GET /actuator/metrics`

- Información de rutas:  
  `GET /actuator/gateway/routes`

---

## 🔧 Configuración Avanzada

### Timeouts
```yaml
spring:
  cloud:
    gateway:
      httpclient:
        connect-timeout: 5000
        response-timeout: 30s
```

### Retry Policy
```yaml
filters:
  - name: Retry
    args:
      retries: 3
      methods: GET,POST
```

### Circuit Breaker (futuro)
```yaml
filters:
  - name: CircuitBreaker
    args:
      name: patientServiceCB
      fallbackUri: forward:/fallback/patients
```

---

## 📝 Logging

### Configuración
```properties
logging.level.org.springframework.cloud.gateway=DEBUG
logging.level.reactor.netty=DEBUG
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} - %msg%n
```

### Logs Importantes
- Peticiones entrantes/salientes
- Errores de enrutamiento
- Tiempos de respuesta
- Estado de servicios

---

## 🎯 Beneficios

| Sistema | Desarrollo | Producción |
|--------|-------------|-------------|
| Punto de entrada único | URLs simplificadas | Escalabilidad horizontal |
| Balanceo automático     | Logging unificado  | Tolerancia a fallos       |
| Service Discovery       | Debugging sencillo | Métricas centralizadas    |
| Manejo de errores       |                    | Seguridad centralizada    |

---

## 🔮 Futuro

- 🔐 **Autenticación y Autorización**
- 🔢 **Versionado de API**

---

## 👨‍💻 Autor

**Rafael Gamero Arrabal**  
[🔗 LinkedIn](https://www.linkedin.com/in/rafael-gamero-arrabal-619200186/)
⭐ Gateway central del Sistema de Microservicios para Bombas de Insulina
