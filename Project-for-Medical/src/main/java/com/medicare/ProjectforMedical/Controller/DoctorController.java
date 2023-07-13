package com.medicare.ProjectforMedical.Controller;

import com.medicare.ProjectforMedical.Dto.AppointmentResponse;
import com.medicare.ProjectforMedical.Dto.DoctorRequest;
import com.medicare.ProjectforMedical.Dto.DoctorResponse;
import com.medicare.ProjectforMedical.Service.AppointmentService;
import com.medicare.ProjectforMedical.Service.DoctorService;
import com.medicare.ProjectforMedical.Service.FileService;
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
import java.util.List;

@RestController
@RequestMapping("/api/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;
    private final FileService fileService;

    private final AppointmentService appointmentService;

    @Value("${project.image}")
    private String path;

    // create
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String createDoctor(@RequestBody DoctorRequest doctorRequest){
        doctorService.createDoc(doctorRequest);
        return "Doctor is created";
    }

    @PostMapping("/image/upload/{docID}")
    public DoctorResponse uploadImage(
            @RequestParam("image")MultipartFile image,
            @PathVariable Integer docID
            ) throws IOException {
        DoctorResponse doctorResponse = doctorService.findDocById(docID);
        String fileName = fileService.uploadImage(path,image);
        doctorResponse.setImage(fileName);
        doctorService.updateDoc(MapToDocRequest(doctorResponse),docID);
        DoctorResponse updatedResponse = doctorService.findDocById(docID);
        return updatedResponse;
    }

    private DoctorRequest MapToDocRequest(DoctorResponse doctorResponse) {
        return DoctorRequest.builder()
                .name(doctorResponse.getName())
                .speciality(doctorResponse.getSpeciality())
                .image(doctorResponse.getImage())
                .experience(doctorResponse.getExperience())
                .build();
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


    @GetMapping(value = "/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable String imageName,
            HttpServletResponse response
    ) throws IOException {
        InputStream resource = fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }

    @GetMapping("/{docID}/appointments")
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentResponse> getAllAppointments(@PathVariable Integer docID){
        DoctorResponse doctorResponse = doctorService.findDocById(docID);
        return appointmentService.getAllAppointmentsByDoc(doctorResponse.getEmail());
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
