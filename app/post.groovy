if (!params.message) {
  redirect "/feed?emptymessage"
  return
}

if (!user) {
  redirect "/"
  return
}

new domain.Post(message: params.message as String, userId: user.userId, viewers: [user.userId]).save()

redirect "/feed"
