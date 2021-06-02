<template>
  <v-dialog
    transition="dialog-bottom-transition"
    max-width="600"
    :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="brown darken-1" dark floating>
          {{ isNew ? "Create Team" : "Edit team" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="team.name" label="Team Name" />
          <v-text-field v-model="team.musherName" label="Musher Name" />
          <v-text-field v-model="team.numberOfDogs" label="Number of Dogs" />
        </v-form>
        <v-card-actions>
          <v-btn @click="persist">{{ isNew ? "Create" : "Save" }}</v-btn>
          <v-btn @click="deleteTeam">Delete Team</v-btn>
          <v-btn @click="diploma">Diploma</v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "TeamDialog",
  props: {
    team: Object,
    opened: Boolean,
  },
  methods: {
    persist() {
      if (this.isNew) {
        api.team
          .create({
            name: this.team.name,
            musherName: this.team.musherName,
            numberOfDogs: this.team.numberOfDogs,
          })
          .then(() => this.$emit("refresh"));
      } else {
        api.team
          .edit({
            id: this.team.id,
            name: this.team.name,
            musherName: this.team.musherName,
            numberOfDogs: this.team.numberOfDogs,
            description: this.team.description,
          })
          .then(() => this.$emit("refresh"));
      }
    },

    deleteTeam() {
      api.team
        .delete({
          id: this.team.id,
        })
        .then(() => this.$emit("refresh"));
    },
    diploma() {
      api.team.createPDF({
        id: this.team.id,
        name: this.team.name,
        musherName: this.team.musherName,
        numberOfDogs: this.team.numberOfDogs,
        description: this.team.description,
      });
    },
  },
  computed: {
    isNew: function () {
      return !this.team.id;
    },
  },
};
</script>

<style scoped></style>
