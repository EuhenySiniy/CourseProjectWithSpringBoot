package com.fintech.yevhensynii.fintechcourseproject2.service;

import com.fintech.yevhensynii.fintechcourseproject2.model.dto.SampleDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SampleService {
    String saveSample(SampleDto sampleDto);

    List<SampleDto> findAll();
}
