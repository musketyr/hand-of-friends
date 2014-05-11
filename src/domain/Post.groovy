package domain

import groovyx.gaelyk.datastore.*

@Entity class Post {
    String message
    String displayName
    @Indexed String userId
    @Indexed List<String> viewers
    @Indexed Date created = new Date()

    List<Comment> getComments() {
      def theKey = ['Post', datastoreKey] as com.google.appengine.api.datastore.Key
        Comment.findAll {
            ancestor theKey
            sort asc by created
        }
    }

    static List<Post> findAllByUser(String theUserId, Map params = [:]) {
        Post.findAll {
            where viewers == theUserId
            sort desc by created
            paginate params
        }
    }

}
