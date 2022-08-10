package br.com.sia.gymsystem.controller;

import br.com.sia.gymsystem.dto.ClienteDto;
import br.com.sia.gymsystem.dto.InstrutorDto;
import br.com.sia.gymsystem.form.ClienteForm;
import br.com.sia.gymsystem.form.InstrutorForm;
import br.com.sia.gymsystem.service.InstrutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/instrutor")
public class InstrutorController {

    @Autowired
    private InstrutorService instrutorService;

    @PreAuthorize("hasRole('INSTRUTOR')")
    @GetMapping("/dados/{id}")
    public ResponseEntity<InstrutorDto> getDados(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(instrutorService.getDados(id));
    }

    @PreAuthorize("hasRole('INSTRUTOR')")
    @PostMapping("/cadastrar")
    public ResponseEntity<InstrutorDto> cadastarInstrutor(@RequestBody @Valid InstrutorForm form) {
        return ResponseEntity.status(HttpStatus.OK).body(instrutorService.cadastrarInstrutor(form));
    }

    @PreAuthorize("hasRole('INSTRUTOR')")
    @PutMapping("/dados/{id}")
    public ResponseEntity<InstrutorDto> atualizarInstrutor(@PathVariable Long id, @RequestBody @Valid ClienteForm form) {
        return ResponseEntity.status(HttpStatus.OK).body(instrutorService.atualizarInstrutor(id, form));
    }

}
