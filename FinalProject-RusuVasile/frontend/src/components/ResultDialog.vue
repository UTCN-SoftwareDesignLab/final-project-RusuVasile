<template>
  <v-dialog
    transition="dialog-bottom-transition"
    max-width="600"
    :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="brown darken-1" dark floating> </v-toolbar>
        <v-form>
          <v-text-field v-model="result.contest" label="Result Contest ID" />
          <v-text-field v-model="result.team" label="Result Team ID" />
          <v-text-field v-model="result.placement" label="Placement" />
        </v-form>
        <v-card-actions>
          <v-btn @click="persist">Create</v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "ResultDialog",
  props: {
    result: Object,
    opened: Boolean,
  },
  methods: {
    persist() {
      api.results
        .create({
          team: this.result.team,
          contest: this.result.contest,
          placement: this.result.placement,
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
