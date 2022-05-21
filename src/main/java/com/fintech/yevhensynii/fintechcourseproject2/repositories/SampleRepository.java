package com.fintech.yevhensynii.fintechcourseproject2.repositories;

import com.fintech.yevhensynii.fintechcourseproject2.model.Sample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleRepository extends JpaRepository<Sample, Long> {
    Sample getSampleByIbanAndOkpoAndAddressId(String iban, String okpo, Long addressId);
}
