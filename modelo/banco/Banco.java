/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.banco;

/**
 *
 * @author pc
 */

import modelo.abstractas.Cuenta;
import modelo.abstractas.Empleado;
import modelo.abstractas.Persona;
import modelo.excepciones.CapacidadExcedidaException;
import modelo.excepciones.ClienteNoEncontradoException;
import modelo.excepciones.DatoInvalidoException;
import modelo.interfaces.Auditable;
import modelo.personas.ClienteNatural;
import modelo.personas.ClienteEmpresarial;
import java.time.LocalDateTime;

public class Banco implements Auditable  {
    
    private static final int MAX_EMPLEADOS = 50;
    private static final int MAX_CLIENTES  = 200;
    private static final int MAX_CUENTAS   = 500;
    
     private final String nombre;

    private final Empleado[] empleados;
    private int totalEmpleados;

    private final Persona[] clientes;
    private int totalClientes;

    private final Cuenta[] cuentas;
    private int totalCuentas;

    private final LocalDateTime fechaCreacion;
    private LocalDateTime ultimaModificacion;
    private String usuarioModificacion;
    
    public Banco(String nombre) {
        if (nombre == null || nombre.isBlank())
            throw new DatoInvalidoException("nombre", nombre);
        this.nombre = nombre;
        this.empleados = new Empleado[MAX_EMPLEADOS];
        this.clientes  = new Persona[MAX_CLIENTES];
        this.cuentas   = new Cuenta[MAX_CUENTAS];
        this.totalEmpleados = 0;
        this.totalClientes  = 0;
        this.totalCuentas   = 0;
        this.fechaCreacion  = LocalDateTime.now();
        this.ultimaModificacion = LocalDateTime.now();
        this.usuarioModificacion = "SISTEMA";
    }
     
    public void registrarCliente(Persona cliente) 
            throws CapacidadExcedidaException {
        if (totalClientes >= MAX_CLIENTES)
            throw new CapacidadExcedidaException("clientes del banco", MAX_CLIENTES);
        for (int i = 0; i < totalClientes; i++) {
            if (clientes[i].getId().equals(cliente.getId()))
                throw new DatoInvalidoException("id cliente duplicado", cliente.getId());
        }
        clientes[totalClientes] = cliente;
        totalClientes++;
    }
    
    public void registrarEmpleado(Empleado empleado) 
            throws CapacidadExcedidaException {
        if (totalEmpleados >= MAX_EMPLEADOS)
            throw new CapacidadExcedidaException("empleados del banco", MAX_EMPLEADOS);
        empleados[totalEmpleados] = empleado;
        totalEmpleados++;
    }
    
    public void abrirCuenta(String idCliente, Cuenta cuenta)
            throws ClienteNoEncontradoException, CapacidadExcedidaException {
        Persona cliente = buscarCliente(idCliente);
        if (totalCuentas >= MAX_CUENTAS)
            throw new CapacidadExcedidaException("cuentas del banco", MAX_CUENTAS);
        if (cliente instanceof ClienteNatural)
            ((ClienteNatural) cliente).agregarCuenta(cuenta);
        else if (cliente instanceof ClienteEmpresarial)
            ((ClienteEmpresarial) cliente).agregarCuenta(cuenta);
        cuentas[totalCuentas] = cuenta;
        totalCuentas++;
    }
    
    public Persona buscarCliente(String id) 
            throws ClienteNoEncontradoException {
        if (id == null || id.isBlank())
            throw new DatoInvalidoException("id", id);
        for (int i = 0; i < totalClientes; i++) {
            if (clientes[i].getId().equals(id))
                return clientes[i];
        }
        throw new ClienteNoEncontradoException(id);
    }
    
    public double calcularNominaTotal() {
        double total = 0;
        for (int i = 0; i < totalEmpleados; i++)
            total += empleados[i].calcularSalario();
        return total;
    }
     
    public void calcularInteresesMensuales() {
        System.out.println("\n── Intereses mensuales ──");
        for (int i = 0; i < totalCuentas; i++)
            System.out.println("  " + cuentas[i].getNumeroCuenta()
                    + " → interes: $" + cuentas[i].calcularInteres());
    }

    public Empleado[] getEmpleados() {
        Empleado[] copia = new Empleado[totalEmpleados];
        System.arraycopy(empleados, 0, copia, 0, totalEmpleados);
        return copia;
    }
    
    public Persona[] getClientes() {
        Persona[] copia = new Persona[totalClientes];
        System.arraycopy(clientes, 0, copia, 0, totalClientes);
        return copia;
    }

    public Cuenta[] getCuentas() {
        Cuenta[] copia = new Cuenta[totalCuentas];
        System.arraycopy(cuentas, 0, copia, 0, totalCuentas);
        return copia;
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

    public String getNombre()       { return nombre; }
    public int getTotalClientes()   { return totalClientes; }
    public int getTotalEmpleados()  { return totalEmpleados; }
    public int getTotalCuentas()    { return totalCuentas; }





    
    
}
