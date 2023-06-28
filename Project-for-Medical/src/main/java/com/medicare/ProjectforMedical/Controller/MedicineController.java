package com.medicare.ProjectforMedical.Controller;

import com.medicare.ProjectforMedical.Dto.MedicineRequest;
import com.medicare.ProjectforMedical.Dto.MedicineResponse;
import com.medicare.ProjectforMedical.Service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;

    // create
    @PostMapping("/user/{userID}/category/{categoryID}/medicines")
    @ResponseStatus(HttpStatus.CREATED)
    public String createMedicines(@RequestBody MedicineRequest medicineRequest,@PathVariable Integer userID,@PathVariable Integer categoryID){
        medicineService.createMedicine(medicineRequest,userID,categoryID);
        return "Medicine has been created";
    }

    // get
    @GetMapping("/medicines")
    @ResponseStatus(HttpStatus.OK)
    public List<MedicineResponse> getAllMedicines(){
        return medicineService.getAllMeds();
    }

    @GetMapping("/medicines/{medID}")
    @ResponseStatus(HttpStatus.OK)
    public MedicineResponse getMedByID(@PathVariable Integer medID){
        return medicineService.getMedById(medID);
    }

    @GetMapping("/user/{userID}/medicines")
    @ResponseStatus(HttpStatus.OK)
    public List<MedicineResponse> getMedByUser(@PathVariable Integer userID){
        return medicineService.getMedByUser(userID);
    }

    @GetMapping("/category/{categoryID}/medicines")
    @ResponseStatus(HttpStatus.OK)
    public List<MedicineResponse> getMedByCategory(@PathVariable Integer categoryID){
        return medicineService.getMedByCategory(categoryID);
    }

    //update

    @PutMapping("/medicines/{medID}")
    @ResponseStatus(HttpStatus.OK)
    public String updateMed(@RequestBody MedicineRequest medicineRequest,@PathVariable Integer medID){
        medicineService.updateMedicine(medicineRequest,medID);
        return "Medicine has been updated Successfully";
    }

    // delete
    @DeleteMapping("/medicines/{medID}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteMedByID(@PathVariable Integer medID){
        medicineService.deleteById(medID);
        return "Medicine is deleted successfully";
    }

}
