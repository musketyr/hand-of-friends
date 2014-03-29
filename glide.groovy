
app {
    name="hand-of-friends"
    version="1"
}

web {
    security = [
        '*' : ['/feed']
    ]
}

layout {
    mappings = [
            "/*": "/_layout.gtpl"
    ]
}
