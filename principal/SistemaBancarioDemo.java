/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package principal;

/**
 *
 * @author pc
 */
import modelo.abstractas.Cuenta;
import modelo.abstractas.Empleado;
import modelo.banco.Banco;
import modelo.banco.Transaccion;
import modelo.cuentas.CuentaAhorros;
import modelo.cuentas.CuentaCorriente;
import modelo.cuentas.CuentaCredito;
import modelo.empleados.AsesorFinanciero;
import modelo.empleados.Cajero;
import modelo.empleados.GerenteSucursal;
import modelo.enums.EstadoTransaccion;
import modelo.enums.TipoDocumento;
import modelo.enums.Turno;
import modelo.excepciones.*;
import modelo.personas.ClienteEmpresarial;
import modelo.personas.ClienteNatural;
import java.time.LocalDate; 

public class SistemaBancarioDemo {

    public static void main(String[] args) {
        Banco banco = new Banco("Banco Nacional SGB");
        titulo("SISTEMA DE GESTION BANCARIA");
        
        seccion(1, "Registro de clientes");
        ClienteNatural c1 = new ClienteNatural(
                "C001", "Laura", "Martinez",
                LocalDate.of(1990, 5, 12),
                "laura@email.com",
                TipoDocumento.CEDULA, "1098765432");
        
        ClienteNatural c2 = new ClienteNatural(
                "C002", "Pedro", "Gomez",
                LocalDate.of(1985, 3, 20),
                "pedro@email.com",
                TipoDocumento.CEDULA, "1034567890");
        
        ClienteEmpresarial c3 = new ClienteEmpresarial(
                "C003", "Ana", "Reyes",
                LocalDate.of(1978, 8, 1),
                "ana@empresa.com",
                "900123456-7", "Tech Solutions SAS",
                "Ana Reyes");
        
        try {
            banco.registrarCliente(c1);
            banco.registrarCliente(c2);
            banco.registrarCliente(c3);
            System.out.println(" " + c1.obtenerResumen());
            System.out.println(" " + c2.obtenerResumen());
            System.out.println(" " + c3.obtenerResumen());
        } catch (CapacidadExcedidaException e) {
            System.out.println("error " + e.getMessage());
        }
        
        seccion(2, "Apertura de cuentas");
        CuentaAhorros   cAhorros   = new CuentaAhorros("AH-001", 500000, 0.05, 3);
        CuentaCorriente cCorriente = new CuentaCorriente("CC-001", 1000000, 200000, 15000);
        CuentaCredito   cCredito   = new CuentaCredito("CR-001", 5000000, 0.18);
        try {
            banco.abrirCuenta("C001", cAhorros);
            banco.abrirCuenta("C002", cCorriente);
            banco.abrirCuenta("C003", cCredito);
            System.out.println(" " + cAhorros.obtenerResumen());
            System.out.println(" " + cCorriente.obtenerResumen());
            System.out.println(" " + cCredito.obtenerResumen());
        } catch (ClienteNoEncontradoException | CapacidadExcedidaException e) {
            System.out.println("error " + e.getMessage());
        }
        
        seccion(3, "Depositos — exitoso y cuenta bloqueada");
        try {
            cAhorros.depositar(200000);
            System.out.println(" Deposito exitoso. Saldo: $" 
                    + cAhorros.consultarSaldo());
        } catch (CuentaBloqueadaException e) {
            System.out.println("error " + e.getMessage());
        }
        
        cAhorros.setBloqueada(true);
        try {
            cAhorros.depositar(50000);
        } catch (CuentaBloqueadaException e) {
            System.out.println(" CuentaBloqueadaException → " + e.getMessage());
        }
        cAhorros.setBloqueada(false);
        
        seccion(4, "Retiros  exitoso y saldo insuficiente");
        try {
            cAhorros.retirar(100000);
            System.out.println(" Retiro exitoso. Saldo: $" 
                    + cAhorros.consultarSaldo());
        } catch (SaldoInsuficienteException | CuentaBloqueadaException e) {
            System.out.println("error " + e.getMessage());
        }
        
        try {
            cAhorros.retirar(99999999);
        } catch (SaldoInsuficienteException e) {
            System.out.println(" SaldoInsuficienteException  Disponible: $"
                    + e.getSaldoActual()
                    + " | Solicitado: $" + e.getMontoSolicitado());
        } catch (CuentaBloqueadaException e) {
            System.out.println("error " + e.getMessage());
        }
        
        seccion(5, "Transferencia entre cuentas");
        try {
            double monto = 50000;
            cCorriente.retirar(monto);
            cAhorros.depositar(monto);
            Transaccion tx = new Transaccion(
                    "TX-001", cCorriente, cAhorros, monto, "Transferencia");
            tx.cambiarEstado(EstadoTransaccion.PROCESANDO);
            tx.cambiarEstado(EstadoTransaccion.COMPLETADA);
            System.out.println(" " + tx.generarComprobante());
        } catch (SaldoInsuficienteException | CuentaBloqueadaException e) {
            System.out.println("error " + e.getMessage());
        }
        
        seccion(6, "Polimorfismo  calcularSalario()");
        Cajero cajero = new Cajero(
                "E001", "Carlos", "Ruiz",
                LocalDate.of(1995, 6, 10),
                "carlos@banco.com", "LEG-001",
                LocalDate.of(2020, 1, 5),
                2000000, Turno.MAÑANA, "Sucursal Norte");
        cajero.registrarTransaccion();
        cajero.registrarTransaccion();
        cajero.registrarTransaccion();
        
        AsesorFinanciero asesor = new AsesorFinanciero(
                "E002", "Maria", "Lopez",
                LocalDate.of(1988, 9, 3),
                "maria@banco.com", "LEG-002",
                LocalDate.of(2018, 3, 1),
                3500000, 500000, 800000);
        asesor.setVentasMensuales(900000);
        
        GerenteSucursal gerente = new GerenteSucursal(
                "E003", "Jorge", "Sanchez",
                LocalDate.of(1975, 2, 14),
                "jorge@banco.com", "LEG-003",
                LocalDate.of(2010, 6, 1),
                6000000, "Sucursal Norte", 500000000);
        
        Empleado[] empleados = { cajero, asesor, gerente };
        for (Empleado emp : empleados)
            System.out.println(" " + emp.obtenerTipo()
                    + " salario: $" + emp.calcularSalario());
        
        seccion(7, "Polimorfismo  calcularInteres()");
        Cuenta[] cuentas = { cAhorros, cCorriente, cCredito };
        for (Cuenta c : cuentas)
            System.out.println(" " + c.getTipoCuenta()
                    + " [" + c.getNumeroCuenta() + "]"
                    + "  interes: $" + c.calcularInteres());
        
        seccion(8, "Transicion de estado invalida");
        Transaccion txInvalida = new Transaccion(
                "TX-002", cAhorros, null, 10000, "Retiro");
        txInvalida.cambiarEstado(EstadoTransaccion.PROCESANDO);
        txInvalida.cambiarEstado(EstadoTransaccion.COMPLETADA);
        try {
            txInvalida.cambiarEstado(EstadoTransaccion.PROCESANDO);
        } catch (EstadoTransaccionInvalidoException e) {
            System.out.println(" EstadoTransaccionInvalidoException → " 
                    + e.getMessage());
        }
        
        seccion(9, "Permiso insuficiente  Cajero intenta aprobar credito");
        try {
            GerenteSucursal.verificarPermisoGerente(cajero);
        } catch (PermisoInsuficienteException e) {
            System.out.println(" PermisoInsuficienteException → " 
                    + e.getMessage());
        }
        GerenteSucursal.verificarPermisoGerente(gerente);
        gerente.aprobarCredito(10000000);
        
        seccion(10, "Notificaciones (Notificable)");
        c1.notificar("Su cuenta ha sido acreditada con $200.000");
        c2.setNotificacionesActivas(false);
        c2.notificar("Tiene un nuevo movimiento en su cuenta");
        
        seccion(11, "Auditoria (Auditable)");
        cAhorros.registrarModificacion("cajero.carlos");
        System.out.println(" Ultima modificacion : " 
                + cAhorros.obtenerUltimaModificacion());
        System.out.println(" Usuario que modifico: " 
                + cAhorros.ObtenerUsuarioModificacion());
        
        seccion(12, "Nomina total del banco");
        try {
            banco.registrarEmpleado(cajero);
            banco.registrarEmpleado(asesor);
            banco.registrarEmpleado(gerente);
        } catch (CapacidadExcedidaException e) {
            System.out.println("error " + e.getMessage());
        }
        System.out.println(" Nomina total: $" + banco.calcularNominaTotal());
        banco.calcularInteresesMensuales();
    }
    
    private static void titulo(String texto) {
        System.out.println("\n===================================");
        System.out.println("  " + texto);
        System.out.println("=======================================");
    }

    private static void seccion(int num, String descripcion) {
        System.out.println("\n  Escenario " + num + ": " + descripcion);
    }
    
        
        
        
    

}
