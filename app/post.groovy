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

def theViewers = domainUser.friends ?: []
theViewers << user.userId

def post = new domain.Post(message: params.message as String, userId: user.userId, viewers: theViewers, displayName: domainUser.nickname ?: domainUser.email, imageUrl: domainUser.avatarUrl)
post.save()

domain.Post.clearCachedPosts(domainUser.id)

def index = search.index('Post')

index.put {
  document(id: "$post.id") {
    message   text: post.message
    created   date: post.created
    viewers   atom: post.viewers
  }
}

redirect "/feed"
