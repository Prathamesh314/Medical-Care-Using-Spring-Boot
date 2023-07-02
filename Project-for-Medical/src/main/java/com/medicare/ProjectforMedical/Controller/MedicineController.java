package com.medicare.ProjectforMedical.Controller;

import com.medicare.ProjectforMedical.Dto.MedicineRequest;
import com.medicare.ProjectforMedical.Dto.MedicineResponse;
import com.medicare.ProjectforMedical.Repository.MedicineRepository;
import com.medicare.ProjectforMedical.Service.FileService;
import com.medicare.ProjectforMedical.Service.MedicineService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;
    private final FileService fileService;

    @Value("$project.image")
    private String path;

    // create
    @PostMapping("/user/{userID}/category/{categoryID}/medicines")
    @ResponseStatus(HttpStatus.CREATED)
    public String createMedicines(@RequestBody MedicineRequest medicineRequest,@PathVariable Integer userID,@PathVariable Integer categoryID){
        medicineService.createMedicine(medicineRequest,userID,categoryID);
        return "Medicine has been created";
    }

    @PostMapping("/medicine/image/upload/{medID}")
    @ResponseStatus(HttpStatus.CREATED)
    public MedicineResponse uploadImage(
            @RequestParam("image")MultipartFile image,
            @PathVariable Integer medID
            ) throws IOException {
        MedicineResponse medicineResponse = medicineService.getMedById(medID);
        String fileName = fileService.uploadImage(path,image);
        medicineResponse.setImage(fileName);
        medicineService.updateMedicine(MapToMedRequest(medicineResponse),medID);
        MedicineResponse updatedResponse = medicineService.getMedById(medID);
        return updatedResponse;
    }

    private MedicineRequest MapToMedRequest(MedicineResponse medicineResponse) {
        return MedicineRequest.builder()
                .name(medicineResponse.getName())
                .Time(medicineResponse.getTime())
                .medToTake(medicineResponse.getMedToTake())
                .numOfMeds(medicineResponse.getNumOfMeds())
                .image(medicineResponse.getImage())
                .build();
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

    @GetMapping("/medicine/{medicineName}")
    @ResponseStatus(HttpStatus.OK)
    public void downloadImage(
            @PathVariable String medicineName,
            HttpServletResponse response
    ) throws IOException
    {
        InputStream resource = fileService.getResource(path,medicineName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
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
