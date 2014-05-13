
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>User Profile</title>
  </head>
  <body>
      <div class="row">
        <div class="col-md-12">
          <h3>User Profile</h3>
          <form class="form-horizontal" role="form" method="POST" action="/profile">
            <div class="form-group">
              <label for="displayName" class="col-sm-3 control-label">Display Name</label>
              <div class="col-sm-9">
                <input type="text" class="form-control" id="displayName" name="name" placeholder="Display Name" value="$request.user.nickname">
              </div>
            </div>
            <div class="form-group">
              <label for="email" class="col-sm-3 control-label">Email</label>
              <div class="col-sm-9">
                <input type="email" class="form-control" id="email" placeholder="Email" value="$request.user.email" disabled>
              </div>
            </div>
            <div class="form-group">
              <div class="col-sm-offset-3 col-sm-9">
                <button type="submit" class="btn btn-default">Update</button>
              </div>
            </div>
          </form>
          <h4>User Avatar</h4>
          <form role="form" class="form-horizontal" action="${blobstore.createUploadUrl('/profile/avatar')}" method="POST" enctype="multipart/form-data">
            <div class="form-group">
              <label for="displayName" class="col-sm-3 control-label">Avatar Image</label>
              <div class="col-sm-9">
                <div class="input-group">
                  <input type="file" name="avatar" class="form-control" accept="image/*">
                  <span class="input-group-btn">
                    <button class="btn btn-default" type="submit">Upload</button>
                  </span>
                </div>
              </div>
          </form>
        </div>
      </div>
  </body>
</html>
