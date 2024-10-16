package co.edu.unbosque.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.unbosque.repository.InversionistaRepository;
import co.edu.unbosque.model.Inversionista;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.Optional;
import co.edu.unbosque.repository.UsuarioRepository;
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
    private InversionistaRepository inversionistaRepository;
    public ArrayList<Inversionista> inversionistas = new ArrayList<Inversionista>();
    public int variant = 0;

    @Autowired
    private UsuarioRepository suarioRepository;

    @PostMapping("/inversionista")
    public ResponseEntity<Inversionista> add(@RequestParam String perfil_riesgo, @RequestParam String pais,
            @RequestParam Integer usuario_id) {

        Optional<Usuario> usuarioOpt = suarioRepository.findById(usuario_id);
        if (!usuarioOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Usuario usuario = usuarioOpt.get();

        // Verificar si ya existe un inversionista para el usuario
        if (inversionistaRepository.existsById(usuario.getUsuario_id())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Inversionista inversionista = new Inversionista(usuario, perfil_riesgo, pais);
        inversionistaRepository.save(inversionista);

        return ResponseEntity.status(HttpStatus.CREATED).body(inversionista);
    }

    @GetMapping("/inversionista")
    public ResponseEntity<List<Inversionista>> mostrarTodo() {

        List<Inversionista> listaInversionistas = inversionistaRepository.findAll();

        if (listaInversionistas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(listaInversionistas);

    }

    @DeleteMapping("/inversionista/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {

        Optional<Inversionista> inversionistaOpt = inversionistaRepository.findById(id);
        if (!inversionistaOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        }
        inversionistaRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.FOUND).body("Deleted");
    }

    @PutMapping("/inversionista/{id}")
    public ResponseEntity<Boolean> update(@RequestParam String perfil_riesgo, @RequestParam String pais,
            @RequestParam Integer inversionista_id) {

        Optional<Inversionista> inversionistaOpt = inversionistaRepository.findById(inversionista_id);
        if (!inversionistaOpt.isPresent()) {
            return ResponseEntity.ok(false);
        }
        return inversionistaOpt.map(invr -> {
            invr.setPerfil_riesgo(perfil_riesgo);
            invr.setPais(pais);

            inversionistaRepository.save(invr);
            return ResponseEntity.ok(true);
        }).orElseGet(() -> {
            Inversionista nuevo = new Inversionista();
            nuevo.setInversionista_id(inversionista_id);
            nuevo.setPerfil_riesgo(perfil_riesgo);
            nuevo.setPais(pais);
            inversionistaRepository.save(nuevo);
            return ResponseEntity.ok(true);
        });
    }
}
