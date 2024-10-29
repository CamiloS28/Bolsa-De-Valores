package co.edu.unbosque.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.unbosque.model.Inversionista;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.Optional;
import co.edu.unbosque.service.InversionistaService;
import co.edu.unbosque.service.UsuarioService;
import co.edu.unbosque.model.Usuario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class InversionistaController {

    @Autowired
    private InversionistaService inversionistaService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/inversionista")
    public ResponseEntity<Inversionista> add(@RequestParam String perfil_riesgo, @RequestParam String pais,
            @RequestParam Integer usuario_id) {

        Optional<Usuario> usuarioOpt = usuarioService.getUsuarioById(usuario_id);
        if (!usuarioOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Usuario usuario = usuarioOpt.get();

        Inversionista inversionista = new Inversionista(usuario, perfil_riesgo, pais);
        inversionistaService.createInversionista(inversionista);

        return ResponseEntity.status(HttpStatus.CREATED).body(inversionista);
    }

    @GetMapping("/inversionista")
    public ResponseEntity<List<Inversionista>> mostrarTodo() {

        List<Inversionista> listaInversionistas = inversionistaService.getAll();

        if (listaInversionistas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(listaInversionistas);

    }

    @DeleteMapping("/inversionista/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {

        Optional<Inversionista> inversionistaOpt = inversionistaService.getInversionistaById(id);
        if (!inversionistaOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        }
        inversionistaService.deleteInversionista(id);

        return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    }

    @PutMapping("/inversionista/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Integer id, @RequestParam String perfil_riesgo,
            @RequestParam String pais) {

        Optional<Inversionista> inversionistaOpt = inversionistaService.getInversionistaById(id);
        Optional<Usuario> usuarioOpt = usuarioService.getUsuarioById(id);

        if (!inversionistaOpt.isPresent() || !usuarioOpt.isPresent()) {
            return ResponseEntity.ok(false);
        }
        Inversionista nuevo = new Inversionista();
        nuevo.setInversionista_id(id);
        nuevo.setPerfil_riesgo(perfil_riesgo);
        nuevo.setPais(pais);
        inversionistaService.createInversionista(nuevo);
        return ResponseEntity.ok(true);

    }

    @GetMapping("/inversionista/{id}")
    public ResponseEntity<Inversionista> getOne(@PathVariable Integer id) {
        Optional<Inversionista> inversionistaOpt = inversionistaService.getInversionistaById(id);
        if (inversionistaOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(inversionistaOpt.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
