package com.medicare.ProjectforMedical.Controller;

import com.medicare.ProjectforMedical.Dto.CategoryRequest;
import com.medicare.ProjectforMedical.Dto.CategoryResponse;
import com.medicare.ProjectforMedical.Dto.MedicineRequest;
import com.medicare.ProjectforMedical.Dto.MedicineResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicines")
@RequiredArgsConstructor
public class MedicineController {

    // create
    @PostMapping("/user/{userID}")
    @ResponseStatus(HttpStatus.CREATED)
    public String createMedicines(@RequestBody MedicineRequest medicineRequest,@PathVariable Integer userID){
        return "Category is Created";
    }

    // get

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<MedicineResponse> getAllMedicines(){
        return null;
    }

    @GetMapping("/{medId}")
    @ResponseStatus(HttpStatus.OK)
    public MedicineResponse getMedById(@PathVariable Integer medId){
        return null;
    }

    // update

    @PutMapping("/{medId}")
    @ResponseStatus(HttpStatus.OK)
    public String updateMed(@RequestBody MedicineRequest medicineRequest,@PathVariable Integer medId){
        return "Medicine has been Updated";
    }

    // delete

    @DeleteMapping("/{medId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteMed(@PathVariable Integer medId){
        return "Medicine has been deleted";
    }

}
