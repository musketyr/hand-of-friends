if (!user) {
  redirect "/"
  return
}

request.posts = domain.Post.findAllByUser(user.userId)

forward "/feed.gtpl"
