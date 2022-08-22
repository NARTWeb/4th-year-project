import req from "@/request";

export function showFriendChatHistory(token, chatId) {
  return req({
    headers: { Authorization: token },
    url: "/chat/history",
    method: "get",
    data: {
      chatId,
      type: "friend",
    },
  });
}

export function showGroupChatHistory(token, chatId) {
  return req({
    headers: { Authorization: token },
    url: "/chat/history",
    method: "get",
    data: {
      chatId,
      type: "group",
    },
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
