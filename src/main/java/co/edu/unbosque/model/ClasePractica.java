package co.edu.unbosque.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ClasePractica")
public class ClasePractica {

    @Id
    @Column(name = "ID_claseP")
    private int idClasePractica;

    @Column(name = "ID_instructor")
    private int idInstructor;

    @Column(name = "ID_vehiculo")
    private int idVehiculo;

    @Column(name = "ID_matriculado")
    private int idMatriculado;
    
    @Column(name = "descripcion")
    private String descripcion;

    
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getIdClasePractica() {
		return idClasePractica;
	}

	public void setIdClasePractica(int idClasePractica) {
		this.idClasePractica = idClasePractica;
	}

	public int getIdInstructor() {
		return idInstructor;
	}

	public void setIdInstructor(int idInstructor) {
		this.idInstructor = idInstructor;
	}

	public int getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(int idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	public int getIdMatriculado() {
		return idMatriculado;
	}

	public void setIdMatriculado(int idMatriculado) {
		this.idMatriculado = idMatriculado;
	}



}
