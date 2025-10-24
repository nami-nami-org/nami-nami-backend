package com.nami.demo.api.local.controller;

import com.nami.demo.api.local.dto.request.CreateLocalRequestDto;
import com.nami.demo.api.local.dto.response.LocalResponseDto;
import com.nami.demo.api.local.service.LocalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/local")
public class LocalController {

    private final LocalService localService;

    public LocalController(LocalService localService) {
        this.localService = localService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalResponseDto> getLocalById(@PathVariable long id) {
        LocalResponseDto response = localService.getLocalById(id);
        return ResponseEntity.ok(response);
    }


    @PostMapping
    public ResponseEntity<LocalResponseDto> createLocal(@RequestBody CreateLocalRequestDto createLocalRequestDto) {
        LocalResponseDto response = localService.newLocal(createLocalRequestDto);
        return ResponseEntity.ok(response);
    }
}
