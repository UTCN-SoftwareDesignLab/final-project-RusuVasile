import authHeader, { BASE_URL, HTTP } from "../http";

export default{
    allContests(){  return HTTP.get(BASE_URL + "/contest", { headers: authHeader() }).then(
        (response) => {
          return response.data;
        }
      );
    },
    create(contest) {
        return HTTP.post(BASE_URL + "/contest", contest, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    edit(contest) {
        return HTTP.put(BASE_URL + `/contest/${contest.id}`, contest, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    delete(contest) {
        return HTTP.delete(BASE_URL + `/contest/${contest.id}`, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
}