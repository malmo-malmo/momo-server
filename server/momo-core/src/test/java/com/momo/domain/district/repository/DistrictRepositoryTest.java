package com.momo.domain.district.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.RepositoryTest;
import com.momo.district.entity.City;
import com.momo.district.entity.District;
import com.momo.district.repository.DistrictRepository;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("구역 레포지토리 테스트")
public class DistrictRepositoryTest extends RepositoryTest {
    @Autowired
    private DistrictRepository districtRepository;

    private District district;

    @BeforeEach
    public void before() {
        district = districtRepository.save(
            District.builder()
                .city(City.SEOUL)
                .districtName("마포구")
                .build()
        );
    }

    @Test
    public void 구역을_저장한다() {
        District district = districtRepository.findAll().get(0);
        Assertions.assertAll(
            () -> assertThat(district).isEqualTo(this.district),
            () -> assertThat(district.getCity()).isEqualTo(City.SEOUL),
            () -> assertThat(district.getDistrictName()).isEqualTo("마포구")
        );
    }

    @Test
    public void 도시에_해당하는_구역_목록을_조회한다() {
        List<District> districts = districtRepository.findAllByCity(City.SEOUL);

        assertThat(districts.size()).isEqualTo(1);

        District district = districts.get(0);
        assertThat(district).isEqualTo(this.district);
    }
}
