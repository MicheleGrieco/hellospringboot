package com.example.hellospringboot;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/software-engineers")
public class SoftwareEngineerController {
    
    @GetMapping
    /**
     * Retrieves a list of software engineers.
     * 
     * @return a list of SoftwareEngineer objects
     */
    public List<SoftwareEngineer> getEngineers() {
        return List.of(
            new SoftwareEngineer(1, "James", "js, node, react, tailwindcss"),
            new SoftwareEngineer(2, "Jamila", "java, spring, spring boot")
        );
    }
}
