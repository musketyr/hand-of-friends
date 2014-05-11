package domain

import groovyx.gaelyk.datastore.Entity
import groovyx.gaelyk.datastore.Parent
import com.google.appengine.api.datastore.Key

@Entity class Invitation {
    @Parent Key user
    @groovyx.gaelyk.datastore.Key String email
}
