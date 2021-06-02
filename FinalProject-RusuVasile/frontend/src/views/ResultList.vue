<template>
  <v-card tile shaped>
    <v-card-title>
      Results
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
      <v-btn @click="addResult">Add Results</v-btn>
      <v-btn @click="contests">Contest CRUD</v-btn>
      <v-btn @click="teams">Teams CRUD</v-btn>
    </v-card-title>
    <v-data-table
      :headers="headers"
      :items="results"
      :search="search"
    ></v-data-table>

    <ResultDialog
      :opened="dialogVisible"
      :result="selectedResult"
      @refresh="refreshList"
    >
    </ResultDialog>
  </v-card>
</template>
<script>
import api from "../api";
import router from "../router";
import ResultDialog from "../components/ResultDialog";

export default {
  name: "ResultList",
  components: { ResultDialog },
  data() {
    return {
      results: [],
      search: "",
      headers: [
        {
          text: "Title",
          align: "start",
          sortable: false,
          value: "title",
        },
        { text: "TeamID", value: "team" },
        { text: "ContestID", value: "contest" },
        { text: "Placement", value: "placement" },
      ],
      dialogVisible: false,
      selectedResult: {},
    };
  },
  methods: {
    teams() {
      router.push("./team");
    },
    contests() {
      router.push("./contest");
    },

    addResult() {
      this.dialogVisible = true;
    },
    async refreshList() {
      this.dialogVisible = false;
      this.selectedResult = {};
      this.results = await api.results.allResults();
    },
  },
  created() {
    this.refreshList();
  },
};
</script>
<style scoped></style>
