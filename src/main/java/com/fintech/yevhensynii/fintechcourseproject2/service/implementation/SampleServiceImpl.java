package com.fintech.yevhensynii.fintechcourseproject2.service.implementation;

import com.fintech.yevhensynii.fintechcourseproject2.model.dto.SampleDto;
import com.fintech.yevhensynii.fintechcourseproject2.model.Sample;
import com.fintech.yevhensynii.fintechcourseproject2.repositories.SampleRepository;
import com.fintech.yevhensynii.fintechcourseproject2.service.SampleService;
import com.fintech.yevhensynii.fintechcourseproject2.service.converters.SampleConverter;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Log4j2
public class SampleServiceImpl implements SampleService {
    private final SampleRepository sampleRepository;
    private final SampleConverter sampleConverter;

    @Override
    public String saveSample(SampleDto sampleDto) {
        Sample sampleFromDb = sampleRepository.getSampleByIbanAndOkpoAndAddressId(
                sampleDto.getIban(),
                sampleDto.getOkpo(),
                sampleDto.getAddressId());
        if(sampleFromDb!=null) {
            log.info("Sample is already exists");
            return "Sample id " + sampleFromDb.getSampleId() + ", already exists!";
        }
        sampleRepository.save(sampleConverter.fromSampleDtoToSample(sampleDto));
        log.info(sampleDto.getSampleName() + " has been saved");
        return sampleDto.getSampleName() + " has been saved";
    }

    @Override
    public List<SampleDto> findAll() {
        return sampleRepository.findAll()
                .stream()
                .map(sampleConverter::fromSampleToSampleDto)
                .collect(Collectors.toList());
    }
}
