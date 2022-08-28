package br.com.sia.gymsystem.controller;

import br.com.sia.gymsystem.dto.HorarioDto;
import br.com.sia.gymsystem.form.HorarioForm;
import br.com.sia.gymsystem.service.HorarioService;
import br.com.sia.gymsystem.service.InstrutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class HorarioController {

    @Autowired
    private InstrutorService instrutorService;

    @Autowired
    private HorarioService horarioService;

    @PreAuthorize("hasRole('INSTRUTOR')")
    @PostMapping("/RegistrarAula")
    public ResponseEntity<HorarioDto> RegistrarAula(@RequestBody @Valid HorarioForm form) {
        return ResponseEntity.status(HttpStatus.OK).body(instrutorService.RegistrarAula(form));
    }

    @PreAuthorize("hasRole('INSTRUTOR')")
    @DeleteMapping("/ExcluirAula/{id}")
    public ResponseEntity ExcluirAula(@PathVariable Long id) {
        return instrutorService.ExcluirAula(id);
    }

    @PreAuthorize("hasAnyRole('INSTRUTOR', 'CLIENTE')")
    @GetMapping("/BuscarAulas")
    public ResponseEntity<List<HorarioDto>> BuscarAulas() {
        return ResponseEntity.status(HttpStatus.OK).body(horarioService.BuscarAulas());
    }

}
