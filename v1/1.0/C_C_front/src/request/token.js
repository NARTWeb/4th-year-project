export function getToken() {
  console.log("get token: " + localStorage.token);
  return localStorage.token;
}

export function setToken(token) {
  console.log("set token: " + token);
  return (localStorage.token = token);
}

export function removeToken() {
  console.log("remove token: ");
  return localStorage.removeItem("token");
}
