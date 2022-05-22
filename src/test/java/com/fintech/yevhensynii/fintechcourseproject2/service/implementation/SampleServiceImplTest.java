package com.fintech.yevhensynii.fintechcourseproject2.service.implementation;

import com.fintech.yevhensynii.fintechcourseproject2.model.Sample;
import com.fintech.yevhensynii.fintechcourseproject2.model.dto.SampleDto;
import com.fintech.yevhensynii.fintechcourseproject2.repositories.SampleRepository;
import com.fintech.yevhensynii.fintechcourseproject2.service.SampleService;
import com.fintech.yevhensynii.fintechcourseproject2.service.converters.SampleConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SampleServiceImplTest {
    @Autowired
    private SampleService sampleService;

    @MockBean
    private SampleRepository sampleRepository;

    @MockBean
    private SampleConverter sampleConverter;

    @Test
    void saveSampleSuccessfully() {
        SampleDto sample = new SampleDto(
                1L,
                "Name",
                "UA111111111111111111111111111",
                "12345678",
                "Som appointment",
                1L
        );
        sampleService.saveSample(sample);

        Assertions.assertTrue(true, sample.getSampleName() + " has been saved");

        Mockito.verify(sampleRepository, Mockito.times(1))
                .getSampleByIbanAndOkpoAndAddressId(
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.anyLong()
                );
        Mockito.verify(sampleConverter, Mockito.times(1)).fromSampleDtoToSample(sample);
        Mockito.verify(sampleRepository, Mockito.times(1))
                .save(sampleConverter.fromSampleDtoToSample(sample));
    }

    @Test
    void saveSampleFailed() {
        SampleDto sample = new SampleDto(
                1L,
                "Name",
                "UA111111111111111111111111111",
                "12345678",
                "Som appointment",
                1L
        );
        Sample sampleFromDb = new Sample();

        Mockito.doReturn(new Sample()).when(sampleRepository).getSampleByIbanAndOkpoAndAddressId(
                "UA111111111111111111111111111",
                "12345678",
                1L
        );

        sampleService.saveSample(sample);

        Assertions.assertTrue(true, "Sample id " + sampleFromDb.getSampleId() + ", already exists!");

        Mockito.verify(sampleConverter, Mockito.times(0)).fromSampleDtoToSample(sample);
        Mockito.verify(sampleRepository, Mockito.times(0))
                .save(sampleConverter.fromSampleDtoToSample(sample));
    }
}