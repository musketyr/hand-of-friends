if (!user) {
  redirect "/"
  return
}

def domainUser = domain.User.get(user.userId)

request.friends = domainUser?.friends ?: []

forward "/friends.gtpl"
