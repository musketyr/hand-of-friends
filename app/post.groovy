if (!params.message) {
  redirect "/feed?emptymessage"
  return
}

if (!user) {
  redirect "/"
  return
}

def domainUser = domain.User.get(user.userId)

if (!domainUser) {
  domainUser = new domain.User(id: user.userId, email: user.email, nickname: user.nickname)
  domainUser.save()
}

def viewers = domainUser.friends ?: []
viewers << user.userId

new domain.Post(message: params.message as String, userId: user.userId, viewers: viewers, displayName: user.nickname ?: user.email).save()

redirect "/feed"
