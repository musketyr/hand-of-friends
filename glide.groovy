
app {
    name="hand-of-friends"
    version="1"
}

web {
    security = [
        '*' : ['/feed', '/join/**', '/invite', '/comment', '/friends', "/search"]
    ]
}

layout {
    mappings = [
            "/*": "/_layout.gtpl"
    ]
}
