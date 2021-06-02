<template>
  <v-card tile shaped>
    <v-card-title>
      Consultations
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
      <v-btn @click="addConsultation">Add Consultation</v-btn>
      <v-btn @click="switchToPatients">Patients</v-btn>
    </v-card-title>
    <v-data-table
      :headers="headers"
      :items="consultations"
      :search="search"
      @click:row="editConsultation"
    ></v-data-table>

    <ConsultationDialog
      :opened="dialogVisible"
      :consultation="selectedConsultation"
      @refresh="refreshList"
    ></ConsultationDialog>
  </v-card>
</template>

<script>
import ConsultationDialog from "../components/ConsultationDialog";
import api from "../api";
import router from "../router";
export default {
  name: "ConsultationList",
  components: { ConsultationDialog },
  data() {
    return {
      consultations: [],
      search: "",
      headers: [
        {
          text: "Title",
          align: "start",
          sortable: false,
          value: "title",
        },
        { text: "Patient", value: "patientName" },
        { text: "Doctor", value: "doctorName" },
        { text: "Date", value: "date" },
        { text: "Time", value: "time" },
        { text: "Description", value: "diagnosis" },
      ],
      dialogVisible: false,
      selectedConsultation: {},
    };
  },
  methods: {
    switchToPatients() {
      router.push("./patients");
    },
    editConsultation(consultation) {
      this.selectedConsultation = consultation;
      this.dialogVisible = true;
    },
    addConsultation() {
      this.dialogVisible = true;
    },

    async refreshList() {
      this.dialogVisible = false;
      this.selectedConsultation = {};
      this.consultations = await api.consultations.allConsultations();
    },
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>
