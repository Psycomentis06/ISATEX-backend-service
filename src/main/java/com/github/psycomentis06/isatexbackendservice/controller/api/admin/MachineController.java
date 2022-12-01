package com.github.psycomentis06.isatexbackendservice.controller.api.admin;

import com.github.psycomentis06.isatexbackendservice.entity.Machine;
import com.github.psycomentis06.isatexbackendservice.entity.MachineBrand;
import com.github.psycomentis06.isatexbackendservice.projection.SimpleMachineBrandProjection;
import com.github.psycomentis06.isatexbackendservice.projection.SimpleMachineBrandProjectionWithMachines;
import com.github.psycomentis06.isatexbackendservice.projection.SimpleMachineProjectionWithBrand;
import com.github.psycomentis06.isatexbackendservice.service.MachineService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/machine")
public class MachineController {

    private final MachineService machineService;

    public MachineController(MachineService machineService) {
        this.machineService = machineService;
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createMachine(
            @RequestBody Machine machine
    ) {
        machineService.createMachine(machine);
        Map<String, String> resp = new HashMap<>();
        resp.put("message", "Machine created successfully");
        resp.put("code", "200");
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SimpleMachineProjectionWithBrand> getMachine(
            @PathVariable int id
    ) {
        Optional<SimpleMachineProjectionWithBrand> machineOptional = machineService.getMachine(SimpleMachineProjectionWithBrand.class, id);
        machineOptional.orElseThrow(() -> {
            throw new EntityNotFoundException("Machine #" + id + " not found");
        });
        return new ResponseEntity<>(machineOptional.get(), HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<Page<SimpleMachineProjectionWithBrand>> getAllMachines(
            @RequestParam(name = "s", required = false, defaultValue = "10") int s,
            @RequestParam(name = "p", required = false, defaultValue = "0") int p,
            @RequestParam(name = "q", required = false, defaultValue = "") String query
    ) {
        Pageable pageable = Pageable.ofSize(s);
        return new ResponseEntity<>(machineService.getAllMachines(SimpleMachineProjectionWithBrand.class, pageable.withPage(p), query), HttpStatus.OK);
    }

    @PostMapping("/brand/create")
    public ResponseEntity<Map<String, String>> createBrand(
            @RequestBody MachineBrand brand
    ) {
        machineService.createMachineBrand(brand);
        Map<String, String> resp = new HashMap<>();
        resp.put("message", "Machine created successfully");
        resp.put("code", "200");
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("/brand/{id}")
    public ResponseEntity<SimpleMachineBrandProjectionWithMachines> getMachineBrand(
            @PathVariable int id
    ) {
        Optional<SimpleMachineBrandProjectionWithMachines> machineOptional = machineService.getMachineBrand(SimpleMachineBrandProjectionWithMachines.class, id);
        machineOptional.orElseThrow(() -> {
            throw new EntityNotFoundException("Machine Brand #" + id + " not found");
        });
        return new ResponseEntity<>(machineOptional.get(), HttpStatus.OK);
    }


    @GetMapping("/brand/all")
    public ResponseEntity<Page<SimpleMachineBrandProjection>> getAllMachineBrand(
            @RequestParam(name = "s", required = false, defaultValue = "10") int s,
            @RequestParam(name = "p", required = false, defaultValue = "0") int p,
            @RequestParam(name = "q", required = false, defaultValue = "") String query
    ) {
        Pageable pageable = Pageable.ofSize(s);
        return new ResponseEntity<>(machineService.getAllBrands(SimpleMachineBrandProjection.class, pageable.withPage(p), query), HttpStatus.OK);
    }
}
