package com.example.demo.service;

import com.example.demo.model.PostalCode;
import com.example.demo.model.State;
import com.example.demo.repositories.AddressRepository;
import com.example.demo.repositories.StateRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class AddAddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private StateRepository stateRepository;

    Logger logger = Logger.getLogger(AddAddressService.class.getName());


    public void saveExcelData(MultipartFile file) {
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            List<PostalCode> Addresses = new ArrayList<>();
            int batchSize = 1000; // Adjust batch size as needed
            int totalRows = sheet.getPhysicalNumberOfRows();
            State state;

            for (int i = 1; i < totalRows /*sheet.getPhysicalNumberOfRows()*/; i++) {
                Row row = sheet.getRow(i);
                PostalCode address = new PostalCode();
                address.setPincode((int) row.getCell(4).getNumericCellValue());
                // Find the state by name
                String stateName = row.getCell(8).getStringCellValue();
                switch (stateName) {
                    case "Megalaya":
                        stateName = "Meghalaya";
                        break;
                    case "Chattisgarh" :
                        stateName = "Chhattisgarh";
                        break;
                    case "Pondicherry" :
                        stateName = "Puducherry";
                        break;
                    case "Dadra and Nagar Hav." :
                        stateName = "Dadra and Nagar Haveli";
                        break;
                    case "Andaman and Nico.In.":
                        stateName = "Andaman and Nicobar Islands";
                        break;
                    default:
                        stateName = stateName;
                        break;
                }

                /*logger.info("state name is " + stateName);*/
                Optional<State> stateOptional = stateRepository.findByStateName(stateName);
                address.setState(stateOptional.orElse(null));
              /*  logger.info("address added is " + address.toString());*/
                Addresses.add(address);
                if (Addresses.size() == batchSize || i == totalRows - 1) {
                    addressRepository.saveAll(Addresses);
                    Addresses.clear();
                }
            }
            addressRepository.saveAll(Addresses);
            logger.info("total no of rows in the sheet are " + sheet.getPhysicalNumberOfRows());
        } catch (IOException e) {
            throw new RuntimeException("Failed to read Excel file", e);
        }catch (Exception e) {
            throw new RuntimeException("Error occurred while processing the file: " + e.getMessage());
        }
    }
}
