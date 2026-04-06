/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.empleados;

/**
 *
 * @author pc
 */
import modelo.abstractas.Empleado;
import modelo.excepciones.CapacidadExcedidaException;
import modelo.excepciones.DatoInvalidoException;
import modelo.excepciones.PermisoInsuficienteException;
import modelo.interfaces.Auditable;
import modelo.interfaces.Consultable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class GerenteSucursal extends  Empleado
        implements Consultable, Auditable {
    
    private static final int MAX_EMPLEADOS = 30;
    private static final double BONO_GERENCIA = 2_000_000.0;
    private static final double BONO_ANTIGUEDAD_POR_AÑO = 150_000.0;
    
    private String sucursal;
    private double presupuestoAnual;

    private final Empleado[] empleadosACargo;
    private int totalEmpleados;

    private final LocalDateTime fechaCreacion;
    private LocalDateTime ultimaModificacion;
    private String usuarioModificacion;
    
    public GerenteSucursal(String id, String nombre, String apellido,
                            LocalDate fechaNacimiento, String email,
                            String legajo, LocalDate fechaContratacion,
                            double salarioBase, String sucursal,
                            double presupuestoAnual) {
        super(id, nombre, apellido, fechaNacimiento, email,
              legajo, fechaContratacion, salarioBase);
        setSucursal(sucursal);
        setPresupuestoAnual(presupuestoAnual);
        this.empleadosACargo = new Empleado[MAX_EMPLEADOS];
        this.totalEmpleados = 0;
        this.fechaCreacion = LocalDateTime.now();
        this.ultimaModificacion = LocalDateTime.now();
        this.usuarioModificacion = "SISTEMA";
    }
    
    @Override
    public int calcularEdad() {
        return calcularEdadbase();
    }

    @Override
    public String obtenerTipo() {
        return "Gerente de Sucursal";
    }

    @Override
    public String obtenerDocumentoidentidad() {
        return "Legajo: " + getLegajo();
    }

    @Override
    public double calcularBono() {
        return BONO_GERENCIA + (calcularAntiguedad() * BONO_ANTIGUEDAD_POR_AÑO);
    }
    @Override
    public double calcularSalario() {
        return getSalarioBase() + calcularBono();
    }

    public void aprobarCredito(double monto) {
        System.out.println("[GERENTE " + getNombrecompleto()
                + "] Credito aprobado por $" + monto
                + " en sucursal " + sucursal);
    }
    
    public static void verificarPermisoGerente(Empleado empleado) {
        if (!(empleado instanceof GerenteSucursal))
            throw new PermisoInsuficienteException(
                    "aprobarCredito",empleado.obtenerTipo());
    }

    public void agregarEmpleado(Empleado empleado) throws CapacidadExcedidaException {
        if (totalEmpleados >= MAX_EMPLEADOS)
            throw new CapacidadExcedidaException("empleados a cargo", MAX_EMPLEADOS);
        empleadosACargo[totalEmpleados] = empleado;
        totalEmpleados++;
    }
    
    public Empleado[] getEmpleadosACargo() {
        Empleado[] copia = new Empleado[totalEmpleados];
        System.arraycopy(empleadosACargo, 0, copia, 0, totalEmpleados);
        return copia;
    }

    @Override
    public String obtenerResumen() {
        return "Gerente | " + getNombrecompleto()
                + " | Sucursal: " + sucursal
                + " | Antiguedad: " + calcularAntiguedad() + " años"
                + " | Empleados a cargo: " + totalEmpleados
                + " | Salario: $" + calcularSalario();
    }
    
    @Override
    public boolean estaActivo() {
        return isActivo();
    }

    @Override
    public LocalDateTime obtenerFechaCreacion()      { return fechaCreacion; }
    @Override
    public LocalDateTime obtenerUltimaModificacion() { return ultimaModificacion; }
    @Override
    public String ObtenerUsuarioModificacion()       { return usuarioModificacion; }
    
    @Override
    public void registrarModificacion(String usuario) {
        if (usuario == null || usuario.isBlank())
            throw new DatoInvalidoException("usuario", usuario);
        this.ultimaModificacion = LocalDateTime.now();
        this.usuarioModificacion = usuario;
    }

    public void setSucursal(String sucursal) {
        if (sucursal == null || sucursal.isBlank())
            throw new DatoInvalidoException("sucursal", sucursal);
        this.sucursal = sucursal;
    }
    
    public void setPresupuestoAnual(double presupuesto) {
        if (presupuesto < 0)
            throw new DatoInvalidoException("presupuestoAnual", presupuesto);
        this.presupuestoAnual = presupuesto;
    }

    public String getSucursal()         { return sucursal; }
    public double getPresupuestoAnual() { return presupuestoAnual; }
    public int getTotalEmpleados()      { return totalEmpleados; }

    

}
