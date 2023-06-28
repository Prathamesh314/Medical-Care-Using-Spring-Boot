package com.medicare.ProjectforMedical.Controller;

import com.medicare.ProjectforMedical.Dto.DoctorRequest;
import com.medicare.ProjectforMedical.Dto.DoctorResponse;
import com.medicare.ProjectforMedical.Service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    // create
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public String createDoctor(@RequestBody DoctorRequest doctorRequest){
        doctorService.createDoc(doctorRequest);
        return "Doctor is created";
    }

    // get
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<DoctorResponse> getAllDcotors(){
        return doctorService.getAllDocs();
    }

    @GetMapping("/{docID}")
    @ResponseStatus(HttpStatus.OK)
    public DoctorResponse getDocById(@PathVariable Integer docID){
        return doctorService.findDocById(docID);
    }

    // put
    @PutMapping("/{docID}")
    @ResponseStatus(HttpStatus.OK)
    public String updateUser(@RequestBody DoctorRequest doctorRequest,@PathVariable Integer docID){
        doctorService.updateDoc(doctorRequest,docID);
        return "Doctor has been Updated";
    }

    // delete
    @DeleteMapping("/{docID}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteDoc(@PathVariable Integer docID){
        doctorService.deleteDoc(docID);
        return "Doctor has been deleted";
    }
}
