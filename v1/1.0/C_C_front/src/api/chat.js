import req from "@/request";

export function showFriendChatHistory(token, chatId) {
  let type = "friend";
  return req({
    headers: { Authorization: token },
    url: `/chat/history/${type}/${chatId}`,
    method: "get"
  });
}

export function showGroupChatHistory(token, chatId) {
  let type = "group";
  return req({
    headers: { Authorization: token },
    url: `/chat/history/${type}/${chatId}`,
    method: "get"
  });
}

export function sendFriendMsg(token, msgInfo) {
  return req({
    headers: { Authorization: token },
    url: "/chat/send",
    method: "post",
    data: {
      chatId: msgInfo.chatId,
      type: "friend",
      msg: msgInfo.msg,
      msgType: msgInfo.type,
    },
  });
}

export function sendGroupMsg(token, msgInfo) {
  return req({
    headers: { Authorization: token },
    url: "/chat/send",
    method: "post",
    data: {
      chatId: msgInfo.chatId,
      type: "group",
      msg: msgInfo.msg,
      msgType: msgInfo.type,
    },
  });
}

export function leaveRoom(token, roomId, isFriend) {
  return req({
    headers: { Authorization: token },
    url: `/chat/leaveRoom/${roomId}/${isFriend}`,
    method: "put"
  })
}
