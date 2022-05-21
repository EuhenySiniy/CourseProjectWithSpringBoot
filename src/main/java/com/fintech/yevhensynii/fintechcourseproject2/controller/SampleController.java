package com.fintech.yevhensynii.fintechcourseproject2.controller;

import com.fintech.yevhensynii.fintechcourseproject2.model.dto.SampleDto;
import com.fintech.yevhensynii.fintechcourseproject2.service.SampleService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sample")
@AllArgsConstructor
@Log4j2
public class SampleController {
    private final SampleService sampleService;

    @PostMapping("/save")
    public String saveSample(@RequestBody @Valid SampleDto sampleDto) {
        return sampleService.saveSample(sampleDto);
    }

    @GetMapping("/list-samples")
    public List<SampleDto> showAllSamples() {
        return sampleService.findAll();
    }
}
