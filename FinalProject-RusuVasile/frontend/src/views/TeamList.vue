<template>
  <v-card tile shaped>
    <v-card-title>
      Teams
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
      <v-btn @click="addTeam">Add Team</v-btn>
      <v-btn @click="contests">Contest CRUD</v-btn>
      <v-btn @click="results">Result CRUD</v-btn>
    </v-card-title>
    <v-data-table
      :headers="headers"
      :items="teams"
      :search="search"
      @click:row="editTeam"
    ></v-data-table>

    <TeamDialog
      :opened="dialogVisible"
      :team="selectedTeam"
      @refresh="refreshList"
    ></TeamDialog>
  </v-card>
</template>
<script>
import api from "../api";
import router from "../router";
import TeamDialog from "../components/TeamDialog";

export default {
  name: "TeamList",
  components: { TeamDialog },
  data() {
    return {
      teams: [],
      search: "",
      headers: [
        {
          text: "Title",
          align: "start",
          sortable: false,
          value: "title",
        },
        { text: "Name", value: "name" },
        { text: "Genre", value: "musherName" },
        { text: "Dogs", value: "numberOfDogs" },
        { text: "Description", value: "description" },
      ],
      dialogVisible: false,
      selectedTeam: {},
    };
  },
  methods: {
    contests() {
      router.push("./contest");
    },
    results() {
      router.push("./results");
    },
    editTeam(team) {
      this.selectedTeam = team;
      this.dialogVisible = true;
    },
    addTeam() {
      this.dialogVisible = true;
    },
    async refreshList() {
      this.dialogVisible = false;
      this.selectedTeam = {};
      this.teams = await api.team.allTeams();
    },
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>
