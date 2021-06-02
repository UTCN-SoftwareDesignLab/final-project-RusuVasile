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