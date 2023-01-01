import req from "@/request";

export function uploadPic(pic, album) {
  return req({
    headers: {
      "Content-Type":
        "multipart/form-data;boundary=<calculated when request is sent",
    },
    contentType: "application/json",
    url: `/upload/${album}`,
    method: "post",
    data: pic,
  });
}

export function deletePic(id) {
  return req({
    url: `/upload/delete/${id}`,
    method: "put",
    data: null,
  });
}
