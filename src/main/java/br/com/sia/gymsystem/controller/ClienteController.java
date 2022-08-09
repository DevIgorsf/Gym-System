package br.com.sia.gymsystem.controller;

import br.com.sia.gymsystem.dto.ClienteDto;
import br.com.sia.gymsystem.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/dados/{id}")
    public ResponseEntity<ClienteDto> getDados(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.getDados(id));
    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')")

}
