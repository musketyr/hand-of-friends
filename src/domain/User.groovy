package domain

import groovyx.gaelyk.datastore.*

@Entity class User {
    @Key String id
    @Indexed List<String> friends
    @Indexed String email
    @Indexed String nickname
}
