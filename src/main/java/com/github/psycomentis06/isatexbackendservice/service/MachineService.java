package com.github.psycomentis06.isatexbackendservice.service;

import com.github.psycomentis06.isatexbackendservice.entity.Machine;
import com.github.psycomentis06.isatexbackendservice.entity.MachineBrand;
import com.github.psycomentis06.isatexbackendservice.repository.MachineBrandRepository;
import com.github.psycomentis06.isatexbackendservice.repository.MachineRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MachineService {
    private final MachineRepository machineRepository;
    private final MachineBrandRepository machineBrandRepository;

    public MachineService(MachineRepository machineRepository, MachineBrandRepository machineBrandRepository) {
        this.machineRepository = machineRepository;
        this.machineBrandRepository = machineBrandRepository;
    }

    public void createMachineBrand(MachineBrand machineBrand) {
        machineBrandRepository.save(machineBrand);
    }

    public <T>Optional<T> getMachineBrand(Class<T> tClass, int id) {
        return machineBrandRepository.findById(tClass, id);
    }

    public <T>Page<T> getAllBrands(Class<T> tClass, Pageable pageable, String query) {
        query = "%" + query + "%";
        return machineBrandRepository.findAllByNameLike(tClass, pageable, query);
    }


    public void createMachine(Machine machineBrand) {
        machineRepository.save(machineBrand);
    }

    public <T>Optional<T> getMachine(Class<T> tClass, int id) {
        return machineRepository.findById(tClass, id);
    }

    public <T>Page<T> getAllMachines(Class<T> tClass, Pageable pageable, String query) {
        query = "%" + query + "%";
        return machineRepository.findAllByNameLike(tClass, pageable, query);
    }
}
