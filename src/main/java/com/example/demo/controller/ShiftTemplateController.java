package com.example.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.ShiftTemplateEntity;
import com.example.demo.service.ShiftTemplateService;

@RestController
@RequestMapping("/shift-templates")
public class ShiftTemplateController {

    private final ShiftTemplateService service;

    public ShiftTemplateController(ShiftTemplateService service) {
        this.service = service;
    }

    @PostMapping
    public ShiftTemplateEntity save(@RequestBody ShiftTemplateEntity shift) {
        return service.save(shift);
    }

    @GetMapping
    public List<ShiftTemplateEntity> getAll() {
        return service.findAll();
    }
}
