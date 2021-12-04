package com.momo;

import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import com.momo.district.domain.model.City;
import com.momo.district.domain.model.District;
import com.momo.district.domain.repository.DistrictRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DistrictDataLoader implements CommandLineRunner {

    private static final int MAX_DISTRICT_COUNT = 261;
    private static final String DISTRICT_XLSX_PATH = "db/district.xlsx";

    private final DistrictRepository districtRepository;

    @Override
    public void run(String... args) {
        long savedDistrictCount = districtRepository.count();
        if (savedDistrictCount == MAX_DISTRICT_COUNT) {
            return;
        }
        insertDistrictData();
    }

    public void insertDistrictData() {
        List<District> districts = parseDistrictXlsxFile();
        insertDistricts(districts);
    }

    public List<District> parseDistrictXlsxFile() {
        List<District> districts = new ArrayList<>();
        ClassPathResource resource = new ClassPathResource(DISTRICT_XLSX_PATH);
        try {
            Workbook workbook = new XSSFWorkbook(resource.getInputStream());
            Sheet workSheet = workbook.getSheetAt(0);
            for (int i = 0; i < workSheet.getPhysicalNumberOfRows(); i++) {
                Row row = workSheet.getRow(i);
                District district = District.builder()
                    .city(City.fromName(row.getCell(0).getStringCellValue()))
                    .districtName(row.getCell(1).getStringCellValue())
                    .build();
                districts.add(district);
            }
        } catch (IOException e) {
            throw new CustomException(ErrorCode.PARSING_DISTRICT_FILE_ERROR);
        }
        return districts;
    }

    public void insertDistricts(List<District> districts) {
        districtRepository.saveAll(districts);
    }
}
