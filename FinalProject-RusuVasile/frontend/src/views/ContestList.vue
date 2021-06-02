<template>
  <v-card tile shaped>
    <v-card-title>
      Contests
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
      <v-btn @click="addContest">Add Contest</v-btn>
      <v-btn @click="teams">Teams CRUD</v-btn>
      <v-btn @click="results">Result CRUD</v-btn>
    </v-card-title>
    <v-data-table
      :headers="headers"
      :items="contests"
      :search="search"
      @click:row="editContest"
    ></v-data-table>

    <ContestDialog
      :opened="dialogVisible"
      :contest="selectedContest"
      @refresh="refreshList"
    ></ContestDialog>
  </v-card>
</template>
<script>
import api from "../api";
import router from "../router";
import ContestDialog from "../components/ContestDialog";

export default {
  name: "ContestList",
  components: { ContestDialog },
  data() {
    return {
      contests: [],
      search: "",
      headers: [
        {
          text: "Title",
          align: "start",
          sortable: false,
          value: "title",
        },
        { text: "Name", value: "name" },
        { text: "Location", value: "location" },
        { text: "DogsAllowed", value: "dogsAllowed" },
        { text: "Date", value: "date" },
      ],
      dialogVisible: false,
      selectedContest: {},
    };
  },
  methods: {
    teams() {
      router.push("./team");
    },
    results() {
      router.push("./results");
    },
    editContest(contest) {
      this.selectedContest = contest;
      this.dialogVisible = true;
    },
    addContest() {
      this.dialogVisible = true;
    },
    async refreshList() {
      this.dialogVisible = false;
      this.selectedContest = {};
      this.contests = await api.contest.allContests();
    },
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>
