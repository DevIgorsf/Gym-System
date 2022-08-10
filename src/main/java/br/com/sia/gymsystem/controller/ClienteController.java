package br.com.sia.gymsystem.controller;

import br.com.sia.gymsystem.dto.ClienteDto;
import br.com.sia.gymsystem.form.ClienteForm;
import br.com.sia.gymsystem.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/client")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PreAuthorize("hasAnyRole('INSTRUTOR', 'CLIENTE')")
    @GetMapping("/dados/{id}")
    public ResponseEntity<ClienteDto> getDados(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.getDados(id));
    }

    @PreAuthorize("hasAnyRole('INSTRUTOR', 'CLIENTE')")
    @PostMapping("/cadastrar")
    public ResponseEntity<ClienteDto> cadastarCliente(@RequestBody @Valid ClienteForm form) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.cadastrarCliente(form));
    }

    @PreAuthorize("hasAnyRole('INSTRUTOR', 'CLIENTE')")
    @PutMapping("/dados/{id}")
    public ResponseEntity<ClienteDto> atualizarCliente(@PathVariable Long id, @RequestBody @Valid ClienteForm form) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.atualizarCliente(id, form));
    }
}
