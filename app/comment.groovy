if (!params.id) {
  redirect "/feed?nocommentid"
  return
}

if (!params.text) {
  redirect "/feed?emptycomment"
  return
}

if (!user) {
  redirect "/"
  return
}

def comment = new domain.Comment(text: params.text as String, authorId: user.userId, post: ['Post', params.id as Long] as com.google.appengine.api.datastore.Key, displayName: user.nickname ?: user.email)
comment.save()

def post = domain.Post.get(params.id as Long)

def index = search.index('Post')

index.put {
  document(id: "$post.id") {
    message   text: post.message + "\n\n" + post.comments.collect { it.text }.join('\n')
    created   date: post.created
    viewers   atom: post.viewers
  }
}


redirect "/feed"
