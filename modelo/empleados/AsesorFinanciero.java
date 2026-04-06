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
import modelo.interfaces.Auditable;
import modelo.interfaces.Consultable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AsesorFinanciero extends Empleado 
        implements Consultable, Auditable {
    
    private static final int MAX_CLIENTES = 20;

    private double comisionBase;
    private double metasMensuales;
    private double ventasMensuales;

    private final Object[] clientesAsignados;
    private int totalClientes;

    private final LocalDateTime fechaCreacion;
    private LocalDateTime ultimaModificacion;
    private String usuarioModificacion;
    
    
    public AsesorFinanciero(String id, String nombre, String apellido,
                             LocalDate fechaNacimiento, String email,
                             String legajo, LocalDate fechaContratacion, 
                             double salarioBase, double comisionBase, 
                             double metasMensuales) {
        super(id, nombre, apellido, fechaNacimiento, email,
              legajo, fechaContratacion, salarioBase);
        setComisionBase(comisionBase);
        setMetasMensuales(metasMensuales);
        this.ventasMensuales = 0;
        this.clientesAsignados = new Object[MAX_CLIENTES];
        this.totalClientes = 0;
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
        return "Asesor Financiero";
    }

    @Override
    public String obtenerDocumentoidentidad() {
        return "Legajo: " + getLegajo();
    }

    @Override
    public double calcularBono() {
        return ventasMensuales > metasMensuales ? comisionBase : 0;
    }
    
    
    @Override
    public double calcularSalario() {
        return getSalarioBase() + calcularBono();
    }

    public void asignarCliente(Object cliente) throws CapacidadExcedidaException {
        if (totalClientes >= MAX_CLIENTES)
            throw new CapacidadExcedidaException("clientes por asesor", MAX_CLIENTES);
        clientesAsignados[totalClientes] = cliente;
        totalClientes++;
    }
    
     public Object[] getClientesAsignados() {
        Object[] copia = new Object[totalClientes];
        System.arraycopy(clientesAsignados, 0, copia, 0, totalClientes);
        return copia;
    }

    @Override
    public String obtenerResumen() {
        return "Asesor Financiero | " + getNombrecompleto()
                + " | Clientes: " + totalClientes
                + " | Meta: $" + metasMensuales
                + " | Ventas: $" + ventasMensuales
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
    
    public void setComisionBase(double comision) {
        if (comision < 0)
            throw new DatoInvalidoException("comisionBase", comision);
        this.comisionBase = comision;
    }

    public void setMetasMensuales(double meta) {
        if (meta < 0)
            throw new DatoInvalidoException("metasMensuales", meta);
        this.metasMensuales = meta;
    }

    public void setVentasMensuales(double ventas) {
        if (ventas < 0)
            throw new DatoInvalidoException("ventasMensuales", ventas);
        this.ventasMensuales = ventas;
    }
    
    public double getComisionBase()    { return comisionBase; }
    public double getMetasMensuales()  { return metasMensuales; }
    public double getVentasMensuales() { return ventasMensuales; }
    public int getTotalClientes()      { return totalClientes; }
    
}
