if (!user) {
  redirect "/"
  return
}

def domainUser = domain.User.get(user.userId)

if (!domainUser) {
  domainUser = new domain.User(id: user.userId, email: user.email, nickname: user.nickname)
  domainUser.save()
}

params.limit = 10

request.posts = domain.Post.findAllByUser(user.userId, params)

forward "/feed.gtpl"
