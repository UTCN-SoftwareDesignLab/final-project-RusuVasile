<template>
  <v-dialog
    transition="dialog-bottom-transition"
    max-width="600"
    :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="brown darken-1" dark floating>
          {{ isNew ? "Create Patient" : "Edit Patient" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="patient.name" label="Name" />
          <v-text-field v-model="patient.address" label="Address" />
          <v-text-field v-model="patient.cnp" label="CNP" />
          <v-text-field v-model="patient.idCardNumber" label="ID CARD NUMBER" />
          <v-text-field v-model="patient.birthDate" label="Birth Date" />
        </v-form>
        <v-card-actions>
          <v-btn @click="persist">{{ isNew ? "Create" : "Save" }}</v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "PatientsCRUD",
  props: {
    patient: Object,
    opened: Boolean,
  },
  methods: {
    persist() {
      if (this.isNew) {
        api.patients
          .create({
            name: this.patient.name,
            address: this.patient.address,
            cnp: this.patient.cnp,
            idCardNumber: this.patient.idCardNumber,
            birthDate: this.patient.birthDate,
          })
          .then(() => this.$emit("refresh"));
      } else {
        api.patients
          .edit({
            id: this.patient.id,
            name: this.patient.name,
            address: this.patient.address,
            cnp: this.patient.cnp,
            idCardNumber: this.patient.idCardNumber,
            birthDate: this.patient.birthDate,
          })
          .then(() => this.$emit("refresh"));
      }
    },
    deletePatient() {
      api.patients
        .delete({
          id: this.patient.id,
        })
        .then(() => this.$emit("refresh"));
    },
  },
  computed: {
    isNew: function () {
      return !this.patient.id;
    },
  },
};
</script>

<style scoped></style>
