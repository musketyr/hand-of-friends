if (!params.name?.trim()) {
  redirect "/profile?emptyname"
  return
}

if (!user) {
  redirect "/"
  return
}

def domainUser = domain.User.get(user.userId)

if (!domainUser) {
  domainUser = new domain.User(id: user.userId, email: user.email, nickname: user.nickname)
}

domainUser.nickname = params.name
domainUser.save()

defaultQueue << [url: '/updateDisplayName.groovy', params: [id: user.userId]]

redirect "/profile"
