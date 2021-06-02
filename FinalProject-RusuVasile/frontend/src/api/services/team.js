import authHeader, { BASE_URL, HTTP } from "../http";
import axios from "axios";
export default {
  allTeams() {
    return HTTP.get(BASE_URL + "/team", { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  create(team) {
    return HTTP.post(BASE_URL + "/team", team, { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  edit(team) {
    return HTTP.put(BASE_URL + `/team/${team.id}`, team, {
      headers: authHeader(),
    }).then((response) => {
      return response.data;
    });
  },
  delete(team) {
    return HTTP.delete(BASE_URL + `/team/${team.id}`, {
      headers: authHeader(),
    }).then((response) => {
      return response.data;
    });
  },
  createPDF(team) {
    return axios({
      url: BASE_URL + "/team/file",
      method: "Post",
      data: team,
      headers: authHeader(),
      responseType: "blob",
    }).then((response) => {
      var fileURL = window.URL.createObjectURL(new Blob([response.data]));
      var fileLink = document.createElement("a");

      fileLink.href = fileURL;
      fileLink.setAttribute("download", "pdfFile.pdf");
      document.body.appendChild(fileLink);

      fileLink.click();
    });
  },
};
