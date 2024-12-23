package co.edu.unbosque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.service.ComisionistaService;
import co.edu.unbosque.service.ContratoService;
import co.edu.unbosque.service.EmpresaService;
import co.edu.unbosque.service.InversionistaService;
import co.edu.unbosque.service.TransaccionService;
import org.springframework.web.bind.annotation.PostMapping;
import co.edu.unbosque.model.Transaccion;
import co.edu.unbosque.model.Inversionista;

import co.edu.unbosque.model.Comisionista;
import co.edu.unbosque.model.Contrato;
import co.edu.unbosque.model.Empresa;

import org.springframework.web.bind.annotation.PutMapping;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class TransaccionController {

	@Autowired
	private TransaccionService transaccionService;

	@Autowired
	private InversionistaService inversionistaService;

	@Autowired
	private ComisionistaService comisionistaService;

	@Autowired
	private ContratoService contratoService;

	@Autowired
	private EmpresaService empresaService;

	@PostMapping("/transaccion")

	public ResponseEntity<Transaccion> add(@RequestParam Integer inversionista_id,
			@RequestParam Integer comisionista_id, @RequestParam String tipo,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha, @RequestParam Double cantidad,
			@RequestParam Double precio, @RequestParam Double monto_total, @RequestParam Integer contrato_id,
			@RequestParam Integer empresa_id, @RequestParam boolean estado) {

		Optional<Inversionista> inversionistaOpt = inversionistaService.getInversionistaById(inversionista_id);
		if (!inversionistaOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		Optional<Comisionista> comisionistaOpt = comisionistaService.getComisionistaById(comisionista_id);
		if (!comisionistaOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		Optional<Contrato> contratoOpt = contratoService.getContratoById(contrato_id);
		if (!contratoOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		Optional<Empresa> empresaOpt = empresaService.getEmpresaById(empresa_id);
		if (!empresaOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		Transaccion transaccion = new Transaccion();
		transaccion.setInversionista(inversionistaOpt.get());
		transaccion.setComisionista(comisionistaOpt.get());
		transaccion.setContrato(contratoOpt.get());
		transaccion.setEmpresa(empresaOpt.get());

		transaccion.setTipo(tipo);
		transaccion.setCantidad(cantidad);
		transaccion.setPrecio(precio);
		transaccion.setFecha(fecha);
		transaccion.setMonto_total(monto_total);
		transaccion.setEstado(estado);

		transaccionService.createTransaccion(transaccion);
		return ResponseEntity.status(HttpStatus.CREATED).body(transaccion);

	}

	@GetMapping("/transaccion")
	public ResponseEntity<List<Transaccion>> mostrarTodo() {

		List<Transaccion> listaTransaccion = transaccionService.getAll();
		if (listaTransaccion.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(listaTransaccion);
	}

	@GetMapping("/transaccion/{id}/{nombre}")
	public ResponseEntity<Boolean> verificarCompra(@PathVariable Integer id, @PathVariable String nombre) {

		int id_empresa = 0;
		boolean verificado = false;
		List<Transaccion> listaTransaccion = transaccionService.getAll();

		if (listaTransaccion.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
		}

		Optional<Empresa> empresaOpt = empresaService.getEmpresaByname(nombre);
		if (!empresaOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
		} else {
			id_empresa = empresaOpt.get().getEmpresa_id();
		}

		for (Transaccion transaccion : listaTransaccion) {
			if (transaccion.getInversionista().getInversionista_id() == id && transaccion.getTipo().equals("compra")
					&& transaccion.getEmpresa().getEmpresa_id() == id_empresa && transaccion.isEstado() == true) {
				verificado = true;
				break;
			}
		}

		return ResponseEntity.ok(verificado);
	}

	@GetMapping("/transaccion/{id}")
	public ResponseEntity<Transaccion> mostrarPorId(@PathVariable Integer id) {

		Optional<Transaccion> transaccionOpt = transaccionService.getTransaccionById(id);
		if (!transaccionOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		return ResponseEntity.status(HttpStatus.OK).body(transaccionOpt.get());

	}

	@DeleteMapping("/transaccion/{id}")
	public ResponseEntity<String> delete(@RequestParam Integer id) {

		Optional<Transaccion> transaccionOpt = transaccionService.getTransaccionById(id);
		if (!transaccionOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		transaccionService.deleteTransaccion(id);
		return ResponseEntity.status(HttpStatus.OK).body("Transaccion eliminada");
	}

	@PutMapping("/transaccion/{id}")
	public ResponseEntity<Transaccion> update(@PathVariable Integer id, @RequestParam Integer inversionista_id,
			@RequestParam Integer accion_id, @RequestParam Integer comisionista_id, @RequestParam String tipo,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha, @RequestParam Double cantidad,
			@RequestParam Double precio, @RequestParam Double monto_total, @RequestParam boolean estado) {

		Optional<Transaccion> transaccionOpt = transaccionService.getTransaccionById(id);
		if (!transaccionOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		Optional<Inversionista> inversionistaOpt = inversionistaService.getInversionistaById(inversionista_id);
		if (!inversionistaOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		Optional<Comisionista> comisionistaOpt = comisionistaService.getComisionistaById(comisionista_id);
		if (!comisionistaOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		Transaccion transaccion = transaccionOpt.get();
		transaccion.setInversionista(inversionistaOpt.get());

		transaccion.setComisionista(comisionistaOpt.get());
		transaccion.setTipo(tipo);
		transaccion.setCantidad(cantidad);
		transaccion.setPrecio(precio);
		transaccion.setFecha(fecha);
		transaccion.setMonto_total(monto_total);
		transaccion.setEstado(estado);

		transaccionService.createTransaccion(transaccion);
		return ResponseEntity.status(HttpStatus.OK).body(transaccion);

	}

	@PutMapping("/transaccion/venta/{id}")
	public ResponseEntity<Transaccion> venta(@PathVariable Integer id) {

		Optional<Transaccion> transaccionOpt = transaccionService.getTransaccionById(id);
		if (!transaccionOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		Transaccion transaccion = transaccionOpt.get();

		transaccion.setTipo("venta");

		transaccion.setEstado(false);

		transaccionService.createTransaccion(transaccion);
		return ResponseEntity.status(HttpStatus.OK).body(transaccion);

	}

	@GetMapping("/transaccion/inversionista/{inversionistaId}/{estado}/{tipo}")
	public ResponseEntity<List<Object[]>> obtenertransaccionInv(@PathVariable Integer inversionistaId,
			@PathVariable Boolean estado, // Cambiado a Integer para manejar 0 y 1
			@PathVariable String tipo) { // Puede ser "compra" o "venta"

		List<Object[]> resultados = transaccionService.getTransaccionesPorInversionistaIdEstadoYTipo(inversionistaId,
				estado, tipo);

		if (resultados.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		return ResponseEntity.status(HttpStatus.OK).body(resultados);
	}

	@PutMapping("/transaccion/comisionista/aceptar/{id}")
	public ResponseEntity<Transaccion> update(@PathVariable Integer id) {

		Optional<Transaccion> transaccionOpt = transaccionService.getTransaccionById(id);
		if (!transaccionOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		Transaccion transaccion = transaccionOpt.get();

		transaccion.setEstado(true);

		transaccionService.updateTransaccion(transaccion);
		return ResponseEntity.status(HttpStatus.OK).body(transaccion);

	}

	@GetMapping("/transaccion/comisionista/{comisionistaId}/transacciones")
	public ResponseEntity<List<Transaccion>> ObtenerTransaccionesPorEstado(@PathVariable Integer comisionistaId) {

		List<Transaccion> transacciones = transaccionService.findTransaccionesByComisionistaIdAndEstado(comisionistaId);

		if (transacciones.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		Optional<Comisionista> comisionistas = comisionistaService.getComisionistaById(comisionistaId);

		if (!comisionistas.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		}

		return ResponseEntity.status(HttpStatus.OK).body(transacciones);
	}

	@PutMapping("/transaccion/aceptar/{id}")
	public ResponseEntity<Transaccion> aceptarTransaccion(@PathVariable Integer id) {
		Optional<Transaccion> transaccionOpt = transaccionService.getTransaccionById(id);
		if (!transaccionOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		Transaccion transaccion = transaccionService.aceptarTransaccion(id);
		return ResponseEntity.status(HttpStatus.OK).body(transaccion);
	}

	@GetMapping("/cantidad/{transaccionId}")
	public ResponseEntity<Integer> getCantidadByTransaccionId(@PathVariable Integer transaccionId) {
		Optional<Integer> cantidad = transaccionService.findCantidad(transaccionId);

		if (cantidad.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(cantidad.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping("/transaccion/comisionista/{comisionistaId}/ventas/{tipo}")
	public ResponseEntity<List<Transaccion>> VentasDeInversionistas(@PathVariable Integer comisionistaId,
			@PathVariable String tipo) {
		List<Transaccion> transacciones = transaccionService.findVentasDeInversionistas(comisionistaId, tipo);
		if (transacciones.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(transacciones);
	}

}
