import req from "@/request";

export function uploadPic(pic) {
    return req({
      url: '/upload',
      method: 'post',
      data: pic
    })
  }