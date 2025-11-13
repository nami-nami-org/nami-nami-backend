package com.nami.demo.api.local.controller;

import com.nami.demo.api.local.dto.request.CreateLocalRequestDto;
import com.nami.demo.api.local.dto.request.UpdateLocalRequestDto;
import com.nami.demo.api.local.dto.response.LocalResponseDto;
import com.nami.demo.api.local.service.LocalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/local")
public class LocalController {

    private final LocalService localService;

    public LocalController(LocalService localService) {
        this.localService = localService;
    }

    @GetMapping
    public ResponseEntity<List<LocalResponseDto>> getAllLocales() {
        return ResponseEntity.ok(localService.getAllLocales());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalResponseDto> getLocalById(@PathVariable long id) {
        return ResponseEntity.ok(localService.getLocalById(id));
    }

    @PostMapping
    public ResponseEntity<LocalResponseDto> createLocal(@RequestBody CreateLocalRequestDto createLocalRequestDto) {
        return ResponseEntity.ok(localService.newLocal(createLocalRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocalResponseDto> updateLocal(
            @PathVariable long id,
            @RequestBody UpdateLocalRequestDto updateLocalRequestDto
    ) {
        return ResponseEntity.ok(localService.updateLocal(id, updateLocalRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocal(@PathVariable long id) {
        localService.deleteLocal(id);
        return ResponseEntity.noContent().build();
    }
}
