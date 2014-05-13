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

if (memcache["posts:search:valid" as String]) {
  def cached = memcache["posts:search:${user.userId}:query:${params.query}" as String]
  if (cached) {
    request.posts = cached
    forward "/feed.gtpl"
    return
  }
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

memcache["posts:search:valid" as String] = true
memcache["posts:search::${user.userId}:query:${params.query}" as String] = request.posts

forward "/feed.gtpl"
