import authHeader, { BASE_URL, HTTP } from "../http";
export default {
  showTeamInfo(team) {
    return HTTP.get(BASE_URL + `/team/${team.id}`, {
      headers: authHeader(),
    }).then((response) => {
      return response.data;
    });
  },
};
