import req from "@/request";

export function uploadPic(pic) {
    return req({
      headers: {'Content-Type': 'multipart/form-data'},
      url: '/upload',
      method: 'post',
      data: pic
    })
  }