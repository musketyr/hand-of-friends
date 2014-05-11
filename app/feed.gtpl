
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Feed</title>
  </head>
  <body>
      <div class="row">
        <div class="col-md-12">
          <form role="form" method="POST" action="/feed">
            <div class="input-group">
              <input type="text" class="form-control" name="message" placeholder="Share something valuable ..." required>
              <span class="input-group-btn">
                <button class="btn btn-primary" type="submit">Post</button>
              </span>
            </div>
          </form>
        </div>
      </div>
      <hr/>
      <div class="row">
        <% request.posts.each { post -> %>
          <div class="col-md-12">
            <blockquote>
              <p class="post-message"><strong>$post.displayName</strong> $post.message</p>
              <% post.comments.each { comment -> %>
                <footer class="post-comment"><strong>$comment.displayName</strong> $comment.text</footer>
              <% } %>
              <div class="row">
                <div class="comment-form col-md-12">
                  <form role="form" method="POST" action="/comment">
                    <input type="hidden" name="id" value="$post.id">
                    <div class="input-group input-group-sm">
                      <input type="text" class="form-control" name="text" placeholder="Commnet only if you have something to valuable to say ..." required>
                      <span class="input-group-btn">
                        <button class="btn btn-default" type="submit">Comment</button>
                      </span>
                    </div>
                  </form>
                </div>
              </div>
            </blockquote>
          </div>
        <% } %>
      </div>
  </body>
</html>
