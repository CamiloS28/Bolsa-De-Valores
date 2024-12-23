package co.edu.unbosque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import co.edu.unbosque.service.EmpresaService;
import co.edu.unbosque.model.Empresa;
import co.edu.unbosque.model.Inversionista;

import org.springframework.web.bind.annotation.PutMapping;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @PostMapping("/empresa")
    public ResponseEntity<Empresa> add(@RequestParam String nombre, @RequestParam String sector,
            @RequestParam String pais) {

        if (empresaService.getEmpresaByname(nombre).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Empresa empresa = new Empresa();
        empresa.setNombre(nombre);
        empresa.setSector(sector);
        empresa.setPais(pais);

        empresaService.createEmpresa(empresa);

        return ResponseEntity.status(HttpStatus.CREATED).body(empresa);
    }

    @GetMapping("/empresa")
    public ResponseEntity<List<Empresa>> mostrarTodo() {

        List<Empresa> listaEmpresas = empresaService.getAll();
        if (listaEmpresas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(listaEmpresas);
    }

    @GetMapping("/empresa/{id}")
    public ResponseEntity<Empresa> getOne(@PathVariable Integer id) {
        Optional<Empresa> empresaOpt = empresaService.getEmpresaById(id);
        if (empresaOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(empresaOpt.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    
    @GetMapping("/empresa/nombre/{nombre}")
    public ResponseEntity<Empresa> getOne(@PathVariable String nombre) {
        Optional<Empresa> inversionistaOpt = empresaService.getEmpresaByname(nombre);
        if (inversionistaOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(inversionistaOpt.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    
    
    @PutMapping("/empresa/{id}")
    public ResponseEntity<Empresa> update(@PathVariable Integer id, @RequestParam String nombre,
            @RequestParam String sector, @RequestParam String pais) {

        Optional<Empresa> empresaOpt = empresaService.getEmpresaById(id);
        if (!empresaOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Empresa empresa = empresaOpt.get();
        empresa.setNombre(nombre);
        empresa.setSector(sector);
        empresa.setPais(pais);

        empresaService.createEmpresa(empresa);
        return ResponseEntity.status(HttpStatus.OK).body(empresa);

    }

    @DeleteMapping("/empresa/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {

        Optional<Empresa> empresaOpt = empresaService.getEmpresaById(id);
        if (!empresaOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        }

        empresaService.deleteEmpresa(id);

        return ResponseEntity.status(HttpStatus.FOUND).body("Deleted");
    }

}
