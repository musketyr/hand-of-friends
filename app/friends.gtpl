
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Friends</title>
  </head>
  <body>
      <div class="row">
        <div class="col-md-12">
          <h2>Invite real friend to Hand of Friends</h2>
          <form role="form" method="POST" action="/invite">
            <div class="input-group input-group-lg">
              <input type="email" class="form-control" name="email" placeholder="For example bob@uncles.com" required>
              <span class="input-group-btn">
                <button class="btn btn-primary" type="submit">Invite</button>
              </span>
            </div>
          </form>
        </div>
      </div>
      <div class="row">
        <div class="col-md-12">
          <h3>Your Friends</h3>
          <ul class="list-group">
          <% request.friends.each { fid ->
              def friend = domain.User.get(fid)
          %>
                <li class="list-group-item"><strong>$friend.nickname</strong> ($friend.email)</li>
          <% } %>
          </ul>
        </div>
      </div>
  </body>
</html>
