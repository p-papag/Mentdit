package com.example.mentdit.controller;

import com.example.mentdit.dto.SubmentditDto;
import com.example.mentdit.service.SubmentditService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submentdit")
@AllArgsConstructor
@Slf4j
public class SubmentditController {

    private final SubmentditService submentditService;

    @PostMapping
    public ResponseEntity<SubmentditDto> createSubmentdit(@RequestBody SubmentditDto submentditDto){
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(submentditService.save(submentditDto));
    }

    @GetMapping
    public ResponseEntity<List<SubmentditDto>> getAllSubMentdits() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(submentditService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubmentditDto> getSubmentdit(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(submentditService.getSubmentdit(id));
    }
}
