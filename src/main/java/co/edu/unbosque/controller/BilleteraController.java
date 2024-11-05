package co.edu.unbosque.controller;

import org.apache.catalina.connector.Response;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.model.Billetera;
import co.edu.unbosque.service.BilleteraService;
import co.edu.unbosque.service.UsuarioService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import co.edu.unbosque.model.Usuario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.List;
import org.springframework.http.HttpStatus;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class BilleteraController {

    @Autowired
    private BilleteraService billeteraService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/billetera")
    public ResponseEntity<Billetera> add(@RequestParam Integer inversionista_id, @RequestParam double saldo) {

        Optional<Usuario> usuario = usuarioService.getUsuarioById(inversionista_id);

        if (!usuario.isPresent()) {
            return ResponseEntity.status(Response.SC_NOT_FOUND).build();
        }

        Billetera billetera = new Billetera();
        billetera.setUsuario(usuario.get());
        billetera.setSaldo(saldo);

        Billetera result = billeteraService.createBilletera(billetera);

        return ResponseEntity.ok(result);

    }

    @GetMapping("/billetera")
    public ResponseEntity<List<Billetera>> getBilletera() {

        List<Billetera> billetera = billeteraService.getAll();
        if (billetera.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(billetera);
    }

    @DeleteMapping("/billetera/{id}")
    public ResponseEntity<Billetera> deleteBilletera(@PathVariable Integer id) {

        Optional<Billetera> billetera = billeteraService.getBilleteraById(id);

        if (!billetera.isPresent()) {
            return ResponseEntity.status(Response.SC_NOT_FOUND).build();
        }

        billeteraService.deleteBilletera(id);

        return ResponseEntity.ok().build();

    }

    @PutMapping("/billetera/{id}")
    public ResponseEntity<Billetera> updateBilletera(@PathVariable Integer id, @RequestParam Integer usuario_id,
            @RequestParam double saldo) {

        Optional<Billetera> billetera = billeteraService.getBilleteraById(id);

        if (!billetera.isPresent()) {
            return ResponseEntity.status(Response.SC_NOT_FOUND).build();
        }

        Optional<Usuario> usuario = usuarioService.getUsuarioById(usuario_id);

        if (!usuario.isPresent()) {
            return ResponseEntity.status(Response.SC_NOT_FOUND).build();
        }

        Billetera billeteraDetails = billetera.get();
        billeteraDetails.setSaldo(saldo);
        billeteraDetails.setUsuario(usuarioService.getUsuarioById(usuario_id).get());

        Billetera result = billeteraService.updateBilletera(billeteraDetails);

        return ResponseEntity.ok(result);
    }

}
