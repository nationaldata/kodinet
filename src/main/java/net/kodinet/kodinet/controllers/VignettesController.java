package net.kodinet.kodinet.controllers;

import net.kodinet.kodinet.entities.Vignette;
import net.kodinet.kodinet.models.ApiResponse;
import net.kodinet.kodinet.repositories.VignetteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/vignettes")
public class VignettesController {

    @Autowired
    VignetteRepository vignetteRepository;
    ApiResponse apiResponse = new ApiResponse();

    @PostMapping("/create")
    public ResponseEntity<?> save(@RequestBody Vignette vignette) {

        vignetteRepository.save(vignette);
        apiResponse.setResponseCode("00");
        apiResponse.setResponseMessage("Vignette created");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        apiResponse.setData(vignetteRepository.findAll());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/pages")
    public ResponseEntity<?> findPagedData(@RequestParam int page, @RequestParam int size) {

        PageRequest pageable = PageRequest.of(page, size);
        apiResponse.setData(vignetteRepository.findPagedData(pageable));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/pages/dates")
    public ResponseEntity<?> findBetweenDates(@RequestParam Long date1,
                                              @RequestParam Long date2,
                                              @RequestParam int page,
                                              @RequestParam int size) {
        PageRequest pageable = PageRequest.of(page, size);
        apiResponse.setData(vignetteRepository.findBetweenDates(new Date(date1),
                new Date(date2), pageable));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
