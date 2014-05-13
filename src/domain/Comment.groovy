package domain

import groovyx.gaelyk.datastore.Entity
import groovyx.gaelyk.datastore.Parent
import groovyx.gaelyk.datastore.Indexed
import com.google.appengine.api.datastore.Key

@Entity class Comment implements Serializable {
    @Parent Key post
    @Indexed String authorId
    String displayName
    String text
    String imageUrl
    @Indexed Date created = new Date()
}
