package domain

import groovyx.gaelyk.datastore.*

@Entity class Post {
    String message
    @Indexed String userId
    @Indexed List<String> viewers
    @Indexed Date created = new Date()

    List<Comment> getComments() {
      def theKey = ['Post', datastoreKey] as com.google.appengine.api.datastore.Key
        Comment.findAll {
            ancestor theKey
            sort desc by created
        }
    }

    static List<Post> findAllByUser(String theUserId) {
        Post.findAll {
            where userId == theUserId
            sort desc by created
        }
    }

}
