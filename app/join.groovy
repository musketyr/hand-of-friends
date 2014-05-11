if (!params.id) {
  redirect "/feed?nouserid"
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

def friend = domain.User.get(params.id)

if (friend.friends?.size() >= 5 || domainUser.friends?.size() >= 5) {
  redirect "/friends?fullhand"
  return
}

def invitation = domain.Invitation.get(['User', params.id] as com.google.appengine.api.datastore.Key, user.email)

if (!invitation) {
  redirect "/friends?noinvitation"
  return
}

if (!friend.friends?.contains(domainUser.id)) {
  friend.friends = friend.friends ?: []
  friend.friends << domainUser.id
  friend.save()
}

if (!domainUser.friends?.contains(friend.id)) {
  domainUser.friends = domainUser.friends ?: []
  domainUser.friends << friend.id
  domainUser.save()
}

redirect "/friends?new=${params.id}"
