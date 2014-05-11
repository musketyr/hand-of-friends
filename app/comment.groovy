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

new domain.Comment(text: params.text as String, authorId: user.userId, post: ['Post', params.id as Long] as com.google.appengine.api.datastore.Key, displayName: user.nickname ?: user.email).save()

redirect "/feed"
