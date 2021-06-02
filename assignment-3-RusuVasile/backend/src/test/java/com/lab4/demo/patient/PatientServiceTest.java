package com.lab4.demo.patient;

import com.lab4.demo.patient.model.Patient;
import com.lab4.demo.patient.model.dto.PatientDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PatientServiceTest {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepository patientRepository;

    @BeforeEach
    void setUp(){

        patientRepository.deleteAll();
    }

    @Test
    void findByName() {
        String name=randomString();
        Patient patient =Patient.builder()
                .id(randomLong())
                .address(randomString())
                .birthDate(randomDate())
                .CNP(randomLong())
                .idCardNumber(randomLong())
                .name(name)
                .build();
        patientRepository.save(patient);
        assertEquals(name,patientService.findByName(name).getName());
    }


    @Test
    void findAll() {
        int nrPatients=10;
        List<Patient> patientList=new ArrayList<>();
        for(int i=0 ;i<10;i++){
            Patient patient =Patient.builder()
                    .id(randomLong())
                    .address(randomString())
                    .birthDate(randomDate())
                    .CNP(randomLong())
                    .idCardNumber(randomLong())
                    .name("Patient"+i)
                    .build();
            patientList.add(patient);
            patientRepository.save(patient);
        }
        List<PatientDTO> allPatients=patientService.findAll();
        assertEquals(allPatients.size(),10);
    }

    @Test
    void create() {
        PatientDTO patient =PatientDTO.builder()
                .id(randomLong())
                .address("This Address")
                .birthDate(randomDate())
                .CNP(100L)
                .idCardNumber(randomLong())
                .name("Patient")
                .build();
        patientService.create(patient);
        assertEquals(patient.getAddress(),patientService.findByName("Patient").getAddress());

    }

    @Test
    void edit() {
        PatientDTO patient =PatientDTO.builder()
                .id(randomLong())
                .address("This Address")
                .birthDate(randomDate())
                .CNP(100L)
                .idCardNumber(randomLong())
                .name("Patient")
                .build();
        patientService.create(patient);
        assertEquals(patient.getAddress(),patientService.findByName("Patient").getAddress());
        System.out.println(patient.getId());
        patient.setAddress("This Edited Address");
        patientService.edit(patient);
        System.out.println(patient.getId());
        assertEquals(patient.getAddress(),patientService.findByName("Patient").getAddress());

    }

    public static long randomLong() {
        return new Random().nextInt(1999);
    }

    public static Boolean randomBoolean() {
        return new Random().nextBoolean();
    }

    public static Date randomDate() {
        return new java.sql.Date(1);
    }

    public static int randomBoundedInt(int upperBound) {
        return new Random().nextInt(upperBound);
    }

    public static String randomString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }


}