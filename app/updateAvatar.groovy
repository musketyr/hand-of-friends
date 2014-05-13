def blobs = blobstore.getUploadedBlobs(request)
def blob = blobs["avatar"]


if (!blob) {
  redirect "/profile?missingavatar"
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

domainUser.avatarUrl = images.getServingUrl(blob)
domainUser.avatarKey = blob.keyString
domainUser.save()

defaultQueue << [url: '/updateDisplayName.groovy', params: [id: user.userId]]

redirect "/profile"
