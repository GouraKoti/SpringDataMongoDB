package com.molcon.henkelMongodb.controller;

import com.molcon.henkelMongodb.model.Henkel;
import com.molcon.henkelMongodb.repo.HenkelRepo;
import com.molcon.henkelMongodb.utils.ExcelReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class HenkelController {

    @Autowired
    private HenkelRepo henkelRepo;

@PostMapping("/henkel/import")
    public ResponseEntity<?> uploadExcel(@RequestBody String path) throws IOException {
        ExcelReader excelReader = new ExcelReader();
        excelReader.readInput(path, henkelRepo);
        return new ResponseEntity<>("Uploaded", HttpStatus.OK);
    }

  
    @PostMapping("/insert")
    public ResponseEntity<?> insertChemical(@RequestBody Henkel henkel){
        try {
            henkelRepo.save(henkel);
            return new ResponseEntity<Henkel>(henkel, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

  @GetMapping("/findAll")
    public ResponseEntity<?> getAllHenkels() {
        List<Henkel> henkels = henkelRepo.findAll();
        if (henkels.size() > 0) {
            return new ResponseEntity<List<Henkel>>(henkels, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("No chemicals available", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/henkel/{mcid}")
    public ResponseEntity<?> findById(@PathVariable("mcid") String mcid) {

        Optional<Henkel> henkelOptional = henkelRepo.findById(mcid);
        if(henkelOptional.isPresent()){
            return new ResponseEntity<>(henkelOptional.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Chemical not found with MCID" + mcid, HttpStatus.NOT_FOUND);
        }
    }

    


}

