
app {
    name="hand-of-friends"
    version="1"
}

web {
    security = [
        '*' : ['/feed', '/join/**', '/invite', '/comment', '/friends']
    ]
}

layout {
    mappings = [
            "/*": "/_layout.gtpl"
    ]
}
