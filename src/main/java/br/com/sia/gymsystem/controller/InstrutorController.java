package br.com.sia.gymsystem.controller;

import br.com.sia.gymsystem.dto.InstrutorDto;
import br.com.sia.gymsystem.form.InstrutorForm;
import br.com.sia.gymsystem.service.InstrutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/instrutor")
public class InstrutorController {

    @Autowired
    private InstrutorService instrutorService;

    @PostMapping("/cadastrar")
    public ResponseEntity<InstrutorDto> cadastarInstrutor(@RequestBody @Valid InstrutorForm form) {
        return ResponseEntity.status(HttpStatus.OK).body(instrutorService.cadastrarInstrutor(form));
    }
}
