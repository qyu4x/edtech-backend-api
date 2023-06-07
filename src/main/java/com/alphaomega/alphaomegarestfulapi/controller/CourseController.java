package com.alphaomega.alphaomegarestfulapi.controller;

import com.alphaomega.alphaomegarestfulapi.payload.request.CourseRequest;
import com.alphaomega.alphaomegarestfulapi.payload.response.BannerResponse;
import com.alphaomega.alphaomegarestfulapi.payload.response.CourseResponse;
import com.alphaomega.alphaomegarestfulapi.payload.response.WebResponse;
import com.alphaomega.alphaomegarestfulapi.service.CourseService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/alpha/v1")
public class CourseController {

    private static final Logger log = LoggerFactory.getLogger(CourseController.class);

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PreAuthorize("hasRole('INSTRUCTOR') or hasRole('ADMIN')")
    @PostMapping("/course/{instructorId}")
    public ResponseEntity<WebResponse<CourseResponse>> createCourse(@PathVariable("instructorId") String instructorId, @Valid @RequestBody CourseRequest courseRequest) {
        log.info("Request create course from instructor id {}", instructorId);
        CourseResponse courseResponse = courseService.create(instructorId, courseRequest);

        WebResponse<CourseResponse> webResponse = new WebResponse<>(
                HttpStatus.CREATED.value(),
                HttpStatus.CREATED.getReasonPhrase(),
                courseResponse
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(webResponse);
    }

}