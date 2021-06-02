import authHeader, { BASE_URL, HTTP } from "../http";

export default{
    allResults(){  return HTTP.get(BASE_URL + "/result", { headers: authHeader() }).then(
        (response) => {
          return response.data;
        }
      );
    },
    create(result) {
        return HTTP.post(BASE_URL + "/result", result, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
 
}