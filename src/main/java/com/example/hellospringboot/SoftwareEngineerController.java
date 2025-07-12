package com.example.hellospringboot;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/software-engineers")
public class SoftwareEngineerController {
    
    private final SoftwareEngineerService softwareEngineerService;
    
    public SoftwareEngineerController(SoftwareEngineerService softwareEngineerService) {
        this.softwareEngineerService = softwareEngineerService;
    }
    
    @GetMapping
    /**
     * Retrieves a list of software engineers.
     * 
     * @return a list of SoftwareEngineer objects
     */
    public List<SoftwareEngineer> getEngineers() {
            return softwareEngineerService.getAllSoftwareEngineers();
    }
}
