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
    @PostMapping("/user/{userID}/medicines")
    @ResponseStatus(HttpStatus.CREATED)
    public String createMedicines(@RequestBody MedicineRequest medicineRequest,@PathVariable Integer userID){
        medicineService.createMedicine(medicineRequest,userID);
        return "Medicine has been created";
    }

    // get

    @GetMapping("/medicines")
    @ResponseStatus(HttpStatus.OK)
    public List<MedicineResponse> getAllMedicines(){
        return medicineService.getAllMeds();
    }

    //update

    // delete

}
