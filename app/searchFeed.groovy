if (!user) {
  redirect "/"
  return
}

request.search = true

if (!params.query) {
  request.posts = []
  forward "/feed.gtpl"
  return
}

def documents = search.search {
  select ids from Post
  sort desc by created, new Date(0)
  where message =~ params.query
  and viewers   == user.userId
  limit 10
}

request.posts = documents.collect {
  domain.Post.get(it.id as Long)
}

forward "/feed.gtpl"
