if (!params.id) {
  log.error "missing id"
  return
}

def domainUser = domain.User.get(params.id)

if (!domainUser) {
  log.error "missing user"
  return
}

datastore.iterate {
  select all from 'Post'
  where userId == params.id
} each {
  it.displayName = domainUser.nickname
  it.imageUrl    = domainUser.avatarUrl
  it.save()
}

datastore.iterate {
  select all from 'Comment'
  where authorId == params.id
} each {
  it.displayName = domainUser.nickname
  it.imageUrl    = domainUser.avatarUrl
  it.save()
}

memcache.clearAll()
