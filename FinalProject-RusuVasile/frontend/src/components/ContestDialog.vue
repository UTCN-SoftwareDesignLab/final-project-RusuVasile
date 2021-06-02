<template>
  <v-dialog
    transition="dialog-bottom-transition"
    max-width="600"
    :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="brown darken-1" dark floating>
          {{ isNew ? "Create Contest" : "Edit contest" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="contest.name" label="Contest Name" />
          <v-text-field v-model="contest.location" label="Contest Location" />
          <v-text-field v-model="contest.dogsAllowed" label="Dogs Allowed" />
          <v-text-field v-model="contest.date" label="Date " />
        </v-form>
        <v-card-actions>
          <v-btn @click="persist">{{ isNew ? "Create" : "Save" }}</v-btn>
          <v-btn @click="deleteContest">Delete Contest</v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "ContestDialog",
  props: {
    contest: Object,
    opened: Boolean,
  },
  methods: {
    persist() {
      if (this.isNew) {
        api.contest
          .create({
            name: this.contest.name,
            location: this.contest.location,
            dogsAllowed: this.contest.dogsAllowed,
            date: this.contest.date,
          })
          .then(() => this.$emit("refresh"));
      } else {
        api.contest
          .edit({
            id: this.book.id,
            name: this.contest.name,
            location: this.contest.location,
            dogsAllowed: this.contest.dogsAllowed,
            date: this.contest.date,
          })
          .then(() => this.$emit("refresh"));
      }
    },

    deleteContest() {
      api.contest
        .delete({
          id: this.contest.id,
        })
        .then(() => this.$emit("refresh"));
    },
  },
  computed: {
    isNew: function () {
      return !this.contest.id;
    },
  },
};
</script>

<style scoped></style>
