import authHeader, { BASE_URL, HTTP } from "../http";

export default {
  allPatients() {
    return HTTP.get(BASE_URL + "/patients", { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  create(patient) {
    return HTTP.post(BASE_URL + "/patients", patient ,{ headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  delete(patient) {
    return HTTP.delete(BASE_URL + "/patients/" + patient.id, {
      headers: authHeader(),
    }).then();
  },
  edit(patient) {
    return HTTP.patch(BASE_URL + "/patients", patient, { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
};
