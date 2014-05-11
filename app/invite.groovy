if (!params.email) {
  redirect "/friends?noemail"
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

def invitation = new domain.Invitation(user: ['User', user.userId] as com.google.appengine.api.datastore.Key, email: params.email)
invitation.save()

defaultQueue << [
  url: '/sendInviteEmail.groovy',
  params: [email: params.email as String, nickname: user.nickname ?: user.email, userId: user.userId]

]

redirect "/feed?invited=$params.email"
