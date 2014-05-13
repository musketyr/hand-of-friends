package domain

import groovyx.gaelyk.datastore.*
import com.google.appengine.api.memcache.*

@Entity class Post implements Serializable {
    String message
    String displayName
    @Indexed String userId
    @Indexed List<String> viewers
    @Indexed Date created = new Date()
    String imageUrl

    List<Comment> getComments() {
      MemcacheService memcache = MemcacheServiceFactory.memcacheService

      List<Comment> cached = memcache["post:${id}:comments" as String]

      if (cached) {
        return cached
      }

      def theKey = ['Post', datastoreKey] as com.google.appengine.api.datastore.Key
      def ret = Comment.findAll {
          ancestor theKey
          sort asc by created
      }

      memcache["post:${id}:comments" as String] = ret

      ret
    }

    void clearCachedComments() {
      MemcacheService memcache = MemcacheServiceFactory.memcacheService
      memcache.delete("post:${id}:comments" as String)
      memcache.delete("posts:search:valid" as String)
      memcache.delete("posts:${userId}:valid" as String)
    }

    static List<Post> findAllByUser(String theUserId, Map params = [:]) {
      MemcacheService memcache = MemcacheServiceFactory.memcacheService

      List<Post> cached = memcache["posts:${theUserId}?${params.toQueryString()}" as String]

      if (memcache["posts:${theUserId}:valid" as String] && cached) {
        return cached
      }

      List<Post> ret = Post.findAll {
          where viewers == theUserId
          sort desc by created
          paginate params
      }

      memcache["posts:${theUserId}?${params.toQueryString()}" as String] = ret
      memcache["posts:${theUserId}:valid" as String]

      ret

    }

    static void clearCachedPosts(String theUserId) {
      MemcacheService memcache = MemcacheServiceFactory.memcacheService
      memcache.delete("posts:search:valid" as String)
      memcache.delete("posts:${theUserId}:valid" as String)
    }

}
