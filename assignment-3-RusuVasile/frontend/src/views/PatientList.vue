<template>
  <v-card tile shaped>
    <v-card-title>
      Patients
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
      <v-btn @click="addPatient">Add Patient</v-btn>
      <v-btn @click="switchToConsultations">Consultations</v-btn>
    </v-card-title>
    <v-data-table
      :headers="headers"
      :items="patients"
      :search="search"
      @click:row="editPatient"
    ></v-data-table>

    <PatientsCRUD
      :opened="dialogVisible"
      :patient="selectedPatient"
      @refresh="refreshList"
    ></PatientsCRUD>
  </v-card>
</template>

<script>
import PatientsCRUD from "../components/PatientsCRUD";
import api from "../api";
import router from "../router";

export default {
  name: "PatientList",
  components: { PatientsCRUD },
  data() {
    return {
      patients: [],
      search: "",
      headers: [
        {
          text: "Title",
          align: "start",
          sortable: false,
          value: "title",
        },
        { text: "Name", value: "name" },
        { text: "Address", value: "address" },
        { text: "CNP", value: "cnp" },
        { text: "ID CARD NUMBER", value: "idCardNumber" },
        { text: "Birth Date", value: "birthDate" },
      ],
      dialogVisible: false,
      selectedPatient: {},
    };
  },
  methods: {
    switchToConsultations() {
      router.push("./consultations");
    },
    editPatient(patient) {
      this.selectedPatient = patient;
      this.dialogVisible = true;
    },
    addPatient() {
      this.dialogVisible = true;
    },
    async refreshList() {
      this.dialogVisible = false;
      this.selectedPatient = {};
      this.patients = await api.patients.allPatients();
    },
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>
