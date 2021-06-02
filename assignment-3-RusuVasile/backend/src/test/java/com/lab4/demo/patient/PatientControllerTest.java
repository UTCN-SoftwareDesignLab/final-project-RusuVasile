package com.lab4.demo.patient;

import com.lab4.demo.BaseControllerTest;
import com.lab4.demo.TestCreationFactory;
import com.lab4.demo.patient.model.dto.PatientDTO;
import com.lab4.demo.user.UserController;
import com.lab4.demo.user.UserService;
import com.lab4.demo.user.dto.UserDTO;
import com.lab4.demo.user.dto.UserListDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.lab4.demo.TestCreationFactory.*;
import static com.lab4.demo.TestCreationFactory.randomString;
import static com.lab4.demo.UrlMapping.USER;
import static com.lab4.demo.UrlMapping.PATIENTS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PatientControllerTest extends BaseControllerTest {

    @InjectMocks
    private PatientController controller;

    @Mock
    public PatientService patientService;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        MockitoAnnotations.openMocks(this);
        controller = new PatientController(patientService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
    @Test
    void allPatients() throws Exception {
        List<PatientDTO> patientListDTOs = TestCreationFactory.listOf(PatientDTO.class);
        when(patientService.findAll()).thenReturn(patientListDTOs);

        ResultActions result = mockMvc.perform(get(PATIENTS));
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(patientListDTOs));
    }


    @Test
    void create() throws Exception {

       PatientDTO patientDTO=PatientDTO.builder()
               .id(randomLong())
               .address(randomString())
               .name(randomString())
               .CNP(randomLong())
               .birthDate(randomDate())
               .idCardNumber(randomLong())
               .build();
        when(patientService.create(patientDTO)).thenReturn(patientDTO);

        ResultActions result = performPostWithRequestBody(PATIENTS,patientDTO);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(patientDTO));
    }

    @Test
    void edit()throws Exception {
    PatientDTO patientDTO=PatientDTO.builder()
               .id(randomLong())
               .address(randomString())
               .name(randomString())
               .CNP(randomLong())
               .birthDate(randomDate())
               .idCardNumber(randomLong())
               .build();
        when(patientService.edit(patientDTO)).thenReturn(patientDTO);
        ResultActions result = performPutWithRequestBody(PATIENTS,patientDTO);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(patientDTO));
    }

    @Test
    void delete() throws Exception {
        long id  = randomLong();
        doNothing().when(patientService).delete(id);

        ResultActions result = performDeleteWithPathVariable(PATIENTS + "/{id}", id);
        result.andExpect(status().isOk());
    }

}