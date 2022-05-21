package com.fintech.yevhensynii.fintechcourseproject2.service.converters;

import com.fintech.yevhensynii.fintechcourseproject2.model.dto.SampleDto;
import com.fintech.yevhensynii.fintechcourseproject2.model.Sample;
import org.springframework.stereotype.Component;

@Component
public class SampleConverter {
    public Sample fromSampleDtoToSample(SampleDto sampleDto) {
        Sample sample = new Sample();
        sample.setSampleId(sampleDto.getSampleId());
        sample.setSampleName(sampleDto.getSampleName());
        sample.setIban(sampleDto.getIban());
        sample.setOkpo(sampleDto.getOkpo());
        sample.setAppointment(sampleDto.getAppointment());
        sample.setAddressId(sampleDto.getAddressId());
        return sample;
    }

    public SampleDto fromSampleToSampleDto(Sample sample) {
        return SampleDto.builder()
                .sampleId(sample.getSampleId())
                .sampleName(sample.getSampleName())
                .iban(sample.getIban())
                .okpo(sample.getOkpo())
                .appointment(sample.getAppointment())
                .addressId(sample.getAddressId())
                .build();
    }
}
