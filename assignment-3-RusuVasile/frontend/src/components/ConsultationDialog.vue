<template>
  <v-dialog
    transition="dialog-bottom-transition"
    max-width="600"
    :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="brown darken-1" dark floating>
          {{ isNew ? "Add Consultation" : "Reschedule Consultation" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="consultation.patientName" label="Patient" />
          <v-text-field v-model="consultation.doctorName" label="Doctor" />
          <v-text-field v-model="consultation.date" label="Date" />
          <v-text-field v-model="consultation.time" label="Time" />
          <v-text-field v-model="consultation.diagnosis" label="Description" />
        </v-form>
        <v-card-actions>
          <v-btn @click="persist">{{ isNew ? "Add" : "Reschedule" }}</v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "ConsultationDialog",
  props: {
    consultation: Object,
    opened: Boolean,
  },
  methods: {
    persist() {
      if (this.isNew) {
        api.consultations
          .create({
            patientName: this.consultation.patientName,
            doctorName: this.consultation.doctorName,
            date: this.consultation.date,
            time: this.consultation.time,
            diagnosis: this.consultation.diagnosis,
          })
          .then(() => this.$emit("refresh"));
      } else {
        api.consultations
          .edit({
            id: this.consultation.id,
            patientName: this.consultation.patientName,
            doctorName: this.consultation.doctorName,
            date: this.consultation.date,
            time: this.consultation.time,
            diagnosis: this.consultation.diagnosis,
          })
          .then(() => this.$emit("refresh"));
      }
    },
    deleteConsultation() {
      api.consultations
        .delete({
          id: this.consultation.id,
        })
        .then(() => this.$emit("refresh"));
    },
  },
  computed: {
    isNew: function () {
      return !this.consultation.id;
    },
  },
};
</script>

<style scoped></style>
