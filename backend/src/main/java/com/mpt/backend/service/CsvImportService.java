package com.mpt.backend.service;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.mpt.backend.model.Company;
import com.mpt.backend.model.CompanyPerformanceHistory;
import com.mpt.backend.repository.CompanyPerformanceHistoryRepository;
import com.mpt.backend.repository.CompanyRepository;

import jakarta.annotation.PostConstruct;

@Service
public class CsvImportService {
	
	private static final Logger logger = LoggerFactory.getLogger(CsvImportService.class);

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private CompanyPerformanceHistoryRepository companyPerformanceHistoryRepository;
	
	@Autowired
    public CsvImportService() {
        logger.info("CsvImportService instantiated");
    }

	private static final String CSV_FOLDER_PATH = "./src/main/resources/data";

	@PostConstruct
	public void importCsvData() {
		try {
			Files.list(Paths.get(CSV_FOLDER_PATH))
				.filter(Files::isRegularFile)
				.filter(file -> file.toString().endsWith(".csv"))
				.forEach(this::processCsvFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void processCsvFile(Path filePath) {
		String cieId = filePath.getFileName().toString().replace(".csv", "");
		Company company = new Company();
		company.setId(cieId);
		companyRepository.save(company);

		try (CSVParser parser = new CSVParser(new FileReader(filePath.toFile()),
				CSVFormat.Builder.create(CSVFormat.DEFAULT).setHeader().setSkipHeaderRecord(true).build());) {
			
			DateFormat df = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
			for (CSVRecord record : parser) {
				try {
				CompanyPerformanceHistory history = new CompanyPerformanceHistory();
				history.setCieId(cieId);
				history.setOpen(Float.parseFloat(record.get("Open")));
				history.setDate(df.parse(record.get("Date")));
				history.setClose(Float.parseFloat(record.get("Close")));
				history.setHigh(Float.parseFloat(record.get("High")));
				history.setLow(Float.parseFloat(record.get("Low")));
				history.setVolume(Integer.parseInt(record.get("Volume")));
				companyPerformanceHistoryRepository.save(history);
				} catch (ParseException e) {
					logger.error("Error parsing date for record: {}", record, e);
				} catch (DataIntegrityViolationException e) {
					logger.error("Data integrity violation for record: {}", record, e);
				}
			}
			logger.info("Data from {} retrieved", cieId);

		} catch (IOException e) {
			logger.error("Error reading file: {}", filePath, e);
		} 
	}
}
