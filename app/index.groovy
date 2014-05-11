if (user) {
    redirect "/feed"
    return
}

forward "/index.gtpl"
