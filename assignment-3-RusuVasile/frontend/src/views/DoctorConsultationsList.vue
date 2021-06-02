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
export default {
  name: "DoctorConsultationList",
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
    editConsultation(consultation) {
      this.selectedConsultation = consultation;
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
    this.connectAndSubscribe();
  },
};
</script>

<style scoped></style>
